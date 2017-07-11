/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import java.util.regex.*;

/**
 *
 * @author Widi Ramadhan
 */
public class user extends javax.swing.JFrame {
    koneksi kon=new koneksi();
    private Object [][] datapengguna=null;
    private String[]label={"Id Pengguna","Nama","Email","Username","Akses Level"};
    
    /**
     * Creates new form user
     */
    public user() {
        initComponents();
        kon.setKoneksi();
        setSize(700,480);
        BacaTabelPengguna();
        tid_pengguna.setVisible(true);
        //temail= new JFormattedTextField(new emailvalidasi());
    }
    
    private void validasiemail(){
          String email = temail.getText();
          Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
          Matcher m = p.matcher(email);
          boolean matchFound = m.matches();
          if (matchFound)
            SimpanData();
          else
            JOptionPane.showMessageDialog(null,"Alamat Email tidak Valid");
    }
    
    private String no_otomatis(){
        String no=null;
        try{
            String sql = "Select right(id_pengguna,3)+1 from pengguna ";
            ResultSet rs = kon.st.executeQuery(sql);
            if (rs.next()){
                rs.last();
                no = rs.getString(1);
                while (no.length()<5){
                    no="00"+no;
                    no="ADM-"+no;
                tid_pengguna.setText(no);    
                }
            }else{
                no="ADM-001";
                tid_pengguna.setText(no);    
            }
        }catch (Exception e){     
        }return no;
    }
    
    private void Bersih(){
        tid_pengguna.setText("");
        tnama.setText("");
        temail.setText("");
        tusername.setText("");
        tpassword.setText("");
        }
    
    private void aktif(){
        tnama.setEnabled(true);
        temail.setEnabled(true);
        tusername.setEnabled(true);
        tpassword.setEnabled(true);
    }
    
    private void nonaktif(){
        tid_pengguna.setEnabled(false);
        tnama.setEnabled(false);
        temail.setEnabled(false);
        tusername.setEnabled(false);
        tpassword.setEnabled(false);
    }
    
