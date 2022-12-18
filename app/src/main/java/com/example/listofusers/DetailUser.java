package com.example.listofusers;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listofusers.model.Data;

import java.util.ArrayList;

public class DetailUser extends AppCompatActivity {

    private TextView detailuser_name, detailuser_age, detailuser_address;
    private ImageView detailuser_backArrow, detailuser_edit, detailuser_delete;
    private ArrayList<Data> dataUser;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        initView();
        getData();
        setListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == 200) {
                dataUser = data.getParcelableArrayListExtra("dataUser");
                String name = dataUser.get(position).getName();
                String age = dataUser.get(position).getAge();
                String address = dataUser.get(position).getAddress();
                detailuser_name.setText(name);
                detailuser_age.setText(age);
                detailuser_address.setText(address);

                Intent intent = getIntent();
                intent.putExtra("dataUser", dataUser);
                setResult(300, intent);
            }
        }
    }

    private void setListener() {
        detailuser_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailuser_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.remove(position);
                Intent intent = new Intent();
                intent.putExtra("dataUser", dataUser);
                setResult(300, intent);
                finish();
            }
        });

        detailuser_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddUser.class);
                intent.putExtra("updateData", true);
                intent.putExtra("dataUser", dataUser);
                intent.putExtra("position", position);
                startActivityForResult(intent,1);
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String address = intent.getStringExtra("address");
        detailuser_name.setText(name);
        detailuser_age.setText(age);
        detailuser_address.setText(address);
        dataUser = intent.getParcelableArrayListExtra("dataUser");
        position = intent.getIntExtra("position", -1);
    }

    private void initView() {
        detailuser_name = findViewById(R.id.detailuser_name);
        detailuser_age = findViewById(R.id.detailuser_age);
        detailuser_address = findViewById(R.id.detailuser_address);
        detailuser_backArrow = findViewById(R.id.detailuser_backArrow);
        detailuser_edit = findViewById(R.id.detailuser_edit);
        detailuser_delete = findViewById(R.id.detailuser_delete);
    }
}