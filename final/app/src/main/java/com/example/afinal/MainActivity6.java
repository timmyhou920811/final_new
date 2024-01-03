package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

//        ImageButton imageButton = findViewById(R.id.imageButton);
        ImageView imageView_top = findViewById(R.id.imageView_top);
        ImageView imageView_pants = findViewById(R.id.imageView_pants);
        ImageView imageView_shoes = findViewById(R.id.imageView_shoes);
        ImageView imageView_model = findViewById(R.id.imageView_model);
        //Button btn_restart = findViewById(R.id.btn_restart);
        Button btn_save = findViewById(R.id.btn_save);
        TextView tv = findViewById(R.id.tv_finish);



        //--------------------------------------------------------------------
        int[] topPhotoIdArray = new int[]{
                R.drawable.top1, R.drawable.top2,
                R.drawable.top3, R.drawable.top4,
                R.drawable.top5, R.drawable.top6,
                R.drawable.top7, R.drawable.top8,
                R.drawable.top9, R.drawable.top10};
        int[] pantsPhotoIdArray = new int[]{
                R.drawable.pants1, R.drawable.pants2,
                R.drawable.pants3, R.drawable.pants4,
                R.drawable.pants7, R.drawable.pants6};
        int[] shoesPhotoIdArray = new int[]{
                R.drawable.shoes1,R.drawable.shoes2,
                R.drawable.shoes3_2,R.drawable.shoes4,
                R.drawable.shoes5_2,R.drawable.shoes6_2};
        //-------------------------------------------------------------------
        Bundle bundle = getIntent().getExtras();
        int top = bundle.getInt("top");
        int pants = bundle.getInt("pants");
        int shoes = bundle.getInt("shoes");
        int sex = bundle.getInt("sex");
        imageView_top.setImageResource(topPhotoIdArray[top]);
        imageView_pants.setImageResource(pantsPhotoIdArray[pants]);
        imageView_shoes.setImageResource(shoesPhotoIdArray[shoes]);
        if (sex ==  1) { imageView_model.setImageResource(R.drawable.model_male);}
        else if (sex == 2){ imageView_model.setImageResource(R.drawable.model_female); }
        //tv_finish.setText(sex);
        //-------------------------------------------------------------------

//        imageButton.setOnClickListener(view -> {
//            Intent intent = new Intent(this, MainActivity5.class);
//            Bundle bundle2 = new Bundle();
//            bundle2.putInt("top", top);
//            bundle2.putInt("pants", pants);
//            bundle2.putInt("shoes", shoes);
//            intent.putExtras(bundle2);
//            startActivity(intent);
//
//
//
//        });
//        btn_restart.setOnClickListener(view -> {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//
//        });

        btn_save.setOnClickListener(view -> {
            Intent intent2 = new Intent(this, MainActivity7.class);
            Bundle bundle3 = new Bundle();
            bundle3.putInt("top", top);
            bundle3.putInt("pants", pants);
            bundle3.putInt("shoes", shoes);
            bundle3.putInt("sex", sex);
            intent2.putExtras(bundle3);
            startActivity(intent2);
        });





    }
}