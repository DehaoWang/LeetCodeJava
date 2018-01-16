package com.leetcodejava.algorithms.gomoku.model;

import com.leetcodejava.algorithms.gomoku.model.Game;
import com.leetcodejava.algorithms.gomoku.model.Location;

import java.util.Scanner;

/**
 * Created by wangdehao on 18/1/16.
 */
public class Engine {
    Game gomokuGame;

    public Engine(Game gomokuGame) {
        this.gomokuGame = gomokuGame;
    }

    public void getLocationOfStep(){
        Scanner scanner = new Scanner(System.in);
        while(gomokuGame.isOnGoing()){
            Player movePlayer = gomokuGame.getMovePlayer();
            System.out.println("please input the piece location for player: "+movePlayer.getName());
            if(movePlayer.hasAi()){
                Location location = movePlayer.getAi().getLocationBasedOnBoard(gomokuGame.getBoard());
                gomokuGame.roundStep(location);
            }
            String loc = scanner.next();
            Location location = new Location(loc);
            gomokuGame.roundStep(location);
        }
    }
}
