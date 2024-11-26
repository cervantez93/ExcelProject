package ExcelTyperProject.A_TempPackageName;

import org.apache.commons.math3.analysis.function.Max;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        TeamResultsObject teamResultsObject = new TeamResultsObject();
        Map<String, TeamResultsObject> mapOfResults = teamResultsObject.initilizeAllTeamResultsMap();

        List<Integer> typerPointsAllRoundsList = new ArrayList<>();
        List<TyperObject> typerObjectList = new ArrayList<>();


        for (int i = 0; i < GetPlayerNames.getAmountOfPlayers(); i++) {
            typerPointsAllRoundsList.add(i, 0);
            try {
                typerObjectList.add(new TyperObject(GetPlayerNames.getNames().get(i), 0, 0, 0, 0, 0));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        //zmienna określająca ilość plików do przetworzenia znajdujących się w oddzielnym package'u
        File directory = new File("src/main/java/ExcelTyperProject/AllRoundsFiles");
        int fileAmount = directory.list().length;
        //  System.out.println("Ilość kolejek (plików) = " + fileAmount);

        for (int roundNumber = 0; roundNumber < fileAmount; roundNumber++) {
            // iterowanie po pętli w celu przetworzenia wszystkich plików
            String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer" + (roundNumber + 1) + ".txt";
            //  ReadFiles.readFiles(path);

            //    System.out.println("\nKolejka: " + (roundNumber + 1));

            // indeks 0 = Damian, 1 = Ryszard, 2 = Paweł, 3 = Łukasz
            List<Integer> typerPointsOneRoundList = new ArrayList<>();
            typerPointsOneRoundList.add(0, 0);
            typerPointsOneRoundList.add(1, 0);
            typerPointsOneRoundList.add(2, 0);
            typerPointsOneRoundList.add(3, 0);

            //regex, w którym są usuwane wszystkie (dodatkowo polskie) litery
            List<String> stringList = ReadFiles.readFiles(path).stream().map(e -> e.replaceAll("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]", "")).toList();

            //stringList.size to ilość meczów w jednej kolejce
            for (int stringlistIterator = 0; stringlistIterator < stringList.size(); stringlistIterator++) {
                List<String> tempResults = new ArrayList<>();
                // Jest 4 typerów + wynik, a więc łącznie 5x3 znaków (wynik format X:Y, zakładając, że nikt nie obstawi dwucfrowego wyniku)
                // W ten sposób otrzymujemy 5 wyników, można je przypisać do zmiennych stringlistIterator porówynwać realny wynik do wyników typera
                for (int j = 0; j < ((GetPlayerNames.getAmountOfPlayers()) * 3 + 3); j += 3) {
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

                PutResultsOnMap.bothTeamResultsObjectUpdate2(teamResultsObjectHome, teamResultsObjectAway, tempCheckResult,
                        (Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(0)).toString())), (Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(2)).toString())));


                for (int k = 1; k <= tempResults.size() - 1; k++) {
                    //sprawdzanie typu wyniku każdego meczu
                    int tempResultType = CheckResult.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                    //sprawdzenie typu wyniku dla każdego TYPERA
                    int tempTyperResultType = CheckResult.checkResultType(tempResults.get(k).charAt(0), tempResults.get(k).charAt(2));

                    if (tempResultType == tempTyperResultType) {
                        if ((tempResults.get(0).charAt(0) == tempResults.get(k).charAt(0)) && (tempResults.get(0).charAt(2) == tempResults.get(k).charAt(2))) {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 3));
                            typerObjectList.get(k - 1).setExactResultsAmount((typerObjectList.get(k - 1).getExactResultsAmount() + 1));
                            typerObjectList.get(k - 1).setCorrectResultsAmount((typerObjectList.get(k - 1).getCorrectResultsAmount() + 1));
//                            System.out.println("Dla gracza " + typerObjectList.get(k - 1).getName() + " dodaję 3 pkt za DOKŁADNY WYNIK [" + teamResultsObjectHome.getTeamName() + " - " + teamResultsObjectAway.getTeamName() + ", wynik:  " + (tempResults.get(0).charAt(0) + ":"
//                                    + (tempResults.get(0).charAt(2) + "]" + ", a który obstawił wynikiem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        } else {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 1));
                            typerObjectList.get(k - 1).setCorrectResultsAmount((typerObjectList.get(k - 1).getCorrectResultsAmount() + 1));
//                            System.out.println("Dla gracza  " + typerObjectList.get(k - 1).getName() + " dodaję 1 pkt za mecz zakończony wynikiem [" + teamResultsObjectHome.getTeamName() + " - " + teamResultsObjectAway.getTeamName() + ", wynik:  " + (tempResults.get(0).charAt(0) + ":"
//                                    + (tempResults.get(0).charAt(2) + "]" + ", a który obstawił typem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        }
                    }
                }
            }

            for (int n = 0; n < 4; n++) {
                typerPointsAllRoundsList.set(n, typerPointsAllRoundsList.get(n) + typerPointsOneRoundList.get(n));
                if (typerObjectList.get(n).getRecordAmountOfPointsInOneRound() < typerPointsOneRoundList.get(n)) {
                    typerObjectList.get(n).setRecordAmountOfPointsInOneRound(typerPointsOneRoundList.get(n));
                    typerObjectList.get(n).setRecordAmountOfPointsInOneRound_RoundNumber(roundNumber + 1);
                }
            }

            if ((roundNumber + 1) == 3) {
                //odjęcie statystyk za mecz 3 kolejki: Śląsk - Radomiak
                typerPointsOneRoundList.set(1, typerPointsOneRoundList.get(1) - 1);
                mapOfResults.get("ŚląskWrocław").addHomeScoredGoals(-9);
                mapOfResults.get("ŚląskWrocław").setHomeWonGames(-1);
                mapOfResults.get("ŚląskWrocław").setHomePoints(mapOfResults.get("ŚląskWrocław").getHomePoints() - 3);
                mapOfResults.get("RadomiakRadom").addAwayLostGoals(-9);
                mapOfResults.get("RadomiakRadom").setAwayLostGames(-1);
            }

