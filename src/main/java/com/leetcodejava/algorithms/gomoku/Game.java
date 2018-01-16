package com.leetcodejava.algorithms.gomoku;

/**
 * Created by wangdehao on 18/1/16.
 */
public class Game {
    private String[][] board;
    private final String emptyLocation = "+ ";
    Player playerProfile0;
    Player playerProfile1;
    int cntOfWin = 5;
    int boardSize = 11;

    int movePlayerId = 0;
    private int stepNum = 0;
    private int roundNum = 0;

    public Game(Player playerProfile0, Player playerProfile1, int boardSize, int cntOfWin) {
        this.playerProfile0 = playerProfile0;
        this.playerProfile1 = playerProfile1;
        this.cntOfWin = cntOfWin;
        this.boardSize = boardSize;

        initBoard();
    }

    private void initBoard() {
        board = new String[boardSize][boardSize];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = "+ ";
            }
        }
    }

    public void roundStep(int x, int y){
        if(stepNum % 2 == 0){
            roundNum++;
        }
        if(board[x][y].equals(emptyLocation)){
            if(movePlayerId == 0){
                board[x][y] = playerProfile0.getName() + roundNum;
            }
            else {
                board[x][y] = playerProfile1.getName() + roundNum;
            }
        }
        else {
            System.out.println("The location has an PIECE !");
            System.exit(1);
        }
        printBoard();
        validateBoard(playerProfile0.getName(), playerProfile1.getName());
        movePlayerId = 1 - movePlayerId;
        stepNum++;
    }

    public void printBoard(){
        System.out.println("==========================\nPrinting Current Board\n==========================");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public String validateBoard(String p1, String p2){
        if(validate(p1)){
            System.out.println("The winner is " + p1 + " !");
        }
        if(validate(p2)){
            System.out.println("The winner is " + p2 + " !");
        }
        return "noBody";
    }

    private boolean validate(String x) {
        if(validateDegree0(x)){
            return true;
        }
        if(validateDegree45(x)){
            return true;
        }
        if(validateDegree90(x)){
            return true;
        }
        if(validateDegree135(x)){
            return true;
        }
        return false;
    }

    private boolean validateDegree45(String x) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(i * j > 0){
                    continue;
                }
                int cnt = 0;
                for(int k = 0; k < board.length-i-j; k++){
                    if(board[i+k][j+k].charAt(0) == x.charAt(0)){
                        cnt++;
                    }
                    else {
                        cnt = 0;
                    }
                    if(cnt == cntOfWin){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean validateDegree135(String x) {
        // j = 0
        for(int i = 0; i < board.length; i++){
            int cnt = 0;
            for(int k = 0; k <= i; k++){
                if(board[i-k][k].charAt(0) == x.charAt(0)){
                    cnt++;
                }
                else {
                    cnt = 0;
                }
                if(cnt == cntOfWin){
                    return true;
                }
            }
        }

        // i = board.length-1
        for(int j = 0; j < board[0].length; j++){
            int cnt = 0;
            for(int k = 0; k < board.length-j; k++){
                if(board[board.length-1-k][j+k].charAt(0) == x.charAt(0)){
                    cnt++;
                }
                else {
                    cnt = 0;
                }
                if(cnt == cntOfWin){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean validateDegree0(String x){
        for(int i = 0; i < board.length; i++){
            int cnt = 0;
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j].charAt(0) == x.charAt(0)){
                    cnt++;
                }else {
                    cnt = 0;
                }
                if(cnt == cntOfWin){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validateDegree90(String x){
        for(int j = 0; j < board[0].length; j++){
            int cnt = 0;
            for(int i = 0; i < board.length; i++){
                if(board[i][j].charAt(0) == x.charAt(0)){
                    cnt++;
                }else {
                    cnt = 0;
                }
                if(cnt == cntOfWin){
                    return true;
                }
            }
        }
        return false;
    }
}
