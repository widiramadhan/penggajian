/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Widi Ramadhan
 */
public class karyawan extends javax.swing.JFrame {
    koneksi kon=new koneksi();
    private Object [][] datakaryawan=null;
    private String[]label={"NIK","Nama","Jabatan","Status","Email","Jenis Kelamin","Tempat Lahir","Tanggal Lahir","Alamat","Agama","Status Perkawinan"};
    
    /**
     * Creates new form datakaryawan
     */
    public karyawan() {
        initComponents();
        kon.setKoneksi();
        BacaTabelKaryawan();
        setSize(1080,650);
        tampiljabatan();
    }
    
    private void tampiljabatan(){
        try{
            String sql="select * from jabatan order by nama_jabatan asc";
            kon.rs=kon.st.executeQuery(sql);
            while(kon.rs.next()){
                cjabatan.addItem(kon.rs.getString("nama_jabatan"));
            }
        }catch(Exception e){
            
        }
    }
    
    public void setTable(){           
            int row=tabel_karyawan.getSelectedRow();
            tnik.setText((String)tabel_karyawan.getValueAt(row,0));
            tnama.setText((String)tabel_karyawan.getValueAt(row,1));
            cjabatan.setSelectedItem((String)tabel_karyawan.getValueAt(row,2));
            cstatus.setSelectedItem((String)tabel_karyawan.getValueAt(row,3));
            temail.setText((String)tabel_karyawan.getValueAt(row,4));
            ttempatlahir.setText((String)tabel_karyawan.getValueAt(row,6));
            talamat.setText((String)tabel_karyawan.getValueAt(row,8));
            cagama.setSelectedItem((String)tabel_karyawan.getValueAt(row,9));
            
            //menampilkan tanggal lahir
            String tgl=(String)tabel_karyawan.getValueAt(row,7);
            SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date tanggal=null;
            try{
                tanggal=date.parse(tgl);
            }catch(ParseException ex){
                Logger.getLogger(karyawan.class.getName()).log(Level.SEVERE,null, ex);
            }ttgl.setDate(tanggal);
                
            //menampilkan status
            if (tabel_karyawan.getValueAt(row,5).equals("Laki-laki")) {
                 laki.setSelected(true);
            } else {
                 perempuan.setSelected(true);
            }
            
            //menampilkan status perkawinan
            if (tabel_karyawan.getValueAt(row,10).equals("Belum Menikah")) {
                 rbm.setSelected(true);
            } else if (tabel_karyawan.getValueAt(row,10).equals("Menikah")) {
                 rmenikah.setSelected(true);
            } else {
                 rdujan.setSelected(true);
            }
    }
    
