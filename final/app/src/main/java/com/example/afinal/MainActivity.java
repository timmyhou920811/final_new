package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_start, btn_male, btn_female;
    private TextView tv_sex;
    int c=0, male=0, female=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = findViewById(R.id.btn_start);
        Button btn_male = findViewById(R.id.btn_male);
        Button btn_female = findViewById(R.id.btn_female);
        TextView tv_sex = findViewById(R.id.tv_sex);

        btn_start.setOnClickListener(view -> {
            c++;
            if ((c != 0 && male != 0) || (c != 0 && female != 0) ) {
                if (tv_sex.getText() == "male") {
                    Intent intent = new Intent();
                    intent.setClass(this, selectActivity.class);
                    intent.putExtra("sex", 1); //可放所有基本類別
                    startActivity(intent);
                } else if (tv_sex.getText() == "female") {
                    Intent intent = new Intent();
                    intent.setClass(this, selectActivity.class);
                    intent.putExtra("sex", 2); //可放所有基本類別
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "尚未選擇性別" , Toast.LENGTH_SHORT).show();
            }
        });


        btn_male.setOnClickListener(view -> {
            tv_sex.setText("male");
            male++;
        });

        btn_female.setOnClickListener(view -> {
            tv_sex.setText("female");
            female++;
        });



    }
}