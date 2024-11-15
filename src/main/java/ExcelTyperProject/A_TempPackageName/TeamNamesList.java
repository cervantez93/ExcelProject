package ExcelTyperProject.A_TempPackageName;

import java.util.*;
import java.util.stream.Collectors;

//TODO: DZIAŁA!

public class TeamNamesList {

    //Lista drużyn będzie prawidłowa przy założeniu, że w każdym innym pliku drużyny są zapisane w takim samym formacie (np polskie znaki)
    public  List<String> getTeamNames(String path) {
        List<String> names = new ArrayList<>();
        try {
            // TODO: ta lista musi być pobierana w każdej kolejce, żeby móc przypisywać odpowiednim drużynom odpowiedni wartości (punkty, bramki) <-- zrobione za pomocą PATH
            List<String> wholeText = ReadFiles.readFiles(path);
            wholeText = wholeText.stream().map(e -> e.replaceAll("\\d", "").replaceAll(":", "")).toList();

            for (int i = 0; i < wholeText.size(); i++) {
                //czyszczenie tymczasowego stringa  zawierającego pary drużyn
                String temp = "";
                for (int j = 0; j < wholeText.get(i).length(); j++) {
                    if (wholeText.get(i).charAt(j) == '–') {
                        names.add(temp);
                        //dodanie drugiej nazwy drużyny z pary drużyn, +1 wynika z pominięcia myślnika
                        names.add(wholeText.get(i).substring(temp.length() + 1, wholeText.get(i).length()));
                        break;
                    } else {
                        temp += wholeText.get(i).charAt(j);
                    }
                }
            }
            return names;
        } catch (Exception e) {
            System.out.println("Wyrzucono błąd w metodzie getTeamNames()");
            throw new RuntimeException(e);
        }
    }
    }

