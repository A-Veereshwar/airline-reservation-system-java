package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class CancelTicket extends JFrame implements ActionListener{
    
    JTextField  tfPnr;
    JButton cancelTicket, fetchDetails;
    JLabel cancelNum, tfAadhar, tfName, tfDate, tfFlgCode;
    
    public CancelTicket(){
        Random random = new Random();
        int randCanNum = random.nextInt(1000000); 
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Cancellation");
        heading.setBounds(280, 15, 250, 25);
        heading.setFont(new Font("Veranda", Font.PLAIN, 28));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel labelPnr = new JLabel("PNR Number : ");
        labelPnr.setBounds(80, 60, 140, 25);
        labelPnr.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelPnr);
        
        tfPnr = new JTextField();
        tfPnr.setBounds(200, 60, 180 , 25);
        add(tfPnr);
        
        fetchDetails = new JButton("Fetch Data");
        fetchDetails.setBounds( 400, 60, 150, 25);
        fetchDetails.addActionListener(this);
        add(fetchDetails);
        
        JLabel labelName = new JLabel("Name : ");
        labelName.setBounds(80, 100, 140, 25);
        labelName.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelName);
        
        tfName = new JLabel();
        tfName.setBounds(180, 100, 300 , 25);
        add(tfName);
        
        JLabel labelAadhar = new JLabel("Aadhar : ");
        labelAadhar.setBounds(80, 140, 140, 25);
        labelAadhar.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelAadhar);
        
        tfAadhar = new JLabel();
        tfAadhar.setBounds(180, 140, 300 , 25);
        add(tfAadhar);
        
        JLabel flgCode = new JLabel("Flight Code : ");
        flgCode.setBounds(80, 180, 120, 25);
        flgCode.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(flgCode);
        
        tfFlgCode = new JLabel();
        tfFlgCode.setBounds(200, 180, 150, 25);
        add(tfFlgCode);
        
        JLabel labelDate = new JLabel("Date of Travel : ");
        labelDate.setBounds(80, 220, 150, 25);
        labelDate.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelDate);
        
        tfDate = new JLabel();
        tfDate.setBounds(230, 220, 150, 25);
        add(tfDate);
        
        
        JLabel labelCanNum = new JLabel("Cancellation Num : ");
        labelCanNum.setBounds(80, 260, 150, 25);
        labelCanNum.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelCanNum);
        
        cancelNum = new JLabel(""+randCanNum);
        cancelNum.setBounds(230, 260, 120, 25);
        add(cancelNum);
        
        cancelTicket = new JButton("Cancel Ticket");
        cancelTicket.setBounds( 300, 300, 150, 25);
        cancelTicket.addActionListener(this);
        add(cancelTicket);
        
        setSize(700, 640);
        setLocation(300, 10);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == fetchDetails){
            String pnrNum = tfPnr.getText();
            try{
                Conn connection = new Conn();
                String query = "SELECT * FROM reservation WHERE pnr_number = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, pnrNum);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    tfAadhar.setText(rs.getString("aadhar"));
                    tfName.setText(rs.getString("name"));
                    tfDate.setText(rs.getString("date"));
                    tfFlgCode.setText(rs.getString("flg_code"));
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid PNR Details");
                } 
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == cancelTicket){
            String pnrNum = tfPnr.getText();
            String name = tfName.getText();
            String canNum = cancelNum.getText();
            String flg_code = tfFlgCode.getText();
            String date = tfDate.getText();
            String insQuery = "INSERT INTO cancellations VALUES (?, ?, ?, ?, ?)";
            String delQuery = "DELETE FROM reservation WHERE pnr_number = ?";
            try{
                Conn connection = new Conn();
                PreparedStatement ps = connection.prepareStatement(insQuery);
                ps.setString(1, pnrNum);
                ps.setString(2, name);
                ps.setString(3, canNum);
                ps.setString(4, flg_code);
                ps.setString(5, date);
                ps.executeUpdate();
                
                PreparedStatement ps2 = connection.prepareStatement(delQuery);
                ps2.setString(1, pnrNum);
                ps2.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Cancellation Successfull");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
    }
    
    public static void main(String[] args){
        new CancelTicket();
    }
}
