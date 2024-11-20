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

    //    @Override
//    public String toString() {
//        return "TeamResultsObject{" +
//                "teamName= " + teamName +
//                ", wonGames=" + getWonGames() +
//                ", awayWonGames=" + awayWonGames +
//                ", homeWonGames=" + homeWonGames +
//                ", lostGames=" + getLostGames() +
//                ", awayLostGames=" + awayLostGames +
//                ", homeLostGames=" + homeLostGames +
//                ", drawGames=" + getDrawGames() +
//                ", homeDrawGames=" + homeDrawGames +
//                ", awayDrawGames=" + awayDrawGames +
//                ", allScoredGoals=" + getAllScoredGoals() +
//                ", allLostGoals=" + getAllLostGoals() +
//                ", homeScoredGoals=" + homeScoredGoals +
//                ", homeLostGoals=" + homeLostGoals +
//                ", awayScoredGoals=" + awayScoredGoals +
//                ", awayLostGoals=" + awayLostGoals + "}\n";
//    }
    @Override
    public String toString() {
        return "TeamResultsObject{" +
                "teamName= " + teamName +
                ", points= " + getAllPoints() +
                ", wonGames=" + getWonGames() +
                "(H:" + homeWonGames +
                " / A:" + awayWonGames + ") " +
                ", lostGames=" + getLostGames() +
                "(H:" + homeLostGames +
                " / A:" + awayLostGames + ") " +
                ", drawGames=" + getDrawGames() +
                "(H:" + homeDrawGames +
                " / A:" + awayDrawGames + ") " +
                ", allScoredGoals=" + getAllScoredGoals() +
                "(H:" + homeScoredGoals +
                " / A:" + awayScoredGoals + ") " +
                ", allLostGoals=" + getAllLostGoals() +
                "(H:" + homeLostGoals +
                " / A:" + awayLostGoals + ") " + "}\n";
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

        teamResultsObjectHashMap.put(teamNamesList.getTeamNames(path).get(firstIndex), new TeamResultsObject(teamNamesList.getTeamNames(path).get(firstIndex), 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0));

        teamResultsObjectHashMap.put(teamNamesList.getTeamNames(path).get(secondIndex), new TeamResultsObject(teamNamesList.getTeamNames(path).get(secondIndex), 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0));

        // System.out.println("TeamResultsObjectHashMap  =  " + teamResultsObjectHashMap.values());
        return teamResultsObjectHashMap;

    }

    public Map<String, TeamResultsObject> initilizeAllTeamResultsMap() {
        String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer1.txt";
        TeamNamesList teamNamesList = new TeamNamesList();
        Map<String, TeamResultsObject> teamResultsObjectHashMap = new HashMap<>();

        for (int i = 0; i < teamNamesList.getTeamNames(path).size(); i++) {
            teamResultsObjectHashMap.put(teamNamesList.getTeamNames(path).get(i), new TeamResultsObject(teamNamesList.getTeamNames(path).get(i), 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0));
        }
        return teamResultsObjectHashMap;
    }


    public Map<String, TeamResultsObject> bothTeamResultsObjectUpdate(String path, TeamResultsObject teamResultsObject, String teamHomeName, String teamAwayName, int checkResult,
                                                                      int homeScoredGoals, int homeLostGoals, int awayScoredGoals, int awayLostGoals, int firstIndex, int secondIndex) {
        Map<String, TeamResultsObject> resultsMap = teamResultsObject.initilizeTeamResultsMap(path, firstIndex, secondIndex);
        System.out.println("Rozmiar mapy = " + resultsMap.size());

        //TODO: zmienić metodę na PUT - trzeba aktualizować całe obiekty, te pola, które pozostają bez zmian ustawić jako setPole(getPole)

        //przypisanie dwóch obiektów - domowej i wyjazdowej drużyny
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

        System.out.println("2. Jestem w metodzie bothTeamResultsObjectUpdate");
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


//    public int setWonGames() {
//        return awayWonGames + homeWonGames;
//    }
//
//    public int setLostGames() {
//        return homeLostGames + awayLostGames;
//    }

    public int addHomeScoredGoals(int getHomeScoredGoals1) {
        return this.homeScoredGoals = homeScoredGoals + getHomeScoredGoals1;
    }

    public int addHomeLostGoals(int getHomeLostGoals1) {
        return this.homeLostGoals = homeLostGoals + getHomeLostGoals1;
    }

    public int addAwayScoredGoals(int getAwayScoredGoals1) {
        return this.awayScoredGoals = awayScoredGoals + getAwayScoredGoals1;
    }

    public int addAwayLostGoals(int getAwayLostGoals1) {
        return this.awayLostGoals = awayLostGoals + getAwayLostGoals1;
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
        return awayWonGames + homeWonGames;
    }

    public int getLostGames() {
        return awayLostGames + homeLostGames;
    }

    public int getDrawGames() {
        return homeDrawGames + awayDrawGames;
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
