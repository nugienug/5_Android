package com.example.TEM_PMSD.homeiot_lights.model;

import android.support.v7.widget.RecyclerView;

import com.example.TEM_PMSD.homeiot_lights.service.LightsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LightsList {
    private List<Lights> lightsList;
    private RecyclerView.Adapter recycleAdapter;
    private LightsService service;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.222.187:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public LightsList(){
        lightsList = new ArrayList<>();
        service = retrofit.create(LightsService.class);
    }

    public void getLightsList() {
        service.getLightList().enqueue(new Callback<List<Lights>>() {
            @Override
            public void onResponse(Call<List<Lights>> call, Response<List<Lights>> response) {
                if(response.isSuccessful()){
                    lightsList = response.body();
                    recycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Lights>> call, Throwable t) {

            }
        });
        //return this.lightsList;
    }

    public void addLights(Lights newLight){
        service.createLights(newLight).enqueue(new Callback<Lights>() {
            @Override
            public void onResponse(Call<Lights> call, Response<Lights> response) {
                if (response.isSuccessful()){
                    Lights newLights = response.body();
                    lightsList.add(newLights);
                    recycleAdapter.notifyItemInserted(lightsList.size()-1);
                }
            }

            @Override
            public void onFailure(Call<Lights> call, Throwable t) {

            }
        });

    }

    public Lights getLights(int position) {
        return this.lightsList.get(position);
    }

    public void registerAdapter(RecyclerView.Adapter recycleAdapter){
        this.recycleAdapter = recycleAdapter;
    }

    public int getTotalSize() {
        return  this.lightsList.size();
    }

    public void removeLightAt (int position){
        this.lightsList.remove(position);
        this.recycleAdapter.notifyItemRemoved(position);
    }

    //update
    int changeIndex = 0;
    public void updateLight (final int position, Lights lights){
        changeIndex = position;
        service.updateLight(lights).enqueue(new Callback<Lights>() {
            @Override
            public void onResponse(Call<Lights> call, Response<Lights> response) {
                if(response.isSuccessful()){
                    Lights lights = response.body();
                    lightsList.set(changeIndex,lights);
                    recycleAdapter.notifyItemChanged(changeIndex, lights);                }
            }

            @Override
            public void onFailure(Call<Lights> call, Throwable t) {

            }
        });

//        this.lightsList.set(position, lights);
//        this.recycleAdapter.notifyItemChanged(position,lights);
    }


}
