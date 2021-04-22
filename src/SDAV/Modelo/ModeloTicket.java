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
 * @author USUARIO
 */
public class ModeloTicket extends Ticket {

    private static ConexionBD cone = new ConexionBD();

    public ModeloTicket() {
    }

    public ModeloTicket(String codigoticket, String id_zona, String placa, Date fechaingreso, Time horaingreso, Time horasalida) {
        super(codigoticket, id_zona, placa, fechaingreso, horaingreso, horasalida);
    }

    

    public List<Ticket> Listar(String aguja) {
        try {
            String sql = "SELECT * FROM estacionamiento WHERE ";
            sql += "UPPER(id_estacionamiento) LIKE UPPER('%" + aguja + "%') OR ";
            sql += "UPPER(id_placa) LIKE UPPER('%" + aguja + "%') OR ";
            sql += "UPPER(id_zona) LIKE UPPER('%" + aguja + "%')";
            ResultSet rs = cone.query(sql);
            List<Ticket> lista = new ArrayList<Ticket>();
            byte[] bf;
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setCodigoticket(rs.getString("id_estacionamiento"));
                t.setId_zona(rs.getString("id_zona"));
                t.setPlaca(rs.getString("id_placa"));
                t.setFechaingreso(rs.getDate("fecha_ingreso"));
                t.setHoraingreso(rs.getTime("hora_ingreso"));
                t.setHorasalida(rs.getTime("hora_salida"));
                lista.add(t);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloZona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean crear() {
        String sql;
        sql = "INSERT INTO estacionamiento (id_estacionamiento, id_zona, id_placa, fecha_ingreso, hora_ingreso, hora_salida)";
        sql += "VALUES('" + getCodigoticket() + "','" + getId_zona() +"','" + getPlaca()   + "','" + getFechaingreso() +"','" + getHoraingreso() + "','" + getHorasalida() + "')";
        if (cone.noquery(sql) == null) {
            return true;
        }
        return false;
    }

    public boolean editar() {
        String sql;
        sql = "UPDATE estacionamiento SET id_estacionamiento='" + getCodigoticket() + "', id_zona='" + getId_zona()
                + "', id_placa='" + getPlaca() + "',fecha_ingreso='" + getFechaingreso() + "',hora_ingreso='" + getHoraingreso() + "',hora_salida='" + getHorasalida() + "'WHERE id_estacionamiento='" + getCodigoticket() + "'";
        if (cone.noquery(sql) == null) {
            return true;
        }
        return false;
    }

    public boolean eliminar(String id) {
        String sql;
        sql = "DELETE from estacionamiento WHERE id_estacionamiento='" + getCodigoticket() + "'";
        if (cone.noquery(sql) == null) {
            return true;
        }
        return false;
    }

}
