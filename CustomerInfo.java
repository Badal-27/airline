import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerInfo extends JFrame implements ActionListener {
    JButton idFetch;
    JLabel dbName,dbGender,dbNationality,dbDob,dbPhoneNo;
    JTextField inputId;
    JButton backToHome;
    CustomerInfo(){
        setBounds(330,150,900,600);
        setLayout(null);
        setTitle("Customer Details");
        JLabel h1 = new JLabel("Customer Details ");
        h1.setBounds(280,10,400,100);
        h1.setFont(new Font("arial", Font.BOLD,30));
        h1.setForeground(Color.MAGENTA);
        add(h1);

        JLabel id = new JLabel("Id Number : ");
        id.setBounds(20,110,120,30);
        id.setFont(new Font("Arial", Font.BOLD,15));
        add(id);
        inputId = new JTextField();
        inputId.setBounds(130,110,130,30);
        add(inputId);
        idFetch = new JButton("Fetch Details");
        idFetch.setBounds(280,110,108,30);
        add(idFetch);
        idFetch.addActionListener(this);

        JLabel name = new JLabel("Name : ");
        name.setBounds(20,170,120,30);
        name.setFont(new Font("Arial", Font.PLAIN,13));
        add(name);
        dbName = new JLabel();
        dbName.setBounds(170,170,120,30);
        add(dbName);

        JLabel gender = new JLabel("Gender : ");
        gender.setBounds(20,220,120,30);
        gender.setFont(new Font("Arial", Font.PLAIN,13));
        add(gender);
        dbGender = new JLabel();
        dbGender.setBounds(170,220,120,30);
        add(dbGender);

        JLabel nationality = new JLabel("Nationality : ");
        nationality.setBounds(20,270,120,30);
        nationality.setFont(new Font("Arial", Font.PLAIN,13));
        add(nationality);
        dbNationality = new JLabel();
        dbNationality.setBounds(170,270,120,30);
        add(dbNationality);

        JLabel dob = new JLabel("Date Of Birth : ");
        dob.setBounds(20,320,120,30);
        dob.setFont(new Font("Arial", Font.PLAIN,13));
        add(dob);
        dbDob = new JLabel();
        dbDob.setBounds(170,320,120,30);
        add(dbDob);

        JLabel phoneNo = new JLabel("Phone Number : ");
        phoneNo.setBounds(20,370,120,30);
        phoneNo.setFont(new Font("Arial", Font.PLAIN,13));
        add(phoneNo);
        dbPhoneNo = new JLabel();
        dbPhoneNo.setBounds(170,370,120,30);
        add(dbPhoneNo);

        backToHome = new JButton("Go Back To Home");
        backToHome.setBounds(60,450,180,30);
        add(backToHome);
        backToHome.addActionListener(this);
        setVisible(true);

    }
    public static void main(String[] args) {
        new CustomerInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==idFetch){
            try{
                Conn c = new Conn();
                String id = inputId.getText();
                String query = "SELECT * FROM customers WHERE id="+id;
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    String name = " "+rs.getString("name");
                    dbName.setText(name);
                    String gender = " "+rs.getString("gender");
                    dbGender.setText(gender);
                    String nationality = " "+rs.getString("nationality");
                    dbNationality.setText(nationality);
                    String dob = " "+rs.getString("dob");
                    dbDob.setText(dob);
                    String phoneno = " "+rs.getString("phoneNumber");
                    dbPhoneNo.setText(phoneno);
                }else{
                    JOptionPane.showMessageDialog(null,"No Details Found");
                }
            }catch(Exception ecx){
                JOptionPane.showMessageDialog(null,"Enter Valid ID Number");
                ecx.printStackTrace();
            }
        } else if (e.getSource()==backToHome) {
            dispose();
        }
    }

}
