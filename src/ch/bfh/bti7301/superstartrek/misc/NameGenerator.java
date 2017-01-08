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

    /**
     * default constructor
     */
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

    /**
     * overloaded constructor
     * @param vocals
     * @param startConsonants
     * @param endConsonants
     */
    public NameGenerator(String[] vocals, String[] startConsonants,
                         String[] endConsonants) {
        this.vocals.addAll(Arrays.asList(vocals));
        this.startConsonants.addAll(Arrays.asList(startConsonants));
        this.endConsonants.addAll(Arrays.asList(endConsonants));
    }

    /**
     * overloaded constructor
     * @param vocals
     * @param startConsonants
     * @param endConsonants
     * @param nameInstructions
     */
    public NameGenerator(String[] vocals, String[] startConsonants, String[] endConsonants, String[] nameInstructions) {
        this(vocals, startConsonants, endConsonants);
        this.nameInstructions.addAll(Arrays.asList(nameInstructions));
    }

    public String getName() {
        return firstCharUppercase(getNameByInstructions(getRandomElementFrom(nameInstructions)));
    }

    /**
     * creates a random number
     * @param min minimum value
     * @param max maximum value
     * @return random number
     */
    private int randomInt(int min, int max) {
        return (int) (min + (Math.random() * (max + 1 - min)));
    }

    /**
     * Creates a word based on given instructions
     * @param nameInstructions instructions how the word should be created
     * @return created word
     */
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

    /**
     * makes first letter of a word to uppercase
     * @param name name to set first letter to uppercase
     * @return name with uppercase first letter
     */
    private String firstCharUppercase(String name) {
        return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1);
    }

    /**
     * gets a random element from the given list
     * @param v list in which a random element should be given
     * @return random element
     */
    private String getRandomElementFrom(List v) {
        return v.get(randomInt(0, v.size() - 1)).toString();
    }
}

