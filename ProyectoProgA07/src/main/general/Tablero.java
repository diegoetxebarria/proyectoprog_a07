package main.general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Date;




//import main.bd.BD;

public class Tablero {
	
	Inicio inicio;

	public Tablero(String usuario) {
		Const.usuarioLogeado = usuario;
	}
	
	public static void main(String[] args) {
		//Lanzar Base de datos
		//BD.initBD();
		//Tablero tablero = new Tablero("Markel");
	}
	
	
	public int[] colorFondo = { 0, 0, 0 };
	public int[] colorPuntos = { 255, 255, 255 };
	public int[] colorParedes = { 24, 30, 182 };
	public int[] colorParedesFantasmas = { 182, 30, 24 };
	public int tamanyoRed = 30;

	protected static int[][] tabler = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1 },
			{ 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1 },
			{ 4, 2, 1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 2, 4 },
			{ 1, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 3, 3, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 1, 2, 1 },
			{ 1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1 },
			{ 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1 },
			{ 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 2, 1 },
			{ 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1 },
			{ 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1 },
			{ 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1 },
			{ 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1 },
			{ 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }

	};
	
	public Tablero(Tablero tablero, int nivel) {
		
		this.inicio = inicio;
		
	}
	private void setNivel( int nivel) {
		if(nivel< 1|| nivel>5) {
			throw new IllegalArgumentException("El valor del nivel tiene que estar entre 1 y 5");
		}
		for (int i = 0; i < Const.contNiv; i ++) {
			Const.contadNiv[i] = Const.niveles[nivel-1];
		}
	}
	
	public void seleccionNivel() {
		setNivel(Const.nivel);
	}
	
	
	
	private void iniciarVariable() {
		Const.colorLab = new Color(5, 100, 5);
		Const.dimension = new Dimension(400, 400);
		Const.fantx = new int[Const.velMaxFant];
		Const.fantdx = new int[Const.velMaxFant];
		Const.fanty = new int[Const.velMaxFant];
		Const.fantdy = new int[Const.velMaxFant];
		Const.fantvel = new int[Const.velMaxFant];
		Const.dx = new int[4];
		Const.dy = new int[4];

		Const.timer.start();
	}
	
	private void jugar(Graphics2D g2d) {
		if (Const.muerteEnYa) {
			asesinato();
		} else {

			moverPacman();
			//Faltan metodos 
		}
	}
	
	//Metodo para mover le pacman
	private void moverPacman() { 

		int pos;
		short ch;

		if (Const.exdx == -Const.pacmandx && Const.exdy == -Const.pacmandy) {
			Const.pacmandx = Const.exdx;
			Const.pacmandy = Const.exdy;
			Const.vistadx = Const.pacmandx;
			Const.vistady = Const.pacmandy;
		}

		if (Const.pacmanx % Const.tamanobloque == 0 && Const.pacmany % Const.tamanobloque == 0) {
			pos = Const.pacmanx / Const.tamanobloque + Const.bloques * (int) (Const.pacmany / Const.tamanobloque);
			ch = Const.screendata[pos];

			if ((ch & 16) != 0) {
				Const.screendata[pos] = (short) (ch & 15);
				Const.resultado++;

			}

			if (Const.exdx != 0 || Const.exdy != 0) {
				if (!((Const.exdx == -1 && Const.exdy == 0 && (ch & 1) != 0) || (Const.exdx == 1 && Const.exdy == 0 && (ch & 4) != 0)
						|| (Const.exdx == 0 && Const.exdy == -1 && (ch & 2) != 0) || (Const.exdx == 0 && Const.exdy == 1 && (ch & 8) != 0))) {
					Const.pacmandx = Const.exdx;
					Const.pacmandy = Const.exdy;
					Const.vistadx = Const.pacmandx;
					Const.vistady = Const.pacmandy;
				}
			}

			if ((Const.pacmandx == -1 && Const.pacmandy == 0 && (ch & 1) != 0) || (Const.pacmandx == 1 && Const.pacmandy == 0 && (ch & 4) != 0)
					|| (Const.pacmandx == 0 && Const.pacmandy == -1 && (ch & 2) != 0)
					|| (Const.pacmandx == 0 && Const.pacmandy == 1 && (ch & 8) != 0)) {
				Const.pacmandx = 0;
				Const.pacmandy = 0;

			}
		}
		Const.pacmanx = Const.pacmanx + Const.velocidadpacman * Const.pacmandx;
		Const.pacmany = Const.pacmany + Const.velocidadpacman * Const.pacmandy;
	}
	
	
	private void asesinato() {// Cuando lo asesinen los fantasmas

		Const.vidaspacman--;

		if (Const.vidaspacman == 0) {

			Date fecha = new Date();
			//Guardar datos falta meter
		}

		continuarNivel();
	}
	

	private void continuarNivel() {
		//Falta crear metodo
	}
	public int contarPuntos(int[][] tablero) {

		int cantidadPuntos = 0;

		for (int filas = 0; filas < tablero.length; filas++) {

			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {

				if (tablero[filas][columnas] == 2) {

					cantidadPuntos++;
				}
			}
		}

		return cantidadPuntos;
	}

	public int contarCerezas(int[][] tablero) {
		int cantidadCerezas = 0;

		for (int filas = 0; filas < tablero.length; filas++) {

			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {

				if (tablero[filas][columnas] == 5) {

					cantidadCerezas++;

				}
			}
		}
		return cantidadCerezas;
	}

	public int contarFresas(int[][] tablero) {
		int cantidadFresas = 0;

		for (int filas = 0; filas < tablero.length; filas++) {

			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {

				if (tablero[filas][columnas] == 5) {

					cantidadFresas++;

				}
			}
		}
		return cantidadFresas;
	}

	public int contarNaranjas(int[][] tablero) {
		int cantidadNaranjas = 0;

		for (int filas = 0; filas < tablero.length; filas++) {

			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {

				if (tablero[filas][columnas] == 5) {

					cantidadNaranjas++;

				}
			}
		}
		return cantidadNaranjas;
	}

	public int contarMelones(int[][] tablero) {
		int cantidadMelones = 0;

		for (int filas = 0; filas < tablero.length; filas++) {

			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {

				if (tablero[filas][columnas] == 5) {

					cantidadMelones++;

				}
			}
		}
		return cantidadMelones;
	}

	public int contarManzanas(int[][] tablero) {
		int cantidadManzanas = 0;

		for (int filas = 0; filas < tablero.length; filas++) {

			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {

				if (tablero[filas][columnas] == 5) {

					cantidadManzanas++;

				}
			}
		}
		return cantidadManzanas;
	}

	public int[] getColorFondo() {
		return colorFondo;
	}

	public int[] getColorPuntos() {
		return colorPuntos;
	}

	public int[] getColorParedes() {
		return colorParedes;
	}

	public int[] getColorParedesFantasmas() {
		return colorParedesFantasmas;
	}

}
