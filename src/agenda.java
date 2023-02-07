import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class agenda {
    PreparedStatement ps;
    private JPanel pagendar;
    private JLabel titulo;
    private JTextField cedula;
    private JTextField nombre;
    private JTextField celular;
    private JTextField email;
    private JButton buscarB;
    private JButton actualizarB;


    public agenda() {
        nombre.setEnabled(false);
        celular.setEnabled(false);
        email.setEnabled(false);
        actualizarB.setEnabled(false);
        buscarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                try{
                    String cedulaIn;
                    cedulaIn = cedula.getText();
                    System.out.println(cedulaIn);
                    con = getConection();
                    String query = "select * from persona;";// WHERE Id = " + cedulaIn + ";";
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery(query);
                    System.out.println(rs);

                    while(rs.next()){
                        if(cedulaIn.equals(rs.getString(1))){
                            nombre.setText(rs.getString(2));
                            celular.setText(rs.getString(3));
                            email.setText(rs.getString(4));

                            System.out.println(rs.getString(1) + " " +
                                    rs.getString(2) + " " +
                                    rs.getString(3) + " " +
                                    rs.getString(4));
                            nombre.setEnabled(true);
                            celular.setEnabled(true);
                            email.setEnabled(true);
                            actualizarB.setEnabled(true);
                            break;
                        }else{
                            System.out.println("Persona no encontrada");
                            break;
                        }
                    }
                    con.close();
                }catch(HeadlessException | SQLException f){
                    System.err.println(f);
                }
            }
        });
        actualizarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;

                try{
                    con = getConection();
                    ps = con.prepareStatement("UPDATE persona SET id = ?, nombre = ?, celular = ?, correo = ? WHERE id ="+cedula.getText() );
                    ps.setString(1, cedula.getText());
                    ps.setString(2, nombre.getText());
                    ps.setString(3, celular.getText());
                    ps.setString(4, email.getText());
                    System.out.println(ps);//Imprime para ver los datos ingresados por consola

                    int res = ps.executeUpdate();
                    if(res > 0){
                        JOptionPane.showMessageDialog(null, "Actualizacion exitosa ");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al actualizar");
                    }
                    con.close();
                }catch(HeadlessException | SQLException f){
                    System.err.println(f);
                }
            }
        });
    }

    public static Connection getConection(){
        Connection con = null;
        String base = "agenda";
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
