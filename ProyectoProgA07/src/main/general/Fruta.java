package main.general;

import java.util.ArrayList;
import java.util.Random;

public class Fruta implements Comestible {

	public static double SHOW_FRUIT_DURATION = 8000;
	private int puntuacion;
	private int frutaComida;

	public Fruta(int x, int y, int puntuacionInicial) {
		this(randomFruitPath(), x, y, puntuacionInicial);
	}

	public Fruta(String fruitImagePath, int x, int y, int initialScore) {
		puntuacion = initialScore;
		frutaComida = 0;
	}

	private static String randomFruitPath() {
		ArrayList<String> frutas = new ArrayList<String>();
		frutas.add("cereza.png");
		frutas.add("manzana.png");
		frutas.add("melon.png");
		frutas.add("fresa.png");
		frutas.add("naranja.png");
		Random r = new Random();
		return frutas.get(r.nextInt(frutas.size()));
	}

	public void comido() {
		frutaComida++;
		puntuacion += 100;
	}

	public int getValor() {
		return puntuacion;
	}

	public void setValor(int valorPuntuacion) {
		this.puntuacion = valorPuntuacion;
	}

}