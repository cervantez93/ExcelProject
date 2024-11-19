package ExcelTyperProject.NotesAndProjectDescription;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestClass {
    public static void main(String[] args) {


        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(i, 2 * i);
        }
        System.out.println(map.size());
    }
}
