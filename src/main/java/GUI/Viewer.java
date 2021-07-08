package GUI;

import javax.swing.*;
import java.awt.*;

public class Viewer {
    public static void addViewerPanel(Data data) {
        int widthSize = data.getGridWidth();
        int heightSize = data.getGridHeight();

        JPanel newSimulationViewer = new JPanel();
        newSimulationViewer.setPreferredSize(
            new Dimension(widthSize * data.getZoom(), heightSize * data.getZoom())
        );

        JPanel gridPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(heightSize, widthSize, 0, 0);
        gridPanel.setLayout(gridLayout);

        data.scrollPane = new JScrollPane(gridPanel);
        data.scrollPane.setPreferredSize(Utils.getSimulationSize());
        newSimulationViewer.setAutoscrolls(true);
        newSimulationViewer.add(data.scrollPane);

        Controls.setPositionLabel(data);
        data.scrollPane.getViewport().addChangeListener(e -> Controls.setPositionLabel(data));

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

    public static int getScrollXPosition(Data data) {
        return data.scrollPane.getHorizontalScrollBar().getValue();
    }

    public static int getScrollYPosition(Data data) {
        return data.scrollPane.getVerticalScrollBar().getValue();
    }
}
