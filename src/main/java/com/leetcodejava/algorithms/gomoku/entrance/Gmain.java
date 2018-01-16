package com.leetcodejava.algorithms.gomoku.entrance;

import com.leetcodejava.algorithms.gomoku.ai.AI;
import com.leetcodejava.algorithms.gomoku.ai.SimpleAI;
import com.leetcodejava.algorithms.gomoku.model.Engine;
import com.leetcodejava.algorithms.gomoku.model.Game;
import com.leetcodejava.algorithms.gomoku.model.Player;

/**
 * Created by wangdehao on 18/1/15.
 */
public class Gmain {

    public static void main(String[] args){
        int boardSize = 11;
        int cntOfWin = 3;

        Player playerA = new Player("A");
        Player playerB = new Player("B");

        AI ai = new SimpleAI();
        Player playerC = new Player("C", ai);

//        Game gomoku = new Game(playerA, player1, boardSize, cntOfWin);
        Game gomoku = new Game(playerA, playerC, boardSize, cntOfWin);

        Engine engine = new Engine(gomoku);
        engine.getLocationOfStep();

    }
}
