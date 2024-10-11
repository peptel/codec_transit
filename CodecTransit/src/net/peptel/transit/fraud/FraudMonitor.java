package net.peptel.transit.fraud;

import net.peptel.transit.data.Message;

public interface FraudMonitor {
	/**
	 * Check is this message fraud
	 * @param message
	 * @return true if @message is fraud or false if not
	 */
	FraudType check(Message message);
	
	/**
	 * If @message fraund this method send notification to 
	 * @param message
	 */
	void sendNotification(Message message);
	
	/**
	 * Save desc to DB
	 * @param message
	 */
	void saveFraud(Message message);
	
	/**
	 * Save desc to DB
	 * @param message
	 * * @param type
	 */
	void saveFraud(Message message, FraudType type);
}
