package com.example.listofusers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.listofusers.model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataRVAdapter.OnCardListener {

    private TextView main_noData;
    private FloatingActionButton main_FAB;
    private RecyclerView main_recyclerView;
    private ArrayList<Data> dataUser;
    private DataRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupRecyclerView();
        setListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == 200) {
                Data newData = data.getParcelableExtra("newData");
                dataUser.add(newData);
                adapter.notifyDataSetChanged();

                if (!dataUser.isEmpty()) {
                    main_noData.setVisibility(View.GONE);
                }
            } else if (resultCode == 300) {
                ArrayList<Data> dataUser2;
                dataUser2 = data.getParcelableArrayListExtra("dataUser");
                dataUser.clear();
                dataUser.addAll(dataUser2);
                adapter.notifyDataSetChanged();

                if (dataUser.isEmpty()) {
                    main_noData.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void setListener() {
        main_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddUser.class);
                startActivityForResult(intent,1);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        main_recyclerView.setLayoutManager(manager);
        main_recyclerView.setAdapter(adapter);
    }

    private void initView() {
        main_noData = findViewById(R.id.main_noData);
        main_FAB = findViewById(R.id.main_FAB);
        main_recyclerView = findViewById(R.id.main_recyclerView);
        dataUser = new ArrayList<Data>();
        adapter = new DataRVAdapter(dataUser, this);
    }

    @Override
    public void onCardClick(int position) {
        Data user = dataUser.get(position);
        Intent intent = new Intent(this, DetailUser.class);
        intent.putExtra("name", user.getName());
        intent.putExtra("age", user.getAge());
        intent.putExtra("address", user.getAddress());
        intent.putExtra("dataUser", dataUser);
        intent.putExtra("position", position);
        startActivityForResult(intent, 1);
    }
}