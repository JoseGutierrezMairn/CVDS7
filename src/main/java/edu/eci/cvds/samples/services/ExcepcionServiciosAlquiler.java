package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8119099821207736989L;

	public ExcepcionServiciosAlquiler(String msg, PersistenceException lanzar) {
		super(msg, lanzar);
	}
	
	public ExcepcionServiciosAlquiler(String msg) {
		super(msg);
	}
}
