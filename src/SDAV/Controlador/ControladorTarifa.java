/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloTarifa;
import SDAV.Modelo.Tarifa;
import SDAV.Vista.VistaTarifa;
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
public class ControladorTarifa {
    
    VistaTarifa vista;
    ModeloTarifa modelo;

    public ControladorTarifa(VistaTarifa vista, ModeloTarifa modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.getTabTarifa().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTabTarifa().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTabTarifa().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.setVisible(true);
    }
    
    public void iniciarContro(){
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
                KeyListener validaridtarifa = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c) && Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
                if (vista.getTxtCod_Tarifa().getText().length() == 10) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " El numero máximo de carácteres es 10");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
            KeyListener validarsueldo = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 32 && e.getKeyChar() <= 45 
                        || e.getKeyChar() >= 58 && e.getKeyChar() <= 127  ) {
                    e.consume();
                }
                if (vista.getTxtPrecio().getText().length() == 8) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, "A excedido del número máximo de caracteres");
                    e.consume();
                }
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        KeyListener validarmax = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 32 && e.getKeyChar() <= 45
                        || e.getKeyChar() >= 58 && e.getKeyChar() <= 127  ) {
                    e.consume();
                } 
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        KeyListener validarmin = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 32 && e.getKeyChar() <= 45
                        || e.getKeyChar() >= 58 && e.getKeyChar() <= 127  ) {
                    e.consume();
                } 
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        vista.getBtnListar().addActionListener(l->cargarLista(""));
        vista.getTxtBuscar().addKeyListener(tcl);
        vista.getBtnCrear().addActionListener(l->CrearTarifa());
        vista.getBtnEditar().addActionListener(l->EditarTarifa());
        vista.getBtnacept().addActionListener(l->grabartarifa1());
        vista.getBtnEliminar().addActionListener(l->EliminarTarifa());
        vista.getBtncanc().addActionListener(l->Cerrar());
        vista.getTxtCod_Tarifa().addKeyListener(validaridtarifa);
        vista.getTxtPrecio().addKeyListener(validarsueldo);
        vista.getTxtpmax().addKeyListener(validarmax);
        vista.getTxtmin().addKeyListener(validarmin);
        vista.getBtnImprimir().addActionListener(l->imprimirReporte());
    }
    
    private void cargarLista(String aguja){
        DefaultTableModel tcModelo=(DefaultTableModel)vista.getTabTarifa().getModel();
        tcModelo.setNumRows(0);
        vista.getTabTarifa().setRowHeight(70);
        List<Tarifa> lista = modelo.Listar(aguja);
        int ncols=tcModelo.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        
       lista.stream().forEach(p->{
 
         tcModelo.addRow(new Object[ncols]);
           vista.getTabTarifa().setValueAt(p.getIdtarifa(), i.value , 0);
           vista.getTabTarifa().setValueAt(p.getTipo(), i.value , 1);
           vista.getTabTarifa().setValueAt(p.getPrecio(), i.value , 2);
           i.value++;
         
        });
    }
    
    public void CrearTarifa(){
        vista.getCrearTarifa().setVisible(true);
        vista.getCrearTarifa().setTitle("INGRESAR TARIFA");
        vista.getCrearTarifa().setSize(400, 300);
        vista.getCrearTarifa().setLocationRelativeTo(vista);
        vista.getBtnacept().setText("Crear");
        vista.getTxtCod_Tarifa().setEnabled(true);
        vista.getTxtCod_Tarifa().setText(null);
        vista.getJcTipo().setSelectedItem("SELECCIONE");
        vista.getTxtPrecio().setText(null);
    }
    
    
    public void EditarTarifa(){
        DefaultTableModel tbTarifa = (DefaultTableModel) vista.getTabTarifa().getModel();
        int fila=vista.getTabTarifa().getSelectedRow();
        if(fila!=-1){
        vista.getCrearTarifa().setVisible(true);
        vista.getCrearTarifa().setTitle("EDITAR TARIFA");
        vista.getCrearTarifa().setSize(400, 300);
        vista.getCrearTarifa().setLocationRelativeTo(vista);
        vista.getBtnacept().setText("Guardar");
        vista.getTxtCod_Tarifa().setEnabled(false);
        
        vista.getTxtCod_Tarifa().setText(tbTarifa.getValueAt(fila, 0).toString());
        vista.getJcTipo().setSelectedItem(tbTarifa.getValueAt(fila, 1).toString());
        vista.getTxtPrecio().setText(tbTarifa.getValueAt(fila, 2).toString());
        }else{
            JOptionPane.showMessageDialog(vista, "DE PRIMERO CLICK ENCIMA EN ALGUNA TARIFA Y LUEGO EN EDITAR","AVISO",2);
        }
    }
    
    public void GuardarTarifa(){
        if (vista.getBtnacept().getText().equals("Crear")) {
            String cod_Tarifa= vista.getTxtCod_Tarifa().getText();
            String tipo = vista.getJcTipo().getSelectedItem().toString();
            double precio = Double.valueOf(vista.getTxtPrecio().getText());
            ModeloTarifa cargo = new ModeloTarifa(cod_Tarifa,tipo,precio);
            if(cargo.CrearCargo()){
            JOptionPane.showMessageDialog(vista, "Tarifa Creada con exito","AVISO",-1);  
             }else{
            JOptionPane.showMessageDialog(vista, "ERROR");   
            }         
            vista.getCrearTarifa().setVisible(false);
            cargarLista("");
        }
         if (vista.getBtnacept().getText().equals("Guardar")) {
           String cod_Tarifa= vista.getTxtCod_Tarifa().getText();
            String tipo = vista.getJcTipo().getSelectedItem().toString();
            double precio = Double.valueOf(vista.getTxtPrecio().getText());
            ModeloTarifa cargo = new ModeloTarifa(cod_Tarifa,tipo,precio);
            if(cargo.EditarCargo()){
            JOptionPane.showMessageDialog(vista, "Tarifa modificada con exito","AVISO",-1);  
             }else{
            JOptionPane.showMessageDialog(vista, "ERROR");   
            }         
            vista.getCrearTarifa().setVisible(false);
            cargarLista("");
        }
       
    }
    
    
    public void EliminarTarifa(){   
        DefaultTableModel tbPersona = (DefaultTableModel) vista.getTabTarifa().getModel();
        int fila=vista.getTabTarifa().getSelectedRow();
        if(fila!=-1){
        String cod_Tarifa = tbPersona.getValueAt(fila, 0).toString();
        ModeloTarifa cargo= new ModeloTarifa();
        cargo.setIdtarifa(cod_Tarifa);
        
        if(cargo.EliminarCargo()){
            JOptionPane.showMessageDialog(vista, "Tarifa eliminada con exito");
            cargarLista("");
            
        }else{
             JOptionPane.showMessageDialog(vista, "ERROR");
             
        }
        }else{
            JOptionPane.showMessageDialog(vista, "DE PRIMERO CLICK ENCIMA EN ALGUNa TARIFA Y LUEGO EN ELIMINAR","AVISO",2);
        }  
    }
    
        public void grabartarifa1() {
        if (vista.getTxtCod_Tarifa().getText().isEmpty() || vista.getTxtPrecio().getText().isEmpty() 
                || vista.getJcTipo().getSelectedItem().toString().equals("SELECCIONE")) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos requeridos");
        } else {
            vista.getBtnacept().setEnabled(true);
            GuardarTarifa();
        }
    }
    
    public void Cerrar(){
        vista.getCrearTarifa().setVisible(false);
    }
     private void imprimirReporte() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/tarifas/reportetarifas.jasper"));
            String aguja = vista.getTxtBuscar().getText();
            String p_max = vista.getTxtpmax().getText();
            String p_min = vista.getTxtmin().getText();
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja", "%" + aguja + "%");
            if (vista.getTxtpmax().getText().isEmpty()||vista.getTxtmin().getText().isEmpty()) {                
            }else{
                if(vista.getJcMax().isSelected()){
                  parametros.put("max", Double.valueOf(p_max));
                }
                if(vista.getJcMin().isSelected()){
                    parametros.put("min", Double.valueOf(p_min)); 
                }
            }  
       JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
              
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
