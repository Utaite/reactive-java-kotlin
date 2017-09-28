package common;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;


public class CommonUtils {

    public static final String GITHUB_ROOT = "https://raw.githubusercontent.com/yudong80/reactivejava/master/";

    public static final String ERROR_CODE = "-500";


    public static long startTime;

    public static void exampleStart() {
        startTime = System.currentTimeMillis();
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void doSomething() {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String numberToAlphabet(long x) {
        return Character.toString(ALPHABET.charAt((int) x % ALPHABET.length()));
    }


    public static boolean isNetworkAvailable() {
        try {
            return InetAddress.getByName("www.google.com").isReachable(1000);
        } catch (IOException e) {
            Log.it("Network is not available");
        }
        return false;
    }

}
