/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.Cliente;
import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloCliente;
import SDAV.Modelo.ModeloVehiculo;
import SDAV.Modelo.Vehiculo;
import SDAV.Vista.VistaVehiculo;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
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
    public class ControladorVehiculo {
        private VistaVehiculo vista;
        private ModeloVehiculo modelo;
    

    public ControladorVehiculo(VistaVehiculo vista,ModeloVehiculo modelo) {
        this.vista = vista;
        this.modelo= modelo;
        vista.getTabVehiculos().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTabVehiculos().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTabVehiculos().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.setVisible(true);
        ModeloCliente modeloclinete = new ModeloCliente();
        List<Cliente> lista = modeloclinete.Listar("");
        lista.stream().forEach(p->{
            vista.getJcPropietario().addItem(p.getCedula()+"-"+p.getApellido());
        });
    }
    public void IniciarControl(){
            KeyListener k1= new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                @Override
                public void keyPressed(KeyEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    cargalistaV(vista.getTxtBuscarV().getText());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
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
/////////////////////////////////////////////////
           vista.getBtnListarV().addActionListener(l->cargalistaV(""));
            vista.getBtnCrearV().addActionListener(l->crearVehiculo());
            vista.getBtnaceptV().addActionListener(l->grabarauto());
            vista.getBtncancV().addActionListener(l->cancelarV());
            vista.getBtnEliminarV().addActionListener(l->EliminarVehiculo());
            vista.getBtnEditarV().addActionListener(l->EditarVehiculo());    
            vista.getTxtBuscarV().addKeyListener(k1);
            vista.getBtnExamnV().addActionListener(l->cargarImagen());
            vista.getTxtplaca().addKeyListener(validarplaca);
            vista.getTxtcolor().addKeyListener(validarcolor);
            vista.getTxtmarca().addKeyListener(validarmarca);
            vista.getBtnImprimirV().addActionListener(l->imprimirReporte());
    }
    private void cargalistaV(String aguja){
            vista.getTabVehiculos().setDefaultRenderer(Object.class, new ImagenTabla());
            vista.getTabVehiculos().setRowHeight(100);
            DefaultTableCellRenderer renderer= new DefaultTableCellRenderer();

            DefaultTableModel tblModel;
            tblModel =(DefaultTableModel)vista.getTabVehiculos().getModel();
            tblModel.setNumRows(0);
            List<Vehiculo> lista = modelo.listar(aguja);
            int ncols=tblModel.getColumnCount();
            Holder<Integer> i = new Holder<>(0);
            lista.stream().forEach(p->{
                tblModel.addRow(new Object[ncols]);
                // String[] fila={p.getIdPersona(),p.getNombre(),p.getApellidos(),String.valueOf(p.getEdad()),p.getTelefono(),p.getSexo(),String.valueOf(p.getSueldo()),String.valueOf(p.getCupo())};
                vista.getTabVehiculos().setValueAt(p.getPlaca(), i.value, 0);
                vista.getTabVehiculos().setValueAt(p.getCedula_cli(), i.value, 1);
                vista.getTabVehiculos().setValueAt(p.getMarca(), i.value, 2);
                vista.getTabVehiculos().setValueAt(p.getColor(), i.value, 3);
                vista.getTabVehiculos().setValueAt(p.getTipo_vehiculo(), i.value, 4);

                Image img = p.getFoto();
                 if(img!=null){
                    Image newimg = img.getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(newimg);
                    renderer.setIcon(icon);
                    vista.getTabVehiculos().setValueAt(new JLabel(icon), i.value, 5);
               }else{
                   vista.getTabVehiculos().setValueAt(null, i.value, 5);
               }
               i.value++;

            });
        }
    
    private void crearVehiculo(){
        vista.getTxtplaca().setEnabled(true);
         vista.getCrearVehiculo().setTitle("  Crear Vehiculo  ");
         vista.getCrearVehiculo().setSize(570, 430);
         vista.getCrearVehiculo().setLocationRelativeTo(vista);
         vista.getTxtplaca().setText("");
         vista.getJcPropietario().setSelectedItem("SELECCIONAR");
         vista.getTxtmarca().setText("");
         vista.getTxtcolor().setText("");
         vista.getJctipoV().setSelectedItem("SELECCIONAR");
         vista.getBtnaceptV().setText("Crear");
         vista.getBtncancV().setText("Cancelar");
         vista.getLblFoto().setIcon(null);
         vista.getCrearVehiculo().setVisible(true);


        }
    private void cancelarV(){

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
          vista.getCrearVehiculo().setVisible(false);  
          System.out.println( "Seleccionaste SI" );

        } else if (res == JOptionPane.CLOSED_OPTION) {
          System.out.println( "Cerraste la ventana" );
        }

        System.out.println( "Valor devuelto: " + res );
      } 
    private void grabarVehiculo(){

            if (vista.getBtnaceptV().getText()=="Crear") {
            String placa=vista.getTxtplaca().getText();
            String[] cedu= vista.getJcPropietario().getSelectedItem().toString().split("-");
            String marca=vista.getTxtmarca().getText();
            String color=vista.getTxtcolor().getText();
            String tipo=vista.getJctipoV().getSelectedItem().toString();
            vista.getTxtplaca().setText("");
            vista.getJcPropietario().setSelectedItem("SELECCIONAR");
            vista.getTxtmarca().setText(""); 
            vista.getTxtcolor().setText("");
            vista.getJctipoV().setSelectedItem("SELECCIONAR");

            ModeloVehiculo vehiculo=new ModeloVehiculo(placa, cedu[0], marca, color, tipo);
            ImageIcon ic= (ImageIcon)vista.getLblFoto().getIcon();
            vehiculo.setFoto(ic.getImage());
            if(vehiculo.crearV()){
                JOptionPane.showMessageDialog(vista, "Vehiculo creado correctamente");
            }else {
                JOptionPane.showMessageDialog(vista, "Vehiculo no creado");
            }
            vista.getCrearVehiculo().setVisible(false);
            cargalistaV("");
            }else{
            String placa=vista.getTxtplaca().getText();
            String[] cedu= vista.getJcPropietario().getSelectedItem().toString().split("-");
            String marca=vista.getTxtmarca().getText();
            String color=vista.getTxtcolor().getText();
            String tipo=vista.getJctipoV().getSelectedItem().toString();
            vista.getTxtplaca().setText("");
            vista.getJcPropietario().setSelectedItem("SELECCIONAR");
            vista.getTxtmarca().setText(""); 
            vista.getTxtcolor().setText("");
            vista.getJctipoV().setSelectedItem("SELECCIONAR");

            ModeloVehiculo vehiculo=new ModeloVehiculo(placa, cedu[0], marca, color, tipo);
            ImageIcon ic= (ImageIcon)vista.getLblFoto().getIcon();
            vehiculo.setFoto(ic.getImage());
            if(vehiculo.editarV()){
                JOptionPane.showMessageDialog(vista, "Vehiculo modificado correctamente");
            }else {
                JOptionPane.showMessageDialog(vista, "Vehiculo no modificado");
            }
            vista.getCrearVehiculo().setVisible(false);
            cargalistaV("");
            }
        }  
    
        public void grabarauto() {
        if (vista.getTxtplaca().getText().isEmpty() || vista.getJcPropietario().getSelectedItem().toString().isEmpty() || 
             vista.getTxtmarca().getText().isEmpty() ||vista.getTxtcolor().getText().isEmpty() || vista.getJctipoV().getSelectedItem().toString().isEmpty()
                ||vista.getJcPropietario().getSelectedItem().toString().equals("SELECCIONAR") || vista.getJctipoV().getSelectedItem().toString().equals("SELECCIONAR")) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos requeridos");
        } else {
            vista.getBtnaceptV().setEnabled(true);
            grabarVehiculo();
        }
    }

    private void cargarImagen(){

            JFileChooser jfc= new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.PNG","*.PNG", "JPG","PNG","jfif");
            jfc.setFileFilter(filtro);
            int estado=jfc.showOpenDialog(null);
            if(estado==JFileChooser.APPROVE_OPTION){
                try {
                    Image icono = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(
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
    private void EliminarVehiculo(){
            int res = JOptionPane.showConfirmDialog(
            null, 
            "¿Esta seguro", 
            "Confirm",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        if ( res == JOptionPane.NO_OPTION ) {     
          System.out.println( "Seleccionaste NO" );

        } else if (res == JOptionPane.YES_OPTION) {

             if(vista.getTabVehiculos().getSelectedRow()!=-1){
         DefaultTableModel tbvehiculo = (DefaultTableModel) vista.getTabVehiculos().getModel();
         int fila=vista.getTabVehiculos().getSelectedRow();
         String string=tbvehiculo.getValueAt(fila, 0).toString();
         ModeloVehiculo vehiculo=new ModeloVehiculo();
         vehiculo.setPlaca(string);
    //     ImageIcon ic= (ImageIcon)vista.getLblFoto().getIcon();
    //     persona.setFoto(ic.getImage());
         if(vehiculo.eliminarV()){
                JOptionPane.showMessageDialog(vista, "Vehiculo Eliminado correctamente");
            }else {
                JOptionPane.showMessageDialog(vista, "Vehiculo no Eliminado");
            }
         cargalistaV("");
             }else{
            JOptionPane.showMessageDialog(vista, "PRIMERO HAGA CLIC EN LA PERSONA QUE DESEA ELIMINAR\n"
                        +"Y LUEGO EN EL BOTON ELIMINAR");
        }

          System.out.println( "Seleccionaste SI" );

        } else if (res == JOptionPane.CLOSED_OPTION) {
          System.out.println( "Cerraste la ventana" );
        }

        System.out.println( "Valor devuelto: " + res );



     }
    private void EditarVehiculo(){
          if(vista.getTabVehiculos().getSelectedRow()!=-1){  
           vista.getTxtplaca().setEnabled(false);
          vista.getCrearVehiculo().setVisible(true);
          vista.getCrearVehiculo().setLocationRelativeTo(vista);
          vista.getCrearVehiculo().setSize(570, 440);
          vista.getCrearVehiculo().setTitle("Modificar Vehículo");
          vista.getBtnaceptV().setText("Guardar");
          DefaultTableModel tbvehiculo = (DefaultTableModel) vista.getTabVehiculos().getModel();
          int fila=vista.getTabVehiculos().getSelectedRow();
          vista.getTxtplaca().setText(tbvehiculo.getValueAt(fila, 0).toString());
          
          ModeloCliente modeloclinete = new ModeloCliente();
            List<Cliente> lista = modeloclinete.Listar(tbvehiculo.getValueAt(fila, 1).toString());
            lista.stream().forEach(p->{
                vista.getJcPropietario().setSelectedItem(p.getCedula()+"-"+p.getApellido());
            });
          vista.getTxtmarca().setText(tbvehiculo.getValueAt(fila, 2).toString());
          vista.getTxtcolor().setText(tbvehiculo.getValueAt(fila, 3).toString());
          vista.getJctipoV().setSelectedItem(tbvehiculo.getValueAt(fila, 4).toString());
          JLabel foto= (JLabel)vista.getTabVehiculos().getValueAt(fila, 5);
          vista.getLblFoto().updateUI();
          vista.getLblFoto().setIcon(foto.getIcon());
            }else{
                JOptionPane.showMessageDialog(vista, "PRIMERO HAGA CLIC EN LA PERSONA QUE DESEA EDITAR\n"
                        +"Y LUEGO EN EL BOTON EDITAR");
            }  
     } 
    private void imprimirReporte() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/vehiculos/ReporteAutos.jasper"));
            String aguja = vista.getTxtBuscarV().getText();
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja","%"+aguja+"%");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

           
}
