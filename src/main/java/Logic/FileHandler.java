package Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public static boolean isFilePresent(File file) {
        return file.exists();
    }

    public static void createEmptyFile(String path) {
        try {
            new File(path).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFile(String path) {
        ArrayList<String> content = new ArrayList<>();

        try {
            File myFile = new File(path);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                content.add(myReader.nextLine());
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static void saveFile(String filePath, ArrayList<String> fileContent) {
        createEmptyFile(filePath);

        try {
            FileWriter myWriter = new FileWriter(filePath);

            for (String s : fileContent) {
                myWriter.write(s + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addLine(String patternPaths, String loadPattern, boolean isUnique) {
        try {
            ArrayList<String> fileContent = readFile(patternPaths);

            if (!isUnique || !fileContent.contains(loadPattern)) {
                FileWriter myWriter = new FileWriter(patternPaths);
                myWriter.write(loadPattern + "\n");
                myWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeLineFromFile(String path, String lineToRemove) {
        ArrayList<String> fileContent = readFile(path);
        fileContent.remove(lineToRemove);

        createEmptyFile(path);
        saveFile(path, fileContent);
    }
}
