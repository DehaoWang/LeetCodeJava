package com.leetcodejava.algorithms.gomoku;

/**
 * Created by wangdehao on 18/1/15.
 */
public class Gmain {

    public static void main(String[] args){
        int boardSize = 11;
        int cntOfWin = 3;

        Player player0 = new Player("A");
        Player player1 = new Player("B");
        Game gomokuGame = new Game(player0, player1, boardSize, cntOfWin);
        gomokuGame.roundStep(0,8);
        gomokuGame.roundStep(8,0);
        gomokuGame.roundStep(1,9);
        gomokuGame.roundStep(9,9);
        gomokuGame.roundStep(2,10);
    }
}
