package ExcelTyperProject.A_TempPackageName;

public class CheckResult {

    //Ustalenie rodzaju wyniku
    public static int checkResultType(int homeTeamGoals, int awayTeamGoals) {
        //Defaultowo ustawiam na 3, żeby w razie czego, w innej metodzie wyrzucić błąd
        int result = 3;
        if (homeTeamGoals == awayTeamGoals) {
            result = 0; // remis
        } else if (homeTeamGoals > awayTeamGoals) {
            result = 1; // wygrana gospodarzy
        }
        if (homeTeamGoals < awayTeamGoals) {
            result = 2; // wygrana gości
        }
        return result;
    }
}
