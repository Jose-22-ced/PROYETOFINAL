/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.ModeloCliente;
import SDAV.Vista.VistaArranquePrincipal;
import SDAV.Vista.VistaMenuPrincipal;
import SDAV.Vista.VistaCliente;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryzen
 */
public class ControladorArranquePrincipal {
    VistaArranquePrincipal vap;
    VistaMenuPrincipal vistavmp = new VistaMenuPrincipal();

    public ControladorArranquePrincipal(VistaArranquePrincipal vap) {
        this.vap = vap;
        vap.setSize(920, 654);
        vap.setVisible(true);
        vap.setLocationRelativeTo(null);
        vap.getTxtError().setText("BIENVENIDO");
        vap.getTxtError().setForeground(Color.GREEN);
    }
    public void InicarControl(){
        vap.getBt_Ingresar().addActionListener(l->InicarControlMenuPrincipal());
        vap.getBtSalir().addActionListener(l->CerrarVentana());
    }
    
    public void InicarControlMenuPrincipal(){
        if (vap.getTxt_User().getText().equals("Admin") && vap.getTxt_Password().getText().equals("admin")) {
           
            ControladorMenuPrincipal cmp = new ControladorMenuPrincipal(vistavmp);
            cmp.IniciarControl();
            vap.setVisible(false);
            vap.getTxtError().setText(null);
        }else{
            vap.getTxtError().setText("Para acceder ingrese los datos por defecto");
            vap.getTxtError().setForeground(Color.RED);
        }      
    }
    public void CerrarVentana(){
        System.exit(0);
    }

    
}
