/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Arranque;

import SDAV.Controlador.ControladorArranquePrincipal;
import SDAV.Controlador.ControladorCliente;
import SDAV.Controlador.ControladorMenuPrincipal;
import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloCliente;
import SDAV.Vista.VistaArranquePrincipal;
import SDAV.Vista.VistaMenuPrincipal;

/**
 *
 * @author Ryzen
 */
public class SDAV_Principal {
    public static void main(String[] args) {
        
        VistaArranquePrincipal vistaar = new VistaArranquePrincipal();
        ControladorArranquePrincipal cap = new ControladorArranquePrincipal(vistaar);
        cap.InicarControl();
    }
}
