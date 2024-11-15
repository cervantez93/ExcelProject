package ExcelTyperProject.NotesAndProjectDescription;

import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) {
        String text = "Damian-Paweł-Ryszard";
        System.out.println(Arrays.stream(text.split("-")).toList().get(1));
        String temp = Arrays.stream(text.split("-")).toList().get(0);
        System.out.println(temp);

    }
}
