package ExcelTyperProject.A_TempPackageName;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        TeamResultsObject teamResultsObject = new TeamResultsObject();
        Map<String, TeamResultsObject> mapOfResults = teamResultsObject.initilizeAllTeamResultsMap();

        List<Integer> typerPointsAllRoundsList = new ArrayList<>(); // lista zdobytych punktów per typer
        List<TyperObject> typerObjectList = new ArrayList<>();
        List<TyperObject> secondRoundResults = new ArrayList<>();

        int numberOfPlayers = GetPlayerNames.getAmountOfPlayers(); // liczba typerów

        //Inicjalizacja list wyników dla typerów
        for (int i = 0; i < numberOfPlayers; i++) {
            typerPointsAllRoundsList.add(i, 0);
            try {
                //Ta lista będzie trzymać łączne wyniki dla wszystkich kolejek (plików)
                typerObjectList.add(new TyperObject(GetPlayerNames.getNames().get(i), 0, 0, 0, 0, 0));
                //Ta lista będzie trzymać wyniki dla drugiej rundy (od 18 kolejki włącznie)
                secondRoundResults.add(new TyperObject(GetPlayerNames.getNames().get(i), 0, 0, 0, 0, 0));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        //Zmienna określająca ilość plików do przetworzenia znajdujących się w oddzielnym package'u
        File directory = new File("src/main/java/ExcelTyperProject/AllRoundsFiles");
        int fileAmount = directory.list().length;


        for (int roundNumber = 0; roundNumber < fileAmount; roundNumber++) {
            //Ustalenie ścieżki pobierania pliku, każdy plik ma w nazwie liczbę oznaczającą numer kolejki
            String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer" + (roundNumber + 1) + ".txt";

            //System.out.println("\nKolejka: " + (roundNumber + 1));

            // indeks 0 = Damian, 1 = Ryszard, 2 = Paweł, 3 = Łukasz
            List<Integer> typerPointsOneRoundList = new ArrayList<>();
            typerPointsOneRoundList.add(0, 0);
            typerPointsOneRoundList.add(1, 0);
            typerPointsOneRoundList.add(2, 0);
            typerPointsOneRoundList.add(3, 0);


            //Regex, w którym są usuwane wszystkie (dodatkowo polskie) litery
            List<String> stringList = ReadFiles.readFiles(path).stream().map(e -> e.replaceAll("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]", "")).toList();

            //stringList.size to ilość meczów w jednej kolejce
            for (int stringlistIterator = 0; stringlistIterator < stringList.size(); stringlistIterator++) {
                List<String> tempResults = new ArrayList<>();
                // Jest 4 typerów + wynik, a więc łącznie 5x3 znaków (wynik format X:Y, zakładając, że nikt nie obstawi dwucfrowego wyniku)
                // W ten sposób otrzymujemy 5 wyników, można je przypisać do zmiennych stringlistIterator porówynwać realny wynik do wyników typera
                for (int j = 0; j < ((numberOfPlayers * 3) + 3); j += 3) {
                    tempResults.add(stringList.get(stringlistIterator).substring(j + 1, j + 4));  //Wynik, Damian, Ryszard, Paweł, Łukasz <-- pierwszy jest wynik, dalej typy graczy
                }

                //Lista drużyn w ODPOWIEDNIEJ kolejności dla każdej kolejki (unikalna dla każdej kolejki)
                List<String> teamNamesFromThisRound = TeamNamesList.getTeamNames(path);
                //Dopasowanie indeksów do iteracji w tej pętli, żeby nie robić oddzielnej pętli
                int firstIndex = 2 * stringlistIterator;
                int secondIndex = firstIndex + 1;
                //Pobranie informacji o drużynie gospodarzy i gości - dopasowanie poprzez klucz w mapie
                TeamResultsObject teamResultsObjectHome = mapOfResults.get(teamNamesFromThisRound.get(firstIndex));
                TeamResultsObject teamResultsObjectAway = mapOfResults.get(teamNamesFromThisRound.get(secondIndex));

                //Ustalenie rodzaju wyniku dla danego meczu
                int tempCheckResult = CheckResult.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                //Aktualizacja danych obu drużyn z danego meczu
                PutResultsOnMap.bothTeamResultsObjectUpdate(teamResultsObjectHome, teamResultsObjectAway, tempCheckResult,
                        (Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(0)).toString())), (Integer.valueOf(Character.valueOf(tempResults.get(0).charAt(2)).toString())));

                //TODO: sprawdzić czy tempCheckResult to dokładnie ta sama wartość co tempResultType, jeśli tak, to można jedną z nich

                //Sprawdzanie czy dany typer miał prawidłowy typ / dokładny wynk
                for (int k = 1; k <= numberOfPlayers; k++) {
                    //Sprawdzanie typu wyniku każdego meczu
                    int tempResultType = CheckResult.checkResultType(tempResults.get(0).charAt(0), tempResults.get(0).charAt(2));

                    //Sprawdzenie typu wyniku dla każdego TYPERA
                    int tempTyperResultType = CheckResult.checkResultType(tempResults.get(k).charAt(0), tempResults.get(k).charAt(2));

                    //Porównanie typów wyników --> mecz == typ danego gracza
                    if (tempResultType == tempTyperResultType) {
                        if ((tempResults.get(0).charAt(0) == tempResults.get(k).charAt(0)) && (tempResults.get(0).charAt(2) == tempResults.get(k).charAt(2))) {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 3));
                            typerObjectList.get(k - 1).setExactResultsAmount((typerObjectList.get(k - 1).getExactResultsAmount() + 1));
                            typerObjectList.get(k - 1).setCorrectResultsAmount((typerObjectList.get(k - 1).getCorrectResultsAmount() + 1));


                            //TODO: sprawdzić czy prawidłowo dolicza tutaj i niżej w else dla samego prawidłowego typu
                            //Ustawienie wartości dla drugiej rundy
                            if ((roundNumber + 1) > 17) {
                                //    System.out.println("jestem tutaj yyyy");
                                secondRoundResults.get(k - 1).setExactResultsAmount((secondRoundResults.get(k - 1).getExactResultsAmount() + 1));
                                secondRoundResults.get(k - 1).setCorrectResultsAmount((secondRoundResults.get(k - 1).getCorrectResultsAmount() + 1));
                            }

                            //TODO: NIE KASOWAĆ - KONTROLNY SOUT!
//                            System.out.println("Dla gracza " + typerObjectList.get(k - 1).getName() + " dodaję 3 pkt za DOKŁADNY WYNIK [" + teamResultsObjectHome.getTeamName() + " - " + teamResultsObjectAway.getTeamName() + ", wynik:  " + (tempResults.get(0).charAt(0) + ":"
//                                    + (tempResults.get(0).charAt(2) + "]" + ", a który obstawił wynikiem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        } else {
                            typerPointsOneRoundList.set(k - 1, (typerPointsOneRoundList.get(k - 1) + 1));
                            typerObjectList.get(k - 1).setCorrectResultsAmount((typerObjectList.get(k - 1).getCorrectResultsAmount() + 1));

                            //Ustawienie wartości dla drugiej rundy
                            if ((roundNumber + 1) > 17) {
                                //      System.out.println("jestem tutaj xxxx");
                                secondRoundResults.get(k - 1).setCorrectResultsAmount((secondRoundResults.get(k - 1).getCorrectResultsAmount() + 1));
                            }

                            //TODO: NIE KASOWAĆ - KONTROLNY SOUT!
//                            System.out.println("Dla gracza  " + typerObjectList.get(k - 1).getName() + " dodaję 1 pkt za mecz zakończony wynikiem [" + teamResultsObjectHome.getTeamName() + " - " + teamResultsObjectAway.getTeamName() + ", wynik:  " + (tempResults.get(0).charAt(0) + ":"
//                                    + (tempResults.get(0).charAt(2) + "]" + ", a który obstawił typem: [" + tempResults.get(k).charAt(0) + ":" + tempResults.get(k).charAt(2) + "]")));
                        }
                    }
                }
            }

            //Aktualizacja wyników typerów, ustawienie ilości punktów, sprawdzenie czy w danej kolejce padł rekord punktowy dla danego gracza
            for (int n = 0; n < 4; n++) {
                typerPointsAllRoundsList.set(n, typerPointsAllRoundsList.get(n) + typerPointsOneRoundList.get(n));
                typerObjectList.get(n).setPoints(typerPointsAllRoundsList.get(n) + typerPointsOneRoundList.get(n));
                if (typerObjectList.get(n).getRecordAmountOfPointsInOneRound() < typerPointsOneRoundList.get(n)) {
                    typerObjectList.get(n).setRecordAmountOfPointsInOneRound(typerPointsOneRoundList.get(n));
                    typerObjectList.get(n).setRecordAmountOfPointsInOneRound_RoundNumber(roundNumber + 1);

                }//Ustawienie wartości dla drugiej rundy
                if ((roundNumber + 1) > 17) {
                    secondRoundResults.get(n).setPoints(secondRoundResults.get(n).getPoints() + typerPointsOneRoundList.get(n));
                    if (secondRoundResults.get(n).getRecordAmountOfPointsInOneRound() < typerPointsOneRoundList.get(n)) {
                        // System.out.println("Jestem tutaj, kolejka: " + (roundNumber + 1));
                        secondRoundResults.get(n).setRecordAmountOfPointsInOneRound_RoundNumber(roundNumber + 1);
                        secondRoundResults.get(n).setRecordAmountOfPointsInOneRound(typerPointsOneRoundList.get(n));
                    }
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


            //wypisanie wyniku po 8 kolejce - w momencie gdy Łukasz zrezygnował
            //TODO: NIE KASOWAĆ - KONTROLNY SOUT!
//            if (roundNumber == 8) {
//                System.out.println("Stan po kolejce: " + (roundNumber));
//                for (int n = 0; n < 4; n++) {
//                    System.out.println(typerObjectList.get(n).getName() + " - punkty: " + typerPointsAllRoundsList.get(n) + ", dokładne wyniki: " + typerObjectList.get(n).getExactResultsAmount()
//                            + ", rekodowa ilość punktów: " + typerObjectList.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + typerObjectList.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
//                }
//           }


            //TODO: trzeba poprawić i sprawdzić SOUTami ustawianie punktów dla różnych metod / list (poza typerObjectList - do 18 kolejki liczy prawidłowo)!!!!
            //TODO: trzeba poprawić i sprawdzić SOUTami ustawianie punktów dla różnych metod / list (poza typerObjectList - do 18 kolejki liczy prawidłowo)!!!!

            //Podsumowanie pierwszej rundy (roundNumber == 16 -> 17 kolejek łącznie)
            TyperRoundResults.firstRoundResults(typerObjectList, roundNumber);
//            if (roundNumber == 16) {
//                TyperRoundResults.firstRoundResults(typerObjectList, roundNumber);
//            }


            //Liczenie od 18 kolejki włącznie - wyniki aktualne dla drugiej rundy
            // Jeśli potrzebne tylko podsumowanie dla aktualnej kolejki, wystarczy zmienić na if ((roundNumber + 1) == fileAmount
            if ((roundNumber + 1) > 17) {
                //  System.out.println("Kolejka:" + (roundNumber + 1));
                // TyperRoundResults.currentOrSecondRoundResults(secondRoundResults);
            }//TODO: plik Typer19.txt dodany tylko do testów!!

            //TODO:  do sprawdzenia czy 33/34 zależnie gdzie jest inkrementowany licznik kolejek
            if ((roundNumber + 1) == 34) {
                System.out.println("Kolejka:" + (roundNumber + 1));
                TyperRoundResults.currentOrSecondRoundResults(secondRoundResults);
            }


        }//TODO: koniec pierwszego fora


        //TODO: kluczowy SOUT! Wyświetlanie całkowitych wyników - DZIAŁA NA 100% PRAWIDŁOWO!!!
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


    }//TODO:KONIEC MAINA

    //Ciekawostki, statystyki - największe i najmniejsze wartości dla drużyn
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

