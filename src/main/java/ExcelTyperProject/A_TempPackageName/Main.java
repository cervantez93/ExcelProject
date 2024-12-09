package ExcelTyperProject.A_TempPackageName;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        TeamResultsObject teamResultsObject = new TeamResultsObject();
        Map<String, TeamResultsObject> mapOfResults = teamResultsObject.initilizeAllTeamResultsMap();

        List<Integer> typerPointsAllRoundsList = new ArrayList<>();
        List<TyperObject> typerObjectList = new ArrayList<>();
        List<TyperObject> firstRoundResults = new ArrayList<>();
        List<TyperObject> secondRoundResults = new ArrayList<>();
        int numberOfPlayers = GetPlayerNames.getAmountOfPlayers();

        for (int i = 0; i < numberOfPlayers; i++) {
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

        int roundNumber2 = 0;

        for (int roundNumber = 0; roundNumber < fileAmount; roundNumber++) {
            roundNumber2++;
            String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer" + (roundNumber + 1) + ".txt";

            //System.out.println("\nKolejka: " + (roundNumber + 1));

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
                for (int j = 0; j < ((numberOfPlayers * 3) + 3); j += 3) {
                    tempResults.add(stringList.get(stringlistIterator).substring(j + 1, j + 4));  //Wynik, Damian, Ryszard, Paweł, Łukasz <-- pierwszy jest wynik, dalej typy graczy
                }

                List<String> teamNamesFromThisRound = TeamNamesList.getTeamNames(path); // tutaj jest lista drużyn w ODPOWIEDNIEJ kolejności dla każdej kolejki
                //dopasowanie indeksów do iteracji w tej pętli, żeby nie robić oddzielnej pętli
                int firstIndex = 2 * stringlistIterator;
                int secondIndex = firstIndex + 1;
                //pobranie informacji o drużynie gospodarzy i gości - dopasowanie poprzez klucz w mapie
                TeamResultsObject teamResultsObjectHome = mapOfResults.get(teamNamesFromThisRound.get(firstIndex));
                TeamResultsObject teamResultsObjectAway = mapOfResults.get(teamNamesFromThisRound.get(secondIndex));

                int tempCheckResult = CheckResult.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                PutResultsOnMap.bothTeamResultsObjectUpdate(teamResultsObjectHome, teamResultsObjectAway, tempCheckResult,
                        (Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(0)).toString())), (Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(2)).toString())));


                for (int k = 1; k <= numberOfPlayers; k++) {
                    //sprawdzanie typu wyniku każdego meczu
                    int tempResultType = CheckResult.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                    //sprawdzenie typu wyniku dla każdego TYPERA
                    int tempTyperResultType = CheckResult.checkResultType(tempResults.get(k).charAt(0), tempResults.get(k).charAt(2));

                    if (tempResultType == tempTyperResultType) {
                        if ((tempResults.get(0).charAt(0) == tempResults.get(k).charAt(0)) && (tempResults.get(0).charAt(2) == tempResults.get(k).charAt(2))) {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 3));
                            typerObjectList.get(k - 1).setExactResultsAmount((typerObjectList.get(k - 1).getExactResultsAmount() + 1));
                            typerObjectList.get(k - 1).setCorrectResultsAmount((typerObjectList.get(k - 1).getCorrectResultsAmount() + 1));
                            //TODO: NIE KASOWAĆ - KONTROLNY SOUT!
//                            System.out.println("Dla gracza " + typerObjectList.get(k - 1).getName() + " dodaję 3 pkt za DOKŁADNY WYNIK [" + teamResultsObjectHome.getTeamName() + " - " + teamResultsObjectAway.getTeamName() + ", wynik:  " + (tempResults.get(0).charAt(0) + ":"
//                                    + (tempResults.get(0).charAt(2) + "]" + ", a który obstawił wynikiem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        } else {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 1));
                            typerObjectList.get(k - 1).setCorrectResultsAmount((typerObjectList.get(k - 1).getCorrectResultsAmount() + 1));
                            //TODO: NIE KASOWAĆ - KONTROLNY SOUT!
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
                mapOfResults.get("ŚląskWrocław").setHomeWonGames(teamResultsObject.getHomeWonGames() - 1);
                mapOfResults.get("ŚląskWrocław").setHomePoints(mapOfResults.get("ŚląskWrocław").getHomePoints() - 3);
                mapOfResults.get("RadomiakRadom").addAwayLostGoals(-9);
                mapOfResults.get("RadomiakRadom").setAwayLostGames(-1);
            }

            //TODO: NIE KASOWAĆ - KONTROLNY SOUT!
//            System.out.println("\nPodsumowanie " + (roundNumber + 1) + " kolejki: \n"
//                    + typerObjectList.get(0).getName() + " uzyskał: " + typerPointsOneRoundList.get(0) + " punktów\n"
//                    + typerObjectList.get(1).getName() + " uzyskał: " + typerPointsOneRoundList.get(1) + " punktów\n"
//                    + typerObjectList.get(2).getName() + " uzyskał: " + typerPointsOneRoundList.get(2) + " punktów\n"
//                    + typerObjectList.get(3).getName() + " uzyskał: " + typerPointsOneRoundList.get(3) + " punktów\n");

            //wypisanie wyniku po 8 kolejce - w momencie gdy Łukasz zrezygnował
            //TODO: NIE KASOWAĆ - KONTROLNY SOUT!
