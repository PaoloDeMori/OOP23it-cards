package it.unibo.itcards.model.audio;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class represents an audio clip.
 */
public class Audio {
    private final Clip clip;
    final AudioInputStream audioStream;

    /**
     * This constructor initializes the audio clip.
     * 
     * @throws UnsupportedAudioFileException if the audio file is not supported
     * @throws IOException 
     * @throws LineUnavailableException 
     */
    public Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        final String path = "Audio/" + "two-grands-piano-bar-music-jazz-3945.wav";
        final URL imgURL = ClassLoader.getSystemResource(path);
        audioStream = AudioSystem.getAudioInputStream(imgURL);
        this.clip = AudioSystem.getClip();
    }

    /**
     * Starts playing the audio clip from the beginning, looping continuously.
     *
     * If the clip is already running, it is stopped before starting again.
     * @throws IOException 
     * @throws LineUnavailableException 
     *
     */
    public void start() throws LineUnavailableException, IOException {
        if(!this.clip.isOpen()&&this.clip!=null){
            this.clip.open(audioStream);
        }
        if (this.clip.isRunning()) {
            this.clip.stop();
        } else {
            this.clip.setFramePosition(0);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.clip.start();
        }
    }

    /**
     * Stops playing the audio clip.
     */
    public void stop() {
        this.clip.stop();
        this.clip.close();
    }

    /**
     * Resets the position of the audio clip to the beginning.
     */
    public void reset() {
        clip.setFramePosition(0);
    }

    /**
     * Closes the audio clip.
     */
    public void close() {
        if(this.clip.isOpen()){
        clip.close();
        }
    }

}
