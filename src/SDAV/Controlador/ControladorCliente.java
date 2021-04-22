package SDAV.Controlador;

import SDAV.Modelo.Cliente;
import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloCliente;
import SDAV.Vista.VistaCliente;
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


public class ControladorCliente {

    private VistaCliente vista;
    private ModeloCliente modelo;

    public ControladorCliente(VistaCliente vista, ModeloCliente modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.getTabClientes().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTabClientes().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTabClientes().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.setVisible(true);

    }

//   
    public void InciarContro() {
        KeyListener k1 = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cargarlista(vista.getTxtBuscar().getText());
            }
        };
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

        /////////////////////////////////////////////////////////////////
        vista.getBtnListar().addActionListener(l -> cargarlista(""));
        vista.getBtnCrear().addActionListener(l -> crearCliente());
        vista.getBtnacept().addActionListener(l -> grabarclientevalidado());
        vista.getBtnEliminar().addActionListener(l -> EliminarCliente());
        vista.getBtnEditar().addActionListener(l -> EditarCliente());
        vista.getBtnImprimir().addActionListener(l->imprimirReporte());
        //////////////////////////////////////////////////////////////
        vista.getTxtBuscar().addKeyListener(k1);
        vista.getId().addKeyListener(validarcedula);
        vista.getNom().addKeyListener(validarnomyape);
        vista.getApell().addKeyListener(validarnomyape);
        vista.getTel().addKeyListener(validartelefono);
        vista.getDireccion().addKeyListener(validardireccion);
        vista.getBtncanc().addActionListener(l->Cerrar());
    }

    private void cargarlista(String aguja) {
        DefaultTableModel tcModelo = (DefaultTableModel) vista.getTabClientes().getModel();
        tcModelo.setNumRows(0);
        vista.getTabClientes().setRowHeight(70);
        List<Cliente> lista = modelo.Listar(aguja);
        int ncols = tcModelo.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        lista.stream().forEach(p -> {

            tcModelo.addRow(new Object[ncols]);
            vista.getTabClientes().setValueAt(p.getCedula(), i.value, 0);
            vista.getTabClientes().setValueAt(p.getNombre(), i.value, 1);
            vista.getTabClientes().setValueAt(p.getApellido(), i.value, 2);
            vista.getTabClientes().setValueAt(p.getNumtelefono(), i.value, 3);
            vista.getTabClientes().setValueAt(p.getDireccion(), i.value, 4);
            i.value++;

        });
    }

    private void limpiarcampos() {
        vista.getId().setText("");
        vista.getNom().setText("");
        vista.getApell().setText("");
        vista.getTel().setText("");
        vista.getDireccion().setText("");
    }

    private void crearCliente() {
        limpiarcampos();
        vista.getId().setEnabled(true);
        vista.getCrearCliente().setTitle("INGRESAR CLIENTE");
        vista.getCrearCliente().setSize(342, 375);
        vista.getCrearCliente().setLocationRelativeTo(vista.getTel());
        vista.getCrearCliente().setVisible(true);
        vista.getBtnacept().setText("Crear");
        vista.getBtncanc().setText("Cancelar");

    }

    public void grabarclientevalidado() {
        if (vista.getId().getText().isEmpty() || vista.getNom().getText().isEmpty() || vista.getApell().getText().isEmpty()
                || vista.getTel().getText().isEmpty() || vista.getDireccion().getText().isEmpty()
                ||ValidacionesCedula()==false) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos requeridos","ERROR", 0);
        } else {
            vista.getBtnacept().setEnabled(true);
            grabarCliente();
        }
    }

    private void grabarCliente() {
        if (vista.getBtnacept().getText() == "Crear") {
            String Cedula = vista.getId().getText();
            String Nombre = vista.getNom().getText();
            String Apellido = vista.getApell().getText();
            String Telefono = vista.getTel().getText();
            String Direccion = vista.getDireccion().getText();
            ModeloCliente Cliente = new ModeloCliente(Telefono, Direccion, Cedula, Nombre, Apellido);
            if (Cliente.CrearCliente()) {
                JOptionPane.showMessageDialog(vista, "Cliente Creada con exito", "AVISO", -1);
            } else {
                JOptionPane.showMessageDialog(vista, "ERROR");
            }
            vista.getCrearCliente().setVisible(false);
            cargarlista("");
        } else {
            String Cedula = vista.getId().getText();
            String Nombre = vista.getNom().getText();
            String Apellido = vista.getApell().getText();
            String Telefono = vista.getTel().getText();
            String Direccion = vista.getDireccion().getText();
            vista.getId().setText("");
            vista.getNom().setText("");
            vista.getApell().setText("");
            vista.getTel().setText("");
            vista.getDireccion().setText("");
            ModeloCliente Cliente = new ModeloCliente(Telefono, Direccion, Cedula, Nombre, Apellido);
            if (Cliente.editar()) {
                JOptionPane.showMessageDialog(vista, "Persona modificada correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Persona no modificada");
            }
            vista.getCrearCliente().setVisible(false);
            cargarlista("");
        }

    }

    private void EliminarCliente() {
        int res = JOptionPane.showConfirmDialog(
                null,
                "¿Esta seguro?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (res == JOptionPane.NO_OPTION) {
            System.out.println("Seleccionaste NO");

        } else if (res == JOptionPane.YES_OPTION) {

            if (vista.getTabClientes().getSelectedRow() != -1) {
                DefaultTableModel tbCli = (DefaultTableModel) vista.getTabClientes().getModel();
                int fila = vista.getTabClientes().getSelectedRow();
                String string = tbCli.getValueAt(fila, 0).toString();
                ModeloCliente cliente = new ModeloCliente();
                cliente.setCedula(string);
                if (cliente.eliminar()) {
                    JOptionPane.showMessageDialog(vista, "Persona Eliminada correctamente");
                } else {
                    JOptionPane.showMessageDialog(vista, "Persona no Eliminada");
                }
                cargarlista("");
            } else {
                JOptionPane.showMessageDialog(vista, "PRIMERO HAGA CLIC EN LA PERSONA QUE DESEA ELIMINAR\n"
                        + "Y LUEGO EN EL BOTON ELIMINAR");
            }
            System.out.println("Seleccionaste SI");
        } else if (res == JOptionPane.CLOSED_OPTION) {
            System.out.println("Cerraste la ventana");
        }
        System.out.println("Valor devuelto: " + res);
    }

    private void EditarCliente() {

        if (vista.getTabClientes().getSelectedRow() != -1) {
            vista.getId().setEnabled(false);
            vista.getCrearCliente().setVisible(true);
            vista.getCrearCliente().setLocationRelativeTo(vista.getApell());
            vista.getCrearCliente().setSize(342, 375);
            vista.getCrearCliente().setTitle("MODIFICAR CLIENTE");
            vista.getBtnacept().setText("Guardar");
            DefaultTableModel tbcli = (DefaultTableModel) vista.getTabClientes().getModel();
            int fila = vista.getTabClientes().getSelectedRow();
            vista.getId().setText(tbcli.getValueAt(fila, 0).toString());
            vista.getNom().setText(tbcli.getValueAt(fila, 1).toString());
            vista.getApell().setText(tbcli.getValueAt(fila, 2).toString());
            vista.getTel().setText(tbcli.getValueAt(fila, 3).toString());
            vista.getDireccion().setText(tbcli.getValueAt(fila, 4).toString());
        } else {
            JOptionPane.showMessageDialog(vista, "PRIMERO HAGA CLIC EN LA PERSONA QUE DESEA EDITAR\n"
                    + "Y LUEGO EN EL BOTON EDITAR");
        }
    }
     private void imprimirReporte() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/clientes/reporteclientes.jasper"));
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
     private void Cerrar(){
        vista.getCrearCliente().setVisible(false);
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