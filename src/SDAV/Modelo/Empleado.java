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
    private String cargo;
    private String sueldo;
    private String emal;

    public Empleado() {
    }

    public Empleado(String cedula, String cargo, String nombre, String apellido, String emal, String sueldo) {
        
        super(cedula, nombre, apellido);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.emal = emal;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getEmal() {
        return emal;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

   
    
    
}
