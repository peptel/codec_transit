package net.peptel.transit.codec.test.emulator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.peptel.transit.codec.test.data.DAOEmulator;
import net.peptel.transit.codec.test.data.MessageEmulator;
import net.peptel.transit.dao.DAOException;
import net.peptel.transit.data.Message;
import net.peptel.transit.util.Log;

public class InsertEmulator extends Thread {

	@Override
	public void run() {
		int min = 0;
		int max = 2;
		
		for (int a = 0; a < 100; a++) {
			
			
			List<Message> messages = new ArrayList<Message>();
			for (int i = 0; i < 100000; i++) {
				int value = (int) (Math.random() * max) + min + 2;
				int codec = (int) (Math.random() * max) + min;
				int suff = (int) (Math.random() * i) + min;

				MessageEmulator message = new MessageEmulator(true, value, "101010555" + suff, codec,
						"010101512" + suff, 0, new Date(), new Date(), 10101);
				messages.add(message);

//				Log.log.info("Message: " + message);
			}
			Calendar c = Calendar.getInstance();
			long start = c.getTimeInMillis();
			DAOEmulator dao = new DAOEmulator();
			try {
				dao.insertOutgoing(messages);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			long delta = (Calendar.getInstance().getTimeInMillis() - start)/1000;
			Log.log.info("TIME CHECK. Insert 1000 records: " + delta);
		}
	}
}
