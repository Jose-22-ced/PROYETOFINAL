package SDAV.Controlador;


import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloTicket;
import SDAV.Modelo.ModeloVehiculo;
import SDAV.Modelo.ModeloZona;
import SDAV.Modelo.Ticket;
import SDAV.Modelo.Vehiculo;
import SDAV.Modelo.Zona;
import SDAV.Vista.VistaTicket;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Date;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/////////////////////////////////////////////////////////////////////////////<<
/**
 *
 * @author LEO
 */
public class ControladorTicket extends Thread{

    private ModeloTicket modelo;
    private VistaTicket vista;
    private Thread h1;
    LocalDateTime nowTime = LocalDateTime.now();
    public ControladorTicket(ModeloTicket modelo, VistaTicket vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.getTablaticket().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTablaticket().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTablaticket().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.setVisible(true);
        h1 = new Thread(this);
        h1.start();
        ModeloVehiculo modelov = new ModeloVehiculo();
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vista.getTABLA_PLACA().getModel();
        tblModel.setNumRows(0);
        List<Vehiculo> lista2 = modelov.listar("");
        lista2.stream().forEach(p -> {
            String[] vehiculo = {p.getPlaca(),p.getMarca()};
            tblModel.addRow(vehiculo);
        });
        
       
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
    public void iniciaControl() {
        KeyListener kl = new KeyListener() {
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
                cargarLista(vista.getTxtbuscar().getText()); //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                if (vista.getTextid_ticket().getText().length() == 5) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
   
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        };
        
        vista.getB_listarticket().addActionListener(l -> cargarLista(""));
        vista.getB_crearticket().addActionListener(l -> crearticket());
        vista.getBaceptar().addActionListener(l -> grabarticketv());
        vista.getB_agregar().addActionListener(l -> agregarplaca());
        vista.getBcancelar().addActionListener(l -> cancelar());
        vista.getB_editarticket().addActionListener(l -> modificarticket());
        vista.getB_eliminarticket().addActionListener(l -> eliminarticket());
        vista.getTxtbuscar().addKeyListener(kl);
            vista.getTextid_ticket().addKeyListener(validarid);
            vista.getB_imprimirticket().addActionListener(l->imprimirReporte());
    }

    private void cargarLista(String aguja) {
        DefaultTableModel tcModelo = (DefaultTableModel) vista.getTablaticket().getModel();
        tcModelo.setNumRows(0);
        vista.getTablaticket().setRowHeight(70);
        List<Ticket> lista = modelo.Listar(aguja);
        int ncols = tcModelo.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(t -> {
            tcModelo.addRow(new Object[ncols]);
            vista.getTablaticket().setValueAt(t.getCodigoticket(), i.value, 0);
            vista.getTablaticket().setValueAt(t.getId_zona(), i.value, 1);
            vista.getTablaticket().setValueAt(t.getPlaca(), i.value, 2);
            vista.getTablaticket().setValueAt(t.getFechaingreso(), i.value, 3);
            vista.getTablaticket().setValueAt(t.getHoraingreso(), i.value, 4);
            vista.getTablaticket().setValueAt(t.getHorasalida(), i.value, 5);
            i.value++;
        });
    }

    private void limpiar() {
        
        vista.getTextid_ticket().setText("");
        vista.getComboBoxid_zona().setSelectedIndex(-1);
        vista.getTxtplaca().setText("");
        vista.getTABLA_PLACA().getSelectedRow();
        vista.getLabelfecha().setText("");
        vista.getLabelhora().setText("");
        vista.getComboBoxid_zona().setEnabled(true);
        
    }

    private void crearticket() {
        limpiar();
        Random num = new Random();
        h1 = new Thread(this);
        h1.start(); 
        vista.getTextid_ticket().setText("TK-0");
        vista.getComboBoxid_zona().removeAllItems();
        vista.getComboBoxid_zona().addItem("SELECCIONAR");
        vista.getDlgTicket().setSize(570, 380);
        vista.getDlgTicket().setLocationRelativeTo(null);
        vista.getDlgTicket().setVisible(true);
        vista.getBaceptar().setVisible(true);
        vista.getBaceptar().setText("CREAR");
        vista.getBcancelar().setText("CANCELAR");
        vista.getDlgTicket().setVisible(true);
         List<Ticket> lista = modelo.Listar("");
        int a =(int) lista.stream().count(); 
         vista.getTextid_ticket().setText("TK"+"-"+String.valueOf(a+1)+""+num.nextInt(9));
        Mostar();
    }

    private void cancelar() {
        int res = JOptionPane.showConfirmDialog(
                null,
                "¿Esta seguro que decea SALIR?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (res == JOptionPane.NO_OPTION) {
            System.out.println("Selecciono NO");
        } else if (res == JOptionPane.YES_OPTION) {
            vista.getDlgTicket().setVisible(false);
            System.out.println("Selecciono  SI");
        } else if (res == JOptionPane.CLOSED_OPTION) {
            System.out.println("Cerraste la ventana");
        }
        System.out.println("Valor devuelto: " + res);
    }

    public void agregarplaca() {
        int fila = vista.getTABLA_PLACA().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una placa");
        } else {
            String placa = (String) vista.getTABLA_PLACA().getValueAt(fila, 0);
            vista.getTxtplaca().setText(placa);
        }
    }
    
