/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.awt.Image;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.Base64;


/**
 *
 * @author Ryzen
 */
public class ModeloCliente extends Cliente{
    private static ConexionBD conex = new ConexionBD();
    public ModeloCliente() {
    }

    public ModeloCliente(String numtelefono, String direccion, String cedula, String nombre, String apellido) {
        super(numtelefono, direccion, cedula, nombre, apellido);
    }
   
    public List<Cliente>Listar(String aguja){
        try {
            String sql="SELECT cedula,nombre,apellido,telefono,direccion FROM cliente WHERE ";
            sql+="UPPER(cedula) LIKE UPPER('%"+aguja+"%') OR ";
            sql+="UPPER(nombre) LIKE UPPER('%"+aguja+"%') OR ";
            sql+="UPPER(apellido) LIKE UPPER('%"+aguja+"%')";
            
            ResultSet rs = conex.query(sql);
            List <Cliente> list = new ArrayList<>();
            while (rs.next()) {
               Cliente c1 = new Cliente(rs.getString("telefono"), rs.getString("direccion"),rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"));   
               list.add(c1);    
            }                    
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }  
    } 
    
     public boolean CrearCliente(){
        String sql;
        sql="INSERT INTO cliente(cedula,nombre,apellido,telefono,direccion)";
        sql+="VALUES ('"+getCedula()+"','"+getNombre()+"','"+getApellido()+"','"+getNumtelefono()+"','"+getDireccion()+"')";
        if(conex.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
        
        
    }
      public boolean eliminar(){
        String sql;
         sql="DELETE FROM cliente"+" "+"WHERE cedula='"+getCedula()+"'";
        
        
        if(conex.noquery(sql)==null){
        return true;
    }else {
        return false;
    }
    } 
       public boolean editar(){
     String sql;
        sql="UPDATE cliente"+" "+"SET nombre='"+getNombre()+"',apellido='"+getApellido()+"',telefono='"+getNumtelefono()+"',direccion='"+getDireccion()+"'";
        sql+="WHERE cedula='"+getCedula()+"'";
          if(conex.noquery(sql)==null){
        return true;
    }else {
        return false;
    }
    }
                      
}
  
