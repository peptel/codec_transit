package net.peptel.transit.dao;

import java.util.List;

import net.peptel.transit.data.Message;
import net.peptel.transit.data.Status;

/**
 * @author Andy Peptyuk
 */
public interface DAOManagement {
	void recoveryStatus(Status message) throws DAOException;

	void recoveryStatus(List<Message> messages) throws DAOException;

	int insertOutgoingLog(Message message) throws DAOException;

	int[] insertOutgoingLog(List<Message> messages) throws DAOException;

	void insertStatusForwardRequest(Status message) throws DAOException;

	void insertStatusForwardRequest(List<Message> messages) throws DAOException;

	void updateVerification(int verificationID, int transactionID) throws DAOException;
}
