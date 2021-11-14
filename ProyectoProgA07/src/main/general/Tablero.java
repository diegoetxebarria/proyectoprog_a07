package main.general;

public class Tablero {

	public int[] colorFondo = { 0, 0, 0 };
	public int[] colorPuntos = { 255, 255, 255 };
	public int[] colorParedes = { 24, 30, 182 };
	public int[] colorParedesFantasmas = { 182, 30, 24 };
	public int tamanyoRed = 30;

	protected static int[][] tablero = {
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
