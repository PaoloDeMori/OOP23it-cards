package it.unibo.itcards.model.Audio;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    private Clip clip;

    // Costruttore che carica il file audio
    public Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String path = "Audio/" + "two-grands-piano-bar-music-jazz-3945.wav";
        final URL imgURL = ClassLoader.getSystemResource(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(imgURL);
        this.clip = AudioSystem.getClip();
        this.clip.open(audioStream);
    }

    /**
     * Starts playing the audio clip from the beginning, looping continuously.
     *
     * If the clip is already running, it is stopped before starting again.
     *
     */
    public void start() {
        if (this.clip.isRunning()) {
            this.clip.stop();
        } else {
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
