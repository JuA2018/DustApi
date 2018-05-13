package org.techtown.dustapi.data;

import java.util.List;

public class Dust {
    private List<Dustlist> list;

    public Dust(List<Dustlist> list) {
        this.list = list;
    }

    public List<Dustlist> getList() {
        return list;
    }

    public void setList(List<Dustlist> list) {
        this.list = list;
    }
}
