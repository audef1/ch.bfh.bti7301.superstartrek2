package ch.bfh.bti7301.superstartrek.misc;

import ch.bfh.bti7301.superstartrek.graphics.MessagePanel;
import ch.bfh.bti7301.superstartrek.state.State;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by buche on 03.01.2017.
 */
public class Message extends MessagePanel {

    Map<Character, String> map = new HashMap<>();

    public Message(State state, int width, int height) {
        super(state, width, height);

        map.put(Character.SPOCK, "Watch out! We're under attack!");
        map.put(Character.SPOCK, "Enemies ahead!");
        map.put(Character.KIRK, "Well done!");
        map.put(Character.KIRK, "All cleared!");
        map.put(Character.SCOTT, "Shield low!");
        map.put(Character.SCOTT, "Better look for a space station!");
        map.put(Character.KLINGON, "You're under attack!");
        map.put(Character.KLINGON, "This is your end!");
    }


    public void createMessage(Character chara) {
        String message;
        BufferedImage img = null;

        switch (chara) {
            case SCOTT:
                message = map.get(chara);
                try {
                    img = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/scotty.jpg").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case SPOCK:
                message = map.get(chara);
                try {
                    img = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/spock.jpg").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case KIRK:
                message = map.get(chara);
                try {
                    img = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/kirk.jpg").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case KLINGON:
                message = map.get(chara);
                try {
                    img = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/klingon.jpg").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                message = "";
                try {
                    img = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Backgrounds/starfleetlogo.png").getFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

        drawMessage(message, img);
    }
}
