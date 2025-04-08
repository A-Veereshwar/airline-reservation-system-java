package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame{
    
    public FlightInfo(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JTable table = new JTable();
        
        try{
            Conn connection = new Conn();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM flight");
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e){
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 700, 500);
        add(jsp);
        
        setSize(700, 500);
        setLocation(270,130);
        setVisible(true);
    }
    
    public static void main(String[] args){
        new FlightInfo();
    }
}
