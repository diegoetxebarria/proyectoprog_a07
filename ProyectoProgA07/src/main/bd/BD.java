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
	


}
