package GUI;

import Logic.Simulation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WindowUI {
    final int SPACING = 2;

    int cellSize = 15;

    JFrame jFrame = null;

    JPanel northControls = null;
    JPanel simulationViewer = null;
    JPanel southControls = null;

    JSpinner spnZoom = null;
    JSpinner spnWidth = null;
    JSpinner spnHeight = null;

    Simulation simulation = null;

    public void createWindow() {
        jFrame = new JFrame("Game Of Life");
        jFrame.setSize(Utils.getWindowSize());

        addNorthControls();
        addSimulationViewer();
        addSouthControls();

        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addNorthControls() {
        northControls = new JPanel();
        jFrame.add(northControls, BorderLayout.NORTH);

        GridLayout gridLayout = new GridLayout(2, 1, SPACING, SPACING);
        northControls.setLayout(gridLayout);

        Border padding = BorderFactory.createEmptyBorder(SPACING, SPACING, SPACING, SPACING);
        northControls.setBorder(padding);

        // Row One - Controlling the simulation

        JPanel rowOne = new JPanel();
        northControls.add(rowOne, BorderLayout.NORTH);

        GridLayout gridOneLayout = new GridLayout(1, 0, SPACING, SPACING);
        rowOne.setLayout(gridOneLayout);

        JLabel lblZoom = new JLabel("Zoom:", SwingConstants.CENTER);
        rowOne.add(lblZoom);

        SpinnerModel zoomModel = new SpinnerNumberModel(cellSize, 5, 50,  5);
        spnZoom = new JSpinner(zoomModel);
        spnZoom.addChangeListener(e -> {
            cellSize = (int) spnZoom.getValue();
            addSimulationViewer();
        });
        rowOne.add(spnZoom);

        JLabel lblWidth = new JLabel("Width:", SwingConstants.CENTER);
        rowOne.add(lblWidth);

        SpinnerModel widthModel = new SpinnerNumberModel(40, 10, 80,  10);
        spnWidth = new JSpinner(widthModel);
        spnWidth.addChangeListener(e -> addSimulationViewer());
        rowOne.add(spnWidth);

        JLabel lblHeight = new JLabel("Height:", SwingConstants.CENTER);
        rowOne.add(lblHeight);

        SpinnerModel heightModel = new SpinnerNumberModel(40, 10, 80,  10);
        spnHeight = new JSpinner(heightModel);
        spnHeight.addChangeListener(e -> addSimulationViewer());
        rowOne.add(spnHeight);

        // Row Two - Showing Simulation Details

        JPanel rowTwo = new JPanel();
        northControls.add(rowTwo, BorderLayout.CENTER);

        GridLayout gridTwoLayout = new GridLayout(1, 0, SPACING, SPACING);
        rowTwo.setLayout(gridTwoLayout);

        JLabel lblGeneration = new JLabel("Generation: 0", SwingConstants.CENTER);
        rowTwo.add(lblGeneration);

        JLabel lblStatus = new JLabel("Status: Stopped", SwingConstants.CENTER);
        rowTwo.add(lblStatus);

        JLabel lblPositions = new JLabel("Position: (x, y)", SwingConstants.CENTER);
        rowTwo.add(lblPositions);
    }

    public void addSimulationViewer() {
        int widthSize = (int) spnWidth.getValue();
        int heightSize = (int) spnHeight.getValue();

        JPanel newSimulationViewer = new JPanel();
        newSimulationViewer.setPreferredSize(new Dimension(widthSize * 100, heightSize * 100));

        JPanel gridPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(heightSize, widthSize, 0, 0);
        gridPanel.setLayout(gridLayout);

        JScrollPane scrollFrame = new JScrollPane(gridPanel);
        scrollFrame.setPreferredSize(Utils.getSimulationSize());
        newSimulationViewer.setAutoscrolls(true);
        newSimulationViewer.add(scrollFrame);

        if (simulationViewer == null) {
            simulation = new Simulation(widthSize, heightSize);
        }

        for(int i = 0; i < heightSize; i ++) {
            for(int j = 0; j < widthSize; j ++) {
                JButton button = new JButton();
                button.setBackground(simulation.board[i][j] ? Color.BLACK : Color.WHITE);
                button.setPreferredSize(new Dimension(cellSize, cellSize));
                gridPanel.add(button);
            }
        }

        if (simulationViewer != null) {
            jFrame.getContentPane().remove(simulationViewer);
            jFrame.getContentPane().add(newSimulationViewer, BorderLayout.CENTER);
            jFrame.revalidate();
        }
        else {
            jFrame.getContentPane().add(newSimulationViewer, BorderLayout.CENTER);
        }

        simulationViewer = newSimulationViewer;
    }

    public void addSouthControls() {
        southControls = new JPanel();

        Border padding = BorderFactory.createEmptyBorder(SPACING, SPACING, SPACING, SPACING);
        southControls.setBorder(padding);

        GridLayout gridLayout = new GridLayout(1, 0, SPACING, SPACING);
        southControls.setLayout(gridLayout);

        JButton btnStart = new JButton("Start");
        southControls.add(btnStart);

        JButton btnStop = new JButton("Stop");
        southControls.add(btnStop);

        JButton btnStep = new JButton("Step");
        southControls.add(btnStep);

        JButton btnRandom = new JButton("Random");
        southControls.add(btnRandom);
        btnRandom.addActionListener(e -> {
            simulation.randomBoard();
            addSimulationViewer();
        });

        JButton btnClear = new JButton("Clear");
        southControls.add(btnClear);
        btnClear.addActionListener(e -> {
            simulation.clearBoard();
            addSimulationViewer();
        });

        jFrame.add(southControls, BorderLayout.SOUTH);
    }
}
