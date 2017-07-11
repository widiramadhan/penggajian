package penggajian;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.*;

public class login extends javax.swing.JFrame {
    koneksi kon=new koneksi();
    private static final int lebar=720;
    private static final int tinggi=430;
    
    public login() {
        initComponents();
        kon.setKoneksi();
        setSize(lebar,tinggi);
        setResizable(false);
        ImageIcon ico = new ImageIcon("src/img/logo.png");
        setIconImage(ico.getImage());
        
    }
    
    void bersih(){
        txtusername.setText("");
        txtpassword.setText("");
        txtusername.requestFocus();
    }

    private void proseslogin(){
        String username=txtusername.getText();
        String password=txtpassword.getText();
        username=txtusername.getText();
            try{
            String sql="Select * from pengguna where username='"+txtusername.getText()+"' AND password='"+txtpassword.getText()+"'";
            kon.rs=kon.st.executeQuery(sql);
            if(username.equals("")){
               JOptionPane.showMessageDialog(null, "Username anda masih kosong");
               txtusername.requestFocus();
            }else if(password.equals("")){
               JOptionPane.showMessageDialog(null, "Password anda masih kosong");
               txtpassword.requestFocus();
            }
            else if(kon.rs.next()){
                try{
                 String status="update pengguna set status='active' where username='"+txtusername.getText()+"'";
                 kon.st.executeUpdate(status);
                 JOptionPane.showMessageDialog(null, "Berhasil Login");
                 menuutama mu = new menuutama();
                         
                 mu.setVisible(true);
                 this.dispose();
                }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
                    
                }
            }
            else{
               JOptionPane.showMessageDialog(null, "Username atau Password Anda Salah !!!");
               bersih();
               txtusername.setEnabled(true);
               txtusername.requestFocus();
           } 

            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtusername = new javax.swing.JTextField();
        txtpassword = new javax.swing.JTextField();
        blogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("login"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtusername.setForeground(new java.awt.Color(153, 153, 153));
        txtusername.setBorder(null);
        txtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusernameKeyPressed(evt);
            }
        });
        getContentPane().add(txtusername);
        txtusername.setBounds(270, 150, 260, 30);

        txtpassword.setForeground(new java.awt.Color(153, 153, 153));
        txtpassword.setBorder(null);
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpasswordKeyPressed(evt);
            }
        });
        getContentPane().add(txtpassword);
        txtpassword.setBounds(270, 220, 260, 30);

        blogin.setBackground(new java.awt.Color(0, 102, 255));
        blogin.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        blogin.setForeground(new java.awt.Color(255, 255, 255));
        blogin.setText("LOGIN");
        blogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        blogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloginActionPerformed(evt);
            }
        });
        getContentPane().add(blogin);
        blogin.setBounds(430, 270, 110, 40);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("LOGIN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(190, 50, 140, 40);

        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Masukan Username dan Password Anda");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(190, 90, 340, 14);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 712, 424);

        setSize(new java.awt.Dimension(711, 396));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        bersih();
    }//GEN-LAST:event_formWindowActivated

    private void bloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloginActionPerformed
        proseslogin();
    }//GEN-LAST:event_bloginActionPerformed

    private void txtusernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusernameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtpassword.requestFocus();
        }
    }//GEN-LAST:event_txtusernameKeyPressed

    private void txtpasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            proseslogin();
        }
    }//GEN-LAST:event_txtpasswordKeyPressed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton blogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
