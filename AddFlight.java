import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class AddFlight extends JFrame implements ActionListener {
    JButton submit,clearFlight,homeButton;
    JTextField inputFlightId,inputFlightCode,inputFlightSource,inputFlightDestination,inputFlightPrice;
    JDateChooser dcdate;
    public AddFlight(){
        setApplicationStyle();
        setTitle("Add Flight");
        setBounds(350,150,800,600);
        setLayout(null);

        JLabel h1 = new JLabel("ADD FLIGHT DETAILS");
        h1.setBounds(250,25,600,100);
        h1.setForeground(Color.RED);
        h1.setFont(new Font("Moserrati",Font.BOLD,25));
        add(h1);

        JLabel flightId = new JLabel("Flight ID ");
        flightId.setBounds(30,120,130,40);
        flightId.setFont(new Font("Arial",Font.BOLD,15));
        add(flightId);
        inputFlightId = new JTextField();
        inputFlightId.setBounds(190,120,200,35);
        add(inputFlightId);

        JLabel flightCode = new JLabel("Flight Code ");
        flightCode.setBounds(30,160,130,40);
        flightCode.setFont(new Font("Arial",Font.BOLD,15));
        add(flightCode);
        inputFlightCode = new JTextField();
        inputFlightCode.setBounds(190,160,200,35);
        add(inputFlightCode);

        JLabel flightSource = new JLabel("Flight Source ");
        flightSource.setBounds(30,200,130,40);
        flightSource.setFont(new Font("Arial",Font.BOLD,15));
        add(flightSource);
        inputFlightSource = new JTextField();
        inputFlightSource.setBounds(190,200,200,35);
        add(inputFlightSource);

        JLabel flightDestination = new JLabel("Flight DEST ");
        flightDestination.setBounds(30,240,130,40);
        flightDestination.setFont(new Font("Arial",Font.BOLD,15));
        add(flightDestination);
        inputFlightDestination = new JTextField();
        inputFlightDestination.setBounds(190,240,200,35);
        add(inputFlightDestination);

        JLabel flightPrice = new JLabel("Flight Price ");
        flightPrice.setBounds(30,280,130,40);
        flightPrice.setFont(new Font("Arial",Font.BOLD,15));
        add(flightPrice);
        inputFlightPrice = new JTextField();
        inputFlightPrice.setBounds(190,280,200,35);
        add(inputFlightPrice);

//        homeButton = new JButton("Back to Home");
//        homeButton.setBounds(30,350,120,30);
//        add(homeButton);

        clearFlight = new JButton("Clear");
        clearFlight.setBounds(180,430,80,30);
        add(clearFlight);
        clearFlight.addActionListener(this);

        submit = new JButton("Submit");
        submit.setBounds(280,430,80,30);
        add(submit);
        submit.addActionListener(this);

        homeButton = new JButton("Back to home");
        homeButton.setBounds(30,430,120,30);
        add(homeButton);
        homeButton.addActionListener(this);

        JLabel dateOfTravel = new JLabel("Date Of Travel");
        dateOfTravel.setBounds(30, 320, 160, 30);
        dateOfTravel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(dateOfTravel);

        dcdate = new JDateChooser();
        dcdate.setBounds(190, 320, 140, 30);
        add(dcdate);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddFlight();
    }

    public void clearFields(){
        inputFlightCode.setText("");
        inputFlightId.setText("");
        inputFlightSource.setText("");
        inputFlightDestination.setText("");
        inputFlightPrice.setText("");
    }
    private static void setApplicationStyle() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exce) {
            //java.util.logging.Logger.getLogger(PokeboardUI.class.getName()).log(java.util.logging.Level.INFO, null, ex);
            exce.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==clearFlight){
            clearFields();
        } else if (e.getSource()==submit) {
            try{
                int fnumber = Integer.parseInt(inputFlightId.getText());
                String fcode = inputFlightCode.getText();
                String source = inputFlightSource.getText();
                String dest = inputFlightDestination.getText();
                float price = Float.parseFloat(inputFlightPrice.getText());
                LocalDate date1 = dcdate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String actualDate = date1.format(ft);
                System.out.println(actualDate);
                String query = "INSERT INTO flights VALUES("+fnumber+",'"+fcode+"','"+source+"','"+dest+"',"+price+",+'"+actualDate+"')";
                System.out.println(query);
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Flight Details Added Sucessfully");
                dispose();
            }catch(Exception exc){
                JOptionPane.showMessageDialog(null,"You may be inserting invalid Details");
                exc.printStackTrace();
            }
        }else if(e.getSource()==homeButton){
            dispose();
        }
    }
}
