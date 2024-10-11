package net.peptel.transit.data;

import java.util.Date;
/**
 * @author Andy Peptyuk
 */
public interface Status {

	/**
	 * returns status value
	 * @return
	 */
	int getStatus();

	/**
	 * returns status change date
	 * @return
	 */
	Date getStatusDate();

	/**
	 * returns codec type value
	 * @return
	 */
	int getCodec();

	/**
	 * returns id of message getting from channel
	 * @return
	 */
	String getCodecMessageID();

}