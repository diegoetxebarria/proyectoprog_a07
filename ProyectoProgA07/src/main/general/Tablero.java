package main.general;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tablero extends JPanel {

	Inicio inicio;

	public Tablero(String usuario) {
		Const.usuarioLogeado = usuario;
	}

	public static void main(String[] args) {
		// Lanzar Base de datos
		// BD.initBD();
		// Tablero tablero = new Tablero("Markel");
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

	private void setNivel(int nivel) {
		if (nivel < 1 || nivel > 5) {
			throw new IllegalArgumentException("El valor del nivel tiene que estar entre 1 y 5");
		}
		for (int i = 0; i < Const.contNiv; i++) {
			Const.contadNiv[i] = Const.niveles[nivel - 1];
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
			pintarPacman(g2d);
			// Faltan metodos
		}
	}

	// Metodo para mover le pacman
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
				if (!((Const.exdx == -1 && Const.exdy == 0 && (ch & 1) != 0)
						|| (Const.exdx == 1 && Const.exdy == 0 && (ch & 4) != 0)
						|| (Const.exdx == 0 && Const.exdy == -1 && (ch & 2) != 0)
						|| (Const.exdx == 0 && Const.exdy == 1 && (ch & 8) != 0))) {
					Const.pacmandx = Const.exdx;
					Const.pacmandy = Const.exdy;
					Const.vistadx = Const.pacmandx;
					Const.vistady = Const.pacmandy;
				}
			}

			if ((Const.pacmandx == -1 && Const.pacmandy == 0 && (ch & 1) != 0)
					|| (Const.pacmandx == 1 && Const.pacmandy == 0 && (ch & 4) != 0)
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
			// Guardar datos falta meter
		}

		continuarNivel();
	}

	private void pintarPacman(Graphics2D g2d) { // Pintar el pacman

		if (Const.vistadx == -1) {
			pintarvidaspacman(g2d);

		} else if (Const.vistadx == 1) {
			pintarPacmanIzquierda(g2d);
		} else if (Const.vistady == -1) {
			pintarPacmanArriba(g2d);
		} else {
			pintarPacmanAbajo(g2d);

		}
	}

	private void continuarNivel() {
		short i;
		int dx = 1;
		int random;

		for (i = 0; i < Const.fantasmasN; i++) {

			Const.fanty[i] = 4 * Const.tamanobloque;
			Const.fantx[i] = 4 * Const.tamanobloque;
			Const.fantdy[i] = 0;
			Const.fantdx[i] = dx;
			dx = -dx;
			random = (int) (Math.random() * (Const.velactual + 1));

			if (random > Const.velactual) {
				random = Const.velactual;

			}

			Const.fantvel[i] = Const.velocidades[random];

		}

		Const.pacmanx = 7 * Const.tamanobloque;
		Const.pacmany = 11 * Const.tamanobloque;
		Const.pacmandx = 0;
		Const.pacmandy = 0;
		Const.exdx = 0;
		Const.exdy = 0;
		Const.vistadx = -1;
		Const.vistady = 0;
		Const.muerteEnYa = false;
	}

	private void pintarPacmanArriba(Graphics2D g2d1) {

		switch (Const.pacmananimpos) {
		case 1:
			g2d1.drawImage(Const.pacman2arriba, Const.pacmanx + 1, Const.pacmany + 1, this);

			break;
		case 2:
			g2d1.drawImage(Const.pacman3arriba, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		case 3:
			g2d1.drawImage(Const.pacman4arriba, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		default:
			g2d1.drawImage(Const.pacman1, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		}
	}

	private void pintarPacmanAbajo(Graphics2D g2d1) {

		switch (Const.pacmananimpos) {
		case 1:
			g2d1.drawImage(Const.pacman2abajo, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		case 2:
			g2d1.drawImage(Const.pacman3abajo, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		case 3:
			g2d1.drawImage(Const.pacman4abajo, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		default:
			g2d1.drawImage(Const.pacman1, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		}
	}

	private void pintarvidaspacman(Graphics2D g2d1) {

		switch (Const.pacmananimpos) {
		case 1:
			g2d1.drawImage(Const.pacman2izquierda, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		case 2:
			g2d1.drawImage(Const.pacman3izquierda, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		case 3:
			g2d1.drawImage(Const.pacman4izquierda, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		default:
			g2d1.drawImage(Const.pacman1, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		}

	}

	private void pintarPacmanIzquierda(Graphics2D g2d1) {

		switch (Const.pacmananimpos) {
		case 1:
			g2d1.drawImage(Const.pacman2derecha, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		case 2:
			g2d1.drawImage(Const.pacman3derecha, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		case 3:
			g2d1.drawImage(Const.pacma4derecha, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		default:
			g2d1.drawImage(Const.pacman1, Const.pacmanx + 1, Const.pacmany + 1, this);
			break;
		}

	}

	private void dibujarLaberinto(Graphics2D g2d) {

		short i = 0;
		int x, y;

		for (y = 0; y < Const.tamanopantalla; y += Const.tamanobloque) {
			for (x = 0; x < Const.tamanopantalla; x += Const.tamanobloque) {

				g2d.setColor(Const.colorLab);
				g2d.setStroke(new BasicStroke(2));

				if ((Const.screendata[i] & 1) != 0) {
					g2d.drawLine(x, y, x, y + Const.tamanobloque - 1);
				}

				if ((Const.screendata[i] & 2) != 0) {
					g2d.drawLine(x, y, x + Const.tamanobloque - 1, y);
				}

				if ((Const.screendata[i] & 4) != 0) {
					g2d.drawLine(x + Const.tamanobloque - 1, y, x + Const.tamanobloque - 1, y + Const.tamanobloque - 1);
				}

				if ((Const.screendata[i] & 8) != 0) {
					g2d.drawLine(x, y + Const.tamanobloque - 1, x + Const.tamanobloque - 1, y + Const.tamanobloque - 1);
				}

				if ((Const.screendata[i] & 16) != 0) {

					g2d.setColor(Const.newcolor);
					g2d.fillRect(x + 11, y + 11, 2, 2);
				}

				i++;
			}
		}
	}

	private void pintar(Graphics g) {

		Graphics2D g2d1 = (Graphics2D) g;

		g2d1.setColor(Color.black);
		g2d1.fillRect(0, 0, Const.dimension.width, Const.dimension.height);

		dibujarLaberinto(g2d1);

		if (Const.inigame) {
			jugar(g2d1);
		} else {
			if (Const.vidaspacman > 0) {
				mostarPanr(g2d1);
			} else {
				// Añadir
				this.Inicio.dispose();
			}
		}

		g2d1.drawImage(Const.i, 5, 5, this);
		Toolkit.getDefaultToolkit().sync();
		g2d1.dispose();
	}

	private void mostarPanr(Graphics2D g2d) {

		g2d.setColor(new Color(236, 105, 100));
		g2d.fillRect(50, Const.tamanopantalla / 2 - 30, Const.tamanopantalla - 100, 50);
		g2d.setColor(Color.pink);
		g2d.drawRect(50, Const.tamanopantalla / 2 - 30, Const.tamanopantalla - 100, 50);
		g2d.drawString("PAUSA (P)", 90, 390);
		g2d.drawString("CONTINUAR (C)", 165, 390);

		String s = "Para comenzar toca la tecla espaciadora";
		Font small = new Font("Helvetica", Font.BOLD, 12);
		FontMetrics metr = this.getFontMetrics(small);

		g2d.setColor(Color.white);
		g2d.setFont(small);
		g2d.drawString(s, (Const.tamanopantalla - metr.stringWidth(s)) / 2, Const.tamanopantalla / 2);
	}

	private void cargarImagenes() {

		Const.fant = new ImageIcon(getClass().getResource("/img/pacman/fant.gif")).getImage();
		Const.pacman1 = new ImageIcon(getClass().getResource("/img/pacman/pacman1.gif")).getImage();
		Const.pacman2arriba = new ImageIcon(getClass().getResource("/img/pacman/pacman2arriba.gif")).getImage();
		Const.pacman3arriba = new ImageIcon(getClass().getResource("/img/pacman/pacman3arriba.gif")).getImage();
		Const.pacman4arriba = new ImageIcon(getClass().getResource("/img/pacman/pacman4arriba.gif")).getImage();
		Const.pacman2abajo = new ImageIcon(getClass().getResource("/img/pacman/pacman2abajo.gif")).getImage();
		Const.pacman3abajo = new ImageIcon(getClass().getResource("/img/pacman/pacman3abajo.gif")).getImage();
		Const.pacman4abajo = new ImageIcon(getClass().getResource("/img/pacman/pacman4abajo.gif")).getImage();
		Const.pacman2izquierda = new ImageIcon(getClass().getResource("/img/pacman/pacman2izquierda.gif")).getImage();
		Const.pacman3izquierda = new ImageIcon(getClass().getResource("/img/pacman/pacman3izquierda.gif")).getImage();
		Const.pacman4izquierda = new ImageIcon(getClass().getResource("/img/pacman/pacman4izquierda.gif")).getImage();
		Const.pacman2derecha = new ImageIcon(getClass().getResource("/img/pacman/pacman2derecha.gif")).getImage();
		Const.pacman3derecha = new ImageIcon(getClass().getResource("/img/pacman/pacman3derecha.gif")).getImage();
		Const.pacma4derecha = new ImageIcon(getClass().getResource("/img/pacman/pacman4derecha.gif")).getImage();

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
