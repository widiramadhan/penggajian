/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

import java.awt.event.KeyEvent;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.engine.xml.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.fonts.*;
import net.sf.jasperreports.engine.fonts.FontUtil.*;
import net.sf.jasperreports.engine.fill.SimpleTextLineWrapper.*;
import net.sf.jasperreports.engine.fill.TextMeasurer.*;
import net.sf.jasperreports.engine.fill.JRFillTextElement.*;
import net.sf.jasperreports.engine.fill.JRFillStaticText.*;
import net.sf.jasperreports.engine.fill.JRFillElementContainer.*;
import net.sf.jasperreports.engine.fill.JRFillBand.*;
import net.sf.jasperreports.engine.fill.JRVerticalFiller.*;
import net.sf.jasperreports.engine.fill.JRVerticalFiller.*;
import net.sf.jasperreports.engine.fill.JRBaseFiller.*;
import net.sf.jasperreports.engine.fill.JRFiller.*;
import net.sf.jasperreports.engine.JasperFillManager.*;

/**
 *
 * @author Widi Ramadhan
 */
public class laporan extends javax.swing.JFrame {
    koneksi kon=new koneksi();
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JasperPrint jasperPrint;
    Map<String, Object> param = new HashMap<String, Object>();
    private Object [][] datalaporan=null;
    private String[]label={"Nomor Transaksi","Tanggal","NIK","Nama Karyawan","Bulan","Tahun","Gaji Bersih"};

    /**
     * Creates new form lap_penggajian
     */
    public laporan() {
        initComponents();
        kon.setKoneksi();
        nonaktif();
        BacaTabel();
        tnotrans.setVisible(false);
        ttgldari.setVisible(false);
        ttglsampai.setVisible(false);
        JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
        JRProperties.setProperty("net.sf.jasperreports.default.font.name", "Arial");
    }
    
    private void nonaktif(){
        tnama.setEnabled(false);
        bnama.setEnabled(false);
        tdari.setEnabled(false);
        tsampai.setEnabled(false);
        bperiode.setEnabled(false);
        cbulan.setEnabled(false);
        ctahun.setEnabled(false);
        bbulan.setEnabled(false);
        bcetak.setEnabled(false);
    }
    
    private void bersih(){
        tnama.setText("");
        cbulan.setSelectedItem("- Pilih Bulan -");
        ctahun.setSelectedItem("- Pilih Tahun -");
    }
    
