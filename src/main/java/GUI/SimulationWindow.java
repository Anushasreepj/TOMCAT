package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimulationWindow {
    final int SPACING = 5;

    JFrame jFrame = null;

    JPanel northControls = null;
    JPanel simulationData = null;
    JPanel simulationViewer = null;
    JPanel southControls = null;

    JSpinner spnGrid = null;
    JSpinner spnView = null;

    public void createWindow() {
        jFrame = new JFrame("Game Of Life");

        addNorthControls();
        addSimulationViewer();
        addSouthControls();

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int minResolution = (int) Math.min(size.getWidth(), size.getHeight()) / 100 * 90;
        jFrame.setSize(minResolution, minResolution);

        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addNorthControls() {
        northControls = new JPanel();

        Border padding = BorderFactory.createEmptyBorder(SPACING, SPACING, SPACING, SPACING);
        northControls.setBorder(padding);

        GridLayout gridRowOneLayout = new GridLayout(1, 0, SPACING, SPACING);
        northControls.setLayout(gridRowOneLayout);

        JLabel lblGridSize = new JLabel("Grid Size:", SwingConstants.CENTER);
        northControls.add(lblGridSize);

        SpinnerModel gridModel = new SpinnerNumberModel(300, 50, 1000,  50);
        spnGrid = new JSpinner(gridModel);
        northControls.add(spnGrid);

        JLabel lblView = new JLabel("View Size:", SwingConstants.CENTER);
        northControls.add(lblView);

        SpinnerModel viewModel = new SpinnerNumberModel(40, 10, 80,  10);
        spnView = new JSpinner(viewModel);
        spnView.addChangeListener(e -> addSimulationViewer());
        northControls.add(spnView);

        JLabel lblGeneration = new JLabel("Generation: 0", SwingConstants.CENTER);
        northControls.add(lblGeneration);

        JLabel lblPositions = new JLabel("Position: (x, y)", SwingConstants.CENTER);
        northControls.add(lblPositions);

        jFrame.add(northControls, BorderLayout.NORTH);
    }

    public void addSimulationViewer() {
        JPanel newSimulationViewer = new JPanel();

        int viewSize = (int) spnView.getValue();

        GridLayout gridLayout = new GridLayout(viewSize, viewSize, 0, 0);
        newSimulationViewer.setLayout(gridLayout);

        for(int i = 0; i < viewSize; i ++) {
            for(int j = 0; j < viewSize; j ++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                newSimulationViewer.add(button);
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

        JButton btnClear = new JButton("Clear");
        southControls.add(btnClear);

        jFrame.add(southControls, BorderLayout.SOUTH);
    }
}
