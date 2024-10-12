package net.peptel.transit.codec;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.peptel.transit.dao.DAOException;
import net.peptel.transit.dao.DAOManagement;
import net.peptel.transit.data.Message;
import net.peptel.transit.data.Status;
import net.peptel.transit.fraud.FraudMonitor;
import net.peptel.transit.util.Log;

/**
 * @author Andy Peptyuk
 */
public abstract class CodecAdapter implements Runnable, Codec {
	protected FraudMonitor fraudMonitor;

	private static final long CODEC_SELECTION_TIMEOUT = 1 * 60 * 1000;// 100;
	private static final long CODEC_EXCEPTION_TIMEOUT = 5 * 60 * 1000;// 100;

	protected SendingMode sendingMode;
	protected ActivityMode activityMode;

	private CodecMonitor monitor;

	protected int codecType;
	protected String codecName;

	private DAOManagement dao;

	@Override
	public void run() {
		Log.log.info("Start Codec " + getCodecType() + " name: " + getCodecName());

		int sleepTimeOut = 1;
		long startNotificationTime = 0, deltaNotificationTime = 0, deltaNotificationSec = 0, startSleep = 0, realSleep = 0;
		
		

		while (monitor.isRunning()) {
			try {
				startNotificationTime = Calendar.getInstance().getTimeInMillis();
				Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() + " try select");
				List<Message> messages = select();
				deltaNotificationTime = Calendar.getInstance().getTimeInMillis() - startNotificationTime;
				Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() + " select " + messages.size() + ", select_time(sec) = " + deltaNotificationTime / 1000);

				if (messages != null && !messages.isEmpty()) {

					startNotificationTime = Calendar.getInstance().getTimeInMillis();

					sleepTimeOut = 1;

					Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() + " select " + messages.size() + " messeges.");

					switch (sendingMode) {
					case SINGLE_MODE:
						
						Log.log.info("SINGLE_MODE");
						
						int coresAmount = Runtime.getRuntime().availableProcessors(); 
						ExecutorService exec = Executors.newFixedThreadPool(coresAmount);
						
						for (Message message : messages) {

							exec.execute(new Runnable() {
								
								@Override
								public void run() {
									try {
										Log.log.info("New Thread. Codec " + getCodecType() + " name: " + getCodecName() +  ". Try send");
										send(message);
										Log.log.info("New Thread. Codec " + getCodecType() + " name: " + getCodecName() +  ". Sent");
										
									} catch (IOException e) {
										Log.log.error("New Thread. Codec " + getCodecType() + " name: " + getCodecName() +  ". Cant send message: " + message, e);
										backstatus(message, dao);
										Log.log.error("New Thread. Codec " + getCodecType() + " name: " + getCodecName() +  ". Cant send message: restore status");
										
									}		
								}
							
							});
						}
						
						exec.shutdown();

						break;
					case MULTIPLY_MODE:
						Log.log.info("MULTIPLY_MODE");
						try {
							Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() +  ". Try send");
							send(messages);
							Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() +  ". Sent");
						} catch (IOException e) {
							Log.log.error("Codec " + getCodecType() + " name: " + getCodecName() +  ". Cant send messages ", e);
							backstatus(messages, dao);
							Log.log.error("Codec " + getCodecType() + " name: " + getCodecName() +  ". Cant send message: restore status");
							sleepTimeOut = sleepTimeOut == 10 ? 1 : sleepTimeOut + 1;

							synchronized (monitor) {
								try {
									Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() +  ". EXCEPTION SLEEP TIMEOUT: " + (getExceptionSelectionTimeout() * sleepTimeOut) );
									monitor.wait(getExceptionSelectionTimeout() * sleepTimeOut);
								} catch (InterruptedException ie) {
									Log.log.error("WAIT SELECT INTERRUPTED.");
									sleepTimeOut = 1;
								}
							}
						}

						break;
					}

					deltaNotificationTime = Calendar.getInstance().getTimeInMillis() - startNotificationTime;

					Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() +  ". time: " + deltaNotificationTime + " TPS : ");

					deltaNotificationSec = deltaNotificationTime / 1000;

					Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() +  ".  select " + messages.size() + "messeges., time: " + deltaNotificationTime
							+ " msec. TPS : " + (deltaNotificationSec == 0 ? "" : messages.size() / deltaNotificationSec));
					
					messages.clear();
					messages = null;
					
					try {
						Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() +  ". SLEEP " + getSelectionTimeout() + " SUCCESS SCENARIO.");
						Thread.sleep(getSelectionTimeout());
					} catch (InterruptedException ex) {
						Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() +  ". INTERRUPT");
					}

				} else {
					sleepTimeOut = sleepTimeOut == 10 ? 1 : sleepTimeOut + 1;

					Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() + ". Coudn't select messages");
					Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() + ". Try sleep " + getExceptionSelectionTimeout() * sleepTimeOut);
					startSleep = Calendar.getInstance().getTimeInMillis();

					// Thread.sleep(CODEC_REQUEST_TIMEOUT * sleepTimeOut);
					synchronized (monitor) {
						try {
							Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() +  ". SELECT NO MESSAGES SLEEP TIMEOUT: " + (getExceptionSelectionTimeout() * sleepTimeOut) );
							monitor.wait(getExceptionSelectionTimeout() * sleepTimeOut);
						} catch (InterruptedException e) {
							Log.log.error("WAIT SELECT INTERRUPTED.");
							sleepTimeOut = 1;
						}
					}

					realSleep = Calendar.getInstance().getTimeInMillis() - startSleep;
					Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() + ".  Real sleep " + realSleep);
				}

			} catch (Exception e) {
				Log.log.error("Codec " + getCodecType() + " name: " + getCodecName() +  ". Global codac exception. ", e);
				
				// Thread.sleep(CODEC_REQUEST_TIMEOUT * sleepTimeOut);
				synchronized (monitor) {
					try {
						Log.log.error("Codec " + getCodecType() + " name: " + getCodecName() +  ". SELECT NO MESSAGES SLEEP TIMEOUT: " + (getExceptionSelectionTimeout() * sleepTimeOut) );
						monitor.wait(getExceptionSelectionTimeout() * sleepTimeOut);
					} catch (InterruptedException ex) {
						Log.log.error("WAIT SELECT INTERRUPTED.");
						sleepTimeOut = 1;
					}
				}
			}
		}

		exit();
		
		Log.log.info("Codec " + getCodecType() + " name: " + getCodecName() + " STOPED! Monitor status: " + monitor.isRunning());
	}

	private void backstatus(List<Message> messages, DAOManagement dao) {
		try {
			Log.log.info("recoveryDBReg. Codec " + getCodecType() + " size = " + messages.size());
			dao.recoveryStatus(messages);
			Log.log.info("recoveryDBReg. Finish. Codec " + getCodecType() + " size = " + messages.size());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			Log.log.error("Error recovery message: " + Arrays.deepToString(messages.toArray(new Message[0])), e);
		}
	}

	private void backstatus(Status message, DAOManagement dao) {
		try {
			dao.recoveryStatus(message);
			Log.log.info("recoveryDBReg. Codec " + message);
		} catch (DAOException e) {
			Log.log.error("Error recovery message " + message, e);
		}
	}

	@Override
	public void setSendingMode(SendingMode mode) {
		this.sendingMode = mode;
	}

	@Override
	public SendingMode getSendingMode() {
		return sendingMode;
	}

	@Override
	public void setActivityMode(ActivityMode mode) {
		this.activityMode = mode;
	}

	@Override
	public ActivityMode getActivityMode() {
		return activityMode;
	};

	/**
	 * registry incoming message into DB
	 * 
	 * @param message is single record for outgoing_log
	 * @param codecID
	 * @return
	 */
	protected int sendDBReg(Message message, DAOManagement dao) {
		try {
			Log.log.info("Codec " + getCodecType() + " insert into LOG. message = " + message);
			int logID = dao.insertOutgoingLog(message);

			if (message.isStatusForward()) {
				Log.log.info("Insert Status Forward Request. message " + message);
				dao.insertStatusForwardRequest(message);
			}

			return logID;
		} catch (DAOException e) {
			Log.log.error(e);
			return 0;
		}
	}

	/**
	 * registry incoming message into DB
	 * 
	 * @param message
	 * @param codecID
	 */
	protected int[] sendDBReg(List<Message> messages, DAOManagement dao) {
		try {
			int[] ids = dao.insertOutgoingLog(messages);
			Log.log.info("Codec " + getCodecType() + "  = " + messages.size());

			if (messages.get(0).isStatusForward()) {
				Log.log.info("Insert Status Forward Request. messages size: " + messages.size());
				dao.insertStatusForwardRequest(messages);
			}

			return ids;
		} catch (DAOException e) {
			Log.log.error(e);

			return null;
		}
	}

	@Override
	public void setMonitor(CodecMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void setCodecType(int codecType) {
		this.codecType = codecType;
	}

	@Override
	public void setCodecName(String codecName) {
		this.codecName = codecName;
	}

	public void setDAO(DAOManagement dao) {
		this.dao = dao;
	}

	public String getCodecName() {
		return codecName;
	}

	public int getCodecType() {
		return codecType;
	}

	@Override
	public long getSelectionTimeout() {
		return CODEC_SELECTION_TIMEOUT;
	}

	@Override
	public long getExceptionSelectionTimeout() {
		return CODEC_EXCEPTION_TIMEOUT;
	}

	public void exit() {
		Log.log.info("stub. codec " + codecName + " has been closed");
	}

	@Override
	public String toString() {
		return "Codec [sendingMode=" + sendingMode + ", activityMode=" + activityMode + ", codecType=" + codecType
				+ ", codecName=" + codecName + "]";
	}

}
