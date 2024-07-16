import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class FlightInfo extends JFrame implements ActionListener {

    JButton backToHome;
    public FlightInfo(){
        setApplicationStyle();
        JPanel panel = new JPanel();
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent automatic resizing
        //        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane= new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBounds(30,30,700,600);
        add(panel);

        table.setGridColor(Color.LIGHT_GRAY);

        //Designing Part
        table.setSelectionBackground(Color.DARK_GRAY);
        table.setSelectionForeground(Color.WHITE);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
//        header.setBackground(Color.GRAY);
//        header.setForeground(Color.WHITE);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM flights");
            int columnCount = rs.getMetaData().getColumnCount();
            for(int i=1;i<=columnCount;i++){
                tableModel.addColumn(rs.getMetaData().getColumnName(i));
            }
            while(rs.next()){
                Object[] row = new Object[columnCount];
                for(int i=1;i<=columnCount;i++){
                    row[i-1] = rs.getObject(i);
                }
                tableModel.addRow(row);
                // Increase column width
                TableColumnModel columnModel = table.getColumnModel();
                for (int column = 0; column < table.getColumnCount(); column++) {
                    columnModel.getColumn(column).setPreferredWidth(70); // Set preferred column width
                }
                table.setRowHeight(30); // Set preferred row height

            }
        }catch(Exception exc){
            exc.printStackTrace();
        }

        backToHome = new JButton("Back To Home");
        backToHome.setBounds(90,650,100,30);
        backToHome.addActionListener(this);
        panel.add(backToHome);
        setLocationRelativeTo(null);
        setTitle("Flight Info");
        setBounds(370,100,800,800);
        setVisible(true);
    }
    public static void main(String[] args) {
        new FlightInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backToHome){
            dispose();
        }
    }

    private static void setApplicationStyle() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exce) {
            //java.util.logging.Logger.getLogger(PokeboardUI.class.getName()).log(java.util.logging.Level.INFO, null, ex);
            exce.printStackTrace();
        }
    }
}
