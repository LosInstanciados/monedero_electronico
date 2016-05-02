/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monedero.electronico;

import db.Mysql;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mariana Villegas
 */
public class Ventana extends JFrame implements ActionListener {

    JMenuItem cmdAltaCliente, cmdRegistroCompras, cmdPagos, cmdConsultaSaldo, cmdConfig;
    JMenu menuClientes, menuMovimientos, menuAdmin;
    JMenuBar barMenu;
    DefaultTableModel model;
    Connection conn;
    Statement sent;
    protected JTextField JTextfield1;
    public JTable JTable1;

    public Ventana() {

        //Establecemos el titulo de la ventana
        super("Monedero Electronico");
        //Establecemos la ubicacion en la pantalla, y las dimensiones de la ventana
        setBounds(200, 70, 700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conn = Mysql.getConnection();

        //Paso 1. Crear los JMenuItems
        cmdAltaCliente = new JMenuItem("Alta de Clientes");
        cmdRegistroCompras = new JMenuItem("Registro de Compras");
        cmdPagos = new JMenuItem("Pagos");
        cmdConsultaSaldo = new JMenuItem("Consulta de Saldo");
        cmdConfig = new JMenuItem("Configuracion");
        //Paso 2. Creamos los JMenus
        menuClientes = new JMenu("Clientes");
        menuMovimientos = new JMenu("Movimientos");
        menuAdmin = new JMenu("Administrador");
        //Paso 3. Creamos la barra JMenuBar
        barMenu = new JMenuBar();

        //Paso 4. Agregar los items a los menus
        menuClientes.add(cmdAltaCliente);
        menuAdmin.add(cmdConfig);
        menuMovimientos.add(cmdRegistroCompras);
        menuMovimientos.add(cmdPagos);
        menuMovimientos.add(cmdConsultaSaldo);
        //Paso 5. Agregar los menus a la barra
        barMenu.add(menuClientes);
        barMenu.add(menuMovimientos);
        barMenu.add(menuAdmin);

        setJMenuBar(barMenu);

        setVisible(true);

        //Paso 6. Que los comandos ESCUCHEN
        cmdAltaCliente.addActionListener(this);
        cmdRegistroCompras.addActionListener(this);
        cmdPagos.addActionListener(this);
        cmdConsultaSaldo.addActionListener(this);
        cmdConfig.addActionListener(this);

    }

    @Override
    @SuppressWarnings("override")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmdAltaCliente) {
            //instanciamos la clase altaDeClientes
            altaDeClientes cliente = new altaDeClientes();

        } else if (e.getSource() == cmdRegistroCompras) {
//Instanciamos la clase registroDeCompras
            registroDeCompras registro = new registroDeCompras();

        } else if (e.getSource() == cmdPagos) {
//Instanciamos la clase pagos
            pagos pagos = new pagos();
        } else if (e.getSource() == cmdConsultaSaldo) {
            //instanciamos la clase consultaDeSaldo
            consultaDeSaldo saldo = new consultaDeSaldo();
        } else if (e.getSource() == cmdConfig) {
            adminConfiguracion config = new adminConfiguracion();
        }
    }

}
