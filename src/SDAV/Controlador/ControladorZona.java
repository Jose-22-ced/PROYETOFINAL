package SDAV.Controlador;

import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloTarifa;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import SDAV.Modelo.ModeloZona;
import SDAV.Modelo.Tarifa;
import SDAV.Modelo.Zona;
import SDAV.Vista.VistaZonaEstacionamiento;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author LEO_Guartambel
 */
public class ControladorZona {

    private ModeloZona modelo;
    private VistaZonaEstacionamiento vista;

    public ControladorZona(ModeloZona modelo, VistaZonaEstacionamiento vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.getTablazona().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTablazona().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTablazona().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.setVisible(true);
        ModeloTarifa modelot = new ModeloTarifa();
        List<Tarifa> lista = modelot.Listar("");
        lista.stream().forEach(p -> {
            vista.getComboBoxid_tarifa().addItem(p.getIdtarifa() + "-> $" + p.getPrecio());
        });
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
                if (vista.getTextid_zona().getText().length() == 8) {
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
                  KeyListener validarn= new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 33 && e.getKeyChar() <= 47
                        ||e.getKeyChar() >= 58 && e.getKeyChar() <= 64
                        || e.getKeyChar() >= 91 && e.getKeyChar() <= 96
                        || e.getKeyChar() >= 123 && e.getKeyChar() <= 208
                        || e.getKeyChar() >= 210 && e.getKeyChar() <= 240) {
                    e.consume();
                }
                if (vista.getTxtnombrezona().getText().length() == 20) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(vista, " Alcanzó el máximo número de caracteres permitidos <20> ");
                }
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        vista.getB_listarzona1().addActionListener(l -> cargarLista(""));
        vista.getB_crearzona1().addActionListener(l -> crearzona());
        vista.getBaceptar().addActionListener(l -> grabarzonav());
        vista.getBcancelar().addActionListener(l -> cancelarzona());
        vista.getB_editarzona1().addActionListener(l -> modificarzona());
        vista.getB_eliminarzona1().addActionListener(l -> eliminarZona());
        vista.getTxtbuscar().addKeyListener(kl);
        vista.getBotonexaminar().addActionListener(l -> cargarImagen());
        vista.getTextid_zona().addKeyListener(validarid);
        vista.getTxtnombrezona().addKeyListener(validarn);
        vista.getB_imprimirzona().addActionListener(l->imprimirReporte());
    }

    private void cargarLista(String aguja) {
        vista.getTablazona().setDefaultRenderer(Object.class, new ImagenTabla());
        vista.getTablazona().setRowHeight(100);
        DefaultTableCellRenderer renderer = new DefaultTableCellHeaderRenderer();
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vista.getTablazona().getModel();
        tblModel.setNumRows(0);
        List<Zona> lista = modelo.listarZonas(aguja);
        int ncols = tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(z1 -> {
            tblModel.addRow(new Object[ncols]);
            vista.getTablazona().setValueAt(z1.getIdzona(), i.value, 1);
            vista.getTablazona().setValueAt(z1.getNombre(), i.value, 2);
            vista.getTablazona().setValueAt(z1.getTarifa(), i.value, 3);
            vista.getTablazona().setValueAt(z1.getEstado(), i.value, 4);

            Image img = z1.getFoto();
            if (img != null) {
                Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);
                renderer.setIcon(icon);
                vista.getTablazona().setValueAt(new JLabel(icon), i.value, 0);
            } else {
                vista.getTablazona().setValueAt(null, i.value, 4);
            }
            i.value++;
            ;
        });
    }

    private void limpiar() {
        vista.getTextid_zona().setText("");
        vista.getTxtnombrezona().setText("");
        vista.getComboBoxid_tarifa().setSelectedItem("SELECCIONE");
        vista.getButtonEstado().clearSelection();
        vista.getLabelfoto().setIcon(null);
    }

    private void crearzona() {
        limpiar();
        vista.getTextid_zona().setEnabled(true);
        vista.getDlgZona().setTitle("CREAR ZONA");
        vista.getDlgZona().setSize(551, 395);
        vista.getDlgZona().setLocationRelativeTo(null);
        vista.getDlgZona().setVisible(true);
        vista.getBaceptar().setVisible(true);
        vista.getBaceptar().setText("Crear");
        vista.getBcancelar().setText("Cancelar");
        vista.getDlgZona().setVisible(true);
    }

    private void cancelarzona() {
        int res = JOptionPane.showConfirmDialog(
                null,
                "¿Esta seguro que decea SALIR?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (res == JOptionPane.NO_OPTION) {
            System.out.println("Seleccionaste NO");
        } else if (res == JOptionPane.YES_OPTION) {
            vista.getDlgZona().setVisible(false);
            System.out.println("Seleccionaste SI");
        } else if (res == JOptionPane.CLOSED_OPTION) {
            System.out.println("Cerraste la ventana");
        }
        System.out.println("Valor devuelto: " + res);
    }

    private void grabarzona() {
        if (vista.getBaceptar().getText() == "Crear") {
            String estado = "";
            String idzona = vista.getTextid_zona().getText();
            String nombre = vista.getTxtnombrezona().getText();
            String[] tarifa = vista.getComboBoxid_tarifa().getSelectedItem().toString().split("-");
            if (vista.getRb_a().isSelected()) {
                estado = vista.getRb_a().getText();
            } else {
                if (vista.getRb_d().isSelected()) {
                    estado = vista.getRb_d().getText();
                }
            }
            ModeloZona mzona = new ModeloZona(idzona, nombre, tarifa[0], estado);
            ImageIcon ic = (ImageIcon) vista.getLabelfoto().getIcon();
            mzona.setFoto(ic.getImage());
            if (mzona.crear()) {
                JOptionPane.showMessageDialog(vista, "ZONA creada correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "ZONA  no creado");
            }
            vista.getDlgZona().setVisible(false);
            cargarLista("");
        } else {
            String estado = "";
            String idzona = vista.getTextid_zona().getText();
            String nombre = vista.getTxtnombrezona().getText();
            String[] tarifa = vista.getComboBoxid_tarifa().getSelectedItem().toString().split("-");
            if (vista.getRb_a().isSelected()) {
                estado = vista.getRb_a().getText();
            } else {
                if (vista.getRb_d().isSelected()) {
                    estado = vista.getRb_d().getText();
                }
            }
            ModeloZona mzona = new ModeloZona(idzona, nombre, tarifa[0], estado);
            ImageIcon ic = (ImageIcon) vista.getLabelfoto().getIcon();
            mzona.setFoto(ic.getImage());
            
            if (mzona.editar()) {
                JOptionPane.showMessageDialog(vista, "ZONA modificada correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "ZONA  no modificada");
            }
            vista.getDlgZona().setVisible(false);
            cargarLista("");
        }
    }

    private void modificarzona() {
        if (vista.getTablazona().getSelectedRow() != -1) {
            vista.getTextid_zona().setEnabled(false);
            vista.getDlgZona().setVisible(true);
            vista.getDlgZona().setLocationRelativeTo(vista);
            vista.getDlgZona().setSize(551, 395);
            vista.getDlgZona().setTitle("MODIFICAR ZONA");
            vista.getBaceptar().setText("Guardar");
            DefaultTableModel nz = (DefaultTableModel) vista.getTablazona().getModel();
            int fila = vista.getTablazona().getSelectedRow();
            vista.getTextid_zona().setText(nz.getValueAt(fila, 1).toString());
            vista.getTxtnombrezona().setText(nz.getValueAt(fila, 2).toString());
            
            ModeloTarifa modelotrf = new ModeloTarifa();
            List<Tarifa> lista = modelotrf.Listar(nz.getValueAt(fila, 3).toString());
            lista.stream().forEach(p -> {
                vista.getComboBoxid_tarifa().setSelectedItem(p.getIdtarifa() + "-> $" + p.getPrecio());
            });
            if(nz.getValueAt(fila, 4).toString().equals("LIBRE")){
                vista.getRb_a().setSelected(true);
            }else{
                vista.getRb_d().setSelected(true);
            }
          JLabel foto = (JLabel) vista.getTablazona().getValueAt(fila, 0);
            vista.getLabelfoto().updateUI();
            vista.getLabelfoto().setIcon(foto.getIcon());
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila primero");
        }
    }

    private void eliminarZona() {
        int fila = vista.getTablazona().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una Zona primero");
        } else {
            String idzona = (String) vista.getTablazona().getValueAt(fila, 1);
            ModeloZona zona = new ModeloZona(idzona, null, null, null);

            if (zona.eliminar(idzona)) {
                JOptionPane.showMessageDialog(vista, "Zona ELIMINADA correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "ERROR");
            }
            vista.getDlgZona().setVisible(false);
            cargarLista("");
        }
    }

    private void cargarImagen() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.PNG","*.PNG", "JPG","PNG","jfif");
        jfc.setFileFilter(filtro);
        int estado = jfc.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                Image icono = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(
                        vista.getLabelfoto().getWidth(),
                        vista.getLabelfoto().getHeight(),
                        Image.SCALE_DEFAULT
                );
                Icon ic = new ImageIcon(icono);
                vista.getLabelfoto().setIcon(ic);
                vista.getLabelfoto().updateUI();
            } catch (IOException ex) {
                Logger.getLogger(ControladorZona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      public void grabarzonav() {
        if (vista.getTextid_zona().getText().isEmpty() || vista.getTxtnombrezona().getText().isEmpty() ||  vista.getComboBoxid_tarifa().getSelectedItem().toString().isEmpty()
            ||vista.getComboBoxid_tarifa().getSelectedItem().toString().equals("SELECCIONE")||vista.getLabelfoto().getIcon().equals(null)) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos requeridos");
        } else {
            vista.getBaceptar().setEnabled(true);
            grabarzona();
        }
    }
        /////////////////////reportes//////////////////////
    private void imprimirReporte() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/zonas/reportezonas.jasper"));
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
