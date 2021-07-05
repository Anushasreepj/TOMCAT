import GUI.SimulationWindow;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class GameOfLife {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        SimulationWindow simulationWindow = new SimulationWindow();
        simulationWindow.createWindow();
    }
}
