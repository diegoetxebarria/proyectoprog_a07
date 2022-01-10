package main.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

import main.mod.Participa;
import main.mod.Usuario;

public class BD {

	private static Exception lastError = null; // Nos informa del ultimo errror sql ocurrido

	private static Connection connection = null;

	public static Connection initBD(String nombreBD) {
		try {
			if (connection == null) {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
				log("Conectada base de datos " + nombreBD, null);
			}
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			lastError = e;
			log("Error en conexión de base de datos " + nombreBD, e);
			e.printStackTrace();
			return null;
		}
	}

	private static Logger logger = null;

	public static void setLogger(Logger logger) {
		BD.logger = logger;
	}

	// metodo para logear
	private static void log(String msg, Throwable excepcion) {
		if (logger == null) { // Logger por defecto
			logger = Logger.getLogger("BD-server"); // Nombre del logger
			try {
				logger.addHandler(new FileHandler("bd.log.xml", true)); // Saca el log a fichero xml
			} catch (Exception e) {
				logger.log(Level.SEVERE, "No se pudo crear fichero de log", e);
			}
		}
		if (excepcion == null)
			logger.log(null, msg);
		else
			logger.log(null, msg, excepcion);
	}

	// Creacion de tablas
	public static Statement creacionTablas(Connection con) {

		try {
			Statement statement;
			statement = con.createStatement();
			statement.setQueryTimeout(10);
			statement.executeUpdate("create table if not exists usuario "
					+ "(nick string primary key, password string not null, fechaNacimiento integer, genero string)");
			statement.executeUpdate(
					"create table if not exists pacman" + "(idJuego integer primary key, pacman string not null)");
			statement.executeUpdate("create table if not exists participacion "
					+ "(idParticipacion Integer primary key AUTOINCREMENT, " + "nick string not null,"
					+ "nivel integer not null, " + "idJuego integer not null,"
					+ "puntuacion integer not null, fecha integer," + "foreign key(idJuego) references pacman(idJuego),"
					+ "foreign key(nick) references usuario(nick))");
			statement.executeUpdate(
					"create table if not exists nivel " + "(idJuego  integer not null," + "nick string not null,"
							+ "maxNivel integer not null," + " foreign key(idJuego) references pacman(idJuego),"
							+ " foreign key(nick) references participacion(nick))");

			// Informamos de que la base de datos ha sido creada
			log("Creada base de datos", null);
			return statement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lastError = e;
			log("Error en creación de base de datos", e);
			e.printStackTrace();
			return null;
		}

	}

	// Reincio/Borrado de las tablas ya creadas

