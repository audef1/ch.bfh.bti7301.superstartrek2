package ch.bfh.bti7301.superstartrek.misc;


import ch.bfh.bti7301.superstartrek.graphics.GamePanel;
import ch.bfh.bti7301.superstartrek.sounds.SoundBoard;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by buche on 03.01.2017.
 */
public class MessageGenerator {

    Map<MsgCharacter, String> map = new HashMap<>();
    public String msg;
    public BufferedImage img;
    public MessageType messageType;
    public int timer;
    public int ttl = 180;


    /**
     * default constructor
     */
    public MessageGenerator() {
        map.put(MsgCharacter.SPOCK, "Spock: ");
        map.put(MsgCharacter.KIRK, "Kirk: ");
        map.put(MsgCharacter.SCOTT, "Scotty: ");
        map.put(MsgCharacter.KLINGON, "Klingon: ");

        msg = "";
        try {
            img = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Backgrounds/starfleetlogo.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageType = MessageType.NORMAL;
    }

    /**
     * Reads the picture and sets the message for the given character
     * @param chara who says the message
     * @param msgType what kind of message is it
     * @param msgShowTime for how many seconds should the message be displayed
     * @param text message text
     */
    public void createMessage(MsgCharacter chara, MessageType msgType, int msgShowTime, String text) {
        timer = 0;
        ttl = msgShowTime * GamePanel.FPS;

        if (timer < ttl) {

            switch (chara) {
                case SCOTT:
                    setMsg(map.get(chara) + text);
                    try {
                        setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/scotty.jpg").getFile())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case SPOCK:
                    setMsg(map.get(chara) + text);
                    try {
                        setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/spock.jpg").getFile())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case KIRK:
                    setMsg(map.get(chara) + text);
                    try {
                        setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/kirk.jpg").getFile())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case KLINGON:
                    setMsg(map.get(chara) + text);
                    try {
                        setImg(ImageIO.read(new File(getClass().getClassLoader().getResource("images/Characters/klingon.jpg").getFile())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            setMessageType(msgType);

            // play sound according messagetype
            if (msgType == MessageType.ALERT){
                SoundBoard.ALARM.play();
            }
            else if (msgType == MessageType.NORMAL){
                SoundBoard.NOTIFICATION.play();
            }
            else {
                SoundBoard.ERROR.play();
            }
        }
    }

    public void update() {
        timer ++;
        if (timer > ttl){
            removeMessage();
        }
    }

    /**
     * Removes the message and sets image to default
     */
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
