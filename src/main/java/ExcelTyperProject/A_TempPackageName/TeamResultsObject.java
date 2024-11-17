package ExcelTyperProject.A_TempPackageName;

import java.util.*;

public class TeamResultsObject {
    private String teamName;

    private int points;
    private int homePoints;
    private int awayPoints;

    private int wonGames;
    private int awayWonGames;
    private int homeWonGames;

    private int lostGames;
    private int awayLostGames;
    private int homeLostGames;

    private int drawGames;
    private int homeDrawGames;
    private int awayDrawGames;

    private int allScoredGoals;
    private int allLostGoals;
    private int homeScoredGoals;

    private int homeLostGoals;
    private int awayScoredGoals;
    private int awayLostGoals;

    @Override
    public String toString() {
        return "TeamResultsObject{" +
                "teamName= " + teamName +
                ", wonGames=" + wonGames +
                ", awayWonGames=" + awayWonGames +
                ", homeWonGames=" + homeWonGames +
                ", lostGames=" + lostGames +
                ", awayLostGames=" + awayLostGames +
                ", homeLostGames=" + homeLostGames +
                ", drawGames=" + drawGames +
                ", homeDrawGames=" + homeDrawGames +
                ", awayDrawGames=" + awayDrawGames +
                ", allScoredGoals=" + allScoredGoals +
                ", allLostGoals=" + allLostGoals +
                ", homeScoredGoals=" + homeScoredGoals +
                ", homeLostGoals=" + homeLostGoals +
                ", awayScoredGoals=" + awayScoredGoals +
                ", awayLostGoals=" + awayLostGoals + "}\n";
    }

    //TODO: Do weryifikacji: czy potrzebny sparametryzowany konstruktor (może powinien być pusty, ale z wszystkimi this.Zmienna
    // Natomiast zamiast getterów może powinny być zamiast/dodatkowo settery, tak żeby aktualizować zdobycze punktowe/bramkowe
    public TeamResultsObject(String teamName, int getHomePoints, int getAwayPoints, int getHomeDrawGames, int getAwayDrawGames, int getHomeScoredGoals, int getHomeLostGoals,
                             int getAwayScoredGoals, int getAwayLostGoals, int getHomeWonGames, int getAwayWonGames, int getLostGames, int getHomeGames) {
        // do weryfikacji czy potrzebny jest teamName, czy zamiast tego w mapie/liście będzie się tworzyć oddzielna
        // instancja i będzie do tej kolekcji przypisany dany obiekt
        this.teamName = teamName;
        this.points = getAllPoints();
        this.homePoints = getHomePoints;
        this.awayPoints = getAwayPoints;

        this.wonGames = getHomeWonGames();
        this.awayWonGames = getAwayWonGames;
        this.homeWonGames = getHomeWonGames;


        this.lostGames = getLostGames();
        this.awayLostGames = getLostGames;
        this.homeLostGames = getHomeGames;

        this.awayDrawGames = getAwayDrawGames;
        this.homeDrawGames = getHomeDrawGames;
        this.drawGames = getDrawGames();

        this.allScoredGoals = getAllScoredGoals();
        this.homeScoredGoals = getHomeScoredGoals;
        this.homeLostGoals = getHomeLostGoals;

        this.awayScoredGoals = getAwayScoredGoals;
        this.awayLostGoals = getAwayLostGoals;
        this.allLostGoals = getAllLostGoals();
    }

    //TODO: settery do pól, które i tak zliczają swoją wartość z innych wartości są raczej bez sensu - pewnie można/trzeba je skasować
    // getPoints(), getDrawGames, lostGames, wonGames, allLostGoals, allScoredGoals itd

    //TODO: ten konstruktor raczej będzie do usunięcia
    public TeamResultsObject(String teamName) {
        this.teamName = teamName;
    }

    public TeamResultsObject() {
    }

    ;

    //TODO: tutaj raczej zamiast pętli trzeba chyba podawać w parametrze licznik pętli -> wtedy tutaj wywoływać pojedynczo,
    // a w klasie Everything... wywoływać tą metodę w pętli???
    public Map<String, TeamResultsObject> initilizeTeamResultsMap(String path, int firstIndex, int secondIndex) {
        TeamNamesList teamNamesList = new TeamNamesList();
        Map<String, TeamResultsObject> teamResultsObjectHashMap = new HashMap<>();

        //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX === " + teamNamesList.getTeamNames(path).get(0));

        teamResultsObjectHashMap.put(teamNamesList.getTeamNames(path).get(firstIndex), new TeamResultsObject(teamNamesList.getTeamNames(path).get(firstIndex), 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0));


        teamResultsObjectHashMap.put(teamNamesList.getTeamNames(path).get(secondIndex), new TeamResultsObject(teamNamesList.getTeamNames(path).get(secondIndex), 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0));
        //   System.out.println("Pierwsza drużna z pary: "+teamNamesList.getTeamNames(path).get(firstIndex) +" oraz druga: "+teamNamesList.getTeamNames(path).get(secondIndex));


//        String tempTeamName = "";
//        for (int firstIndex = 0; firstIndex < teamNamesList.getTeamNames(path).size(); firstIndex++) {
//            tempTeamName = teamNamesList.getTeamNames(path).get(firstIndex);
//            //      System.out.println("AAAAAAAAAAAAAAAAAA = "+teamNamesList.getTeamNames(path).get(firstIndex));
//            teamResultsObjectHashMap.put(tempTeamName, new TeamResultsObject(tempTeamName, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
//        }
//        for (String teamNames : teamNamesList.getTeamNames(path)) {
//            teamResultsObjectHashMap.put(teamNames, new TeamResultsObject(teamNames, 0, 0,
//                    0, 0, 0, 0, 0, 0, 0, 0,
//                    0, 0));
//        }

     //   System.out.println("teamResultsObjectHashMap= " + teamResultsObjectHashMap.values());
        return teamResultsObjectHashMap;
    }


