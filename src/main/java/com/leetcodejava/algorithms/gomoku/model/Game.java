package com.leetcodejava.algorithms.gomoku.model;

/**
 * Created by wangdehao on 18/1/16.
 */
public class Game {
    private Board board;
    Player player0;
    Player player1;

    int movePlayerId = 0;
    Player movePlayer;
    private int stepNum = 0;
    private int roundNum = 1;
    private boolean onGoing = true;

    public Game(Player player0, Player player1, int boardSize, int cntOfWin) {
        this.player0 = player0;
        this.player1 = player1;
        movePlayer = player0;
        board = new Board();
        board.init(boardSize, cntOfWin);
    }

    public void roundStep(Location location){

        boolean successPut = board.putPiece(location.getX(), location.getY(), movePlayer.getName(), roundNum);
        if(!successPut){
            return;
        }
        // // TODO: 18/1/16 problem 
        if(stepNum % 2 == 0){
            roundNum++;
        }
        board.printBoard();
        validateBoard(player0.getName(), player1.getName());

        // switch player
        movePlayerId = 1 - movePlayerId;
        if(movePlayerId == 0){
            movePlayer = player0;
        }
        else {
            movePlayer = player1;
        }
        stepNum++;
    }

    public String validateBoard(String p1, String p2){
        if(validate(p1)){
            System.out.println("The winner is " + p1 + " !");
            onGoing = false;
        }
        if(validate(p2)){
            System.out.println("The winner is " + p2 + " !");
            onGoing = false;
        }
        return "noBody";
    }

    public boolean validate(String x) {
        if(board.validateDegree0(x)){
            return true;
        }
        if(board.validateDegree45(x)){
            return true;
        }
        if(board.validateDegree90(x)){
            return true;
        }
        if(board.validateDegree135(x)){
            return true;
        }
        return false;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public Player getMovePlayer() {
        return movePlayer;
    }

    public Board getBoard() {
        return board;
    }
}
