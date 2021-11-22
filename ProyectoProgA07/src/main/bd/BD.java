package main.bd;


import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BD {
	

	
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
