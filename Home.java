import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    JMenuItem addCustomerDetails,addFlightDetails,customerDetails,flightDetails,journeyDetails;
    JMenuItem bookTicket,cancelTicket,boardingPass;

    public Home(){
        setTitle("Welcome to AIR India");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("resources/background.jpg"));
        JLabel bgimage = new JLabel(i1);
        bgimage.setBounds(-50,-80,1728,1080);
        add(bgimage);

        JLabel h1 = new JLabel("Welcome to AIR India");
        h1.setBounds(600,200,600,100);
        h1.setForeground(Color.WHITE);
        h1.setFont(new Font("Futura", Font.BOLD,46));
        bgimage.add(h1);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu newDetails = new JMenu("New");
        menuBar.add(newDetails);

        addCustomerDetails = new JMenuItem("Add Customer Details");
        newDetails.add(addCustomerDetails);
        addCustomerDetails.addActionListener(this);
        addFlightDetails = new JMenuItem("Add Flight Details");
        newDetails.add(addFlightDetails);
        addFlightDetails.addActionListener(this);

        JMenu details = new JMenu("Details");
        menuBar.add(details);

        customerDetails = new JMenuItem("Customer Details");
        details.add(customerDetails);
        customerDetails.addActionListener(this);
        flightDetails = new JMenuItem("Flight Details");
        details.add(flightDetails);
        flightDetails.addActionListener(this);
        journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);

        JMenu ticket = new JMenu("Ticket");
        menuBar.add(ticket);

        bookTicket = new JMenuItem("Book Ticket");
        ticket.add(bookTicket);
        bookTicket.addActionListener(this);
        cancelTicket = new JMenuItem("Cancel Ticket");
        cancelTicket.addActionListener(this);
        ticket.add(cancelTicket);
        boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);


        setSize(1920,1024);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addFlightDetails){
            new AddFlight();
        } else if (e.getSource()==addCustomerDetails) {
            new AddCustomer();
        } else if (e.getSource()==customerDetails) {
            new CustomerInfo();
        } else if(e.getSource()==flightDetails){
            new FlightInfo();
        } else if (e.getSource()==bookTicket) {
            new BookTicket();
        } else if (e.getSource()==cancelTicket){
            new CancelTicket();
        } else if (e.getSource()==journeyDetails) {
            new JourneyDetails();
        }
    }
}
