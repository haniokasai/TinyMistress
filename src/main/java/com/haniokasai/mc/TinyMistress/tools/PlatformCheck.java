package com.haniokasai.mc.TinyMistress.tools;

/**
 * Created by hani on 2017/03/25.
 */
public class PlatformCheck {
    //http://www.saka-en.com/java/java-get-os/
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    public static boolean isLinux() {
        return OS_NAME.startsWith("linux");
    }

    public static boolean isMac() {
        return OS_NAME.startsWith("mac");
    }

    public static boolean isWindows() {
        return OS_NAME.startsWith("windows");
    }

    public static boolean isSunOS() {
        return OS_NAME.startsWith("sunos");
    }
}
