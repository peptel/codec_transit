package net.peptel.transit.codec.test.emulator;

import java.util.Date;

import net.peptel.transit.codec.test.data.DAOEmulator;
import net.peptel.transit.codec.test.data.StatusEmulator;
import net.peptel.transit.dao.DAOException;

public class UpdateEmulator extends Thread {
	@Override
	public void run() {
		int min = 0;
		int max = 3;
		
		DAOEmulator dao = new DAOEmulator();

		for (int i = 0; i < 10000; i++) {
			// 01010151224
			int suff = (int) (Math.random() * i) + min;
			int codec = (int) (Math.random() * max) + min;
//			MessageEmulator message = new MessageEmulator(true, 0, "1010105551", 0, "010101512" + suff, 7, new Date(),
//					new Date(), 10101);
			StatusEmulator status = new StatusEmulator(3, new Date(), codec, "010101512" + suff);
			try {
				dao.updateStatus(status);
			} catch (DAOException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
