package ExcelTyperProject.A_TempPackageName;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class GetPlayerNames {

    private static String getTextNames() {
        String names;
        try {
            String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer1.txt";
            FileReader fileReader = new FileReader(path);
            String temp;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Usunięcie spacji, tabów itd
            temp = bufferedReader.readLine().replaceAll("\\s", "");
            names = temp;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int removeWords = "MeczWynik".length();
        //substring zaczyna się od litery po "MeczWynik" - do ostatniej litery stringa names
        return names.substring(removeWords, names.length());
    }

    public static List<String> getNames() throws FileNotFoundException {
        //Split - regex -> rozdzielenie stringa na listę substringów, seperatorem są wielkie litery
        return Arrays.stream(getTextNames().split("(?=\\p{Lu})")).toList();
    }
}