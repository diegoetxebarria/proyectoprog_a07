package main.mod;

import java.util.Date;

public interface Juego {

	public void jugarA();

	public void setNivel(int level);

	public void guardarPuntuacion(String nick, Date fecha, int nivel, int puntuacion);

	public void guardarNuevoMaxNivel(Integer idJuego, Integer nivel, String nick);

}
