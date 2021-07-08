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

        SpinnerModel zoomModel = new SpinnerNumberModel(Data.INIT_ZOOM, 5, 50,  5);
        data.spnZoom = new JSpinner(zoomModel);
        data.spnZoom.addChangeListener(e -> Viewer.addViewerPanel(data));
        rowOne.add(data.spnZoom);

        JLabel lblWidth = new JLabel("Width:", SwingConstants.CENTER);
        rowOne.add(lblWidth);

        SpinnerModel widthModel = new SpinnerNumberModel(Data.INIT_WIDTH, 10, Data.MAX_WIDTH, 10);
        data.spnWidth = new JSpinner(widthModel);
        rowOne.add(data.spnWidth);
        data.spnWidth.addChangeListener(e -> {
            data.simulation.changeBoardSize(data.getGridWidth(), data.getGridHeight());
            Viewer.addViewerPanel(data);
        });

        JLabel lblHeight = new JLabel("Height:", SwingConstants.CENTER);
        rowOne.add(lblHeight);

        SpinnerModel heightModel = new SpinnerNumberModel(Data.INIT_HEIGHT, 10, Data.MAX_HEIGHT, 10);
        data.spnHeight = new JSpinner(heightModel);
        rowOne.add(data.spnHeight);
        data.spnHeight.addChangeListener(e -> {
            data.simulation.changeBoardSize(data.getGridWidth(), data.getGridHeight());
            Viewer.addViewerPanel(data);
        });
    }

    private static void addSimulationDetails(Data data) {
        JPanel rowTwo = new JPanel();
        data.controlsPanel.add(rowTwo, BorderLayout.CENTER);

        GridLayout gridTwoLayout = new GridLayout(1, 0, Utils.SPACING, Utils.SPACING);
        rowTwo.setLayout(gridTwoLayout);

        data.lblGeneration = new JLabel("", SwingConstants.CENTER);
        setGenerationLabel(data);
        rowTwo.add(data.lblGeneration);

        data.lblStatus = new JLabel("", SwingConstants.CENTER);
        setStatusLabel(data);
        rowTwo.add(data.lblStatus);

        data.lblPosition = new JLabel("", SwingConstants.CENTER);
        rowTwo.add(data.lblPosition);
    }

    public static void setGenerationLabel(Data data) {
        data.lblGeneration.setText("Generation: " + data.simulation.generation);
    }

    private static void setStatusLabel(Data data) {
        data.lblStatus.setText("Status: " + (data.simulation.isRunning ? "Running" : "Idle"));
    }

    public static void setPositionLabel(Data data) {
        data.lblPosition.setText(
            "View Position: (" +
            Viewer.getScrollXPosition(data) / data.getZoom() + ", " +
            Viewer.getScrollYPosition(data) / data.getZoom() + ")"
        );
    }
}
