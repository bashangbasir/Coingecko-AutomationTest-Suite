package utils;

import java.util.List;
import java.util.Random;

public class CommonUtils {

    public static String parseListToStringCommaSeparated(List list) {
        return String.join(",", list);
    }

    public static String getRandomCharacters(int length) {
        StringBuilder rndString = new StringBuilder();

        while (rndString.length() < length) {
            int index = (new Random()).nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".length());
            rndString.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(index));
        }
        return rndString.toString();
    }

    public static String getRandomCharacters() {
        StringBuilder rndString = new StringBuilder();

        while (rndString.length() < 5) {
            int index = (new Random()).nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".length());
            rndString.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(index));
        }
        return rndString.toString();
    }


}
