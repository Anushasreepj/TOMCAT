package GUI;

import java.awt.*;

class Utils {
    private static int getMinimumResolution() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) Math.min(size.getWidth(), size.getHeight());
    }

    public static int getWindowWidth() {
        return getMinimumResolution() / 100 * 85;
    }

    public static int getWindowHeight() {
        return getMinimumResolution() / 100 * 90;
    }
}
