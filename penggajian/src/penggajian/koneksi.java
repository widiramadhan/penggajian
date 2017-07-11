/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Widi Ramadhan
 */
public class koneksi {
    Connection conn; 
    Statement st; 
    ResultSet rs;
    
public Connection setKoneksi() 
{ 
    try{ 
    Class.forName("com.mysql.jdbc.Driver"); 
    conn=DriverManager.getConnection("jdbc:mysql://localhost/penggajian","root",""); 
    st=conn.createStatement(); 
    } 
    catch(Exception e){ 
    JOptionPane.showMessageDialog(null,"Koneksi Gagal : " +e); 
    } 
    return conn; 
} 
}
