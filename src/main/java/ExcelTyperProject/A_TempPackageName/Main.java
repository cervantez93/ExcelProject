package ExcelTyperProject.A_TempPackageName;


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //TODO: wystarczy wybrać największą wartość z instancji TyperClass
        int allTimeRecordPointsInOneRound = 0;

        // tutaj można umieszczać wyniki drużyny w formie: <{NazwaDrużyny}, {Nazwa drużyny, ilość punktów (wzięta z
        // rzeczywistch wyników), ilość strzelonych bramek, ilość straconych bramek; a także bramki i punkty dom/wyjazd
        // (można tylko te liczyć, a następnie sumować do łącznej tabelki)}>


        TeamResultsObject teamResultsObject = new TeamResultsObject();


        // tutaj kluczem, czyli integerem będzie numer kolejki, a listą będzie teamResultsObject - metody mapy powinny pomóc
        // w liczeniu punktów, bramek itd (streamOf)
        Map<Integer, Map<String, TeamResultsObject>> teamAllResults = new HashMap<>();

        //Format wrzucania danych : teamAllResults.put(numerKolejki wzięty z iteratora pętli,
        // teamResultsObject <nazwaDrużyny, {lista stringów zawięrająca inty określające ilość punktów, bramek itd});
        // teamAllResults.put(i, teamResultsObject) -> teamAllResults.put(numerKolejki, teamResultsObject<nazwaDrużyny, {listaStringów});


        String path = "";
        FileReader fileReader;

        //zmienna określająca ilość plików do przetworzenia znajdujących się w oddzielnym package'u
        File directory = new File("src/main/java/ExcelTyperProject/AllRoundsFiles");
        int fileAmount = directory.list().length;

        for (int i = 0; i < fileAmount; i++) {
            // iterowanie po pętli w celu przetworzenia wszystkich plików
            path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer" + (i + 1) + ".txt";
            ReadFiles.readFiles(path);


            //TODO : zweryfikować czy nie należy tego zrobić w metodzie readFiles, bo będzie to po 8 nazw drużyn,
            // być może trzeba tam wywołać metody z klasy PutResultsOnList, a następnie z klas liczących wyniki drużyn,
            // a także typerów

            //TODO trzeba pobrać nazwę drużyny gości
            String awayTeamName = "";
            //TODO trzeba pobrać nazwę drużyny gości
            String homeTeamName = "";

           // PutResultsOnList.awayResults(path, awayTeamName, teamResultsObject);
          //  PutResultsOnMap.bothTeamResultsObjectUpdate(path, homeTeamName, teamResultsObject);

        }

//TODO można pewnie przenieść do klasy LoadingFiles
//        for (int i = 0; i < fileAmount; i++) {
//            // iterowanie po pętli w celu przetworzenia wszystkich plików
//            path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer" + (i + 1) + ".txt";
//            try {
//                fileReader = new FileReader(path);
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    System.out.println(line);
//                }
//                bufferedReader.close();
//            } catch (FileNotFoundException ex) {
//                throw new RuntimeException(ex);
//            } catch (IOException ex) {
//                System.out.println("Problem z wczytaniem pliku");
//                throw new RuntimeException(ex);
//            }
//        }

    }
}
