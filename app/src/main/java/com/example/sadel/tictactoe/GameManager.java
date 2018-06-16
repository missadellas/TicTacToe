package com.example.sadel.tictactoe;

import android.widget.Button;

/**
 * Created by sadel on 2018-06-15.
 */

public class GameManager {
    int numWins=0;
    int numLoss=0;
    int gridSize=3;

    public GameManager() {
    }

    public int getNumWins() {
        return numWins;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public int getNumLoss() {
        return numLoss;
    }

    public void setNumLoss(int numLoss) {
        this.numLoss = numLoss;
    }

    public void addWin(){
        numWins++;
    }
    public void addLoss(){
        numLoss++;
    }

    public boolean ThreeInARow(Button[][] board) {
        boolean match = true;
        String[][] buttonText = new String[board.length][board.length];
        for(int i =0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                buttonText[i][j] = board[i][j].getText().toString();
            }
        }


        //check rows
        for (int i = 0; i < board.length; i++) {
            match=true;
            for (int j = 0; j < board.length - 1; j++) {
                if (!buttonText[i][j].equals(buttonText[i][j + 1])|| buttonText[i][j].equals("")){
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        //check columns
        for (int i = 0; i < board.length; i++) {
            match=true;
            for (int j = 0; j < board.length - 1; j++) {
                if (!buttonText[j][i].equals(buttonText[j + 1][i])|| buttonText[j][i].equals("")) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        //check diagnols
        match=true;
        for (int i = 0; i < board.length-1; i++) {
            if(!buttonText[i][i].equals(buttonText[i+1][i+1])|| buttonText[i][i].equals("")){
                match=false;
                break;
            }
        }
        if(match){
            return true;
        }
        match=true;
        //check other diagnol
        for (int i = 0; i < board.length-1; i++) {
            if(!buttonText[i][board.length-i-1].equals(buttonText[i+1][board.length-(i+2)])|| buttonText[i][board.length-i-1].equals("")){
                match=false;
                break;
            }
        }
        if(match){
            return true;
        }
        return false;
    }



}
