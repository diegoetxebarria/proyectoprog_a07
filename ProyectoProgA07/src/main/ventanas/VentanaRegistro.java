package main.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import main.ventanas.VentanaRegistro;
import main.bd.BD;
import main.mod.Usuario;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField textField_nombre;
	private JComboBox<Object> cbDia;
	private JComboBox<String> comboBox_mes;
	private JComboBox<Object> cbAnyo;
	String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
			"Octubre", "Noviembre", "Diciembre" };
	JRadioButton[] rdbtnArray = new JRadioButton[3];
	Usuario.Genero[] generos = { Usuario.Genero.HOMBRE, Usuario.Genero.MUJER, Usuario.Genero.NO_BINAR };

	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(600, 330);
		setLocation(400, 150);
		setTitle("Registro");
		getContentPane().setLayout(null);

		JLabel lblNombre = new JLabel("Usuario");
		lblNombre.setBounds(72, 31, 61, 16);
		getContentPane().add(lblNombre);

		JLabel lblContraseina = new JLabel("Contraseña");
		lblContraseina.setBounds(72, 72, 86, 16);
		getContentPane().add(lblContraseina);

		passwordField = new JPasswordField();
		passwordField.setBounds(156, 70, 152, 21);
		getContentPane().add(passwordField);

		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(201, 189, 86, 23);
		getContentPane().add(rdbtnHombre);
		rdbtnArray[0] = rdbtnHombre;

		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(361, 189, 78, 23);
		getContentPane().add(rdbtnMujer);
		rdbtnArray[1] = rdbtnMujer;
		
		JRadioButton rdbtnNoBinario = new JRadioButton("No Binario");
		rdbtnNoBinario.setBounds(385, 189, 147, 23);
		getContentPane().add(rdbtnNoBinario);
		rdbtnArray[2] = rdbtnNoBinario;
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnHombre);
		bg.add(rdbtnMujer);
		bg.add(rdbtnNoBinario);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(72, 110, 173, 16);
		getContentPane().add(lblFechaDeNacimiento);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(156, 247, 117, 29);
		getContentPane().add(btnAceptar);

		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread() {
					public void run() {
						Connection con = BD.initBD(VentanaLogin.NOMBRE_BD);
						Statement st = BD.creacionTablas(con);
						String mesSelecionado = (String) comboBox_mes.getSelectedItem();
						int numMes = 0;
						for (int i = 0; i < meses.length; i++) {
							if (meses[i].equals(mesSelecionado)) {
								numMes = i + 1;
							}
						}
						int dia = (int) cbDia.getSelectedItem();
						int year = (int) cbAnyo.getSelectedItem();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date fecha = null;
						try {
							fecha = sdf.parse(year + "-" + numMes + "-" + dia);
						} catch (ParseException e) {
							e.printStackTrace();
						}

						String usuarioNick = textField_nombre.getText();
						String password = new String(passwordField.getPassword());
						Usuario.Genero genero = null;
						for (int i = 0; i < rdbtnArray.length; i++) {
							if (rdbtnArray[i].isSelected()) {
								genero = generos[i];
							}
						}

						Usuario user = new Usuario(usuarioNick, password, fecha, genero);
						boolean nuevoUsuario = BD.usuarioInsert(st, user);
						if (!nuevoUsuario)
							// Usuario no insertado
							System.out.println("Error.");
						new VentanaLogin().setVisible(true);
						dispose();
					}
				};
				t.start();
				System.out.println("Aceptar");
			}
		});


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(342, 247, 117, 29);
		getContentPane().add(btnCancelar);

		// Esto se lanza cuando alguien pulsa el boton de Cancelar
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(72, 168, 86, 14);
		getContentPane().add(lblGenero);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(156, 29, 152, 20);
		getContentPane().add(textField_nombre);
		textField_nombre.setColumns(10);

		List<Integer> lstDia = new ArrayList<>();
		int dia = (31);
		for (int i = 1; i <= dia; i++) {
			lstDia.add(i);
		}

		cbDia = new JComboBox<Object>();
		cbDia.setModel(
				(ComboBoxModel<Object>) new DefaultComboBoxModel<Object>(lstDia.toArray(new Integer[lstDia.size()])));
		cbDia.setBounds(72, 138, 78, 20);
		getContentPane().add(cbDia);

		comboBox_mes = new JComboBox<String>();
		comboBox_mes.setBounds(174, 137, 159, 20);
		getContentPane().add(comboBox_mes);
		comboBox_mes.addItem(meses[0]);
		comboBox_mes.addItem(meses[1]);
		comboBox_mes.addItem(meses[2]);
		comboBox_mes.addItem(meses[3]);
		comboBox_mes.addItem(meses[4]);
		comboBox_mes.addItem(meses[5]);
		comboBox_mes.addItem(meses[6]);
		comboBox_mes.addItem(meses[7]);
		comboBox_mes.addItem(meses[8]);
		comboBox_mes.addItem(meses[9]);
		comboBox_mes.addItem(meses[10]);
		comboBox_mes.addItem(meses[11]);

		List<Integer> lstAnyos = new ArrayList<>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 1900; i <= year; i++) {
			lstAnyos.add(i);
		}

		cbAnyo = new JComboBox<Object>();
		cbAnyo.setModel((ComboBoxModel<Object>) new DefaultComboBoxModel<Object>(
				lstAnyos.toArray(new Integer[lstAnyos.size()])));
		cbAnyo.setBounds(361, 138, 128, 20);
		getContentPane().add(cbAnyo);

	}

	public static void main(String[] args) {
		BD.initData();
		try { 
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		} 
		VentanaRegistro v = new VentanaRegistro();
		v.setVisible(true);

	}
}
