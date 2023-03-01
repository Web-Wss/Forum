package com.ahnu.domain;

public class BarPostsFz {
    private int bar_id;
    private int yz_user_id;

    public int getBar_id() {
        return bar_id;
    }

    public void setBar_id(int bar_id) {
        this.bar_id = bar_id;
    }

    public int getYz_user_id() {
        return yz_user_id;
    }

    public void setYz_user_id(int yz_user_id) {
        this.yz_user_id = yz_user_id;
    }

    @Override
    public String toString() {
        return "BarPostsFz{" +
                "bar_id=" + bar_id +
                ", yz_user_id=" + yz_user_id +
                '}';
    }
}
