/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.Cliente;
import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.Facturas;
import SDAV.Modelo.ModeloCliente;
import SDAV.Modelo.ModeloEmpleado;
import SDAV.Modelo.ModeloFactura;
import SDAV.Modelo.ModeloTarifa;
import SDAV.Modelo.ModeloTicket;
import SDAV.Modelo.ModeloVehiculo;
import SDAV.Modelo.ModeloZona;
import SDAV.Modelo.Tarifa;
import SDAV.Modelo.Ticket;
import SDAV.Modelo.Vehiculo;
import SDAV.Modelo.Zona;
import SDAV.Vista.VistaFactura;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class ControladorFactura extends Thread{
    private VistaFactura vista;
    private ModeloFactura modelof;
    private ModeloCliente modeloc;
    private ModeloZona modeloz;
    private ModeloVehiculo modelov;
    private ModeloTicket modelot;
    private ModeloEmpleado modeloe;
    private ModeloTarifa modelota;
    private String IdTicket;
     private Thread h1;
    LocalDateTime nowTime = LocalDateTime.now();

   

    public ControladorFactura(VistaFactura vista, ModeloFactura modelof, ModeloCliente modeloc, ModeloZona modeloz, ModeloVehiculo modelov, ModeloTicket modelot, ModeloEmpleado modeloe, ModeloTarifa modelota) {
        this.vista = vista;
        this.modelof = modelof;
        this.modeloc = modeloc;
        this.modeloz = modeloz;
        this.modelov = modelov;
        this.modelot = modelot;
        this.modeloe = modeloe;
        this.modelota = modelota;
        vista.getTabFactura().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTabFactura().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTabFactura().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        vista.getTb_Final().getTableHeader().setFont(new Font("Tw Cen MT", Font.CENTER_BASELINE, 18));
        vista.getTb_Final().getTableHeader().setBackground(new Color(1,187,246));
        vista.getTb_Final().getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
        h1= new Thread(this);
        h1.start();
        cargarLista();
    }
    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            LocalDateTime nowTime = LocalDateTime.now();
            vista.getTxtHora1().setText("" + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)));
            vista.getTxtDIA1().setText("" + nowTime.format(DateTimeFormatter.ISO_DATE));
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }
    public void InciarControl() {
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
                cargarLista(vista.getTxtBuscarE().getText());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        vista.getBtnListarF().addActionListener(l->cargarLista(""));
        vista.getBtTerminar().addActionListener(l->Finalizar());
        vista.getBtnEliminarF().addActionListener(l->Eliminarfactura());
         vista.getTxtBuscarE().addKeyListener(k1);
         vista.getBtnListarF1().addActionListener(l->imprimirFactura());
          vista.getBtnImprimirF().addActionListener(l->imprimirReporte());
      
    }
    private void cargarLista(String aguja){
            DefaultTableModel tcModelo=(DefaultTableModel)vista.getTabFactura().getModel();
            tcModelo.setNumRows(0);
            List<Facturas> lista = modelof.Listar(aguja);
            int ncols=tcModelo.getColumnCount();
            Holder<Integer> i = new Holder<>(0);
           lista.stream().forEach(p->{
             tcModelo.addRow(new Object[ncols]);
               vista.getTabFactura().setValueAt(p.getIdFactura(), i.value , 0);
               vista.getTabFactura().setValueAt(p.getCedulaCliente(), i.value , 1);
               vista.getTabFactura().setValueAt(p.getIdEstacionamiento(), i.value , 2);
               vista.getTabFactura().setValueAt(p.getFechaemicion(), i.value , 3);
               vista.getTabFactura().setValueAt(p.getSubTotal(), i.value , 4);
               vista.getTabFactura().setValueAt(p.getHoraingreso(), i.value , 5);
               vista.getTabFactura().setValueAt(p.getHorasalida(), i.value ,6);
               vista.getTabFactura().setValueAt(p.getMontototal(), i.value , 7);
               
               i.value++;

            });
        }
    
    private void cargarLista() {
        vista.getTb_Final().setDefaultRenderer(Object.class, new ImagenTabla());
        vista.getTb_Final().setRowHeight(100);
        DefaultTableCellRenderer renderer= new DefaultTableCellRenderer();
        
        ModeloTicket modeloti = new ModeloTicket();
        ModeloVehiculo modelov = new ModeloVehiculo();
        ModeloCliente modeloc = new ModeloCliente();
        
        DefaultTableModel tcModelo = (DefaultTableModel) vista.getTb_Final().getModel();
        tcModelo.setNumRows(0);
         
        List<Ticket> listat = modeloti.Listar("");
        int ncols = tcModelo.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        //Tikect
         
        listat.stream().forEach(t -> {
 
            if(t.getHoraingreso().equals(t.getHorasalida())){  
            tcModelo.addRow(new Object[ncols]);
            vista.getTb_Final().setValueAt(t.getCodigoticket(), i.value, 0);
            vista.getTb_Final().setValueAt(t.getHoraingreso(), i.value, 5);
            //Vehiculo
            List<Vehiculo> listav = modelov.listar(t.getPlaca());
            listav.stream().forEach(p->{
                vista.getTb_Final().setValueAt(p.getPlaca(), i.value, 2);
                
                Image img = p.getFoto();
                 if(img!=null){
                    Image newimg = img.getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(newimg);
                    renderer.setIcon(icon);
                    vista.getTb_Final().setValueAt(new JLabel(icon), i.value, 1);
                   }else{
                       vista.getTb_Final().setValueAt(null, i.value, 5);
                   } 
                 
                     List<Cliente> listac = modeloc.Listar(p.getCedula_cli());
                      listac.stream().forEach(c->{
                          vista.getTb_Final().setValueAt(c.getCedula(), i.value, 3);
                          vista.getTb_Final().setValueAt(c.getApellido()+" "+c.getNombre(), i.value, 4);

                        });
                });
                
            i.value++;
            }
        });
        
    }
    private void Finalizar(){
        DefaultTableModel tbCargo = (DefaultTableModel) vista.getTb_Final().getModel();
        int fila=vista.getTb_Final().getSelectedRow();
        if(fila!=-1){
         ModeloTicket modeloT = new ModeloTicket();
            List<Ticket> lista =modeloT.Listar(tbCargo.getValueAt(fila, 0).toString());
            lista.stream().forEach(p -> {
                Time horafinal = Time.valueOf(nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                modeloT.setCodigoticket(p.getCodigoticket());
                modeloT.setFechaingreso(p.getFechaingreso());
                modeloT.setHoraingreso(p.getHoraingreso());
                modeloT.setId_zona(p.getId_zona());
                modeloT.setPlaca(p.getPlaca());
                modeloT.setHorasalida(horafinal);
                 ModeloZona zona = new ModeloZona();
                List<Zona> listar =modeloz.listarZonas(p.getId_zona());
                listar.stream().forEach(l -> {
                zona.setIdzona(l.getIdzona());
                zona.setNombre(l.getNombre());
                zona.setNombre(l.getNombre());
                zona.setTarifa(l.getTarifa());  
                zona.setEstado("LIBRE");
                 zona.setFoto(l.getFoto());
            });
            zona.editar();
            });
            modeloT.editar();   
            
        }else{
            JOptionPane.showMessageDialog(vista, "DE PRIMERO CLICK ENCIMA EN ALGUN CARGO Y LUEGO EN EDITAR","AVISO",2);
        }
        Guardarfactura();
    }
    
    

    private void Guardarfactura(){
        Random num = new Random();
        DefaultTableModel tcModelo = (DefaultTableModel) vista.getTb_Final().getModel(); 
        int fila=vista.getTb_Final().getSelectedRow();
        System.err.println(fila);
        if(fila!=-1){
        ModeloFactura factura = new ModeloFactura();
        List<Facturas> lista = modelof.Listar("");
        String idFactura = String.valueOf("FK-"+lista.stream().count()+1+""+num.nextInt(9));
        String iddDetlla = String.valueOf("FKD-"+lista.stream().count()+1+""+num.nextInt(9));
        factura.setIdFactura(idFactura);
        factura.setIdDetalle(iddDetlla);
        
        List<Ticket> listat = modelot.Listar(tcModelo.getValueAt(fila, 0).toString());
        listat.stream().forEach(t -> {
            factura.setIdEstacionamiento(t.getCodigoticket());
            factura.setFechaemicion(Date.valueOf(nowTime.toLocalDate()));
            factura.setHoraingreso(t.getHoraingreso());
            factura.setHorasalida(t.getHorasalida());  
            factura.setCedulaCliente(tcModelo.getValueAt(fila, 3).toString());
            //RestarHora
             LocalDateTime horaIngreso = LocalDateTime.parse(t.getFechaingreso()+" "+t.getHoraingreso(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
             LocalDateTime salida = LocalDateTime.now() ;
            long diff = ChronoUnit.MILLIS.between(horaIngreso, salida);
            long diffMinutos =  Math.abs (diff / (60 * 1000));
            long restominutos = diffMinutos%60;
            long diffHoras =   (diff / (60 * 60 * 1000));
            factura.setTiempo(diffHoras+":"+restominutos);
           //FinRestarHora
            List<Zona> listaz = modeloz.listarZonas(t.getId_zona());
            listaz.stream().forEach(z -> {
                 List<Tarifa> listata = modelota.Listar(z.getTarifa());
                 listata.stream().forEach(ta -> {
                      factura.setSubTotal(ta.getPrecio());
                     if(diffHoras==0){
                        factura.setMontototal(ta.getPrecio());
                     }else{
                        factura.setMontototal(ta.getPrecio()*diffHoras); 
                     }

                 });
             });
        });
        
        if(factura.CrearFactura()&&factura.CrearDetalleFactura()){
        JOptionPane.showMessageDialog(vista, "Factura Creada con exito","AVISO",-1);  
         }else{
        JOptionPane.showMessageDialog(vista, "ERROR");   
        }         
        vista.setVisible(true);
        cargarLista();
        cargarLista("");
         int res = JOptionPane.showConfirmDialog(null,"IMPRIMIR FACTURA","MENSAJE",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
            if (res==JOptionPane.YES_OPTION) {
                imprimirReporteFactura(idFactura); 
            }
        }
        
    }
     
     private void Eliminarfactura(){
         
        DefaultTableModel tcModelo = (DefaultTableModel) vista.getTabFactura().getModel(); 
        if (vista.getTabFactura().getSelectedRow() != -1) {
            int fila=vista.getTabFactura().getSelectedRow();
         ModeloFactura factura = new ModeloFactura();
         factura.setIdFactura(tcModelo.getValueAt(fila, 0).toString());
         if(factura.EliminarDetalleFactura()){

         }else{
        JOptionPane.showMessageDialog(vista, "ERROR");   
        }  
         if(factura.EliminarFactura()){
        JOptionPane.showMessageDialog(vista, "Factura eliminada con exito","AVISO",-1);  
         }else{
        JOptionPane.showMessageDialog(vista, "ERROR");   
        }  
         cargarLista("");
         }else {
                JOptionPane.showMessageDialog(vista, "PRIMERO HAGA CLIC EN UNA FACTURA QUE DESEA ELIMINAR\n"
                        + "Y LUEGO EN EL BOTON ELIMINAR");
            }
        
         
     }
     private void imprimirFactura(){
        DefaultTableModel tcModelo = (DefaultTableModel) vista.getTabFactura().getModel(); 
        if (vista.getTabFactura().getSelectedRow() != -1) {
             int fila=vista.getTabFactura().getSelectedRow();
         ModeloFactura factura = new ModeloFactura();
        imprimirReporteFactura(tcModelo.getValueAt(fila, 0).toString());
        }else {
                JOptionPane.showMessageDialog(vista, "PRIMERO HAGA CLIC EN EN UNA FACTURA QUE DESEA ELIMINAR\n"
                        + "Y LUEGO EN EL BOTON ELIMINAR");
            }
       
         
    }
     
      private void imprimirReporteFactura(String Aguja){
        HashMap<String,Object> parametros=new HashMap(); 
        parametros.put("numFactura", Aguja);
        parametros.put("RUTOSUBREPORTE", "SDAV/Vista/reportes/factura"); 
       try {
             ConexionBD con = new ConexionBD();
            JasperReport jr= (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/factura/FacturasSDAV.jasper"));
            JasperPrint jp=JasperFillManager.fillReport(jr,parametros,con.getCon());
            JasperViewer jv= new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void imprimirReporte() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/factura/ReporteSDAV.jasper"));
            String aguja = vista.getTxtBuscarE().getText();
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("numFactura", "%" + aguja + "%");
            parametros.put("RUTOSUBREPORTE", "SDAV/Vista/reportes/factura"); 
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
