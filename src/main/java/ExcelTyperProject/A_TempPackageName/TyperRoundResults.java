package ExcelTyperProject.A_TempPackageName;

import java.util.List;

public class TyperRoundResults {

    static int numberOfPlayers = GetPlayerNames.getAmountOfPlayers();

    public static String firstRoundResults(List<TyperObject> typerRoundResults) {

        String roundSumUp = "\nLista wyników po zakończonej pierwszej rundzie: \n";

        for (int n = 0; n < numberOfPlayers; n++) {
            roundSumUp += typerRoundResults.get(n).getName() + " - punkty: " + typerRoundResults.get(n).getPoints() + ", dokładne wyniki: " + typerRoundResults.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + typerRoundResults.get(n).getCorrectResultsAmount() +
                    ", rekodowa ilość punktów: " + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "\n";
//            System.out.println(typerRoundResults.get(n).getName() + " - punkty: " + typerRoundResults.get(n).getPoints() + ", dokładne wyniki: " + typerRoundResults.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + typerRoundResults.get(n).getCorrectResultsAmount() +
//                    ", rekodowa ilość punktów: " + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
        }
        return roundSumUp += "\n";
        //  System.out.println(roundSumUp);
    }

    //TODO: trzeba wyczyścić wynki z pierwszej rundy i liczyć je od 17 rundy, najlepiej po prostu odjąć
    // od całkowitych wyników wyniki z pierwszej rundy - wtedy można będzie śledzić aktualne wyniki drugiej rundy
    public static void secondRoundResults(List<TyperObject> firstRoundResults, List<TyperObject> secondRoundResults) {

        String roundSumUp = "\nLista wyników po zakończonej drugiej rundzie: \n";

        for (int n = 0; n < numberOfPlayers; n++) {
            secondRoundResults.get(n).setCorrectResultsAmount(secondRoundResults.get(n).getCorrectResultsAmount() - firstRoundResults.get(n).getCorrectResultsAmount());
            secondRoundResults.get(n).setExactResultsAmount(secondRoundResults.get(n).getExactResultsAmount() - firstRoundResults.get(n).getExactResultsAmount());
            secondRoundResults.get(n).setPoints(secondRoundResults.get(n).getPoints() - firstRoundResults.get(n).getPoints());

            //TODO: tutaj trzeba będzie rozróżnić dwie zmienne (dla dwóch rund) lub jakoś inaczej to weryfikować - do poprawy / weryfikacji
            secondRoundResults.get(n).setRecordAmountOfPointsInOneRound(secondRoundResults.get(n).getRecordAmountOfPointsInOneRound() - firstRoundResults.get(n).getRecordAmountOfPointsInOneRound());
            secondRoundResults.get(n).setRecordAmountOfPointsInOneRound_RoundNumber(secondRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() - firstRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());

            roundSumUp += secondRoundResults.get(n).getName() + " - punkty: " + secondRoundResults.get(n).getPoints() + ", dokładne wyniki: " + secondRoundResults.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + secondRoundResults.get(n).getCorrectResultsAmount() +
                    ", rekodowa ilość punktów: " + secondRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + secondRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "\n";
//            System.out.println(typerRoundResults.get(n).getName() + " - punkty: " + typerRoundResults.get(n).getPoints() + ", dokładne wyniki: " + typerRoundResults.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + typerRoundResults.get(n).getCorrectResultsAmount() +
//                    ", rekodowa ilość punktów: " + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + typerRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
        }
        roundSumUp += "\n";
        System.out.println(roundSumUp);
    }

    public static void currentSecondRoundResults(List<TyperObject> firstRoundResults, List<TyperObject> secondRoundResults) {

        String roundSumUp = "\nAktualna lista wyników drugiej rundy: \n";
        //System.out.println(" xxx gracz: " + (secondRoundResults.get(0).getPoints() - firstRoundResults.get(0).getPoints()));

        for (int n = 0; n < numberOfPlayers; n++) {
            secondRoundResults.get(n).setCorrectResultsAmount(secondRoundResults.get(n).getCorrectResultsAmount() - firstRoundResults.get(n).getCorrectResultsAmount());
            secondRoundResults.get(n).setExactResultsAmount(secondRoundResults.get(n).getExactResultsAmount() - firstRoundResults.get(n).getExactResultsAmount());
            secondRoundResults.get(n).setPoints(secondRoundResults.get(n).getPoints() - firstRoundResults.get(n).getPoints());

            System.out.println(n + " gracz: " + secondRoundResults.get(n).getPoints());

            //TODO: tutaj trzeba będzie rozróżnić dwie zmienne (dla dwóch rund) lub jakoś inaczej to weryfikować - do poprawy / weryfikacji
            // secondRoundResults.get(n).setRecordAmountOfPointsInOneRound(secondRoundResults.get(n).getRecordAmountOfPointsInOneRound() - firstRoundResults.get(n).getRecordAmountOfPointsInOneRound());
            //  secondRoundResults.get(n).setRecordAmountOfPointsInOneRound_RoundNumber(secondRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() - firstRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());


            roundSumUp += secondRoundResults.get(n).getName() + " - punkty: " + secondRoundResults.get(n).getPoints() + ", dokładne wyniki: " + secondRoundResults.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + secondRoundResults.get(n).getCorrectResultsAmount() +
                    ", rekodowa ilość punktów: " + secondRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + secondRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber() + "\n";
            //   System.out.println(firstRoundResults.get(n).getName() + " - punkty: " + firstRoundResults.get(n).getPoints() + ", dokładne wyniki: " + firstRoundResults.get(n).getExactResultsAmount() + ", prawidłowe typy (łącznie z dokładnymi wynikami): " + firstRoundResults.get(n).getCorrectResultsAmount() +
            //           ", rekodowa ilość punktów: " + firstRoundResults.get(n).getRecordAmountOfPointsInOneRound() + " w kolejce nr:" + firstRoundResults.get(n).getRecordAmountOfPointsInOneRound_RoundNumber());
        }

        roundSumUp += "\n";
               System.out.println(roundSumUp);
    }
}
