package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddCustomer extends JFrame implements ActionListener{
    
    JTextField tfName, tfNationality, tfAadhar, tfAddress, tfPhone;
    JRadioButton rbMale, rbFemale;
    public AddCustomer(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(150, 15, 425, 25);
        heading.setFont(new Font("Veranda", Font.PLAIN, 28));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel labelName = new JLabel("Name : ");
        labelName.setBounds(80, 80, 140, 25);
        labelName.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelName);
        
        tfName = new JTextField();
        tfName.setBounds(180, 80, 300 , 25);
        add(tfName);
        
        JLabel labelNationality = new JLabel("Nationality : ");
        labelNationality.setBounds(80, 120, 140, 25);
        labelNationality.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelNationality);
        
        tfNationality = new JTextField();
        tfNationality.setBounds(180, 120, 300 , 25);
        add(tfNationality);
        
        JLabel labelAadhar = new JLabel("Aadhar : ");
        labelAadhar.setBounds(80, 160, 140, 25);
        labelAadhar.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelAadhar);
        
        tfAadhar = new JTextField();
        tfAadhar.setBounds(180, 160, 300 , 25);
        add(tfAadhar);
        
        JLabel labelAddress = new JLabel("Address : ");
        labelAddress.setBounds(80, 200, 140, 25);
        labelAddress.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelAddress);
        
        tfAddress = new JTextField();
        tfAddress.setBounds(180, 200, 300 , 25);
        add(tfAddress);
        
        JLabel labelPhone = new JLabel("Phone : ");
        labelPhone.setBounds(80, 280, 140, 25);
        labelPhone.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelPhone);
        
        tfPhone = new JTextField();
        tfPhone.setBounds(180, 280, 300 , 25);
        add(tfPhone);
        
        JLabel labelGender = new JLabel("Gender : ");
        labelGender.setBounds(80, 240, 140, 25);
        labelGender.setFont(new Font("Veranda", Font.PLAIN, 16));
        add(labelGender);
        
        ButtonGroup genderGroup = new ButtonGroup();
        
        rbMale = new JRadioButton("Male");
        rbMale.setBounds(180, 240, 100, 25);
        rbMale.setBackground(Color.WHITE);
        add(rbMale);
        
        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(290, 240, 100, 25);
        rbFemale.setBackground(Color.WHITE);
        add(rbFemale);
        
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        
        JButton save = new JButton("SAVE");
        save.setBounds(280, 320, 100, 25);
        save.addActionListener(this);
        add(save);
        
        setSize(650, 470);
        setLocation(300, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String name = tfName.getText();
        String nationality = tfNationality.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String aadhar = tfAadhar.getText();
        String gender = null;
        if(rbMale.isSelected()){
            gender = "Male";
        }else{
            gender = "Female";
        }
        try{
            Conn conn = new Conn();
            String query = "INSERT INTO passenger (name, nationality, phone, address, aadhar, gender) values( ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, nationality);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, aadhar);
            ps.setString(6, gender);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Succefully Added Passenger");
            setVisible(false);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new AddCustomer();
    }
}
