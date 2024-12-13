package ExcelTyperProject.A_TempPackageName;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//Metody zwracają tutaj najmniejsze wartości dla podanych nazw metod
public class MinValuesInMap {

    public static String minAmountOfWins(Map<String, TeamResultsObject> mapOfResults) {

        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getWonGames");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość wygranych: [" + result.get(0) + "], drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minAmountOfLoses(Map<String, TeamResultsObject> mapOfResults) {

        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getLostGames");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość przegranych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minAmountOfDraws(Map<String, TeamResultsObject> mapOfResults) {

        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getDrawGames");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość remisów: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minScoredGoals(Map<String, TeamResultsObject> mapOfResults) {

        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getAllScoredGoals");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość łącznie strzelonych bramek: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minLostGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getAllLostGoals");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość łącznie straconych bramek: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minHomeLostGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getHomeLostGoals");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość straconych bramek w meczach domowych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minAwayLostGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getAwayLostGoals");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość straconych bramek w meczach wyjazdowych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minHomeScoredGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getHomeScoredGoals");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość strzelonych bramek w meczach domowych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minAwayScoredGoals(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getAwayScoredGoals");

        StringBuilder temp = new StringBuilder("Najmniejsza ilość strzelonych bramek w meczach wyjazdowych: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minGoalDifference(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getGoalDifference");

        StringBuilder temp = new StringBuilder("Najgorszy łączny bilans bramek: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minHomeGoalDifference(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getHomeGoalDifference");

        StringBuilder temp = new StringBuilder("Najgorszy domowy  bilans bramek: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static String minAwayGoalDifference(Map<String, TeamResultsObject> mapOfResults) {
        // Znalezienie maksymalnej wartości i odpowiadających kluczy
        List<String> result = findMinValueAndKeys(mapOfResults, "getAwayGoalDifference");

        StringBuilder temp = new StringBuilder("Najgorszy wyjazdowy bilans bramek:: [" + result.get(0) + "],  drużyny: ");

        for (int i = 1; i < result.size(); i++) {
            temp.append(result.get(i) + ", ");
        }
        temp.replace(temp.length() - 2, temp.length(), ".");
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Metoda zwraca null!");
        }
        return temp.toString();
    }

    public static List<String> findMinValueAndKeys(Map<String, TeamResultsObject> map, String methodName) {
        Integer minValue = Integer.MAX_VALUE;
        List<String> keysWithMinValue = new ArrayList<>();

        for (Map.Entry<String, TeamResultsObject> entry : map.entrySet()) {
            String key = entry.getKey();
            TeamResultsObject teamResultsObject = entry.getValue();

            // Pobierz wartość pola przy użyciu refleksji
            try {
                int value = (int) teamResultsObject.getClass().getMethod(methodName).invoke(teamResultsObject);
                if (value < minValue) {
                    minValue = value;
                    keysWithMinValue.clear();
                    keysWithMinValue.add(key);
                } else if (value == minValue) {
                    keysWithMinValue.add(key);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        List<String> results = new ArrayList<>();
        results.add(minValue.toString());
        results.addAll(keysWithMinValue);
        return results;
    }
}
