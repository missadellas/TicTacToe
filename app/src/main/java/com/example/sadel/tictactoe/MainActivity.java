package com.example.sadel.tictactoe;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button[][] board = new Button[3][3];
    GameManager gm = new GameManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiate buttons
        for(int i = 0; i<board.length;i++){
            for (int j = 0; j < board.length; j++) {
                String buttonID = "button"+i+j;
                int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
                board[i][j] = findViewById(resourceID);
                board[i][j].setOnClickListener(this);
                Log.d("Sam", ""+board.length);

            }
        }
    }
    static int turnCount=0;

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if(turnCount%2==0) {
            button.setText("X");
            button.setTextColor(0xffcc0000);
            if(gm.ThreeInARow(board)){
                Log.d("Sam", "X WINS");
                xWins();
            }
        }
        else{
            button.setText("O");
            button.setTextColor(0xFF2739AD);

            if(gm.ThreeInARow(board)){
                Log.d("Sam", "O WINS");
            }
        }
        button.setEnabled(false);
        turnCount++;
    }


    public void xWins(){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("X Wins!!!");
        dlgAlert.setTitle("Winner!");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();


    }


}
