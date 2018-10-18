package pmsd2.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText firstNum;
    EditText secondNum;

    Button addBut;
    Button subBut;
    Button mulBut;
    Button divBut;

    TextView resultView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNum = findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);

        addBut = findViewById(R.id.addBut);
        subBut = findViewById(R.id.subBut);
        mulBut = findViewById(R.id.mulBut);
        divBut = findViewById(R.id.divBut);

        resultView = findViewById(R.id.textView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intFirstNum = stringToNum(firstNum);
                int intSecondNum = stringToNum(secondNum);
                int result = intFirstNum + intSecondNum;
                showResult(result);
            }
        };
        addBut.setOnClickListener(listener);
        subBut.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                showResult(stringToNum(firstNum)-stringToNum(secondNum));
            }
            });

        mulBut.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                showResult(stringToNum(firstNum)*stringToNum(secondNum));
                return false;
            }
        });


    }

    public void multiplyNum(View v){
        showResult(stringToNum(firstNum)*stringToNum(secondNum));
    }

    private void showResult(int result){
        resultView.setText(String.valueOf(result));
    }

    private int stringToNum (EditText number){
        String text = number.getText().toString();
        if (TextUtils.isEmpty(text)){
            return 0;
        }
        return Integer.valueOf(text);
    }

}
