package main.general;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.ventanas.NivelPacman;
import main.bd.BD;
import main.musica.Audio;
import main.musica.SoundJLayer;


public class Tablero extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Inicio inicio;

	public Tablero(String usuario) {
		Const.usuarioLogeado = usuario;
	}

	public static void main(String[] args) {
		BD.initData();
		Tablero tablero = new Tablero("Markel");
		tablero.seleccionNivel();
	}

	public int[] colorFondo = { 0, 0, 0 };
	public int[] colorPuntos = { 255, 255, 255 };
	public int[] colorParedes = { 24, 30, 182 };
	public int[] colorParedesFantasmas = { 182, 30, 24 };
	public int tamanyoRed = 30;

	public Tablero(Inicio inicio, int nivel) {

		this.inicio = inicio;
		hiloMusica(Audio.class.getResource("/musica/snowman.wav"));

		cargarImagenes();
		iniciarVariable();
		setNivel(nivel);

		addKeyListener(new TAdapter());
		setFocusable(true);

		setBackground(Color.BLACK);
		setDoubleBuffered(true);

	}

	private void setNivel(int nivel) {
		if (nivel < 1 || nivel > 6) {
			throw new IllegalArgumentException("El valor del nivel tiene que estar entre 1 y 6");
		}
		for (int i = 0; i < Const.fantasmasN; i++) {
			Const.fantvel[i] = Const.velocidades[nivel - 1];
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
		Const.screendata = new short[Const.leveldata.length];

		Const.timer = new Timer(40, this);
		Const.timer.start();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		initGame();
	}

	private void doAnim() {
		Const.animadapacman--;

		if (Const.animadapacman <= 0) {
			Const.animadapacman = Const.retrasopacman;
			Const.pacmananimpos = Const.pacmananimpos + Const.animadorpacman;

			if (Const.pacmananimpos == (Const.cuentapacman - 1) || Const.pacmananimpos == 0) {
				Const.animadorpacman = -Const.animadorpacman;
			}
		}

	}

	private void jugar(Graphics2D g2d) {
		if (Const.muerteEnYa) {
			asesinato();
		} else {

			moverPacman();
			pintarPacman(g2d);
			moverFantasma(g2d);
			resueltoYa();
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

	private void pintarFant(Graphics2D g2d, int x, int y) { // Pintar el fantasma

		g2d.drawImage(Const.fant, x, y, this);
	}

	private void moverFantasma(Graphics2D g2d) {// mover fantasmas

		short i;
		int pos;
		int count;

		for (i = 0; i < Const.fantasmasN; i++) {
			if (Const.fantx[i] % Const.tamanobloque == 0 && Const.fanty[i] % Const.tamanobloque == 0) {
				pos = Const.fantx[i] / Const.tamanobloque + Const.bloques * (int) (Const.fanty[i] / Const.tamanobloque);

				count = 0;

				if ((Const.screendata[pos] & 1) == 0 && Const.fantdx[i] != 1) {
					Const.dx[count] = -1;
					Const.dy[count] = 0;
					count++;
				}

				if ((Const.screendata[pos] & 2) == 0 && Const.fantdy[i] != 1) {
					Const.dx[count] = 0;
					Const.dy[count] = -1;
					count++;
				}

				if ((Const.screendata[pos] & 4) == 0 && Const.fantdx[i] != -1) {
					Const.dx[count] = 1;
					Const.dy[count] = 0;
					count++;
				}

				if ((Const.screendata[pos] & 8) == 0 && Const.fantdy[i] != -1) {
					Const.dx[count] = 0;
					Const.dy[count] = 1;
					count++;
				}

				if (count == 0) {

					if ((Const.screendata[pos] & 15) == 15) {
						Const.fantdx[i] = 0;
						Const.fantdy[i] = 0;
					} else {
						Const.fantdx[i] = -Const.fantdx[i];
						Const.fantdy[i] = -Const.fantdy[i];
					}

				} else {

					count = (int) (Math.random() * count);

					if (count > 3) {
						count = 3;
					}

					Const.fantdx[i] = Const.dx[count];
					Const.fantdy[i] = Const.dy[count];
				}

			}

			Const.fantx[i] = Const.fantx[i] + (Const.fantdx[i] * Const.fantvel[i]);
			Const.fanty[i] = Const.fanty[i] + (Const.fantdy[i] * Const.fantvel[i]);
			pintarFant(g2d, Const.fantx[i] + 1, Const.fanty[i] + 1);

			if (Const.pacmanx > (Const.fantx[i] - 12) && Const.pacmanx < (Const.fantx[i] + 12)
					&& Const.pacmany > (Const.fanty[i] - 12) && Const.pacmany < (Const.fanty[i] + 12)
					&& Const.inigame) {

				Const.muerteEnYa = true;
			}
		}
	}

	private void resueltoYa() {
		short i = 0;
		boolean acabado = true;

		while (i < Const.bloques * Const.bloques && acabado) {

			if ((Const.screendata[i] & 48) != 0) {
				acabado = false;
			}

			i++;
		}

		if (acabado) {

			Const.resultado += 50;

			if (Const.fantasmasN < Const.velMaxFant) {
				Const.fantasmasN++;
			}

			if (Const.velactual < Const.velactual) {
				Const.velactual++;
			}
			Date fecha = new Date();
			this.inicio.guardarPuntuacion(Const.usuarioLogeado, fecha, Const.nivel, Const.resultado);
			this.inicio.guardarNuevoMaxNivel(2, Const.nivel, Const.usuarioLogeado);
			initLevel();
		}
	}

	private void initGame() {

		Const.vidaspacman = 3;

		Const.resultado = 0;
		initLevel();
		Const.fantasmasN = 6;
		Const.velactual = 3;
	}

	private void initLevel() {

		int i;
		for (i = 0; i < Const.bloques * Const.bloques; i++) {
			Const.screendata[i] = Const.leveldata[i];

		}

		continuarNivel();
	}

	private void asesinato() {// Cuando lo asesinen los fantasmas

		Const.vidaspacman--;
		SoundJLayer soundToPlay = new SoundJLayer("src/musicPacman/muerte_pacman.mp3");
		soundToPlay.play();

		if (Const.vidaspacman == 0) {
			Audio.paraAudioEnHilo(Audio.class.getResource("/musicPacman/Egundasantimamiña.wav"));
			Const.inigame = false;
			Date fecha = new Date();
			this.inicio.guardarPuntuacion(Const.usuarioLogeado, fecha, Const.nivel, Const.resultado);

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

	class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if (Const.inigame) {
				if (key == KeyEvent.VK_LEFT) {
					Const.exdx = -1;
					Const.exdy = 0;
				} else if (key == KeyEvent.VK_RIGHT) {
					Const.exdx = 1;
					Const.exdy = 0;
				} else if (key == KeyEvent.VK_UP) {
					Const.exdx = 0;
					Const.exdy = -1;
				} else if (key == KeyEvent.VK_DOWN) {
					Const.exdx = 0;
					Const.exdy = 1;
				} else if (key == KeyEvent.VK_P) {
					Const.inigame = false;
					if (Const.timer.isRunning()) {
						Const.timer.stop();
					} else {
						Const.timer.start();
					}
				} else if (key == KeyEvent.VK_C) {
					Const.inigame = true;
					if (Const.timer.isRunning()) {
						Const.timer.stop();
					} else {
						Const.timer.start();
					}
				}

			} else {
				// Define la letra para inicio del juego
				if (key == ' ') {
					Const.inigame = true;
					initGame();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

			int key = e.getKeyCode();

			if (key == Event.LEFT || key == Event.RIGHT || key == Event.UP || key == Event.DOWN) {
				Const.exdx = 0;
				Const.exdy = 0;
			}
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

	private void enseniarResultado(Graphics2D g) {
		int i;
		String s;

		g.setFont(Const.smallfont);
		g.setColor(new Color(96, 128, 255));
		s = "Resultado: " + Const.resultado;
		g.drawString(s, Const.tamanopantalla / 2 + 96, Const.tamanopantalla + 16);

		for (i = 0; i < Const.vidaspacman; i++) {
			g.drawImage(Const.pacman3derecha, i * 28 + 8, Const.tamanopantalla + 1, this);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		pintar(g);
	}

	private void pintar(Graphics g) {

		Graphics2D g2d1 = (Graphics2D) g;

		g2d1.setColor(Color.black);
		g2d1.fillRect(0, 0, Const.dimension.width, Const.dimension.height);

		dibujarLaberinto(g2d1);
		enseniarResultado(g2d1);
		doAnim();

		if (Const.inigame) {
			jugar(g2d1);
		} else {
			if (Const.vidaspacman > 0) {
				mostarPanr(g2d1);
			} else {
				System.out.println("He llegado al cambio de ventana");
				new NivelPacman(Const.usuarioLogeado).setVisible(true);
				this.inicio.dispose();
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

//	public int contarPuntos(int[][] tablero) {
//
//		int cantidadPuntos = 0;
//
//		for (int filas = 0; filas < tablero.length; filas++) {
//
//			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {
//
//				if (tablero[filas][columnas] == 2) {
//
//					cantidadPuntos++;
//				}
//			}
//		}
//
//		return cantidadPuntos;
//	}
//
//	public int contarCerezas(int[][] tablero) {
//		int cantidadCerezas = 0;
//
//		for (int filas = 0; filas < tablero.length; filas++) {
//
//			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {
//
//				if (tablero[filas][columnas] == 5) {
//
//					cantidadCerezas++;
//
//				}
//			}
//		}
//		return cantidadCerezas;
//	}
//
//	public int contarFresas(int[][] tablero) {
//		int cantidadFresas = 0;
//
//		for (int filas = 0; filas < tablero.length; filas++) {
//
//			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {
//
//				if (tablero[filas][columnas] == 5) {
//
//					cantidadFresas++;
//
//				}
//			}
//		}
//		return cantidadFresas;
//	}
//
//	public int contarNaranjas(int[][] tablero) {
//		int cantidadNaranjas = 0;
//
//		for (int filas = 0; filas < tablero.length; filas++) {
//
//			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {
//
//				if (tablero[filas][columnas] == 5) {
//
//					cantidadNaranjas++;
//
//				}
//			}
//		}
//		return cantidadNaranjas;
//	}

//	public int contarMelones(int[][] tablero) {
//		int cantidadMelones = 0;
//
//		for (int filas = 0; filas < tablero.length; filas++) {
//
//			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {
//
//				if (tablero[filas][columnas] == 5) {
//
//					cantidadMelones++;
//
//				}
//			}
//		}
//		return cantidadMelones;
//	}

//	public int contarManzanas(int[][] tablero) {
//		int cantidadManzanas = 0;
//
//		for (int filas = 0; filas < tablero.length; filas++) {
//
//			for (int columnas = 0; columnas < tablero[filas].length; columnas++) {
//
//				if (tablero[filas][columnas] == 5) {
//
//					cantidadManzanas++;
//
//				}
//			}
//		}
//		return cantidadManzanas;
//	}


	public void hiloMusica(URL recurso) {
		Audio.lanzaAudioEnHilo(recurso);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();

	}

}
