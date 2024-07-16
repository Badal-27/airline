import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;

import com.toedter.calendar.JDateChooser;

public class BookTicket extends JFrame implements ActionListener {
    JLabel dbName, dbGender, dbNationality, dbFlightNo, dbFlightCode, flightNumber, flightCode;
    JButton idFetch, flightFetch;
    JTextField inputId;
    Choice sourceChoice, destinationChoice;
    JButton bookTicket;
    JDateChooser dcdate;
    HashSet<Integer> usedPNRs = new HashSet<>();
    HashSet<Integer> usedTIds = new HashSet<>();
    Random r = new Random();

    public BookTicket() {
        setLayout(null);
        JLabel h1 = new JLabel("Ticket Booking");
        h1.setBounds(430, 40, 280, 40);
        h1.setFont(new Font("Arial", Font.BOLD, 32));
        h1.setForeground(Color.RED);
        add(h1);

        JLabel id = new JLabel("Id Number : ");
        id.setBounds(60, 130, 160, 30);
        id.setFont(new Font("Arial", Font.BOLD, 17));
        add(id);
        inputId = new JTextField();
        inputId.setBounds(180, 130, 160, 30);
        add(inputId);
        idFetch = new JButton("Fetch Details");
        idFetch.setBounds(365, 130, 108, 30);
        add(idFetch);
        idFetch.addActionListener(this);

        JLabel name = new JLabel("Name");
        name.setBounds(60, 190, 160, 30);
        name.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(name);
        dbName = new JLabel();
        dbName.setBounds(180, 190, 160, 30);
        dbName.setFont(new Font("Comic Sans", Font.BOLD, 13));
        dbName.setForeground(Color.green);
        add(dbName);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(60, 240, 160, 30);
        gender.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(gender);
        dbGender = new JLabel();
        dbGender.setBounds(180, 240, 160, 30);
        dbGender.setFont(new Font("Tahoma", Font.BOLD, 13));
        add(dbGender);

        JLabel nationality = new JLabel("Nationality");
        nationality.setBounds(60, 290, 160, 30);
        nationality.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(nationality);
        dbNationality = new JLabel();
        dbNationality.setBounds(180, 290, 160, 30);
        dbNationality.setFont(new Font("Tahoma", Font.BOLD, 13));
        add(dbNationality);


        JLabel source = new JLabel("Source");
        source.setBounds(60, 340, 160, 30);
        source.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(source);
        sourceChoice = new Choice();
        sourceChoice.setBounds(230, 340, 100, 30);
        add(sourceChoice);


        JLabel destination = new JLabel("Destination");
        destination.setBounds(60, 390, 160, 30);
        destination.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(destination);
        destinationChoice = new Choice();
        destinationChoice.setBounds(230, 390, 100, 30);
        add(destinationChoice);

        flightFetch = new JButton("Fetch Flights");
        flightFetch.setBounds(360, 440, 120, 30);
        add(flightFetch);
        flightFetch.addActionListener(this);

        flightNumber = new JLabel("Flight No.");
        flightNumber.setBounds(60, 490, 160, 30);
        flightNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(flightNumber);
        dbFlightNo = new JLabel();
        dbFlightNo.setBounds(215, 490, 80, 30);
        dbFlightNo.setFont(new Font("Tahoma", Font.BOLD, 13));
        add(dbFlightNo);

        flightCode = new JLabel("Flight Code");
        flightCode.setBounds(60, 540, 160, 30);
        flightCode.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(flightCode);
        dbFlightCode = new JLabel();
        dbFlightCode.setBounds(215, 540, 80, 30);
        dbFlightCode.setFont(new Font("Tahoma", Font.BOLD, 13));
        add(dbFlightCode);

        JLabel dateOfTravel = new JLabel("Date Of Travel");
        dateOfTravel.setBounds(60, 440, 160, 30);
        dateOfTravel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(dateOfTravel);

        dcdate = new JDateChooser();
        dcdate.setBounds(224, 440, 100, 30);
        add(dcdate);

        bookTicket = new JButton("Book Ticket");
        bookTicket.setBounds(110, 590, 150, 30);
        add(bookTicket);
        bookTicket.addActionListener(this);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM flights";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                sourceChoice.add(rs.getString("src"));
                destinationChoice.add(rs.getString("dest"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }


        setBounds(207, 80, 1100, 700);
        setTitle("Ticket Booking");
        setVisible(true);
    }

    public static void main(String[] args) {
        new BookTicket();
    }

    public int generatePNR() {
        int pnr;
        do {
            pnr = r.nextInt(900000) + 100000;
        } while (usedPNRs.contains(pnr));
        usedPNRs.add(pnr);
        return pnr;
    }

    public int generateTId() {
        int tId;
        do {
            tId = r.nextInt(1000) + 1000;
        } while (usedTIds.contains(tId));
        usedTIds.add(tId);
        return tId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == idFetch) {
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM customers WHERE id=" + inputId.getText();
                if (inputId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid User Credentials");
                } else {
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                        dbName.setText(rs.getString("name"));
                        dbGender.setText(rs.getString("gender"));
                        dbNationality.setText(rs.getString("nationality"));
                    } else {
                        JOptionPane.showMessageDialog(null, "User Doesn't Exist");
                    }
                }

            } catch (Exception ecx) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Details");
                ecx.printStackTrace();
            }
        } else if (e.getSource() == flightFetch) {
            if (inputId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Passenger Details First");
            } else {
                String sourc = sourceChoice.getSelectedItem();
                String dest = destinationChoice.getSelectedItem();
                try {
                    Conn c = new Conn();
                    LocalDate date1 = dcdate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String actualDate = date1.format(ft);
                    String query = "SELECT * from flights WHERE src='" + sourc + "' AND dest='" + dest + "' AND dot='" + actualDate + "'";
                    System.out.println(query);
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                        String fno = rs.getString("fnumber");
                        dbFlightNo.setText(fno);
                        String fcode = rs.getString("fcode");
                        dbFlightCode.setText(fcode);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Flights Scheduled for the Selected Options");
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        } else if (e.getSource() == bookTicket) {
            try {
                String id = inputId.getText();
                String name = dbName.getText();
                String nationality = dbNationality.getText();
                String fnumber = dbFlightNo.getText();
                String fcode = dbFlightCode.getText();
                String source = sourceChoice.getSelectedItem();
                String destination = destinationChoice.getSelectedItem();
//            String date = ((JTextField)(dcdate.getDateEditor().getUiComponent())).getText();
                LocalDate date1 = dcdate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String actualDate = date1.format(ft);
                System.out.println(actualDate);
                int pnr = generatePNR();
                int tId = generateTId();
                try {
                    Conn c = new Conn();
                    String query = "SELECT * FROM reservations WHERE id= " + id + " AND dot= '" + actualDate + "'";
                    ResultSet a = c.s.executeQuery(query);
                    if (a.next()) {
                        JOptionPane.showMessageDialog(null, "Booking Already Done On That Date");
                    } else {
                        try {
                            System.out.println(actualDate);
                            String query1 = "INSERT INTO reservations VALUES (" + pnr + "," + tId + "," + id + ",'" + name + "','" + nationality + "'," + fnumber + ",'" + fcode + "','" + source + "','" + destination + "','" + actualDate + "')";
                            System.out.println(query1);
                            c.s.executeUpdate(query1);
                            JOptionPane.showMessageDialog(null, "Ticket Successfully Booked");
                            dispose();
                        } catch (Exception ea) {
                            JOptionPane.showMessageDialog(null, "Something Went Wrong...EXITING");
                            setVisible(false);
                            ea.printStackTrace();
                        }
                    }

                } catch (Exception a) {
                    a.printStackTrace();
                }
            } catch (Exception ae) {
                JOptionPane.showMessageDialog(null, "Invalid Operation");
                ae.printStackTrace();
            }
        }
    }
}
