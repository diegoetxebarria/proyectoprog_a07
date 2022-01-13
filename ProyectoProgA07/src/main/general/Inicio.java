package main.general;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.UIManager;

import main.bd.BD;
import main.mod.Juego;
import main.ventanas.VentanaLogin;

public class Inicio extends JFrame implements Juego{

	private static final long serialVersionUID = 1L;
	private int nivel = 1;

	public Inicio(String usuario) {
		Const.usuarioLogeado = usuario;
	}

	private void initUI() {

		add(new Tablero(this, nivel));
		setTitle("Juego PacMan");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(this.getClass().getResource("/img/Imagenes_sueltas/pacman-favicon.png")));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(385, 420);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		} //
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Inicio ex = new Inicio(Const.usuarioLogeado);
				ex.initUI();
				ex.setVisible(true);
			}
		});
	}

	public void jugar() {
		initUI();
		Const.log.log(Level.INFO, "Se inicia el juego");
		this.setVisible(true);

	}

	public void setNivel(int level) {
		Const.log.log(Level.INFO, "Se establece el nivel " + level + " al juego pacman");
		if (level < 1 || level > 6) {
			Const.log.log(Level.SEVERE, "El valor del nivel tiene que estar entre 1 y 6");
			throw new IllegalArgumentException("El valor del nivel tiene que estar entre 1 y 6");
		}

		this.nivel = level;

	}

	public void guardarPuntuacion(String nick, Date fecha, int nivel, int puntuacion) {
		Connection con = BD.initBD(VentanaLogin.NOMBRE_BD);
		Statement st = BD.usarBD(con);
		BD.participacionInsert(st, nick, 2, nivel, puntuacion, fecha.getTime());
	}

	public void guardarNuevoMaxNivel(Integer idJuego, Integer nivel, String nick) {
		Connection con = BD.initBD(VentanaLogin.NOMBRE_BD);
		Statement sta = BD.usarBD(con);
		String sql = "update nivel set maxNivel = maxNivel +1 where idJuego =2 and nick = '" + nick
				+ "' and maxNivel < 6 and maxNivel = " + nivel;
		try {
			sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
