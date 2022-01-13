package main.ventanas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import main.ventanas.VentanaLogin;

import main.bd.BD;

import main.mod.Usuario;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	public static String nombre;
	private JTextField textField;
	private JPasswordField passwordField_1;
	public static final String NOMBRE_BD = "pacman.bd";

	public VentanaLogin() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(370, 200);
		setLocation(500, 200);
		setTitle("Login");
		getContentPane().setLayout(null);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/Imagenes_sueltas/usuario.png")));

		JButton btnNewButton = new JButton("Inicio sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread() {
					public void run() {
						// Para que se pueda comprobar en la BD que el usuario y contraseña son
						// correctos
						String usuario = textField.getText();
						String password = new String(passwordField_1.getPassword());
						Connection con = BD.initBD(NOMBRE_BD);
						Statement st = BD.creacionTablas(con);
						Usuario usuarioLogeado = BD.usuarioSelect(st, usuario, password);
						if (usuarioLogeado != null) {

							new VentanaSeleccionJuego(usuario).setVisible(true);
							dispose();
						} else {
							JOptionPane optionPane = new JOptionPane("Usuario o contraseña incorrecto",
									JOptionPane.ERROR_MESSAGE);
							JDialog dialog = optionPane.createDialog("Login error");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
						}
					}
				};

				t.start();
			}
		});
		btnNewButton.setBounds(10, 127, 111, 23);
		getContentPane().add(btnNewButton);

		JButton btnRegistro = new JButton("Registro");
		btnRegistro.setBounds(143, 127, 86, 23);
		getContentPane().add(btnRegistro);

		btnRegistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread() {
					public void run() {
						VentanaRegistro v = new VentanaRegistro();
						v.setVisible(true);
						dispose();
					}
				};
				t.start();
			}
		});
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
		BD.initData();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		VentanaLogin v = new VentanaLogin();
		v.setVisible(true);

	}
}
