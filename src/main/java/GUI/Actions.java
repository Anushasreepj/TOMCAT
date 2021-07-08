package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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
        data.actionsPanel.add(btnStop);

        JButton btnStep = new JButton("Step");
        data.actionsPanel.add(btnStep);

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
