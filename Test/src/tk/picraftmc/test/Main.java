package tk.picraftmc.test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
        test.getList().add("Test2");
        List<String> list2 = test.getList();
        list2.add("1234");
        System.out.println(test.getList());
        System.out.println(list2);
        list2.add("...");
        System.out.println(test.getList());
        System.out.println(list2);
        List<String> list3 = new ArrayList<>(test.getList());
        list3.add("!!!");
        System.out.println(test.getList());
        System.out.println(list3);
    }
}
