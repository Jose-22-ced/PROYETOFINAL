/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.Empleado;
import SDAV.Modelo.ModeloCliente;
import SDAV.Modelo.ModeloEmpleado;
import SDAV.Vista.VistaArranquePrincipal;
import SDAV.Vista.VistaMenuPrincipal;
import SDAV.Vista.VistaCliente;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;


/**
 *
 * @author Ryzen
 */
public class ControladorArranquePrincipal {
    VistaArranquePrincipal vap;
    VistaMenuPrincipal vistavmp = new VistaMenuPrincipal();
     ModeloEmpleado modelo = new ModeloEmpleado();
    public ControladorArranquePrincipal(VistaArranquePrincipal vap) {
        this.vap = vap;
        vap.setSize(960, 654);
        vap.setVisible(true);
        vap.setLocationRelativeTo(null);
        vap.getTxtError().setText("BIENVENIDO");
        vap.getTxtError().setForeground(new Color(51,153,0));
        vap.getSueldE().setEnabled(false);
        
    }
    public void InicarControl(){
         KeyListener validarcedula = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 32 && e.getKeyChar() <= 47
                        || e.getKeyChar() >= 58 && e.getKeyChar() <= 127) {
                    e.consume();
                }
                if (vap.getCeduE().getText().length() == 10) {
                    Toolkit.getDefaultToolkit().beep();
                  
                    e.consume();
                }
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        KeyListener validarnomyape = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 33 && e.getKeyChar() <= 64
                        || e.getKeyChar() >= 91 && e.getKeyChar() <= 96
                        || e.getKeyChar() >= 123 && e.getKeyChar() <= 208
                        || e.getKeyChar() >= 210 && e.getKeyChar() <= 240) {
                    e.consume();
                }
                if (vap.getNomE().getText().length() == 25) {
                    Toolkit.getDefaultToolkit().beep();
                    
                } else {
                    if (vap.getApellE().getText().length() == 25) {
                        Toolkit.getDefaultToolkit().beep();
                       
                    }
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
        KeyListener validarcorreo = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
                if(validar_correo(vap.getEmailE().getText())==true){
                    vap.getEtiquetacorreo().setForeground(Color.GREEN);
                    vap.getEtiquetacorreo().setText("/");         
                }else{   
                    vap.getEtiquetacorreo().setText("*");
                    vap.getEtiquetacorreo().setForeground(Color.RED);
            }
            }
        };
        KeyListener validarsueldo = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 32 && e.getKeyChar() <= 45 
                        || e.getKeyChar() >= 58 && e.getKeyChar() <= 127  ) {
                    e.consume();
                }
                if (vap.getSueldE().getText().length() == 8) {
                    Toolkit.getDefaultToolkit().beep();
                   
                    e.consume();
                }
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        
        vap.getBt_Ingresar().addActionListener(l->InicarControlMenuPrincipal());
        vap.getBtSalir().addActionListener(l->CerrarVentana());
        vap.getJbRegistarse().addActionListener(l->Registarse());
        vap.getBtncancE().addActionListener(l->IniciarSesion());
        vap.getBtnaceptE().addActionListener(l->grabarempleadovalidado());
        vap.getCeduE().addKeyListener(validarcedula);
        vap.getNomE().addKeyListener(validarnomyape);
        vap.getApellE().addKeyListener(validarnomyape);
        vap.getEmailE().addKeyListener(validarcorreo);
        vap.getSueldE().addKeyListener(validarsueldo);
        vap.getTxt_Cedu().addKeyListener(validarcedula);
    }
    
    public void InicarControlMenuPrincipal(){
        List<Empleado> lista = modelo.Listar("001");
        lista.stream().forEach(p -> {
            if (vap.getTxt_Correo().getText().equals(p.getEmal()) && vap.getTxt_Cedu().getText().equals(p.getCedula())) { 
            ControladorMenuPrincipal cmp = new ControladorMenuPrincipal(vistavmp);
            cmp.IniciarControl();
            vap.setVisible(false);
            vap.getTxtError().setText(null);
        }else{
            vap.getTxtError().setText("Datos incorrectos");
            vap.getTxtError().setForeground(Color.RED);
        }     
    });
 
    }
    
    private void grabarEmpleado() {

            String Cedula = vap.getCeduE().getText();
            String Nombre = vap.getNomE().getText();
            String Apellido = vap.getApellE().getText();
            String Email = vap.getEmailE().getText();
            String Sueldo = vap.getSueldE().getText();

            ModeloEmpleado empleado = new ModeloEmpleado(Cedula, "001", Nombre, Apellido, Email, Sueldo);
            List<Empleado> lista = modelo.Listar("001");
            lista.stream().forEach(p -> {
            if (vap.getEmailEAd().getText().equals(p.getEmal())) { 
                if (empleado.CrearEmpleado()) {
                JOptionPane.showMessageDialog(vap, "Empleado Creado con exito", "AVISO", -1);
                ControladorMenuPrincipal cmp = new ControladorMenuPrincipal(vistavmp);
                cmp.IniciarControl();
                vap.setVisible(false);
                vap.getTxtError().setText(null);
                } else {
                    JOptionPane.showMessageDialog(vap, "No se creo,talves ya existe ese atministrador","ERROR", 0);
                }
            }else{


            }     
             });

    }
    public void grabarempleadovalidado() {
        if (vap.getCeduE().getText().isEmpty() || vap.getNomE().getText().isEmpty() || vap.getApellE().getText().isEmpty()
                || vap.getSueldE().getText().isEmpty() ||vap.getEmailE().getText().isEmpty() 
                || validar_correo(vap.getEmailE().getText())==false 
                || ValidacionesCedula()==false) {
            JOptionPane.showMessageDialog(vap, "Llenar todos los campos requeridos o incompletos", "ERROR", 0);
        } else {
            grabarEmpleado();
        }
    }
    public boolean validar_correo(String correo) {
        Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(correo);
        return mat.find();
    }
    public boolean ValidacionesCedula(){
        int c, suma=0, acum, resta=0;
        try {
        for (int i = 0; i < vap.getCeduE().getText().length()-1; i++) {
            c=Integer.parseInt(vap.getCeduE().getText().charAt(i)+"");
            if(i%2==0){
                c=c*2;
                if(c>9){
                    c=c-9;
                }
            }
            suma=suma+c;
        } }catch(Exception e){
           JOptionPane.showMessageDialog(null,"La cedula Ecuatoriana no tinen letras por defecto", "ERROR", 0);
           return false;
        }
        if (suma%10 !=0) {
            acum=((suma/10)+1)*10;
            resta=acum-suma;
        }
        int ultimos = 0;
        try{
        ultimos=Integer.parseInt(vap.getCeduE().getText().charAt(9)+"");
        }catch(Exception e){
           JOptionPane.showMessageDialog(null,"No cumple dos 10 dijitos por defecto", "ERROR", 0);
           return false; 
        }
        int ultimo = ultimos;
        if (ultimo==resta) {
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"la cedula es incorrecta o no existe por defecto", "ERROR", 0);
            return false; 
        }   
    }
    

    public void CerrarVentana(){
        System.exit(0);
    }
     public void Registarse(){
        vap.getJpRegistrarse().setVisible(true);
        vap.getJpInicairSesion().setVisible(false);
    }
     public void IniciarSesion(){
        vap.getJpRegistrarse().setVisible(false);
        vap.getJpInicairSesion().setVisible(true);
    }
    

    
}
