package edu.eci.cvds.sampleprj.dao;

import java.util.Date;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {

   public void save(Cliente cli) throws PersistenceException;

   public Cliente load(int id) throws PersistenceException;
   
   public void rentarItem(int idcliente,int iditem, Date desde, Date hasta) throws PersistenceException;

}