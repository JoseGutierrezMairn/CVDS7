package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception {
	
	public PersistenceException(String msg, Throwable lanzar) {
		super(msg);
	}
}
