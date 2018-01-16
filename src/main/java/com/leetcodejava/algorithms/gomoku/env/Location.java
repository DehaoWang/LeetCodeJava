package com.leetcodejava.algorithms.gomoku.env;

/**
 * Created by wangdehao on 18/1/16.
 */
public class Location {
    int x;
    int y;


    public Location(String loc) {
        String[] locs = loc.split(",");
        this.x = Integer.parseInt(locs[0]);
        this.y = Integer.parseInt(locs[1]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
