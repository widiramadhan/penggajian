/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;


import java.util.logging.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Widi Ramadhan
 */
public class splash extends javax.swing.JDialog {

    /**
     * Creates new form splash
     */
    public splash() {
        initComponents();
        setSize(646,309);
        setResizable(false);
    //    JP.setStringPainted(true);
        setUndecorated(true);
    }
    
    
    public JProgressBar getProgressBar() {
        return JP;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JP = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(JP);
        JP.setBounds(40, 220, 550, 20);

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel2.setText("Please Wait ...");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 190, 220, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loading.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 646, 309);

        setSize(new java.awt.Dimension(644, 307));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        splash pb = new splash();
        pb.setVisible(true);
        for(int i=0;i<=100;i++){
            try {
                pb.getProgressBar().setValue(i);
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(splash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        pb.dispose();
        menuutama mu = new menuutama();
        mu.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar JP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}