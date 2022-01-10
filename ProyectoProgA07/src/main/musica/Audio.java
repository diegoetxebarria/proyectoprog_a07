package main.musica;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	private static HashMap<String, AudioRunner> hilosMusica = new HashMap<String, AudioRunner>();

	public static void paraAudioEnHilo(URL recurso) {

		if (hilosMusica.containsKey(recurso.toString())) {
			AudioRunner hilo = hilosMusica.get(recurso.toString());
			hilo.stopMusic();
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			hilosMusica.remove(recurso.toString());
		}
	}

	public static void lanzaAudioEnHilo(URL recurso) {
		Audio a = new Audio();
		hilosMusica.put(recurso.toString(), a.new AudioRunner((recurso)));
		hilosMusica.get(recurso.toString()).start();
	}

	private class AudioRunner extends Thread {

		URL recurso;
		private volatile boolean playing;

		AudioRunner(URL recurso) {
			this.recurso = recurso;
		}

		void stopMusic() {
			playing = false;
		}


		@Override
		public void run() {

			playing = true;
			int BUFFER_SIZE = 128000;
			AudioInputStream flujoAudio = null;
			AudioFormat formatAudio = null;
			SourceDataLine linDatosSonido = null;
			while ( true ) {
				try {
					flujoAudio = AudioSystem.getAudioInputStream(recurso);
					formatAudio = flujoAudio.getFormat();
					DataLine.Info info = new DataLine.Info(SourceDataLine.class, formatAudio);
					linDatosSonido = (SourceDataLine) AudioSystem.getLine(info);
					linDatosSonido.open(formatAudio);
					linDatosSonido.start();
					int bytesLeidos = 0;
					byte[] bytesAudio = new byte[BUFFER_SIZE];
					while (bytesLeidos != -1 && playing) {
						bytesLeidos = flujoAudio.read(bytesAudio, 0, bytesAudio.length);
						if (bytesLeidos >= 0) {
							linDatosSonido.write(bytesAudio, 0, bytesLeidos);
						}
					}
				} catch (IOException e) {
					// Excepcion si el fichero es nulo, erroneo, o wav incorrecto
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
				if (linDatosSonido != null) {
					linDatosSonido.drain();
					try {
						linDatosSonido.close();
						flujoAudio.close();
					} catch (IOException e) {
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		pruebaHilos();
	}

	private static void pruebaHilos() {
		Audio.lanzaAudioEnHilo(Audio.class.getResource("metermusica"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Audio.paraAudioEnHilo(Audio.class.getResource("metercancion"));
		System.out.println("Mensaje despues de los audios");
	}

}
