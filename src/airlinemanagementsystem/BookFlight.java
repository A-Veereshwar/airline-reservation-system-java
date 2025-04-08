package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener{
    
    JTextField  tfAadhar;
    JDateChooser calDate;
    Choice source, dest;
    JButton bookFlight, flight, fetchDetails;
    JLabel tfName, tfNationality, tfAddress, tfPhone, tfGender, tfFlightName, tfFlightCode;
    public BookFlight(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(270, 15, 150, 25);
        heading.setFont(new Font("Veranda", Font.PLAIN, 28));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel labelAadhar = new JLabel("Aadhar : ");
        labelAadhar.setBounds(80, 60, 140, 25);
        labelAadhar.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelAadhar);
        
        tfAadhar = new JTextField();
        tfAadhar.setBounds(180, 60, 180 , 25);
        add(tfAadhar);
        
        fetchDetails = new JButton("Fetch Data");
        fetchDetails.setBounds( 380, 60, 150, 25);
        fetchDetails.addActionListener(this);
        add(fetchDetails);
        
        JLabel labelName = new JLabel("Name : ");
        labelName.setBounds(80, 100, 140, 25);
        labelName.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelName);
        
        tfName = new JLabel();
        tfName.setBounds(180, 100, 300 , 25);
        add(tfName);
        
        JLabel labelNationality = new JLabel("Nationality : ");
        labelNationality.setBounds(80, 140, 140, 25);
        labelNationality.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelNationality);
        
        tfNationality = new JLabel();
        tfNationality.setBounds(180, 140, 300 , 25);
        add(tfNationality);
        
        JLabel labelAddress = new JLabel("Address : ");
        labelAddress.setBounds(80, 180, 140, 25);
        labelAddress.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelAddress);
        
        tfAddress = new JLabel();
        tfAddress.setBounds(180, 180, 300 , 25);
        add(tfAddress);
        
        JLabel labelPhone = new JLabel("Phone : ");
        labelPhone.setBounds(80, 220, 140, 25);
        labelPhone.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelPhone);
        
        tfPhone = new JLabel();
        tfPhone.setBounds(180, 220, 300 , 25);
        add(tfPhone);
        
        JLabel labelGender = new JLabel("Gender : ");
        labelGender.setBounds(80, 260, 140, 25);
        labelGender.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelGender);
        
        tfGender = new JLabel();
        tfGender.setBounds(180, 260, 300 , 25);
        add(tfGender);
        
        JLabel labelSource = new JLabel("Source : ");
        labelSource.setBounds(80, 300, 120, 25);
        labelSource.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelSource);
        
        source = new Choice();
        source.setBounds(200, 300, 150, 25);
        add(source);
        
        JLabel labelDestination = new JLabel("Destination : ");
        labelDestination.setBounds(80, 340, 120, 25);
        labelDestination.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelDestination);
        
        dest = new Choice();
        dest.setBounds(200, 340, 150, 25);
        add(dest);
        
        try{
            Conn connection = new Conn();
            String query = "SELECT * FROM flight";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                source.add(rs.getString("flg_src"));
                dest.add(rs.getString("flg_dest"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        flight = new JButton("Check Flights");
        flight.setBounds(370, 340, 150, 25);
        flight.addActionListener(this);
        add(flight);
        
        JLabel labelFlightName = new JLabel("FlightName : ");
        labelFlightName.setBounds(80, 380, 140, 25);
        labelFlightName.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelFlightName);
        
        tfFlightName = new JLabel();
        tfFlightName.setBounds(180, 380, 180 , 25);
        add(tfFlightName);
        
        JLabel labelFlightCode = new JLabel("FlightCode : ");
        labelFlightCode.setBounds(80, 420, 140, 25);
        labelFlightCode.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelFlightCode);
        
        tfFlightCode = new JLabel();
        tfFlightCode.setBounds(180, 420, 180 , 25);
        add(tfFlightCode);
        
        JLabel labelDate = new JLabel("Date of Travel : ");
        labelDate.setBounds(80, 460, 140, 25);
        labelDate.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelDate);
        
        calDate = new JDateChooser();
        calDate.setBounds(240, 460, 180 , 25);
        add(calDate);
        
        bookFlight = new JButton("Book Flight");
        bookFlight.setBounds(300, 520, 150, 25);
        bookFlight.addActionListener(this);
        add(bookFlight);
        
        setSize(700, 640);
        setLocation(300, 10);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == fetchDetails){
            String aadhar = tfAadhar.getText();
          
            try{
                Conn conn = new Conn();
                String query = "SELECT * FROM passenger WHERE aadhar = ?;";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, aadhar);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    tfName.setText(rs.getString("name"));
                    tfNationality.setText(rs.getString("nationality"));
                    tfAddress.setText(rs.getString("address"));
                    tfPhone.setText(rs.getString("phone"));
                    tfGender.setText(rs.getString("gender"));
                }else{
                    JOptionPane.showMessageDialog(null, "Unable to Fetch Passenger Details");
                }
                                
            } catch (Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == flight){
            String src = source.getSelectedItem();
            String dst = dest.getSelectedItem();
            try{
                Conn conn = new Conn();
                String query = "SELECT * FROM flight WHERE flg_src = ? AND flg_dest = ?;";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, src);
                ps.setString(2, dst);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    tfFlightName.setText(rs.getString("flg_name"));
                    tfFlightCode.setText(rs.getString("flg_code"));
                }else{
                    JOptionPane.showMessageDialog(null, "Unable to Fetch Flights");
                }
                                
            } catch (Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == bookFlight){
            
            Random random = new Random();
            int randPNR = random.nextInt(1000000);
            int randTIC = random.nextInt(10000);
            String name = tfName.getText();
            String aadhar = tfAadhar.getText();
            String nationality = tfNationality.getText();
            String gender = tfGender.getText();
            String flg_name = tfFlightName.getText();
            String flg_code = tfFlightCode.getText();
            String src = source.getSelectedItem();
            String dst = dest.getSelectedItem();
            String date = ((JTextField) calDate.getDateEditor().getUiComponent()).getText();
            
            try{
                Conn conn = new Conn();
                String query = "INSERT INTO reservation VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, "PNR-"+randPNR);
                ps.setString(2, "TIC-"+randTIC);
                ps.setString(3, name);
                ps.setString(4, aadhar);
                ps.setString(5, nationality);
                ps.setString(6, gender);
                ps.setString(7, flg_name);
                ps.setString(8, flg_code);
                ps.setString(9, src);
                ps.setString(10, dst);
                ps.setString(11, date);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Booking Successfull!");
                setVisible(false);
            } catch (Exception e){
                e.printStackTrace();
            }
        } 
    }
    
    public static void main(String[] args){
        new BookFlight();
    }
}
