package tem_pmsd.intentsample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    final static String MY_NAME = "MYNAME";
    final static int MY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button = findViewById(R.id.second_button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(MY_NAME, "JESSY");
        intent.putExtras(bundle);
//        startActivity(intent);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == MY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String name = data.getStringExtra(MY_NAME);
                ((TextView)findViewById(R.id.textView2)).setText(name);
            }else {

            }
        }
    }
}
