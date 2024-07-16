import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class CancelTicket extends JFrame implements ActionListener {
    HashSet<Integer> usedcNos = new HashSet<>();
    JButton cancelButton,pnrFetch,backToHome;
    JLabel dbFlightCode,dbName,dbDot,dbNo;
    JTextField pnrNoInput;
    public CancelTicket(){
        JLabel h1 = new JLabel("CANCELLATION");
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

        JLabel dot = new JLabel("Date : ");
        dot.setBounds(60,200,100,30);
        dot.setFont(new Font("Tahoma", Font.BOLD,15));
        add(dot);
        dbDot = new JLabel();
        dbDot.setBounds(190,200,100,30);
        add(dbDot);

        JLabel flightCode = new JLabel("Flight Code : ");
        flightCode.setBounds(60,260,120,30);
        flightCode.setFont(new Font("Tahoma", Font.BOLD,15));
        add(flightCode);
        dbFlightCode = new JLabel();
        dbFlightCode.setBounds(190,260,100,30);
        add(dbFlightCode);

        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(120,380,120,30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        backToHome = new JButton("Back To Home");
        backToHome.setBounds(260,380,120,30);
        backToHome.addActionListener(this);
        add(backToHome);


        JLabel cancelNo = new JLabel("Cancellation No. : ");
        cancelNo.setBounds(60,320,190,30);
        cancelNo.setFont(new Font("Tahoma", Font.BOLD,15));
        add(cancelNo);
        dbNo = new JLabel();
        dbNo.setBounds(220,320,100,30);
        add(dbNo);

        setSize(900,600);
        setLocation(310,100);
        setLayout(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        new CancelTicket();
    }
    public int generateNumber(){
        Random r = new Random();
        int cNo;
        do{
            cNo = r.nextInt(9000)+1000;
        }while(usedcNos.contains(cNo));
        usedcNos.add(cNo);
        return cNo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backToHome){
            dispose();
        }else{
            String pnrNo = pnrNoInput.getText();
            if(pnrNo.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please Enter PNR Number");
            }else{
                if(e.getSource()==pnrFetch){
                    try{
                        Conn c = new Conn();
                        String query = "SELECT * FROM reservations WHERE pnrNo="+pnrNo;
                        ResultSet rs = c.s.executeQuery(query);
                        if(rs.next()){
                            dbName.setText(rs.getString("name"));
                            dbDot.setText(rs.getString("dot"));
                            dbFlightCode.setText(rs.getString("fcode"));
                        }else{
                            JOptionPane.showMessageDialog(null,"No Details Matched");
                        }
                    }catch (Exception a){
                        JOptionPane.showMessageDialog(null,"Please Enter Valid PNR");
                        a.printStackTrace();
                    }
                }else if(e.getSource()==cancelButton){
                    try{
                        String name = dbName.getText();
                        String fCode = dbFlightCode.getText();
                        String dot = dbDot.getText();
                        String cNo = dbNo.getText();
                        dbNo.setText(""+generateNumber());
                        Conn c = new Conn();
                        String query1 = "INSERT INTO cancellations VALUES("+pnrNo+",'"+name+"','"+fCode+"','"+dot+"',"+cNo+")";
                        System.out.println(query1);
                        c.s.executeUpdate(query1);
                        String query2 = "DELETE FROM reservations WHERE pnrNo="+pnrNo;
                        c.s.executeUpdate(query2);
                        JOptionPane.showMessageDialog(null,"Ticket Cancelled Successfully");
                        dispose();

                    }catch(Exception b){
                        JOptionPane.showMessageDialog(null,"Something Went Wrong");
                        b.printStackTrace();
                    }
                }
            }
        }
    }
}