//            System.out.println("\nPodsumowanie " + (roundNumber + 1) + " kolejki: \n"
//                    + typerObjectList.get(0).getName() + " uzyskał: " + typerPointsOneRoundList.get(0) + " punktów\n"
//                    + typerObjectList.get(1).getName() + " uzyskał: " + typerPointsOneRoundList.get(1) + " punktów\n"
//                    + typerObjectList.get(2).getName() + " uzyskał: " + typerPointsOneRoundList.get(2) + " punktów\n"
//                    + typerObjectList.get(3).getName() + " uzyskał: " + typerPointsOneRoundList.get(3) + " punktów\n");

            //wypisanie wyniku po 8 kolejce - w momencie gdy Łukasz zrezygnował
//            if (roundNumber == 8) {
//                System.out.println("Stan po kolejce: " + (roundNumber));
//                for (int n = 0; n < 4; n++) {
//                    System.out.println(typerObjectList.get(n).getName() + ": [" + "punkty: " + typerPointsAllRoundsList.get(n) + ", dokładne wyniki: " + typerObjectList.get(n).getExactResultsAmount()
//                            + ", rekodowa ilość punktów: " + typerObjectList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce " + typerObjectList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "]");
//                }
//            }

            //TODO: koniec pierwszego fora
        }

        for (int n = 0; n < 4; n++) {
            //prawidłowe ustawienie liczby zsumowanych punktów
            typerObjectList.get(n).setPoints(typerPointsAllRoundsList.get(n));
            System.out.println(typerObjectList.get(n).getName() + ": [" + "punkty: " + typerObjectList.get(n).getPoints() + ", dokładne wyniki: " + typerObjectList.get(n).getExactResultsAmount()
                    + ", rekodowa ilość punktów: " + typerObjectList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce " + typerObjectList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "]");
        }

        System.out.println(funFacts(mapOfResults));

        //    chooseTableVariant(mapOfResults);
        //KONIEC MAINA
    }


    public static String funFacts(Map<String, TeamResultsObject> mapOfResults) {
        MaxValuesInMap maxValuesInMap = new MaxValuesInMap();

        return "Ciekawostki:\n" + MaxValuesInMap.maxAmountOfWins(mapOfResults) + "\n" +
                MaxValuesInMap.maxAmountOfLoses(mapOfResults) + "\n" +
                MaxValuesInMap.maxAmountOfDraws(mapOfResults) + "\n" +
                MaxValuesInMap.maxScoredGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxHomeScoredGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxAwayScoredGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxLostGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxHomeLostGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxAwayLostGoals(mapOfResults);
    }


    public static void chooseTableVariant(Map<String, TeamResultsObject> mapOfResults) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz opcję: ");
        System.out.println("1. Tabela łączna.");
        System.out.println("2. Tabela domowa.");
        System.out.println("3. Tabela wyjazdowa.");
        System.out.println("4. Zakończ program");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.print("\nTabela łączna: ");
                SortMapToTable.sortedAllMatchesInTable(mapOfResults);
                break;
            case 2:
                System.out.print("\nTabela domowa: ");
                SortMapToTable.sortedHomeMatchesInTable(mapOfResults);
                break;
            case 3:
                System.out.print("\nTabela wyjazdowa: ");
                SortMapToTable.sortedAwayMatchesInTable(mapOfResults);
                break;
            case 4:
                System.out.print("\nTabela wyjazdowa: ");
                SortMapToTable.sortedAwayMatchesInTable(mapOfResults);
                break;
        }
    }
}

