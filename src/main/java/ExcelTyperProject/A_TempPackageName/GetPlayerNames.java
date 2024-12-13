package ExcelTyperProject.A_TempPackageName;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class GetPlayerNames {

    //Pobranie listy imion do Stringa  - z pierwszego wiersza pliku tekstowego
    private static String getTextNames() {
        String names;
        try {
            String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer1.txt";
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Usunięcie spacji, tabów itd
            names = bufferedReader.readLine().replaceAll("\\s", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int removeWords = "MeczWynik".length();
        //substring zaczyna się od litery po "MeczWynik" - do ostatniej litery stringa names
        return names.substring(removeWords, names.length());
    }

    //Rozdzielenie imion ze Stringa do listy imion
    public static List<String> getNames() throws FileNotFoundException {
        //Split - regex -> rozdzielenie stringa na listę substringów, seperatorem są wielkie litery
        return Arrays.stream(getTextNames().split("(?=\\p{Lu})")).toList();
    }

    //Obliczenie liczby typerów
    public static int getAmountOfPlayers() {
        try {
            return getNames().size();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}