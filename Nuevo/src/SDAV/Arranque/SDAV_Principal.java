/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Arranque;

import SDAV.Controlador.ControladorCliente;
import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloCliente;

/**
 *
 * @author Ryzen
 */
public class SDAV_Principal {
    public static void main(String[] args) {
        ModeloCliente modelo =  new ModeloCliente();
        ControladorCliente control = new ControladorCliente(modelo);
        control.InciarContro();
        //Holaque hace
    }
}
