import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomer extends JFrame implements ActionListener {
    JButton submit,clear,homeButton;
    JTextField inputName,inputNationality,inputDob,inputId,inputPhone;
    JRadioButton male,female;
    public AddCustomer(){
        setTitle("Add Customer");
        setBounds(350,150,800,600);
        setLayout(null);

        JLabel h1 = new JLabel("ADD CUSTOMER DETAILS");
        h1.setBounds(250,25,600,100);
        h1.setForeground(Color.MAGENTA);
        h1.setFont(new Font("Moserrati",Font.BOLD,25));
        add(h1);

        JLabel name = new JLabel("Name");
        name.setBounds(30,120,130,40);
        name.setFont(new Font("Arial",Font.BOLD,15));
        add(name);
        inputName = new JTextField();
        inputName.setBounds(190,120,200,35);
        add(inputName);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(30,160,130,40);
        gender.setFont(new Font("Arial",Font.BOLD,15));
        add(gender);

        ButtonGroup genderGroup = new ButtonGroup();
        male = new JRadioButton("MALE");
        male.setBounds(190,167,80,25);
        male.setBackground(Color.white);
        add(male);
        female = new JRadioButton("FEMALE");
        female.setBounds(250,167,100,25);
        female.setBackground(Color.white);
        add(female);
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel nationality = new JLabel("Nationality");
        nationality.setBounds(30,200,130,40);
        nationality.setFont(new Font("Arial",Font.BOLD,15));
        add(nationality);
        inputNationality = new JTextField();
        inputNationality.setBounds(190,200,140,35);
        add(inputNationality);

        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(30,240,130,40);
        dob.setFont(new Font("Arial",Font.BOLD,15));
        add(dob);
        inputDob = new JTextField();
        inputDob.setBounds(190,240,200,35);
        add(inputDob);

        JLabel idNumber = new JLabel("ID Number");
        idNumber.setBounds(30,280,130,40);
        idNumber.setFont(new Font("Arial",Font.BOLD,15));
        add(idNumber);
        inputId = new JTextField();
        inputId.setBounds(190,280,200,35);
        add(inputId);

        JLabel phoneNumber = new JLabel("Phone Number");
        phoneNumber.setBounds(30,320,130,40);
        phoneNumber.setFont(new Font("Arial",Font.BOLD,15));
        add(phoneNumber);
        inputPhone = new JTextField();
        inputPhone.setBounds(190,320,200,35);
        add(inputPhone);

        homeButton = new JButton("Back To Home");
        homeButton.setBounds(30,400,120,30);
        add(homeButton);
        homeButton.addActionListener(this);

        submit = new JButton("SAVE");
        submit.setBounds(280,400,80,30);
        add(submit);
        submit.addActionListener(this);

        clear = new JButton("CLEAR");
        clear.setBounds(180,400,80,30);
        add(clear);
        clear.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddCustomer();
    }

    public void clearFields(){
        inputName.setText("");
        inputNationality.setText("");
        inputDob.setText("");
        inputId.setText("");
        inputPhone.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String user = inputName.getText();
            String gender;
            String nationality = inputNationality.getText();
            String dob = inputDob.getText();
            String id = inputId.getText();
            String phone  = inputPhone.getText();
            if(male.isSelected()){
                gender = "male";
            }else{
                gender = "female";
            }
            if(e.getSource()==submit){
                try{
                    Conn c = new Conn();
                    String query = "INSERT INTO customers VALUES('"+user+"','"+gender+"','"+nationality+"','"+dob+"','"+id+"','"+phone+"')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
                    dispose();
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(null,"You may be inserting invalid Details");
                    exc.printStackTrace();
                }

            }else if(e.getSource()==clear){
                clearFields();
            } else if (e.getSource()==homeButton) {
                dispose();
            }
        }catch (Exception a){
            JOptionPane.showMessageDialog(null,"You may be inserting invalid Details");
        }
    }
}
