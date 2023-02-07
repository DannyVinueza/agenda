import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public agenda() {
        nombre.setEnabled(false);
        apellido.setEnabled(false);
        email.setEnabled(false);
        actualizarB.setEnabled(false);
        buscarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;/*
                try{

                    con = getConection();
                    ps = con.prepareStatement("INSERT INTO registro (ID, NOMBRE, CELULAR, CORREO, DIRECCION, EDAD) VALUES (?, ?, ?, ?, ?, ?);");
                    ps.setString(1, TFid.getText());
                    ps.setString(2, TFnombre.getText());
                    ps.setString(3, TFcelular.getText());
                    ps.setString(4,TFcorreo.getText());
                    ps.setString(5,TFdireccion.getText());
                    ps.setString(6, TFedad.getText());
                    System.out.println(ps);//Imprime para ver los datos ingresados por consola

                    int res = ps.executeUpdate();
                    if(res > 0){
                        JOptionPane.showMessageDialog(null, "Persona Guardada");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al guardar");
                    }
                    con.close();
                }catch(HeadlessException | SQLException f){
                    System.err.println(f);
                }*/
            }
        });
    }

    public static Connection getConection(){
        Connection con = null;
        String base = "formulario";
        String url = "jdbc:mysql://localhost/" + base;
        String user = "root";
        String password = "1234";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        }catch(ClassNotFoundException | SQLException es){
            System.err.println(es);
        }
        return con;

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("pagendar");
        frame.setContentPane(new agenda().pagendar);
        frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
