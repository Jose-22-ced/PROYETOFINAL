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
public class ModeloCliente extends Cliente{
    private static ConexionBD con = new ConexionBD();
    public ModeloCliente() {
    }

    public ModeloCliente(String numtelefono, String direccion, String cedula, String nombre, String apellido) {
        super(numtelefono, direccion, cedula, nombre, apellido);
    }
    
    public List<Cliente>Listar(){
        try {
            String sql="SELECT * FROM cliente";
            ResultSet rs = con.query(sql);
            List <Cliente> list = new ArrayList<>();
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
