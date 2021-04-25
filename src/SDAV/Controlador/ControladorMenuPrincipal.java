/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.Cliente;
import SDAV.Modelo.ConexionBD;
import SDAV.Modelo.ModeloCargo;
import SDAV.Modelo.ModeloCliente;
import SDAV.Modelo.ModeloEmpleado;
import SDAV.Modelo.ModeloFactura;
import SDAV.Modelo.ModeloTarifa;
import SDAV.Modelo.ModeloTicket;
import SDAV.Modelo.ModeloVehiculo;
import SDAV.Modelo.ModeloZona;
import SDAV.Modelo.Ticket;
import SDAV.Modelo.Vehiculo;
import SDAV.Modelo.Zona;
import SDAV.Vista.VistaCargo;
import SDAV.Vista.VistaCliente;
import SDAV.Vista.VistaEmpleado;
import SDAV.Vista.VistaFactura;
import SDAV.Vista.VistaMenuPrincipal;
import SDAV.Vista.VistaNuevoTicket;
import SDAV.Vista.VistaTarifa;
import SDAV.Vista.VistaTicket;
import SDAV.Vista.VistaVehiculo;
import SDAV.Vista.VistaZonaEstacionamiento;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class ControladorMenuPrincipal extends Thread{
    static VistaMenuPrincipal vis;   
    private Thread h1;
   LocalDateTime nowTime = LocalDateTime.now();
      private String hora =nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    public ControladorMenuPrincipal(VistaMenuPrincipal vis) {
        this.vis = vis;
        vis.setVisible(true);
        vis.setLocationRelativeTo(null);
        
        h1= new Thread(this);
        h1.start();
    }
     @Override
    public void run() {
        Thread ct = Thread.currentThread();
       while (ct==h1) {
             LocalDateTime nowTime = LocalDateTime.now();
        vis.getTxtHora().setText("" + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)));
        vis.getTxtDIA().setText("" + nowTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }  
       
    }
    public void IniciarControl(){
        vis.getBtDispliege().addActionListener(l->Despliegue());
        vis.getBtToolcrudEmpleado().addActionListener(l->VistaEmplado());
        vis.getBtToolcrudClientes().addActionListener(l->VistaCliente());
        vis.getBtToolcrudVehiculo().addActionListener(l->VistaVehiculo());
        vis.getJmEscritorio().addActionListener(l->mostrarEscritorio());
        vis.getJmPrincipal().addActionListener(l->mostrarPrincipal());
        vis.getBtEmpezar().addActionListener(l->VistaNuevoTicket());
        vis.getBtToolcrudCargo().addActionListener(l->VistaCargo());
        vis.getBtToolcrudTarifa().addActionListener(l->VistaTarifa());
        vis.getBt_zonas().addActionListener(l->VistaZonaEstacionamiento());
        vis.getJmMantenimetoAuto().addActionListener(l->VistaVehiculo());
        vis.getJmMantenimetoCargo().addActionListener(l->VistaCargo());
        vis.getJmMantenimetoCliente().addActionListener(l->VistaCliente());
        vis.getJmMantenimetoEmple().addActionListener(l->VistaEmplado());
        vis.getJmMantenimetoFactura().addActionListener(l->VistaFactura());
        vis.getJmMantenimetoTarifa().addActionListener(l->VistaTarifa());
        vis.getJmMantenimetoTickes().addActionListener(l->VistaVehiculo());
        vis.getJmMantenimetoZonas().addActionListener(l->VistaZonaEstacionamiento());
        vis.getBtSalir().addActionListener(l->Cerrar());
        vis.getB_ticket().addActionListener(l->VistaTicket());
        vis.getBtnCrudFactura().addActionListener(l->VistaFactura());
        vis.getJmServicio().addActionListener(l->VistaFacturaUno());
        vis.getJmTarifa().addActionListener(l->imprimirGRAFICOtarifa());
        vis.getJmZonas().addActionListener(l->imprimirGRAFICOzonas());
        vis.getJmReportes().addActionListener(l->imprimirGRAFICOtickets());
        vis.getJmEmpleado().addActionListener(l->imprimirGRAFICOEmplado());
        vis.getJmCargos().addActionListener(l->imprimirGRAFICOcargos());
        vis.getJcAuto().addActionListener(l->imprimirGRAFICOautos());
        vis.getJbUso().addActionListener(l->Tiempouso());
        vis.getJmRCargos().addActionListener(l->imprimirReporteC());
        vis.getJmREmple().addActionListener(l->imprimirReporteemple());
        vis.getJmRFac().addActionListener(l->imprimirReportefact());
        vis.getJmRTari().addActionListener(l->imprimirReportetarifa());
        vis.getJmRTikects().addActionListener(l->imprimirReportetick());
        vis.getJmRZonas().addActionListener(l->imprimirReportezona());
        vis.getJmRcLI().addActionListener(l->imprimirReportecli());
        vis.getJmRImpri().addActionListener(l->imprimirReportevehi());
    }
    
     private void VistaCliente(){
        VistaCliente vistac = new VistaCliente();
         ModeloCliente modeloc = new ModeloCliente();
        ControladorCliente con = new ControladorCliente(vistac,modeloc);
        con.InciarContro();
        vis.getDekEscritorio().add(vistac);
        vis.getJpPrincipal().setVisible(false);
        vis.getDekEscritorio().setVisible(true);
    }
    private void VistaEmplado(){
        VistaEmpleado vistae = new VistaEmpleado();
        ModeloEmpleado modeloe = new ModeloEmpleado();
        ControladorEmpledo con = new ControladorEmpledo(vistae,modeloe);
        con.InciarContro();
        vis.getDekEscritorio().add(vistae);
        
        vis.getJpPrincipal().setVisible(false);
        vis.getDekEscritorio().setVisible(true);
    }
     private void VistaVehiculo(){
        VistaVehiculo vistav = new VistaVehiculo();
         ModeloVehiculo modelov = new ModeloVehiculo();
        ControladorVehiculo con = new ControladorVehiculo(vistav,modelov);
        con.IniciarControl();
        vis.getDekEscritorio().add(vistav);
        vis.getJpPrincipal().setVisible(false);
        vis.getDekEscritorio().setVisible(true);
    }
     
     private void VistaNuevoTicket(){
       VistaNuevoTicket vistan = new VistaNuevoTicket();
       ModeloCliente modelom = new ModeloCliente();
       ModeloVehiculo modelov = new ModeloVehiculo();
       ModeloZona modeloz = new ModeloZona();
       ModeloTicket modeloti = new ModeloTicket();
       ControladorNuevoTicket con = new ControladorNuevoTicket(vistan, modelom, modelov, modeloti, modeloz);
       con.InciarControl();
       vis.getDekEscritorio().add(vistan);
       vis.getJpPrincipal().setVisible(false);
       vis.getDekEscritorio().setVisible(true);
        
    }
     
     private void VistaCargo(){
        VistaCargo vistac= new VistaCargo();
        ModeloCargo modeloc = new ModeloCargo();
        ControladorCargo con = new ControladorCargo(vistac, modeloc);
        con.iniciarControl();
       vis.getDekEscritorio().add(vistac);
       vis.getJpPrincipal().setVisible(false);
       vis.getDekEscritorio().setVisible(true);
    }
     private void VistaTarifa(){
        VistaTarifa vistat= new VistaTarifa ();
        ModeloTarifa modelot = new ModeloTarifa();
        ControladorTarifa con = new ControladorTarifa(vistat, modelot);
        con.iniciarContro();
       vis.getDekEscritorio().add(vistat);
       vis.getJpPrincipal().setVisible(false);
       vis.getDekEscritorio().setVisible(true);      
    }
    private void VistaZonaEstacionamiento() {
        VistaZonaEstacionamiento vistaz = new VistaZonaEstacionamiento();
        ModeloZona modeloz = new ModeloZona();
        ControladorZona con = new ControladorZona(modeloz, vistaz);
        con.iniciaControl();
        vis.getDekEscritorio().add(vistaz);
        vis.getJpPrincipal().setVisible(false);
        vis.getDekEscritorio().setVisible(true);
    }
     private void VistaTicket() {
        VistaTicket vistat = new VistaTicket();
        ModeloTicket modelot = new ModeloTicket();
        ControladorTicket con = new ControladorTicket(modelot,vistat);
        con.iniciaControl();
        vis.getDekEscritorio().add(vistat);
        vis.getJpPrincipal().setVisible(false);
        vis.getDekEscritorio().setVisible(true);
    }
      private void VistaFactura() {
        VistaFactura vistaf = new VistaFactura();
        ModeloTicket modelot = new ModeloTicket();
        ModeloZona modeloz = new ModeloZona();
        ModeloCliente modeloc = new ModeloCliente();
        ModeloTarifa modelota= new ModeloTarifa();
        ModeloFactura modelof = new ModeloFactura();
        ModeloVehiculo modelov = new ModeloVehiculo();
        ModeloEmpleado modeloe = new ModeloEmpleado();
        ControladorFactura con = new ControladorFactura(vistaf, modelof, modeloc, modeloz, modelov, modelot, modeloe, modelota);
        con.InciarControl();
        vistaf.setVisible(true);
        vistaf.getServicio().setVisible(false);
        vis.getDekEscritorio().add(vistaf.getServicio());
        vis.getDekEscritorio().add(vistaf);
        vis.getJpPrincipal().setVisible(false);
        vis.getDekEscritorio().setVisible(true);
    }
     private void VistaFacturaUno() {
        VistaFactura vistaf = new VistaFactura();
        ModeloTicket modelot = new ModeloTicket();
        ModeloZona modeloz = new ModeloZona();
        ModeloCliente modeloc = new ModeloCliente();
        ModeloTarifa modelota= new ModeloTarifa();
        ModeloFactura modelof = new ModeloFactura();
        ModeloVehiculo modelov = new ModeloVehiculo();
        ModeloEmpleado modeloe = new ModeloEmpleado();
        ControladorFactura con = new ControladorFactura(vistaf, modelof, modeloc, modeloz, modelov, modelot, modeloe, modelota);
        con.InciarControl();
        vistaf.getServicio().setVisible(true);
        vistaf.getServicio().setSize(900,600);
        vis.getDekEscritorio().add(vistaf.getServicio());
        vis.getDekEscritorio().add(vistaf);
        vis.getJpPrincipal().setVisible(false);
        vis.getDekEscritorio().setVisible(true);
    }

    private void Despliegue(){
        if (vis.getBtDispliege().isSelected()) {
            vis.getDekEscritorio().setLocation(0, vis.getDekEscritorio().getLocation().y);
            vis.getJp_Descktop().setLocation(0, vis.getJp_Descktop().getLocation().y);            
            vis.getDekEscritorio().setSize(vis.getSize().width, vis.getDekEscritorio().getSize().height);
            vis.getJp_Descktop().setSize(vis.getSize().width, vis.getJp_Descktop().getSize().height);        
            vis.getJp_Despegable().setSize(0, vis.getJp_Despegable().getSize().height); 
            vis.getJpPrincipal().setSize(vis.getSize().width, vis.getJp_Descktop().getSize().height);
        }else{
            vis.getJp_Descktop().setLocation(83, vis.getJp_Descktop().getLocation().y);  
            vis.getDekEscritorio().setSize(vis.getSize().width-83, vis.getDekEscritorio().getSize().height);         
            vis.getJp_Descktop().setSize(vis.getSize().width-83, vis.getJp_Descktop().getSize().height);
            vis.getJp_Despegable().setSize(83, vis.getJp_Despegable().getSize().height);
            vis.getJpPrincipal().setSize(vis.getSize().width-83, vis.getJp_Descktop().getSize().height);
            
        }    
    }
    
    private void imprimirGRAFICOtarifa() {
        ConexionBD con = new ConexionBD();
        try {
           JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/tarifas/reportegraficotarifa.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirGRAFICOzonas() {
        ConexionBD con = new ConexionBD();
        try {
           JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/zonas/reportegraficozona.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirGRAFICOtickets() {
        ConexionBD con = new ConexionBD();
        try {
           JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/tickets/reportegraficoticket.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirGRAFICOEmplado() {
        ConexionBD con = new ConexionBD();
        try {
           JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/empleados/ReporteGraficoEmpleados.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirGRAFICOcargos() {
        ConexionBD con = new ConexionBD();
        try {
           JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/cargos/cargosreportegraficos.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirGRAFICOautos() {
        ConexionBD con = new ConexionBD();
        try {
           JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/vehiculos/reportegraficoautos.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void Tiempouso(){
            //RestarHora
            LocalDateTime horaIngreso = LocalDateTime.parse(hora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime salida = LocalDateTime.now() ;
            long diff = ChronoUnit.MILLIS.between(horaIngreso, salida);
            long diffMinutos =  Math.abs (diff / (60 * 1000));
            long restominutos = diffMinutos%60;
            long diffHoras =   (diff / (60 * 60 * 1000));
            JOptionPane.showMessageDialog(vis, "TIEMPO DE USO DEL PROGRAMA ES DE: "+diffHoras+":"+restominutos, "TIEMPO DE USO", 1);
           //FinRestarHora
    }
    
      //REPORTES
      private void imprimirReporteC() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/cargos/ReporteCargos.jasper"));
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja", "%" + "" + "%");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void imprimirReportecli() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/clientes/reporteclientes.jasper"));
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja", "%" + ""+ "%");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       private void imprimirReporteemple() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/empleados/reportepemplados.jasper"));
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja", "%" + "" + "%");
                  
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
              
        } catch (JRException ex) {
            Logger.getLogger(ControladorEmpledo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       private void imprimirReportefact() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/factura/ReporteSDAV.jasper"));
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("numFactura", "%" + "" + "%");
             parametros.put("RUTOSUBREPORTE", "SDAV/Vista/reportes/factura"); 
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirReportetarifa() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/tarifas/reportetarifas.jasper"));
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja", "%" + "" + "%");  
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
              
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirReportetick() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/tickets/reporteticket.jasper"));
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja", "%" + "" + "%");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirReportevehi() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/vehiculos/ReporteAutos.jasper"));
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja","%"+""+"%");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void imprimirReportezona() {
        ConexionBD con = new ConexionBD();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/SDAV/Vista/reportes/zonas/reportezonas.jasper"));;
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("aguja", "%" + "" + "%");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mostrarPrincipal(){
        vis.getJpPrincipal().setVisible(true);
        vis.getDekEscritorio().setVisible(false);
    }
    private void mostrarEscritorio(){
        vis.getJpPrincipal().setVisible(false);
        vis.getDekEscritorio().setVisible(true);
    }

    private void Cerrar (){
        System.exit(0);
    }

}
