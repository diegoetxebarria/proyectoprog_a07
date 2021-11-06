package main.Ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class VentanaLoggin extends JFrame {
	private static final long serialVersionUID = 1L;
	public static String nombre;
	private JTextField textField;
	private JPasswordField passwordField_1;
	public static final String NOMBRE_BD = "multiJuegos.bd";

	public VentanaLoggin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(370, 200);
		setLocation(500, 200);
		setTitle("Login");
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Inicio sesion");
//		btnNewButton.addActionListener(new ActionListener() {
//			Servira para comprobar con la base de datos
//		});
		btnNewButton.setBounds(10, 127, 111, 23);
		getContentPane().add(btnNewButton);

		JButton btnAceptar = new JButton("Registro");
		btnAceptar.setBounds(143, 127, 86, 23);
		getContentPane().add(btnAceptar);
//		btnAceptar.addActionListener(new ActionListener() {
//		Nos servira para usarlo con la base de datos
//
//		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(258, 127, 86, 23);
		getContentPane().add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(90, 28, 60, 14);
		getContentPane().add(lblUsuario);

		JLabel lblcontraseina = new JLabel("Contraseña:");
		lblcontraseina.setBounds(70, 65, 74, 14);
		getContentPane().add(lblcontraseina);

		textField = new JTextField();
		textField.setBounds(154, 25, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(154, 62, 86, 20);
		getContentPane().add(passwordField_1);

	}


	public static void main(String[] args) {
		VentanaLoggin v = new VentanaLoggin();
		v.setVisible(true);

	}
}
