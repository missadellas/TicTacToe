package com.example.sadel.tictactoe;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button[][] board = new Button[3][3];
    GameManager gm = new GameManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView p1 = findViewById(R.id.player1);
        TextView p2 = findViewById(R.id.player2);
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
        if(gm.isP1Turn()){
            p1.setTextColor(0xffcc0000);
        }else{
            p2.setTextColor(0xFF2739AD);
        }
        Button newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newGame();

            }
        });


    }


    private void newGame() {
        TextView p1 = findViewById(R.id.player1);
        TextView p2 = findViewById(R.id.player2);
        p1.setText("Player1: 0");
        p2.setText("Player2: 0");
        gm.rematch(board);
        gm = new GameManager();
    }


    //play the game
    @Override
    public void onClick(View v) {
        TextView p1 = findViewById(R.id.player1);
        TextView p2 = findViewById(R.id.player2);
        p2.setTextColor(0xffffffff);
        p1.setTextColor(0xffffffff);

        Button button = (Button) v;
        button.setEnabled(false);
        //player1's turn
        if(gm.isP1Turn()) {
            button.setText("X");
            button.setTextColor(0xffcc0000);
            p2.setTextColor(0xFF2739AD);

            //check if game is over (Xwins)
            gm.setP1Turn(false);
            if(gm.ThreeInARow(board)){
                xWins();
            }
        }
        //Player 2's turn
        else if (!gm.isP1Turn()){
            button.setText("O");
            button.setTextColor(0xFF2739AD);
            p1.setTextColor(0xffcc0000);
            gm.setP1Turn(true);
    //check for gameover (O wins)
            if(gm.ThreeInARow(board)) {
                oWins();
            }
        }
        if(gm.getTurncount()==8) {
            draw();
        }

        gm.incrementTurn();

    }
    Toast toast;
    public void  xWins() {
        toast= Toast.makeText(getApplicationContext(), "Player1 Wins!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 390);
        toast.show();
        TextView p1 = findViewById(R.id.player1);
        gm.addWin();
        p1.setText("Player1: "+gm.getNumWins());
        gm.rematch(board);

    }
    public void oWins(){
        TextView p2 = findViewById(R.id.player2);
        toast= Toast.makeText(getApplicationContext(), "Player2 Wins!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 350);
        toast.show();
        gm.addLoss();
        p2.setText("Player2: "+gm.getNumLoss());
        gm.rematch(board);


    }
    public void draw(){
        toast= Toast.makeText(getApplicationContext(), "Tie!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 350);
        toast.show();
        gm.rematch(board);

    }
/*
    @Override
    protected void onSavedInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);



    }
*/
}
