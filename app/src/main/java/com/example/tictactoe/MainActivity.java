package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int player=1;
            /*player 1 is red player 2 is yellow*/
    int[] game_state={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winning_state={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    TextView result;
    Button playAgain;
    int counter;
    boolean active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=(TextView) findViewById(R.id.result);
        playAgain=(Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.GONE);
        counter=0;
        active=true;
    }


    public void Tic(View v){
        counter++;
        ImageView s=(ImageView)v;
        int tag=Integer.parseInt(s.getTag().toString());
        if(game_state[tag]==-1){
            s.setTranslationY(-1500);
            if(player==1 && active){
                s.setImageResource(R.drawable.red);
                game_state[tag]=player;
                player=2;
            }else if(player==2 && active){
                s.setImageResource(R.drawable.yellow);
                game_state[tag]=player;
                player=1;
            }
            s.animate().translationYBy(1500).rotation(3600).setDuration(400);
            for(int[] winState:winning_state){
                if(game_state[winState[0]]==game_state[winState[1]] && game_state[winState[1]]== game_state[winState[2]] && game_state[winState[0]]!=-1){
                    if (player == 1) {
                        result.setText("PLayer 2 wins!");
                        playAgain.setVisibility(View.VISIBLE);
                        active=false;
                    }else{
                        result.setText("PLayer 1 wins!");
                        playAgain.setVisibility(View.VISIBLE);
                        active=false;
                    }
                }
            }
            if(counter==9 && active==true){
                playAgain.setVisibility(View.VISIBLE);
                result.setText("It's a tie!");

            }

        }

    }
    public void reset(View v){
        player=1;
        active=true;
        for(int i=0;i<9;i++) {
            game_state[i] = -1;
        }
        playAgain.setVisibility(View.GONE);
        result.setText("");
        GridLayout gridLayout=(GridLayout) findViewById(R.id.grid);
        ImageView img;
        for(int j=0;j<9;j++){
            Log.i("Tag","removed");
            img= (ImageView) gridLayout.getChildAt(j);
            img.setImageDrawable(null);
        }
    }
}