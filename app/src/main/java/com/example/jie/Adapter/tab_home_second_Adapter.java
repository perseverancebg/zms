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

public class tab_home_second_Adapter extends RecyclerView.Adapter<tab_home_second_Adapter.ViewHolder> {

    private Context context;
    private List<Map<String, Object>> data;

    public tab_home_second_Adapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_home_second_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(data.get(i).get("pic")).into(viewHolder.Grid_item_image);
        viewHolder.Grid_item_recycler_name.setText(data.get(i).get("name").toString());

        //点击图片，将图片的详情显示出来
        final Intent intent = new Intent(context, detail_item_Activity.class);
        intent.putExtra("pic", (Integer) data.get(i).get("pic"));
        intent.putExtra("name", (String) data.get(i).get("name"));
        viewHolder.Grid_item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Grid_item_image;
        TextView Grid_item_recycler_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Grid_item_image = itemView.findViewById(R.id.Grid_item_image);
            Grid_item_recycler_name = itemView.findViewById(R.id.Grid_item_recycler_name);
        }
    }
}
