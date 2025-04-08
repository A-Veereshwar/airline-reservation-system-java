package airlinemanagementsystem;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class Login extends JFrame implements ActionListener{
    JButton resetBtn, submitBtn, closeBtn;
    JTextField tfUsername;
    JPasswordField tfPassword;
    
    public Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel labelUsername = new JLabel("Username :");
        labelUsername.setBounds(20, 20, 100, 20);
        add(labelUsername);
        
        tfUsername = new JTextField();
        tfUsername.setBounds(130, 20, 200, 20);
        add(tfUsername);
        
        JLabel labelPassword = new JLabel("Password :");
        labelPassword.setBounds(20, 60, 100, 20);
        add(labelPassword);
        
        tfPassword = new JPasswordField();
        tfPassword .setBounds(130, 60, 200, 20);
        add(tfPassword );
        
        resetBtn = new JButton("Reset");
        resetBtn.setBounds(70, 120, 100, 20);
        resetBtn.addActionListener(this);
        add(resetBtn);
        
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(230, 120, 100, 20);
        submitBtn.addActionListener(this);
        add(submitBtn);
        
        closeBtn = new JButton("Close");
        closeBtn.setBounds(160, 150, 100, 20);
        closeBtn.addActionListener(this);
        add(closeBtn);
        
        setSize(400, 250);
        setLocation(450, 250);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == resetBtn){
            tfUsername.setText("");
            tfPassword.setText("");
        }else if(ae.getSource() == submitBtn){
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            try{
                Conn connection = new Conn();
                String query = "SELECT * FROM login WHERE username = ? and password = ?";
                PreparedStatement ps = connection.prepareStatement(query);                
                ps.setString(1, username);
                ps.setString(2, password);
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    new Home();
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    setVisible(false);
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else if(ae.getSource() == closeBtn){
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new Login();
    }
}
