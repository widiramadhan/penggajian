/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
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
public class transaksi extends javax.swing.JFrame {
    koneksi kon=new koneksi();
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JasperPrint jasperPrint;
    Map<String, Object> param = new HashMap<String, Object>();

    /**
     * Creates new form transaksi
     */
    public transaksi() {
        initComponents();
        kon.setKoneksi();
        setSize(670,600);
        ltanggal2.setVisible(false);
        ceklogin();
        JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
        JRProperties.setProperty("net.sf.jasperreports.default.font.name", "Arial");
        
    }
    
    public String nik;

    public String getNik() {
        return nik;
    }
    
    void bersih(){
        tnotrans.setText("");
        tnik.setText("");
        tnama.setText("");
        tjabatan.setText("");
        tstatus.setText("");
        tbulan.setText("");
        ttahun.setText("");
        thadir.setText("0");
        tlembur.setText("0");
        tgapok.setText("0");
        ttunjab.setText("0");
        ttunkel.setText("0");
        ttransport.setText("0");
        tmakan.setText("0");
        tulembur.setText("0");
        tpph.setText("0");
        lgaji.setText("0");
        talpa.setText("0");
        lgajikotor.setText("0");
        ltotpot.setText("0");
    }
    
    void nonaktif(){
        tnotrans.setEnabled(false);
        tnik.setEnabled(false);
        tnama.setEnabled(false);
        tjabatan.setEnabled(false);
        tstatus.setEnabled(false);
        tbulan.setEnabled(false);
        ttahun.setEnabled(false);
        thadir.setEnabled(false);
        tlembur.setEnabled(false);
        tgapok.setEnabled(false);
        ttunjab.setEnabled(false);
        ttunkel.setEnabled(false);
        ttransport.setEnabled(false);
        tmakan.setEnabled(false);
        tulembur.setEnabled(false);
        talpa.setEnabled(false);
        tpph.setEnabled(false);
    }
    
    public void angka(java.awt.event.KeyEvent evt){
        char a = evt.getKeyChar();
        if(!((Character.isDigit(a) || (a==KeyEvent.VK_BACK_SPACE) || (a==KeyEvent.VK_DELETE))))
        {
            evt.consume();
        }
    }
    
