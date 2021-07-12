package GUI;

import javax.swing.*;

public class WindowUI {
    Data data;

    public WindowUI() {
        data = new Data();
    }

    public void createWindow() {
        data.jFrame = new JFrame("Game of Life");
        data.jFrame.setSize(Utils.getWindowSize());

        SimMenu.addMenuBar(data);

        Controls.addControlsPanel(data);
        Viewer.addViewerPanel(data);
        Actions.addActionsPanel(data);

        data.jFrame.setVisible(true);
        data.jFrame.setResizable(false);
        data.jFrame.setLocationRelativeTo(null);
        data.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
