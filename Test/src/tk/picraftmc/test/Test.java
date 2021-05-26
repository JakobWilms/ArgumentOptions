package tk.picraftmc.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Test() {
        this.list = new ArrayList<>();
    }
}
