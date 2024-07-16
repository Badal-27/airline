import java.sql.*;

public class Conn {
    Connection con;
    Statement s;
    public Conn(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline","root","pass");
            s = con.createStatement();

        }catch(Exception e){
            System.out.println("Something Went Wrong");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}
