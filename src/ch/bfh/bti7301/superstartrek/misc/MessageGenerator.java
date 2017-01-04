package ch.bfh.bti7301.superstartrek.misc;


import ch.bfh.bti7301.superstartrek.graphics.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by buche on 03.01.2017.
 */
public class MessageGenerator {

    Map<Character, String> map = new HashMap<>();
    public String msg;
    public BufferedImage img;
    public MessageType messageType;
    public int timer;
    public int ttl = 180;


    /**
     * default constructor
     */
    public MessageGenerator() {
        map.put(Character.SPOCK, "Watch out! We're under attack!");
        map.put(Character.SPOCK, "Enemies ahead!");
        map.put(Character.KIRK, "Well done!");
        map.put(Character.KIRK, "All cleared!");
        map.put(Character.SCOTT, "Shield low!");
        map.put(Character.SCOTT, "Better look for a space station!");
        map.put(Character.KLINGON, "You're under attack!");
        map.put(Character.KLINGON, "This is your end!");

        msg = "";
        try {
            img = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Backgrounds/starfleetlogo.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageType = MessageType.NORMAL;
    }

    /**
     * @param chara which character sends the message
     */
    public void createMessage(Character chara, MessageType msgType, int msgShowTime) {
        timer = 0;

        while(timer < (msgShowTime * GamePanel.FPS)) {

            switch (chara) {
                case SCOTT:
                    setMsg(map.get(chara));
                    try {
                        setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/scotty.jpg").getFile())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case SPOCK:
                    setMsg(map.get(chara));
                    try {
                        setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/spock.jpg").getFile())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case KIRK:
                    setMsg(map.get(chara));
                    try {
                        setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/kirk.jpg").getFile())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case KLINGON:
                    setMsg(map.get(chara));
                    try {
                        setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/klingon.jpg").getFile())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            setMessageType(msgType);
        }

        removeMessage();
    }

    public void update() {
        timer ++;
    }

    public void removeMessage() {
        setMsg("");

        try {
            setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Backgrounds/starfleetlogo.png").getFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setMessageType(MessageType.NORMAL);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
