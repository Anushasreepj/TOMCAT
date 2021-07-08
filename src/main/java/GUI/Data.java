package GUI;

import Logic.Simulation;

import javax.swing.*;
import java.awt.*;

public class Data {
    final static int INIT_ZOOM = 10;
    final static int INIT_WIDTH = 40;
    final static int INIT_HEIGHT = 40;
    final static int INIT_FPS = 8;
    final static int MAX_ZOOM = 80;
    final static int MAX_WIDTH = 100;
    final static int MAX_HEIGHT = 100;

    JFrame jFrame = null;

    JPanel controlsPanel = null;
    JPanel viewerPanel = null;
    JPanel actionsPanel = null;

    JSpinner spnZoom = null;
    JSpinner spnWidth = null;
    JSpinner spnHeight = null;
    JSpinner spnFps = null;

    JLabel lblGeneration = null;
    JLabel lblStatus = null;
    JLabel lblPosition = null;

    JScrollPane scrollPane = null;

    Simulation simulation;

    Data() {
        simulation = new Simulation(INIT_WIDTH, INIT_HEIGHT, MAX_WIDTH, MAX_HEIGHT);
    }

    public int getZoom() {
        return (int) spnZoom.getValue();
    }

    public int getGridWidth() {
        return (int) spnWidth.getValue();
    }

    public int getGridHeight() {
        return (int) spnHeight.getValue();
    }

    public int getFps() {
        return (int) spnFps.getValue();
    }

    public Dimension getCellSize() {
        return new Dimension(getZoom(), getZoom());
    }
}
