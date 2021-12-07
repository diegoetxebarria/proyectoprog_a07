package main.general;

import java.awt.Color;
import java.awt.Point;

public abstract class Figura {

	private Point posicion;
	private int velocidad;
	private int direccionMovimiento;
	private Color color;
	private int radio;
	private int direccion;
	protected int modo;
	private int velocidadX;
	private int velocidadY;

	public Figura() {

	}

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(int xPos, int yPos) {
		this.posicion = new Point(xPos, yPos);
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getDireccionMovimiento() {
		return direccionMovimiento;
	}

	public void setDireccionMovimiento(int direccionMovimiento) {
		this.direccionMovimiento = direccionMovimiento;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public void hayParedDelante(int[][] tablero, int tamanyoRed) {
		System.out.println(this.getDireccionMovimiento() + "; " + this.getDireccion() + "; " + this.getVelocidadX()
				+ "; " + this.getVelocidadY() + "; " + this.getPosicion().x + "; " + this.getPosicion().y);

		switch (this.getDireccionMovimiento()) {
		case 1:// derecha
			if (tablero[this.getPosicion().y / tamanyoRed][this.getPosicion().x / tamanyoRed + 1] == 1) {
				this.setVelocidadX(0);
				this.setDireccionMovimiento(0);
				if (this.getDireccion() == 1)
					this.setDireccion(0);
			}
			break;
		case 3:// izquierda
			if (tablero[this.getPosicion().y / tamanyoRed][(this.getPosicion().x - 1) / tamanyoRed] == 1) {
				this.setVelocidadX(0);
				this.setDireccionMovimiento(0);
				if (this.getDireccion() == 3)
					this.setDireccion(0);
			}
			break;
		case 2:// arriba
			if (tablero[(this.getPosicion().y - 1) / tamanyoRed][this.getPosicion().x / tamanyoRed] == 1) {
				this.setVelocidadY(0);
				this.setDireccionMovimiento(0);
				if (this.getDireccion() == 2)
					this.setDireccion(0);
			}
			break;
		case 4:// abajo
			if (tablero[this.getPosicion().y / tamanyoRed + 1][this.getPosicion().x / tamanyoRed] == 1) {
				this.setVelocidadY(0);
				this.setDireccionMovimiento(0);
				if (this.getDireccion() == 4)
					this.setDireccion(0);
			}
			break;
		default:
			break;
		}

		System.out.println(this.getDireccionMovimiento() + "; " + this.getDireccion() + "; " + this.getVelocidadX()
				+ "; " + this.getVelocidadY() + "; " + this.getPosicion().x + "; " + this.getPosicion().y);

		switch (this.getDireccion()) {
		case 1:// derecha
			if (tablero[this.getPosicion().y / tamanyoRed][this.getPosicion().x / tamanyoRed + 1] != 1
					&& this.getPosicion().y % tamanyoRed == 0) {
				this.mover();
			}
			break;
		case 3:// izquierda
			if (tablero[this.getPosicion().y / tamanyoRed][(this.getPosicion().x - 1) / tamanyoRed] != 1
					&& this.getPosicion().y % tamanyoRed == 0) {
				this.mover();
			}
			break;
		case 2:// arriba
			if (tablero[(this.getPosicion().y - 1) / tamanyoRed][this.getPosicion().x / tamanyoRed] != 1
					&& this.getPosicion().x % tamanyoRed == 0) {
				this.mover();
			}
			break;
		case 4:// abajo
			if (tablero[this.getPosicion().y / tamanyoRed + 1][this.getPosicion().x / tamanyoRed] != 1
					&& this.getPosicion().x % tamanyoRed == 0) {
				this.mover();
			}
			break;
		default:
			break;
		}

		System.out.println(this.getDireccionMovimiento() + "; " + this.getDireccion() + "; " + this.getVelocidadX()
				+ "; " + this.getVelocidadY() + "; " + this.getPosicion().x + "; " + this.getPosicion().y);

		this.setPosicion(this.getPosicion().x + this.getVelocidadX(), this.getPosicion().y + this.getVelocidadY());
	}

	// se cambia la direccion
	public void mover() {
		switch (this.getDireccion()) {
		case 1: // derecha
			this.setVelocidadX(this.getVelocidad());
			this.setVelocidadY(0);
			this.setDireccionMovimiento(1);
			break;

		case 3: // izquierda
			this.setVelocidadX(0 - this.getVelocidad());
			this.setVelocidadY(0);
			this.setDireccionMovimiento(3);
			break;

		case 2: // arriba
			this.setVelocidadX(0);
			this.setVelocidadY(0 - this.getVelocidad());
			this.setDireccionMovimiento(2);
			break;

		case 4: // abajo
			this.setVelocidadX(0);
			this.setVelocidadY(this.getVelocidad());
			this.setDireccionMovimiento(4);
			break;

		default:
			break;
		}
	}

	public abstract void actualizacionDireccion(int x);

	public abstract void reset(int tamanyoRed);

}
