package net.peptel.transit.codec.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import net.peptel.transit.codec.test.data.ConnectionEmulator;
import net.peptel.transit.codec.test.data.DAOEmulator;
import net.peptel.transit.codec.test.data.MessageEmulator;
import net.peptel.transit.codec.test.data.StatusEmulator;
import net.peptel.transit.dao.DAOException;
import net.peptel.transit.data.Message;
import net.peptel.transit.util.Log;

public class SpeedTest extends TestCase {
	
	public void testConnection() throws SQLException {
		ConnectionEmulator connection = ConnectionEmulator.instance();
		try {
			Connection conn = connection.open();
			if (conn == null) assertFalse(true);
			conn.close();
			
			Log.log.info("Test 1");
			assertTrue(true);
		} catch (DAOException e) {
			assertFalse(true);
		}
	}
	
	public void testmath() {
		int min = 0;
		int max = 5;
		int value = (int)(Math.random() * max) + min;
		Log.log.info("value = " + value);
		Log.log.info("Test 2");
		assertFalse(value > 5);
	}
	
	public void testMultipleInsert() {
		int min = 0;
		int max = 5;
		int value = (int)(Math.random() * max) + min;
		int codec = (int)(Math.random() * max) + min;
		
		List<Message> messages = new ArrayList<Message>(); 
		DAOEmulator dao = new DAOEmulator();
		try {
			for (int i = 0; i < 1000; i++) {
				int suff = (int)(Math.random() * i) + min;
				MessageEmulator message = new MessageEmulator(true, value, "101010555" + suff, codec, "010101512" + suff, 0, new Date(), new Date(), 10101);
				messages.add(message);
				
				Log.log.info("Message: " + message);
			}
			
			int[] ids = dao.insertOutgoing(messages);
			
			Log.log.info("Test 3");
			assertEquals(1000, ids.length);
		} catch (DAOException e) {
			Log.log.info("", e);
		}
	}
	
	public void testSelectMessages() {
		DAOEmulator dao = new DAOEmulator();
		try {
			List<Message> messages = dao.selectMessages(0);
			Log.log.info("Test 1");
			assertEquals(33, messages.size());
		} catch (DAOException e) {
			assertFalse(true);
		}
	}
	
	public void testUpdateStatus() {
		DAOEmulator dao = new DAOEmulator();
		//01010151224
		StatusEmulator status = new StatusEmulator(3, new Date(), 0, "010101512");
		try {
			dao.updateStatus(status);
			Log.log.info("Test 1");
			assertTrue(true);
		} catch (DAOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testSelectReport() {
		DAOEmulator dao = new DAOEmulator();
		try {
			int account = 0;
			int codec = 0;
			Date finishPeriod = new Date();
			Date startPeriod = new Date();
			List<Message> messages = dao.selectReport(startPeriod , finishPeriod, codec, account);
			assertEquals(0, messages.size());
		} catch (DAOException e) {
			e.printStackTrace();
			assertTrue(false);
		}	
	}
	
	public void insertRecords() {
		int min = 0;
		int max = 5;
		int value = (int)(Math.random() * max) + min;
		int codec = (int)(Math.random() * max) + min;
		
		List<Message> messages = new ArrayList<Message>();
		for (int i = 0; i < 1000; i++) {
			int suff = (int)(Math.random() * i) + min;
			MessageEmulator message = new MessageEmulator(true, value, "101010555" + suff, codec, "010101512" + suff, 0, new Date(), new Date(), 10101);
			message.setMessageID(i);
			messages.add(message);
			
			Log.log.info("Message: " + message);
		}

		DAOEmulator dao = new DAOEmulator();
		
		try {
			dao.insertOutgoingLog(messages);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
