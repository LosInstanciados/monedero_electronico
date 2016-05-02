/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monedero.electronico;

import db.Mysql;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author casa
 */
public class pagos extends JFrame implements ActionListener {

    private final JButton btnAceptar, btnCancelar, btnLimpiar;
    private final JTextField txtID, txtCantidad;

    Connection conn;
    Statement sent;

    public pagos() {
        //aqui va el nombre de la ventana
        super("Registra tu pago");
        conn = Mysql.getConnection();
        setBounds(200, 200, 500, 500);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creamos los botones,las labels y los text field asi como su posiciones y tamaño
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        btnLimpiar = new JButton("Limpiar");
        JLabel lblID = new JLabel("ID de Tarjeta: ");
        JLabel lblCantidad = new JLabel("Cantidad: ");
        txtID = new JTextField(20);
        txtCantidad = new JTextField(20);
        lblID.setBounds(10, 30, 120, 30);
        lblCantidad.setBounds(10, 100, 150, 30);
        txtID.setBounds(200, 30, 120, 30);
        txtCantidad.setBounds(200, 100, 120, 30);
        btnAceptar.setBounds(50, 180, 100, 50);
        btnCancelar.setBounds(200, 180, 100, 50);
        btnLimpiar.setBounds(350, 180, 100, 50);
        // creamos el panel y agregamos los botones, labels y  text fields
        JPanel jp = new JPanel();
        jp.setBounds(0, 0, 800, 800);
        jp.setLayout(null);
        this.setContentPane(jp);
        this.getContentPane().add(btnAceptar);
        this.getContentPane().add(btnCancelar);
        this.getContentPane().add(btnLimpiar);
        this.getContentPane().add(lblID);
        this.getContentPane().add(lblCantidad);
        this.getContentPane().add(txtID);
        this.getContentPane().add(txtCantidad);
        setVisible(true);
        this.repaint();

        //agregamos el actionlistener para que haga una accion si pretamos uno de los dos botones
        //si el usuario pulsa cancelar se cerrara el progra
        btnCancelar.addActionListener(this);
        //si le pulsa aceptar se desplegara este mensaje
        btnAceptar.addActionListener(this);
        btnLimpiar.addActionListener(this);

    }
    public void insertarDatos() {
        if ((txtID.getText().isEmpty()) || (txtCantidad.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben de estar llenos");
               /* }if (!txtCantidad.getText().toLowerCase().contentEquals(txtCantidad.getText()) || txtCantidad.getText().length() != 10 || !txtCantidad.getText().toUpperCase().contentEquals(txtCantidad.getText())) {
                        JOptionPane.showMessageDialog(null, "Sólo se adminten números en el campo de 'Cantiad' ");
                        txtCantidad.setText("");*/
                    } else {
                        try {

                            Statement comando = conn.createStatement();
                            Statement coman = conn.createStatement();
                            ResultSet registro = comando.executeQuery("select * from saldo where id_tarjeta=" + txtID.getText());
                            ResultSet verificarsaldo = coman.executeQuery("select * from saldo where Cantidad >=" + txtCantidad.getText());
                            if ((registro.next() == true) && (verificarsaldo.next() == true)) {
                                String sql = "insert into pagos(id_tarjeta, Cantidad)"
                                        + "values(?,?)";
                                PreparedStatement ps = conn.prepareCall(sql);
                                ps.setString(1, txtID.getText());
                                ps.setString(2, txtCantidad.getText());

                                int n = ps.executeUpdate();

                                String sql2 = "update saldo" + " set cantidad = cantidad - ?";
                                PreparedStatement update = conn.prepareStatement(sql2);
                                update.setString(1, txtCantidad.getText());
                                update.executeUpdate();
                                
                                if (n > 0) {
                                    JOptionPane.showMessageDialog(rootPane, txtID.getText()
                                            + "\n" + txtCantidad.getText() + "\nSe registró correctamente");
                                    JOptionPane.showMessageDialog(null, "Datos Guardados");

                                } else {
                                    JOptionPane.showMessageDialog(null, "ID INCORRECTA");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "ID incorrecta o saldo insuficiente");
                            }

                        } catch (SQLException ex) {
                            setTitle(ex.toString());
                        }
                    }
                }

     private void salir() {
        System.exit(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar){
            insertarDatos();
        }else  if(e.getSource() == btnCancelar){
            salir();
        }else if (e.getSource()== btnLimpiar){
            txtID.setText("");
            txtCantidad.setText("");
        }
    }

}
    

   

