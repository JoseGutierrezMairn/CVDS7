package edu.eci.cvds.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {
	
    @Inject
    private SqlSession sqlSession;
    
    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
    	SqlSessionFactory sessionfact = getSqlSessionFactory();
    	sqlSession=sessionfact.openSession();
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }
    
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config-h2.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    @Before
    public void setUp() {
    }
    
    @Test
    public void emptyDB() {
    	try {
    		Assert.assertTrue(serviciosAlquiler.consultarClientes().size()==0);
    	} catch(Exception e) {
    		Assert.assertTrue(false);  
    	}
    }
    
    @Test
    public void consultarcliente() {
    	try {
    		if(serviciosAlquiler.consultarCliente(1026585664)!=null) {
    			Assert.assertTrue(true);
    		}
    		else {
    			Assert.assertTrue(false);
    		}
    	} catch(ExcepcionServiciosAlquiler e) {
    		Assert.assertTrue(false);
    	}
    		
    }
    
    
}