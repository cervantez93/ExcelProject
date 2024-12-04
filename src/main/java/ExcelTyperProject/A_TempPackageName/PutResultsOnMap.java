package ExcelTyperProject.A_TempPackageName;

import java.util.*;


public class PutResultsOnMap {

    public static void bothTeamResultsObjectUpdate(TeamResultsObject teamResultsObjectHome, TeamResultsObject teamResultsObjectAway, int tempCheckResult, int homeTeamGoals, int awayTeamGoals) {

        if (tempCheckResult == 1) {
            teamResultsObjectHome.setHomePoints(teamResultsObjectHome.getHomePoints() + 3);
            teamResultsObjectHome.setHomeWonGames(teamResultsObjectHome.getHomeWonGames() + 1);
            teamResultsObjectAway.setAwayLostGames(teamResultsObjectAway.getAwayLostGames() + 1);

            checkHomeWonRecordResult(teamResultsObjectHome, teamResultsObjectAway, (homeTeamGoals + ":" + awayTeamGoals));
        } else if (tempCheckResult == 2) {
            teamResultsObjectAway.setAwayPoints(teamResultsObjectAway.getAwayPoints() + 3);
            teamResultsObjectAway.setAwayWonGames(teamResultsObjectAway.getAwayWonGames() + 1);
            teamResultsObjectHome.setHomeLostGames(teamResultsObjectHome.getHomeLostGames() + 1);

            // System.out.println("Home goals = " + homeTeamGoals + ", away goals: " + awayTeamGoals + " homeRecord: " + teamResultsObjectHome.getRecordAwayWonResult() + "\n");
            checkAwayWonRecordResult(teamResultsObjectAway, teamResultsObjectHome, (homeTeamGoals + ":" + awayTeamGoals));

        } else if (tempCheckResult == 0) {
            teamResultsObjectHome.setHomePoints(teamResultsObjectHome.getHomePoints() + 1);
            teamResultsObjectHome.setHomeDrawGames(teamResultsObjectHome.getHomeDrawGames() + 1);
            teamResultsObjectAway.setAwayPoints(teamResultsObjectAway.getAwayPoints() + 1);
            teamResultsObjectAway.setAwayDrawGames(teamResultsObjectAway.getAwayDrawGames() + 1);

        } else if (tempCheckResult == 3) {
            System.out.println("Wystąpił błąd powiązany z obliczeniem typu zwycięstwa (checkResult)");
        }

        teamResultsObjectHome.addHomeScoredGoals(homeTeamGoals);
        teamResultsObjectHome.addHomeLostGoals(awayTeamGoals);
        // ^^ wyżej update drużyny gospodarzy

        // niżej update drużyny gości
        teamResultsObjectAway.addAwayLostGoals(homeTeamGoals);
        teamResultsObjectAway.addAwayScoredGoals(awayTeamGoals);

//        System.out.println("HomeObject = " + teamResultsObjectHome.toString());
//        System.out.println("AwayObject = " + teamResultsObjectAway.toString());
//        System.out.println("Jestem w metodzie bothTeamResultsObjectUpdate");
    }

