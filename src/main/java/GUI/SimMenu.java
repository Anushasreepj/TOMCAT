package GUI;

import Logic.FileHandler;
import Logic.PathHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Scanner;

public class SimMenu {
    public static void addMenuBar(Data data) {
        if (data.menuBar != null) {
            data.menuBar.remove(data.brushMenu);
        }

        data.menuBar = new JMenuBar();

        addFileMenu(data);
        addBrushMenu(data);
        addHelpMenu(data);

        if (data.menuBar != null) {
            data.menuBar.revalidate();
            data.menuBar.repaint();
        }
    }

    private static void addFileMenu(Data data) {
        data.fileMenu = new JMenu("File");

        JMenuItem fileLoadBoard = new JMenuItem("Load Board");
        data.fileMenu.add(fileLoadBoard);
        fileLoadBoard.addActionListener(e -> {
            String workingDirectory = Paths.get("").toAbsolutePath().toString();
            JFileChooser jFileChooser = new JFileChooser(workingDirectory);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setDialogTitle("Load Board File");

            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
                "Text document (*.txt)",
                "txt", "text"
            );
            jFileChooser.setFileFilter(fileFilter);

            int operationIndex = jFileChooser.showOpenDialog(null);

            if(operationIndex == JFileChooser.APPROVE_OPTION) {
                String boardPath = jFileChooser.getSelectedFile().getAbsolutePath();
                data.simulation.loadBoard(boardPath);
                Viewer.addViewerPanel(data);
            }
        });

