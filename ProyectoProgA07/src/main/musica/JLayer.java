package main.musica;

import javazoom.jl.player.advanced.*;

public class JLayer {

	public static void main(String[] args) {
		// Prueba de tres sonidos con 1,5 segundos de diferencia
		System.out.println( "Ya!" );
		SoundJLayer soundToPlay = new SoundJLayer("mnetercancion");
		soundToPlay.play();  // Reproduce boing
		try {
			Thread.sleep(1500);
		} catch (Exception e) {}
		System.out.println( "Ya!" );
		SoundJLayer soundToPlay2 = new SoundJLayer("mnetercancion");
		soundToPlay2.play();  // Reproduce informativo
		try {
			Thread.sleep(1500);
		} catch (Exception e) {}
		System.out.println( "Ya!" );
		soundToPlay.play();  // Reproduce el mismo boing
	}
}