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
    private String tipo;
    private double precio;

    public Tarifa() {
    }

    public Tarifa(String idtarifa, String tipo, double precio) {
        this.idtarifa = idtarifa;
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getIdtarifa() {
        return idtarifa;
    }

    public void setIdtarifa(String idtarifa) {
        this.idtarifa = idtarifa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    
    
    
}
