/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Widi Ramadhan
 */
public class master extends javax.swing.JFrame {
    koneksi kon=new koneksi();
    /**
     * Creates new form tunjangan
     */
    public master() {
        initComponents();
        kon.setKoneksi();
    }
    
    private void nonaktif(){
        tlembur.setEnabled(false);
        talpa.setEnabled(false);
        tmakan.setEnabled(false);
        ttransport.setEnabled(false);
        tstatus_kawin.setEnabled(false);
        cstatus_kawin.setEnabled(false);
        beditlembur.setEnabled(false);
        beditalpa.setEnabled(false);
        beditmakan.setEnabled(false);
        bedittransport.setEnabled(false);
        beditkawin.setEnabled(false);
        bupdatelembur.setEnabled(false);
        bupdatealpa.setEnabled(false);
        bupdatemakan.setEnabled(false);
        bupdatetransport.setEnabled(false);
        bupdatekawin.setEnabled(false);
    }
    
    
    //STATUS PERKAWINAN
    private void tampilStatus(){
        try{
            String sql="select * from status order by status_perkawinan asc";
            kon.rs=kon.st.executeQuery(sql);
            while(kon.rs.next()){
                cstatus_kawin.addItem(kon.rs.getString("status_perkawinan"));
            }
        }catch(Exception e){
            
        }
    }
    
