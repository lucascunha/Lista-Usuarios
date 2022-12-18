package com.example.listofusers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;

import com.example.listofusers.model.Data;

import java.util.ArrayList;

public class AddUser extends AppCompatActivity {

    private Toolbar toolbar2;
    private ImageView adduser_backArrow;
    private EditText adduser_name, adduser_age, adduser_address;
    private Button adduser_saveButton;
    private LoadingScreen loadingScreen = new LoadingScreen(AddUser.this);
    private ArrayList<Data> dataUser;
    private int position;
    private boolean updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initView();
        setListener();
    }

    private void setListener() {

        if (updateData == false) {
            // Add Data User
            adduser_saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = adduser_name.getText().toString().trim();
                    String age = adduser_age.getText().toString().trim();
                    String address = adduser_address.getText().toString().trim();
                    Data temp = new Data(name, age, address);

                    loadingScreen.startLoadingScreen();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingScreen.dismissLoading();
                            Intent intent = new Intent();
                            intent.putExtra("newData", temp);
                            setResult(200, intent);
                            finish();
                        }
                    }, 1000);
                }
            });

        } else {
            // Update Data User
            adduser_saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = adduser_name.getText().toString().trim();
                    String age = adduser_age.getText().toString().trim();
                    String address = adduser_address.getText().toString().trim();
                    Data temp = new Data(name, age, address);
                    dataUser.set(position, temp);

                    loadingScreen.startLoadingScreen();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingScreen.dismissLoading();
                            Intent intent = new Intent();
                            intent.putExtra("dataUser", dataUser);
                            setResult(200, intent);
                            finish();
                        }
                    }, 1000);
                }
            });
        }

        adduser_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar2 = findViewById(R.id.toolbar2);
        adduser_backArrow = findViewById(R.id.adduser_backArrow);
        adduser_name = findViewById(R.id.adduser_name);
        adduser_age = findViewById(R.id.adduser_age);
        adduser_address = findViewById(R.id.adduser_address);
        adduser_saveButton = findViewById(R.id.adduser_saveButton);
        Intent intent = getIntent();
        updateData = intent.getBooleanExtra("updateData", false);

        if (updateData == true) {
            dataUser = intent.getParcelableArrayListExtra("dataUser");
            position = intent.getIntExtra("position", -1);
            Data user = dataUser.get(position);
            toolbar2.setTitle("Update User");
            adduser_name.setText(user.getName());
            adduser_age.setText(user.getAge());
            adduser_address.setText(user.getAddress());
            adduser_saveButton.setText("Update Data");
        }
    }
}