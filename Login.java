import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField usernameInput;
    JPasswordField passwordInput;
    JButton reset,submit,close;
    public Login(){
        setTitle("Login");
        setLayout(null);




        JLabel username = new JLabel("Username : ");
        username.setBounds(20,20,100,20);
        add(username);

        usernameInput = new JTextField();
        usernameInput.setBounds(150,20,200,22);
        add(usernameInput);

        JLabel password = new JLabel("Password : ");
        password.setBounds(20,60,100,20);
        add(password);

        passwordInput = new JPasswordField();
        passwordInput.setBounds(150,60,200,22);
        add(passwordInput);

        reset = new JButton("Reset");
        reset.setBounds(40,120,120,20);
        add(reset);
        reset.addActionListener(this);

        submit = new JButton("Submit");
        submit.setBounds(220,120,120,20);
        add(submit);
        submit.addActionListener(this);

        close = new JButton("Close");
        close.setBounds(400,120,120,20);
        add(close);
        close.addActionListener(this);

        setBounds(350,124,900,614);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==reset){
            usernameInput.setText("");
            passwordInput.setText("");
        } else if (e.getSource()==submit) {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            try{
                Conn c = new Conn();
                String query = "SELECT * FROM login WHERE username = '"+username+"'AND password ='"+password+"'";
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    new Home();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Username or Password");

                }
            }catch(Exception ex){
                ex.printStackTrace();
            }

        } else if (e.getSource()==close) {
            setVisible(false);
        }
    }
}
