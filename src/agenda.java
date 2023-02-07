import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class agenda {
    private JPanel pagendar;
    private JLabel titulo;
    private JTextField cedula;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField email;
    private JButton buscarB;
    private JButton actualizarB;

    public agenda(){
        getConection();

    }
    public static Connection getConection(){
        Connection con = null;
        String url = "jdbc:mysql://localhost/Agenda",
                user = "root",
                password = "UGPCUGR2002";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }


    public static void main(String[] args) {
        JFrame frame =new JFrame("Datos");

        frame.setContentPane(new agenda().pagendar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);
    }
}

