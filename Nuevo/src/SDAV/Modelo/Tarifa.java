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
public class Tarifa {
    private String idtarifa;
    private double tipo;

    public Tarifa() {
    }

    public Tarifa(String idtarifa, double tipo) {
        this.idtarifa = idtarifa;
        this.tipo = tipo;
    }

    public double getTipo() {
        return tipo;
    }

    public void setTipo(double tipo) {
        this.tipo = tipo;
    }

    public String getIdtarifa() {
        return idtarifa;
    }

    public void setIdtarifa(String idtarifa) {
        this.idtarifa = idtarifa;
    }
    
    
}
