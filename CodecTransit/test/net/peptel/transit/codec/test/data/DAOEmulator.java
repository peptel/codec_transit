package net.peptel.transit.codec.test.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.peptel.transit.dao.DAOException;
import net.peptel.transit.dao.DAOManagement;
import net.peptel.transit.data.Message;
import net.peptel.transit.data.Status;
import net.peptel.transit.util.Log;

public class DAOEmulator implements DAOManagement {

	private static final String INSERT_OUTGOING = "insert into outgoing_test(account, account_message_id, codec, codec_message_id, sent_date, status, status_date) values(?, ?, ?, ?, NOW(), ?, NOW());";
	private static final String SELECT_OUTGOING = "select id, account, account_message_id, codec, codec_message_id, sent_date, status, status_date, status_forward, priority from outgoing_test where id in (select id from outgoing_test where sent_date < NOW() and status = 0 and codec = ? FOR UPDATE SKIP LOCKED LIMIT 2400) limit 800";
	// private static final String UPDATE_OUTGOING = "update outgoing_test set
	// status = 1 where id in (select id from outgoing_test where status = 0 LIMIT
	// 100) limit 33";
	private static final String UPDATE_OUTGOING_PART = "update outgoing_test set status = 1 where id in (";
	
//	private static final String UPDATE_OUTGOING_LOG = "update outgoing_log_test set status = ?, status_date = now() where codec_message_id = ?";
	
	private static final String INSERT_OUTGOING_LOG_STATUS = "insert into outgoing_log_status_test(codec, codec_message_id, status, status_date) values(?, ?, ?, NOW())";
	
	
	private static final String SELECT_OUTGOING_LOG = "select account, account_message_id, codec, codec_message_id, sent_date, outgoing_id from outgoing_log_test where sent_date > ? and sent_date  < ? and account = ? and codec = ?";
	
//	private static final String INSERT_OUTGOING_LOG = "insert into outgoing_log_test(account, account_message_id, codec, codec_message_id, sent_date, status_date, status, outgoing_id) values(?, ?, ?, ?, NOW(), NOW(), 0, ?)";
	private static final String INSERT_OUTGOING_LOG = "insert into outgoing_log_test(account, account_message_id, codec, codec_message_id, sent_date, outgoing_id) values(?, ?, ?, ?, NOW(), ?)";

	@Override
	public int insertOutgoingLog(Message message) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionEmulator.instance().open();
			pstmt = con.prepareStatement(INSERT_OUTGOING_LOG);

			// account, account_message_id, codec, codec_message_id, sent_date, status_date,
			// outgoing_id (?, ?, ?, ?, NOW(), NOW(), ?)
			Log.log.info("insert into output_log Message " + message);

			pstmt.setInt(1, message.getAccountID());
			pstmt.setString(2, message.getAccountMessageID());
			pstmt.setInt(3, message.getCodec());
			pstmt.setString(4, message.getCodecMessageID());
			pstmt.setLong(5, message.getMessageID());
			pstmt.executeUpdate();

			// ResultSet rs = statement.executeQuery("INSERT ... RETURNING ID");
			// rs.next();
			// rs.getInt(1);

			// pstmt.executeUpdate();
			// rs = pstmt.getGeneratedKeys();
			// if (rs.next()) {
			// return rs.getInt(1);
			// } else {
			// throw new DAOException("Can't get seq outgoing log");
			// }

