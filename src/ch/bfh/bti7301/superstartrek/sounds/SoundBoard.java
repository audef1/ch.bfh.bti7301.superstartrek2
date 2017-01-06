package ch.bfh.bti7301.superstartrek.sounds;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * Created by Florian on 06.01.2017.
 */

public enum SoundBoard {

    MENU("music/menu.wav", true),
    ENDING("music/ending.wav", true),
    BACKGROUND("music/backgroundmusic.wav", true),
    BOSS("music/boss.wav", true),
    LASER("soundeffects/lasers/1.wav", false);

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.MEDIUM;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;

    // Constructor to construct each element of the enum with its own sound file.
    SoundBoard(String soundFileName, Boolean loop) {
        try {
            URL url = this.getClass().getClassLoader().getResource("sounds/" + soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            System.out.println(soundFileName);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }

    public void stop() {
        if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
        clip.setFramePosition(0); // rewind to the beginning
    }

    // Optional static method to pre-load all the sound files.
    public static void init() {
        values(); // calls the constructor for all the elements
    }
}