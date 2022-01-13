package main.ventanas;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.UIManager;

import main.bd.BD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class VentanaSeleccionJuego extends JFrame {

	private static final long serialVersionUID = 1L;


	String usuarioLogeado;

	public VentanaSeleccionJuego(String usuario) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/Imagenes_sueltas/M.png")));

		Connection con = BD.initBD(VentanaLogin.NOMBRE_BD);
		Statement st = BD.creacionTablas(con);
		usuarioLogeado = usuario;
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(700, 550);
		setLocation(370, 100);
		setTitle("Selecciona un juego");
		getContentPane().setLayout(null);

		texto();
		botones();

	}

	public void texto() {


		JLabel lblPacman = new JLabel("Pac-Man");
		lblPacman.setForeground(Color.WHITE);
		lblPacman.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPacman.setBounds(307, 444, 65, 14);
		getContentPane().add(lblPacman);

	
		JLabel lblSeleccioneUnJuego = new JLabel("Selecciona el juego " + usuarioLogeado);
		lblSeleccioneUnJuego.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSeleccioneUnJuego.setForeground(Color.WHITE);
		lblSeleccioneUnJuego.setBounds(263, 88, 209, 23);
		getContentPane().add(lblSeleccioneUnJuego);
	}

	public void botones() {

		
		JButton boton_pacman = new JButton();
		boton_pacman.setOpaque(true);
		boton_pacman.setBounds(283, 288, 143, 128);

		ImageIcon icono_pacman = new ImageIcon(getClass().getResource("/img/recuadros_imagenes/recuadro_pacman.png"));
		ImageIcon icono_pacman2 = new ImageIcon(icono_pacman.getImage().getScaledInstance(boton_pacman.getWidth(),
				boton_pacman.getHeight(), java.awt.Image.SCALE_DEFAULT));

		boton_pacman.setIcon(icono_pacman2);
		getContentPane().add(boton_pacman);

		boton_pacman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NivelPacman d = new NivelPacman(usuarioLogeado);
				d.setVisible(true);
				dispose();

			}
		});
		boton_pacman.setBounds(282, 288, 117, 128);
		getContentPane().add(boton_pacman);

		

		JLabel panel = new JLabel();
		panel.setBounds(168, 113, 304, 73);
		getContentPane().add(panel);



		JButton terminar = new JButton();
		terminar.setOpaque(false);
		terminar.setContentAreaFilled(false);
		terminar.setBorderPainted(false);
		terminar.setBounds(643, 472, 51, 49);
		getContentPane().add(terminar);

		terminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

		ImageIcon puerta = new ImageIcon(getClass().getResource("/img/Imagenes_sueltas/Puerta.png"));
		ImageIcon puerta2 = new ImageIcon(puerta.getImage().getScaledInstance(terminar.getWidth(), terminar.getHeight(),
				java.awt.Image.SCALE_DEFAULT));

		terminar.setIcon(puerta2);
	}

	private static VentanaSeleccionJuego v;

	public static void main(String[] args) {
		BD.initData();
		try { 
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		} 
		v = new VentanaSeleccionJuego("Markel");
		v.setVisible(true);

	}
}