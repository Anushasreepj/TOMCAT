package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Actions {
    public static void addActionsPanel(Data data) {
        data.actionsPanel = new JPanel();

        Border padding = BorderFactory.createEmptyBorder(Utils.SPACING, Utils.SPACING, Utils.SPACING, Utils.SPACING);
        data.actionsPanel.setBorder(padding);

        GridLayout gridLayout = new GridLayout(1, 0, Utils.SPACING, Utils.SPACING);
        data.actionsPanel.setLayout(gridLayout);

        JButton btnStart = new JButton("Start");
        data.actionsPanel.add(btnStart);

        JButton btnStop = new JButton("Stop");
        btnStop.setEnabled(false);
        data.actionsPanel.add(btnStop);

        btnStart.addActionListener(e -> {
            data.simulation.isRunning = true;

            btnStart.setEnabled(false);
            btnStop.setEnabled(true);

            CompletableFuture.runAsync(() -> {
                while (data.simulation.isRunning) {
                    data.simulation.simStep();
                    Viewer.addViewerPanel(data);

                    try {
                        TimeUnit.MILLISECONDS.sleep(1000 / data.getFps());
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
        });

        btnStop.addActionListener(e -> {
            data.simulation.isRunning = false;

            btnStart.setEnabled(true);
            btnStop.setEnabled(false);

            Viewer.addViewerPanel(data);
        });

        JButton btnStep = new JButton("Step");
        data.actionsPanel.add(btnStep);
        btnStep.addActionListener(e -> {
            data.simulation.simStep();
            Viewer.addViewerPanel(data);
        });

        JButton btnRandom = new JButton("Random");
        data.actionsPanel.add(btnRandom);
        btnRandom.addActionListener(e -> {
            data.simulation.randomBoard();
            Viewer.addViewerPanel(data);
        });

        JButton btnClear = new JButton("Clear");
        data.actionsPanel.add(btnClear);
        btnClear.addActionListener(e -> {
            data.simulation.clearBoard();
            Viewer.addViewerPanel(data);
        });

        data.jFrame.add(data.actionsPanel, BorderLayout.SOUTH);
    }
}
