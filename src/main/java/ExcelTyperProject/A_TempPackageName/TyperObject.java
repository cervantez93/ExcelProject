package ExcelTyperProject.A_TempPackageName;

public class TyperObject {

    private final String name;
    private int points;

    private int correctResultsAmount;
    private int exactResultsAmount;
    private int recordAmountOfPointsInOneRound;

    private int recordAmountOfPointsInOneRound_RoundNumber;

    //Konstruktor do stworzenia typera
    public TyperObject(String name, int points, int correctResultsAmount, int exactResultsAmount,
                       int recordAmountOfPointsInOneRound, int recordAmountOfPointsInOneRound_RoundNumber) {
        this.name = name;
        this.points = points;
        this.correctResultsAmount = correctResultsAmount;
        this.exactResultsAmount = exactResultsAmount;
        this.recordAmountOfPointsInOneRound = recordAmountOfPointsInOneRound;
        this.recordAmountOfPointsInOneRound_RoundNumber = recordAmountOfPointsInOneRound_RoundNumber;
    }


    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCorrectResultsAmount() {
        return correctResultsAmount;
    }

    public void setCorrectResultsAmount(int correctResultsAmount) {
        this.correctResultsAmount = correctResultsAmount;
    }

    public int getExactResultsAmount() {
        return exactResultsAmount;
    }

    public void setExactResultsAmount(int exactResultsAmount) {
        this.exactResultsAmount = exactResultsAmount;
    }

    public int getRecordAmountOfPointsInOneRound() {
        return recordAmountOfPointsInOneRound;
    }

    public void setRecordAmountOfPointsInOneRound(int recordAmountOfPointsInOneRound) {
        this.recordAmountOfPointsInOneRound = recordAmountOfPointsInOneRound;
    }

    public int getRecordAmountOfPointsInOneRound_RoundNumber() {
        return recordAmountOfPointsInOneRound_RoundNumber;
    }

    public void setRecordAmountOfPointsInOneRound_RoundNumber(int recordAmountOfPointsInOneRound_RoundNumber) {
        this.recordAmountOfPointsInOneRound_RoundNumber = recordAmountOfPointsInOneRound_RoundNumber;
    }
}
