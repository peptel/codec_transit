package net.peptel.transit.codec.test.data;

import java.util.Date;

import net.peptel.transit.data.Message;

public class MessageEmulator implements Message {

	private long id;
	private boolean statusForward;
	private int accountID;
	private String accountMessageID;
	private int codec;
	private String codecMessageID;
	private int status;
	private Date sentDate;
	private Date statusDate;
	private long priority;

	public MessageEmulator() {
	}
	
	public MessageEmulator(boolean statusForward, int accountID, String accountMessageID, int codec,
			String codecMessageID, int status, Date sentDate, Date statusDate, long priority) {
		this.statusForward = statusForward;
		this.accountID = accountID;
		this.accountMessageID = accountMessageID;
		this.codec = codec;
		this.codecMessageID = codecMessageID;
		this.status = status;
		this.sentDate = sentDate;
		this.statusDate = statusDate;
		this.priority = priority;
	}

	public MessageEmulator(int accountID, String accountMessageID, int codec,
			String codecMessageID, int status, Date sentDate, Date statusDate) {
		this.statusForward = true;
		this.accountID = accountID;
		this.accountMessageID = accountMessageID;
		this.codec = codec;
		this.codecMessageID = codecMessageID;
		this.status = status;
		this.sentDate = sentDate;
		this.statusDate = statusDate;
	}


	@Override
	public boolean isStatusForward() {
		return statusForward;
	}

	@Override
	public int getAccountID() {
		return accountID;
	}

	@Override
	public String getAccountMessageID() {
		return accountMessageID;
	}

	@Override
	public int getCodec() {
		return codec;
	}

	@Override
	public String getCodecMessageID() {
		return codecMessageID;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public Date getSentDate() {
		return sentDate;
	}

	@Override
	public Date getStatusDate() {
		return statusDate;
	}

	@Override
	public long getPriority() {
		return priority;
	}

	@Override
	public String toString() {
		return "MessageEmulator [statusForward=" + statusForward + ", accountID=" + accountID + ", accountMessageID="
				+ accountMessageID + ", codec=" + codec + ", codecMessageID=" + codecMessageID + ", status=" + status
				+ ", sentDate=" + sentDate + ", statusDate=" + statusDate + ", priority=" + priority + "]";
	}

	@Override
	public long getMessageID() {
		return id;
	}
	
	public void setMessageID(long id) {
		this.id = id;
	}
}
