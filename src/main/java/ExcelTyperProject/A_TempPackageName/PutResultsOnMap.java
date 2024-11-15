package ExcelTyperProject.A_TempPackageName;

import java.util.*;


public class PutResultsOnMap {
    //TODO: trzeba tutaj zainicjalizować mapę z drużynami, gdzie kluczem będą nazwy drużyn, a wartością lista wartości drużyn (ZMIENIĆ NA OBIEKT)

    public  Map<String, TeamResultsObject> bothTeamResultsObjectUpdate(String path, TeamResultsObject teamResultsObject, String teamHomeName, String teamAwayName, int checkResult,
                                                                             int homeScoredGoals, int homeLostGoals, int awayScoredGoals, int awayLostGoals, int loopCounter) {
        Map<String, TeamResultsObject> resultsMap = teamResultsObject.initilizeTeamResultsMap(path, loopCounter);

        //TODO: zweryfikować czy metoda uptdatuje wyniki
        if (checkResult == 1) {
            resultsMap.get(teamHomeName).setHomePoints(teamResultsObject.getHomePoints() + 3);
        } else if (checkResult == 2) {
            resultsMap.get(teamAwayName).setAwayPoints(teamResultsObject.getAwayPoints() + 3);
        } else if (checkResult == 0) {
            resultsMap.get(teamHomeName).setHomeDrawGames(teamResultsObject.getHomeDrawGames() + 1);
            resultsMap.get(teamAwayName).setAwayDrawGames(teamResultsObject.getAwayDrawGames() + 1);
        } else if (checkResult == 3) {
            System.out.println("Wystąpił błąd powiązany z obliczeniem typu zwycięstwa (checkResult)");
        }

        resultsMap.get(teamHomeName).setHomeLostGoals(homeLostGoals);
        resultsMap.get(teamHomeName).setHomeScoredGoals(homeScoredGoals);
        // ^^ wyżej update drużyny gospodarzy

        // niżej update drużyny gości
        resultsMap.get(teamAwayName).setAwayLostGoals(+awayLostGoals);
        resultsMap.get(teamAwayName).setAwayScoredGoals(+awayScoredGoals);

        //trzeba uzupełnić tak, żeby aktualizować mapę z wynikami każdej drużyny
        return resultsMap;
    }
}
