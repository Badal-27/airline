import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class JourneyDetails extends JFrame implements ActionListener {
    JTextField pnrNoInput;
    JButton pnrFetch,backToHome;
    JLabel dbName,dbNationality,dbSource,dbDest,dbDot,dbFlightCode;
    public JourneyDetails(){
        JLabel h1 = new JLabel("JOURNEY DETAILS");
        h1.setBounds(350,20,200,40);
        h1.setFont(new Font("Tahoma",Font.BOLD,20));
        h1.setForeground(Color.MAGENTA);
        add(h1);

        JLabel pnrNoField = new JLabel("PNR Number : ");
        pnrNoField.setBounds(60,80,120,30);
        pnrNoField.setFont(new Font("Tahoma", Font.BOLD,15));
        add(pnrNoField);
        pnrNoInput = new JTextField();
        pnrNoInput.setBounds(190,80,150,30);
        add(pnrNoInput);
        pnrFetch = new JButton("Fetch Details");
        pnrFetch.setBounds(350,80,120,30);
        add(pnrFetch);
        pnrFetch.addActionListener(this);

        JLabel custName = new JLabel("Name : ");
        custName.setBounds(60,140,100,30);
        custName.setFont(new Font("Tahoma", Font.BOLD,15));
        add(custName);
        dbName = new JLabel();
        dbName.setBounds(190,140,180,30);
        add(dbName);

        JLabel custNationality = new JLabel("Nationality");
        custNationality.setBounds(60,200,100,30);
        custNationality.setFont(new Font("Tahoma", Font.BOLD,15));
        add(custNationality);
        dbNationality = new JLabel();
        dbNationality.setBounds(190,200,100,30);
        add(dbNationality);

        JLabel dot = new JLabel("Date : ");
        dot.setBounds(60,260,100,30);
        dot.setFont(new Font("Tahoma", Font.BOLD,15));
        add(dot);
        dbDot = new JLabel();
        dbDot.setBounds(190,260,100,30);
        add(dbDot);

        JLabel flightCode = new JLabel("Flight Code : ");
        flightCode.setBounds(60,320,120,30);
        flightCode.setFont(new Font("Tahoma", Font.BOLD,15));
        add(flightCode);
        dbFlightCode = new JLabel();
        dbFlightCode.setBounds(190,320,100,30);
        add(dbFlightCode);

//        cancelButton = new JButton("Cancel Ticket");
//        cancelButton.setBounds(120,380,120,30);
//        cancelButton.addActionListener(this);
//        add(cancelButton);

        backToHome = new JButton("Back To Home");
        backToHome.setBounds(260,480,120,30);
        backToHome.addActionListener(this);
        add(backToHome);


        JLabel source = new JLabel("Source : ");
        source.setBounds(60,380,120,30);
        source.setFont(new Font("Tahoma", Font.BOLD,15));
        add(source);
        dbSource = new JLabel();
        dbSource.setBounds(190,380,100,30);
        add(dbSource);

        JLabel dest = new JLabel("Destination : ");
        dest.setBounds(60,440,120,30);
        dest.setFont(new Font("Tahoma", Font.BOLD,15));
        add(dest);
        dbDest = new JLabel();
        dbDest.setBounds(190,440,100,30);
        add(dbDest);

        setSize(900,600);
        setLocation(310,100);
        setLayout(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        new JourneyDetails();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backToHome){
            dispose();
        }else{
            try{
                Conn c = new Conn();
                String query = "SELECT * FROM reservations WHERE pnrNo="+pnrNoInput.getText();
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    dbName.setText(rs.getString("name"));
                    dbNationality.setText(rs.getString("nationality"));
                    dbDot.setText(rs.getString("dot"));
                    dbFlightCode.setText(rs.getString("fcode"));
                    dbSource.setText(rs.getString("src"));
                    dbDest.setText(rs.getString("dest"));
                }else{
                    JOptionPane.showMessageDialog(null,"PNR Details are Incorrect");
                }
            }catch (Exception qe){
                JOptionPane.showMessageDialog(null,"Please Enter Valid PNR");
                qe.printStackTrace();
            }
        }
    }
}

