package main.bd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

import main.mod.Usuario;
import main.mod.Participa;



public class BD {

	private static Exception lastError = null; // Nos informa de último error SQL ocurrido

	private static Connection connection = null;


	public static Connection initBD(String nombreBD) {
		try {
			if (connection == null) {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
				log(Level.INFO, "Conectada base de datos " + nombreBD, null);
			}
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			lastError = e;
			log(Level.SEVERE, "Error en conexión de base de datos " + nombreBD, e);
			e.printStackTrace();
			return null;
		}
	}
	
	private static void log(Level level, String msg, Throwable excepcion) {
		if (logger == null) { // Logger por defecto 
			logger = Logger.getLogger("BD-server-ejemplocs"); // Nombre del logger
			logger.setLevel(Level.ALL); // Loguea todos los niveles
			try {
				logger.addHandler(new FileHandler("bd.log.xml", true)); // Y saca el log a fichero xml
			} catch (Exception e) {
				logger.log(Level.SEVERE, "No se pudo crear fichero de log", e);
			}
		}
		if (excepcion == null)
			logger.log(level, msg);
		else
			logger.log(level, msg, excepcion);
	}

	public static Statement creacionTablas(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // poner timeout 30 msg
			statement.executeUpdate(
					"create table if not exists juego " + "(idJuego integer primary key, juego string not null)");

			statement.executeUpdate(
					"create table if not exists nivel " + "(idJuego  integer not null," + "nick string not null,"
							+ "maxNivel integer not null," + " foreign key(idJuego) references juego(idJuego),"
							+ " foreign key(nick) references participacion(nick))");

			statement.executeUpdate("create table if not exists usuario "
					+ "(nick string primary key, password string not null, fechaNacimiento integer, genero string)");

			statement.executeUpdate("create table if not exists participacion "
					+ "(idParticipacion Integer primary key AUTOINCREMENT, " + "nick string not null,"
					+ "nivel integer not null, " + "idJuego integer not null,"
					+ "puntuacion integer not null, fecha integer," + "foreign key(idJuego) references juego(idJuego),"
					+ "foreign key(nick) references usuario(nick))");

			log(Level.INFO, "Creada base de datos", null);
			return statement;
		} catch (SQLException e) {
			lastError = e;
			log(Level.SEVERE, "Error en creación de base de datos", e);
			e.printStackTrace();
			return null;
		}
	}

	
	public static Statement usarBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // poner timeout 30 msg
			return statement;
		} catch (SQLException e) {
			lastError = e;
			log(Level.SEVERE, "Error en uso de base de datos", e);
			e.printStackTrace();
			return null;
		}
	}
	//Reinicio - borrado de las tablas ya creadas
	
	public static Statement reiniciarBD(Connection con, String tabla) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // poner timeout 30 msg
			statement.executeUpdate("drop table if exists " + tabla);
			log(Level.INFO, "Eliminada tabla " + tabla, null);
			return creacionTablas(con);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en reinicio de base de datos", e);
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}
	



 //Metodo para cerrar la base de datos
	public static void cerrarBD(Connection con, Statement st) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
			log(Level.INFO, "Cierre de base de datos", null);
		} catch (SQLException e) {
			lastError = e;
			log(Level.SEVERE, "Error en cierre de base de datos", e);
			e.printStackTrace();
		}
	}


	public static Exception getLastError() {
		return lastError;
	}

	public static boolean usuarioInsert(Statement st, Usuario usuario) {
		String sentSQL = "";
		String sentSQL2 = "";
		
		try {
			sentSQL = "insert into usuario (nick, password, fechaNacimiento, genero) values('" + usuario.getNick()
					+ "', '" + usuario.getPassword() + "', " + usuario.getFechaNacimientoLong() + ", '"
					+ usuario.getGenero() + "')";
			int val = st.executeUpdate(sentSQL);
			log(Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null);
			if (val != 1) { // Se tiene que añadir ,sino dara error
				log(Level.SEVERE, "Error en insert de BD\t" + sentSQL, null);
				return false;
			}

			sentSQL2 = "insert into nivel(idJuego, nick, maxNivel) values(" + 1 + ",'" + usuario.getNick() + "'," + 1
					+ ")";
			

			int val2 = st.executeUpdate(sentSQL2);
			log(Level.INFO, "BD añadida " + val2 + " fila\t" + sentSQL2, null);
			if (val2 != 1) { // Se tiene que añadir porque da sino el mismo error que arriba
				log(Level.SEVERE, "Error en insert de BD\t" + sentSQL2, null);
				return false;
			}
			
			return true;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en BD\t" + sentSQL + " o " + sentSQL2, e);
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}

	//Metodo para meter informacion sobre el juego del pacman
	public static boolean juegoInsert(Statement st, int idJuego, String juego) {
		String sentSQL = "";
		try {
			sentSQL = "insert into juego (idJuego, juego) values(" + idJuego + ", '" + secu(juego) + "')";
			int val = st.executeUpdate(sentSQL);
			log(Level.INFO, "BD añadida  con exito" + val + " fila\t" + sentSQL, null);
			if (val != 1) { 
				log(Level.SEVERE, "Error en el insert de BD\t" + sentSQL, null);
				return false;
			}
			return true;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}

	//Metodo que  metera los niveles que tiene el pacman
	public static boolean nivelInsert(Statement st, int idJuego, String nick, int maxNivel) {
		String sentSQL = "";
		try {
			sentSQL = "insert into nivel (idJuego, nick, maxNivel) values(" + idJuego + ", " + nick + ", " + maxNivel
					+ ")";
			int val = st.executeUpdate(sentSQL);
			log(Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null);
			if (val != 1) { // Se tiene que añadir 1 - error si no
				log(Level.SEVERE, "Error en insert de BD\t" + sentSQL, null);
				return false;
			}
			return true;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en BD\t" + sentSQL, e);
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
			log(Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null);
			if (val != 1) { // Se tiene que añadir 
				log(Level.SEVERE, "Error en insert de BD\t" + sentSQL, null);
				return false;
			}
			return true;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}

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
			log(Level.SEVERE, "Error en BD\t" + sentSQL, e);
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
			log(Level.SEVERE, "Error en BD\t" + sentSQL, e);
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
			log(Level.SEVERE, "Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
			return null;
		}
		return participaciones;
	}

	public static void deleteData(Statement st) {
		String sentSQL = "";
		try {
			sentSQL = "delete from participacion";
			int val = st.executeUpdate(sentSQL);
			System.out.println("Numero de participaciones eliminadas: " + val);
			sentSQL = "delete from nivel";
			val = st.executeUpdate(sentSQL);
			System.out.println("Numero de niveles eliminados: " + val);
			sentSQL = "delete from usuario";
			val = st.executeUpdate(sentSQL);
			System.out.println("Numero de usuarios eliminados: " + val);
			sentSQL = "delete from juego";
			val = st.executeUpdate(sentSQL);
			System.out.println("Numero de juegos eliminados: " + val);
			log(Level.INFO, "BD eliminada", null);

		} catch (SQLException e) {
			log(Level.SEVERE, "Error en BD\t" + sentSQL, e);
			lastError = e;
			e.printStackTrace();
		}
	}

	// Metodo para crear datos de DEMO
	public static void initData() {

		// Paso 1 abrir y crear las tablas de la BDD
		Connection con = initBD("pacman.bd");
		Statement st = creacionTablas(con);
		// deleteData(st);
		// Paso 2 Insertar datos tabla juego
		File file = new File("juegos.csv");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				String linea = null;
				while ((linea = br.readLine()) != null) {
					String[] datos = linea.split(";");
					int idJuego = Integer.parseInt(datos[0]);
					String juego = datos[1];
					juegoInsert(st, idJuego, juego);
				}
			} finally {
				br.close(); // cerrar el fichero al terminar
			}
		} catch (IOException e) {
			System.err.println("reading file error IOException = " + e.toString());
		}
		// Datos niveles
//		file = new File("niveles.csv");
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(file));
//			try {
//				String linea = null;
//				while ((linea = br.readLine()) != null) {
//					String[] datos = linea.split(";");
//					int idNivel = Integer.parseInt(datos[0]);
//					int idJuego = Integer.parseInt(datos[1]);
//					int nivel = Integer.parseInt(datos[2]);
//					nivelInsert(st, idNivel, idJuego, nivel);
//				}
//			} finally {
//				br.close(); // cerrar el fichero al terminar
//			}
//		} catch (IOException e) {
//			System.err.println("reading file error IOException = " + e.toString());
//		}

		file = new File("usuarios.csv");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				String linea = null;
				while ((linea = br.readLine()) != null) {
					String[] datos = linea.split(";");
					String nick = datos[0];
					String password = datos[1];
					long fecha = Long.parseLong(datos[2]);
					Usuario.Genero genero = Usuario.Genero.valueOf(datos[3]);
					Date fechaNacimiento = new Date(fecha);
					Usuario usuario = new Usuario(nick, password, fechaNacimiento, genero);
					usuarioInsert(st, usuario);
				}
			} finally {
				br.close(); // cerrar el fichero al terminar
			}
		} catch (IOException e) {
			System.err.println("reading file error IOException = " + e.toString());
		}
