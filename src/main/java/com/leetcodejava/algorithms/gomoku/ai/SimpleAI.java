package com.leetcodejava.algorithms.gomoku.ai;

import com.leetcodejava.algorithms.gomoku.model.Board;
import com.leetcodejava.algorithms.gomoku.model.Location;

import java.util.Random;

/**
 * Created by wangdehao on 18/1/16.
 */
public class SimpleAI extends AI{

    @Override
    public Location getLocationBasedOnBoard(Board board) {
        int boardSize = board.getBoardSize();
        Random random = new Random();
        int x = Math.abs(random.nextInt()) % boardSize;
        int y = Math.abs(random.nextInt()) % boardSize;
        return new Location(x+","+y);
    }
}