    private void SimpanData(){
        try{
            String sql="insert into pengguna values('"+tid_pengguna.getText()+"','"+tnama.getText()+"','"+temail.getText()+"','"+tusername.getText()+"','"+tpassword.getText()+"','admin','non-active')";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            Bersih();
            BacaTabelPengguna();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void UpdateData(){
        try{
            String sql="Update pengguna set id_pengguna='"+tid_pengguna.getText()+"',nama='"+tnama.getText()+"',email='"+temail.getText()+"',username='"+tusername.getText()+"',password='"+tpassword.getText()+"' where id_pengguna='"+tid_pengguna.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            Bersih();
            BacaTabelPengguna();
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }

        private void HapusData(){
        try{
            String sql="Delete from pengguna where id_pengguna='"+tid_pengguna.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            Bersih();
            BacaTabelPengguna();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void BacaTabelPengguna(){
        try{
            String sql="Select * From pengguna order by id_pengguna";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datapengguna=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datapengguna[x][0]=kon.rs.getString("id_pengguna");
                datapengguna[x][1]=kon.rs.getString("nama");
                datapengguna[x][2]=kon.rs.getString("email");
                datapengguna[x][3]=kon.rs.getString("username");
                datapengguna[x][4]=kon.rs.getString("hak_akses");
                x++;
            }
            tabel_pengguna.setModel(new DefaultTableModel(datapengguna,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

     private void Pencarian(){
        try{
            String sql="select * from pengguna where nama like '%" +tCari.getText()+ "%' ";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datapengguna=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datapengguna[x][0]=kon.rs.getString("id_pengguna");
                datapengguna[x][1]=kon.rs.getString("nama");
                datapengguna[x][2]=kon.rs.getString("email");
                datapengguna[x][3]=kon.rs.getString("username");
                datapengguna[x][4]=kon.rs.getString("hak_akses");
                 x++;
            }
            tabel_pengguna.setModel(new DefaultTableModel(datapengguna,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
          
        private void setTable(){
            int row=tabel_pengguna.getSelectedRow();
            tid_pengguna.setText((String)tabel_pengguna.getValueAt(row,0));
            tnama.setText((String)tabel_pengguna.getValueAt(row,1));
            temail.setText((String)tabel_pengguna.getValueAt(row,2));
            tusername.setText((String)tabel_pengguna.getValueAt(row,3));
            tpassword.setText((String)tabel_pengguna.getValueAt(row,4));
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
        bKeluar = new javax.swing.JButton();
        btambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pengguna = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bsimpan = new javax.swing.JButton();
        bCari = new javax.swing.JButton();
        bAll = new javax.swing.JButton();
        tCari = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tpassword = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tusername = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tid_pengguna = new javax.swing.JTextField();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        temail = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.white);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        bKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        bKeluar.setText("Keluar");
        bKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKeluarActionPerformed(evt);
            }
        });

        btambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        btambah.setText("Tambah");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        tabel_pengguna.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_pengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_penggunaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pengguna);

        jLabel1.setText("Cari Berdasarkan Nama Pengguna :");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/datapengguna.png"))); // NOI18N

        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simpan.png"))); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
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

        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tCariKeyPressed(evt);
            }
        });

        jLabel3.setText("Nama");

        jLabel4.setText("Email");

        jLabel5.setText("Nama User");

        jLabel6.setText("Kata Sandi");

        jLabel7.setText("Id Pengguna");

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

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/banner.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3)
                                    .addComponent(tnama)
                                    .addComponent(jLabel5)
                                    .addComponent(tpassword)
                                    .addComponent(jLabel6)
                                    .addComponent(tusername, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addComponent(tid_pengguna)
                                    .addComponent(temail))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bCari)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bsimpan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bKeluar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(43, 43, 43))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCari)
                            .addComponent(bAll)
                            .addComponent(tid_pengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(temail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tusername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(bKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(709, 472));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Bersih();
        nonaktif();
        bsimpan.setEnabled(false);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "yakin mau dihapus?", "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            HapusData();
            Bersih();
            nonaktif();
            bsimpan.setText("Simpan");
            bKeluar.setText("Keluar");
            btambah.setEnabled(true);
            bsimpan.setEnabled(false);
            bedit.setEnabled(false);
            bhapus.setEnabled(false);
            tCari.setEnabled(true);
            bCari.setEnabled(true);
            bAll.setEnabled(true);
        } else {

            JOptionPane.showMessageDialog(this, "Data Batal Dihapus", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            //btambah.setEnabled(true);
            return;
        }
        //formWindowActivated(null);
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        // TODO add your handling code here:
        aktif();
        bsimpan.setText("Update");
        bKeluar.setText("Batal");
        bsimpan.setEnabled(true);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
        tCari.setEnabled(false);
        bCari.setEnabled(false);
        bAll.setEnabled(false);

    }//GEN-LAST:event_beditActionPerformed

    private void bAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAllActionPerformed
        // TODO add your handling code here:
        BacaTabelPengguna();
    }//GEN-LAST:event_bAllActionPerformed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        Pencarian();
    }//GEN-LAST:event_bCariActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here:
        String simpan=bsimpan.getText();
        if(simpan.equals("Simpan"))
        if (tid_pengguna.getText().isEmpty() || tnama.getText().isEmpty() || temail.getText().isEmpty() || tusername.getText().isEmpty() || tpassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi Data", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String email = temail.getText();
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();
            if (matchFound){
                SimpanData();
                Bersih();
                nonaktif();
                bsimpan.setText("Simpan");
                bKeluar.setText("Keluar");
                btambah.setEnabled(true);
                bsimpan.setEnabled(false);
                bedit.setEnabled(false);
                bhapus.setEnabled(false);
                tCari.setEnabled(true);
                bCari.setEnabled(true);
                bAll.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null,"Alamat Email tidak Valid");
                temail.setText("");
                temail.requestFocus();
            }
        }else{
            String email = temail.getText();
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();
            if (matchFound){
                UpdateData();
                Bersih();
                nonaktif();
                bsimpan.setText("Simpan");
                bKeluar.setText("Keluar");
                btambah.setEnabled(true);
                bsimpan.setEnabled(false);
                bedit.setEnabled(false);
                bhapus.setEnabled(false);
                tCari.setEnabled(true);
                bCari.setEnabled(true);
                bAll.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null,"Alamat Email tidak Valid");
            }
        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void tabel_penggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_penggunaMouseClicked
        // TODO add your handling code here:
        setTable();
        bKeluar.setText("Batal");
        bhapus.setEnabled(true);
        bedit.setEnabled(true);
        btambah.setEnabled(false);
    }//GEN-LAST:event_tabel_penggunaMouseClicked

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        // TODO add your handling code here:
        aktif();
        tnama.requestFocus();
        bKeluar.setText("Batal");
        bsimpan.setEnabled(true);
        btambah.setEnabled(false);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
        tCari.setEnabled(false);
        bCari.setEnabled(false);
        bAll.setEnabled(false);
        no_otomatis();
    }//GEN-LAST:event_btambahActionPerformed

    private void bKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKeluarActionPerformed
        // TODO add your handling code here:
        String keluar=bKeluar.getText();
        if(keluar.equals("Batal")){
            Bersih();
            nonaktif();
            bsimpan.setText("Simpan");
            bKeluar.setText("Keluar");
            btambah.setEnabled(true);
            bsimpan.setEnabled(false);
            bedit.setEnabled(false);
            bhapus.setEnabled(false);
            tCari.setEnabled(true);
            bCari.setEnabled(true);
            bAll.setEnabled(true);
        }else{
            this.dispose();
            menuutama mu = new menuutama(); //habis ini berjalan langsung ke form login
            mu.setVisible(true);
        }
    }//GEN-LAST:event_bKeluarActionPerformed

    private void tCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Pencarian();
        }
    }//GEN-LAST:event_tCariKeyPressed

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
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user().setVisible(true);
            }
        });
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAll;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bKeluar;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField tCari;
    private javax.swing.JTable tabel_pengguna;
    private javax.swing.JFormattedTextField temail;
    private javax.swing.JTextField tid_pengguna;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tpassword;
    private javax.swing.JTextField tusername;
    // End of variables declaration//GEN-END:variables
}