package com.example.android.homeiot_fan;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggeButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    final int NOW_SPEED[] = {0, 5000, 3000, 1000};

    GradientDrawable gd = new GradientDrawable();

    boolean isOn=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Log:Activity", "onCreate:" );
        Log.d("Log:Activity", "onCreate:savedInstanceState" +savedInstanceState);

        setContentView(R.layout.activity_main);

        toggeButton=findViewById(R.id.toggleButton);
        imageView=findViewById(R.id.imageView);
        switchButton=findViewById(R.id.switch1);
        seekBar = findViewById(R.id.seekBar);

        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        //gd.setCornerRadius(330);
        gd.setGradientRadius(330);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                rotateFan(progress);




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        toggeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("ToggleCheckd",isChecked+"");
                if(isChecked){ //true
                    seekBar.setProgress(1);
                }else{ //false
                     seekBar.setProgress(0);
                }
                isOn=isChecked; //true
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("switchButton", isChecked+"");
                if(isChecked){
                    turnLightsON();
                }else{
                    turnLightsOff();
                }
            }
        });
    }
    public void rotateFan(int index){
        if(index==0){
            rotateAnimator.end();
            toggeButton.setChecked(false);
            return;
        }

        if(!isOn){   //isOn == false
           toggeButton.setChecked(true);
        }
        rotateAnimator.setDuration(NOW_SPEED[index]);
        rotateAnimator.start();
    }

    public void turnLightsON(){
        gd.setColors(new int[]{  Color.YELLOW , Color.TRANSPARENT });
        imageView.setBackground(gd);
    }

    public void turnLightsOff(){
        //gd.setColors(new int[]{Color.RED, Color.TRANSPARENT});
        imageView.setBackgroundColor(Color.TRANSPARENT);
    }


    public void rotateFan(){ //toggle : true
        rotateAnimator.start();
    }

    public void stopFan(){ //toggle : false
        rotateAnimator.end();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log:Activity", "onStart:" );
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Log:Activity", "onResume:" );
    }

    @Override
    protected void onPause() {
        Log.d("Log:Activity", "onPause:" );
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("Log:Activity", "onStop:" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Log:Activity", "onDestroy:" );
        super.onDestroy();
    }
}
