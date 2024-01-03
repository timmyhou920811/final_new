package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private Button btn_finish,btn_top, btn_pants, bth_shoes;
    private TextView tv_select;
    private String str;
    private GridView gridView3;
    private ImageView imageView_top,imageView_pants,imageView_shoes;
    private PreferenceManager preferenceManager;


    class Data {
        int photo; //圖片id
        String name; //名稱
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btn_finish = findViewById(R.id.btn_finish);
        Button btn_top = findViewById(R.id.btn_top);
        Button btn_pants = findViewById(R.id.btn_pants);
        Button btn_shoes = findViewById(R.id.btn_shoes);
        TextView tv_select = findViewById(R.id.tv_select);
        ImageView imageView_tops = findViewById(R.id.imageView_top);
        ImageView imageView_pants = findViewById(R.id.imageView_pants);
        ImageView imageView_shoes = findViewById(R.id.imageView_shoes);

        //-----------------------------------------------------------------------------------
        //使用 GridView，透過客製化 layout 與 myAdapter 顯示 cubeeData (自製的資料)
        String[] topNameArray = new String[]{"哭哭","發抖","再見","生氣","top5","top6"};
        int[] topPhotoIdArray = new int[]{
                R.drawable.top1, R.drawable.top2,
                R.drawable.top3, R.drawable.top4,
                R.drawable.top5, R.drawable.top6};
        //Step1：建立資料來源，用陣列方式宣告自行設計的類別，將陣列的每個內容填入要顯示的資料
        MainActivity3.Data[] topData = new MainActivity3.Data[topNameArray.length];
        for (int i = 0; i < topData.length; i++) {
            topData[i] = new Data();
            topData[i].name = topNameArray[i];
            topData[i].photo = topPhotoIdArray[i];
        }

        String[] pantsNameArray = new String[]{"昏倒","竊笑","很棒","你好","你好","你好"};
        int[] pantsPhotoIdArray = new int[]{
                R.drawable.pants1, R.drawable.pants2,
                R.drawable.pants3, R.drawable.pants4,
                R.drawable.pants7, R.drawable.pants6};
        //Step1：建立資料來源，用陣列方式宣告自行設計的類別，將陣列的每個內容填入要顯示的資料
        Data[] pantsData = new Data[pantsNameArray.length];
        for (int i = 0; i < pantsData.length; i++) {
            pantsData[i] = new Data();
            pantsData[i].name = pantsNameArray[i];
            pantsData[i].photo = pantsPhotoIdArray[i];
        }

        MyAdapter pantsAdapter = new MyAdapter(pantsData, R.layout.cubee_list);
        GridView gridView3 = findViewById(R.id.gridView3);
        gridView3.setAdapter(pantsAdapter);
        gridView3.setNumColumns(3); //Step3：連接 GridView 元件，並連結 myAdapter


        //-------------------------------------------------------------------------------


        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pantsselectedItem = position; // 所选值即为 selectedItem
                Toast.makeText(MainActivity3.this, "你選取了" + pantsselectedItem, Toast.LENGTH_SHORT).show();
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    String result = bundle.getString("name");
                    int topselectedItem = bundle.getInt("top");
                }

                //imageView_top.setImageResource(topData[topselectedItem].photo);
                imageView_pants.setImageResource(pantsData[pantsselectedItem].photo);
                //imageView_shoes.setImageResource(shoesData[shoesselectedItem].photo);
                //tv_select.setText(result);
                Intent intent2 = new Intent(MainActivity3.this, MainActivity6.class);
                Bundle bundle2 = new Bundle();
                bundle.putInt("pants", pantsselectedItem);
                intent2.putExtras(bundle2);
            }
        });


        //-------------------------------------------------------------------------------
        btn_top.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);

        });
        btn_shoes.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity4.class);
            startActivity(intent);

        });
        btn_finish.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity6.class);
            startActivity(intent);

        });


    }

    //建立 myAdapter 來顯示 Spinner 及 GridView 的客製化畫面。
    public class MyAdapter extends BaseAdapter { //繼承BaseAdapter
        private MainActivity3.Data[] data; //保存在myAdapter之中的資料夾
        private int view; //保存在myAdapter之中的畫面

        //透過建構子儲存資料來源與畫面到myAdapater之中
        public MyAdapter (MainActivity3.Data[] data, int view) {
            this.data = data;
            this.view = view;
        }

        //回傳資料來源筆數
        @Override
        public int getCount() { return data.length; }
        //回傳資料項目內容
        @Override
        public Object getItem(int position) { return data[position]; }
        //回傳某筆項目id
        @Override
        public long getItemId(int position) { return 0; }

        //取得畫面元件-------------------------------------
        @Override               //需要顯示的項目編號
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(view, parent, false); //取得XML畫面
            TextView name = convertView.findViewById(R.id.name);            //連接 TextView 元件
            name.setText(data[position].name);                              //根據 position 把字串顯示到 TextView
            ImageView imageView = convertView.findViewById(R.id.imageView); //連接 ImageView 元件
            imageView.setImageResource(data[position].photo);               //根據 position 把圖片顯示到 ImageView

            return  convertView;
        }
    }
}