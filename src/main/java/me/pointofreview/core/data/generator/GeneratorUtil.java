package me.pointofreview.core.data.generator;

import java.util.Date;
import java.util.Random;

public class GeneratorUtil {
    private static Random rand = new Random();

    /**
     * @return a random positive integer
     */
    public static int getRandomNumber() {
        int r = rand.nextInt();
        return r >= 0 ? r : -r;
    }

    /**
     * @return the current timestamp
     */
    public static long getTimestamp() {
        Date date = new Date();
        return date.getTime();
    }
}
