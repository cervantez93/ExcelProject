package ExcelTyperProject.A_TempPackageName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {
    public static List<String> readFiles(String path) {
        List<String> processedFile = new ArrayList<>();
        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replaceAll("\\s", "");
                processedFile.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        processedFile.remove(0); // usunięcie zbędnej pierwszej linijki
        return processedFile;
    }
}
