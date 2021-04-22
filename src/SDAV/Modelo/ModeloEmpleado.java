/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryzen
 */
public class ModeloEmpleado extends Empleado{
   
    private static ConexionBD cone = new ConexionBD();

    public ModeloEmpleado() {
    }

    public ModeloEmpleado(String cedula, String cargo, String nombre, String apellido, String emal, String sueldo) {
        super(cedula, cargo, nombre, apellido, emal, sueldo);
    }
public List<Empleado>Listar(String aguja){
        try {
            String sql="SELECT cedula,id_cargo,nombre,apellido,email,sueldo FROM empleado WHERE ";
            sql+="UPPER(cedula) LIKE UPPER('%"+aguja+"%') OR ";
            sql+="UPPER(nombre) LIKE UPPER('%"+aguja+"%') OR ";
            sql+="UPPER(apellido) LIKE UPPER('%"+aguja+"%')";
            
            ResultSet rs = cone.query(sql);
            List <Empleado> list = new ArrayList<>();
            while (rs.next()) {
               Empleado em= new Empleado(rs.getString("cedula"), rs.getString("id_cargo"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("sueldo"));
               list.add(em);    
            }                    
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }  
    } 
public boolean CrearEmpleado(){
        String sql;
        sql="INSERT INTO empleado(cedula,id_cargo,nombre,apellido,email,sueldo)";
        sql+="VALUES ('"+getCedula()+"','"+getCargo()+"','"+getNombre()+"','"+getApellido()+"','"+getEmal()+"','"+getSueldo()+"')";
        if(cone.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
               
    }
public boolean eliminarE(){
        String sql;
         sql="DELETE FROM empleado"+" "+" WHERE cedula='"+getCedula()+"'";
        
        
        if(cone.noquery(sql)==null){
        return true;
    }else {
        return false;
    }
    } 
public boolean editarE(){
     String sql;
        sql="UPDATE empleado "+" "+"SET id_cargo='"+getCargo()+"',nombre='"+getNombre()+"',apellido='"+getApellido()+"',email='"+getEmal()+"',sueldo='"+getSueldo()+"'";
        sql+="WHERE cedula='"+getCedula()+"'";
          if(cone.noquery(sql)==null){
        return true;
    }else {
        return false;
    }
    } 
   
    
}
