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

	}

}
