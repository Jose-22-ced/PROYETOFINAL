/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Ryzen
 */
public class Facturas {
    private String idFactura;
    private String cedulaCliente;
    private String idEstacionamiento;
    private double subTotal;
    private Date fechaemicion;
    private Time horaingreso;
    private Time horasalida;
    private String tiempo;
    private String idDetalle;
    private double montototal;
    
    
    public Facturas() {
    }

    public Facturas(String idFactura, String cedulaCliente, String idEstacionamiento, double subTotal, Date fechaemicion, Time horaingreso, Time horasalida, String tiempo, String idDetalle, double montototal) {
        this.idFactura = idFactura;
        this.cedulaCliente = cedulaCliente;
        this.idEstacionamiento = idEstacionamiento;
        this.subTotal = subTotal;
        this.fechaemicion = fechaemicion;
        this.horaingreso = horaingreso;
        this.horasalida =horasalida;
        this.tiempo = tiempo;
        this.montototal = montototal;
    }

    public Facturas(String idFactura, String cedulaCliente, String idEstacionamiento, double subTotal, Date fechaemicion, Time horaingreso, Time horasalida, double montototal) {
        this.idFactura = idFactura;
        this.cedulaCliente = cedulaCliente;
        this.idEstacionamiento = idEstacionamiento;
        this.subTotal = subTotal;
        this.fechaemicion = fechaemicion;
        this.horaingreso = horaingreso;
        this.horasalida = horasalida;
        this.montototal = montototal;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getIdEstacionamiento() {
        return idEstacionamiento;
    }

    public void setIdEstacionamiento(String idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Date getFechaemicion() {
        return fechaemicion;
    }

    public void setFechaemicion(Date fechaemicion) {
        this.fechaemicion = fechaemicion;
    }

    public Time getHoraingreso() {
        return horaingreso;
    }

    public void setHoraingreso(Time horaingreso) {
        this.horaingreso = horaingreso;
    }

    public Time getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(Time horasalida) {
        this.horasalida = horasalida;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }


    

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }



    public double getMontototal() {
        return montototal;
    }

    public void setMontototal(double montototal) {
        this.montototal = montototal;
    }
    
    
    
}