//            if (roundNumber == 8) {
//                System.out.println("Stan po kolejce: " + (roundNumber));
//                for (int n = 0; n < 4; n++) {
//                    System.out.println(typerObjectList.get(n).getName() + " - punkty: " + typerPointsAllRoundsList.get(n) + ", dokładne wyniki: " + typerObjectList.get(n).getExactResultsAmount()
//                            + ", rekodowa ilość punktów: " + typerObjectList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + typerObjectList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
//                }
//            }

            //Podsumowanie pierwszej rundy
            if (roundNumber == 15) {
                for (int n = 0; n < 4; n++) {
                    typerObjectList.get(n).setPoints(typerPointsAllRoundsList.get(n));
                }
                firstRoundResults.addAll(typerObjectList);
                //System.out.println(" yyy gracz: " + firstRoundResults.get(0).getPoints());
            }

            //TODO:Podsumowanie drugiej rundy
//            if (roundNumber == 33) {
//                for (int n = 0; n < 4; n++) {
//                    typerObjectList.get(n).setPoints(typerPointsAllRoundsList.get(n));
//                }
//                TyperRoundResults.secondRoundResults(firstRoundResults, typerObjectList);
//            }


            //TODO: wyświetlenie aktualnego wyniku dla drugiej rundy (bez pierwszej rundy)
            // DO POPRAWY
            if (roundNumber > fileAmount -2) {
                secondRoundResults.addAll(firstRoundResults);


                for (int i = 0; i < numberOfPlayers; i++) {
                    secondRoundResults.get(i).setPoints(0);
                }
                {
                    //Resetowanie wartości punktów, a następnie nadawanie jej poprawnych wartości
                    for (int n = 0; n < numberOfPlayers; n++) {
                        secondRoundResults.get(n).setPoints((secondRoundResults.get(n).getPoints() + typerPointsOneRoundList.get(n)));
                        System.out.println(typerObjectList.get(n).getName() + " - punkty w drugiej rundzie: " + secondRoundResults.get(n).getPoints());
                    }
                    //    TyperRoundResults.currentSecondRoundResults(typerObjectList, firstRoundResults);
                }
                if (roundNumber == fileAmount - 1) {
                    //System.out.println(" zzzz gracz: " + secondRoundResults.get(0).getPoints());
                }

            }


//            if (roundNumber == fileAmount - 2) {
//                System.out.println(" ddddd gracz: " + firstRoundResults.get(0).getPoints());
//                System.out.println("Kolejka: " + (roundNumber + 1) + "\n");
//                {
////                    for (int n = 0; n < 4; n++) {
////                        typerObjectList.get(n).setPoints(typerPointsAllRoundsList.get(n));
////                    }
//                    TyperRoundResults.currentSecondRoundResults(typerObjectList, firstRoundResults);
//                }
//                System.out.println(" zzzz gracz: " + firstRoundResults.get(0).getPoints());
//
//            }
            //TODO: koniec pierwszego fora
        }

        for (int n = 0; n < 4; n++) {
            //prawidłowe ustawienie liczby zsumowanych punktów
            typerObjectList.get(n).setPoints(typerPointsAllRoundsList.get(n));
            System.out.println(typerObjectList.get(n).getName() + " - punkty: " + typerObjectList.get(n).getPoints() + ", dokładne wyniki: " + typerObjectList.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + typerObjectList.get(n).getCorrectResultsAmount() +
                    ", rekodowa ilość punktów: " + typerObjectList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + typerObjectList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
        }

        //funFacts(mapOfResults);

        //TODO: poprawić tak, żeby wykonywało się to na SAMYM KOŃCU - chodzi o podmianę wyniku rekordu domowej wygranej Śląska
        // PutResultsOnMap.slaskWroclawHomeRecordCorrection();

        //PrintTable.printAwayTable(mapOfResults);

        //TODO: pomyśleć jak dodać rekordową wygraną domową i wyjazdową
        // Można liczyć różnicę bramek i założyć, że rekordowa wygrana to ta z największą ilością strzelonych bramek (np 5:2 jest lepszym wynikiem niż 3:0)
        // Analogicznie dla rekordowych przegranych, remisów nie ma chyba sensu.

        //KONIEC MAINA
    }


    public static void funFacts(Map<String, TeamResultsObject> mapOfResults) {
        System.out.println("Statystyki:\n" +
                MaxValuesInMap.maxAmountOfWins(mapOfResults) + "\n" +
                MaxValuesInMap.maxAmountOfLoses(mapOfResults) + "\n" +
                MaxValuesInMap.maxAmountOfDraws(mapOfResults) + "\n" +
                MaxValuesInMap.maxScoredGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxHomeScoredGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxAwayScoredGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxLostGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxHomeLostGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxAwayLostGoals(mapOfResults) + "\n" +
                MaxValuesInMap.maxGoalDifference(mapOfResults) + "\n" +
                MaxValuesInMap.maxHomeGoalDifference(mapOfResults) + "\n" +
                MaxValuesInMap.maxAwayGoalDifference(mapOfResults) + "\n" +
                MinValuesInMap.minAmountOfWins(mapOfResults) + "\n" +
                MinValuesInMap.minAmountOfLoses(mapOfResults) + "\n" +
                MinValuesInMap.minAmountOfDraws(mapOfResults) + "\n" +
                MinValuesInMap.minScoredGoals(mapOfResults) + "\n" +
                MinValuesInMap.minHomeScoredGoals(mapOfResults) + "\n" +
                MinValuesInMap.minAwayScoredGoals(mapOfResults) + "\n" +
                MinValuesInMap.minLostGoals(mapOfResults) + "\n" +
                MinValuesInMap.minHomeLostGoals(mapOfResults) + "\n" +
                MinValuesInMap.minAwayLostGoals(mapOfResults) + "\n" +
                MinValuesInMap.minGoalDifference(mapOfResults) + "\n" +
                MinValuesInMap.minHomeGoalDifference(mapOfResults) + "\n" +
                MinValuesInMap.minAwayGoalDifference(mapOfResults) + "\n");
    }

}

