package com.karamgajjar.dicegame;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3;
    ImageView i1;

    TextView user_score_text,computer_score_text;
    private Thread thread;

    Random random = new Random();

    int user_turn_score=0;
    int computer_score_turn=0;
    int user_overall_score=0;
    int computer_overall_score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        i1 = (ImageView)findViewById(R.id.imageView);
        user_score_text = (TextView)findViewById(R.id.textView);
        computer_score_text = (TextView)findViewById(R.id.textView2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = random.nextInt(6) + 1;
                int r1 = getResources().getIdentifier("dice"+r,"drawable",getApplication().getPackageName());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    i1.setImageDrawable(getDrawable(r1));
                }else
                {
                    i1.setImageResource(r1);
                }

                if(r==1)
                {
                    user_turn_score=0;
                    user_overall_score=0;
                    computerturn();
                }
                else
                {
                    if(user_overall_score>=100)
                    {
                        user_score_text.setText("User Wins");
                        Toast.makeText(MainActivity.this, "User Wins", Toast.LENGTH_SHORT).show();
                    }
                    user_turn_score=r;
                    user_overall_score+=user_turn_score;

                    computer_score_text.setText("Computer Score :" +computer_overall_score);
                    user_score_text.setText("User Score :"+user_overall_score);

                }


            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_overall_score+=user_turn_score;
                user_score_text.setText("User Score :"+user_overall_score);
                user_turn_score=0;
                computerturn();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_turn_score=0;
                computer_score_turn=0;
                user_overall_score=0;
                computer_overall_score=0;

                computer_score_text.setText("Computer Score"+computer_overall_score);
                user_score_text.setText("User Score :"+user_overall_score);
            }
        });

    }
    private void computerturn(){

        new CountDownTimer(5000,1000)
        {
            @Override
            public void onTick(long l) {


                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);

                computer_score_text.setText("Plz wait computer is playing:"+l/1000);
            }

            public void onFinish() {
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);

                int i=0;
                while(i!=20) {



                    int r = random.nextInt(6) + 1;
                    if (r == 1) {

                        computer_score_turn = 0;
                        computer_overall_score=0;
                        b1.setEnabled(true);
                        b2.setEnabled(true);
                        b3.setEnabled(true);
                        break;

                    } else {

                        if(computer_overall_score>=100)
                        {
                            computer_score_text.setText("Computer Wins");
                            Toast.makeText(MainActivity.this, "Computer Wins", Toast.LENGTH_LONG).show();
                        }
                        computer_score_turn=r;
                        computer_overall_score += computer_score_turn;
-                    }

                    computer_score_text.setText("Computer Score :" +computer_overall_score);
                    i++;
                }

            }
        }.start();



    }
}
