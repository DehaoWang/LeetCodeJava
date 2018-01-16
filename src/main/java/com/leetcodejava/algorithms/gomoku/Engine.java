package com.leetcodejava.algorithms.gomoku;

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
            System.out.println("please input the piece location for player: "+gomokuGame.getMovePlayerName());
            String loc = scanner.next();
            Location location = new Location(loc);
            gomokuGame.roundStep(location.getX(), location.getY());
        }
    }
}
