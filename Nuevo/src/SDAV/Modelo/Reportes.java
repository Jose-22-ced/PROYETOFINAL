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
public class Reportes {
    private String idreporte;
    private byte informacion; 

    public Reportes() {
    }

    public Reportes(String idreporte, byte informacion) {
        this.idreporte = idreporte;
        this.informacion = informacion;
    }

    public byte getInformacion() {
        return informacion;
    }

    public void setInformacion(byte informacion) {
        this.informacion = informacion;
    }

    public String getIdreporte() {
        return idreporte;
    }

    public void setIdreporte(String idreporte) {
        this.idreporte = idreporte;
    }
    
    
}