    private void ceklogin(){
        try{
            String sql="select * from pengguna where status='active'";
            kon.rs=kon.st.executeQuery(sql);
            if (kon.rs.next()){
                String nama=kon.rs.getString("nama");
                ladmin.setText(nama);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void setnik(){
        try{
            String sql="SELECT jabatan.kode_jabatan, jabatan.nama_jabatan, jabatan.tunjab, jabatan.gajipokok,karyawan.nik,karyawan.nama_karyawan,karyawan.kode_jabatan,karyawan.status,status.tunjangan_status FROM jabatan INNER JOIN (karyawan INNER JOIN status ON karyawan.status_perkawinan = status.status_perkawinan) ON karyawan.kode_jabatan = jabatan.kode_jabatan WHERE karyawan.nik ='"+tnik.getText()+"'";
            kon.rs=kon.st.executeQuery(sql);
            if (kon.rs.next()){
                int nik_col=kon.rs.getInt("karyawan.nik");
                String nama=kon.rs.getString("nama_karyawan");
                String jab=kon.rs.getString("nama_jabatan");
                String gol=kon.rs.getString("status");
                String gaji=kon.rs.getString("gajipokok");
                String tunjab=kon.rs.getString("tunjab");
                String stat=kon.rs.getString("tunjangan_status");
                tnama.setText(nama);
                tjabatan.setText(jab);
                tgapok.setText(gaji);
                ttunjab.setText(tunjab);
                ttunkel.setText(stat);
                tstatus.setText(gol);
                thadir.setEnabled(true);
                thadir.requestFocus();
                tnik.setEnabled(false);
                bcari.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(this,"NIK tidak terdaftar","Informasi", JOptionPane.INFORMATION_MESSAGE);
                tnik.setText("");
                tnama.setText("");
                tjabatan.setText("");
                tstatus.setText("");
                tnik.requestFocus();
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void setkehadiran(){
        try{
            String query="Select * from master";
            kon.rs=kon.st.executeQuery(query);
            while(kon.rs.next()){
                int honor=kon.rs.getInt("honor_lembur");
                int makan=kon.rs.getInt("uang_makan");
                int transport=kon.rs.getInt("uang_transport");
                int potongan_alpa=kon.rs.getInt("potongan_alpa");
                
                //menghitung potongan alpa
                int alpa=Integer.parseInt(thadir.getText());
                int pot_alpa=alpa*potongan_alpa;
                talpa.setText(Integer.toString(pot_alpa));
                
                //menghitung uang makan
                int umakan=alpa*(makan/30);
                int totmakan=makan-umakan;
                tmakan.setText(Integer.toString(totmakan));
                
                //menghitung uang transport
                int utrans=alpa*(transport/30);
                int tottrans=transport-utrans;
                ttransport.setText(Integer.toString(tottrans));
                
                //menghitung lembur
                int lembur=Integer.parseInt(tlembur.getText());
                int hitung=lembur*honor;
                tulembur.setText(Integer.toString(hitung));
                
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }

    void bulan(){
        String bulan=tbulan.getText();
        int hadir=Integer.parseInt(thadir.getText());
        if(bulan.equals("December")){
            if(hadir>31){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Desember hanya sampai 31","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("January")){
            if(hadir>31){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Januari hanya sampai 31","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("February")){
            if(hadir>29){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Februari hanya sampai 29","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("March")){
            if(hadir>31){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Maret hanya sampai 31","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("April")){
            if(hadir>30){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan April hanya sampai 30","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("May")){
            if(hadir>31){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Mei hanya sampai 31","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("June")){
            if(hadir>30){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Juni hanya sampai 30","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("July")){
            if(hadir>31){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Juli hanya sampai 31","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("August")){
            if(hadir>31){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Agustus hanya sampai 31","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("September")){
            if(hadir>30){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan September hanya sampai 30","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("October")){
            if(hadir>31){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan Oktober hanya sampai 31","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }else if(bulan.equals("November")){
            if(hadir>30){
                JOptionPane.showMessageDialog(this,"Jumlah tanggal pada bulan November hanya sampai 30","Informasi", JOptionPane.INFORMATION_MESSAGE);
                thadir.setEnabled(true);
                thadir.setText("0");
                thadir.requestFocus();
                bproses.setEnabled(false);
            }  
        }
    }
    
    private void SimpanData(){
        try{
            String sql="insert into transaksi values('"+tnotrans.getText()+"','"+ltanggal2.getText()+"','"+tnik.getText()+"','"+tjabatan.getText()+"','"+tbulan.getText()+"','"+ttahun.getText()+"','"+lgaji.getText()+"')";
            kon.st.executeUpdate(sql);
            String detail="insert into detail_transaksi values ('"+tnotrans.getText()+"','"+thadir.getText()+"','"+tlembur.getText()+"','"+tgapok.getText()+"','"+ttunjab.getText()+"','"+ttunkel.getText()+"','"+ttransport.getText()+"','"+tmakan.getText()+"','"+tulembur.getText()+"','"+lgajikotor.getText()+"','"+talpa.getText()+"','"+tpph.getText()+"','"+ltotpot.getText()+"')";
            kon.st.executeUpdate(detail);
            
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            if (JOptionPane.showConfirmDialog(this, "Cetak Slip gaji? ", "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                String path="src/penggajian/slipgaji.jasper";      // letak penyimpanan report
                HashMap parameter = new HashMap();
                parameter.put("notrans",tnotrans.getText()); // "notrans" => nama parameter yang dibuat
                //tnotrans.getText() ==> disesuaikan dengan jTextField yang digunakan
                JasperPrint print = JasperFillManager.fillReport(path,parameter,kon.setKoneksi());
                JasperViewer.viewReport(print, false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);
                }
                
            } else {
                bersih();
                nonaktif();
                btambah.setEnabled(true);
                bproses.setEnabled(false);
                bcari.setEnabled(false);
                bsimpan.setEnabled(false);
                bkeluar.setText("Keluar");
            }
           
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tnotrans = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ltanggal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tnik = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tjabatan = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        tstatus = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tgapok = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ttunjab = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ttunkel = new javax.swing.JTextField();
        ttransport = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tulembur = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tmakan = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        lgajikotor = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        tbulan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        thadir = new javax.swing.JTextField();
        tlembur = new javax.swing.JTextField();
        ttahun = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        btambah = new javax.swing.JButton();
        bproses = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        talpa = new javax.swing.JTextField();
        tpph = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        ltotpot = new javax.swing.JLabel();
        ltanggal2 = new javax.swing.JLabel();
        bsimpan = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lgaji = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        ladmin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/datatransaksi.png"))); // NOI18N

        jLabel1.setText("Nomor Transaksi");

        jLabel3.setText("Tanggal Transaksi :");

        ltanggal.setText("tanggal");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Karyawan"));

        jLabel4.setText("NIK");

        tnik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tnikKeyPressed(evt);
            }
        });

        jLabel5.setText("Nama");

        jLabel6.setText("Jabatan");

        bcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });

        jLabel18.setText("Status");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tstatus, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(tnama)
                    .addComponent(tjabatan)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tnik, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pendapatan"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Gaji Pokok");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 30, -1, -1));
        jPanel3.add(tgapok, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 27, 100, -1));

        jLabel8.setText("Tunjangan Jabatan");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 56, -1, -1));
        jPanel3.add(ttunjab, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 53, 100, -1));

        jLabel9.setText("Tunjangan Keluarga");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 85, -1, -1));
        jPanel3.add(ttunkel, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 85, 100, -1));
        jPanel3.add(ttransport, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 116, 100, -1));

        jLabel11.setText("Uang Transportasi");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 119, -1, -1));

        tulembur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tulemburActionPerformed(evt);
            }
        });
        jPanel3.add(tulembur, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 176, 100, -1));

        jLabel15.setText("Lembur");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 179, -1, -1));
        jPanel3.add(tmakan, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 147, 100, -1));

        jLabel16.setText("Uang Makan");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 150, -1, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 207, 214, 10));

        jLabel19.setText("Gaji Kotor");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 223, -1, -1));

        lgajikotor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lgajikotor.setText("0");
        jPanel3.add(lgajikotor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 80, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Rp.");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 223, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data kehadiran"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setText("Bulan");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 30, -1, -1));
        jPanel4.add(tbulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 27, 130, -1));

        jLabel13.setText("Jumlah tidak hadir dalam sebulan");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 92, -1, -1));

        jLabel14.setText("Jumlah Lembur dalam sebulan");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 123, -1, -1));

        thadir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                thadirKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                thadirKeyTyped(evt);
            }
        });
        jPanel4.add(thadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 89, 130, -1));

        tlembur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tlemburKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tlemburKeyTyped(evt);
            }
        });
        jPanel4.add(tlembur, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 120, 130, -1));
        jPanel4.add(ttahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 58, 130, -1));

        jLabel24.setText("Tahun");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 61, -1, -1));

        btambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trans.png"))); // NOI18N
        btambah.setText("Input");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        bproses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/proses.png"))); // NOI18N
        bproses.setText("Proses");
        bproses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprosesActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Potongan"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText("Ketidakhadiran");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 30, -1, -1));
        jPanel5.add(talpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 27, 103, -1));
        jPanel5.add(tpph, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 53, 103, -1));

        jLabel10.setText("PPH Pasal 21");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 56, -1, -1));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 214, 10));

        jLabel22.setText("Total Potongan");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Rp.");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        ltotpot.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ltotpot.setText("0");
        jPanel5.add(ltotpot, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 80, 20));

        ltanggal2.setText("tanggal");

        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simpan.png"))); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        bkeluar.setText("Keluar");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Gaji Bersih");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Rp.");

        lgaji.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lgaji.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lgaji))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel26.setText("Admin :");

        ladmin.setText("ladmin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(bsimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bproses)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(101, 101, 101)
                                    .addComponent(ltanggal2)
                                    .addGap(158, 158, 158))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel26)
                                    .addGap(67, 67, 67)
                                    .addComponent(ladmin))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(41, 41, 41)
                                .addComponent(tnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ltanggal))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ltanggal2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(ladmin))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(ltanggal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bproses, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                    .addComponent(bkeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(672, 548));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tulemburActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tulemburActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tulemburActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Date skrg = new Date();
        SimpleDateFormat tgl=new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat tgl2=new SimpleDateFormat("yyyy-MM-dd");
        ltanggal.setText(tgl.format(skrg));
        ltanggal2.setText(tgl2.format(skrg));
        bersih();
        nonaktif();
        bproses.setEnabled(false);
        bcari.setEnabled(false);
        bsimpan.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        // TODO add your handling code here:
         bersih();
         Date skrg = new Date();
         SimpleDateFormat notrans=new SimpleDateFormat("ddMMyyyy-HHmmss");
         SimpleDateFormat bulan=new SimpleDateFormat("MMMM");
         SimpleDateFormat tahun=new SimpleDateFormat("yyyy");
         tbulan.setText(bulan.format(skrg));
         ttahun.setText(tahun.format(skrg));
         tnotrans.setText(notrans.format(skrg));
         bcari.setEnabled(true);
         tnik.setEnabled(true);
         tnik.requestFocus();
         bkeluar.setText("Batal");
         btambah.setEnabled(false);
    }//GEN-LAST:event_btambahActionPerformed

    private void tnikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnikKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            setnik();
        }
    }//GEN-LAST:event_tnikKeyPressed

    private void thadirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thadirKeyTyped
        // TODO add your handling code here:
        angka(evt);       
    }//GEN-LAST:event_thadirKeyTyped

    private void tlemburKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tlemburKeyTyped
        // TODO add your handling code here:
        angka(evt);
    }//GEN-LAST:event_tlemburKeyTyped

    private void bprosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bprosesActionPerformed
        // TODO add your handling code here:
        bulan();
        setkehadiran();
        
        //penghitungan gaji kotor
        float a=Float.parseFloat(tgapok.getText());
        float b=Float.parseFloat(ttunjab.getText());
        float c=Float.parseFloat(ttunkel.getText());
        float d=Float.parseFloat(ttransport.getText());
        float e=Float.parseFloat(tmakan.getText());
        float f=Float.parseFloat(tulembur.getText());
        float gajikotor=a+b+c+d+e+f;
        lgajikotor.setText(Float.toString(gajikotor));
                        
        //penghitungan pajak diambil dari golongan dan gaji kotor
        String status=tstatus.getText();
            if(status.equals("Honorer")){
                double pph=0;
                tpph.setText(Double.toString(pph));
            }else if(status.equals("Karyawan Tetap")){
                double pph=gajikotor*0.15;
                tpph.setText(Double.toString(pph));
            }else{
                tpph.setText("0");
            }
        
        //penghitungan potongan
        double g=Double.parseDouble(talpa.getText());
        double h=Double.parseDouble(tpph.getText());
        double totalpotongan=g+h;
        ltotpot.setText(Double.toString(totalpotongan));
        
        //penghitungan gaji bersih
        double gaji_kotor=Double.parseDouble(lgajikotor.getText());
        double potongan=Double.parseDouble(ltotpot.getText());
        double gajibersih=gaji_kotor-potongan;
        lgaji.setText(Double.toString(gajibersih));
        
        bproses.setEnabled(false);
        bsimpan.setEnabled(true);
    }//GEN-LAST:event_bprosesActionPerformed

    private void thadirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thadirKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tlembur.setEnabled(true);
            tlembur.requestFocus();
            thadir.setEnabled(false);
        }
    }//GEN-LAST:event_thadirKeyPressed

    private void tlemburKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tlemburKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            bproses.setEnabled(true);
            bproses.requestFocus();
            tlembur.setEnabled(false);
        }
    }//GEN-LAST:event_tlemburKeyPressed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here:
        SimpanData();
        bersih();
        nonaktif();
        btambah.setEnabled(true);
        bproses.setEnabled(false);
        bcari.setEnabled(false);
        bsimpan.setEnabled(false);
        bkeluar.setText("Keluar");
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        // TODO add your handling code here:
        boolean closable = true;

        datakaryawan lk = new datakaryawan(null, closable);
        lk.trans = this;
        lk.setVisible(true);
        lk.setResizable(true);
        tnik.setText(nik);
        setnik();
    }//GEN-LAST:event_bcariActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        // TODO add your handling code here:
        String keluar=bkeluar.getText();
        if(keluar.equals("Batal")){
            Date skrg = new Date();
            SimpleDateFormat tgl=new SimpleDateFormat("dd MMMM yyyy");
            SimpleDateFormat tgl2=new SimpleDateFormat("yyyy-MM-dd");
            ltanggal.setText(tgl.format(skrg));
            ltanggal2.setText(tgl2.format(skrg));
            bersih();
            nonaktif();
            btambah.setEnabled(true);
            bproses.setEnabled(false);
            bcari.setEnabled(false);
            bsimpan.setEnabled(false);
            bkeluar.setText("Keluar");
        }else{
            this.dispose();
            menuutama mu = new menuutama(); //habis ini berjalan langsung ke form login
            mu.setVisible(true);
        }
    }//GEN-LAST:event_bkeluarActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcari;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton bproses;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel ladmin;
    private javax.swing.JLabel lgaji;
    private javax.swing.JLabel lgajikotor;
    private javax.swing.JLabel ltanggal;
    private javax.swing.JLabel ltanggal2;
    private javax.swing.JLabel ltotpot;
    private javax.swing.JTextField talpa;
    private javax.swing.JTextField tbulan;
    private javax.swing.JTextField tgapok;
    private javax.swing.JTextField thadir;
    private javax.swing.JTextField tjabatan;
    private javax.swing.JTextField tlembur;
    private javax.swing.JTextField tmakan;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnik;
    private javax.swing.JTextField tnotrans;
    private javax.swing.JTextField tpph;
    private javax.swing.JTextField tstatus;
    private javax.swing.JTextField ttahun;
    private javax.swing.JTextField ttransport;
    private javax.swing.JTextField ttunjab;
    private javax.swing.JTextField ttunkel;
    private javax.swing.JTextField tulembur;
    // End of variables declaration//GEN-END:variables
}
