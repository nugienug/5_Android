package com.example.TEM_PMSD.homeiot_lights;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TEM_PMSD.homeiot_lights.adapter.LightsRecycleAdapter;
import com.example.TEM_PMSD.homeiot_lights.dialog.NewDialog;
import com.example.TEM_PMSD.homeiot_lights.model.Lights;
import com.example.TEM_PMSD.homeiot_lights.model.LightsList;

public class MainActivity extends AppCompatActivity implements NewDialog.NoticeDialogListener{
    public static final String LIGHT_NUMBER = "LIGHT_NUMBER";
    public static final String LIGHTS = "LIGHTS";
    public static final int MY_REQUEST_CODE = 100;

    FloatingActionButton fab;
    LightsList lightsList;
    RecyclerView mRecyclerView;
    LightsRecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightsList = new LightsList();
//        lightsList.addLights(new Lights("test", 1));

//        for (int i = 0; i < 10; i++){
//            Lights lights = new Lights("text", 1);
//            lightsList.addLights(lights);
//        }


//        for (int i = 0; i < 10; i++){
//            Lights lights;
//            if (i%2 ==0) {
//                lights = new Lights("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",1);
//            }else{
//                lights = new Lights("text2222", 2);
//            }
//            lightsList.addLights(lights);
//        }

        //"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."


        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewDialog dialog = new NewDialog();
                dialog.show(getSupportFragmentManager(), "Hello");

                ///////////////// DIALOG STYLE
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("new Dialog");
//                builder.setMessage("Hello Dialog");
//                builder.create().show();

                ///////////////// TOAST STYLE
//                Toast.makeText(getApplicationContext(),"Hello Toast", Toast.LENGTH_SHORT).show();

                ///////////////// SNACKBAR STYLE
//                Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Hello snackBar", Snackbar.LENGTH_SHORT);
//                snackbar.setAction("Bye SnackBar", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getApplicationContext(),"Hello SnackBar", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                snackbar.show();
            }
        });

        recycleAdapter = new LightsRecycleAdapter(this, lightsList);
        recycleAdapter.setOnItemClickListener(new LightsRecycleAdapter.LightsItemClickListerner() {

            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(LIGHT_NUMBER, position);
                bundle.putSerializable(LIGHTS, lightsList.getLights(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, MY_REQUEST_CODE);
//                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Hello"+position, Toast.LENGTH_SHORT).show();
            }
        });
        lightsList.registerAdapter(recycleAdapter);

        mRecyclerView = findViewById(R.id.lights_list);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(recycleAdapter);

        lightsList.getLightsList();
    }

    @Override
    public void OnDialogPositiveClick(DialogFragment fragment, Lights lights) {
        lightsList.addLights(lights);
        //recycleAdapter.notifyItemInserted(lightsList.getTotalSize()-1);
        Toast.makeText(getApplicationContext(),"Hello Fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == MY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                int position = data.getIntExtra(LIGHT_NUMBER,0);
                Lights lights = (Lights)data.getSerializableExtra(LIGHTS);

                if (lights == null){//deleted
                    lightsList.removeLightAt(position);
                }else{//modified
                    lightsList.updateLight(position, lights);
                }


            }else {

            }
        }
    }

}
