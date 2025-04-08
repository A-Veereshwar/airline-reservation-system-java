package airlinemanagementsystem;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Home extends JFrame implements ActionListener{
    
    public Home(){
        setLayout(null);
        
        ImageIcon imgMain = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/airplane.png"));
        JLabel img = new JLabel(imgMain);
        img.setBounds(0, 0, 1300, 750);
        add(img);
        
        JLabel heading = new  JLabel("Welcome to Garuda Airlines");
        heading.setBounds(420, 40, 800, 50);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Verdana", Font.PLAIN, 36));
        img.add(heading);
        
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu details = new JMenu("Details");
        menubar.add(details);
        
        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);
        
        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);
        
        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);
        
        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);
        
        JMenuItem cancelTicket = new JMenuItem("Cancel Ticket");
        cancelTicket.addActionListener(this);
        details.add(cancelTicket);
        
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        
        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        if(action.equals("Add Customer Details")){
            new AddCustomer();
        }else if(action.equals("Flight Details")){
            new FlightInfo();
        }else if(action.equals("Book Flight")){
            new BookFlight();
        }else if(action.equals("Journey Details")){
            new JourneyDetails();
        }else if(action.equals("Cancel Ticket")){
            new CancelTicket();
        }else if(action.equals("Boarding Pass")){
            new BoardingPass();
        }
    } 
    
    public static void main(String[] args){
        new Home();
    }
}
