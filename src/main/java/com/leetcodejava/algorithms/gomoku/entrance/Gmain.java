package com.leetcodejava.algorithms.gomoku.entrance;

import com.leetcodejava.algorithms.gomoku.ai.AI;
import com.leetcodejava.algorithms.gomoku.ai.AreaStraAI;
import com.leetcodejava.algorithms.gomoku.ai.SimpleAI;
import com.leetcodejava.algorithms.gomoku.model.Engine;
import com.leetcodejava.algorithms.gomoku.model.Game;
import com.leetcodejava.algorithms.gomoku.model.Player;

/**
 * Created by wangdehao on 18/1/15.
 */
public class Gmain {

    public static void main(String[] args) throws InterruptedException {
        int boardSize = 15;
        int cntOfWin = 5;
        boolean printInfo = false;
        int sleepTimeForSingleStep = 0;
        int sleepTimeForSingleGame = 0;
        int numOfGames = 10000;

        int winCntForPlayerA = 0;
        int winCntForPlayerB = 0;

        for(int i = 0; i < numOfGames; i++){
            AI aiA = new SimpleAI(sleepTimeForSingleStep);
            Player playerA = new Player("A", aiA);

            AI aiB = new AreaStraAI(sleepTimeForSingleStep);
            Player playerB = new Player("B", aiB);

            Game gomoku = new Game(playerA, playerB, boardSize, cntOfWin, printInfo);

            Engine engine = new Engine(gomoku);
            engine.getLocationOfStep();
            if(gomoku.getWinnerPlayer() == playerA){
                winCntForPlayerA++;
            }
            if(gomoku.getWinnerPlayer() == playerB){
                winCntForPlayerB++;
            }
            if(printInfo){
                System.out.println("Player A win " + winCntForPlayerA + " times");
                System.out.println("Player B win " + winCntForPlayerB + " times");
            }
            Thread.sleep(sleepTimeForSingleGame);
        }
        System.out.println("Player A win " + winCntForPlayerA + " times");
        System.out.println("Player B win " + winCntForPlayerB + " times");
    }
}