	public static Statement reinicioBD(Connection con, String tabla) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(10);
			statement.executeUpdate("Drop table if exists " + tabla);
			log("Tabla eliminida con extio:" + tabla, null);
			return creacionTablas(con);
		} catch (SQLException e) {
			log("Error en reinicio de base de datos", e);
			lastError = e;
			e.printStackTrace();

			return null;
		}
	}

	// Metodo para cerrar la base de datos

	public static void closeBD(Connection con, Statement st) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
			log("Base de datos cerra da con extio", null);
		} catch (SQLException e) {
			lastError = e;
			log("Error al cerrar la base de  datos", e);
			e.printStackTrace();
		}
	}

	// Metodo que nos devovlera informacion sobre la excepcion del ultimo error que
	// ha pasado

	public static Exception getLastError() {
		return lastError;
	}

	// Metodo que introducira los datos del usuario
	public static boolean usuarioInsert(Statement st, Usuario usuario) {
		String sentenciaSQL = "";
		String sentenciaSQL2 = "";

		try {

			sentenciaSQL = "insert into usuario (nick, password, fechaNacimiento, genero) values('" + usuario.getNick()
					+ "', '" + usuario.getPassword() + "', " + usuario.getFechaNacimientoLong() + ", '"
					+ usuario.getGenero() + "')";
			int val = st.executeUpdate(sentenciaSQL);
			log("BD añadida con exito " + val + " fila\t" + sentenciaSQL, null);
			if (val != 1) { // Se tendra que añadir, sino dara error
				log("Error en insert de BD\t" + sentenciaSQL, null);
				return false;
			}

			sentenciaSQL2 = "insert into nivel(idJuego, nick, maxNivel) values(" + 1 + ",'" + usuario.getNick() + "',"
					+ 1 + ")";
			int val2 = st.executeUpdate(sentenciaSQL2);
			log("BD añadida con exito " + val2 + " fila\t" + sentenciaSQL2, null);
			if (val2 != 1) { // Se tendra que añadir, sino dara error
				log("Error en insert de BD\t" + sentenciaSQL2, null);
				return false;
			}
			return true;

		} catch (SQLException e) {
			// TODO: handle exception
			log("Error en BD\t" + sentenciaSQL + " o " + sentenciaSQL2, e);
			lastError = e;
			e.printStackTrace();
			return false;
		}

	}

	// Metodo para meter informacion sobre el juego del pacman
	public static boolean juegoInsert(Statement st, int idJuego, String juego) {
		String sentSQL = "";
		try {
			sentSQL = "insert into juego (idJuego, juego) values(" + idJuego + ", '" + juego + "')";
			int val = st.executeUpdate(sentSQL);
			log("BD añadida con exito" + val + " fila\t" + sentSQL, null);
			if (val != 1) {// Se tendra que añadir, sino dara error
				log("Error al realizar el insert de BD\t" + sentSQL, null);
				return false;
			}
			return true;
		} catch (SQLException e) {
			log("Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}

	// Metodo que metera los niveles que tiene el pacman

	public static boolean nivelInsert(Statement st, int idJuego, String nick, int maxNivel) {
		String sentSQL = "";
		try {
			sentSQL = "insert into nivel (idJuego, nick, maxNivel) values(" + idJuego + ", " + nick + ", " + maxNivel
					+ ")";
			int val = st.executeUpdate(sentSQL);
			log("BD añadida con exito" + val + " fila\t" + sentSQL, null);
			if (val != 1) { // Se tendra que añadir, sino dara error
				log("Error al realizar el insert de BD\t" + sentSQL, null);
				return false;
			}
			return true;
		} catch (SQLException e) {
			log("Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}

	// metodo que nos servira para insertar los datos del usuario con el juego al
	// que ha jugado con el nivel, puntuacio.....
	public static boolean participacionInsert(Statement st, String nick, int idJuego, int nivel, int puntuacion,
			long fecha) {
		String sentSQL = "";
		try {
			sentSQL = "insert into participacion (nick, idJuego, nivel, puntuacion, fecha) values('" + nick + "',"
					+ idJuego + "," + nivel + ", " + puntuacion + ", " + fecha + ")";
			int val = st.executeUpdate(sentSQL);
			log("BD añadida " + val + " fila\t" + sentSQL, null);
			if (val != 1) { // Se tendra que añadir, sino dara error
				log("Error en insert de BD\t" + sentSQL, null);
				return false;
			}
			return true;
		} catch (SQLException e) {
			log("Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return false;
		}

	}

	// ME FALTA POR AÑADIR LOS METODOS PARA REALIZAR LOS SELECTS Y PARA GUARDAR LOS
	// DATOS

	public static Usuario usuarioSelect(Statement st, String usuario, String password) {
		String sentSQL = "";
		Usuario usuarioObj = null;
		try {
			sentSQL = "select * from usuario where nick = '" + usuario + "' and password = '" + password + "'";
			ResultSet rs = st.executeQuery(sentSQL);
			if (rs != null && rs.next()) {
				long fechaLong = rs.getLong("fechaNacimiento");
				Date fecha = new Date(fechaLong);
				Usuario.Genero genero = Usuario.Genero.valueOf(rs.getString("genero"));
				usuarioObj = new Usuario(rs.getString("nick"), rs.getString("password"), fecha, genero);
			}
		} catch (SQLException e) {
			log("Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return null;
		}
		return usuarioObj;
	}

	public static int nivelselect(Statement st, Integer idJuego, String nick) {
		String sentSQL = "";
		Integer maxNivel = 1;
		try {
			sentSQL = "select maxNivel from nivel where idJuego = '" + idJuego + "' and nick = '" + nick + "'";
			ResultSet rs = st.executeQuery(sentSQL);
			if (rs != null && rs.next()) {
				maxNivel = rs.getInt("maxNivel");

			}
		} catch (SQLException e) {
			log("Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return 1;
		}
		return maxNivel;

	}

	public static ArrayList<Participa> participacionSelect(Statement st, String nick, Integer idJuego) {
		String sentSQL = "";
		ArrayList<Participa> participaciones = new ArrayList<>();
		Participa participacionObj = null;
		try {
			sentSQL = "select * from participacion " + "where nick = '" + nick + "' and idJuego = " + idJuego
					+ " order by nivel desc";
			ResultSet rs = st.executeQuery(sentSQL);
			while (rs.next()) {
				int idParticipacion = rs.getInt("idParticipacion");
				long fechaLong = rs.getLong("fecha");
				Date fecha = new Date(fechaLong);
				int puntos = rs.getInt("puntuacion");
				int nivel = rs.getInt("nivel");
				participacionObj = new Participa(idParticipacion, nick, nivel, idJuego, puntos, fecha);
				participaciones.add(participacionObj);
			}
		} catch (SQLException e) {
			log("Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return null;
		}
		return participaciones;
	}

}
