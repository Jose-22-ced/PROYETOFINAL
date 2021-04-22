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
public class ModeloCargo extends Cargo{
    private static ConexionBD conex = new ConexionBD();

    public ModeloCargo() {
    }

    public ModeloCargo(String id_cargo, String nombres) {
        super(id_cargo, nombres);
    }
    
    public List<Cargo>Listar(String aguja){
        try {
            String sql="SELECT id_cargo,nombre FROM cargo WHERE ";
            sql+="id_cargo LIKE '%"+aguja+"%' OR ";
            sql+="UPPER(nombre) LIKE UPPER('%"+aguja+"%')";
            ResultSet rs = conex.query(sql);
            List <Cargo> list = new ArrayList<>();
            while (rs.next()) {
                try {
                    Cargo c1= new Cargo(rs.getString("id_cargo"), rs.getString("nombre"));
                    list.add(c1);
                } catch (SQLException ex) {
                    Logger.getLogger(ModeloCargo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCargo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public boolean CrearCargo(){
        String sql;
        sql="INSERT INTO cargo(id_cargo,nombre)";
        sql+="VALUES ('"+getId_cargo()+"','"+getNombres()+"')";
        if(conex.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
    }
    public boolean EditarCargo(){
        String sql;
        sql="UPDATE cargo"+" "+"SET id_cargo='"+getId_cargo()+"',nombre='"+getNombres()+"'";
        sql+="WHERE id_cargo='"+getId_cargo()+"'";
        if(conex.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
    }
    public boolean EliminarCargo(){
       String sql;
       sql="DELETE FROM cargo"+" "+"WHERE id_cargo='"+getId_cargo()+"'";
       if(conex.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
    }
   
}
