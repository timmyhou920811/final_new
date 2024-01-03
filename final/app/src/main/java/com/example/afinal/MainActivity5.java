package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    private EditText ed_ootd;
    private Button btn_query, btn_delete,btn_insert,btn_back;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items = new ArrayList<>();
    //建立MyDBHelper物件
    private SQLiteDatabase dbrw;
    int insert=0;

    class Data5 {
        int photo1; //圖片id
        //int photo2;
        //int photo3;
        String name; //名稱
        public Data5(String itemName, int itemImageId) {
            this.name = itemName;
            this.photo1 = itemImageId;
        }
        public String getItemName() {
            return name;
        }

        public int getItemImageId() {
            return photo1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        //連結畫面元件
        ed_ootd = findViewById(R.id.ed_ootd);
        btn_query = findViewById(R.id.btn_query);
        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        btn_back = findViewById(R.id.btn_back);

        //-------------------------------------------------
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
        String[] ootdNameArray = new String[]{"ootd1","ootd2","ootd3","ootd4","ootd5","ootd6"};
        //-------------------------------------------------
        Bundle bundle = getIntent().getExtras();
        int top = bundle.getInt("top");
        int pants = bundle.getInt("pants");
        int shoes = bundle.getInt("shoes");

        //-------------------------------------------------
        ArrayList<Data5> ootdList = new ArrayList<>();
//        CustomAdapter ootdAdapter = new CustomAdapter(this, ootdList);
//        listView = findViewById(R.id.listView);
//        listView.setAdapter(ootdAdapter);

        // Add more items as needed

        //MainActivity5.Data5[] ootdData = new MainActivity5.Data5[1];
        //for (int i = 0; i < ootdData.length; i++) {
            //ootdData[0] = new Data5();
            //ootdData[0].photo1 = topPhotoIdArray[top];
            //ootdData[i].photo2 = pantsPhotoIdArray[pants];
            //ootdData[i].photo3 = shoesPhotoIdArray[shoes];
            //ootdData[0].name = ootdNameArray[0];
        //}
        //ArrayAdapter<String> ootdAdapter = new ArrayAdapter<>(this, android.R.layout.listview_list, ootdNameArray);
        //MainActivity5.MyAdapter ootdAdapter = new MainActivity5.MyAdapter(ootdData, R.layout.listview_list);
//        CustomAdapter ootdAdapter = new CustomAdapter(this, ootdList);
//        listView = findViewById(R.id.listView);
//        listView.setAdapter(ootdAdapter);
//        dbrw = new MyDBHelper(this).getWritableDatabase();

        //宣告Adapter，使用simple_list_item_1並連結listView
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        //listView.setAdapter(adapter);
        //取得資料庫實體
        dbrw = new MyDBHelper(this).getWritableDatabase();

        btn_insert.setOnClickListener(view -> {
            insert++;
            ootdList.add(new Data5("Item " + insert, topPhotoIdArray[top]));
            CustomAdapter ootdAdapter = new CustomAdapter(this, ootdList);
            listView = findViewById(R.id.listView);
            listView.setAdapter(ootdAdapter);
            //adapter.notifyDataSetChanged();

        });

        //--------------------------------------------------------------------------------------------
        btn_delete.setOnClickListener(view -> {
            ootdList.remove(ed_ootd);

        });
        //--------------------------------------------------------------------------------------------
        btn_query.setOnClickListener(view -> {
//            Cursor c;
//            if (ed_ootd.length() < 1)
//                c = dbrw.rawQuery(" SELECT * FROM myTable", null);
//            else
//                c = dbrw.rawQuery("SELECT * FROM myTable WHERE book LIKE '" +
//                        ed_ootd.getText().toString() + "'", null);
//
//            c.moveToFirst();
//            items.clear();
//            Toast.makeText(MainActivity5.this, "共有" + c.getCount() + "筆", Toast.LENGTH_SHORT).show();
//            for (int i = 0; i < c.getCount(); i++) {
//                items.add("書籍:" + c.getString(0) + "\t\t\t\t價格" + c.getString(1));
//                c.moveToNext();
//            }
//            adapter.notifyDataSetChanged();
//            c.close();
        });
        btn_back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity6.class);
            startActivity(intent);
        });


    }

    //建立 myAdapter 來顯示 Spinner 及 GridView 的客製化畫面。
    public class MyAdapter extends BaseAdapter { //繼承BaseAdapter
        private MainActivity5.Data5[] data; //保存在myAdapter之中的資料夾
        private int view; //保存在myAdapter之中的畫面

        //透過建構子儲存資料來源與畫面到myAdapater之中
        public MyAdapter (MainActivity5.Data5[] data, int view) {
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
            ImageView imageView1 = convertView.findViewById(R.id.imageView1); //連接 ImageView 元件
            imageView1.setImageResource(data[position].photo1);               //根據 position 把圖片顯示到 ImageView
            //ImageView imageView2 = convertView.findViewById(R.id.imageView2); //連接 ImageView 元件
            //imageView2.setImageResource(data[position].photo2);               //根據 position 把圖片顯示到 ImageView
            //ImageView imageView3 = convertView.findViewById(R.id.imageView3); //連接 ImageView 元件
            //imageView3.setImageResource(data[position].photo3);               //根據 position 把圖片顯示到 ImageView

            return  convertView;
        }
    }

    public class CustomAdapter extends ArrayAdapter<Data5> {
        private MainActivity5.Data5[] data; //保存在myAdapter之中的資料夾
        private int view; //保存在myAdapter之中的畫面

        public CustomAdapter(Context context, ArrayList<Data5> Data5List) {
            super(context, 0, Data5List);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Data5 itemData = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_list, parent, false);
            }

            TextView itemName = convertView.findViewById(R.id.ootd);
            ImageView itemImage = convertView.findViewById(R.id.imageView1);

            itemName.setText(itemData.name);
            itemImage.setImageResource(itemData.photo1);

            return convertView;
        }
    }



}

