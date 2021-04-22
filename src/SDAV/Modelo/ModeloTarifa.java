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
public class ModeloTarifa extends Tarifa{
    private static ConexionBD conex = new ConexionBD();
    
    public ModeloTarifa() {
    }

    public ModeloTarifa(String idtarifa, String tipo, double precio) {
        super(idtarifa, tipo, precio);
    }

    
    public List<Tarifa>Listar(String aguja){
        try {
            String sql="SELECT cod_tarifa,tipo,precio FROM tarifa WHERE ";
            sql+="cod_tarifa LIKE '%"+aguja+"%' OR ";
            sql+="UPPER(tipo) LIKE UPPER('%"+aguja+"%') OR ";
            sql+="CAST(precio AS varchar(10)) LIKE '%"+aguja+"%'";
            ResultSet rs = conex.query(sql);
            List <Tarifa> list = new ArrayList<>();
            while (rs.next()) {
                try {
                    Tarifa t1= new Tarifa(rs.getString("cod_tarifa"),rs.getString("tipo"),rs.getDouble("precio"));
                    list.add(t1);
                } catch (SQLException ex) {
                    Logger.getLogger(ModeloTarifa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloTarifa.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public boolean CrearCargo(){
        String sql;
        sql="INSERT INTO tarifa(cod_tarifa,tipo,precio)";
        sql+="VALUES ('"+getIdtarifa()+"','"+getTipo()+"','"+getPrecio()+"')";
        if(conex.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
    }
    public boolean EditarCargo(){
        String sql;
        sql="UPDATE tarifa"+" "+"SET cod_tarifa='"+getIdtarifa()+"',tipo='"+getTipo()+"',precio='"+getPrecio()+"'";
        sql+="WHERE cod_tarifa='"+getIdtarifa()+"'";
        if(conex.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
    }
    public boolean EliminarCargo(){
       String sql;
       sql="DELETE FROM tarifa"+" "+"WHERE cod_tarifa='"+getIdtarifa()+"'";
       if(conex.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
    }
    
    
}
