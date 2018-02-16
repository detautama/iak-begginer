package com.example.detautama.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nilai, timer, bio;
    private Button btn1, btn2, start, share;
    private LinearLayout layout;
    Integer score = 0;
    Boolean bigSmall = false;
    Context context = this;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nilai = (TextView) findViewById(R.id.score);
        timer = (TextView) findViewById(R.id.timer);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        bio = (TextView) findViewById(R.id.bio);
        start= (Button) findViewById(R.id.start);
        share= (Button) findViewById(R.id.share);
        layout = (LinearLayout) findViewById(R.id.layout);

        btn1.setEnabled(false);
        btn2.setEnabled(false);
        share.setVisibility(View.GONE);

    }

    public void click(View v) {
        Vibrator c = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        c.vibrate(20);
        score = score + 1;
        nilai.setText(score.toString());

        bigSmall = !bigSmall;

        if(bigSmall){
            nilai.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }else{
            nilai.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        }

        if ( score < 50) {
            layout.setBackgroundColor(Color.parseColor("#00fc21"));
        }else if(score < 100){
            layout.setBackgroundColor(Color.parseColor("#faff00"));
        }else if(score < 150){
            layout.setBackgroundColor(Color.parseColor("#ff0000"));
        }else if(score < 200){
            layout.setBackgroundColor(Color.parseColor("#2e00ff"));
            nilai.setTextColor(Color.parseColor("#ffffff"));
            timer.setTextColor(Color.parseColor("#ffffff"));
        }else if(score < 250){
            layout.setBackgroundResource(R.drawable.doit);
        }else if(score < 300){
            layout.setBackgroundResource(R.drawable.jackiechanwhut);
        }else if(score < 350){
            layout.setBackgroundResource(R.drawable.successkid);
        }else if(score < 400){
            layout.setBackgroundResource(R.drawable.impsibru);
        }else if(score < 450){
            layout.setBackgroundResource(R.drawable.omae);
        }
    }

    public void share(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "My fingers can clicks "+ score +" times in 30s");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void start(View v) {
        mp = MediaPlayer.create(context, R.raw.bs);
        mp.start();
        bio.setVisibility(View.GONE);
        start.setVisibility(View.GONE);
        share.setVisibility(View.GONE);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        score = 0;
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done! " + score/30 + "touch/s");
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                start.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                mp.stop();
            }
        }.start();
    }
}
