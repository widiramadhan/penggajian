/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Widi Ramadhan
 */
public class jabatan extends javax.swing.JFrame {
    koneksi kon=new koneksi();
    private Object [][] datajabatan=null;
    private String[]label={"Kode Jabatan","Nama Jabatan","Tunjangan Jabatan","Gaji Pokok"};

    /**
     * Creates new form user
     */
    public jabatan() {
        initComponents();
        kon.setKoneksi();
        setSize(700,450);
        BacaTabelJabatan();
        tkdjabatan.setVisible(true);
    }
    
    private String cek_kode(){
        String kode=null;
        try{
            String sql = "Select * from jabatan where kode_jabatan='"+tkdjabatan.getText()+"'";
            ResultSet rs = kon.st.executeQuery(sql);
            if (rs.next()){
                rs.last();
                kode = rs.getString(1);
                JOptionPane.showMessageDialog(null,"Kode '"+tkdjabatan.getText()+"' Sudah Ada");
                Bersih();
                tkdjabatan.requestFocus();
            
            }else{
                SimpanData();
                Bersih();
                nonaktif();
                btambah.setEnabled(true);
                bKeluar.setText("Tutup");
                bsimpan.setEnabled(false);
                bedit.setEnabled(false);
                bhapus.setEnabled(false);
            }
        }catch (Exception e){     
        }return kode;
    }
    
    private void Bersih(){
        tkdjabatan.setText("");
        tnamajabatan.setText("");
        ttunjab.setText("");
        tgaji.setText("");
        }
    
    private void aktif(){
        tkdjabatan.setEnabled(true);
        tnamajabatan.setEnabled(true);
        ttunjab.setEnabled(true);
        tgaji.setEnabled(true);
    }
    
    private void nonaktif(){
        tkdjabatan.setEnabled(false);
        tnamajabatan.setEnabled(false);
        ttunjab.setEnabled(false);
        tgaji.setEnabled(false);
    }
    
    private void SimpanData(){
        try{
            String sql="insert into jabatan values('"+tkdjabatan.getText()+"','"+tnamajabatan.getText()+"','"+ttunjab.getText()+"','"+tgaji.getText()+"')";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            Bersih();
            BacaTabelJabatan();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void UpdateData(){
        try{
            String sql="Update jabatan set kode_jabatan='"+tkdjabatan.getText()+"',nama_jabatan='"+tnamajabatan.getText()+"',tunjab='"+ttunjab.getText()+"',gajipokok='"+tgaji.getText()+"' where kode_jabatan='"+tkdjabatan.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            Bersih();
            BacaTabelJabatan();
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }

        private void HapusData(){
        try{
            String sql="Delete from jabatan where kode_jabatan='"+tkdjabatan.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            Bersih();
            BacaTabelJabatan();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void BacaTabelJabatan(){
        try{
            String sql="Select * From jabatan order by kode_jabatan asc";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datajabatan=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datajabatan[x][0]=kon.rs.getString("kode_jabatan");
                datajabatan[x][1]=kon.rs.getString("nama_jabatan");
                datajabatan[x][2]=kon.rs.getString("tunjab");
                datajabatan[x][3]=kon.rs.getString("gajipokok");
                x++;
            }
            tabel_jabatan.setModel(new DefaultTableModel(datajabatan,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

     private void Pencarian(){
        try{
            String sql="select * from jabatan where nama_jabatan like '%" +tCari.getText()+ "%' ";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datajabatan=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datajabatan[x][0]=kon.rs.getString("kode_jabatan");
                datajabatan[x][1]=kon.rs.getString("nama_jabatan");
                datajabatan[x][2]=kon.rs.getString("tunjab");
                datajabatan[x][3]=kon.rs.getString("gajipokok");
                 x++;
            }
            tabel_jabatan.setModel(new DefaultTableModel(datajabatan,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
          
        private void setTable(){
            int row=tabel_jabatan.getSelectedRow();
            tkdjabatan.setText((String)tabel_jabatan.getValueAt(row,0));
            tnamajabatan.setText((String)tabel_jabatan.getValueAt(row,1));
            ttunjab.setText((String)tabel_jabatan.getValueAt(row,2));
            tgaji.setText((String)tabel_jabatan.getValueAt(row,3));
            
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
        tabel_jabatan = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bsimpan = new javax.swing.JButton();
        bCari = new javax.swing.JButton();
        bAll = new javax.swing.JButton();
        tCari = new javax.swing.JTextField();
        tnamajabatan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tgaji = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tkdjabatan = new javax.swing.JTextField();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ttunjab = new javax.swing.JTextField();

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

        tabel_jabatan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_jabatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_jabatanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_jabatan);

        jLabel1.setText("Cari Berdasarkan Nama Jabatan :");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/datajabatan.png"))); // NOI18N

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

        jLabel3.setText("Nama Jabatan");

        jLabel4.setText("Gaji Pokok");

        jLabel7.setText("Kode Jabatan");

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

        jLabel5.setText("Tunjangan Jabatan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btambah)
                                    .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bKeluar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3)
                                    .addComponent(tnamajabatan)
                                    .addComponent(tkdjabatan, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addComponent(ttunjab)
                                    .addComponent(jLabel4)
                                    .addComponent(tgaji))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tCari, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bCari)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bAll, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
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
                            .addComponent(tkdjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tnamajabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ttunjab, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(709, 464));
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
        BacaTabelJabatan();
    }//GEN-LAST:event_bAllActionPerformed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        Pencarian();
    }//GEN-LAST:event_bCariActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here:
        String simpan=bsimpan.getText();
        if(simpan.equals("Simpan"))
            if (tkdjabatan.getText().isEmpty() || tnamajabatan.getText().isEmpty() || tgaji.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Lengkapi Data", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cek_kode();
        }else{
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

        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void tabel_jabatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_jabatanMouseClicked
        // TODO add your handling code here:
        setTable();
        bKeluar.setText("Batal");
        bhapus.setEnabled(true);
        bedit.setEnabled(true);
        btambah.setEnabled(false);
    }//GEN-LAST:event_tabel_jabatanMouseClicked

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        // TODO add your handling code here:
        aktif();
        tkdjabatan.requestFocus();
        bKeluar.setText("Batal");
        bsimpan.setEnabled(true);
        btambah.setEnabled(false);
        bedit.setEnabled(false);
        bhapus.setEnabled(false);
        tCari.setEnabled(false);
        bCari.setEnabled(false);
        bAll.setEnabled(false);
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
            java.util.logging.Logger.getLogger(jabatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jabatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jabatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jabatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jabatan().setVisible(true);
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField tCari;
    private javax.swing.JTable tabel_jabatan;
    private javax.swing.JTextField tgaji;
    private javax.swing.JTextField tkdjabatan;
    private javax.swing.JTextField tnamajabatan;
    private javax.swing.JTextField ttunjab;
    // End of variables declaration//GEN-END:variables
}