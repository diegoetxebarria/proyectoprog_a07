package main.general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

public class Const {
	
	static Color colorLab;
	static Dimension dimension;
	
	static javax.swing.Timer timer;
	
	static String usuarioLogeado;
	
	static Image pacman1, pacman2arriba, pacman2izquierda, pacman2derecha, pacman2abajo;
	static Image pacman3arriba, pacman3abajo, pacman3izquierda, pacman3derecha;
	static Image pacman4arriba, pacman4abajo, pacman4izquierda, pacma4derecha;
	
	static int nivel = 1;
	static int contNiv = 6;
	static int vidaspacman = 3; 
	static int pacmanx, pacmany;
	static int pacmandx, pacmandy;
	static int exdx, exdy;
	static int vistadx, vistady;
	static int resultado;
	static int pacmananimpos = 0;
	
	static int[] contadNiv;
	static int[] dx;
	static int[] dy;
	static int[] fantx;
	static int[] fanty;
	static int[] fantdx;
	static int[] fantdy;
	static int[] fantvel;
	
	static short[] screendata;
	
	static boolean muerteEnYa;
	
	
	final static int niveles[] = { 1, 2, 3, 4, 5 };
	final static int tamanobloque = 24;
	final static int bloques = 15;
	final static int velMaxFant = 12;
	final static int velocidadpacman = 6;

}
