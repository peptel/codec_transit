package net.peptel.transit.dao;

/**
 * @author Andy Peptyuk
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}
	
	public DAOException(String exception) {
		super(exception);
	}
	
	public DAOException(String desc, Throwable e) {
		super(desc, e);
	}
	
}
