package com.leetcodejava.algorithms.gomoku;

/**
 * Created by wangdehao on 18/1/16.
 */
public class Player {
    private String name = "";

    public Player(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
