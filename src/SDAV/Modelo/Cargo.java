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
public class Cargo {
    
    private String id_cargo;
    private String nombres;

    public Cargo() {
    }

    public Cargo(String id_cargo, String nombres) {
        this.id_cargo = id_cargo;
        this.nombres = nombres;
    }

    public String getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(String id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    
}
