package com.example.TEM_PMSD.homeiot_lights.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.TEM_PMSD.homeiot_lights.R;
import com.example.TEM_PMSD.homeiot_lights.model.Lights;
import com.example.TEM_PMSD.homeiot_lights.util.CommonUtil;

public class NewDialog extends DialogFragment {
    String[] lightsName;
    Spinner spinner;
    ImageView imageView;
    int currentIndex;
    EditText editText;

    NoticeDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener=(NoticeDialogListener) context;
    }

    public interface NoticeDialogListener{
        public void OnDialogPositiveClick(DialogFragment fragment, Lights lights);

//        public void OnDialogNegativeClick(DialogFragment fragment);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.content_new_lights, null);
        builder.setView(view);
//        builder.setTitle("new Dialog");
//        builder.setMessage("Hello Dialog");

        lightsName = getResources().getStringArray(R.array.lights_name);

        spinner = view.findViewById(R.id.spinner);
        imageView = view.findViewById(R.id.imageView);
        editText = view.findViewById(R.id.editText);

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity()
                , android.R.layout.simple_spinner_item
                , lightsName);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Drawable drawable = CommonUtil.makeLightsType(getResources(), position);
                imageView.setImageDrawable(drawable);
                currentIndex=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editText.getText().toString();
                if(TextUtils.isEmpty(name)){
                    name = lightsName[currentIndex];
                }

                Lights lights = new Lights(name, currentIndex);
                Log.d("NewDialog","currentIndex : "+currentIndex );

                listener.OnDialogPositiveClick(NewDialog.this, lights);
//                listener.OnDialogPositiveClick(NewDialog.this);
//                Toast.makeText(getActivity(), lights.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), lightsName[currentIndex], Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }


}