    public Map<String, TeamResultsObject> bothTeamResultsObjectUpdate(String path, TeamResultsObject teamResultsObject, String teamHomeName, String teamAwayName, int checkResult,
                                                                      int homeScoredGoals, int homeLostGoals, int awayScoredGoals, int awayLostGoals, int firstIndex, int secondIndex) {
        Map<String, TeamResultsObject> resultsMap = teamResultsObject.initilizeTeamResultsMap(path, firstIndex,secondIndex);

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


    //TODO: wszelkiego rodzaju settery można chyba zmienić tak, żeby zwracały wartość obecną powiększoną o parametr, np:
    //public int setHomePoints(int getHomePoints) {
    //        return this.homePoints = getHomePoints()+getHomePoints;
    //    }

    //Zastanowić się co lepsze - prosta metoda zliczania punktów, czy getter do int points i aktualizowanie zmiennej
    public int getAllPoints() {
        return getHomePoints() + getAwayPoints();
    }

    public int setHomePoints(int getHomePoints) {
        return this.homePoints = getHomePoints;
    }

    public int setAwayPoints(int getAwayPoints) {
        return this.awayPoints = getAwayPoints;
    }

    public int setWonGames() {
        return awayWonGames + homeWonGames;
    }

    public int setLostGames() {
        return homeLostGames + awayLostGames;
    }

    public int setHomeScoredGoals(int getHomeScoredGoals) {
        return this.homeScoredGoals = getHomeScoredGoals;
    }

    public int setHomeLostGoals(int getHomeLostGoals) {
        return this.homeLostGoals = getHomeLostGoals;
    }

    public int setAwayScoredGoals(int getAwayScoredGoals) {
        return this.awayScoredGoals = getAwayScoredGoals() + getAwayScoredGoals;
    }

    public int setAwayLostGoals(int getAwayLostGoals) {
        return this.awayLostGoals = getAwayLostGoals;
    }


    public int getAwayWonGames() {
        return awayWonGames;
    }

    public void setAwayWonGames(int awayWonGames) {
        this.awayWonGames = awayWonGames;
    }

    public int getHomeWonGames() {
        return homeWonGames;
    }

    public void setHomeWonGames(int homeWonGames) {
        this.homeWonGames = homeWonGames;
    }

    public int getAwayLostGames() {
        return awayLostGames;
    }

    public void setAwayLostGames(int awayLostGames) {
        this.awayLostGames = awayLostGames;
    }

    public int getHomeLostGames() {
        return homeLostGames;
    }

    public void setHomeLostGames(int homeLostGames) {
        this.homeLostGames = homeLostGames;
    }

    public int getHomeDrawGames() {
        return homeDrawGames;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPoints() {
        return points;
    }

    public int getAwayPoints() {
        return awayPoints;
    }

    public int getWonGames() {
        return wonGames;
    }

    public int getLostGames() {
        return lostGames;
    }

    public int getDrawGames() {
        return drawGames;
    }

    public int getHomeScoredGoals() {
        return homeScoredGoals;
    }

    public int getHomeLostGoals() {
        return homeLostGoals;
    }

    public int getAwayScoredGoals() {
        return awayScoredGoals;
    }

    public int getAwayLostGoals() {
        return awayLostGoals;
    }

    public int getHomePoints() {
        return homePoints;
    }

    public int setDrawGames() {
        return this.drawGames = getAwayDrawGames() + getHomeDrawGames();
    }

    public void setHomeDrawGames(int homeDrawGames) {
        this.homeDrawGames = homeDrawGames;
    }

    public int getAwayDrawGames() {
        return awayDrawGames;
    }

    public void setAwayDrawGames(int awayDrawGames) {
        this.awayDrawGames = awayDrawGames;
    }

    public int getAllLostGoals() {
        return homeLostGoals + homeLostGoals;
    }

    public int getAllScoredGoals() {
        return homeScoredGoals + awayScoredGoals;
    }

    public int getLostScoredGoals() {
        return homeLostGoals + awayLostGoals;
    }
}
