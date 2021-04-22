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
import javax.swing.JOptionPane;

/**
 *
 * @author Ryzen
 */
public class ConexionBD {
    private String url="jdbc:postgresql://Localhost:5432/BASE_SDVA";
    private String usuariopg="postgres";
    private String passpg="2001";
    
  
  private Connection con;
    public ConexionBD(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Esta mal el driver");
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
           con=DriverManager.getConnection(url,usuariopg,passpg);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Esta mal los datos");
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ResultSet query(String sql){
        
        try {
            Statement st;
            st=con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public SQLException noquery(String nsql){
        
        try {
            Statement st;
            st=con.createStatement();
            st.execute(nsql);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
        
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}