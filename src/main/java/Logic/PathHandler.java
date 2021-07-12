package Logic;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

public class PathHandler {
    public static String getPrettyPatternName(String patternPath) {
        String patternFileName = Paths.get(patternPath).getFileName().toString();

        int pos = patternFileName.lastIndexOf(".");
        if (pos > 0 && pos < (patternFileName.length() - 1)) {
            patternFileName = patternFileName.substring(0, pos);
        }

        String patternName = patternFileName.replaceAll("([a-z])([A-Z]+)", "$1 $2");
        patternName = patternName.substring(0, 1).toUpperCase() + patternName.substring(1);

        return patternName;
    }

    public static String getRelativePath(String selectedPath) {
        String workingPath = new File("").getAbsolutePath();

        URI selectedPathUri = new File(selectedPath).toURI();
        URI workingPathUri = new File(workingPath).toURI();

        return workingPathUri.relativize(selectedPathUri).toString();
    }
}
