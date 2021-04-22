/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.Cargo;
import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloCargo;
import SDAV.Vista.VistaCargo;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ryzen
 */
public class ControladorCargo {
    VistaCargo vista;
    ModeloCargo modelo;

    public ControladorCargo(VistaCargo vista, ModeloCargo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.getTabCargo().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTabCargo().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTabCargo().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.setVisible(true);
    }
   
    public void iniciarControl(){
        KeyListener tcl=new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void keyReleased(KeyEvent e) {
                cargarLista(vista.getTxtBuscar().getText());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
          KeyListener validarid = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c) && Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
                if (vista.getTxtId_Cargo().getText().length() == 8) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " El numero máximo de carácteres es 8");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
             KeyListener validarnombre = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 33 && e.getKeyChar() <= 64
                        || e.getKeyChar() >= 91 && e.getKeyChar() <= 96
                        || e.getKeyChar() >= 123 && e.getKeyChar() <= 208
                        || e.getKeyChar() >= 210 && e.getKeyChar() <= 240) {
                    e.consume();
                }
                if (vista.getTxtNombre().getText().length() == 30) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " Alcanzó el máximo número de caracteres permitidos <30> ");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
     vista.getBtnListar().addActionListener(l->cargarLista(""));
     vista.getBtnCrear().addActionListener(l->CrearCargo());
     vista.getTxtBuscar().addKeyListener(tcl);
     vista.getBtnacept().addActionListener(l->grabarcargo());
     vista.getBtnEditar().addActionListener(l->EditarCargo());
     vista.getBtnEliminar().addActionListener(l->EliminarCargo());
     vista.getBtncanc().addActionListener(l->Cerrar());
     vista.getTxtId_Cargo().addKeyListener(validarid);
     vista.getTxtNombre().addKeyListener(validarnombre);
     vista.getBtnImprimir().addActionListener(l->imprimirReporte());
    }
    
    private void cargarLista(String aguja){
        DefaultTableModel tcModelo=(DefaultTableModel)vista.getTabCargo().getModel();
        tcModelo.setNumRows(0);
        vista.getTabCargo().setRowHeight(70);
        List<Cargo> lista = modelo.Listar(aguja);
        int ncols=tcModelo.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        
       lista.stream().forEach(p->{
 
         tcModelo.addRow(new Object[ncols]);
           vista.getTabCargo().setValueAt(p.getId_cargo(), i.value , 0);
           vista.getTabCargo().setValueAt(p.getNombres(), i.value , 1);
           i.value++;
         
        });
    }
    
    public void CrearCargo(){
        
        vista.getCrearCargo().setVisible(true);
        vista.getCrearCargo().setTitle("INGRESAR CARGO");
        vista.getCrearCargo().setSize(400, 250); 
        vista.getCrearCargo().setLocationRelativeTo(vista);
        vista.getBtnacept().setText("Crear");     
        vista.getTxtId_Cargo().setEnabled(true);
        vista.getTxtId_Cargo().setText(null);
        vista.getTxtNombre().setText(null);
       
    }
    
     public void EditarCargo(){
        DefaultTableModel tbCargo = (DefaultTableModel) vista.getTabCargo().getModel();
        int fila=vista.getTabCargo().getSelectedRow();
        if(fila!=-1){
        vista.getCrearCargo().setVisible(true);
        vista.getCrearCargo().setTitle("EDITAR CARGO");
        vista.getCrearCargo().setSize(400, 250);
        vista.getCrearCargo().setLocationRelativeTo(vista);
        vista.getBtnacept().setText("Guardar");
        vista.getTxtId_Cargo().setEnabled(false);
        
        vista.getTxtId_Cargo().setText(tbCargo.getValueAt(fila, 0).toString());
        vista.getTxtNombre().setText(tbCargo.getValueAt(fila, 1).toString());
        }else{
            JOptionPane.showMessageDialog(vista, "DE PRIMERO CLICK ENCIMA EN ALGUN CARGO Y LUEGO EN EDITAR","AVISO",2);
        }
        
         
        
        
    }
     public void EliminarCargo(){   
        DefaultTableModel tbPersona = (DefaultTableModel) vista.getTabCargo().getModel();
        int fila=vista.getTabCargo().getSelectedRow();
        if(fila!=-1){
        String id_cargo = tbPersona.getValueAt(fila, 0).toString();
        ModeloCargo cargo= new ModeloCargo();
        cargo.setId_cargo(id_cargo);
        
        if(cargo.EliminarCargo()){
            JOptionPane.showMessageDialog(vista, "Cargo eliminado con exito");
            cargarLista("");
            
        }else{
             JOptionPane.showMessageDialog(vista, "ERROR(PUEDE QUE EL CARGO ESTE ASIGANDO A UN EMPLEADO)");
             
        }
        }else{
            JOptionPane.showMessageDialog(vista, "DE PRIMERO CLICK ENCIMA EN ALGUN CARGO Y LUEGO EN ELIMINAR","AVISO",2);
        }
        
        
    }
    
    public void GuardarCargo(){
        if (vista.getBtnacept().getText().equals("Crear")) {
            String id_cargo= vista.getTxtId_Cargo().getText();
            String nombre = vista.getTxtNombre().getText();
            ModeloCargo cargo = new ModeloCargo(id_cargo,nombre);
            if(cargo.CrearCargo()){
            JOptionPane.showMessageDialog(vista, "Cargo Creada con exito","AVISO",-1);  
             }else{
            JOptionPane.showMessageDialog(vista, "ERROR","AVISO",0);   
            }         
            vista.getCrearCargo().setVisible(false);
            cargarLista("");
        }
         if (vista.getBtnacept().getText().equals("Guardar")) {
            String id_cargo= vista.getTxtId_Cargo().getText();
            String nombre = vista.getTxtNombre().getText();
            ModeloCargo cargo = new ModeloCargo(id_cargo,nombre);
            if(cargo.EditarCargo()){
            JOptionPane.showMessageDialog(vista, "Cargo modificada con exito","AVISO",-1);  
             }else{
            JOptionPane.showMessageDialog(vista, "ERROR","AVISO",0);   
            }         
            vista.getCrearCargo().setVisible(false);
            cargarLista("");
        }
       
    }
    public void Cerrar(){
        vista.getCrearCargo().setVisible(false);
    }
     public void grabarcargo() {
        if (vista.getTxtId_Cargo().getText().isEmpty() || vista.getTxtNombre().getText().isEmpty() ) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos requeridos","AVISO",1);
        } else {
            vista.getBtnacept().setEnabled(true);
            GuardarCargo();
        }
    }
     
     private void imprimirReporte() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/cargos/ReporteCargos.jasper"));
            String aguja = vista.getTxtBuscar().getText();
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja", "%" + aguja + "%");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
        
}