    private void grabarticket() {
        if (vista.getBaceptar().getText() == "CREAR") {
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
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "TICKET  no creado");
            }
            vista.getDlgTicket().setVisible(false);
            cargarLista("");
        } else {
            String idticket = vista.getTextid_ticket().getText();
            String[] idzona = vista.getComboBoxid_zona().getSelectedItem().toString().split("-");
            String idplaca = vista.getTxtplaca().getText();
            
            Date fechaingreso = Date.valueOf(vista.getLabelfecha().getText());
            Time hora = Time.valueOf(vista.getLabelhora().getText());
            Time horafinal = Time.valueOf(vista.getLabelhora().getText());
            
            ModeloTicket ticket = new ModeloTicket(idticket, idzona[0], idplaca, fechaingreso, hora, horafinal);
            if (ticket.editar()) {
                JOptionPane.showMessageDialog(vista, "TICKET MODIFICADO correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "TICKET no  MODIFICADO ");
            }
            vista.getDlgTicket().setVisible(false);
            cargarLista("");
           
        }
    }

    private void modificarticket() {
        if (vista.getTablaticket().getSelectedRow() != -1) {
             h1.stop();
             vista.getComboBoxid_zona().setEnabled(false);
             vista.getTextid_ticket().setEnabled(false);
            vista.getDlgTicket().setVisible(true);
            vista.getDlgTicket().setLocationRelativeTo(vista);
            vista.getDlgTicket().setSize(570, 380);
            vista.getDlgTicket().setTitle("MODIFICAR TICKET");
            vista.getBaceptar().setText("GUARDAR");
            DefaultTableModel nt = (DefaultTableModel) vista.getTablaticket().getModel();
            int fila = vista.getTablaticket().getSelectedRow();

            vista.getTextid_ticket().setText(nt.getValueAt(fila, 0).toString());
            ModeloZona modeloz = new ModeloZona();
            List<Zona> lista = modeloz.listarZonas(nt.getValueAt(fila, 1).toString());
            lista.stream().forEach(p -> {
                vista.getComboBoxid_zona().addItem(p.getIdzona() + "-" + p.getNombre());
                vista.getComboBoxid_zona().setSelectedItem(p.getIdzona() + "-" + p.getNombre());

            });
            vista.getTxtplaca().setText(nt.getValueAt(fila, 2).toString());
            vista.getTxtplaca().enable(false);
            vista.getLabelfecha().setText(nt.getValueAt(fila, 3).toString());
            vista.getLabelhora().setText(nt.getValueAt(fila, 4).toString());
            

        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila primero");
        }
    }

    private void eliminarticket() {
        int fila = vista.getTablaticket().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Ticket primero");
        } else {
            String idticket = (String) vista.getTablaticket().getValueAt(fila, 0);
            String zonas = (String) vista.getTablaticket().getValueAt(fila, 1);
            ModeloTicket ticket = new ModeloTicket();
            ticket.setCodigoticket(idticket);
            
            
             ModeloZona zona = new ModeloZona();
            List<Zona> lista =zona.listarZonas(zonas);
            lista.stream().forEach(p -> {
                zona.setIdzona(p.getIdzona());
                zona.setNombre(p.getNombre());
                zona.setNombre(p.getNombre());
                zona.setTarifa(p.getTarifa());  
                zona.setEstado("LIBRE");
                 zona.setFoto(p.getFoto());
            });
            zona.editar();

            if (ticket.eliminar(idticket)) {
                JOptionPane.showMessageDialog(vista, "Ticket ELIMINADO correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "ERROR");
            }
            vista.getDlgTicket().setVisible(false);
            cargarLista("");
            Mostar();
        }
    }
    public void Mostar(){
        ModeloZona modeloz = new ModeloZona();
        List<Zona> lista = modeloz.listarZonas("");
        lista.stream().forEach(z -> {
            if (z.getEstado().equals("LIBRE")) {
                 vista.getComboBoxid_zona().addItem(z.getIdzona()+"-"+z.getNombre());
            }
        });
    }
  public void grabarticketv() {
        if (vista.getTextid_ticket().getText().isEmpty() || vista.getTxtplaca().getText().isEmpty() ||  vista.getComboBoxid_zona().getSelectedItem().toString().equals("SELECCIONAR")
       ) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos requeridos");
        } else {
            vista.getBaceptar().setEnabled(true);
            grabarticket();
        }
    }
  private void imprimirReporte() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/tickets/reporteticket.jasper"));
            String aguja = vista.getTxtbuscar().getText();
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