//		file = new File("participacion.csv");
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(file));
//			try {
//				String linea = null;
//				while ((linea = br.readLine()) != null) {
//					String[] datos = linea.split(";");
//					int idParticipacion = Integer.parseInt(datos[0]);
//					String nick = datos[1];
//					int nivel = Integer.parseInt(datos[2]);
//					int idJuego = Integer.parseInt(datos[3]);
//					int puntuacion = Integer.parseInt(datos[4]);
//					long fechaL = Long.parseLong(datos[5]);
//					Date fecha = new Date(fechaL);
//					// Participacion participacion = new Participacion(idParticipacion, nick,
//					// nivel,idJuego, puntuacion, fecha);
//					participacionInsert(st, nick, idJuego, nivel, puntuacion, fechaL);
//				}
//			} finally {
//				br.close(); // cerrar el fichero al terminar
//			}
//		} catch (IOException e) {
//			System.err.println("reading file error IOException = " + e.toString());
//		}
	}

	// Métodos privados

	
	private static String secu(String string) {
		StringBuffer ret = new StringBuffer();
		for (char c : string.toCharArray()) {
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZñÑáéíóúüÁÉÍÓÚÚ.,:;-_(){}[]-+*=<>'\"¿?¡!&%$@#/\\0123456789 "
					.indexOf(c) >= 0)
				ret.append(c);
		}
		return ret.toString().replaceAll("'", "''");
	}

	// Logging

	private static Logger logger = null;

	public static void setLogger(Logger logger) {
		BD.logger = logger;
	}

	// Método local para loggear (si no se asigna un logger externo, se asigna uno
	// local)


}
