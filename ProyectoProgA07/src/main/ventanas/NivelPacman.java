package main.ventanas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import main.bd.BD;
import main.ventanas.VentanaSeleccionJuego;
import main.mod.Participa;
import main.general.Tablero;
import main.general.Inicio;

public class NivelPacman extends JFrame {

	private static final long serialVersionUID = 1L;
	String usuarioLogeado;

	public NivelPacman(String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/Imagenes_sueltas/pacman-favicon.png")));
 
		
		usuarioLogeado = usuario;
		setContentPane(new JPanel() {
			 
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(670, 450);
			}
		});
		pack();
		setLocation(390, 100);
		setTitle("Niveles PacMan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);

		labels();
		botones();

	}

	public void labels() {
		JLabel Titulo = new JLabel("PacMan");
		Titulo.setForeground(Color.YELLOW);
		Titulo.setFont(new Font("Stencil", Font.PLAIN, 40));
		Titulo.setBackground(Color.WHITE);
		Titulo.setBounds(257, 55, 289, 40);
		getContentPane().add(Titulo);

		JLabel Pacman = new JLabel();
		Pacman.setBounds(50, 55, 561, 50);
		getContentPane().add(Pacman);

		ImageIcon pacman = new ImageIcon(getClass().getResource("/img/Imagenes_sueltas/imagen_fondo_pacman.png"));
		ImageIcon pacman2 = new ImageIcon(pacman.getImage().getScaledInstance(Pacman.getWidth(), Pacman.getHeight(),
				java.awt.Image.SCALE_DEFAULT));

		Pacman.setIcon(pacman2);

		JLabel lblNiveles = new JLabel("Niveles");
		lblNiveles.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNiveles.setForeground(Color.ORANGE);
		lblNiveles.setBounds(290, 106, 89, 23);
		getContentPane().add(lblNiveles);

	}

	public void botones() {

		JButton [] botonesNivel = new JButton[6];
		Connection con = BD.initBD(VentanaLogin.NOMBRE_BD);
		Statement st = BD.creacionTablas(con);
		ArrayList<Participa> participaciones = BD.participacionSelect(st, usuarioLogeado, 2);
		
		int nivelMax = BD.nivelselect(st, 2, usuarioLogeado);
		
		ListenerBoton listener = new ListenerBoton();
		
		crearBoton(botonesNivel, listener, 1, 30);
		crearBoton(botonesNivel, listener, 2, 140);
		crearBoton(botonesNivel, listener, 3, 254);
		crearBoton(botonesNivel, listener, 4, 362);
		crearBoton(botonesNivel, listener, 5, 466);
		crearBoton(botonesNivel, listener, 6, 577);
		
		for (JButton boton : botonesNivel) {
			int btnNum = Integer.parseInt(boton.getText());
			if(nivelMax >= btnNum) {
				boton.setEnabled(true);
			}else {
				boton.setEnabled(false);
			}
		}
		
		

		JButton btnAtras = new JButton();
		btnAtras.setBackground(Color.BLACK);
		btnAtras.setForeground(new Color(0, 0, 0));
		btnAtras.setBounds(271, 338, 89, 23);

		ImageIcon icono_atras = new ImageIcon(getClass().getResource("/img/Imagenes_sueltas/Atras.png"));
		ImageIcon icono_atras2 = new ImageIcon(icono_atras.getImage().getScaledInstance(btnAtras.getWidth(),
				btnAtras.getHeight(), java.awt.Image.SCALE_DEFAULT));

		btnAtras.setIcon(icono_atras2);
		getContentPane().add(btnAtras);

		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionJuego v = new VentanaSeleccionJuego(usuarioLogeado);
				v.setVisible(true);
				dispose();
			}
		});

		JButton ranking = new JButton();
		ranking.setOpaque(false);
		ranking.setContentAreaFilled(false);
		ranking.setBorderPainted(false);
		ranking.setBounds(574, 406, 44, 40);
		getContentPane().add(ranking);

		ranking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				Ranking rv = new Ranking();
//				rv.setVisible(true);
				dispose();
			}
		});

		ImageIcon copa = new ImageIcon(getClass().getResource("/img/Imagenes_sueltas/Copa.png"));
		ImageIcon copa2 = new ImageIcon(copa.getImage().getScaledInstance(ranking.getWidth(), ranking.getHeight(),
				java.awt.Image.SCALE_DEFAULT));

		ranking.setIcon(copa2);

		JButton terminar = new JButton();
		terminar.setOpaque(false);
		terminar.setContentAreaFilled(false);
		terminar.setBorderPainted(false);
		terminar.setBounds(623, 406, 44, 40);
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
	
	
	private void crearBoton(JButton[] botonesNivel, ListenerBoton listenner, int numero, int x) {
		if(numero < 1) {
			throw new IllegalArgumentException("El número mayor que cero");
		}
		JButton btnNivel1 = new JButton(numero + "");
		btnNivel1.setBackground(Color.BLACK);
		btnNivel1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNivel1.setForeground(Color.MAGENTA);
		btnNivel1.setBounds(x, 199, 70, 70);
		getContentPane().add(btnNivel1);
		
		btnNivel1.addActionListener(listenner);
		botonesNivel[numero-1] = btnNivel1;
	}

	private class ListenerBoton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton botonPulsado = (JButton) e.getSource();
			int nivel = Integer.parseInt(botonPulsado.getText());
			Thread t = new Thread() {
				public void run() {
					Tablero m = new Tablero(usuarioLogeado);
					m.setNivel(nivel);
					m.jugarA();
				}
			};
			t.start();
			dispose();
		}
	}

	public static void main(String[] args) {
		try { 
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		} 
		NivelPacman d = new NivelPacman("Diego");
		d.setVisible(true);
	}
}
