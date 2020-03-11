/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        ItemMapper im=sqlss.getMapper(ItemMapper.class);
        Item item = new Item(new TipoItem(5,"Lo mejor de lo mejor"),2133951,"Lab CVDS","Como resolver el lab",new Date(2020,03,10),985674,"Fromato","Programacion");
        im.insertarItem(item);
        cm.agregarItemRentadoACliente(-33,2,new Date(2098,12,11),new Date(2189,12,30));
        System.out.println(cm.consultarClientes());
        System.out.println(cm.consultarCliente(-33));
        System.out.println(im.consultarItems());
        //System.out.println(im.consultarItem(12021));
        
        
        sqlss.commit();
        
        
        sqlss.close();
    }
    

}
