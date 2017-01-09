package ch.bfh.bti7301.superstartrek.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Florian on 06.01.2017.
 * A Soundboard which provides different sounds,
 * which can be played all over the project by simply calling
 * "SoundBoard.SOUNDNAME.play()"
 */
public enum SoundBoard {

    /**
     * The definition of the different sounds
     */
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
    BUTTON2("soundeffects/buttonselect/3.wav", false),
    BUTTON3("soundeffects/buttonselect/4.wav", false),
    BUTTONSELECT("soundeffects/buttonselect/7.wav", false),
    ACTION("soundeffects/powerups/2.wav", false);

    /**
     * Sound should loop.
     */
    private Boolean loop = false;

    /**
     * Sound should be paused.
     */
    private Boolean paused = false;

    /**
     * A sound clip.
     * Each sound effect has its own clip, loaded with its own sound file.
     */
    private Clip clip;

    /**
     * Class Constructor
     * Constructs each element of the enum with its own sound file.
     *
     * @param soundFileName The path and filename of the sound file, starting from Resources/sounds/.
     * @param loop          Sound should loop.
     */
    SoundBoard(String soundFileName, Boolean loop) {
        this.loop = loop;
        try {
            URL url = this.getClass().getClassLoader().getResource("sounds/" + soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
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

    /**
     *  Optional static method to pre-load all the sound files.
     *  values() calls the constructor for all the elements.
     */
    public static void init() {
        values();
    }

    /**
     * Play or replay the sound clip from the beginning, by rewinding.
     */
    public void play() {
        /* Stop the player if it is still running */
        if (clip.isRunning()) {
            clip.stop();
        }

        /* rewind to the beginning, play coming from paused mode */
        if (!paused) {
            clip.setFramePosition(0);
        }

        /* set playback to loop if defined */
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }

        paused = false;
    }

    /**
     * Stop sound clip and rewind to beginning.
     */
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
    }

    /**
     * Pause playback without rewinding it.
     */
    public void pause() {
        paused = true;
        if (clip.isRunning()){
            clip.stop();
        }
    }
}