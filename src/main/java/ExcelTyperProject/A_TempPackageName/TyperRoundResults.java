package ExcelTyperProject.A_TempPackageName;

import java.util.List;

public class TyperRoundResults {

    static int numberOfPlayers = GetPlayerNames.getAmountOfPlayers();

    //Metoda podsumowująca wyniki pierwszej rundy po 17 rundach ekstraklasy.
    public static void firstRoundResults(List<TyperObject> typerRoundResults, int roundNumber) {

        if (roundNumber == 16) {
            System.out.println("\nPodusmowanie pierwszej rundy " + " - po " + (roundNumber + 1) + " kolejkach:");
            for (int n = 0; n < numberOfPlayers; n++) {
                System.out.println(typerRoundResults.get(n).getName() + " - punkty: " + typerRoundResults.get(n).getPoints() + ", dokładne wyniki: " + typerRoundResults.get(n).getExactResultsAmount()
                        + ", rekodowa ilość punktów: " + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
            }
            System.out.println("Notka: Łukasz grał tylko 8 kolejek." + "\n");
        }
    }


    //TODO: Stworzyć nową listę i dodawać wyniki do tej nowej listy, można tu w zasadzie zależnie od potrzeby i implementacji (w klasie Main) wyświetlać wyniki co kolejkę, aktualny stan
    // lub np podusmowanie całkowite drugiej rundy (po 34 kolejkach), można na samym końcu dodać wartości z obu list i wyświetlać łączne wyniki z całego sezonu dla typerów
    public static void currentOrSecondRoundResults(List<TyperObject> typerRoundResults) {

        //TODO: można wyrzucić tego souta i przerzucić do maina, gdzie będzie informował o aktualnym stanie wyników / podsumowaniu drugiej rundy po 34 rundach
        System.out.println("\nPodusmowanie drugiej rundy:");
        //Łukasz jest ostatnim graczem w tabelkach, można go wykluczyć z racji tego, że już nie gra, więc jego wyniki są zawsze zerowe //TODO: można od 18 kolejki nie liczyć jego wyników
        for (int n = 0; n < numberOfPlayers - 1; n++) {
            System.out.println(typerRoundResults.get(n).getName() + " - punkty: " + typerRoundResults.get(n).getPoints() + ", dokładne wyniki: " + typerRoundResults.get(n).getExactResultsAmount()
                    + ", rekodowa ilość punktów: " + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
        }
    }

    //TODO: przy dobrze zaprojektowanej metodzie wyżej + dobrej implementacji w main nie potrzeba dwóch oddzielnych metod
//    public static void currentSecondRoundResults(List<TyperObject> secondRoundResults, List<Integer> typerPointsOneRoundList) {
//
//        String roundSumUp = "";
//        // String roundSumUp = "\nAktualna lista wyników drugiej rundy: \n";
//        //System.out.println(" xxx gracz: " + (secondRoundResults.get(0).getPoints() - firstRoundResults.get(0).getPoints()));
//
//        for (int n = 0; n < numberOfPlayers; n++) {
//            secondRoundResults.get(n).setCorrectResultsAmount(secondRoundResults.get(n).getCorrectResultsAmount() + typerPointsOneRoundList.get(n));
//            secondRoundResults.get(n).setExactResultsAmount(secondRoundResults.get(n).getExactResultsAmount() + typerPointsOneRoundList.get(n));
//            secondRoundResults.get(n).setPoints(secondRoundResults.get(n).getPoints() + typerPointsOneRoundList.get(n));
//
//            // System.out.println(n + " gracz: " + secondRoundResults.get(n).getPoints());
//
//            //TODO: tutaj trzeba będzie rozróżnić dwie zmienne (dla dwóch rund) lub jakoś inaczej to weryfikować - do poprawy / weryfikacji
//            // secondRoundResults.get(n).setRecordAmountOfPointsInOneRound(secondRoundResults.get(n).getRecordAmountOfPointsInOneRound() - firstRoundResults.get(n).getRecordAmountOfPointsInOneRound());
//            //  secondRoundResults.get(n).setRecordAmountOfPointsInOneRound_RoundNumber(secondRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() - firstRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
//
//
//            roundSumUp += secondRoundResults.get(n).getName() + " - punkty: " + secondRoundResults.get(n).getPoints() + ", dokładne wyniki: " + secondRoundResults.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + secondRoundResults.get(n).getCorrectResultsAmount() +
//                    ", rekodowa ilość punktów: " + secondRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + secondRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "\n";
//            //   System.out.println(firstRoundResults.get(n).getName() + " - punkty: " + firstRoundResults.get(n).getPoints() + ", dokładne wyniki: " + firstRoundResults.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + firstRoundResults.get(n).getCorrectResultsAmount() +
//            //           ", rekodowa ilość punktów: " + firstRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + firstRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
//        }
//
//        roundSumUp += "\n";
//        System.out.println(roundSumUp);
//    }



}
