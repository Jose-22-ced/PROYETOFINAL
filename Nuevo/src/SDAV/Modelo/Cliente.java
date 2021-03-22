/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

/**
 *
 * @author Ryzen
 */
public class Cliente extends Persona{
    private String numtelefono;
    private String direccion;

 
    public Cliente() {
        
    }

    public Cliente(String numtelefono, String direccion, String cedula, String nombre, String apellido) {
        super(cedula, nombre, apellido);
        this.numtelefono = numtelefono;
        this.direccion = direccion;
    }

    public String getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(String numtelefono) {
        this.numtelefono = numtelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
