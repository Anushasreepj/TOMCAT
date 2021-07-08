package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Controls {
    public static void addControlsPanel(Data data) {
        data.controlsPanel = new JPanel();
        data.jFrame.add(data.controlsPanel, BorderLayout.NORTH);

        GridLayout gridLayout = new GridLayout(2, 1, Utils.SPACING, Utils.SPACING);
        data.controlsPanel.setLayout(gridLayout);

        Border padding = BorderFactory.createEmptyBorder(Utils.SPACING, Utils.SPACING, Utils.SPACING, Utils.SPACING);
        data.controlsPanel.setBorder(padding);

        addSimulationControls(data);
        addSimulationDetails(data);
    }

    private static void addSimulationControls(Data data) {
        JPanel rowOne = new JPanel();
        data.controlsPanel.add(rowOne, BorderLayout.NORTH);

        GridLayout gridOneLayout = new GridLayout(1, 0, Utils.SPACING, Utils.SPACING);
        rowOne.setLayout(gridOneLayout);

        JLabel lblZoom = new JLabel("Zoom:", SwingConstants.CENTER);
        rowOne.add(lblZoom);

        SpinnerModel zoomModel = new SpinnerNumberModel(15, 5, 50,  5);
        data.spnZoom = new JSpinner(zoomModel);
        data.spnZoom.addChangeListener(e -> Viewer.addViewerPanel(data));
        rowOne.add(data.spnZoom);

        JLabel lblWidth = new JLabel("Width:", SwingConstants.CENTER);
        rowOne.add(lblWidth);

        SpinnerModel widthModel = new SpinnerNumberModel(40, 10, 80,  10);
        data.spnWidth = new JSpinner(widthModel);
        data.spnWidth.addChangeListener(e -> Viewer.addViewerPanel(data));
        rowOne.add(data.spnWidth);

        JLabel lblHeight = new JLabel("Height:", SwingConstants.CENTER);
        rowOne.add(lblHeight);

        SpinnerModel heightModel = new SpinnerNumberModel(40, 10, 80,  10);
        data.spnHeight = new JSpinner(heightModel);
        data.spnHeight.addChangeListener(e -> Viewer.addViewerPanel(data));
        rowOne.add(data.spnHeight);
    }

    private static void addSimulationDetails(Data data) {
        JPanel rowTwo = new JPanel();
        data.controlsPanel.add(rowTwo, BorderLayout.CENTER);

        GridLayout gridTwoLayout = new GridLayout(1, 0, Utils.SPACING, Utils.SPACING);
        rowTwo.setLayout(gridTwoLayout);

        data.lblGeneration = new JLabel("Generation: 0", SwingConstants.CENTER);
        rowTwo.add(data.lblGeneration);

        JLabel lblStatus = new JLabel("Status: Stopped", SwingConstants.CENTER);
        rowTwo.add(lblStatus);

        JLabel lblPositions = new JLabel("Position: (x, y)", SwingConstants.CENTER);
        rowTwo.add(lblPositions);
    }

    private static void setGeneration(Data data) {
        data.lblGeneration.setText("Generation: " + data.generation);
    }
}
