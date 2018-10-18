package com.example.TEM_PMSD.homeiot_lights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.TEM_PMSD.homeiot_lights.fragment.NewItemFragment;
import com.example.TEM_PMSD.homeiot_lights.model.Lights;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    Button delete;
    Button modify;
    Lights lights;
    int position;

    NewItemFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        delete = findViewById(R.id.delete_button);
        modify = findViewById(R.id.modify_button);

        delete.setOnClickListener(this);
        modify.setOnClickListener(this);

        position = getIntent().getExtras().getInt(MainActivity.LIGHT_NUMBER);
        lights = (Lights)getIntent().getExtras().getSerializable(MainActivity.LIGHTS);

        fragment = (NewItemFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.setItemName(lights.getName());
        fragment.setItemResource(lights.getResourceIndex());
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.delete_button: sendDataToActivity(false); break;
            case R.id.modify_button: sendDataToActivity(true); break;
        }
    }

    public void sendDataToActivity(boolean doSave){
        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();

        if (doSave){
            String newName = fragment.getLightsName();
            int newResourceIndex = fragment.getResourcesIndex();
            lights.setName(newName);
            lights.setResourceIndex(newResourceIndex);
            bundle.putSerializable(MainActivity.LIGHTS, lights);
        }else{
            bundle.putSerializable(MainActivity.LIGHTS, null);
        }
        bundle.putInt(MainActivity.LIGHT_NUMBER, position);
        resultIntent.putExtras(bundle);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