    private void BacaTabel(){
        try{
            String sql="Select * from karyawan inner join transaksi on karyawan.nik=transaksi.nik order by transaksi.tanggal";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datalaporan=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datalaporan[x][0]=kon.rs.getString("notrans");
                datalaporan[x][1]=kon.rs.getString("tanggal");
                datalaporan[x][2]=kon.rs.getString("nik");
                datalaporan[x][3]=kon.rs.getString("nama_karyawan");
                datalaporan[x][4]=kon.rs.getString("bulan");
                datalaporan[x][5]=kon.rs.getString("tahun");
                datalaporan[x][6]=kon.rs.getString("gaji_bersih");
                x++;
            }
            tabel_laporan.setModel(new DefaultTableModel(datalaporan,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void CariNama(){
        try{
            String sql="Select * from karyawan inner join transaksi on karyawan.nik=transaksi.nik where karyawan.nama_karyawan like '%" +tnama.getText()+ "%'";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datalaporan=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datalaporan[x][0]=kon.rs.getString("notrans");
                datalaporan[x][1]=kon.rs.getString("tanggal");
                datalaporan[x][2]=kon.rs.getString("nik");
                datalaporan[x][3]=kon.rs.getString("nama_karyawan");
                datalaporan[x][4]=kon.rs.getString("bulan");
                datalaporan[x][5]=kon.rs.getString("tahun");
                datalaporan[x][6]=kon.rs.getString("gaji_bersih");
                x++;
            }
            tabel_laporan.setModel(new DefaultTableModel(datalaporan,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void CariPeriode(){
        try{
            SimpleDateFormat date;
            date= new SimpleDateFormat("yyyy-MM-dd");
            String sql="Select * from transaksi inner join karyawan on transaksi.nik=karyawan.nik where transaksi.tanggal between '"+date.format(tdari.getDate())+ "' and '" +date.format(tsampai.getDate())+ "'";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datalaporan=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datalaporan[x][0]=kon.rs.getString("notrans");
                datalaporan[x][1]=kon.rs.getString("tanggal");
                datalaporan[x][2]=kon.rs.getString("nik");
                datalaporan[x][3]=kon.rs.getString("nama_karyawan");
                datalaporan[x][4]=kon.rs.getString("bulan");
                datalaporan[x][5]=kon.rs.getString("tahun");
                datalaporan[x][6]=kon.rs.getString("gaji_bersih");
                x++;
            }
            tabel_laporan.setModel(new DefaultTableModel(datalaporan,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void CariBulan(){
        try{
            String sql="Select * from transaksi inner join karyawan on transaksi.nik=karyawan.nik where transaksi.bulan like '%" +cbulan.getSelectedItem()+ "%' and transaksi.tahun like '%" +ctahun.getSelectedItem()+ "%'";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datalaporan=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datalaporan[x][0]=kon.rs.getString("notrans");
                datalaporan[x][1]=kon.rs.getString("tanggal");
                datalaporan[x][2]=kon.rs.getString("nik");
                datalaporan[x][3]=kon.rs.getString("nama_karyawan");
                datalaporan[x][4]=kon.rs.getString("bulan");
                datalaporan[x][5]=kon.rs.getString("tahun");
                datalaporan[x][6]=kon.rs.getString("gaji_bersih");
                x++;
            }
            tabel_laporan.setModel(new DefaultTableModel(datalaporan,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btutup = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_laporan = new javax.swing.JTable();
        bcetak = new javax.swing.JButton();
        rnama = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        tnama = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bnama = new javax.swing.JButton();
        rperiode = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bperiode = new javax.swing.JButton();
        tdari = new com.toedter.calendar.JDateChooser();
        tsampai = new com.toedter.calendar.JDateChooser();
        rbulan = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        cbulan = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bbulan = new javax.swing.JButton();
        ctahun = new javax.swing.JComboBox();
        tnotrans = new javax.swing.JTextField();
        ttgldari = new javax.swing.JTextField();
        ttglsampai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        btutup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        btutup.setText("Tutup");
        btutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btutupActionPerformed(evt);
            }
        });

        tabel_laporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_laporanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_laporan);

        bcetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print.png"))); // NOI18N
        bcetak.setText("Cetak");
        bcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcetakActionPerformed(evt);
            }
        });

        rnama.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rnama);
        rnama.setText("Cari Berdasarkan Nama Karyawan");
        rnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rnamaActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        tnama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tnamaKeyPressed(evt);
            }
        });

        jLabel1.setText("Nama Karyawan :");

        bnama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N
        bnama.setText("Cari");
        bnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnamaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bnama)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bnama)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rperiode.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rperiode);
        rperiode.setText("Cari Berdasarkan Periode");
        rperiode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rperiodeActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel2.setText("Tanggal Dari");

        jLabel3.setText("Tanggal Sampai");

        bperiode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N
        bperiode.setText("Cari");
        bperiode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bperiodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bperiode))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tdari, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(tsampai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(tdari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(tsampai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(bperiode)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rbulan.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbulan);
        rbulan.setText("Cari Berdasarkan Per-Bulan");
        rbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbulanActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        cbulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Bulan -", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        jLabel4.setText("Bulan");

        jLabel5.setText("Tahun");

        bbulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N
        bbulan.setText("Cari");
        bbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbulanActionPerformed(evt);
            }
        });

        ctahun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Tahun -", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bbulan)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(88, 88, 88))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbulan, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctahun, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bbulan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tnotrans.setText("tnotrans");

        ttgldari.setText("jTextField1");

        ttglsampai.setText("jTextField2");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/datalaporan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ttgldari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ttglsampai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbulan)
                                    .addComponent(rnama)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rperiode)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(bcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btutup, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ttgldari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ttglsampai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rnama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btutup, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rperiode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbulan)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(921, 549));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tnamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnamaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            CariNama();
        }
    }//GEN-LAST:event_tnamaKeyPressed

    private void btutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btutupActionPerformed
        // TODO add your handling code here:
        menuutama mu=new menuutama();
        mu.setVisible(true);
        this.dispose();       
    }//GEN-LAST:event_btutupActionPerformed

    private void bnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnamaActionPerformed
        // TODO add your handling code here:
        CariNama();
    }//GEN-LAST:event_bnamaActionPerformed

    private void bbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbulanActionPerformed
        // TODO add your handling code here:
        CariBulan();
        bcetak.setEnabled(true);
    }//GEN-LAST:event_bbulanActionPerformed

    private void rnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rnamaActionPerformed
        // TODO add your handling code here:
        nonaktif();
        bersih();
        tnama.setEnabled(true);
        bnama.setEnabled(true);
    }//GEN-LAST:event_rnamaActionPerformed

    private void rperiodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rperiodeActionPerformed
        // TODO add your handling code here:
        nonaktif();
        bersih();
        tdari.setEnabled(true);
        tsampai.setEnabled(true);
        bperiode.setEnabled(true);
    }//GEN-LAST:event_rperiodeActionPerformed

    private void rbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbulanActionPerformed
        // TODO add your handling code here:
        nonaktif();
        bersih();
        cbulan.setEnabled(true);
        ctahun.setEnabled(true);
        bbulan.setEnabled(true);
    }//GEN-LAST:event_rbulanActionPerformed

    private void bperiodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bperiodeActionPerformed
        // TODO add your handling code here:
        if(tdari.equals("") || tsampai.equals("")){
            JOptionPane.showMessageDialog(null,"Tanggal Belum Diisi");
        }else{
            CariPeriode();
            bcetak.setEnabled(true);
        }
    }//GEN-LAST:event_bperiodeActionPerformed

    private void tabel_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_laporanMouseClicked
        // TODO add your handling code here:
        bcetak.setEnabled(true);
        int row=tabel_laporan.getSelectedRow();
        tnotrans.setText((String)tabel_laporan.getValueAt(row,0));
    }//GEN-LAST:event_tabel_laporanMouseClicked

    private void bcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcetakActionPerformed
        // TODO add your handling code here:
        if(rnama.isSelected()){
            try {
                int row=tabel_laporan.getSelectedRow();
                String path="src/penggajian/slipgaji.jasper";      // letak penyimpanan report
                HashMap parameter = new HashMap();
                parameter.put("notrans",tnotrans.getText());
                JasperPrint print = JasperFillManager.fillReport(path,parameter,kon.setKoneksi());
                JasperViewer.viewReport(print, false);
            } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);
            }
        }else if(rperiode.isSelected()){
            SimpleDateFormat date;
            date= new SimpleDateFormat("dd/MM/yyyy");
            ttgldari.setText(date.format(tdari.getDate()));
            ttglsampai.setText(date.format(tsampai.getDate()));
        
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                //konversi dari nilai string ke tanggal
                Date TanggalDari= df.parse(ttgldari.getText());
                Date TanggalSampai = df.parse(ttglsampai.getText());
                
                String path="src/penggajian/lap_periode.jasper";      // letak penyimpanan report
                HashMap parameter = new HashMap(2);
                parameter.put("tanggaldari",TanggalDari);
                parameter.put("tanggalsampai",TanggalSampai);
                JasperPrint print = JasperFillManager.fillReport(path,parameter,kon.setKoneksi());
                JasperViewer.viewReport(print, false);
            } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);
            }
        }else{
            try {
                String path="src/penggajian/lap_perbulan.jasper";      // letak penyimpanan report
                HashMap parameter = new HashMap(2);
                parameter.put("bulan",cbulan.getSelectedItem());
                parameter.put("tahun",ctahun.getSelectedItem());
                JasperPrint print = JasperFillManager.fillReport(path,parameter,kon.setKoneksi());
                JasperViewer.viewReport(print, false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);
                }
        }
    }//GEN-LAST:event_bcetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbulan;
    private javax.swing.JButton bcetak;
    private javax.swing.JButton bnama;
    private javax.swing.JButton bperiode;
    private javax.swing.JButton btutup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbulan;
    private javax.swing.JComboBox ctahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rbulan;
    private javax.swing.JRadioButton rnama;
    private javax.swing.JRadioButton rperiode;
    private javax.swing.JTable tabel_laporan;
    private com.toedter.calendar.JDateChooser tdari;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnotrans;
    private com.toedter.calendar.JDateChooser tsampai;
    private javax.swing.JTextField ttgldari;
    private javax.swing.JTextField ttglsampai;
    // End of variables declaration//GEN-END:variables
}
