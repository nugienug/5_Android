package tem_pmsd.intentsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String name = getIntent().getExtras().getString(SecondActivity.MY_NAME);
        text = findViewById(R.id.editText);
        text.setText(name);

        Button button = findViewById(R.id.third_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putString(SecondActivity.MY_NAME, text.getText().toString());
                returnIntent.putExtras(bundle);
                setResult(ThirdActivity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
