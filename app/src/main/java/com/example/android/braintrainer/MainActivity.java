package com.example.android.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView sumTextView,timerTextView;
    GridLayout gridLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctTag;
    int score = 0;
    int total = 0;
    String secondsLeft;
    TextView result;
    Button button0,button1,button2,button3,playAgain;
    CountDownTimer countDownTimer;
    TextView scoreView;
    public void start(View view){
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setVisibility(View.INVISIBLE);
    }
    public void playAgain(View view){
        score = 0;
        total = 0;
        timerTextView.setText("30s");
        scoreView.setText("0/0");
        result.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        countDownTimer = new CountDownTimer(30*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondsLeft = String.valueOf((int)millisUntilFinished/1000);
                timerTextView.setText(String.valueOf(secondsLeft) + "s");

            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                result.setText("Your Score is :" + String.valueOf(score) + "/" + String.valueOf(total));
                playAgain.setEnabled(true);
                playAgain.setVisibility(View.VISIBLE);
                gridLayout.setClickable(false);
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        result = (TextView)(findViewById(R.id.scoreResultText));
        result.setText("");
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        scoreView = (TextView)(findViewById(R.id.scoreTextView));
        secondsLeft = "30";
        playAgain = (Button)findViewById(R.id.playAgainButton);
        playAgain.setEnabled(false);
        playAgain.setVisibility(View.INVISIBLE);
        play();
        playAgain(findViewById(R.id.playAgainButton));

    }

    public void play(){
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.button3);
        button3 = (Button)findViewById(R.id.button4);

        int a,b,sum,optionCount,options,rand;
        a=(int)(Math.random() * 20 + 1);
        b=(int)(Math.random() * 20 + 1);
        sum = a+b;
        optionCount = gridLayout.getChildCount();
        options = optionCount;
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        int correctPosition = (int)(Math.random() * 4 +1);
        correctTag = correctPosition;
        for(int i = 1;i<=gridLayout.getChildCount();i++){

            if(i == correctTag)
                answers.add(sum);
            else{
                rand = (int)(Math.random() * 50 + 1);
                while(rand == sum){
                    rand = (int)(Math.random()*50 + 1);
                }
                answers.add(rand);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
        answers.clear();
    }
    public void checkAnswer(View view){
        total++;

        String clickedTag = (String)(((Button)view).getTag());
        if(Integer.parseInt(clickedTag) == correctTag){
            score++;
            result.setText("Correct");
        }
        else{
            result.setText("Incorrect");
        }
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(total));
        play();

    }
}
