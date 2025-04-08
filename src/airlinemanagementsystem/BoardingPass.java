package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BoardingPass extends JFrame implements ActionListener{
    
    JTextField  tfPnr;
    JButton fetchDetails;
    JLabel tfName, tfNationality, tfFlightName, tfFlightCode, source, dest, tfDate;
    public BoardingPass(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Boarding Pass");
        heading.setBounds(200, 15, 200, 25);
        heading.setFont(new Font("Veranda", Font.PLAIN, 28));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel labelPnr = new JLabel("Enter PNR : ");
        labelPnr.setBounds(50, 60, 100, 20);
        labelPnr.setFont(new Font("Veranda", Font.PLAIN, 14));
        add(labelPnr);
        
        tfPnr = new JTextField();
        tfPnr.setBounds(170, 60, 150 , 20);
        add(tfPnr);
        
        fetchDetails = new JButton("Get Ticket");
        fetchDetails.setBounds( 350, 60, 100, 20);
        fetchDetails.addActionListener(this);
        add(fetchDetails);
        
        JLabel labelName = new JLabel("Name : ");
        labelName.setBounds(50, 100, 100, 20);
        labelName.setFont(new Font("Veranda", Font.PLAIN, 14));
        add(labelName);
        
        tfName = new JLabel();
        tfName.setBounds(150, 100, 150 , 20);
        add(tfName);
        
        JLabel labelNationality = new JLabel("Nationality : ");
        labelNationality.setBounds(50, 140, 140, 20);
        labelNationality.setFont(new Font("Veranda", Font.PLAIN, 14));
        add(labelNationality);
        
        tfNationality = new JLabel();
        tfNationality.setBounds(150, 140, 150 , 20);
        add(tfNationality);
        
        JLabel labelSource = new JLabel("Source : ");
        labelSource.setBounds(50, 180, 100, 20);
        labelSource.setFont(new Font("Veranda", Font.PLAIN, 14));
        add(labelSource);
        
        source = new JLabel();
        source.setBounds(150, 180, 120, 20);
        add(source);
        
        JLabel labelDestination = new JLabel("Destination : ");
        labelDestination.setBounds(270, 180, 100, 20);
        labelDestination.setFont(new Font("Veranda", Font.PLAIN, 14));
        add(labelDestination);
        
        dest = new JLabel();
        dest.setBounds(370, 180, 120, 20);
        add(dest);
        
        JLabel labelFlightName = new JLabel("FlightName : ");
        labelFlightName.setBounds(50, 220, 100, 20);
        labelFlightName.setFont(new Font("Veranda", Font.PLAIN, 14));
        add(labelFlightName);
        
        tfFlightName = new JLabel();
        tfFlightName.setBounds(150, 220, 120 , 20);
        add(tfFlightName);
        
        JLabel labelFlightCode = new JLabel("FlightCode : ");
        labelFlightCode.setBounds(270, 220, 100, 20);
        labelFlightCode.setFont(new Font("Veranda", Font.PLAIN, 14));
        add(labelFlightCode);
        
        tfFlightCode = new JLabel();
        tfFlightCode.setBounds(370, 220, 120 , 20);
        add(tfFlightCode);
        
        JLabel labelDate = new JLabel("Date of Travel : ");
        labelDate.setBounds(50, 260, 120, 20);
        labelDate.setFont(new Font("Veranda", Font.PLAIN, 14));
        add(labelDate);
        
        tfDate = new JLabel();
        tfDate.setBounds(170, 260, 120 , 20);
        add(tfDate);
        
        setSize(600, 450);
        setLocation(300, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == fetchDetails){
            String pnr = tfPnr.getText();
      
            try{
                Conn conn = new Conn();
                String query = "SELECT * FROM reservation WHERE pnr_number = ?;";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, pnr);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    tfName.setText(rs.getString("name"));
                    tfNationality.setText(rs.getString("nationality"));
                    tfFlightName.setText(rs.getString("flg_name"));
                    tfFlightCode.setText(rs.getString("flg_code"));
                    source.setText(rs.getString("flg_src"));
                    dest.setText(rs.getString("flg_dest"));
                    tfDate.setText(rs.getString("date"));
                }else{
                    JOptionPane.showMessageDialog(null, "Unable to Fetch Ticket Details");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        new BoardingPass();
    }
}
