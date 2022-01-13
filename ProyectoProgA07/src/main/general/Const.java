package main.general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.logging.Logger;


public class Const {
	
	static Image i; //listo
	static Color colorLab; //listo
	static Dimension dimension; //
	static javax.swing.Timer timer;
	
	static String usuarioLogeado;
	
	static Image fant, pacman1, pacman2arriba, pacman2izquierda, pacman2derecha, pacman2abajo;
	static Image pacman3arriba, pacman3abajo, pacman3izquierda, pacman3derecha;
	static Image pacman4arriba, pacman4abajo, pacman4izquierda, pacma4derecha;
	static Image fresa;
	static Image melon;
	static Image cereza;
	static Image naranja;
	static Image manzana;
	
	
	
	static int nivel = 1;
	static int contNiv = 6;
	static int vidaspacman = 3; 
	static int pacmanx, pacmany;
	static int pacmandx, pacmandy;
	static int exdx, exdy;
	static int vistadx, vistady;
	static int resultado;
	static int pacmananimpos = 0;
	static int fantasmasN = 6;
	static int velactual = 3;
	static int animadorpacman = 1;
	
	
	
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
	static boolean inigame = false;
	
	
	final static int niveles[] = { 1, 2, 3, 4, 5 };
	final static int velocidades[] = { 1, 2, 3, 4, 6, 8 };
	final static Color newcolor = new Color(192, 192, 0);
	final static int tamanobloque = 24;
	final static int bloques = 15;
	final static int tamanopantalla = bloques * tamanobloque;
	final static int velMaxFant = 12;
	final static int velocidadpacman = 6;
	final static int retrasopacman = 2;
	final static int cuentapacman = 4;
	static int animadapacman = retrasopacman;
	
	static final short leveldata[] = { 19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22, 21, 0, 0, 0, 17, 16,
			16, 16, 16, 16, 16, 16, 16, 16, 20, 21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 21, 0, 0, 0,
			17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20, 17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20, 17,
			16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20, 25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0,
			21, 1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21, 1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0,
			21, 1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21, 1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16,
			20, 0, 21, 1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21, 1, 17, 16, 16, 16, 16, 16, 16, 16, 16,
			16, 16, 20, 0, 21, 1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8,
			25, 24, 24, 24, 28 };

	static final Font smallfont = new Font("Helvetica", Font.BOLD, 14);

	static Logger log = Logger.getLogger(Inicio.class.getName());
}