        JMenuItem fileSaveBoard = new JMenuItem("Save Board");
        data.fileMenu.add(fileSaveBoard);
        fileSaveBoard.addActionListener(e -> {
            String workingDirectory = Paths.get("").toAbsolutePath().toString();
            JFileChooser jFileChooser = new JFileChooser(workingDirectory);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setDialogTitle("Save Board File");

            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
                "Text document (*.txt)",
                "txt", "text"
            );
            jFileChooser.setFileFilter(fileFilter);

            int operationIndex = jFileChooser.showSaveDialog(null);

            if(operationIndex == JFileChooser.APPROVE_OPTION) {
                String boardPath = jFileChooser.getSelectedFile().getAbsolutePath() + ".txt";
                data.simulation.saveBoard(boardPath);
                Viewer.addViewerPanel(data);
            }
        });

        JMenuItem fileLoadPattern = new JMenuItem("Load Pattern");
        data.fileMenu.add(fileLoadPattern);
        fileLoadPattern.addActionListener(e -> {
            String workingDirectory = Paths.get("").toAbsolutePath() + "/" + Data.PATTERN_FOLDER;
            JFileChooser jFileChooser = new JFileChooser(workingDirectory);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setDialogTitle("Load Pattern File");

            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
                "Text document (*.txt)",
                "txt", "text"
            );
            jFileChooser.setFileFilter(fileFilter);

            int operationIndex = jFileChooser.showOpenDialog(null);

            if(operationIndex == JFileChooser.APPROVE_OPTION) {
                String selectedPath = jFileChooser.getSelectedFile().getAbsolutePath();
                String workingPath = new File("").getAbsolutePath();

                URI selectedPathUri = new File(selectedPath).toURI();
                URI workingPathUri = new File(workingPath).toURI();

                data.loadPattern = workingPathUri.relativize(selectedPathUri).toString();
                FileHandler.addLine(Data.PATTERN_PATHS, data.loadPattern, true);
                addMenuBar(data);
            }
        });

        JMenuItem fileRemovePattern = new JMenuItem("Delete Pattern");
        data.fileMenu.add(fileRemovePattern);
        fileRemovePattern.addActionListener(e -> {
            String workingDirectory = Paths.get("").toAbsolutePath() + "/" + Data.PATTERN_FOLDER;
            JFileChooser jFileChooser = new JFileChooser(workingDirectory);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setDialogTitle("Delete Pattern File");

            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
                "Text document (*.txt)",
                "txt", "text"
            );
            jFileChooser.setFileFilter(fileFilter);

            int operationIndex = jFileChooser.showOpenDialog(null);

            if(operationIndex == JFileChooser.APPROVE_OPTION) {
                String selectedPath = jFileChooser.getSelectedFile().getAbsolutePath();
                String workingPath = new File("").getAbsolutePath();

                URI selectedPathUri = new File(selectedPath).toURI();
                URI workingPathUri = new File(workingPath).toURI();

                String patternPath = workingPathUri.relativize(selectedPathUri).toString();
                FileHandler.removeLineFromFile(Data.PATTERN_PATHS, patternPath);
                addMenuBar(data);
            }
        });

        JSeparator fileSeparator = new JSeparator();
        data.fileMenu.add(fileSeparator);

        JMenuItem fileRandomBoard = new JMenuItem("Random Board");
        data.fileMenu.add(fileRandomBoard);
        fileRandomBoard.addActionListener(e -> {
            data.simulation.randomBoard();
            Viewer.addViewerPanel(data);
        });

        JMenuItem fileClearBoard = new JMenuItem("Clear Board");
        data.fileMenu.add(fileClearBoard);
        fileClearBoard.addActionListener(e -> {
            data.simulation.clearBoard();
            Viewer.addViewerPanel(data);
        });

        data.menuBar.add(data.fileMenu);
        data.jFrame.setJMenuBar(data.menuBar);
    }

    private static void addBrushMenu(Data data) {
        data.brushMenu = new JMenu("Brush");

        File patternsFile = new File(Data.PATTERN_PATHS);

        JMenuItem filePattern = new JMenuItem("Cell");
        data.brushMenu.add(filePattern);
        filePattern.addActionListener(e -> {
            data.loadPattern = "";
            Controls.setBrushLabel(data);
        });

        if(FileHandler.isFilePresent(patternsFile)) {
            try {
                Scanner fileReader = new Scanner(patternsFile);

                while (fileReader.hasNextLine()) {
                    String patternPath = fileReader.nextLine();
                    String patternName = PathHandler.getPatternName(patternPath);

                    filePattern = new JMenuItem(patternName);
                    data.brushMenu.add(filePattern);
                    filePattern.addActionListener(e -> {
                        data.loadPattern = patternPath;
                        Controls.setBrushLabel(data);
                    });
                }

                fileReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            FileHandler.createEmptyFile(Data.PATTERN_PATHS);
        }

        data.menuBar.add(data.brushMenu);
        data.jFrame.setJMenuBar(data.menuBar);
    }

    private static void addHelpMenu(Data data) {
        data.helpMenu = new JMenu("Help");

        JMenuItem helpIntro = new JMenuItem("Introduction");
        data.helpMenu.add(helpIntro);
        helpIntro.addActionListener(e -> {
            JFrame messageFrame = new JFrame();
            JOptionPane.showMessageDialog(messageFrame,
        "The Game of Life is simulated on a grid of cells where each cell can either be alive or dead. " +
                "Each status is updated for every generation dependent on a set of rules based on the surrounding cells. " +
                "These cells depend on the state of these 8 cells."
            , "Simulation Rules", JOptionPane.INFORMATION_MESSAGE);
        });

        JMenuItem helpRules = new JMenuItem("Rules");
        data.helpMenu.add(helpRules);
        helpRules.addActionListener(e -> {
            JFrame messageFrame = new JFrame();
            JOptionPane.showMessageDialog(messageFrame,
        "For each generation, each cell status is determined by following set of rules;\n" +
                "1) Any live cell with fewer than two live neighbours dies, as if by underpopulation.\n" +
                "2) Any live cell with two or three live neighbours lives on to the next generation.\n" +
                "3) Any live cell with more than three live neighbours dies, as if by overpopulation.\n" +
                "4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."
            , "Simulation Rules", JOptionPane.INFORMATION_MESSAGE);
        });

        data.menuBar.add(data.helpMenu);
        data.jFrame.setJMenuBar(data.menuBar);
    }
}
