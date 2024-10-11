package net.peptel.transit.data;

import java.util.Date;

/**
 * 
 * @author Andy Peptyuk
 *
 */
public interface Message extends Status {
	/**
	 * returns message id from system (outgoing_id)
	 * @return
	 */
	long getMessageID();
	
	/**
	 * is this message require to forward status to user
	 * @return
	 */
	boolean isStatusForward();

	/**
	 * return id of accounts table
	 * @return
	 */
	int getAccountID();

	/**
	 * returns message id was initial set by generator account (your client) which is use your system  
	 * @return
	 */
	String getAccountMessageID();

	/**
	 * returns message sent date.
	 * @return
	 */
	Date getSentDate();
	
	/**
	 * returns message priority value. could be use for ordering select from initial 'outgoing' table  
	 * @return
	 */
	long getPriority();
}
