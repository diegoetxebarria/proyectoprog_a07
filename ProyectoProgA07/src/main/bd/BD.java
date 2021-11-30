package main.bd;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.logging.*;


public class BD {
	
	private static Exception lastError = null; // Nos informa del ultimo errror sql ocurrido

	private static Connection connection = null;
	
	public static Connection initBD(String nombreBD) {
		try {
			if (connection == null) {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
				log( "Conectada base de datos " + nombreBD, null);
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
	
	//metodo para logear
	private static void log( String msg, Throwable excepcion) {
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
	
	//Creacion de tablas
	public static Statement creacionTablas(Connection con) {
	
		try {
			Statement statement;
			statement = con.createStatement();
			statement.setQueryTimeout(10);
			statement.executeUpdate("create table if not exists usuario "
					+ "(nick string primary key, password string not null, fechaNacimiento integer, genero string)");
			statement.executeUpdate("create table if not exists pacman" + "(idJuego integer primary key, pacman string not null)");
			statement.executeUpdate("create table if not exists participacion "
					+ "(idParticipacion Integer primary key AUTOINCREMENT, " + "nick string not null,"
					+ "nivel integer not null, " + "idJuego integer not null,"
					+ "puntuacion integer not null, fecha integer," + "foreign key(idJuego) references pacman(idJuego),"
					+ "foreign key(nick) references usuario(nick))");
			statement.executeUpdate("create table if not exists nivel " + "(idJuego  integer not null," + "nick string not null,"
					+ "maxNivel integer not null," + " foreign key(idJuego) references pacman(idJuego),"
					+ " foreign key(nick) references participacion(nick))");

			//Informamos de que la base de datos ha sido creada
			log("Creada base de datos", null);
			return statement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lastError= e;
			log("Error en creación de base de datos", e);
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	//Reincio/Borrado de las tablas ya creadas
	
	public static Statement reinicioBD (Connection con, String tabla) {
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
	
	//Metodo para cerrar la base de datos

}
