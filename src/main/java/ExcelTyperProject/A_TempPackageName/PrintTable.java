package ExcelTyperProject.A_TempPackageName;

import java.util.Map;
import java.util.Scanner;

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
                SortMapToTable.sortedAllMatchesInTable(mapOfResults);
                break;
            case 2:
                System.out.print("\nTabela domowa: ");
                SortMapToTable.sortedHomeMatchesInTable(mapOfResults);
                break;
            case 3:
                System.out.print("\nTabela wyjazdowa: ");
                SortMapToTable.sortedAwayMatchesInTable(mapOfResults);
                break;
            case 4:
                System.out.print("\nTabela wyjazdowa: ");
                SortMapToTable.sortedAwayMatchesInTable(mapOfResults);
                break;
        }
    }
}
