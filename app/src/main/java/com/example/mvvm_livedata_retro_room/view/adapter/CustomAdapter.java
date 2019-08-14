package com.example.mvvm_livedata_retro_room.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvvm_livedata_retro_room.R;
import com.example.mvvm_livedata_retro_room.service.model.LocationDto;
import com.example.mvvm_livedata_retro_room.view.EditActivity;

import java.util.ArrayList;

/**
 * Created by user1 on 31/7/19.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    ArrayList<LocationDto> list;

    public CustomAdapter(Context context, ArrayList<LocationDto> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_custom_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_1.setText(list.get(position).getDevice_id());
        holder.tv_2.setText(list.get(position).getMobile());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                LocationDto dto = list.get(position);
                intent.putExtra("location",dto);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_1, tv_2;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_2 = itemView.findViewById(R.id.tv_2);
        }
    }
}
