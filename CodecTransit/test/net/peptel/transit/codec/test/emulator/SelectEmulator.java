package net.peptel.transit.codec.test.emulator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.peptel.transit.codec.test.data.DAOEmulator;
import net.peptel.transit.dao.DAOException;
import net.peptel.transit.data.Message;
import net.peptel.transit.util.Log;

public class SelectEmulator extends Thread {

	@Override
	public void run() {
		DAOEmulator dao = new DAOEmulator();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 1, 1);
		Date startPeriod = calendar.getTime();
		
		while (true) {
			try {
				int account = 0;
				int codec = 0;
				Date finishPeriod = new Date();
				Calendar c = Calendar.getInstance();
				long start = c.getTimeInMillis();
				List<Message> messages = dao.selectReport(startPeriod, finishPeriod, codec, account);
				
				long delta = (Calendar.getInstance().getTimeInMillis() - start)/1000;
				Log.log.info("TIME CHECK. SELECT "+messages.size()+" records: " + delta);

				
				Log.log.info("SELECTED MESSAGES SIZE: " + messages.size());
				
			} catch (DAOException e) {
				e.printStackTrace();
			} 
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}
