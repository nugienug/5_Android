package pmsd.homeiot_fan;

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

    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;

    final int NOW_SPEED[]={0, 1000, 500, 100};

    GradientDrawable gd = new GradientDrawable();

    boolean isOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Log Activity", "onCREATE");
        setContentView(R.layout.activity_main);

        toggleButton=findViewById(R.id.toggleButton);
        imageView=findViewById(R.id.imageView);
        switchButton=findViewById(R.id.switch1);
        seekBar=findViewById(R.id.seekBar);

        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0,360);
        rotateAnimator.setDuration(50);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(300);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("ToggleChecked", isChecked+"");
                if(isChecked && isOn == false){
//                    rotateFan();
                    seekBar.setProgress(1);
                }else{
//                    stopFan();
                    if (isOn==true) {
                        seekBar.setProgress(0);
                    }
                }
                isOn=isChecked;
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("SwitchButton", isChecked+"");
                if (isChecked) {
                    turnLightON();
                }else{
                    turnLightOFF();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("setOnSeekBar", progress+"");
                rotateFan(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void rotateFan(int index){
        if (index ==0){
            rotateAnimator.end();
            toggleButton.setChecked(false);
            return;
        }
        if(isOn){
            toggleButton.setChecked(true);
        }
        rotateAnimator.setDuration(NOW_SPEED[index]);
        rotateAnimator.start();
    }

    public void stopFan(){
        rotateAnimator.end();
    }

    public void turnLightON(){
        gd.setColors(new int[]{Color.CYAN, Color.TRANSPARENT});
        imageView.setBackground(gd);
    }

    public void turnLightOFF(){
        imageView.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log Activity", "onSTART");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Log Activity", "onRESUME");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Log Activity", "onSTOP");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Log Activity", "onPAUSE");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Log Activity", "onDESTROY");
    }
}
