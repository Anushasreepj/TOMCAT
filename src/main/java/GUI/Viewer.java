package GUI;

import Logic.Simulation;

import javax.swing.*;
import java.awt.*;

public class Viewer {
    public static void addViewerPanel(Data data) {
        int widthSize = data.getGridWidth();
        int heightSize = data.getGridHeight();

        JPanel newSimulationViewer = new JPanel();
        newSimulationViewer.setPreferredSize(new Dimension(widthSize * 100, heightSize * 100));

        JPanel gridPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(heightSize, widthSize, 0, 0);
        gridPanel.setLayout(gridLayout);

        JScrollPane scrollFrame = new JScrollPane(gridPanel);
        scrollFrame.setPreferredSize(Utils.getSimulationSize());
        newSimulationViewer.setAutoscrolls(true);
        newSimulationViewer.add(scrollFrame);

        if (data.viewerPanel == null) {
            data.simulation = new Simulation(widthSize, heightSize);
        }

        for(int i = 0; i < heightSize; i ++) {
            for(int j = 0; j < widthSize; j ++) {
                JButton button = new JButton();
                button.setBackground(data.simulation.board[i][j] ? Color.BLACK : Color.WHITE);
                button.setPreferredSize(data.getCellSize());
                gridPanel.add(button);
            }
        }

        if (data.viewerPanel != null) {
            data.jFrame.getContentPane().remove(data.viewerPanel);
            data.jFrame.getContentPane().add(newSimulationViewer, BorderLayout.CENTER);
            data.jFrame.revalidate();
        }
        else {
            data.jFrame.getContentPane().add(newSimulationViewer, BorderLayout.CENTER);
        }

        data.viewerPanel = newSimulationViewer;
    }
}
