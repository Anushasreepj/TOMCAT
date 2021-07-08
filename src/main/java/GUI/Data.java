package GUI;

import Logic.Simulation;

import javax.swing.*;
import java.awt.*;

public class Data {
    int generation = 0;

    JFrame jFrame = null;

    JPanel controlsPanel = null;
    JPanel viewerPanel = null;
    JPanel actionsPanel = null;

    JSpinner spnZoom = null;
    JSpinner spnWidth = null;
    JSpinner spnHeight = null;
    JLabel lblGeneration = null;

    Simulation simulation = null;

    public int getZoom() {
        return (int) spnZoom.getValue();
    }

    public int getGridWidth() {
        return (int) spnWidth.getValue();
    }

    public int getGridHeight() {
        return (int) spnHeight.getValue();
    }

    public Dimension getCellSize() {
        return new Dimension(getZoom(), getZoom());
    }
}
