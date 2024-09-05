package it.unibo.itcards.model.Audio;
import java.io.IOException;
import javax.sound.sampled.*;
public class Audio {
    private Clip clip;

    // Costruttore che carica il file audio
    public Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		String path = "/Audio/"+"two-grands-piano-bar-music-jazz-3945.wav";
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(this.getClass().getResource(path));
	    this.clip = AudioSystem.getClip();
		this.clip.open(audioStream);
    }

    /**
     * Starts playing the audio clip from the beginning, looping continuously.
     *
     * If the clip is already running, it is stopped before starting again.
     *
     * @return void
     */
    public void start() {
        if (this.clip.isRunning()) {
            this.clip.stop(); 
        }
        else{
        this.clip.setFramePosition(0); 
        this.clip.loop(Clip.LOOP_CONTINUOUSLY); 
        this.clip.start();
        }
    }

    public void stop() {
            this.clip.stop(); 
    }

    public void reset() {
        clip.setFramePosition(0); 
    }

    
    public void close() {
        clip.close(); 
    }

}
