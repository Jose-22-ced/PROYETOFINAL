/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.awt.Image;

public class Zona {
    private String idzona;
    private String nombre;
    private String tarifa;
    private String estado;
    private Image foto;

    public Zona() {
    }

    public Zona(String idzona, String nombre, String tarifa, String estado) {
        this.idzona = idzona;
        this.nombre = nombre;
        this.tarifa = tarifa;
        this.estado = estado;
       
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdzona() {
        return idzona;
    }

    public void setIdzona(String idzona) {
        this.idzona = idzona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

}
