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
public class Ticket {
    private String codigoticket;
    private String id_zona;
    private String placa;
    private Date fechaingreso;
    private Time horaingreso;
    private Time horasalida;

    public Ticket() {
    }

    public Ticket(String codigoticket, String id_zona, String placa, Date fechaingreso, Time horaingreso, Time horasalida) {
        this.codigoticket = codigoticket;
        this.id_zona = id_zona;
        this.placa = placa;
        this.fechaingreso = fechaingreso;
        this.horaingreso = horaingreso;
        this.horasalida = horasalida;
    }

    public String getCodigoticket() {
        return codigoticket;
    }

    public void setCodigoticket(String codigoticket) {
        this.codigoticket = codigoticket;
    }

    public String getId_zona() {
        return id_zona;
    }

    public void setId_zona(String id_zona) {
        this.id_zona = id_zona;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
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

    



    
    
    
}
