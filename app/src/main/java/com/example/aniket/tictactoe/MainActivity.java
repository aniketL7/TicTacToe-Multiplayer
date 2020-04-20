package com.example.aniket.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //  0 : yellow
    //  1 : red
    //  2 : empty

    int[] gameState = {2 , 2 , 2 , 2 , 2 , 2 , 2 , 2 , 2};

    int [][] winningPositions = {{0 , 1 , 2} , {3 , 4 , 5} , {6 , 7 , 8} , {0 , 3 , 6} , {1 , 4 , 7} , {2 , 5 , 8} , {0 , 4 , 8} , {2 , 4 , 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view)
    {
        ImageView counter = (ImageView)view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 1) {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            } else {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            }
            String winner = "";
            for (int[] x : winningPositions) {
                if ((gameState[x[0]] == gameState[x[1]]) && (gameState[x[1]] == gameState[x[2]]) && (gameState[x[0]] != 2)) {
                    gameActive = false;
                    if (gameState[x[0]] == 1)
                        winner = "Red";
                    else
                        winner = "Yellow";
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!!");
                    winnerTextView.setVisibility(View.VISIBLE);
                    Button button = findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view)
    {
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        Button button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView)gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        for (int i=0;i<gameState.length;i++)
        {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
