package ExcelTyperProject.A_TempPackageName;

import java.util.*;


public class PutResultsOnMap {
    //TODO: trzeba tutaj zainicjalizować mapę z drużynami, gdzie kluczem będą nazwy drużyn, a wartością lista wartości drużyn (ZMIENIĆ NA OBIEKT)

    public Map<String, TeamResultsObject> bothTeamResultsObjectUpdate(String path, TeamResultsObject teamResultsObject, String teamHomeName, String teamAwayName, int checkResult,
                                                                      int homeScoredGoals, int homeLostGoals, int awayScoredGoals, int awayLostGoals, int firstIndex, int secondIndex) {
        Map<String, TeamResultsObject> resultsMap = teamResultsObject.initilizeTeamResultsMap(path, firstIndex, secondIndex);

        //przypisanie dwóch obiektów - domowej i wyjazdowej drużyny
        TeamResultsObject teamResultsObjectHome = resultsMap.get(teamHomeName);
        TeamResultsObject teamResultsObjectAway = resultsMap.get(teamAwayName);
        resultsMap.get(teamResultsObjectAway).setAwayPoints(0);

        if (checkResult == 1) {
            resultsMap.get(teamHomeName).setHomePoints(teamResultsObjectHome.getHomePoints() + 3);
        } else if (checkResult == 2) {
            resultsMap.get(teamAwayName).setAwayPoints(teamResultsObjectAway.getAwayPoints() + 3);
        } else if (checkResult == 0) {
            resultsMap.get(teamHomeName).setHomeDrawGames(teamResultsObjectHome.getHomeDrawGames() + 1);
            resultsMap.get(teamAwayName).setAwayDrawGames(teamResultsObjectAway.getAwayDrawGames() + 1);
        } else if (checkResult == 3) {
            System.out.println("Wystąpił błąd powiązany z obliczeniem typu zwycięstwa (checkResult)");
        }

        resultsMap.get(teamHomeName).setHomeLostGoals(teamResultsObjectHome.getHomeLostGoals() + homeLostGoals);
        resultsMap.get(teamHomeName).setHomeScoredGoals(teamResultsObjectHome.getHomeScoredGoals() + homeScoredGoals);
        // ^^ wyżej update drużyny gospodarzy

        // niżej update drużyny gości
        resultsMap.get(teamAwayName).setAwayLostGoals(teamResultsObjectAway.getAwayLostGoals() + awayLostGoals);
        resultsMap.get(teamAwayName).setAwayScoredGoals(teamResultsObjectAway.getAwayScoredGoals() + awayScoredGoals);


        //TODO: poprzednia wersja, w razie czego odkomentować
//        //TODO: zweryfikować czy metoda uptdatuje wyniki
//        if (checkResult == 1) {
//            resultsMap.get(teamHomeName).setHomePoints(teamResultsObject.getHomePoints() + 3);
//        } else if (checkResult == 2) {
//            resultsMap.get(teamAwayName).setAwayPoints(teamResultsObject.getAwayPoints() + 3);
//        } else if (checkResult == 0) {
//            resultsMap.get(teamHomeName).setHomeDrawGames(teamResultsObject.getHomeDrawGames() + 1);
//            resultsMap.get(teamAwayName).setAwayDrawGames(teamResultsObject.getAwayDrawGames() + 1);
//        } else if (checkResult == 3) {
//            System.out.println("Wystąpił błąd powiązany z obliczeniem typu zwycięstwa (checkResult)");
//        }
//
//        resultsMap.get(teamHomeName).setHomeLostGoals(homeLostGoals);
//        resultsMap.get(teamHomeName).setHomeScoredGoals(homeScoredGoals);
//        // ^^ wyżej update drużyny gospodarzy
//
//        // niżej update drużyny gości
//        resultsMap.get(teamAwayName).setAwayLostGoals(+awayLostGoals);
//        resultsMap.get(teamAwayName).setAwayScoredGoals(+awayScoredGoals);


        //trzeba uzupełnić tak, żeby aktualizować mapę z wynikami każdej drużyny
        System.out.println("2. Jestem w metodzie bothTeamResultsObjectUpdate");
        return resultsMap;
    }
}
