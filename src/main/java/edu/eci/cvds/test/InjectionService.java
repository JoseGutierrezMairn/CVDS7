package edu.eci.cvds.test;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISTipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerItemsStub;

public class InjectionService extends com.google.inject.AbstractModule{
	@Override
    protected void configure() {
		bind(ClienteDAO.class).to(MyBATISClienteDAO.class);
        bind(ItemDAO.class).to(MyBATISItemDAO.class);
        bind(TipoItemDAO.class).to(MyBATISTipoItemDAO.class);
        bind(ServiciosAlquiler.class).to(ServiciosAlquilerItemsStub.class);
    }
}
