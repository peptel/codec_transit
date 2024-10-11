package net.peptel.transit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Andy Peptyuk
 */
public interface DAOConnection {
	
	Connection open() throws DAOException;

	void close(Connection con, Statement stmt, ResultSet rs) throws DAOException;
	
}
