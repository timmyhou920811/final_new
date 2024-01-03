package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    private Button btn_finish,btn_top, btn_pants, bth_shoes;
    private TextView tv_select;
    private String str;
    private GridView gridView4;

    class Data {
        int photo; //圖片id
        String name; //名稱
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button btn_finish = findViewById(R.id.btn_finish);
        Button btn_top = findViewById(R.id.btn_top);
        Button btn_pants = findViewById(R.id.btn_pants);
        Button btn_shoes = findViewById(R.id.btn_shoes);
        TextView tv_select = findViewById(R.id.tv_select);

        //-----------------------------------------------------------------------------------
        //使用 GridView，透過客製化 layout 與 myAdapter 顯示 cubeeData (自製的資料)
        String[] shoesNameArray = new String[]{"驚嚇","大笑","驚嚇","大笑","驚嚇","大笑"};
        int[] shoesPhotoIdArray = new int[]{
                R.drawable.shoes1,R.drawable.shoes2,
                R.drawable.shoes3,R.drawable.shoes4,
                R.drawable.shoes5,R.drawable.shoes6};
        //Step1：建立資料來源，用陣列方式宣告自行設計的類別，將陣列的每個內容填入要顯示的資料
        Data[] shoesData = new Data[shoesNameArray.length];
        for (int i = 0; i < shoesData.length; i++) {
            shoesData[i] = new Data();
            shoesData[i].name = shoesNameArray[i];
            shoesData[i].photo = shoesPhotoIdArray[i];
        }

        MyAdapter shoesAdapter = new MyAdapter(shoesData, R.layout.cubee_list);
        GridView gridView4 = findViewById(R.id.gridView4);
        gridView4.setAdapter(shoesAdapter);
        gridView4.setNumColumns(3); //Step3：連接 GridView 元件，並連結 myAdapter


        //-------------------------------------------------------------------------------
        btn_top.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);

        });
        btn_pants.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity3.class);
            startActivity(intent);

        });
        btn_finish.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity6.class);
            startActivity(intent);

        });
        //-------------------------------------------------------------------------------

        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int shoesselectedItem = position+1; // 所选值即为 selectedItem
                Toast.makeText(MainActivity4.this, "你選取了" + shoesselectedItem, Toast.LENGTH_SHORT).show();

            }
        });

    }

    //建立 myAdapter 來顯示 Spinner 及 GridView 的客製化畫面。
    public class MyAdapter extends BaseAdapter { //繼承BaseAdapter
        private MainActivity4.Data[] data; //保存在myAdapter之中的資料夾
        private int view; //保存在myAdapter之中的畫面

        //透過建構子儲存資料來源與畫面到myAdapater之中
        public MyAdapter (MainActivity4.Data[] data, int view) {
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