    private void Pencarian(){
        try{
            String sql="Select * From karyawan inner join jabatan on karyawan.kode_jabatan=jabatan.kode_jabatan where nama_karyawan like '%" +tcari.getText()+ "%'";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datakaryawan=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datakaryawan[x][0]=kon.rs.getString("nik");
                datakaryawan[x][1]=kon.rs.getString("nama_karyawan");
                datakaryawan[x][2]=kon.rs.getString("nama_jabatan");
                datakaryawan[x][3]=kon.rs.getString("status");
                datakaryawan[x][4]=kon.rs.getString("email");
                datakaryawan[x][5]=kon.rs.getString("jk");
                datakaryawan[x][6]=kon.rs.getString("tempat_lahir");
                datakaryawan[x][7]=kon.rs.getString("tgl_lahir");
                datakaryawan[x][8]=kon.rs.getString("alamat");
                datakaryawan[x][9]=kon.rs.getString("agama");
                datakaryawan[x][10]=kon.rs.getString("status_perkawinan");
                x++;
            }
            tabel_karyawan.setModel(new DefaultTableModel(datakaryawan,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void BacaTabelKaryawan(){
        try{
            String baca="Select * From karyawan inner join jabatan on karyawan.kode_jabatan=jabatan.kode_jabatan order by nama_karyawan asc";
            kon.rs=kon.st.executeQuery(baca);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datakaryawan=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datakaryawan[x][0]=kon.rs.getString("nik");
                datakaryawan[x][1]=kon.rs.getString("nama_karyawan");
                datakaryawan[x][2]=kon.rs.getString("nama_jabatan");
                datakaryawan[x][3]=kon.rs.getString("status");
                datakaryawan[x][4]=kon.rs.getString("email");
                datakaryawan[x][5]=kon.rs.getString("jk");
                datakaryawan[x][6]=kon.rs.getString("tempat_lahir");
                datakaryawan[x][7]=kon.rs.getString("tgl_lahir");
                datakaryawan[x][8]=kon.rs.getString("alamat");
                datakaryawan[x][9]=kon.rs.getString("agama");
                datakaryawan[x][10]=kon.rs.getString("status_perkawinan");
                x++;
            }
            tabel_karyawan.setModel(new DefaultTableModel(datakaryawan,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    void nonaktif(){
        tnik.setEnabled(false);
        tnama.setEnabled(false);
        temail.setEnabled(false);
        laki.setEnabled(false);
        perempuan.setEnabled(false);
        talamat.setEnabled(false);
        cagama.setEnabled(false);
        rbm.setEnabled(false);
        rmenikah.setEnabled(false);
        rdujan.setEnabled(false);
        ttempatlahir.setEnabled(false);
        ttgl.setEnabled(false);
        cjabatan.setEnabled(false);
        cstatus.setEnabled(false);
    }
    
    void aktif(){
        tnik.setEnabled(true);
        tnama.setEnabled(true);
        temail.setEnabled(true);
        laki.setEnabled(true);
        perempuan.setEnabled(true);
        talamat.setEnabled(true);
        cagama.setEnabled(true);
        rbm.setEnabled(true);
        rmenikah.setEnabled(true);
        rdujan.setEnabled(true);
        ttempatlahir.setEnabled(true);
        ttgl.setEnabled(true);
        cjabatan.setEnabled(true);
        cstatus.setEnabled(true);
    }
    
    void bersih(){
        tnik.setText("");
        tnama.setText("");
        temail.setText("");
        laki.setSelected(false);
        perempuan.setSelected(false);
        talamat.setText("");
        cagama.setSelectedItem("- PILIH -");
        rbm.setSelected(false);
        rmenikah.setSelected(false);
        rdujan.setSelected(false);
        ttempatlahir.setText("");
        cstatus.setSelectedItem("- PILIH -");
        cjabatan.setSelectedItem("- PILIH -");
        
        /*String tgl="0000/00/00";
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date tanggal=null;
        try{
            tanggal=date.parse("0000-00-00");
        }catch(ParseException ex){
            Logger.getLogger(karyawan.class.getName()).log(Level.SEVERE,null, ex);
        }ttgl.setDate(tanggal);*/
    }
    
    private String simpanJK(){
        String jk="";
        if(laki.isSelected()){
            jk="Laki-laki";
        }else{
            jk="Perempuan";
        }return jk;
    }
    
    private String simpanStatus(){
        String status="";
        if(rbm.isSelected()){
            status="Belum Menikah";
        }else if(rmenikah.isSelected()){
            status="Menikah";
        }else{
            status="Duda/Janda";
        }return status;
    }
    
    private String simpanJabatan(){
        String kode=null;
        try{
            String jab="select * from jabatan where nama_jabatan='"+cjabatan.getSelectedItem()+"'";
            kon.rs = kon.st.executeQuery(jab);
            while(kon.rs.next()){
            kode = kon.rs.getString("kode_jabatan");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }return kode;
    }
    
    private void SimpanData(){
        SimpleDateFormat date;
        date= new SimpleDateFormat("yyyy-MM-dd");
        try{
            String cek="select * from karyawan where nik='"+tnik.getText()+"'";
            kon.rs=kon.st.executeQuery(cek);
             if (kon.rs.next()){
                kon.rs.last();
                //nik = kon.rs.getString("nik");
                JOptionPane.showMessageDialog(null,"Data karyawan dengan NIK '"+tnik.getText()+"' sudah ada");
                 bersih();
             }else{
                 try{
                    String sql="insert into karyawan values('"+tnik.getText()+"','"+tnama.getText()+"','"+simpanJabatan()+"','"+cstatus.getSelectedItem()+"','"+temail.getText()+"','"+simpanJK()+"','"+ttempatlahir.getText()+"','"+date.format(ttgl.getDate())+"','"+talamat.getText()+"','"+cagama.getSelectedItem()+"','"+simpanStatus()+"')";
                    kon.st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
                    bersih();
                    BacaTabelKaryawan();
                }
                catch(SQLException e){
                    JOptionPane.showMessageDialog(null,e);
                }
             }
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void UpdateData(){
        SimpleDateFormat date;
        date= new SimpleDateFormat("yyyy-MM-dd");
        try{
            String sql="Update karyawan set nama_karyawan='"+tnama.getText()+"',kode_jabatan='"+simpanJabatan()+"',status='"+cstatus.getSelectedItem()+"',email='"+temail.getText()+"',jk='"+simpanJK()+"',tempat_lahir='"+ttempatlahir.getText()+"',tgl_lahir='"+date.format(ttgl.getDate())+"',alamat='"+talamat.getText()+"',agama='"+cagama.getSelectedItem()+"',status_perkawinan='"+simpanStatus()+"' where nik='"+tnik.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            BacaTabelKaryawan();
            bersih();
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }
    
     private void HapusData(){
        try{
            String sql="Delete from karyawan where nik='"+tnik.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            bersih();
            BacaTabelKaryawan();
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

        jk = new javax.swing.ButtonGroup();
        status = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btutup = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tnik = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        temail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        talamat = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        laki = new javax.swing.JRadioButton();
        ttempatlahir = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cagama = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        perempuan = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rbm = new javax.swing.JRadioButton();
        rmenikah = new javax.swing.JRadioButton();
        rdujan = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        cjabatan = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cstatus = new javax.swing.JComboBox();
        bsimpan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_karyawan = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        bAll = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        btambah = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/datakaryawan.png"))); // NOI18N

        btutup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        btutup.setText("Tutup");
        btutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btutupActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Karyawan"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("NIK");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel3.setText("Nama Pegawai");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel4.setText("Email");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel5.setText("Tanggal lahir");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel6.setText("Alamat");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));
        jPanel2.add(tnik, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 102, -1));
        jPanel2.add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 174, -1));
        jPanel2.add(temail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 174, -1));

        talamat.setColumns(20);
        talamat.setRows(5);
        jScrollPane1.setViewportView(talamat);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 193, 60));

        jLabel7.setText("Jenis Kelamin");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        laki.setBackground(new java.awt.Color(255, 255, 255));
        jk.add(laki);
        laki.setText("Laki - Laki");
        jPanel2.add(laki, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, -1));
        jPanel2.add(ttempatlahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 174, -1));

        jLabel8.setText("Tempat Lahir");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        cagama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "Islam", "Prostestan", "Katolik", "Hindu", "Budha", "Lainnya" }));
        jPanel2.add(cagama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 175, -1));

