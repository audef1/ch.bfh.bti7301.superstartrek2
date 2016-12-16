package ch.bfh.bti7301.superstartrek.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas Buchegger on 20.11.2016.
 */
public class NameGenerator {

    private List vocals = new ArrayList<String>();
    private List startConsonants = new ArrayList<String>();
    private List endConsonants = new ArrayList<String>();
    private List nameInstructions = new ArrayList<String>();

    public NameGenerator() {
        String[] ArrVocals = { "a", "e", "i", "o", "u", "ei", "ai", "ou", "j",
                "ji", "y", "oi", "au", "oo" };

        String[] ArrStartConsonants = { "b", "c", "d", "f", "g", "h", "k",
                "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z",
                "ch", "bl", "br", "fl", "gl", "gr", "kl", "pr", "st", "sh",
                "th" };

        String[] ArrEndConsonants = { "b", "d", "f", "g", "h", "k", "l", "m",
                "n", "p", "r", "s", "t", "v", "w", "z", "ch", "gh", "nn", "st",
                "sh", "th", "tt", "ss", "pf", "nt" };

        String[] ArrNameInstructions = { "ve", "sveve", "sve", "veve" };

        this.vocals.addAll(Arrays.asList(ArrVocals));
        this.startConsonants.addAll(Arrays.asList(ArrStartConsonants));
        this.endConsonants.addAll(Arrays.asList(ArrEndConsonants));
        this.nameInstructions.addAll(Arrays.asList(ArrNameInstructions));
    }

    public NameGenerator(String[] vocals, String[] startConsonants,
                         String[] endConsonants) {
        this.vocals.addAll(Arrays.asList(vocals));
        this.startConsonants.addAll(Arrays.asList(startConsonants));
        this.endConsonants.addAll(Arrays.asList(endConsonants));
    }

    public NameGenerator(String[] vocals, String[] startConsonants, String[] endConsonants, String[] nameInstructions) {
        this(vocals, startConsonants, endConsonants);
        this.nameInstructions.addAll(Arrays.asList(nameInstructions));
    }

    public String getName() {
        return firstCharUppercase(getNameByInstructions(getRandomElementFrom(nameInstructions)));
    }

    private int randomInt(int min, int max) {
        return (int) (min + (Math.random() * (max + 1 - min)));
    }

    private String getNameByInstructions(String nameInstructions) {
        String name = "";
        int l = nameInstructions.length();

        for (int i = 0; i < l; i++) {
            char x = nameInstructions.charAt(0);
            switch (x) {
                case 'v': name += getRandomElementFrom(vocals);
                    break;
                case 's': name += getRandomElementFrom(startConsonants);
                    break;
                case 'e': name += getRandomElementFrom(endConsonants);
                    break;
            }
            nameInstructions = nameInstructions.substring(1);
        }
        return name;
    }

    private String firstCharUppercase(String name) {
        return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1);
    }

    private String getRandomElementFrom(List v) {
        return v.get(randomInt(0, v.size() - 1)).toString();
    }
}

