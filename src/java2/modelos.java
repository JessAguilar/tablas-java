/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2;


import com.oracle.jrockit.jfr.DataType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java2.modelos;
import java.sql.Statement;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Delux
 */
public class modelos {
    private static conexion conexion;
    private Connection con;
    
    public void select()
    {
        try {
            int id;
            String nombre;
            int edad;
            String res;
            String sql = "SELECT\n"
                    + "  id,\n"
                    + "  nombre,\n"
                    + "  edad\n"
                    + "FROM tabla.persona";
                    con = conexion.getInstance().getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next())
                    {   
                        id = rs.getInt(1);
                        nombre = rs.getString(2);
                        edad = rs.getInt(3);
                        System.out.println(id+" "+nombre+" "+edad);
                    }
                    con.commit();
                    con.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(modelos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void update(String nombre)
    {
        try {
            con = conexion.getInstance().getConnection();
            
            String sql = "UPDATE clase.persona \n"
                    + "SET\n"
                    + "  nombre = ?\n"
                    + "WHERE\n"
                    + "  id = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, nombre);
            pstm.setInt(2, 3);
            //executeUpdate usado tambien para deletes
            pstm.executeUpdate();
            pstm.close();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(modelos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String DatosSQL()
    {
        String total;
        total = "";
        try {
            int id;
            String nombre;
            int edad;
            String sql = "SELECT\n"
                    + "  id,\n"
                    + "  nombre,\n"
                    + "  edad\n"
                    + "FROM tabla.persona";
            con = conexion.getInstance().getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next())
                    {   
                        id = rs.getInt(1);
                        nombre = rs.getString(2);
                        edad = rs.getInt(3);
                        total+=(id+"\t"+nombre+"\t"+edad+"\n");
                    }
                    con.commit();
                    con.close();            
            
        } catch (SQLException ex) {
            Logger.getLogger(modelos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public DefaultTableModel Datostabla()
    {
        ResultSet variablex = null;
        DefaultTableModel andresito =new DefaultTableModel();
        String total;
        total = "";
        try {
            int id;
            String nombre;
            int edad;
            String sql = "SELECT\n"
                    + "  id,\n"
                    + "  nombre,\n"
                    + "  edad\n"
                    + "FROM tabla.persona";
            con = conexion.getInstance().getConnection();
                    Statement st = con.createStatement();
                    variablex = st.executeQuery(sql);
                   
                    andresito.setColumnIdentifiers(new Object[]{"id","nombre","edad"});
                    try{
                        while(variablex.next()){
                            andresito.addRow(new Object[]{variablex.getString(1),variablex.getString(2),variablex.getString(3)});
                        }   
                    } catch(Exception e){

                    }
                    
    
       
                    con.commit();
                    con.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(modelos.class.getName()).log(Level.SEVERE, null, ex);
        }
       return andresito;
    }
}