        jLabel9.setText("Agama");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        perempuan.setBackground(new java.awt.Color(255, 255, 255));
        jk.add(perempuan);
        perempuan.setText("Perempuan");
        jPanel2.add(perempuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        jLabel10.setText("Status Perkawinan");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 120, -1));

        rbm.setBackground(new java.awt.Color(255, 255, 255));
        status.add(rbm);
        rbm.setText("Belum Menikah");
        jPanel2.add(rbm, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, 20));

        rmenikah.setBackground(new java.awt.Color(255, 255, 255));
        status.add(rmenikah);
        rmenikah.setText("Menikah");
        jPanel2.add(rmenikah, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, -1, -1));

        rdujan.setBackground(new java.awt.Color(255, 255, 255));
        status.add(rdujan);
        rdujan.setText("Duda/Janda");
        jPanel2.add(rdujan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, -1, -1));

        jLabel11.setText("Jabatan");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        cjabatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        jPanel2.add(cjabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 170, -1));

        jLabel12.setText("Status");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        cstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -", "Honorer", "Karyawan Tetap" }));
        cstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cstatusActionPerformed(evt);
            }
        });
        jPanel2.add(cstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 170, -1));

        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simpan.png"))); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        tabel_karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_karyawanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_karyawan);

        jLabel13.setText("Cari berdasarkan Nama Karyawan :");

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tcariKeyPressed(evt);
            }
        });

        bCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N
        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        bAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        bAll.setText("Tampilkan Semua");
        bAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAllActionPerformed(evt);
            }
        });

        bedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        bedit.setText("Edit");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });

        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remove.png"))); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        btambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        btambah.setText("Tambah");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btambah)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btutup, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bAll)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCari)
                            .addComponent(bAll))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btambah, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(bsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bedit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(bhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btutup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1079, 642));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        bersih();
        nonaktif();
        btutup.setText("Tutup");
        btambah.setEnabled(true);
        bsimpan.setEnabled(false);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void btutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btutupActionPerformed
        // TODO add your handling code here:
        String tutup=btutup.getText();
        if(tutup.equals("Batal")){
           bersih();
           nonaktif();
           bsimpan.setText("Simpan");
           bsimpan.setEnabled(false);
           btambah.setEnabled(true);
           bedit.setEnabled(false);
           bhapus.setEnabled(false);
           btutup.setText("Tutup");
        }else{
        menuutama mu=new menuutama();
        mu.setVisible(true);
        this.dispose() ;
        }
    }//GEN-LAST:event_btutupActionPerformed

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        // TODO add your handling code here:
        bersih();
        aktif();
        tnik.requestFocus();
        btutup.setText("Batal");
        btambah.setEnabled(false);
        bsimpan.setEnabled(true);
        bsimpan.setText("Simpan");
        
    }//GEN-LAST:event_btambahActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here:
        String simpan=bsimpan.getText();
        if(simpan.equals("Simpan")){
            if (tnik.getText().isEmpty() || tnama.getText().isEmpty() || temail.getText().isEmpty() || ttempatlahir.getText().isEmpty() || talamat.getText().isEmpty() || cagama.getSelectedItem().equals("-PILIH-") || cjabatan.getSelectedItem().equals("- PILIH -") || cstatus.getSelectedItem().equals("- PILIH -")) {
                JOptionPane.showMessageDialog(this, "Lengkapi Data", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
            SimpanData();
            nonaktif();
            btutup.setText("Tutup");
            btambah.setEnabled(true);
            bsimpan.setEnabled(false);
            }
        }else{
            UpdateData();
            nonaktif();
            bsimpan.setText("Simpan");
            bsimpan.setEnabled(false);
            btambah.setEnabled(true);
            bedit.setEnabled(false);
            bhapus.setEnabled(false);
            btutup.setText("Tutup");
        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void tabel_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_karyawanMouseClicked
        // TODO add your handling code here:
        setTable();
        bedit.setEnabled(true);
        btambah.setEnabled(false);
        btutup.setText("Batal");
        bhapus.setEnabled(true);
    }//GEN-LAST:event_tabel_karyawanMouseClicked

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Pencarian();
        }
    }//GEN-LAST:event_tcariKeyPressed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        Pencarian();
    }//GEN-LAST:event_bCariActionPerformed

    private void bAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAllActionPerformed
        // TODO add your handling code here:
        BacaTabelKaryawan();
    }//GEN-LAST:event_bAllActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        // TODO add your handling code here:
        aktif();
        bsimpan.setEnabled(true);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
        bsimpan.setText("Update");
        btutup.setText("Batal");
        tnama.requestFocus();
    }//GEN-LAST:event_beditActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "yakin mau dihapus?", "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            HapusData();
        }else{
            JOptionPane.showMessageDialog(this, "Data Batal Dihapus", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        formWindowOpened(null);
    }//GEN-LAST:event_bhapusActionPerformed

    private void cstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cstatusActionPerformed

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
            java.util.logging.Logger.getLogger(karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new karyawan().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAll;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btambah;
    private javax.swing.JButton btutup;
    public javax.swing.JComboBox cagama;
    private javax.swing.JComboBox cjabatan;
    private javax.swing.JComboBox cstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.ButtonGroup jk;
    public javax.swing.JRadioButton laki;
    public javax.swing.JRadioButton perempuan;
    public javax.swing.JRadioButton rbm;
    public javax.swing.JRadioButton rdujan;
    public javax.swing.JRadioButton rmenikah;
    private javax.swing.ButtonGroup status;
    public javax.swing.JTable tabel_karyawan;
    public javax.swing.JTextArea talamat;
    private javax.swing.JTextField tcari;
    public javax.swing.JTextField temail;
    public javax.swing.JTextField tnama;
    public javax.swing.JTextField tnik;
    public javax.swing.JTextField ttempatlahir;
    // End of variables declaration//GEN-END:variables
}
