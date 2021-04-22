/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author USUARIO
 */
public class VistaTicket extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaTicket
     */
    public VistaTicket() {
        initComponents();
    }

    public JDialog getDlgTicket() {
        return dlgTicket;
    }

    public void setDlgTicket(JDialog dlgTicket) {
        this.dlgTicket = dlgTicket;
    }


    public JTextField getTextid_ticket() {
        return textid_ticket;
    }

    public void setTextid_ticket(JTextField textid_ticket) {
        this.textid_ticket = textid_ticket;
    }

    public JComboBox<String> getComboBoxid_zona() {
        return ComboBoxid_zona;
    }

    public void setComboBoxid_zona(JComboBox<String> ComboBoxid_zona) {
        this.ComboBoxid_zona = ComboBoxid_zona;
    }


    public JButton getB_crearticket() {
        return b_crearticket;
    }

    public void setB_crearticket(JButton b_crearticket) {
        this.b_crearticket = b_crearticket;
    }

    public JButton getB_editarticket() {
        return b_editarticket;
    }

    public void setB_editarticket(JButton b_editarticket) {
        this.b_editarticket = b_editarticket;
    }

    public JButton getB_eliminarticket() {
        return b_eliminarticket;
    }

    public void setB_eliminarticket(JButton b_eliminarticket) {
        this.b_eliminarticket = b_eliminarticket;
    }

    public JButton getB_imprimirticket() {
        return b_imprimirticket;
    }

    public void setB_imprimirticket(JButton b_imprimirticket) {
        this.b_imprimirticket = b_imprimirticket;
    }

    public JButton getB_listarticket() {
        return b_listarticket;
    }

    public void setB_listarticket(JButton b_listarticket) {
        this.b_listarticket = b_listarticket;
    }

    public JButton getBaceptar() {
        return baceptar;
    }

    public void setBaceptar(JButton baceptar) {
        this.baceptar = baceptar;
    }

    public JButton getBcancelar() {
        return bcancelar;
    }

    public void setBcancelar(JButton bcancelar) {
        this.bcancelar = bcancelar;
    }

    public JTable getTablaticket() {
        return tablaticket;
    }

    public void setTablaticket(JTable tablaticket) {
        this.tablaticket = tablaticket;
    }

    public JTextField getTxtbuscar() {
        return txtbuscar;
    }

    public void setTxtbuscar(JTextField txtbuscar) {
        this.txtbuscar = txtbuscar;
    }

    public JTable getTABLA_PLACA() {
        return TABLA_PLACA;
    }

    public void setTABLA_PLACA(JTable TABLA_PLACA) {
        this.TABLA_PLACA = TABLA_PLACA;
    }

    public JLabel getLabelfecha() {
        return labelfecha;
    }

    public void setLabelfecha(JLabel labelfecha) {
        this.labelfecha = labelfecha;
    }

    public JLabel getLabelhora() {
        return labelhora;
    }

    public void setLabelhora(JLabel labelhora) {
        this.labelhora = labelhora;
    }




    public JButton getB_agregar() {
        return b_agregar;
    }

    public void setB_agregar(JButton b_agregar) {
        this.b_agregar = b_agregar;
    }

    public JTextField getTxtplaca() {
        return txtplaca;
    }

    public void setTxtplaca(JTextField txtplaca) {
        this.txtplaca = txtplaca;
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgTicket = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textid_ticket = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ComboBoxid_zona = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelfecha = new javax.swing.JLabel();
        labelhora = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TABLA_PLACA = new javax.swing.JTable();
        b_agregar = new javax.swing.JButton();
        txtplaca = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        bcancelar = new javax.swing.JButton();
        baceptar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtbuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        b_crearticket = new javax.swing.JButton();
        b_editarticket = new javax.swing.JButton();
        b_listarticket = new javax.swing.JButton();
        b_eliminarticket = new javax.swing.JButton();
        b_imprimirticket = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaticket = new javax.swing.JTable();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        txtbuscar1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        b_crearticket1 = new javax.swing.JButton();
        b_editarticket1 = new javax.swing.JButton();
        b_listarticket1 = new javax.swing.JButton();
        b_eliminarticket1 = new javax.swing.JButton();
        b_imprimirticket1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaticket1 = new javax.swing.JTable();

        dlgTicket.setBackground(new java.awt.Color(0, 204, 0));
        dlgTicket.setForeground(new java.awt.Color(204, 255, 204));

        jPanel10.setBackground(new java.awt.Color(9, 122, 253));

        jLabel13.setBackground(new java.awt.Color(9, 122, 253));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_ticket_48px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel6.setText("ID TICKET:");

        textid_ticket.setEditable(false);
        textid_ticket.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        textid_ticket.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textid_ticket.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel7.setText("ID ZONA:");

        ComboBoxid_zona.setBackground(new java.awt.Color(240, 240, 240));
        ComboBoxid_zona.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        ComboBoxid_zona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR" }));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel15.setText(" FECHA:");

        jLabel12.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel12.setText("HORA:");

        labelfecha.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N

        labelhora.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N

        jSeparator1.setBackground(new java.awt.Color(9, 122, 253));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel5.setText("SELECCIONAR UNA PLACA:");

        TABLA_PLACA.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        TABLA_PLACA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PLACA", "VEHICULO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TABLA_PLACA.setGridColor(new java.awt.Color(151, 153, 73));
        TABLA_PLACA.setSelectionBackground(new java.awt.Color(153, 255, 153));
        TABLA_PLACA.setSelectionForeground(new java.awt.Color(0, 0, 51));
        jScrollPane3.setViewportView(TABLA_PLACA);

        b_agregar.setBackground(new java.awt.Color(51, 255, 0));
        b_agregar.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        b_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/Add.png"))); // NOI18N
        b_agregar.setText("AGREGAR");

        txtplaca.setBackground(new java.awt.Color(240, 240, 240));
        txtplaca.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        txtplaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtplaca.setBorder(null);

        bcancelar.setBackground(new java.awt.Color(255, 51, 51));
        bcancelar.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        bcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/CANCELACION.png"))); // NOI18N
        bcancelar.setText("CANCELAR");
        bcancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2));

        baceptar.setBackground(new java.awt.Color(0, 153, 0));
        baceptar.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        baceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/añadir compra.png"))); // NOI18N
        baceptar.setText("CREAR");
        baceptar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0), 2));

        javax.swing.GroupLayout dlgTicketLayout = new javax.swing.GroupLayout(dlgTicket.getContentPane());
        dlgTicket.getContentPane().setLayout(dlgTicketLayout);
        dlgTicketLayout.setHorizontalGroup(
            dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgTicketLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dlgTicketLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textid_ticket))
                    .addGroup(dlgTicketLayout.createSequentialGroup()
                        .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dlgTicketLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(dlgTicketLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelfecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelhora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dlgTicketLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(ComboBoxid_zona, 0, 171, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dlgTicketLayout.createSequentialGroup()
                        .addComponent(b_agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(txtplaca))))
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgTicketLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(baceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgTicketLayout.setVerticalGroup(
            dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgTicketLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(textid_ticket))
                .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dlgTicketLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxid_zona, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(dlgTicketLayout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(dlgTicketLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(labelhora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(dlgTicketLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(b_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(dlgTicketLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dlgTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baceptar)
                    .addComponent(bcancelar))
                .addGap(5, 5, 5))
        );

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("TICKET");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_ticket_16px_1.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(9, 122, 253));
        jPanel4.setName(""); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(944, 65));

        txtbuscar.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtbuscar.setToolTipText("BUSCAR");
        txtbuscar.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar:");

        b_crearticket.setBackground(new java.awt.Color(147, 191, 242));
        b_crearticket.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_crearticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_add_40px.png"))); // NOI18N
        b_crearticket.setToolTipText("NUEVO");
        b_crearticket.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        b_editarticket.setBackground(new java.awt.Color(147, 191, 242));
        b_editarticket.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_editarticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_edit_40px_1.png"))); // NOI18N
        b_editarticket.setToolTipText("EDITAR");
        b_editarticket.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        b_listarticket.setBackground(new java.awt.Color(147, 191, 242));
        b_listarticket.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_listarticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_list_40px.png"))); // NOI18N
        b_listarticket.setToolTipText("LISTAR");
        b_listarticket.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        b_eliminarticket.setBackground(new java.awt.Color(147, 191, 242));
        b_eliminarticket.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_eliminarticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_delete_40px.png"))); // NOI18N
        b_eliminarticket.setToolTipText("ELIMINAR");
        b_eliminarticket.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        b_imprimirticket.setBackground(new java.awt.Color(147, 191, 242));
        b_imprimirticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_print_40px.png"))); // NOI18N
        b_imprimirticket.setToolTipText("IMPRIMIR");
        b_imprimirticket.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_crearticket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_editarticket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_eliminarticket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
                .addComponent(b_listarticket, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_imprimirticket, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_crearticket, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_editarticket, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(b_eliminarticket, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_listarticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_imprimirticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setPreferredSize(new java.awt.Dimension(918, 50));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/logo4.PNG"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 574, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_END);

        tablaticket.setBackground(new java.awt.Color(254, 255, 255));
        tablaticket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        tablaticket.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        tablaticket.setForeground(new java.awt.Color(102, 102, 102));
        tablaticket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ticket", "Zona", "Placa", "Fecha", "Hora Ingreso", "Hora Salida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaticket.setGridColor(new java.awt.Color(151, 153, 73));
        tablaticket.setSelectionBackground(new java.awt.Color(153, 255, 153));
        tablaticket.setSelectionForeground(new java.awt.Color(0, 0, 51));
        jScrollPane1.setViewportView(tablaticket);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("TICKET");

        jPanel5.setBackground(new java.awt.Color(9, 122, 253));
        jPanel5.setName(""); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(944, 65));

        txtbuscar1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtbuscar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buscar:");

        b_crearticket1.setBackground(new java.awt.Color(147, 191, 242));
        b_crearticket1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_crearticket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_add_40px.png"))); // NOI18N
        b_crearticket1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));

        b_editarticket1.setBackground(new java.awt.Color(147, 191, 242));
        b_editarticket1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_editarticket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_edit_40px_1.png"))); // NOI18N
        b_editarticket1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 2));

        b_listarticket1.setBackground(new java.awt.Color(147, 191, 242));
        b_listarticket1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_listarticket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_list_40px.png"))); // NOI18N
        b_listarticket1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153), 2));

        b_eliminarticket1.setBackground(new java.awt.Color(147, 191, 242));
        b_eliminarticket1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_eliminarticket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_delete_40px.png"))); // NOI18N
        b_eliminarticket1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 0), 2));

        b_imprimirticket1.setBackground(new java.awt.Color(147, 191, 242));
        b_imprimirticket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SDAV/Vista/Iconos/icons8_print_40px.png"))); // NOI18N
        b_imprimirticket1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51), 2));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_crearticket1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_editarticket1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_eliminarticket1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_listarticket1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_imprimirticket1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_crearticket1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_editarticket1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(b_eliminarticket1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_listarticket1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_imprimirticket1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jInternalFrame1.getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setPreferredSize(new java.awt.Dimension(918, 50));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jInternalFrame1.getContentPane().add(jPanel7, java.awt.BorderLayout.PAGE_END);

        tablaticket1.setBackground(new java.awt.Color(254, 255, 255));
        tablaticket1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        tablaticket1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        tablaticket1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID TICKET", "ID ZONA", "PLACA", "FECHA", "HORA INGRESO", "HORA SALIDA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaticket1.setGridColor(new java.awt.Color(151, 153, 73));
        tablaticket1.setSelectionBackground(new java.awt.Color(153, 255, 153));
        tablaticket1.setSelectionForeground(new java.awt.Color(0, 0, 51));
        jScrollPane2.setViewportView(tablaticket1);

        jInternalFrame1.getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jInternalFrame1, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxid_zona;
    private javax.swing.JTable TABLA_PLACA;
    private javax.swing.JButton b_agregar;
    private javax.swing.JButton b_crearticket;
    private javax.swing.JButton b_crearticket1;
    private javax.swing.JButton b_editarticket;
    private javax.swing.JButton b_editarticket1;
    private javax.swing.JButton b_eliminarticket;
    private javax.swing.JButton b_eliminarticket1;
    private javax.swing.JButton b_imprimirticket;
    private javax.swing.JButton b_imprimirticket1;
    private javax.swing.JButton b_listarticket;
    private javax.swing.JButton b_listarticket1;
    private javax.swing.JButton baceptar;
    private javax.swing.JButton bcancelar;
    private javax.swing.JDialog dlgTicket;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelfecha;
    private javax.swing.JLabel labelhora;
    private javax.swing.JTable tablaticket;
    private javax.swing.JTable tablaticket1;
    private javax.swing.JTextField textid_ticket;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtbuscar1;
    private javax.swing.JTextField txtplaca;
    // End of variables declaration//GEN-END:variables
}
