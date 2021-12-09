package main.general;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Fantasma extends Figura {
	public int pared;
	public boolean empezar = true;
	public int quadrante;
	public int countFantasma;
	public boolean paredArriba;
	public boolean paredAbajo;
	public boolean paredDerecha;
	public boolean paredIzquierda;

	public void actualizacionDireccion(int x) {
		this.setDireccionMovimiento(x);
	}

	public Fantasma(Point p, Color Color) {
		this.setRadio(30);
		this.setColor(Color);
		this.setPosicion(p.x, p.y);
		this.setDireccionMovimiento(2);
		this.setDireccion(2);
		this.setVelocidad(6);
	}

	public int getPared() {
		return this.pared;
	}

	public boolean getEmpezar() {
		return this.empezar;
	}

	public int getQuadrante() {
		return this.quadrante;
	}

	public boolean getParedArriba() {
		return this.paredArriba;
	}

	public boolean getParedAbajo() {
		return this.paredAbajo;
	}

	public boolean getParedDerechat() {
		return this.paredDerecha;
	}

	public boolean getParedIzquierda() {
		return this.paredIzquierda;
	}

	public void setPared(int pared) {
		this.pared = pared;
	}

	public void setEmpezar(boolean empezar) {
		this.empezar = empezar;
	}

	public void setQuadrante(int quadrante) {
		this.quadrante = quadrante;
	}

	public void setParedArriba(boolean paredArriba) {
		this.paredArriba = paredArriba;
	}

	public void setParedAbajo(boolean paredAbajo) {
		this.paredAbajo = paredAbajo;
	}

	public void setParedDerecha(boolean paredDerecha) {
		this.paredDerecha = paredDerecha;
	}

	public void setParedIzquierda(boolean paredIzquierda) {
		this.paredIzquierda = paredIzquierda;
	}

	public void reset(int tamanyoRed) {
		setPosicion(14 * tamanyoRed, 9 * tamanyoRed);
		setEmpezar(true);
		setDireccionMovimiento(2);
	}

	public void paredAnteFantasma(int count, int[][] tablero, int tamanyoRed) {
		if (this.getEmpezar()) {
			if (count <= 3 * tamanyoRed / this.getVelocidad()) {

				this.setDireccion(2);
			} else if (count <= 5 * tamanyoRed / this.getVelocidad()) {

				this.setDireccion(1);
			} else
				this.setEmpezar(false);
		}

		switch (this.getDireccionMovimiento()) {
		case 1: // derecha
			if (tablero[this.getPosicion().y / tamanyoRed][this.getPosicion().x / tamanyoRed + 1] == 1
					&& tablero[(this.getPosicion().y - 1) / tamanyoRed][this.getPosicion().x / tamanyoRed] == 1) {
				this.setPared(2);
				System.out.print("2");
			} // derechaArriba
			else if (tablero[this.getPosicion().y / tamanyoRed][this.getPosicion().x / tamanyoRed + 1] == 1
					&& tablero[this.getPosicion().y / tamanyoRed + 1][this.getPosicion().x / tamanyoRed] == 1) {
				this.setPared(3);
				System.out.print("3");
			} // derechaAbajo
			else if (tablero[this.getPosicion().y / tamanyoRed][this.getPosicion().x / tamanyoRed + 1] == 1) {
				this.setPared(1);
				System.out.print("1");
			} else
				this.setPared(0);
			break;

		case 2: // izquierda
			if (tablero[this.getPosicion().y / tamanyoRed][(this.getPosicion().x - 1) / tamanyoRed] == 1
					&& tablero[(this.getPosicion().y - 1) / tamanyoRed][this.getPosicion().x / tamanyoRed] == 1)
				this.setPared(4); // izquierdaArriba
			else if (tablero[this.getPosicion().y / tamanyoRed][(this.getPosicion().x - 1) / tamanyoRed] == 1
					&& tablero[this.getPosicion().y / tamanyoRed + 1][this.getPosicion().x / tamanyoRed] == 1)
				this.setPared(5); // izquierdaAbajo
			else if (tablero[this.getPosicion().y / tamanyoRed][(this.getPosicion().x - 1) / tamanyoRed] == 1)
				this.setPared(1);
			else
				this.setPared(0);
			break;

		case 3: // arriba
			if (tablero[(this.getPosicion().y - 1) / tamanyoRed][this.getPosicion().x / tamanyoRed] == 1
					&& tablero[this.getPosicion().y / tamanyoRed][this.getPosicion().x / tamanyoRed + 1] == 1)
				this.setPared(2); // arribaDerecha
			else if (tablero[(this.getPosicion().y - 1) / tamanyoRed][this.getPosicion().x / tamanyoRed] == 1
					&& tablero[this.getPosicion().y / tamanyoRed][(this.getPosicion().x - 1) / tamanyoRed] == 1)
				this.setPared(4); // arribaIzquierda
			else if (tablero[(this.getPosicion().y - 1) / tamanyoRed][this.getPosicion().x / tamanyoRed] == 1)
				this.setPared(1);
			else
				this.setPared(0);
			break;

		case 4: // abajo
			if (tablero[this.getPosicion().y / tamanyoRed + 1][this.getPosicion().x / tamanyoRed] == 1
					&& tablero[this.getPosicion().y / tamanyoRed][this.getPosicion().x / tamanyoRed + 1] == 1)
				this.setPared(3); // abajoDerecha
			else if (tablero[this.getPosicion().y / tamanyoRed + 1][this.getPosicion().x / tamanyoRed] == 1
					&& tablero[this.getPosicion().y / tamanyoRed][(this.getPosicion().x - 1) / tamanyoRed] == 1)
				this.setPared(5);// abajoIzquierda
			else if (tablero[this.getPosicion().y / tamanyoRed + 1][this.getPosicion().x / tamanyoRed] == 1)
				this.setPared(1);
			else
				this.setPared(0);
			break;
		default:
		}
	}

	// randomiza direccion de movimiento
	public void updateDireccion(Point PacPosicion) {
		Random ran = new Random();
		if (this.getPared() == 1 && !this.getEmpezar()) {
			if (this.getQuadrante() == 1)
				this.setDireccion(ran.nextInt(2) * 3 + 1);
			if (this.getQuadrante() == 2)
				this.setDireccion(ran.nextInt(2) + 1);
			if (this.getQuadrante() == 3)
				this.setDireccion(ran.nextInt(2) + 2);
			if (this.getQuadrante() == 4)
				this.setDireccion(ran.nextInt(2) + 3);
		} else if (this.getPared() == 2 && !this.getEmpezar() && this.getQuadrante() == 1)
			this.setDireccion(4);
		else if (this.getPared() == 2 && !this.getEmpezar() && this.getQuadrante() == 2)
			this.setDireccion(ran.nextInt(2) + 3);
		else if (this.getPared() == 2 && !this.getEmpezar() && this.getQuadrante() == 3)
			this.setDireccion(3);
		else if (this.getPared() == 2 && !this.getEmpezar() && this.getQuadrante() == 4)
			this.setDireccion(ran.nextInt(2) + 3);
		else if (this.getPared() == 3 && !this.getEmpezar() && this.getQuadrante() == 1)
			this.setDireccion(ran.nextInt(2) + 2);
		else if (this.getPared() == 3 && !this.getEmpezar() && this.getQuadrante() == 2)
			this.setDireccion(2);
		else if (this.getPared() == 3 && !this.getEmpezar() && this.getQuadrante() == 3)
			this.setDireccion(ran.nextInt(2) + 2);
		else if (this.getPared() == 3 && !this.getEmpezar() && this.getQuadrante() == 4)
			this.setDireccion(3);
		else if (this.getPared() == 4 && !this.getEmpezar() && this.getQuadrante() == 1)
			this.setDireccion(ran.nextInt(2) * 3 + 1);
		else if (this.getPared() == 4 && !this.getEmpezar() && this.getQuadrante() == 2)
			this.setDireccion(1);
		else if (this.getPared() == 4 && !this.getEmpezar() && this.getQuadrante() == 3)
			this.setDireccion(ran.nextInt(2) * 3 + 1);
		else if (this.getPared() == 4 && !this.getEmpezar() && this.getQuadrante() == 4)
			this.setDireccion(4);
		else if (this.getPared() == 5 && !this.getEmpezar() && this.getQuadrante() == 1)
			this.setDireccion(1);
		else if (this.getPared() == 5 && !this.getEmpezar() && this.getQuadrante() == 2)
			this.setDireccion(ran.nextInt(2) + 1);
		else if (this.getPared() == 5 && !this.getEmpezar() && this.getQuadrante() == 3)
			this.setDireccion(2);
		else if (this.getPared() == 5 && !this.getEmpezar() && this.getQuadrante() == 4)
			this.setDireccion(ran.nextInt(2) + 1);
	}
}
