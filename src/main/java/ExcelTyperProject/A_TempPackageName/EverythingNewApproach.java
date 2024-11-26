package ExcelTyperProject.A_TempPackageName;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EverythingNewApproach {
    Map<String, TeamResultsObject> unsortedMap = new HashMap<>();

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

            System.out.println("\nKolejka: " + (roundNumber + 1));

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

              List<String> teamNamesFromThisRound = TeamNamesList.getTeamNames(path); // tutaj jest lista drużyn dla każdej kolejki w ODPOWIEDNIEJ kolejności
                //dopasowanie indeksów do iteracji w tej pętli, żeby nie robić oddzielnej pętli
                int firstIndex = 2 * stringlistIterator;
                int secondIndex = firstIndex + 1;
                //pobranie informacji o drużynie gospodarzy i gości - dopasowanie poprzez klucz w mapie
                TeamResultsObject teamResultsObjectHome = mapOfResults.get(teamNamesFromThisRound.get(firstIndex));
                TeamResultsObject teamResultsObjectAway = mapOfResults.get(teamNamesFromThisRound.get(secondIndex));

                int tempCheckResult = CheckResult.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

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
                    teamResultsObjectAway.setAwayDrawGames(teamResultsObjectAway.getAwayDrawGames() + 1);

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
                    int tempResultType = CheckResult.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                    //sprawdzenie typu wyniku dla każdego TYPERA
                    int tempTyperResultType = CheckResult.checkResultType(tempResults.get(k).charAt(0), tempResults.get(k).charAt(2));

                    if (tempResultType == tempTyperResultType) {
                        if ((tempResults.get(0).charAt(0) == tempResults.get(k).charAt(0)) && (tempResults.get(0).charAt(2) == tempResults.get(k).charAt(2))) {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 3));

                            typerClassList.get(k - 1).setExactResultsAmount((typerClassList.get(k - 1).getExactResultsAmount() + 1));
                            typerClassList.get(k - 1).setCorrectResultsAmount((typerClassList.get(k - 1).getCorrectResultsAmount() + 1));
                            System.out.println("Dla gracza " + typerClassList.get(k - 1).getName() + " dodaję 3 pkt za DOKŁADNY WYNIK [" + teamResultsObjectHome.getTeamName() + " - " + teamResultsObjectAway.getTeamName() + ", wynik:  " + (tempResults.get(0).charAt(0) + ":"
                                    + (tempResults.get(0).charAt(2) + "]" + ", a który obstawił wynikiem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        } else {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 1));
                            typerClassList.get(k - 1).setCorrectResultsAmount((typerClassList.get(k - 1).getCorrectResultsAmount() + 1));
                            System.out.println("Dla gracza  " + typerClassList.get(k - 1).getName() + " dodaję 1 pkt za mecz zakończony wynikiem [" + teamResultsObjectHome.getTeamName() + " - " + teamResultsObjectAway.getTeamName() + ", wynik:  " + (tempResults.get(0).charAt(0) + ":"
                                    + (tempResults.get(0).charAt(2) + "]" + ", a który obstawił typem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
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
                //odjęcie statystyk za mecz 3 kolejki: Śląsk - Radomiak
                typerPointsOneRoundList.set(1, typerPointsOneRoundList.get(1) - 1);
                mapOfResults.get("ŚląskWrocław").addHomeScoredGoals(-9);
                mapOfResults.get("ŚląskWrocław").setHomeWonGames(-1);
                mapOfResults.get("ŚląskWrocław").setHomePoints(mapOfResults.get("ŚląskWrocław").getHomePoints() - 3);
                mapOfResults.get("RadomiakRadom").addAwayLostGoals(-9);
                mapOfResults.get("RadomiakRadom").setAwayLostGames(-1);
            }

            System.out.println("Podsumowanie " + (roundNumber + 1) + " kolejki: \n"
                    + typerClassList.get(0).getName() + " uzyskał: " + typerPointsOneRoundList.get(0) + " punktów\n"
                    + typerClassList.get(1).getName() + " uzyskał: " + typerPointsOneRoundList.get(1) + " punktów\n"
                    + typerClassList.get(2).getName() + " uzyskał: " + typerPointsOneRoundList.get(2) + " punktów\n"
                    + typerClassList.get(3).getName() + " uzyskał: " + typerPointsOneRoundList.get(3) + " punktów\n");


            if (roundNumber == 8) {
                System.out.println("Stan po kolejce: " + (roundNumber));
                for (int n = 0; n < 4; n++) {
                    System.out.println(typerClassList.get(n).getName() + ": [" + "punkty: " + typerPointsAllRoundsList.get(n) + ", dokładne wyniki: " + typerClassList.get(n).getExactResultsAmount()
                            + ", rekodowa ilość punktów: " + typerClassList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce " + typerClassList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "]");
                }
            }

            //TODO: koniec pierwszego fora
        }


        for (int n = 0; n < 4; n++) {
            //prawidłowe ustawienie liczby zsumowanych punktów
            typerClassList.get(n).setPoints(typerPointsAllRoundsList.get(n));
            System.out.println(typerClassList.get(n).getName() + ": [" + "punkty: " + typerClassList.get(n).getPoints() + ", dokładne wyniki: " + typerClassList.get(n).getExactResultsAmount()
                    + ", rekodowa ilość punktów: " + typerClassList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce " + typerClassList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "]");
        }


        //TODO: to można wyrzucić do metody (dorzucić może pętlę + ew. czyszczenie ekranu)
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Tabela łączna: ");
                SortMapToTable.sortedAllMatchesInTable(mapOfResults);
                break;
            case 2:
                System.out.println("Tabela domowa: ");
                SortMapToTable.sortedHomeMatchesInTable(mapOfResults);
                break;
            case 3:
                System.out.println("Tabela wyjazdowa: ");
                SortMapToTable.sortedAwayMatchesInTable(mapOfResults);
                break;
        }

        //TODO : SPRAWDZIĆ POZOSTAŁE WARTOŚCI PÓL KAŻDEGO TYPERA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //KONIEC MAINA
    }


    // 1 - zwycięsto gospodarzy, 2 - zwycięstwo gości, 0 - remis // 3 można uznać za błąd <-- ew przerobić metodę

}

