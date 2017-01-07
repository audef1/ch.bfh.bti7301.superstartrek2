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
    LASER("soundeffects/lasers/1.wav", false),
    LASER2("soundeffects/lasers/2.wav", false),
    LASER3("soundeffects/lasers/3.wav", false),
    LASER4("soundeffects/lasers/4.wav", false),
    LASER5("soundeffects/lasers/5.wav", false),
    LASER6("soundeffects/lasers/6.wav", false),
    LASER7("soundeffects/lasers/7.wav", false),
    EXPLOSION("soundeffects/explosions/1.wav", false),
    EXPLOSION2("soundeffects/explosions/2.wav", false),
    EXPLOSION3("soundeffects/explosions/3.wav", false),
    EXPLOSION4("soundeffects/explosions/4.wav", false),
    EXPLOSION5("soundeffects/explosions/5.wav", false),
    EXPLOSION6("soundeffects/explosions/6.wav", false),
    EXPLOSION7("soundeffects/explosions/7.wav", false),
    ALARM("soundeffects/misc/alarm.wav", false),
    ERROR("soundeffects/misc/error.wav", false),
    NOTIFICATION("soundeffects/powerups/4.wav", false),
    BUTTON("soundeffects/buttonselect/2.wav", false),
    BUTTONSELECT("soundeffects/buttonselect/7.wav", false),
    ACTION("soundeffects/powerups/2.wav", false);

    private Boolean loop = false;

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.MEDIUM;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;

    // Constructor to construct each element of the enum with its own sound file.
    SoundBoard(String soundFileName, Boolean loop) {
        this.loop = loop;
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

            if (loop){
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else{
                clip.start();
            }
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