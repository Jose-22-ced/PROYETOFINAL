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
public class Empleado extends Persona{
    private String idemplado;
    private String cargo;

    public Empleado() {
    }

    public Empleado(String idemplado, String cargo, String cedula, String nombre, String apellido) {
        super(cedula, nombre, apellido);
        this.idemplado = idemplado;
        this.cargo = cargo;
    }

    public String getIdemplado() {
        return idemplado;
    }

    public void setIdemplado(String idemplado) {
        this.idemplado = idemplado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
}
