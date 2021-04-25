/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.ModeloCliente;
import SDAV.Modelo.ModeloTicket;
import SDAV.Modelo.ModeloVehiculo;
import SDAV.Modelo.ModeloZona;
import SDAV.Modelo.Ticket;
import SDAV.Modelo.Zona;
import SDAV.Vista.VistaNuevoTicket;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ryzen
 */
public class ControladorNuevoTicket extends Thread{
    
    VistaNuevoTicket vista;
    ModeloCliente modeloCliente;
    ModeloVehiculo modeloVehiculo;
    ModeloTicket modeloTiket;
    ModeloZona modeloZona;
    private Thread h1;
    LocalDateTime nowTime = LocalDateTime.now();
    public ControladorNuevoTicket(VistaNuevoTicket vista, ModeloCliente modeloCliente, ModeloVehiculo modeloVehiculo, ModeloTicket modeloTiket, ModeloZona modeloZona) {
        this.vista = vista;
        this.modeloCliente = modeloCliente;
        this.modeloVehiculo = modeloVehiculo;
        this.modeloTiket = modeloTiket;
        this.modeloZona = modeloZona;
        vista.setVisible(true);
        h1= new Thread(this);
        h1.start();
        ListarZonas();
        Random num = new Random();
        List<Ticket> lista = modeloTiket.Listar("");
        int a =(int) lista.stream().count();
        vista.getTextid_ticket().setText("TK"+"-"+String.valueOf(a+1)+""+num.nextInt(9));
    }
    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            LocalDateTime nowTime = LocalDateTime.now();
            vista.getLabelhora().setText("" + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)));
            vista.getLabelfecha().setText("" + nowTime.format(DateTimeFormatter.ISO_DATE));
            System.out.println("" + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)));
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }
    public void InciarControl() {
         KeyListener validarcedula = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 32 && e.getKeyChar() <= 47
                        || e.getKeyChar() >= 58 && e.getKeyChar() <= 127) {
                    e.consume();
                }
                if (vista.getId().getText().length() == 10) {
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
                if (vista.getNom().getText().length() == 25) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " Alcanzó el máximo número de caracteres permitidos <25> ");
                } else {
                    if (vista.getApell().getText().length() == 25) {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(vista, " Alcanzó el máximo número de caracteres permitidos <25> ");
                    }
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
        KeyListener validartelefono = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 32 && e.getKeyChar() <= 47
                        || e.getKeyChar() >= 58 && e.getKeyChar() <= 127) {
                    e.consume();
                }
                if (vista.getTel().getText().length() == 10) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " Para el telefono se ha considerado un máximo de 10 números");
                    e.consume();
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
        KeyListener validardireccion = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c) && Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
                if (vista.getDireccion().getText().length() == 30) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " El numero máximo de carácteres es 30");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
        //Cliente
        vista.getId().addKeyListener(validarcedula);
        vista.getNom().addKeyListener(validarnomyape);
        vista.getApell().addKeyListener(validarnomyape);
        vista.getTel().addKeyListener(validartelefono);
        vista.getDireccion().addKeyListener(validardireccion);
         KeyListener validarplaca = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                  char c = e.getKeyChar();
                if (Character.isLetter(c) && Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
                if (vista.getTxtplaca().getText().length() == 8) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " Se alacanzo el número máximo de 8 caracteres");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
          KeyListener validarcolor= new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 33 && e.getKeyChar() <= 64
                        || e.getKeyChar() >= 91 && e.getKeyChar() <= 96
                        || e.getKeyChar() >= 123 && e.getKeyChar() <= 208
                        || e.getKeyChar() >= 210 && e.getKeyChar() <= 240) {
                    e.consume();
                }
                if (vista.getTxtcolor().getText().length() == 19) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " Alcanzó el máximo número de caracteres permitidos <20> ");
                } 
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
           KeyListener validarmarca = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                  char c = e.getKeyChar();
                if (Character.isLetter(c) && Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
                if (vista.getTxtmarca().getText().length() == 10) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " Se alacanzo el número máximo de 10 caracteres");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
        //Auto
        vista.getTxtplaca().addKeyListener(validarplaca);
        vista.getTxtcolor().addKeyListener(validarcolor);
        vista.getTxtmarca().addKeyListener(validarmarca);
        
        
        vista.getBtNext().addActionListener(l->CambiarelPanelfrente());
        vista.getBtBack().addActionListener(l->CambiarelPaneltras());
        vista.getBtCancel().addActionListener(l->Cerrar());
        vista.getBtnExamnV().addActionListener(l->cargarImagen());
    }
    
    private void ListarZonas(){
        List<Zona> lista =modeloZona.listarZonas("");
            lista.stream().forEach(p -> {
                if (p.getEstado().equals("LIBRE")) {
                   vista.getComboBoxid_zona().addItem(p.getIdzona()+"-"+p.getNombre());  
                }
            });
    }
    
    private void GuardarDatos(){
        if(vista.getBtNext().getText().equals("FINALIZAR")&&vista.getTxtNumpanel().getText().equals("3")){
        //Cliente
            System.out.println("hkjshfkjd");
       String Cedula= vista.getId().getText();
       String Nombre = vista.getNom().getText();
       String Apellido = vista.getApell().getText();
       String Telefono = vista.getTel().getText();
       String Direccion = vista.getDireccion().getText();
       
       ModeloCliente Cliente = new ModeloCliente(Telefono,Direccion,Cedula, Nombre, Apellido);
        
        if(Cliente.CrearCliente()){
            JOptionPane.showMessageDialog(vista, "Cliente Creada con exito","AVISO",-1);  
            //VEHICULO
            String placa=vista.getTxtplaca().getText();
            String cedu= vista.getId().getText();
            String marca=vista.getTxtmarca().getText();
            String color=vista.getTxtcolor().getText();
            String tipo=vista.getJctipoV().getSelectedItem().toString();
            
            ModeloVehiculo vehiculo=new ModeloVehiculo(placa, cedu, marca, color, tipo);
            ImageIcon ic= (ImageIcon)vista.getLblFoto().getIcon();
            vehiculo.setFoto(ic.getImage());
            if(vehiculo.crearV()){
                JOptionPane.showMessageDialog(vista, "Vehiculo creado correctamente");
                //TICKET
                String idticket = vista.getTextid_ticket().getText();
                String[] idzona = vista.getComboBoxid_zona().getSelectedItem().toString().split("-");
                String idplaca = vista.getTxtplaca().getText();
                Date fechaingreso = Date.valueOf(nowTime.toLocalDate());
                Time hora = Time.valueOf(nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                Time horafinal = Time.valueOf(nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                
                ModeloTicket ticket = new ModeloTicket(idticket, idzona[0], idplaca, fechaingreso, hora, horafinal);     
            ///
            ModeloZona zona = new ModeloZona();
            List<Zona> lista =zona.listarZonas(idzona[0]);
            lista.stream().forEach(p -> {
                zona.setIdzona(p.getIdzona());
                zona.setNombre(p.getNombre());
                zona.setNombre(p.getNombre());
                zona.setTarifa(p.getTarifa());  
                zona.setEstado("OCUPADO");
                 zona.setFoto(p.getFoto());
            });
            zona.editar();
            ///
            if (ticket.crear()) {
                JOptionPane.showMessageDialog(vista, "TICKET creado correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "TICKET  no creado");
            }
            }else {
                JOptionPane.showMessageDialog(vista, "Vehiculo no creado");
            }
             vista.setVisible(false);
        }else{
             JOptionPane.showMessageDialog(vista, "ERROR");   
        }
        }
       
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void CambiarelPanelfrente(){ 
         if(Integer.parseInt(vista.getTxtNumpanel().getText())==3){
           GuardarDatos();
        }
        if(Integer.parseInt(vista.getTxtNumpanel().getText())==2){
            if(ValidarT()==false){
            vista.getJpConfirmacion2().setVisible(true);
            vista.getJpTicket2().setVisible(false);
            vista.getBtNext().setText("FINALIZAR");
            vista.getJpLTicket().setBackground(new Color(9,122,253));
            vista.getJpLConfirmacion().setBackground(new Color(1,187,246));
            vista.getTxtNumpanel().setText(String.valueOf(Integer.parseInt(vista.getTxtNumpanel().getText())+1));
            
            vista.getLcc1().setText(vista.getId().getText());
            vista.getLcp1().setText(vista.getTxtplaca().getText());
            vista.getLct().setText(vista.getTextid_ticket().getText());
            vista.getLcf().setText("" + nowTime.format(DateTimeFormatter.ISO_DATE));
            vista.getLch().setText("" + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)));
            }else{
                JOptionPane.showMessageDialog(null,"Llene los datos requeridos", "ERROR", 0);
            }
            
        }
        if(Integer.parseInt(vista.getTxtNumpanel().getText())==1){
            if(Validarv()==false){
                 vista.getJpAuto2().setVisible(false);
            vista.getJpTicket2().setVisible(true);
            vista.getJoLAuto().setBackground(new Color(9,122,253));
            vista.getJpLTicket().setBackground(new Color(1,187,246));
            vista.getTxtNumpanel().setText(String.valueOf(Integer.parseInt(vista.getTxtNumpanel().getText())+1));
            }else{
                JOptionPane.showMessageDialog(null,"Llene los datos requeridos", "ERROR", 0);
            }
           
        }
        if(Integer.parseInt(vista.getTxtNumpanel().getText())==0){
            if(Validarc()==false){
            vista.getJpAuto2().setVisible(true);
            vista.getJpCliente2().setVisible(false);
            vista.getJpLCliente().setBackground(new Color(9,122,253));
            vista.getJoLAuto().setBackground(new Color(1,187,246));
            vista.getTxtNumpanel().setText(String.valueOf(Integer.parseInt(vista.getTxtNumpanel().getText())+1));
            }else{
                JOptionPane.showMessageDialog(null,"Llene los datos requeridos", "ERROR", 0);
            }
           
        }
   
    }
    public void CambiarelPaneltras(){
        if(Integer.parseInt(vista.getTxtNumpanel().getText())==1){
            vista.getJpAuto2().setVisible(false);
            vista.getJpCliente2().setVisible(true);
            vista.getJpLCliente().setBackground(new Color(1,187,246));
            vista.getJoLAuto().setBackground(new Color(9,122,253));
            vista.getTxtNumpanel().setText(String.valueOf(Integer.parseInt(vista.getTxtNumpanel().getText())-1));
        }
        if(Integer.parseInt(vista.getTxtNumpanel().getText())==2){
            vista.getJpAuto2().setVisible(true);
            vista.getJpTicket2().setVisible(false);
            vista.getJoLAuto().setBackground(new Color(1,187,246));
            vista.getJpLTicket().setBackground(new Color(9,122,253));
            vista.getTxtNumpanel().setText(String.valueOf(Integer.parseInt(vista.getTxtNumpanel().getText())-1));
        }   
        if(Integer.parseInt(vista.getTxtNumpanel().getText())==3){
            vista.getJpConfirmacion2().setVisible(false);
            vista.getJpTicket2().setVisible(true);
            vista.getJpLTicket().setBackground(new Color(1,187,246));
            vista.getJpLConfirmacion().setBackground(new Color(9,122,253));
            vista.getBtNext().setText("SIGUIENTE");
            vista.getTxtNumpanel().setText(String.valueOf(Integer.parseInt(vista.getTxtNumpanel().getText())-1));
        }

    }
     private void cargarImagen(){

            JFileChooser jfcs= new JFileChooser();
            jfcs.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfcs.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.PNG","*.PNG", "JPG","PNG","jfif");
            jfcs.setFileFilter(filtro);
            int estado=jfcs.showOpenDialog(null);
            if(estado==JFileChooser.APPROVE_OPTION){
                try {
                    Image icono = ImageIO.read(jfcs.getSelectedFile()).getScaledInstance(
                            vista.getLblFoto().getWidth(),
                            vista.getLblFoto().getHeight(),
                            Image.SCALE_DEFAULT
                    );
                 Icon ic=new ImageIcon(icono);
                 vista.getLblFoto().setIcon(ic);
                 vista.getLblFoto().updateUI();
                } catch (IOException ex) {
                    Logger.getLogger(ControladorVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    private void  Cerrar(){
        int res = JOptionPane.showConfirmDialog(
            null, 
            "¿Esta seguro que decea SALIR?", 
            "Confirm",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        if ( res == JOptionPane.NO_OPTION ) {     
          System.out.println( "Seleccionaste NO" );

        } else if (res == JOptionPane.YES_OPTION) {
          vista.setVisible(false);  
          System.out.println( "Seleccionaste SI" );

        } else if (res == JOptionPane.CLOSED_OPTION) {
          System.out.println( "Cerraste la ventana" );
        }

        System.out.println( "Valor devuelto: " + res );
    }
    
    
    
    
    private boolean Validarc(){
        return vista.getId().getText().isEmpty()||vista.getNom().getText().isEmpty()||vista.getApell().getText().isEmpty()
                ||vista.getTel().getText().isEmpty()||vista.getDireccion().getText().isEmpty()||ValidacionesCedula()==false;
    }
     private boolean Validarv(){
        return vista.getTxtplaca().getText().isEmpty()||vista.getTxtmarca().getText().isEmpty()||vista.getTxtcolor().getText().isEmpty()
                ||vista.getJctipoV().getSelectedItem().equals("SELECCIONAR")||vista.getLblFoto().getIcon().equals(null);
    }
     private boolean ValidarT(){
        return vista.getComboBoxid_zona().getSelectedItem().equals("SELECCIONAR");
    }
    public boolean ValidacionesCedula(){
        int c, suma=0, acum, resta=0;
        try {
        for (int i = 0; i < vista.getId().getText().length()-1; i++) {
            c=Integer.parseInt(vista.getId().getText().charAt(i)+"");
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
        ultimos=Integer.parseInt(vista.getId().getText().charAt(9)+"");
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
    
}
