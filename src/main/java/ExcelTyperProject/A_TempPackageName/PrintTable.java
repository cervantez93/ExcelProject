package ExcelTyperProject.A_TempPackageName;

import java.util.Map;
import java.util.Scanner;

//Drukowanie tabeli drużyn w zależności od wybranej opcji - łącznej / domowej / wyjazdowej.
public class PrintTable {

    public static void chooseTableVariant(Map<String, TeamResultsObject> mapOfResults) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz opcję: ");
        System.out.println("1. Tabela łączna.");
        System.out.println("2. Tabela domowa.");
        System.out.println("3. Tabela wyjazdowa.");
        System.out.println("4. Zakończ program");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.print("\nTabela łączna: ");
                printFullTable(mapOfResults);
                break;
            case 2:
                System.out.print("\nTabela domowa: ");
                printHomeTable(mapOfResults);
                break;
            case 3:
                System.out.print("\nTabela wyjazdowa: ");
                printAwayTable(mapOfResults);
                break;
            case 4:
                System.out.print("\nKoniec programu. ");
                break;
        }
    }

    public static void printFullTable(Map<String, TeamResultsObject> mapOfResults) {
        System.out.print("\nTabela łączna: ");
        SortMapToTable.sortedAllMatchesInTable(mapOfResults);

    }

    public static void printHomeTable(Map<String, TeamResultsObject> mapOfResults) {
        System.out.print("\nTabela domowa: ");
        SortMapToTable.sortedHomeMatchesInTable(mapOfResults);

    }

    public static void printAwayTable(Map<String, TeamResultsObject> mapOfResults) {
        System.out.print("\nTabela wyjazdowa: ");
        SortMapToTable.sortedAwayMatchesInTable(mapOfResults);

    }
}
