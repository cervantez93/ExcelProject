package ExcelTyperProject.A_TempPackageName;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EverythingNewApproach {

    public static void main(String[] args) {

        TeamResultsObject teamResultsObject = new TeamResultsObject();
        Map<String, TeamResultsObject> mapOfResults = teamResultsObject.initilizeAllTeamResultsMap();

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

        //zmienna określająca ilość plików do przetworzenia znajdujących się w oddzielnym package'u
        File directory = new File("src/main/java/ExcelTyperProject/AllRoundsFiles");
        int fileAmount = directory.list().length;
        System.out.println("Ilość kolejek (plików) = " + fileAmount);

        for (int roundNumber = 0; roundNumber < fileAmount; roundNumber++) {
            // iterowanie po pętli w celu przetworzenia wszystkich plików
            String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer" + (roundNumber + 1) + ".txt";
            ReadFiles.readFiles(path);

            //  System.out.println("\nAktualny numer kolejki: " + (roundNumber + 1));

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
                List<String> teamNamesFromThisRound = teamNamesList.getTeamNames(path); // tutaj jest lista drużyn dla każdej kolejki w ODPOWIEDNIEJ kolejności

                int tempCheckResult = checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                //dopasowanie indeksów do iteracji w tej pętli, żeby nie robić oddzielnej pętli
                int firstIndex = 2 * stringlistIterator;
                int secondIndex = firstIndex + 1;

                //pobranie informacji o drużynie gospodarzy i gości - dopasowanie poprzez klucz w mapie
                TeamResultsObject teamResultsObjectHome = mapOfResults.get(teamNamesFromThisRound.get(firstIndex));
                TeamResultsObject teamResultsObjectAway = mapOfResults.get(teamNamesFromThisRound.get(secondIndex));

                if (tempCheckResult == 1) {
                    teamResultsObjectHome.setHomePoints(teamResultsObjectHome.getHomePoints() + 3);
                    teamResultsObjectHome.setHomeWonGames(teamResultsObjectHome.getHomeWonGames() + 1);
                    teamResultsObjectAway.setAwayLostGames(teamResultsObjectAway.getAwayLostGames() + 1);

                } else if (tempCheckResult == 2) {
                    teamResultsObjectAway.setAwayPoints(teamResultsObjectAway.getAwayPoints() + 3);
                    teamResultsObjectAway.setAwayWonGames(teamResultsObjectAway.getAwayWonGames() + 1);
                    teamResultsObjectHome.setHomeLostGames(teamResultsObjectHome.getHomeLostGames() + 1);

                } else if (tempCheckResult == 0) {
                    teamResultsObjectHome.setHomePoints(teamResultsObjectHome.getHomePoints() + 1);
                    teamResultsObjectHome.setHomeDrawGames(teamResultsObjectHome.getHomeDrawGames() + 1);
                    teamResultsObjectAway.setAwayPoints(teamResultsObjectAway.getAwayPoints() + 1);
                    teamResultsObjectAway.setAwayDrawGames(teamResultsObjectAway.getHomeDrawGames() + 1);

                } else if (tempCheckResult == 3) {
                    System.out.println("Wystąpił błąd powiązany z obliczeniem typu zwycięstwa (checkResult)");
                }


                //TODO: to są wartości int z tempResults - przerobione z charów!!!!!!!!
                teamResultsObjectHome.addHomeScoredGoals(Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(0)).toString()));
                teamResultsObjectHome.addHomeLostGoals(Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(2)).toString()));
                // ^^ wyżej update drużyny gospodarzy

                // niżej update drużyny gości
                teamResultsObjectAway.addAwayLostGoals(Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(0)).toString()));
                teamResultsObjectAway.addAwayScoredGoals(Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(2)).toString()));

                for (int k = 1; k <= tempResults.size() - 1; k++) {
                    //sprawdzanie typu wyniku każdego meczu
                    int tempResultType = EverythingNewApproach.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                    //sprawdzenie typu wyniku dla każdego TYPERA
                    int tempTyperResult = EverythingNewApproach.checkResultType(tempResults.get(k).charAt(0), tempResults.get(k).charAt(2));

//                    System.out.println("Porównuję typy zwycięstw " + tempResultType + ", " + tempTyperResult);
//                    System.out.println("Sprawdzam czy wynik " + tempResults.get(0).charAt(0) + ":" + tempResults.get(0).charAt(2) + " (Typ " + tempResultType + ") jest równy wynikowi "
//                            + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + " (Typ " + tempTyperResult + ")");

                    if (tempResultType == tempTyperResult) {
                        if ((tempResults.get(0).charAt(0) == tempResults.get(k).charAt(0)) && (tempResults.get(0).charAt(2) == tempResults.get(k).charAt(2))) {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 3));

                            typerClassList.get(k - 1).setExactResultsAmount((typerClassList.get(k - 1).getExactResultsAmount() + 1));
                            typerClassList.get(k - 1).setCorrectResultsAmount((typerClassList.get(k - 1).getCorrectResultsAmount() + 1));
//                            System.out.println("Dla gracza o indeksie[" + (k - 1) + "], dodaję 3 pkt za dokładny wynik [" + (tempResults.get(0).charAt(0) + ":"
//                                    + (tempResults.get(0).charAt(2) + "]" + " ,a który obstawił wynikiem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        } else {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 1));
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
                if (typerClassList.get(n).getRecordAmountOfPointsInOneRound() < typerPointsOneRoundList.get(n)) {
                    typerClassList.get(n).setRecordAmountOfPointsInOneRound(typerPointsOneRoundList.get(n));
                    typerClassList.get(n).setRecordAmountOfPointsInOneRound_RoundNumber(roundNumber + 1);
                }
            }

            //TODO: w zasadzie też powinno odjąć się punkty, bramki strzelone/stracone itd Śląskowi i Radomiakowi dopóki nie zostanie rozegrana zaległa kolejka
            if ((roundNumber + 1) == 3) {
                //odjęcie jednego punktu za mecz 3 kolejki: Śląsk - Radomiak
                typerPointsOneRoundList.set(1, typerPointsOneRoundList.get(1) - 1);
                mapOfResults.get("ŚląskWrocław").addHomeScoredGoals(-9);
                mapOfResults.get("RadomiakRadom").addAwayLostGoals(-9);
            }

            //TODO: koniec pierwszego fora
        }

        for (int n = 0; n < 4; n++) {
            //prawidłowe ustawienie liczby zsumowanych punktów
            typerClassList.get(n).setPoints(typerPointsAllRoundsList.get(n));
            System.out.println(typerClassList.get(n).getName() + ": [" + "punkty: " + typerClassList.get(n).getPoints() + ", dokładne wyniki: " + typerClassList.get(n).getExactResultsAmount()
                    + ", rekodowa ilość punktów: " + typerClassList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce " + typerClassList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "]");
        }

        //TODO : SPRAWDZIĆ POZOSTAŁE WARTOŚCI PÓL KAŻDEGO TYPERA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        System.out.println(" Stan tabeli: " + mapOfResults.values());
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

