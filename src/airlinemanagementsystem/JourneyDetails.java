package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener{
    JTable table;
    JLabel labelPnr;
    JTextField tfPnr;
    JButton fetchTicket;
    public JourneyDetails(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        labelPnr = new JLabel("PNR Details : ");
        labelPnr.setFont(new Font("Verdana", Font.PLAIN, 16));
        labelPnr.setBounds(40, 20, 150, 25);
        add(labelPnr);
        
        tfPnr = new JTextField();
        tfPnr.setBounds(200, 20, 150, 25);
        add(tfPnr);
        
        fetchTicket = new JButton("Fetch Ticket");
        fetchTicket.setBounds(350, 60, 150, 25);
        fetchTicket.addActionListener(this);
        add(fetchTicket);
        
        table = new JTable();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 850, 500);
        jsp.setBackground( Color.WHITE);
        add(jsp);
        
        setSize(850, 500);
        setLocation(270,130);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        
        try{
            String pnr = tfPnr.getText();
            Conn connection = new Conn();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM reservation WHERE pnr_number = ?");
            ps.setString(1, pnr);
            ResultSet rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args){
        new JourneyDetails();
    }
}
