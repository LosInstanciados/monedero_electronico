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
 * @author Mariana Villegas
 */
public class adminConfiguracion extends JFrame implements ActionListener {

    private final JButton btnAceptar, btnCancelar, btnLimpiar, btnHabilitar;
    protected JTextField txtPorcentaje;
    protected JPasswordField id;
    protected final JFrame v = new JFrame("CONFIGURACION");
    int porcentaje;
    String cadena;
    Connection conn;
    Statement sent;

    public adminConfiguracion() {
        v.setSize(550, 250);  //Establecemos las dimensiones del formulario (ancho x alto)
        v.setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)

        conn = Mysql.getConnection();

        //Paso 1. Vamos a crear etiquetas
        JLabel lblpc = new JLabel("Porcentaje de compra");
        JLabel lblus = new JLabel("Id administrador");

        //Paso 2. Vamos a crear campos de texto
        txtPorcentaje = new JTextField(4);
        id = new JPasswordField(9);
        

        //Paso 3. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        btnLimpiar = new JButton("Limpiar");
        btnHabilitar = new JButton("Habilitar");

        //Paso 4. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 5. Ubicamos los elementos en el contenedor
        lblpc.setBounds(120, 100, 200, 20); //x,y, ancho y alto
        txtPorcentaje.setBounds(130, 125, 200, 25);
        lblus.setBounds(50, 50, 180, 10);
        id.setBounds(170, 50, 180, 25);

        btnAceptar.setBounds(40, 175, 80, 25);
        btnCancelar.setBounds(130, 175, 100, 25);
        btnLimpiar.setBounds(240, 175, 120, 25);
        btnHabilitar.setBounds(370, 175, 140, 25);

        //Paso 6. Agremos los componentes al contenedor
        pnlContenido.add(lblpc);
        pnlContenido.add(txtPorcentaje);
        pnlContenido.add(lblus);
        pnlContenido.add(id);

        pnlContenido.add(btnAceptar);
        pnlContenido.add(btnCancelar);
        pnlContenido.add(btnLimpiar);
        pnlContenido.add(btnHabilitar);

        //Paso 7. Asociamos el contenedor a la ventana
        v.setContentPane(pnlContenido);

        //Paso 8. Hacemos visible la ventana
        v.setVisible(true);

        //Paso 9. Escucha de eventos.
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnHabilitar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        txtPorcentaje.setEnabled(false);
    }

    public void insertarDatos() {
        if (id.getText().isEmpty()/*) || (txtpass.getText().isEmpty())*/) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben de estar llenos");
        } else {
            try {

                Statement comando = conn.createStatement();
                //el query quedara guardado en un resultado establecido, este query se encarga de seleccionar las colunas cantidad y nombre de la base de datos y compara si el id es igual al id que se introdujo en el formulario
                ResultSet registro = comando.executeQuery("select * from admin where id_admin=" + id.getText());
                if ((registro.next() == true)) {
                    txtPorcentaje.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "¿Eres en verdad el administrador?");
                }

            } catch (SQLException ex) {
                setTitle(ex.toString());
            }

        }

    }

    public void insertarDatos2() {
        try {

            Statement comando = conn.createStatement();
            //el query quedara guardado en un resultado establecido, este query se encarga de seleccionar las colunas cantidad y nombre de la base de datos y compara si el id es igual al id que se introdujo en el formulario
            ResultSet registro = comando.executeQuery("select * from admin where id_admin =" + id.getText());
            //si el resultado es verdadero en los campos de saldo y nombre del formulario apareceran los datos de la consulta que se hizo
            if (registro.next() == true) {

                 //actualizamos la columna porcentaje de la tabla admin en la base de datos
                int numero = Integer.parseInt(txtPorcentaje.getText());
                porcentaje = numero;
                cadena = String.valueOf(porcentaje);
                String sql2 = "update admin" + " set porcentaje = ?";
                PreparedStatement update = conn.prepareStatement(sql2);
                update.setString(1, cadena);
                update.executeUpdate();
                
                
                JOptionPane.showMessageDialog(rootPane, txtPorcentaje.getText() + "\nSe registró correctamente");

            } else {
                JOptionPane.showMessageDialog(null, "ID INCORRECTO");
            }
        } catch (Exception el) {
            JOptionPane.showMessageDialog(null, "Error" + el.getMessage());

        }

    }

    public void salir() {
        System.exit(0);
    }

 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnHabilitar) {
            insertarDatos();

        } else if (e.getSource() == btnCancelar) {
            salir();
        } else if (e.getSource() == btnAceptar) {
            insertarDatos2();

        } else if (e.getSource() == btnLimpiar) {
            id.setText("");

        }
    }
}