			return 0;

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}

			Log.log.error("DB INSERT OUTGOING_LOG ERROR", e);
			throw new DAOException("Insert user in DB exception", e);
		} finally {
			ConnectionEmulator.instance().close(con, pstmt, rs);
		}
	}

	@Override
	public int[] insertOutgoingLog(List<Message> messages) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = ConnectionEmulator.instance().open();

		try {
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_OUTGOING_LOG);

			// account, account_message_id, codec, codec_message_id, sent_date, status_date,
			// outgoing_id (?, ?, ?, ?, NOW(), NOW(), ?)
			for (Message message : messages) {
//				Log.log.info("insert into output_log Message " + message);

				pstmt.setInt(1, message.getAccountID());
				pstmt.setString(2, message.getAccountMessageID());
				pstmt.setInt(3, message.getCodec());
				pstmt.setString(4, message.getCodecMessageID());
				pstmt.setLong(5, message.getMessageID());
				pstmt.addBatch();
			}

			pstmt.executeBatch();

			int[] logIDs = new int[messages.size()];

			con.commit();

			return logIDs;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}

			Log.log.error("DB INSERT OUTGOING_LOG ERROR", e);
			throw new DAOException("Insert user in DB exception", e);
		} finally {
			ConnectionEmulator.instance().close(con, pstmt, rs);
		}
	}

	@Override
	public void insertStatusForwardRequest(Status message) throws DAOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void insertStatusForwardRequest(List<Message> messages) throws DAOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void recoveryStatus(List<Message> messages) throws DAOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void recoveryStatus(Status message) throws DAOException {
		// TODO Auto-generated method stub
	}

	public int insertOutgoing(MessageEmulator message) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = ConnectionEmulator.instance().open();
		try {
			pstmt = con.prepareStatement(INSERT_OUTGOING);

			// account, account_message_id, codec, codec_message_id, sent_date, status,
			// status_date
			pstmt.setInt(1, message.getAccountID());
			pstmt.setString(2, message.getAccountMessageID());
			pstmt.setInt(3, message.getCodec());
			pstmt.setString(4, message.getCodecMessageID());
			pstmt.setInt(5, message.getStatus());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			Log.log.error("DB INSERT smtp_outgoing_log ERROR", e);
			throw new DAOException("Insert smtp_outgoing_log in DB exception", e);
		} finally {
			ConnectionEmulator.instance().close(con, pstmt, rs);
		}
	}

	public int[] insertOutgoing(List<Message> messages) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = ConnectionEmulator.instance().open();

		try {
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_OUTGOING);

			for (Message message : messages) {
//				Log.log.info("insert into output Message " + message);

				pstmt.setInt(1, message.getAccountID());
				pstmt.setString(2, message.getAccountMessageID());
				pstmt.setInt(3, message.getCodec());
				pstmt.setString(4, message.getCodecMessageID());
				pstmt.setInt(5, message.getStatus());
				pstmt.addBatch();
			}

			pstmt.executeBatch();

			int[] logIDs = new int[messages.size()];
			// int i = 0;
			// rs = pstmt.getGeneratedKeys();
			// while (rs.next()) {
			// Integer logID = rs.getInt(1);
			// logIDs[i] = logID;
			// Log.log.debug("insert into output. id = " + logID);
			// }

			con.commit();

			return logIDs;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}

			Log.log.error("DB INSERT OUTGOING_LOG ERROR", e);
			throw new DAOException("Insert user in DB exception", e);
		} finally {
			ConnectionEmulator.instance().close(con, pstmt, rs);
		}
	}

	/**
	 * 
	 * @param codec
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<Message> selectMessages(int codec) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Message> messages = new ArrayList<Message>();
		Calendar c = Calendar.getInstance();

		con = ConnectionEmulator.instance().open();
		try {
			con.setAutoCommit(false);

			long start = c.getTimeInMillis();
			pstmt = con.prepareStatement(SELECT_OUTGOING);
			pstmt.setInt(1, codec);
			rs = pstmt.executeQuery();

			StringBuilder sb = new StringBuilder();
			while (rs.next()) {
				long messgeID = rs.getLong("id");
				sb.append(messgeID);
				sb.append(", ");
				MessageEmulator messageEmulator = new MessageEmulator(rs.getInt("status_forward") == 1,
						rs.getInt("account"), rs.getString("account_message_id"), rs.getInt("codec"),
						rs.getString("codec_message_id"), rs.getInt("status"), rs.getTimestamp("sent_date"),
						rs.getTimestamp("status_date"), rs.getLong("priority"));
				messageEmulator.setMessageID(messgeID);
				messages.add(messageEmulator);
			}

			long delta = (Calendar.getInstance().getTimeInMillis() - start) / 1000;
			Log.log.info("TIME CHECK. codec " + codec + ". SELECT FOR UPDATE (select) " + messages.size()
					+ " records: " + delta);

			if (!messages.isEmpty()) {
				c = Calendar.getInstance();
				start = c.getTimeInMillis();
				Log.log.info(UPDATE_OUTGOING_PART + sb.subSequence(0, sb.length() - 2) + ")");
				pstmt = con.prepareStatement(UPDATE_OUTGOING_PART + sb.subSequence(0, sb.length() - 2) + ")");
				pstmt.executeUpdate();

				delta = (Calendar.getInstance().getTimeInMillis() - start) / 1000;
				Log.log.info("TIME CHECK. codec " + codec + ". SELECT FOR UPDATE (update) " + messages.size()
						+ " records: " + delta);
			}

			con.commit();

			return messages;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}

			Log.log.error("DB INSERT OUTGOING_LOG ERROR", e);
			throw new DAOException("Insert user in DB exception", e);
		} finally {
			ConnectionEmulator.instance().close(con, pstmt, rs);
		}
	}

	public void insertStatus() throws DAOException {
		
	}
	
	public int updateStatus(StatusEmulator status) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = ConnectionEmulator.instance().open();
		try {
			//insert into outgoing_log_status_test(codec, codec_message_id, status, status_date) values(?, ?, ?, NOW())
			pstmt = con.prepareStatement(INSERT_OUTGOING_LOG_STATUS);

			
			pstmt.setInt(1, status.getCodec());
			pstmt.setString(2, status.getCodecMessageID());
			pstmt.setInt(3, status.getStatus());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			Log.log.error("DB INSERT smtp_outgoing_log ERROR", e);
			throw new DAOException("Insert smtp_outgoing_log in DB exception", e);
		} finally {
			ConnectionEmulator.instance().close(con, pstmt, rs);
		}
	}

	public List<Message> selectReport(Date startPeriod, Date finishPeriod, int codec, int account) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Message> messages = new ArrayList<Message>();

		con = ConnectionEmulator.instance().open();
		try {
			pstmt = con.prepareStatement(SELECT_OUTGOING_LOG);

			pstmt.setTimestamp(1, new Timestamp(startPeriod.getTime()));
			pstmt.setTimestamp(2, new Timestamp(finishPeriod.getTime()));
			pstmt.setInt(3, account);
			pstmt.setInt(4, codec);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// select account, account_message_id, codec, codec_message_id, sent_date,
				// status_date, outgoing_id from outgoing_log_test where sent_date > ? and
				// sent_date < ? and account = ? and codec = ?
				messages.add(new MessageEmulator(rs.getInt("account"), rs.getString("account_message_id"),
						rs.getInt("codec"), rs.getString("codec_message_id"), 0,
						rs.getDate("sent_date"), new Date()));
			}

			return messages;
		} catch (SQLException e) {
			Log.log.error("DB INSERT smtp_outgoing_log ERROR", e);
			throw new DAOException("Insert smtp_outgoing_log in DB exception", e);
		} finally {
			ConnectionEmulator.instance().close(con, pstmt, rs);
		}
	}

	@Override
	public void updateVerification(int verificationID, int transactionID) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
