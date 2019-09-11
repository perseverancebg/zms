package com.example.jie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jie.R;
import com.example.jie.detail_item_Activity;

import java.util.List;
import java.util.Map;

public class tab_home_one_Adapter extends RecyclerView.Adapter<tab_home_one_Adapter.ViewHolder> {

    private Context context;
    private List<Map<String, Object>> data;

    public tab_home_one_Adapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_home_one_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(data.get(i).get("pic")).into(viewHolder.item_image);
        viewHolder.item_recycler_name.setText(data.get(i).get("name").toString());
        viewHolder.item_recycler_desc.setText(data.get(i).get("desc").toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_recycler_name;
        TextView item_recycler_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.item_image);
            item_recycler_name = itemView.findViewById(R.id.item_recycler_name);
            item_recycler_desc = itemView.findViewById(R.id.item_recycler_desc);
        }
    }
}
