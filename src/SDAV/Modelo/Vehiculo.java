/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.awt.Image;

/**
 *
 * @author Ryzen
 */
public class Vehiculo {
    private String placa;
    private String cedula_cli;
    private String marca;
    private String color;
    private String tipo_vehiculo;
    private Image foto;

    public Vehiculo() {
    }

    public Vehiculo(String placa, String cedula_cli, String marca, String color, String tipo_vehiculo) {
        this.placa = placa;
        this.cedula_cli = cedula_cli;
        this.marca = marca;
        this.color = color;
        this.tipo_vehiculo = tipo_vehiculo;
        
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCedula_cli() {
        return cedula_cli;
    }

    public void setCedula_cli(String cedula_cli) {
        this.cedula_cli = cedula_cli;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

   
}
