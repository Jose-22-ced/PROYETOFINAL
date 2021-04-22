/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryzen
 */
public class ModeloFactura extends Facturas{
    private static ConexionBD cone = new ConexionBD();

    public ModeloFactura() {
    }

    public ModeloFactura(String idFactura, String cedulaCliente, String idEstacionamiento, double subTotal, Date fechaemicion, Time horaingreso, Time horaIngreso, String tiempo, String idDetalle, double montototal) {
        super(idFactura, cedulaCliente, idEstacionamiento, subTotal, fechaemicion, horaingreso, horaIngreso, tiempo, idDetalle, montototal);
    }

    public ModeloFactura(String idFactura, String cedulaCliente, String idEstacionamiento, double subTotal, Date fechaemicion, Time horaingreso, Time horaIngreso, double montototal) {
        super(idFactura, cedulaCliente, idEstacionamiento, subTotal, fechaemicion, horaingreso, horaIngreso, montototal);
    }
    

    public List<Facturas>Listar(String aguja){
        try {
            String sql="SELECT f.id_factura, f.cedula_cli,fd.id_estacionamiento,f.fecha_emision,fd.subtotal,fd.hora_ingreso,fd.hora_salida,f.monto_total FROM factura f inner join detalle_factura fd ON f.id_factura = fd.id_factura WHERE ";
            sql+="UPPER(f.id_factura) LIKE UPPER('%"+aguja+"%') OR ";
            sql+="UPPER(f.cedula_cli) LIKE UPPER('%"+aguja+"%') OR ";
            sql+="UPPER(fd.id_estacionamiento) LIKE UPPER('%"+aguja+"%')";
            
            ResultSet rs = cone.query(sql);
            List <Facturas> list = new ArrayList<>();
            while (rs.next()) {
               Facturas em= new Facturas(rs.getString("id_factura"),rs.getString("cedula_cli"),rs.getString("id_estacionamiento"),rs.getDouble("subtotal"),rs.getDate("fecha_emision"),rs.getTime("hora_ingreso"),rs.getTime("hora_salida"),rs.getDouble("monto_total"));
               list.add(em);    
            }                    
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }  
    } 
    
    public boolean CrearFactura(){
        String sql;
        sql="INSERT INTO factura (id_factura,cedula_cli,fecha_emision,monto_total)";
        sql+="VALUES ('"+getIdFactura()+"','"+getCedulaCliente()+"','"+getFechaemicion()+"','"+getMontototal()+"')";
        
        if(cone.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
          
    }
    public boolean CrearDetalleFactura(){
        String sql;
        sql="INSERT INTO detalle_factura (id_detalle,id_factura,tiempo,subtotal,id_estacionamiento,hora_ingreso,hora_salida)";
        sql+="VALUES ('"+getIdDetalle()+"','"+getIdFactura()+"','"+getTiempo()+"','"+getSubTotal()+"','"+getIdEstacionamiento()+"','"+getHoraingreso()+"','"+getHorasalida()+"')";
        
        if(cone.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
          
    }
    public boolean EliminarFactura(){
        String sql;
         sql="DELETE FROM factura"+" "+"WHERE id_factura='"+getIdFactura()+"'";
        
        if(cone.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
        
          
    }
    public boolean EliminarDetalleFactura(){
        String sql;
         sql="DELETE FROM detalle_factura"+" "+"WHERE id_factura='"+getIdFactura()+"'";
        
        if(cone.noquery(sql)==null){
            return true;
        }else{
            return false;
        }
          
    }
    
    
}
