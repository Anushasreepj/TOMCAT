package GUI;

import javax.swing.*;
import java.awt.*;

public class Viewer {
    public static void addViewerPanel(Data data) {
        int xScrollPosition = 0;
        int yScrollPosition = 0;

        int widthSize = data.getGridWidth();
        int heightSize = data.getGridHeight();

        JPanel newSimulationViewer = new JPanel();
        newSimulationViewer.setPreferredSize(
            new Dimension(widthSize * data.getZoom(), heightSize * data.getZoom())
        );

        JPanel gridPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(heightSize, widthSize, 0, 0);
        gridPanel.setLayout(gridLayout);

        if(data.viewerPanel != null) {
            yScrollPosition = data.scrollPane.getVerticalScrollBar().getValue();
            xScrollPosition = data.scrollPane.getHorizontalScrollBar().getValue();
        }

        data.scrollPane = new JScrollPane(gridPanel);
        data.scrollPane.setPreferredSize(Utils.getSimulationSize());
        newSimulationViewer.setAutoscrolls(true);
        newSimulationViewer.add(data.scrollPane);

        Controls.setPositionLabel(data);
        data.scrollPane.getViewport().addChangeListener(e -> Controls.setPositionLabel(data));

        for(int y = 0; y < heightSize; y++) {
            for(int x = 0; x < widthSize; x++) {
                JButton button = new JButton();
                button.setPreferredSize(data.getCellSize());
                updateButtonColour(button, data.simulation.board[y][x]);
                gridPanel.add(button);

                int finalY = y;
                int finalX = x;
                button.addActionListener(e -> {
                    data.simulation.switchCell(finalY, finalX);
                    updateButtonColour(button, data.simulation.board[finalY][finalX]);
                });
            }
        }

        if (data.viewerPanel != null) {
            data.jFrame.getContentPane().remove(data.viewerPanel);
            data.jFrame.getContentPane().add(newSimulationViewer, BorderLayout.CENTER);
            data.jFrame.revalidate();
            data.scrollPane.getHorizontalScrollBar().setValue(xScrollPosition);
            data.scrollPane.getVerticalScrollBar().setValue(yScrollPosition);
        }
        else {
            data.jFrame.getContentPane().add(newSimulationViewer, BorderLayout.CENTER);
        }

        data.viewerPanel = newSimulationViewer;
    }

    private static void updateButtonColour(JButton button, boolean cellState) {
        button.setBackground(cellState ? Color.BLACK : Color.WHITE);
    }

    public static int getScrollXPosition(Data data) {
        return data.scrollPane.getHorizontalScrollBar().getValue();
    }

    public static int getScrollYPosition(Data data) {
        return data.scrollPane.getVerticalScrollBar().getValue();
    }
}
