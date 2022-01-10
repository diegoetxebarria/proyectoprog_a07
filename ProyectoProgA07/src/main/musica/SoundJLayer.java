package main.musica;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class SoundJLayer extends PlaybackListener implements Runnable {

	private String filePath;
	private AdvancedPlayer player;
	private Thread playerThread;

	public SoundJLayer(String filePath) {
		this.filePath = filePath;
	}

	public void play() {
		try {
			String urlAsString = "file:///" + new java.io.File(".").getCanonicalPath() + "/" + this.filePath;
			this.player = new AdvancedPlayer(new java.net.URL(urlAsString).openStream(),
					javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
			this.player.setPlayBackListener(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		playerThread = new Thread(this, "AudioPlayerThread");
		playerThread.start();
	}

	// PlaybackListener

	public void playbackStarted(PlaybackEvent playbackEvent) {
		System.out.println("  empieza sonido " + filePath);
	}

	public void playbackFinished(PlaybackEvent playbackEvent) {
		System.out.println("  acaba sonido " + filePath);
		try {
			this.player.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Runnable members

	@Override
	public void run() {
		try {
			this.player.play();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}