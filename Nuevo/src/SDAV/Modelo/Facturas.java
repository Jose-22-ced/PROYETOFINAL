/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.sql.Date;

/**
 *
 * @author Ryzen
 */
public class Facturas {
    private String codigofactura;
    private Date fechaemicion;
    private Date fechadeentrada;
    private double Monto;

    public Facturas() {
    }

    public Facturas(String codigofactura, Date fechaemicion, Date fechadeentrada, double Monto) {
        this.codigofactura = codigofactura;
        this.fechaemicion = fechaemicion;
        this.fechadeentrada = fechadeentrada;
        this.Monto = Monto;
    }

    public String getCodigofactura() {
        return codigofactura;
    }

    public void setCodigofactura(String codigofactura) {
        this.codigofactura = codigofactura;
    }

    public Date getFechaemicion() {
        return fechaemicion;
    }

    public void setFechaemicion(Date fechaemicion) {
        this.fechaemicion = fechaemicion;
    }

    public Date getFechadeentrada() {
        return fechadeentrada;
    }

    public void setFechadeentrada(Date fechadeentrada) {
        this.fechadeentrada = fechadeentrada;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }
    
    
    
}
