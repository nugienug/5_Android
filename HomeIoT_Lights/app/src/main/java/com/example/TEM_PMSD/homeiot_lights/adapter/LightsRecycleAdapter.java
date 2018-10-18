package com.example.TEM_PMSD.homeiot_lights.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.TEM_PMSD.homeiot_lights.R;
import com.example.TEM_PMSD.homeiot_lights.model.Lights;
import com.example.TEM_PMSD.homeiot_lights.model.LightsList;
import com.example.TEM_PMSD.homeiot_lights.util.CommonUtil;

public class LightsRecycleAdapter
        extends RecyclerView.Adapter<LightsRecycleAdapter.LightsViewHolder> {

    static LightsItemClickListerner nItemClickListerner;

    public interface LightsItemClickListerner{
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(LightsItemClickListerner clickListener){
        nItemClickListerner = clickListener;
    }

    Context mContext;
    LayoutInflater mInflater;
    LightsList mLightsList;


    public LightsRecycleAdapter(Context context, LightsList lightsList){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLightsList = lightsList;
    }


    @NonNull
    @Override
    public LightsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.item_card_view,viewGroup,false);
        return new LightsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LightsViewHolder lightsViewHolder, int position) {
        Log.d("onBindViewHolder",""+position);
        Lights light = mLightsList.getLights(position);
        Log.d("onBindViewHolder-22",""+light.toString());
        lightsViewHolder.name.setText(light.getName());
        Drawable drawable = CommonUtil.makeLightsType(mContext.getResources(), light.getResourceIndex());
        lightsViewHolder.image.setImageDrawable(drawable);




//        light.getResouceIndex();
//        lightsViewHolder.image();
    }

    @Override
    public int getItemCount() {
        return mLightsList.getTotalSize();
    }

    public static class LightsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageView image;

        public LightsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById((R.id.lights_icon));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            nItemClickListerner.onItemClick(getAdapterPosition(),v);
        }
    }
}
