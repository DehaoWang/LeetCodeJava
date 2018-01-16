package com.leetcodejava.algorithms.gomoku.model;

/**
 * Created by wangdehao on 18/1/16.
 */
public class Board {
    private final String emptyLocation = "+ ";
    private String[][] board;
    private int boardSize;
    private int cntOfWin;

    public boolean putPiece(int x, int y, String movePlayerName, int roundNum){
        if(board[x][y].equals(emptyLocation)){
            board[x][y] = movePlayerName + roundNum;
            return true;
        }
        else {
            System.out.println("The location has an PIECE !");
            return false;
        }
    }

    public void init(int boardSize, int cntOfWin) {
        this.boardSize = boardSize;
        board = new String[boardSize][boardSize];
        this.cntOfWin = cntOfWin;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = "+ ";
            }
        }
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

    public int getBoardSize() {
        return boardSize;
    }

    public int getCntOfWin() {
        return cntOfWin;
    }

    public boolean validateDegree45(String x) {
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

    public boolean validateDegree135(String x) {
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
