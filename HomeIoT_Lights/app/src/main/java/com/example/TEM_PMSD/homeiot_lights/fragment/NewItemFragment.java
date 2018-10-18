package com.example.TEM_PMSD.homeiot_lights.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.TEM_PMSD.homeiot_lights.R;
import com.example.TEM_PMSD.homeiot_lights.util.CommonUtil;

public class NewItemFragment extends Fragment {
    private ImageView imageView;
    private Spinner spinner;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_lights, container, false);

        spinner = view.findViewById(R.id.spinner);
        imageView = view.findViewById(R.id.imageView);
        editText = view.findViewById(R.id.editText);

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity()
                , android.R.layout.simple_spinner_item
                , getResources().getStringArray(R.array.lights_name));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Drawable drawable = CommonUtil.makeLightsType(getResources(), position);
                imageView.setImageDrawable(drawable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    public void setItemResource(int index){
        this.spinner.setSelection(index);
    }


    public void setItemName(String name){
        this.editText.setText(name);
    }

    public String getLightsName() {
        return this.editText.getText().toString();
    }

    public int getResourcesIndex() {
        return  this.spinner.getSelectedItemPosition();
    }
}