    public static void checkHomeWonRecordResult(TeamResultsObject teamResultsObjectHome, TeamResultsObject teamResultsObjectRival, String recordHomeWonResult) {

        //TODO: usunąć po nadrobionej kolejce Śląska
        if (teamResultsObjectHome.getTeamName().equals("ŚląskWrocław") && teamResultsObjectHome.getRecordHomeWonResult().equals("9:0") && teamResultsObjectHome.getHomeWonGames() > 0) {
            teamResultsObjectHome.setHomeWonGames(teamResultsObjectHome.getHomeWonGames() - 1);
        }

        if (teamResultsObjectHome.getHomeWonGames() > 0) {
            //Rozdzielenie wyniku, który jest stringiem na dwie liczby
            int homeScoredGoals = Integer.valueOf(Arrays.stream(recordHomeWonResult.split(":")).toList().get(0));
            int homeLostGoals = Integer.valueOf(Arrays.stream(recordHomeWonResult.split(":")).toList().get(1));
            //Rozdzielenie wyniku, który jest stringiem na dwie liczby
            int homeRecordScoredGoals = Integer.valueOf(Arrays.stream(teamResultsObjectHome.getRecordHomeWonResult().split(":")).toList().get(0));
            int homeRecordLostGoals = Integer.valueOf(Arrays.stream(teamResultsObjectHome.getRecordHomeWonResult().split(":")).toList().get(1));

            // Sprawdzenie czy różnica bramki w meczu jest większa niż w rekordowym rezultacie
            if (homeScoredGoals > homeRecordScoredGoals && ((homeScoredGoals - homeLostGoals) > (homeRecordScoredGoals - homeRecordLostGoals))) {
                teamResultsObjectHome.setRecordHomeWonResult(homeScoredGoals + ":" + homeLostGoals);
                //System.out.println(teamResultsObjectHome.getTeamName() + ", rekord: " + teamResultsObjectHome.getRecordHomeWonResult());
                // Sprawdzenie czy ilość strzelonych bramek (przy jednakowej różnicy bramek) jest większa niż przy rekordowym rezultacie,
                // jeśli tak - nadal aktualizujemy rekord (czyli np 5:2  jest lepszym rekordem niż 3:0)
            } else if (((homeScoredGoals - homeLostGoals) == (homeRecordScoredGoals - homeRecordLostGoals)) && homeScoredGoals > homeRecordScoredGoals) {
                //System.out.println(teamResultsObjectHome.getTeamName() + ", rekord przed zmianą: " + teamResultsObjectHome.getRecordHomeWonResult());
                teamResultsObjectHome.setRecordHomeWonResult(homeScoredGoals + ":" + homeLostGoals);
                //System.out.println(teamResultsObjectHome.getTeamName() + ", rekord po zmianie: " + teamResultsObjectHome.getRecordHomeWonResult());
            }
            teamResultsObjectHome.setRecordHomeWonRivalName(teamResultsObjectRival.getTeamName());
        }
    }

    public static void checkAwayWonRecordResult(TeamResultsObject teamResultsObjectAway, TeamResultsObject teamResultsObjectRival, String recordAwayWonResult) {
        //System.out.println("team =  " + teamResultsObjectAway.getTeamName() + ", won away games= " + teamResultsObjectAway.getAwayWonGames());
        if (teamResultsObjectAway.getAwayWonGames() == 0) {
            System.out.println("team =  " + teamResultsObjectAway.getTeamName());
        }

        if (teamResultsObjectAway.getAwayWonGames() > 0) {
            //Rozdzielenie wyniku, który jest stringiem na dwie liczby
            int awayScoredGoals = Integer.valueOf(Arrays.stream(recordAwayWonResult.split(":")).toList().get(1));
            int awayLostGoals = Integer.valueOf(Arrays.stream(recordAwayWonResult.split(":")).toList().get(0));
            //Rozdzielenie wyniku, który jest stringiem na dwie liczby
            int awayRecordScoredGoals = Integer.valueOf(Arrays.stream(teamResultsObjectAway.getRecordAwayWonResult().split(":")).toList().get(1));
            int awayRecordLostGoals = Integer.valueOf(Arrays.stream(teamResultsObjectAway.getRecordAwayWonResult().split(":")).toList().get(0));

            // Sprawdzenie czy różnica bramki w meczu jest większa niż w rekordowym rezultacie
            if (awayScoredGoals > awayRecordScoredGoals && (awayScoredGoals - awayLostGoals) > (awayRecordScoredGoals - awayRecordLostGoals)) {
                teamResultsObjectAway.setRecordAwayWonResult(awayLostGoals + ":" + awayScoredGoals);
                //System.out.println(teamResultsObjectAway.getTeamName() + ", rekord: " + teamResultsObjectAway.getRecordAwayWonResult());
                // Sprawdzenie czy ilość strzelonych bramek (przy jednakowej różnicy bramek) jest większa niż przy rekordowym rezultacie,
                // jeśli tak - nadal aktualizujemy rekord (czyli np 5:2  jest lepszym rekordem niż 3:0)
            } else if (((awayScoredGoals - awayLostGoals) == (awayRecordScoredGoals - awayRecordLostGoals)) && awayScoredGoals > awayRecordScoredGoals) {
                //System.out.println(teamResultsObjectAway.getTeamName() + ", rekord przed zmianą: " + teamResultsObjectAway.getRecordAwayWonResult());
                teamResultsObjectAway.setRecordAwayWonResult(awayLostGoals + ":" + awayScoredGoals );
                //System.out.println(teamResultsObjectAway.getTeamName() + ", rekord po zmianie: " + teamResultsObjectAway.getRecordAwayWonResult());
            }
            teamResultsObjectAway.setRecordAwayWonRivalName(teamResultsObjectRival.getTeamName());
        }
    }
}



