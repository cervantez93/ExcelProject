package ExcelTyperProject.A_TempPackageName;

import java.util.*;


public class PutResultsOnMap {

    public Map<String, TeamResultsObject> bothTeamResultsObjectUpdate(String path, TeamResultsObject teamResultsObject, String teamHomeName, String teamAwayName, int checkResult,
                                                                      int homeScoredGoals, int homeLostGoals, int awayScoredGoals, int awayLostGoals, int firstIndex, int secondIndex) {
        Map<String, TeamResultsObject> resultsMap = teamResultsObject.initilizeTeamResultsMap(path, firstIndex, secondIndex);
        System.out.println("Rozmiar mapy = " + resultsMap.size());

        TeamResultsObject teamResultsObjectHome = resultsMap.get(teamHomeName);
        TeamResultsObject teamResultsObjectAway = resultsMap.get(teamAwayName);

        if (checkResult == 1) {
            teamResultsObjectHome.setAwayPoints(teamResultsObjectHome.getHomePoints() + 3);
            teamResultsObjectHome.setAwayPoints(teamResultsObjectHome.getHomeWonGames() + 1);
        } else if (checkResult == 2) {
            teamResultsObjectAway.setAwayPoints(teamResultsObjectAway.getAwayPoints() + 3);
            teamResultsObjectHome.setAwayPoints(teamResultsObjectHome.getAwayWonGames() + 1);
        } else if (checkResult == 0) {
            teamResultsObjectAway.setAwayPoints(teamResultsObjectAway.getHomeDrawGames() + 1);
            teamResultsObjectHome.setAwayPoints(teamResultsObjectHome.getHomeDrawGames() + 1);
        } else if (checkResult == 3) {
            System.out.println("Wystąpił błąd powiązany z obliczeniem typu zwycięstwa (checkResult)");
        }

        teamResultsObjectHome.addHomeScoredGoals(teamResultsObjectHome.getHomeScoredGoals() + homeScoredGoals);
        teamResultsObjectHome.addHomeLostGoals(teamResultsObjectHome.getHomeLostGoals() + homeLostGoals);
        // ^^ wyżej update drużyny gospodarzy

        // niżej update drużyny gości
        teamResultsObjectHome.addAwayLostGoals(teamResultsObjectAway.getAwayLostGoals() + awayLostGoals);
        teamResultsObjectHome.addAwayScoredGoals(teamResultsObjectAway.getAwayScoredGoals() + awayScoredGoals);

        resultsMap.put(teamHomeName, teamResultsObjectHome);
        resultsMap.put(teamAwayName, teamResultsObjectAway);

//        System.out.println("HomeObject = " + teamResultsObjectHome.toString());
//        System.out.println("AwayObject = " + teamResultsObjectAway.toString());

        System.out.println("Jestem w metodzie bothTeamResultsObjectUpdate");
        return resultsMap;
    }


    public static void bothTeamResultsObjectUpdate2(TeamResultsObject teamResultsObjectHome, TeamResultsObject teamResultsObjectAway, int tempCheckResult, int homeTeamGaols, int awayTeamGoals) {

        if (tempCheckResult == 1) {
            teamResultsObjectHome.setHomePoints(teamResultsObjectHome.getHomePoints() + 3);
            teamResultsObjectHome.setHomeWonGames(teamResultsObjectHome.getHomeWonGames() + 1);
            teamResultsObjectAway.setAwayLostGames(teamResultsObjectAway.getAwayLostGames() + 1);

        } else if (tempCheckResult == 2) {
            teamResultsObjectAway.setAwayPoints(teamResultsObjectAway.getAwayPoints() + 3);
            teamResultsObjectAway.setAwayWonGames(teamResultsObjectAway.getAwayWonGames() + 1);
            teamResultsObjectHome.setHomeLostGames(teamResultsObjectHome.getHomeLostGames() + 1);

        } else if (tempCheckResult == 0) {
            teamResultsObjectHome.setHomePoints(teamResultsObjectHome.getHomePoints() + 1);
            teamResultsObjectHome.setHomeDrawGames(teamResultsObjectHome.getHomeDrawGames() + 1);
            teamResultsObjectAway.setAwayPoints(teamResultsObjectAway.getAwayPoints() + 1);
            teamResultsObjectAway.setAwayDrawGames(teamResultsObjectAway.getAwayDrawGames() + 1);

        } else if (tempCheckResult == 3) {
            System.out.println("Wystąpił błąd powiązany z obliczeniem typu zwycięstwa (checkResult)");
        }


        //TODO: to są wartości int z tempResults - przerobione z charów!!!!!!!!
        teamResultsObjectHome.addHomeScoredGoals(homeTeamGaols);
        teamResultsObjectHome.addHomeLostGoals(awayTeamGoals);
        // ^^ wyżej update drużyny gospodarzy

        // niżej update drużyny gości
        teamResultsObjectAway.addAwayLostGoals(homeTeamGaols);
        teamResultsObjectAway.addAwayScoredGoals(awayTeamGoals);

//        System.out.println("HomeObject = " + teamResultsObjectHome.toString());
//        System.out.println("AwayObject = " + teamResultsObjectAway.toString());
//        System.out.println("Jestem w metodzie bothTeamResultsObjectUpdate");
       }
}
