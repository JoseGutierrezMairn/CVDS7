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
package edu.eci.cvds.sampleprj.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
            
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=2133951;
            registrarNuevoProducto(con, suCodigoECI, "SU NOMBRE", 99999999);            
            con.commit();
                        
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
    	
    	PreparedStatement insertProducto = null;
        String InsertString =
                "Insert into ORD_PRODUCTOS values (?,?,?);" ;
        try {
        	insertProducto=con.prepareStatement(InsertString);
        	insertProducto.setInt(1, codigo);
        	insertProducto.setString(2, nombre);
        	insertProducto.setInt(3, precio);
        	insertProducto.executeUpdate();
        	con.commit();
        } catch (SQLException  e) {
        	System.out.println(e.getMessage());
        	con.rollback();
        } finally {
            if (insertProducto != null) {
            	insertProducto.close();
            }
        }
        
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido) throws SQLException{
        List<String> np=new LinkedList<>();
        
        PreparedStatement selectProductos = null;
        String SelectString =
                "Select nombre FROM ORD_PRODUCTOS join ORD_DETALLE_PEDIDO on producto_fk=codigo where pedido_fk=?" ;
        try {
        	selectProductos=con.prepareStatement(SelectString);
        	selectProductos.setInt(1, codigoPedido);
        	ResultSet resultado=selectProductos.executeQuery();
        	while(resultado.next()) {
        		np.add(resultado.getString(1));
        	}
        } catch (SQLException  e) {
        	System.out.println(e.getMessage());
        } finally {
            if (selectProductos != null) {
            	selectProductos.close();
            }
        }
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido) throws SQLException{
    	
        int valor=0; 
    	PreparedStatement selectValorTotal = null;
        String SelectString =
                "Select SUM(cantidad*precio) FROM ORD_PRODUCTOS join ORD_DETALLE_PEDIDO on producto_fk=codigo where pedido_fk=?" ;
        try {
        	selectValorTotal=con.prepareStatement(SelectString);
        	selectValorTotal.setInt(1, codigoPedido);
        	ResultSet resultado=selectValorTotal.executeQuery();
        	resultado.next();
        	valor=Integer.parseInt(resultado.getString(1));
        } catch (SQLException  e) {
        	System.out.println(e.getMessage());
        } finally {
            if (selectValorTotal != null) {
            	selectValorTotal.close();
            }
        }

        
        return valor;
    }
}