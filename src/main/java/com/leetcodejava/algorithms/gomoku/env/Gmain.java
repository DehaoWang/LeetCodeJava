package com.leetcodejava.algorithms.gomoku.env;

/**
 * Created by wangdehao on 18/1/15.
 */
public class Gmain {

    public static void main(String[] args){
        int boardSize = 11;
        int cntOfWin = 3;

        Player player0 = new Player("A");
        Player player1 = new Player("B");
        Game gomoku = new Game(player0, player1, boardSize, cntOfWin);

        Engine engine = new Engine(gomoku);
        engine.getLocationOfStep();

    }
}
