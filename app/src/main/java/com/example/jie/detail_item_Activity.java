package com.example.jie;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class detail_item_Activity extends AppCompatActivity {
    private ImageView detail_image;
    private TextView tv_name_detail;
    private Toolbar detail_toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_);
        detail_image = findViewById(R.id.detail_image);
        tv_name_detail = findViewById(R.id.tv_name_detail);
        detail_toolbar = findViewById(R.id.detail_toolbar);

        collapsingToolbarLayout = findViewById(R.id.detail_collapsing);
        fb = findViewById(R.id.fb);

        setSupportActionBar(detail_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        String name = null;
        if (intent != null) {
            int id = intent.getIntExtra("pic", R.drawable.apple);
            name = intent.getStringExtra("name");

            Glide.with(this).load(id).into(detail_image);
            collapsingToolbarLayout.setTitle(name);
            tv_name_detail.setText(animalName(name));
        }


        detail_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail_item_Activity.this, "完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String animalName(String name) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1200; i++) {
            builder.append(name);
        }
        return builder.toString();
    }

}
