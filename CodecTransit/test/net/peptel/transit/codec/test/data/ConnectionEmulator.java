package net.peptel.transit.codec.test.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.peptel.transit.dao.DAOConnection;
import net.peptel.transit.dao.DAOException;

public class ConnectionEmulator implements DAOConnection {

	private static String db_connect_str;
	private static String db_userid;
	private static String db_password;
	
	private static ConnectionEmulator instance;
	
	static {
		db_connect_str = "jdbc:postgresql://172.20.128.19:5432/viber";
		db_userid = "viber";
		db_password = "v1b3rab3rus";
	}

	
	private ConnectionEmulator() {
	}
	
	public static ConnectionEmulator instance() {
		if (instance == null) instance = new ConnectionEmulator();
		
		return instance;
	}

	@Override
	public void close(Connection con, Statement stmt, ResultSet rs) throws DAOException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException sqle) {
			throw new DAOException("Unable to close connection to Informix DB !!!", sqle);
		}
	}

	@Override
	public Connection open() throws DAOException {	
		Connection conn;
		try {
			Class.forName("org.postgresql.Driver").newInstance();

			conn = DriverManager.getConnection(db_connect_str, db_userid, db_password);
			return conn;
		} catch (Exception e) {
			conn = null;
			throw new DAOException("Postgress DB not available", e);
		}
	}

}
