/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryzen
 */
public class ConexionBD {
    private String Url="jdbc:postgresql://localhost:5432/BASE_SDVA";
    private String User="postgres";
    private String Passware="2001";
    private Connection Coneccion;

    public ConexionBD() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Conectado");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
           Coneccion = DriverManager.getConnection(Url,User,Passware);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public ResultSet query(String sql){
        try {
            Statement st;
            st=Coneccion.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
    }
    
    public SQLException noquery(String nsql){
        try {
            Statement st;
            st=Coneccion.createStatement();
            st.execute(nsql);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
    }

    public Connection getConeccion() {
        return Coneccion;
    }

    public void setConeccion(Connection Coneccion) {
        this.Coneccion = Coneccion;
    }

    
    
}
