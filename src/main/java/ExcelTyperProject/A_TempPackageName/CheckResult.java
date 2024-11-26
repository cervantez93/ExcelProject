package ExcelTyperProject.A_TempPackageName;

public class CheckResult {

    public static int checkResultType(int homeTeamGoals, int awayTeamGoals) {
        int result = 3;
        if (homeTeamGoals == awayTeamGoals) {
            result = 0;
        } else if (homeTeamGoals > awayTeamGoals) {
            result = 1;
        }
        if (homeTeamGoals < awayTeamGoals) {
            result = 2;
        }
        return result;
    }
}
