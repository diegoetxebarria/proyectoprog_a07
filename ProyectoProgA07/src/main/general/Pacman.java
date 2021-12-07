package main.general;

import java.awt.Color;
import java.awt.Point;

public class Pacman extends Figura {

	public int vinculoMin;
	public int vinculoMax;
	public int vidas;
	public boolean bocaAbierta;
	public boolean contactoConFantasma;
	public int puntuacion;

	public int getVinculoMin() {
		return vinculoMin;
	}

	public void setVinculoMin(int vinculoMin) {
		this.vinculoMin = vinculoMin;
	}

	public int getVinculoMax() {
		return vinculoMax;
	}

	public void setVinculoMax(int vinculoMax) {
		this.vinculoMax = vinculoMax;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public boolean isBocaAbierta() {
		return bocaAbierta;
	}

	public void setBocaAbierta(boolean bocaAbierta) {
		this.bocaAbierta = bocaAbierta;
	}

	public boolean isContactoConFantasma() {
		return contactoConFantasma;
	}

	public void setContactoConFantasma(boolean contactoConFantasma) {
		this.contactoConFantasma = contactoConFantasma;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	// perder vidas
	public boolean perderVidas(int[][] tablero, Point posicionFantasma, int tamanyoRed) {
		boolean vidaPerdida = false;
		try {
			if ((int) (this.getPosicion().x + tamanyoRed / 2)
					/ tamanyoRed == (int) (posicionFantasma.x + tamanyoRed / 2) / tamanyoRed
					&& (int) (this.getPosicion().y + tamanyoRed / 2)
							/ tamanyoRed == (int) (posicionFantasma.y + tamanyoRed / 2) / tamanyoRed) {
				vidaPerdida = true;
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			// Fallo("comerPuntos", "ArrayIndexOutOfBoundsException");
		}
		return vidaPerdida;

	}

	public boolean derrotado() {
		boolean muerto = false;
		if (this.getVidas() == -1) {
			muerto = true;
		}
		return muerto;
	}

	public void actualizacionDireccion(int x) {
		this.setDireccion(x);
	}

	public void reset(int tamanyoRed) {
		setVidas(this.getVidas() - 1);
		setPosicion(19 * tamanyoRed, 19 * tamanyoRed);
		setDireccionMovimiento(2);
		setDireccion(1);
	}

	public void gameReset(int tamanyoRed) {
		this.setPosicion(19 * tamanyoRed, 19 * tamanyoRed);
		this.setVelocidad(5);
		this.setDireccionMovimiento(2);
		this.setColor(Color.yellow);
		this.setRadio(tamanyoRed * 5 / 6);
		this.setDireccionMovimiento(1);
		this.setPuntuacion(0);
		this.setVinculoMin(45);
		this.setVinculoMax(275);
		this.setVidas(2);
		this.setBocaAbierta(true);
		this.setContactoConFantasma(false);
	}

	public void comerPuntos(int[][] tablero, int tamanyoRed) {
		int punkt = tablero[getPosicion().y / tamanyoRed][getPosicion().x / tamanyoRed];
		try {
			if (punkt == 2 && getPosicion().x % tamanyoRed == 0 && getPosicion().y % tamanyoRed == 0) {
				tablero[getPosicion().y / tamanyoRed][getPosicion().x / tamanyoRed] = 0;
				this.setPuntuacion(this.getPuntuacion() + 100);
			}
			if (punkt == 5 && getPosicion().x % tamanyoRed == 0 && getPosicion().y % tamanyoRed == 0) {
				tablero[getPosicion().y / tamanyoRed][getPosicion().x / tamanyoRed] = 0;
				this.setPuntuacion(this.getPuntuacion() + 500);
			}

		} catch (ArrayIndexOutOfBoundsException exception) {
			// Fallo("comerPuntos", "ArrayIndexOutOfBoundsException");
		}
	}

	public Pacman(int tamanyoRed) {
		this.setPosicion(19 * tamanyoRed, 19 * tamanyoRed);
		this.setVelocidad(5);
		this.setDireccionMovimiento(2);
		this.setColor(Color.yellow);
		this.setRadio(tamanyoRed * 5 / 6);
		this.setDireccionMovimiento(1);
		this.setPuntuacion(0);

		this.setVinculoMin(45);
		this.setVinculoMax(275);
		this.setVidas(2);
		this.setBocaAbierta(true);
		this.setContactoConFantasma(false);
	}
}
