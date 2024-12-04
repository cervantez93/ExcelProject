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

    private String recordHomeWonResult;
    private String recordAwayWonResult;
    private String recordHomeWonRivalName = "";
    private String recordAwayWonRivalName = "";

    public String getRecordHomeWonRivalName() {
        return recordHomeWonRivalName;
    }

    public void setRecordHomeWonRivalName(String recordHomeWonRivalName) {
        this.recordHomeWonRivalName = recordHomeWonRivalName;
    }

    public String getRecordAwayWonRivalName() {
        return recordAwayWonRivalName;
    }

    public void setRecordAwayWonRivalName(String recordAwayWonRivalName) {
        this.recordAwayWonRivalName = recordAwayWonRivalName;
    }

    public String getRecordHomeWonResult() {
        return recordHomeWonResult;
    }

    public void setRecordHomeWonResult(String recordHomeWonResult) {
        this.recordHomeWonResult = recordHomeWonResult;
    }

    public String getRecordAwayWonResult() {
        return recordAwayWonResult;
    }

    public void setRecordAwayWonResult(String recordAwayWonResult) {
        this.recordAwayWonResult = recordAwayWonResult;
    }

    public int getGoalDifference() {
        return getAllScoredGoals() - getAllLostGoals();
    }

    public int getAwayGoalDifference() {
        return awayScoredGoals - awayLostGoals;
    }

    public int getHomeGoalDifference() {
        return homeScoredGoals - homeLostGoals;
    }

    @Override
    public String toString() {
        return
//                "Tabela łączna: \n" +
                ", points= " + getAllPoints() +
                        ", matches=" + (getWonGames() + getLostGames() + getDrawGames()) +
                        ", wonGames=" + getWonGames() +
                        "(H:" + homeWonGames +
                        " / A:" + awayWonGames + ")" +
                        ", lostGames=" + getLostGames() +
                        "(H:" + homeLostGames +
                        " / A:" + awayLostGames + ")" +
                        ", drawGames=" + getDrawGames() +
                        "(H:" + homeDrawGames +
                        " / A:" + awayDrawGames + ")" +
                        ", allScoredGoals=" + getAllScoredGoals() +
                        "(H:" + homeScoredGoals +
                        " / A:" + awayScoredGoals + ")" +
                        ", allLostGoals=" + getAllLostGoals() +
                        "(H:" + homeLostGoals +
                        " / A:" + awayLostGoals + ")+" +
                        ", goalDifference=" + getGoalDifference() + "}" +
                        ", homeRecordWonResult = [" + getRecordHomeWonResult() + "]" +
                        ", awayRecordWonResult= [" + getRecordAwayWonResult() + "]}\n";
    }


    public String homeMatchesInTableToString() {
        return
//                "Tabela domowa: \n" +
                "points= " + getHomePoints() +
                        ", matches=" + (getHomeWonGames() + getHomeLostGames() + getHomeDrawGames()) +
                        ", wonGames=" + getHomeWonGames() +
                        ", lostGames=" + getLostGames() +
                        ", drawGames=" + getHomeDrawGames() +
                        ", scoredGoals=" + homeScoredGoals +
                        ", lostGoals=" + homeLostGoals +
                        ", goalDifference=" + getHomeGoalDifference() +
                        ", homeRecordWonResult = [" + getRecordHomeWonResult() + "]" + "}\n";
    }

    public String awayMatchesInTableToString() {
        return
//                "Tabela wyjazdowa: \n" +
                "points= " + getAwayPoints() +
                        ", teamName= " + getTeamName() +
                        ", matches=" + (getAwayWonGames() + getAwayLostGames() + getAwayDrawGames()) +
                        ", wonGames=" + getAwayWonGames() +
                        ", lostGames=" + getAwayLostGames() +
                        ", drawGames=" + getAwayDrawGames() +
                        ", scoredGoals=" + awayScoredGoals +
                        ", lostGoals=" + awayLostGoals +
                        ", goalDifference=" + getAwayGoalDifference() +
                        ", awayRecordWonResult= [" + getRecordAwayWonResult() + "}\n";
    }

    public TeamResultsObject(String teamName, int getHomePoints, int getAwayPoints, int getHomeDrawGames, int getAwayDrawGames, int getHomeScoredGoals, int getHomeLostGoals,
                             int getAwayScoredGoals, int getAwayLostGoals, int getHomeWonGames, int getAwayWonGames, int getLostGames, int getHomeGames, String recordHomeWonResult, String recordAwayWonResult) {
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

        this.recordHomeWonResult = recordHomeWonResult;
        this.recordAwayWonResult = recordAwayWonResult;
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


    public Map<String, TeamResultsObject> initilizeTeamResultsMap(String path, int firstIndex, int secondIndex) {
        TeamNamesList teamNamesList = new TeamNamesList();
        Map<String, TeamResultsObject> teamResultsObjectHashMap = new HashMap<>();

        teamResultsObjectHashMap.put(teamNamesList.getTeamNames(path).get(firstIndex), new TeamResultsObject(teamNamesList.getTeamNames(path).get(firstIndex), 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, "0:0", "0:0"));

        teamResultsObjectHashMap.put(teamNamesList.getTeamNames(path).get(secondIndex), new TeamResultsObject(teamNamesList.getTeamNames(path).get(secondIndex), 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, "0:0", "0:0"));

        return teamResultsObjectHashMap;
    }

    public Map<String, TeamResultsObject> initilizeAllTeamResultsMap() {
        String path = "src/main/java/ExcelTyperProject/AllRoundsFiles/Typer1.txt";
        TeamNamesList teamNamesList = new TeamNamesList();
        Map<String, TeamResultsObject> teamResultsObjectHashMap = new HashMap<>();

        for (int i = 0; i < teamNamesList.getTeamNames(path).size(); i++) {
            teamResultsObjectHashMap.put(teamNamesList.getTeamNames(path).get(i), new TeamResultsObject(teamNamesList.getTeamNames(path).get(i), 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, "0:0", "0:0"));
        }
        return teamResultsObjectHashMap;
    }


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
        //teamName.split("(?=\\p{Upper})"); //TODO: pokombinować gdzie zmienić sposób wyświetlania nazwy drużyny - dodać spację!
        return teamName;
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
        return homeLostGoals + awayLostGoals;
    }

    public int getAllScoredGoals() {
        return homeScoredGoals + awayScoredGoals;
    }

    public int getLostScoredGoals() {
        return homeLostGoals + awayLostGoals;
    }
}
