package ExcelTyperProject.A_TempPackageName;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


//Metody zwracają tutaj największe wartości dla podanych nazw metod
public class MaxValuesInMap {

   public static String maxAmountOfWins(Map<String, TeamResultsObject> mapOfResults) {

        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getWonGames");

        StringBuilder temp = new StringBuilder("Największa ilość wygranych: [" + result.get(0) + "], drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxAmountOfLoses(Map<String, TeamResultsObject> mapOfResults) {

        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getLostGames");

        StringBuilder temp = new StringBuilder("Największa ilość przegranych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxAmountOfDraws(Map<String, TeamResultsObject> mapOfResults) {

        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getDrawGames");

        StringBuilder temp = new StringBuilder("Największa ilość remisów: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxScoredGoals(Map<String, TeamResultsObject> mapOfResults) {

        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getAllScoredGoals");

        StringBuilder temp = new StringBuilder("Największa ilość łącznie strzelonych bramek: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxLostGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getAllLostGoals");

        StringBuilder temp = new StringBuilder("Największa ilość łącznie straconych bramek: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxHomeLostGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getHomeLostGoals");

        StringBuilder temp = new StringBuilder("Największa ilość straconych bramek w meczach domowych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxAwayLostGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getAwayLostGoals");

        StringBuilder temp = new StringBuilder("Największa ilość straconych bramek w meczach wyjazdowych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxHomeScoredGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getHomeScoredGoals");

        StringBuilder temp = new StringBuilder("Największa ilość strzelonych bramek w meczach domowych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxAwayScoredGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getAwayScoredGoals");

        StringBuilder temp = new StringBuilder("Największa ilość strzelonych bramek w meczach wyjazdowych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i));
        }
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxGoalDifference(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getGoalDifference");

        StringBuilder temp = new StringBuilder("Najlepszy łączny bilans bramek: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxHomeGoalDifference(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getHomeGoalDifference");

        StringBuilder temp = new StringBuilder("Najlepszy domowy bilans bramek: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String maxAwayGoalDifference(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMaxValueAndKeys(mapOfResults, "getAwayGoalDifference");

        StringBuilder temp = new StringBuilder("Najlepszy wyjazdowy bilans bramek:: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }


    public static List<String> findMaxValueAndKeys(Map<String, TeamResultsObject> map, String methodName) {
        Integer maxValue = Integer.MIN_VALUE;
        List<String> keysWithMaxValue = new ArrayList<>();

        for (Map.Entry<String, TeamResultsObject> entry : map.entrySet()) {
            String key = entry.getKey();
            TeamResultsObject teamResultsObject = entry.getValue();

            // Pobierz wartość pola przy użyciu refleksji
            try {
                int value = (int) teamResultsObject.getClass().getMethod(methodName).invoke(teamResultsObject);
                if (value > maxValue) {
                    maxValue = value;
                    keysWithMaxValue.clear();
                    keysWithMaxValue.add(key);
                } else if (value == maxValue) {
                    keysWithMaxValue.add(key);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        List<String> results = new ArrayList<>();
        results.add(maxValue.toString());
        results.addAll(keysWithMaxValue);
        return results;
    }
}
