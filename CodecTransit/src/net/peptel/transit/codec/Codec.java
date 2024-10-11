package net.peptel.transit.codec;

import java.io.IOException;
import java.util.List;

import net.peptel.transit.dao.DAOManagement;
import net.peptel.transit.data.Message;

/**
 * @author Andy Peptyuk
 */
public interface Codec {
	/**
	 * Select Message list from outgoing table and update status 
	 * @return
	 */
	List<Message> select();
	
	/**
	 * Send message to Codec
	 * @param message
	 * @throws IOException
	 */
	void send(Message message) throws IOException;
	
	/**
	 * Send messages to Codec
	 * @param message
	 * @throws IOException
	 */
	void send(List<Message> message) throws IOException;
	
	/**
	 * The posibility to work in single or multiple messages sending mode
	 * @param mode
	 */
	void setSendingMode(SendingMode mode);
	SendingMode getSendingMode();

	/**
	 * Codec has reference to particular activity, like payment, task management, notification management, others
	 * @param mode
	 */
	void setActivityMode(ActivityMode mode);
	ActivityMode getActivityMode();
	
	/**
	 * Returns id from Codec type
	 * @return
	 */
	int getCodecType();
	
	/**
	 * Set Codec Type
	 * @param codecType
	 */
	void setCodecType(int codecType);
	
	/**
	 * Return Codec name
	 * @return
	 */
	String getCodecName();
	
	/**
	 * Set Code name
	 * @param codecName
	 */
	void setCodecName(String codecName);

	/**
	 * Set monitor to manage delay
	 * @param obj
	 */
	void setMonitor(CodecMonitor monitor);
	
	/**
	 * set class to integrate DB
	 * @param management DAOManagement
	 */
	void setDAO(DAOManagement management);
	
	/**
	 * get regular timeout for selection operation.
	 * @return
	 */
	long getSelectionTimeout();

	/**
	 * get regular timeout for selection operation.
	 * @return
	 */
	long getExceptionSelectionTimeout();

	/**
	 * Codec has been stoped
	 */
	void exit();
}
