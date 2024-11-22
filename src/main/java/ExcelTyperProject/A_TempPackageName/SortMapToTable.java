package ExcelTyperProject.A_TempPackageName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public  class SortMapToTable {

    public static void sortedAllMatchesInTable(Map<String, TeamResultsObject> mapOfResults) {
        List<Map.Entry<String, TeamResultsObject>> sortedList = new ArrayList<>(mapOfResults.entrySet());
        sortedList.sort((entry1, entry2) -> {
            TeamResultsObject teamResultsObject1 = entry1.getValue();
            TeamResultsObject teamResultsObject2 = entry2.getValue();

            // Sortowanie według punktów malejąco
            if (teamResultsObject1.getAllPoints() != teamResultsObject2.getAllPoints()) {
                return Integer.compare(teamResultsObject2.getAllPoints(), teamResultsObject1.getAllPoints());
            }
            // Sortowanie według bramek malejąco
            if (teamResultsObject1.getAllScoredGoals() != teamResultsObject2.getAllScoredGoals()) {
                return Integer.compare(teamResultsObject2.getAllScoredGoals(), teamResultsObject1.getAllScoredGoals());
            }
            // Sortowanie według nazwy rosnąco
            return teamResultsObject1.getTeamName().compareTo(teamResultsObject2.getTeamName());
        });
        int place = 1;
        System.out.print("\n");
        // Wyświetlenie posortowanych wyników
        for (Map.Entry<String, TeamResultsObject> entry : sortedList) {
            System.out.print(place + "." + entry.getKey() + ": " + entry.getValue().toString());
            place++;
        }
    }

    public static void sortedHomeMatchesInTable(Map<String, TeamResultsObject> mapOfResults) {
        List<Map.Entry<String, TeamResultsObject>> sortedList = new ArrayList<>(mapOfResults.entrySet());
        sortedList.sort((entry1, entry2) -> {
            TeamResultsObject teamResultsObject1 = entry1.getValue();
            TeamResultsObject teamResultsObject2 = entry2.getValue();

            // Sortowanie według punktów malejąco
            if (teamResultsObject1.getHomePoints() != teamResultsObject2.getHomePoints()) {
                return Integer.compare(teamResultsObject2.getHomePoints(), teamResultsObject1.getHomePoints());
            }
            // Sortowanie według bramek malejąco
            if (teamResultsObject1.getHomeScoredGoals() != teamResultsObject2.getHomeScoredGoals()) {
                return Integer.compare(teamResultsObject2.getHomeScoredGoals(), teamResultsObject1.getHomeScoredGoals());
            }
            // Sortowanie według nazwy rosnąco
            return teamResultsObject1.getTeamName().compareTo(teamResultsObject2.getTeamName());
        });
        int place = 1;
        System.out.print("\n");
        // Wyświetlenie posortowanych wyników
        for (Map.Entry<String, TeamResultsObject> entry : sortedList) {
            System.out.print(place + "." + entry.getKey() + ": " + entry.getValue().homeMatchesInTableToString());
            place++;
        }
    }

    public static void sortedAwayMatchesInTable(Map<String, TeamResultsObject> mapOfResults) {
        List<Map.Entry<String, TeamResultsObject>> sortedList = new ArrayList<>(mapOfResults.entrySet());
        sortedList.sort((entry1, entry2) -> {
            TeamResultsObject teamResultsObject1 = entry1.getValue();
            TeamResultsObject teamResultsObject2 = entry2.getValue();

            // Sortowanie według punktów malejąco
            if (teamResultsObject1.getAwayPoints() != teamResultsObject2.getAwayPoints()) {
                return Integer.compare(teamResultsObject2.getAwayPoints(), teamResultsObject1.getAwayPoints());
            }
            // Sortowanie według bramek malejąco
            if (teamResultsObject1.getAwayScoredGoals() != teamResultsObject2.getAwayScoredGoals()) {
                return Integer.compare(teamResultsObject2.getAwayScoredGoals(), teamResultsObject1.getAwayScoredGoals());
            }
            // Sortowanie według nazwy rosnąco
            return teamResultsObject1.getTeamName().compareTo(teamResultsObject2.getTeamName());
        });
        int place = 1;
        System.out.print("\n");
        // Wyświetlenie posortowanych wyników
        for (Map.Entry<String, TeamResultsObject> entry : sortedList) {
            System.out.print(place + "." + entry.getKey() + ": " + entry.getValue().awayMatchesInTableToString());
            place++;
        }
    }



}
