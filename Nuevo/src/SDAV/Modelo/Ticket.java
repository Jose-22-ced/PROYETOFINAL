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
public class Ticket {
    private String codigoticket;
    private Date emicion;
    private Date fechaemacion;
    private Date horaentrada;

    public Ticket() {
    }

    public Ticket(String codigoticket, Date emicion, Date fechaemacion, Date horaentrada) {
        this.codigoticket = codigoticket;
        this.emicion = emicion;
        this.fechaemacion = fechaemacion;
        this.horaentrada = horaentrada;
    }

    public Date getHoraentrada() {
        return horaentrada;
    }

    public void setHoraentrada(Date horaentrada) {
        this.horaentrada = horaentrada;
    }

    public String getCodigoticket() {
        return codigoticket;
    }

    public void setCodigoticket(String codigoticket) {
        this.codigoticket = codigoticket;
    }

    public Date getEmicion() {
        return emicion;
    }

    public void setEmicion(Date emicion) {
        this.emicion = emicion;
    }

    public Date getFechaemacion() {
        return fechaemacion;
    }

    public void setFechaemacion(Date fechaemacion) {
        this.fechaemacion = fechaemacion;
    }
    
    
    
}
