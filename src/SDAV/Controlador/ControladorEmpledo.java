package SDAV.Controlador;

import SDAV.Modelo.Cargo;
import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.Empleado;
import SDAV.Modelo.ModeloCargo;
import SDAV.Vista.VistaEmpleado;
import SDAV.Modelo.ModeloEmpleado;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorEmpledo {

    private VistaEmpleado vista;
    private ModeloEmpleado modelo;

    public ControladorEmpledo(VistaEmpleado vista, ModeloEmpleado modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.modelo = modelo;
        vista.getTabEmpleados().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTabEmpleados().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTabEmpleados().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.setVisible(true);
        ModeloCargo modelocargo = new ModeloCargo();
        List<Cargo> lista = modelocargo.Listar("");
        lista.stream().forEach(p -> {
            vista.getJbCargo().addItem(p.getId_cargo() + "-" + p.getNombres());
        });
    }

    public void InciarContro() {
        KeyListener k1 = new KeyListener() {
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
                cargarlistaE(vista.getTxtBuscarE().getText());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        KeyListener validarcedula = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= 32 && e.getKeyChar() <= 47
                        || e.getKeyChar() >= 58 && e.getKeyChar() <= 127) {
                    e.consume();
                }
                if (vista.getCeduE().getText().length() == 10) {
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
                if (vista.getNomE().getText().length() == 25) {
                    Toolkit.getDefaultToolkit().beep();
                    
                } else {
                    if (vista.getApellE().getText().length() == 25) {
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
                if(validar_correo(vista.getEmailE().getText())==true){
                    vista.getEtiquetacorreo().setForeground(Color.GREEN);
                    vista.getEtiquetacorreo().setText("/");         
                }else{   
                    vista.getEtiquetacorreo().setText("*");
                      vista.getEtiquetacorreo().setForeground(Color.RED);
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
                if (vista.getSueldE().getText().length() == 8) {
                    Toolkit.getDefaultToolkit().beep();
                   
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
        //////////////////////////////////////////////////////////
        vista.getBtnListarE().addActionListener(l -> cargarlistaE(""));
        vista.getBtnCrearE().addActionListener(l -> crearEmpleado());
        vista.getBtnaceptE().addActionListener(l -> grabarempleadovalidado());
        vista.getBtnEliminarE().addActionListener(l -> EliminarEmpleado());
        vista.getBtnEditarE().addActionListener(l -> EditarEmpleado());
        vista.getTxtBuscarE().addKeyListener(k1);
        //////////////////////////////////////////////////////////
        vista.getCeduE().addKeyListener(validarcedula);
        vista.getNomE().addKeyListener(validarnomyape);
        vista.getApellE().addKeyListener(validarnomyape);
        vista.getEmailE().addKeyListener(validarcorreo);
        vista.getSueldE().addKeyListener(validarsueldo);
        vista.getTxtpmax().addKeyListener(validarmax);
        vista.getTxtmin().addKeyListener(validarmin);
        vista.getBtnImprimirE().addActionListener(l->imprimirReporte());
        vista.getBtncancE().addActionListener(l->Cerrar());

    }

    private void cargarlistaE(String aguja) {
        DefaultTableModel tcModelo = (DefaultTableModel) vista.getTabEmpleados().getModel();
        tcModelo.setNumRows(0);
        vista.getTabEmpleados().setRowHeight(70);
        List<Empleado> lista = modelo.Listar(aguja);
        int ncols = tcModelo.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        lista.stream().forEach(p -> {

            tcModelo.addRow(new Object[ncols]);
            vista.getTabEmpleados().setValueAt(p.getCedula(), i.value, 0);
            vista.getTabEmpleados().setValueAt(p.getCargo(), i.value, 1);
            vista.getTabEmpleados().setValueAt(p.getNombre(), i.value, 2);
            vista.getTabEmpleados().setValueAt(p.getApellido(), i.value, 3);
            vista.getTabEmpleados().setValueAt(p.getEmal(), i.value, 4);
            vista.getTabEmpleados().setValueAt(p.getSueldo(), i.value, 5);
            i.value++;

        });
    }

    private void crearEmpleado() {
        vista.getCeduE().setEnabled(true);
        vista.getCrearEmpleado().setTitle("INGRESAR EMPLEADO");
        vista.getCrearEmpleado().setSize(440, 470); 
        vista.getCrearEmpleado().setLocationRelativeTo(vista.getCeduE());
        vista.getCeduE().setText("");
        vista.getJbCargo().setSelectedItem("SELECCIONE");
        vista.getNomE().setText("");
        vista.getApellE().setText("");
        vista.getEmailE().setText("");
        vista.getSueldE().setText("");
        vista.getBtnaceptE().setText("Crear");
        vista.getBtncancE().setText("Cancelar");
        vista.getCrearEmpleado().setVisible(true);
      
    }

    private void grabarEmpleado() {
        if (vista.getBtnaceptE().getText() == "Crear") {
            String Cedula = vista.getCeduE().getText();
            String[] Cargo = vista.getJbCargo().getSelectedItem().toString().split("-");
            String Nombre = vista.getNomE().getText();
            String Apellido = vista.getApellE().getText();
            String Email = vista.getEmailE().getText();
            String Sueldo = vista.getSueldE().getText();

            ModeloEmpleado empleado = new ModeloEmpleado(Cedula, Cargo[0], Nombre, Apellido, Email, Sueldo);

            if (empleado.CrearEmpleado()) {
                JOptionPane.showMessageDialog(vista, "Empleado Creado con exito", "AVISO", -1);
            } else {
                JOptionPane.showMessageDialog(vista, "No se creo","ERROR", 0);
            }
            vista.getCrearEmpleado().setVisible(false);
            cargarlistaE("");
        } else {
            String Cedula = vista.getCeduE().getText();
            String[] Cargo = vista.getJbCargo().getSelectedItem().toString().split("-");
            String Nombre = vista.getNomE().getText();
            String Apellido = vista.getApellE().getText();
            String Email = vista.getEmailE().getText();
            String Sueldo = vista.getSueldE().getText();
            vista.getCeduE().setText("");
            vista.getJbCargo().setSelectedItem("SELECCIONE");
            vista.getNomE().setText("");
            vista.getApellE().setText("");
            vista.getEmailE().setText("");
            vista.getSueldE().setText("");

            ModeloEmpleado empleado = new ModeloEmpleado(Cedula, Cargo[0], Nombre, Apellido, Email, Sueldo);
            if (empleado.editarE()) {
                JOptionPane.showMessageDialog(vista, "Empleado modificado correctamente","AVISO", -1);
            } else {
                JOptionPane.showMessageDialog(vista, "Empleado no modificado","ERROR", 0);
            }
            vista.getCrearEmpleado().setVisible(false);
            cargarlistaE("");
        }
    }
    
    private void EliminarEmpleado() {
        int res = JOptionPane.showConfirmDialog(
                null,
                "¿Esta seguro",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (vista.getTabEmpleados().getSelectedRow() != -1) {
            if (res == JOptionPane.NO_OPTION) {
                System.out.println("Seleccionaste NO");
            } else if (res == JOptionPane.YES_OPTION) {

                DefaultTableModel tbEmp = (DefaultTableModel) vista.getTabEmpleados().getModel();
                int fila = vista.getTabEmpleados().getSelectedRow();
                String string = tbEmp.getValueAt(fila, 0).toString();
                ModeloEmpleado empleado = new ModeloEmpleado();
                empleado.setCedula(string);
                if (empleado.eliminarE()) {
                    JOptionPane.showMessageDialog(vista, "Empleado Eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(vista, "Empleado no Eliminado");
                }
                cargarlistaE("");
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

    private void EditarEmpleado() {
        if (vista.getTabEmpleados().getSelectedRow() != -1) {
            vista.getCeduE().setEnabled(false);
            vista.getCrearEmpleado().setVisible(true);
            vista.getCrearEmpleado().setLocationRelativeTo(vista.getApellE());
            vista.getCrearEmpleado().setSize(440, 470);
            vista.getCrearEmpleado().setTitle("MODIFICAR EMPLEADO");
            vista.getBtnaceptE().setText("Guardar");
            DefaultTableModel tbemp = (DefaultTableModel) vista.getTabEmpleados().getModel();
            int fila = vista.getTabEmpleados().getSelectedRow();
            vista.getCeduE().setText(tbemp.getValueAt(fila, 0).toString());

            ModeloCargo modelocargo = new ModeloCargo();
            List<Cargo> lista = modelocargo.Listar(tbemp.getValueAt(fila, 1).toString());
            lista.stream().forEach(p -> {
                vista.getJbCargo().setSelectedItem(p.getId_cargo() + "-" + p.getNombres());
            });

            vista.getNomE().setText(tbemp.getValueAt(fila, 2).toString());
            vista.getApellE().setText(tbemp.getValueAt(fila, 3).toString());
            vista.getEmailE().setText(tbemp.getValueAt(fila, 4).toString());
            vista.getSueldE().setText(tbemp.getValueAt(fila, 5).toString());

        } else {
            JOptionPane.showMessageDialog(vista, "PRIMERO HAGA CLIC EN LA PERSONA QUE DESEA EDITAR\n"
                    + "Y LUEGO EN EL BOTON EDITAR");
        }
    }
    private void Cerrar(){
        vista.getCrearEmpleado().setVisible(false);
    }

    //Reportes
     private void imprimirReporte() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/empleados/reportepemplados.jasper"));
            String aguja = vista.getTxtBuscarE().getText();
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
            Logger.getLogger(ControladorEmpledo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
    //VALIDACIONES 
    public void grabarempleadovalidado() {
        if (vista.getCeduE().getText().isEmpty() || vista.getNomE().getText().isEmpty() || vista.getApellE().getText().isEmpty()
                || vista.getSueldE().getText().isEmpty() || vista.getEmailE().getText().isEmpty() 
                || validar_correo(vista.getEmailE().getText())==false 
                || vista.getJbCargo().getSelectedItem().toString().equals("SELECCIONE")
                || ValidacionesCedula()==false) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos requeridos o incompletos", "ERROR", 0);
        } else {
            vista.getBtnaceptE().setEnabled(true);
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
        for (int i = 0; i < vista.getCeduE().getText().length()-1; i++) {
            c=Integer.parseInt(vista.getCeduE().getText().charAt(i)+"");
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
        ultimos=Integer.parseInt(vista.getCeduE().getText().charAt(9)+"");
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