    //LEMBUR
    public void setLembur(){
        try{
            String sql="Select * from master";
            kon.rs=kon.st.executeQuery(sql);
            while(kon.rs.next()){
                int id_col=kon.rs.getInt("id_master");
                String honor=kon.rs.getString("honor_lembur");
                tlembur.setText(honor);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
        private void UpdateLembur(){
        try{
            String sql="Update master set honor_lembur='"+tlembur.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }
        
    //KETIDAKHADIRAN
    public void setAlpa(){
        try{
            String sql="Select * from master";
            kon.rs=kon.st.executeQuery(sql);
            while(kon.rs.next()){
                int id_col=kon.rs.getInt("id_master");
                String honor=kon.rs.getString("potongan_alpa");
                talpa.setText(honor);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
        private void UpdateAlpa(){
        try{
            String sql="Update master set potongan_alpa='"+talpa.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }    
        
    //UANG MAKAN
    public void setMakan(){
        try{
            String sql="Select * from master";
            kon.rs=kon.st.executeQuery(sql);
            while(kon.rs.next()){
                int id_col=kon.rs.getInt("id_master");
                String honor=kon.rs.getString("uang_makan");
                tmakan.setText(honor);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
        private void UpdateMakan(){
        try{
            String sql="Update master set uang_makan='"+tmakan.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }
        
    //UANG TRANSPORT
    public void setTransport(){
        try{
            String sql="Select * from master";
            kon.rs=kon.st.executeQuery(sql);
            while(kon.rs.next()){
                int id_col=kon.rs.getInt("id_master");
                String honor=kon.rs.getString("uang_transport");
                ttransport.setText(honor);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
        private void UpdateTransport(){
        try{
            String sql="Update master set uang_transport='"+ttransport.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }
     
    private void UpdateKawin(){
        try{
            String sql="Update status set tunjangan_status='"+tstatus_kawin.getText()+"' where status_perkawinan='"+cstatus_kawin.getSelectedItem()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
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
        jPanel2 = new javax.swing.JPanel();
        cstatus_kawin = new javax.swing.JComboBox();
        tstatus_kawin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        beditkawin = new javax.swing.JButton();
        bupdatekawin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tmakan = new javax.swing.JTextField();
        beditmakan = new javax.swing.JButton();
        bupdatemakan = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tlembur = new javax.swing.JTextField();
        beditlembur = new javax.swing.JButton();
        bupdatelembur = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ttransport = new javax.swing.JTextField();
        bedittransport = new javax.swing.JButton();
        bupdatetransport = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        talpa = new javax.swing.JTextField();
        beditalpa = new javax.swing.JButton();
        bupdatealpa = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/datatunjangan.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tunjangan Status Perkawinan"));

        cstatus_kawin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- PILIH -" }));
        cstatus_kawin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cstatus_kawinActionPerformed(evt);
            }
        });

        jLabel1.setText("Status Perkawinan :");

        jLabel3.setText("Besarnya tunjangan :");

        beditkawin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        beditkawin.setText("Edit");
        beditkawin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditkawinActionPerformed(evt);
            }
        });

        bupdatekawin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simpan.png"))); // NOI18N
        bupdatekawin.setText("Update");
        bupdatekawin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdatekawinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cstatus_kawin, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(beditkawin)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bupdatekawin))
                        .addComponent(tstatus_kawin, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cstatus_kawin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tstatus_kawin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beditkawin)
                    .addComponent(bupdatekawin))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Uang Makan"));

        jLabel4.setText("Uang Makan PerBulan :");

        beditmakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        beditmakan.setText("Edit");
        beditmakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditmakanActionPerformed(evt);
            }
        });

        bupdatemakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simpan.png"))); // NOI18N
        bupdatemakan.setText("Update");
        bupdatemakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdatemakanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(beditmakan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bupdatemakan))
                    .addComponent(tmakan, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tmakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beditmakan)
                    .addComponent(bupdatemakan))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Honor Lembur"));

        jLabel6.setText("Honor Lembur PerJam :");

        beditlembur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        beditlembur.setText("Edit");
        beditlembur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditlemburActionPerformed(evt);
            }
        });

        bupdatelembur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simpan.png"))); // NOI18N
        bupdatelembur.setText("Update");
        bupdatelembur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdatelemburActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(beditlembur)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bupdatelembur))
                    .addComponent(tlembur, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tlembur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beditlembur)
                    .addComponent(bupdatelembur))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Uang Transportasi"));

        jLabel7.setText("Uang Transportasi PerBulan :");

        bedittransport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        bedittransport.setText("Edit");
        bedittransport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bedittransportActionPerformed(evt);
            }
        });

        bupdatetransport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simpan.png"))); // NOI18N
        bupdatetransport.setText("Update");
        bupdatetransport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdatetransportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(bedittransport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bupdatetransport))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(ttransport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ttransport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bedittransport)
                    .addComponent(bupdatetransport))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ketidakhadiran"));

        jLabel8.setText("Potongan Ketidakhadiran PerHari :");

        beditalpa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        beditalpa.setText("Edit");
        beditalpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditalpaActionPerformed(evt);
            }
        });

        bupdatealpa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simpan.png"))); // NOI18N
        bupdatealpa.setText("Update");
        bupdatealpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdatealpaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(beditalpa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bupdatealpa))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(talpa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(talpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beditalpa)
                    .addComponent(bupdatealpa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        bedit.setText("Edit Data");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });

        bkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        bkeluar.setText("Keluar");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/banner.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator1))
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(734, 516));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void beditmakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditmakanActionPerformed
        // TODO add your handling code here:
        tmakan.setEnabled(true);
        tmakan.requestFocus();
        beditmakan.setEnabled(false);
        bupdatemakan.setEnabled(true);
    }//GEN-LAST:event_beditmakanActionPerformed

    private void bupdatemakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdatemakanActionPerformed
        // TODO add your handling code here:
        UpdateMakan();
        bupdatemakan.setEnabled(false);
        beditmakan.setEnabled(true);
        tmakan.setEnabled(false);
    }//GEN-LAST:event_bupdatemakanActionPerformed

    private void beditlemburActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditlemburActionPerformed
        // TODO add your handling code here:
        bupdatelembur.setEnabled(true);
        tlembur.setEnabled(true);
        beditlembur.setEnabled(false);
        tlembur.requestFocus();
    }//GEN-LAST:event_beditlemburActionPerformed

    private void bupdatelemburActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdatelemburActionPerformed
        // TODO add your handling code here:
        UpdateLembur();
        bupdatelembur.setEnabled(false);
        beditlembur.setEnabled(true);
        tlembur.setEnabled(false);
    }//GEN-LAST:event_bupdatelemburActionPerformed

    private void bedittransportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bedittransportActionPerformed
        // TODO add your handling code here:
        bupdatetransport.setEnabled(true);
        ttransport.setEnabled(true);
        bedittransport.setEnabled(false);
        ttransport.requestFocus();
    }//GEN-LAST:event_bedittransportActionPerformed

    private void bupdatetransportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdatetransportActionPerformed
        // TODO add your handling code here:
        UpdateTransport();
        bupdatetransport.setEnabled(false);
        bedittransport.setEnabled(true);
        ttransport.setEnabled(false);
    }//GEN-LAST:event_bupdatetransportActionPerformed

    private void beditalpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditalpaActionPerformed
        // TODO add your handling code here:
        bupdatealpa.setEnabled(true);
        talpa.setEnabled(true);
        beditalpa.setEnabled(false);
        talpa.requestFocus();
    }//GEN-LAST:event_beditalpaActionPerformed

    private void bupdatealpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdatealpaActionPerformed
        // TODO add your handling code here:
        UpdateAlpa();
        bupdatealpa.setEnabled(false);
        beditalpa.setEnabled(true);
        talpa.setEnabled(false);
    }//GEN-LAST:event_bupdatealpaActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        // TODO add your handling code here:
        String keluar=bkeluar.getText();
        if(keluar.equals("Batal")){
            nonaktif();
            setLembur();
            setAlpa();
            setMakan();
            setTransport(); 
            bedit.setEnabled(true);
            bkeluar.setText("Keluar");
        }else{
            menuutama mu=new menuutama();
            mu.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_bkeluarActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        // TODO add your handling code here:
        bedit.setEnabled(false);
        beditlembur.setEnabled(true);
        beditalpa.setEnabled(true);
        beditmakan.setEnabled(true);
        bedittransport.setEnabled(true);
        beditkawin.setEnabled(true);
        bkeluar.setText("Batal");
    }//GEN-LAST:event_beditActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        nonaktif();
        tampilStatus();
        setLembur();
        setAlpa();
        setMakan();
        setTransport();
    }//GEN-LAST:event_formWindowOpened

    private void beditkawinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditkawinActionPerformed
        // TODO add your handling code here:
        cstatus_kawin.setEnabled(true);
        beditkawin.setEnabled(false);
    }//GEN-LAST:event_beditkawinActionPerformed

    private void cstatus_kawinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cstatus_kawinActionPerformed
        // TODO add your handling code here:
        try{
            String cek="select * from status where status_perkawinan='"+cstatus_kawin.getSelectedItem()+"'";
            kon.rs=kon.st.executeQuery(cek);
            if (kon.rs.next()){
            int tunkel = kon.rs.getInt("tunjangan_status");
            tstatus_kawin.setText(Integer.toString(tunkel));
            tstatus_kawin.setEnabled(true);
            tstatus_kawin.requestFocus();
            bupdatekawin.setEnabled(true);
            }
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_cstatus_kawinActionPerformed

    private void bupdatekawinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdatekawinActionPerformed
        // TODO add your handling code here:
        UpdateKawin();
        bupdatekawin.setEnabled(false);
        beditkawin.setEnabled(true);
        tstatus_kawin.setEnabled(false);
    }//GEN-LAST:event_bupdatekawinActionPerformed

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
            java.util.logging.Logger.getLogger(master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new master().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton beditalpa;
    private javax.swing.JButton beditkawin;
    private javax.swing.JButton beditlembur;
    private javax.swing.JButton beditmakan;
    private javax.swing.JButton bedittransport;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton bupdatealpa;
    private javax.swing.JButton bupdatekawin;
    private javax.swing.JButton bupdatelembur;
    private javax.swing.JButton bupdatemakan;
    private javax.swing.JButton bupdatetransport;
    private javax.swing.JComboBox cstatus_kawin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField talpa;
    private javax.swing.JTextField tlembur;
    private javax.swing.JTextField tmakan;
    private javax.swing.JTextField tstatus_kawin;
    private javax.swing.JTextField ttransport;
    // End of variables declaration//GEN-END:variables
}
