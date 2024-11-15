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


    //TODO: jeśli wszystko działa (zwłaszcza getNames()) - usunąć te gettery
    //gettery - zwracanie imion graczy
//    public String getTyper1() {
//        try {
//            return getNames().get(0);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String getTyper2() {
//        try {
//            return getNames().get(1);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String getTyper3() {
//        try {
//            return getNames().get(2);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String getTyper4() {
//        try {
//            return getNames().get(3);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
