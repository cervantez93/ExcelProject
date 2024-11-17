package ExcelTyperProject.A_TempPackageName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EverythingInOneClass {

    // TODO: Następnie trzeba zaktualizować wartości typera i drużyn

    // TODO: Prawidłowe wartości tabeli wyników dla drużyn można porównać ze stanem tabeli np po 2 kolejce na jakimś flashscore

    public static void main(String[] args) {

        List<Integer> typerPointsAllRoundsList = new ArrayList<>();
        List<TyperClass> typerClassList = new ArrayList<>();
        GetPlayerNames getPlayerNames = new GetPlayerNames();

        for (int i = 0; i < 4; i++) {
            typerPointsAllRoundsList.add(i, 0);
            try {
                typerClassList.add(new TyperClass(getPlayerNames.getNames().get(i), 0, 0, 0, 0, 0));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        //TODO chyba nie będzie potrzebne, jeśli będzie to pobierane z MAPY.getValue
        TeamResultsObject teamResultsObject = new TeamResultsObject();

        //zmienna określająca ilość plików do przetworzenia znajdujących się w oddzielnym package'u
        File directory = new File("src/main/java/ExcelTyperProject/AllRoundsFiles");
        int fileAmount = directory.list().length;
        System.out.println("Ilość kolejek (plików) = " + fileAmount);

        for (int roundNumber = 0; roundNumber < fileAmount; roundNumber++) {
            // iterowanie po pętli w celu przetworzenia wszystkich plików
            String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer" + (roundNumber + 1) + ".txt";
            ReadFiles.readFiles(path);


            // indeks 0 = Damian, 1 = Ryszard, 2 = Paweł, 3 = Łukasz
            List<Integer> typerPointsOneRoundList = new ArrayList<>();
            typerPointsOneRoundList.add(0, 0);
            typerPointsOneRoundList.add(1, 0);
            typerPointsOneRoundList.add(2, 0);
            typerPointsOneRoundList.add(3, 0);

            List<String> stringList = ReadFiles.readFiles(path);


            //regex, w którym są usuwane wszystkie (dodatkowo polskie) litery
            stringList = stringList.stream().map(e -> e.replaceAll("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]", "")).toList();

            List<String> tempResults = new ArrayList<>();

            //stringList.size to ilość meczów w jednej kolejce
            for (int stringlistIterator = 0; stringlistIterator < stringList.size(); stringlistIterator++) {

                // Jest 4 typerów + wynik, a więc łącznie 5x3 znaków (wynik format X:Y, zakładając, że nikt nie obstawi dwucfrowego wyniku)
                // W ten sposób otrzymujemy 5 wyników, można je przypisać do zmiennych stringlistIterator porówynwać realny wynik do wyników typera
                for (int j = 0; j < 15; j += 3) {
                    tempResults.add(stringList.get(stringlistIterator).substring(j + 1, j + 4));  //Wynik, Damian, Ryszard, Paweł, Łukasz <-- pierwszy jest wynik, dalej typy graczy
                }

                //TODO : aby uzupełniać dane o drużynach będzie trzeba iterować po tej liście z przyrostem stringlistIterator+=2 (pobierać dwie nazwy drużyn co iterację)
                TeamNamesList teamNamesList = new TeamNamesList();
               // Ten sout zwraca poprawnie parę drużyn
                //System.out.println("Para drużyn to: " + teamNamesList.getTeamNames(path).get(0) + " oraz " + teamNamesList.getTeamNames(path).get(1));

                List<String> teamNamesFromThisRound = teamNamesList.getTeamNames(path); // tutaj jest lista drużyn dla każdej kolejki w ODPOWIEDNIEJ kolejności

                int tempCheckResult = checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));
                // wynik checkResult przekazany jest do metody bothTeamResultsObjectUpdate -> a tam dalej w zależności od typu wyniku następują update'y drużyn

                //dopasowanie indeksów do iteracji w tej pętli, żeby nie robić oddzielnej pętli
                int firstIndex = 2 * stringlistIterator;
                int secondIndex = firstIndex + 1;

                // TODO: Ta mapa będzie zbierać wyniki jednej kolejki, a następnie będzie je zerować (patrz sposób inicjalizacji
                //  Z niej można przenosić wyniki do mapy zawierającej wszystkie wyniki (per kolejka)
                // TODO: tutaj trzeba przeształcić sposób inicjalizacji - bo inicjalizuje się chyba ciągle ten sam obiekt
                Map<String, TeamResultsObject> initilizeTempTeamResultsMap = teamResultsObject.initilizeTeamResultsMap(path, firstIndex, secondIndex);
                System.out.println("Aktuany stan, klucze w mapie: "+initilizeTempTeamResultsMap.keySet());

                //TODO: zamiast get trzeba dać put? Tak czy siak wygląda na przypiswanie wartości do tej samej drużyny
                PutResultsOnMap putResultsOnMap = new PutResultsOnMap();


//               initilizeTempTeamResultsMap.bothTeamResultsObjectUpdate <-- metodę bothTeamResultsObjectUpdate przenieść do klasy TeamResultsObject dzięki czemu
//                  na instancji teamResultsObject można będzie właśnie tą metodę wywołać - i nie będzie to pusta mapa

                System.out.println("Para drużyn to: " + teamNamesFromThisRound.get(firstIndex)+" oraz " + teamNamesFromThisRound.get(secondIndex));

                teamResultsObject.bothTeamResultsObjectUpdate(path, teamResultsObject, teamNamesFromThisRound.get(firstIndex),
                        teamNamesFromThisRound.get(secondIndex), tempCheckResult,
                        initilizeTempTeamResultsMap.get(teamNamesFromThisRound.get(firstIndex)).setHomeScoredGoals(+tempResults.get(0).charAt(0)),
                        initilizeTempTeamResultsMap.get(teamNamesFromThisRound.get(firstIndex)).setHomeLostGoals(+tempResults.get(0).charAt(2)),
                        initilizeTempTeamResultsMap.get(teamNamesFromThisRound.get(secondIndex)).setAwayScoredGoals(+tempResults.get(0).charAt(2)),
                        initilizeTempTeamResultsMap.get(teamNamesFromThisRound.get(secondIndex)).setAwayLostGoals(+tempResults.get(0).charAt(0)),
                        firstIndex, secondIndex);

                //bothTeamResultsObjectUpdate(String path, TeamResultsObject teamResultsObject, String teamHomeName, String teamAwayName, int checkResult,
                //                            int homeScoredGoals, int homeLostGoals, int awayScoredGoals, int awayLostGoals)

//                putResultsOnMap.bothTeamResultsObjectUpdate(path, teamResultsObject, teamNamesFromThisRound.get(stringlistIterator),
//                        teamNamesFromThisRound.get(secondIndex), tempCheckResult,
//                        tempResults.get(0).charAt(0),  //bramki strzelone w domu
//                        tempResults.get(0).charAt(2),  //bramki stracone  w domu
//                        tempResults.get(0).charAt(2),  //bramki strzelone na wyjeździe
//                        tempResults.get(0).charAt(0)); //bramki strzelone na wyjeździe


                //TODO: sout kontrolny z nazwami drużyn w danej iteracji
                // System.out.println("Nazwa drużyn: " + teamNamesFromThisRound.get(firstIndex) + " - " + teamNamesFromThisRound.get(secondIndex));


                // TODO: Wyrzucenie souta w ostatniej kolejce // można zrobić co kolejke żeby sprawdzić czy prawidłowo zapisuje wartości do każdej kolejki
                if (roundNumber == fileAmount - 1) {
                    System.out.println("XXX: " + initilizeTempTeamResultsMap.values());
                }


                for (int k = 1; k <= tempResults.size() - 1; k++) {
                    //sprawdzanie typu wyniku każdego meczu
                    int tempResultType = EverythingInOneClass.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                    //sprawdzenie typu wyniku dla każdego TYPERA
                    int tempTyperResult = EverythingInOneClass.checkResultType(tempResults.get(k).charAt(0), tempResults.get(k).charAt(2));
                    //   System.out.println("Porównuję typy zwycięstw " + tempResultType + ", " + tempTyperResult);
//                    System.out.println("Sprawdzam czy wynik " + tempResults.get(0).charAt(0) + ":" + tempResults.get(0).charAt(2) + " (Typ " + tempResultType + ") jest równy wynikowi "
//                            + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + " (Typ " + tempTyperResult + ")");

                    if (tempResultType == tempTyperResult) {
                        if ((tempResults.get(0).charAt(0) == tempResults.get(k).charAt(0)) && (tempResults.get(0).charAt(2) == tempResults.get(k).charAt(2))) {
                            // dodajemy danemu zawodnikowi 3pkt, zwiększamy mu licznk exact points, sprawdzamy informacje nt rekordowej ilości pkt typera w kolejce itd
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 3));

                            typerClassList.get(k - 1).setExactResultsAmount((typerClassList.get(k - 1).getExactResultsAmount() + 1));
                            // typerClassList.get(k - 1).setPoints((typerClassList.get(k - 1).getPoints() + 3));
                            typerClassList.get(k - 1).setCorrectResultsAmount((typerClassList.get(k - 1).getCorrectResultsAmount() + 1));
//                            System.out.println("Dla gracza o indeksie[" + (k - 1) + "], dodaję 3 pkt za dokładny wynik [" + (tempResults.get(0).charAt(0) + ":"
//                                    + (tempResults.get(0).charAt(2) + "]" + " ,a który obstawił wynikiem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        } else {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 1));
                            //  typerClassList.get(k - 1).setPoints((typerClassList.get(k - 1).getPoints() + 1));
                            typerClassList.get(k - 1).setCorrectResultsAmount((typerClassList.get(k - 1).getCorrectResultsAmount() + 1));
//                            System.out.println("Dla gracza o indeksie[" + (k - 1) + "], dodaję 1 pkt za wynik [" + (tempResults.get(0).charAt(0) + ":"
//                                    + (tempResults.get(0).charAt(2) + "]" + " ,a który obstawił wynikiem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        }
                    }
                }
                tempResults.clear();
            }

            for (int n = 0; n < 4; n++) {
                typerPointsAllRoundsList.set(n, typerPointsAllRoundsList.get(n) + typerPointsOneRoundList.get(n));
                // TODO tutaj można ewentualnie się zastanowić czy nie zrobić z parametru setRecordAmountOfPointsInOneRound_RoundNumber listy, który w przypadku 2 lub więcej
                //  kolejek z takim samym rekordową ilością punktów zapisywał by numery kolejek
                if (typerClassList.get(n).getRecordAmountOfPointsInOneRound() < typerPointsOneRoundList.get(n)) {
                    typerClassList.get(n).setRecordAmountOfPointsInOneRound(typerPointsOneRoundList.get(n));
                    typerClassList.get(n).setRecordAmountOfPointsInOneRound_RoundNumber(roundNumber + 1);
                }
//                System.out.println("Rekord punktów w jednej kolejce gracza [" + typerClassList.get(n).getName() + "], ilość punktów "
//                        + typerClassList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce numer " + typerClassList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
            }

            //TODO: w zasadzie też powinno odjąć się punkty, bramki strzelone/stracone itd Śląskowi i Radomiakowi dopóki nie zostanie rozegrana zaległa kolejka
            if ((roundNumber + 1) == 3) {
                //odjęcie jednego punktu za mecz 3 kolejki: Śląsk - Radomiak
                typerPointsOneRoundList.set(1, typerPointsOneRoundList.get(1) - 1);
            }
            System.out.println("Łączna punktacja " + (roundNumber + 1) + " kolejki [Damian, Ryszard, Paweł, Łukasz] =" + typerPointsOneRoundList);


            //TODO: koniec pierwszego fora
        }

        for (int n = 0; n < 4; n++) {
            //prawidłowe ustawienie liczby zsumowanych punktów
            typerClassList.get(n).setPoints(typerPointsAllRoundsList.get(n));
            System.out.println(typerClassList.get(n).getName() + ": [" + "punkty: " + typerClassList.get(n).getPoints() + ", dokładne wyniki: " + typerClassList.get(n).getExactResultsAmount()
                    + ", rekodowa ilość punktów: " + typerClassList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce " + typerClassList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "]");
        }


        //TODO : SPRAWDZIĆ POZOSTAŁE WARTOŚCI PÓL KAŻDEGO TYPERA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //KONIEC MAINA
    }


    // 1 - zwycięsto gospodarzy, 2 - zwycięstwo gości, 0 - remis // 3 można uznać za błąd <-- ew przerobić metodę
    public static int checkResultType(int homeTeamGoals, int awayTeamGoals) {
        int result = 3;
        if (homeTeamGoals == awayTeamGoals) {
            result = 0;
        } else if (homeTeamGoals > awayTeamGoals) {
            result = 1;
        }
        if (homeTeamGoals < awayTeamGoals) {
            result = 2;
        }
        return result;
    }
}

