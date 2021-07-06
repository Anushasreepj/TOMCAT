package GUI;

import java.awt.*;

class Utils {
    private static int getMinimumResolution() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) Math.min(size.getWidth(), size.getHeight());
    }

    public static Dimension getWindowSize() {
        return new Dimension(
            getMinimumResolution() / 100 * 90,
            getMinimumResolution() / 100 * 95
        );
    }

    public static Dimension getSimulationSize() {
        Dimension windowSize = getWindowSize();
        int minDimen = Math.min(windowSize.width, windowSize.height);

        return new Dimension(
            minDimen / 100 * 90,
            minDimen / 100 * 90
        );
    }
}
