package com.leetcodejava.algorithms.gomoku.ai;

import com.leetcodejava.algorithms.gomoku.model.Board;
import com.leetcodejava.algorithms.gomoku.model.Location;

/**
 * Created by wangdehao on 18/1/16.
 */
public class AI {
    public int sleepTime = 500;

    public Location getLocationBasedOnBoard(Board board) throws InterruptedException {
        return new Location("0,0");
    }
}
