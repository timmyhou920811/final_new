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

public class selectActivity extends AppCompatActivity {

    private Button btn_finish;
    private TextView tv_select;
    private String str;
    private GridView topgridView,pantsgridView,shoesgridView;
    private ImageView imageView_top,imageView_pants,imageView_shoes;

    int topselectedItem = 0;
    int pantsselectedItem = 0;
    int shoesselectedItem = 0;

    class Data {
        int photo; //圖片id
        String name; //名稱
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button btn_finish = findViewById(R.id.btn_finish);
        TextView tv_select = findViewById(R.id.tv_select);
        ImageView imageView_top = findViewById(R.id.imageView_top);
        ImageView imageView_pants = findViewById(R.id.imageView_pants);
        ImageView imageView_shoes = findViewById(R.id.imageView_shoes);


        String[] topNameArray = new String[]{"top1","top2","top3","top4","top5","top6","top7","top8","top9","top10"};
        int[] topPhotoIdArray = new int[]{
                R.drawable.top1, R.drawable.top2,
                R.drawable.top3, R.drawable.top4,
                R.drawable.top5, R.drawable.top6,
                R.drawable.top7, R.drawable.top8,
                R.drawable.top9, R.drawable.top10};
        //Step1：建立資料來源，用陣列方式宣告自行設計的類別，將陣列的每個內容填入要顯示的資料
        selectActivity.Data[] topData = new selectActivity.Data[topNameArray.length];
        for (int i = 0; i < topData.length; i++) {
            topData[i] = new selectActivity.Data();
            topData[i].name = topNameArray[i];
            topData[i].photo = topPhotoIdArray[i];
        }

        MyAdapter topAdapter = new MyAdapter(topData, R.layout.cubee_list);
        GridView topgridView = findViewById(R.id.topgridView);
        topgridView.setAdapter(topAdapter);
        topgridView.setNumColumns(3); //Step3：連接 GridView 元件，並連結 myAdapter

        //------------------------------------------------------------

        String[] pantsNameArray = new String[]{"pants1","pants2","pants3","pants4","pants5","pants6"};
        int[] pantsPhotoIdArray = new int[]{
                R.drawable.pants1, R.drawable.pants2,
                R.drawable.pants3, R.drawable.pants4,
                R.drawable.pants7, R.drawable.pants6};
        //Step1：建立資料來源，用陣列方式宣告自行設計的類別，將陣列的每個內容填入要顯示的資料
        selectActivity.Data[] pantsData = new selectActivity.Data[pantsNameArray.length];
        for (int i = 0; i < pantsData.length; i++) {
            pantsData[i] = new selectActivity.Data();
            pantsData[i].name = pantsNameArray[i];
            pantsData[i].photo = pantsPhotoIdArray[i];
        }

        MyAdapter pantsAdapter = new MyAdapter(pantsData, R.layout.cubee_list);
        GridView pantsgridView = findViewById(R.id.pantsgridView);
        pantsgridView.setAdapter(pantsAdapter);
        pantsgridView.setNumColumns(3); //Step3：連接 GridView 元件，並連結 myAdapter

        //----------------------------------------------------------

        String[] shoesNameArray = new String[]{"shoes1","shoes2","shoes3","shoes4","shoes5","shoes6"};
        int[] shoesPhotoIdArray = new int[]{
                R.drawable.shoes1,R.drawable.shoes2,
                R.drawable.shoes3_2,R.drawable.shoes4,
                R.drawable.shoes5_2,R.drawable.shoes6_2};
        //Step1：建立資料來源，用陣列方式宣告自行設計的類別，將陣列的每個內容填入要顯示的資料
        selectActivity.Data[] shoesData = new selectActivity.Data[shoesNameArray.length];
        for (int i = 0; i < shoesData.length; i++) {
            shoesData[i] = new selectActivity.Data();
            shoesData[i].name = shoesNameArray[i];
            shoesData[i].photo = shoesPhotoIdArray[i];
        }

        MyAdapter shoesAdapter = new MyAdapter(shoesData, R.layout.cubee_list);
        GridView shoesgridView = findViewById(R.id.shoesgridView);
        shoesgridView.setAdapter(shoesAdapter);
        shoesgridView.setNumColumns(3); //Step3：連接 GridView 元件，並連結 myAdapter
        //----------------------------------------------------------------
        topgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topselectedItem = position; // 所选值即为 selectedItem
                String topselectedname = topNameArray[position];
                //Toast.makeText(selectActivity.this, "你選取了" + topselectedItem, Toast.LENGTH_SHORT).show();
                imageView_top.setImageResource(topData[topselectedItem].photo);


            }
        });
        pantsgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pantsselectedItem = position; // 所选值即为 selectedItem
                String topselectedname = topNameArray[position];
                //Toast.makeText(selectActivity.this, "你選取了" + pantsselectedItem, Toast.LENGTH_SHORT).show();
                imageView_pants.setImageResource(pantsData[pantsselectedItem].photo);

            }
        });
        shoesgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shoesselectedItem = position; // 所选值即为 selectedItem
                String topselectedname = topNameArray[position];
                //Toast.makeText(selectActivity.this, "你選取了" + shoesselectedItem, Toast.LENGTH_SHORT).show();
                imageView_shoes.setImageResource(shoesData[shoesselectedItem].photo);

            }
        });
        Bundle bundle = getIntent().getExtras();
        int sex = bundle.getInt("sex");
        //----------------------------------------------------------------
        btn_finish.setOnClickListener(view -> {
            Intent intent2 = new Intent(this, MainActivity6.class);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("top", topselectedItem);
            bundle2.putInt("pants", pantsselectedItem);
            bundle2.putInt("shoes", shoesselectedItem);
            bundle2.putInt("sex", sex);
            intent2.putExtras(bundle2);
            startActivity(intent2);

        });
    }

    public class MyAdapter extends BaseAdapter { //繼承BaseAdapter
        private selectActivity.Data[] data; //保存在myAdapter之中的資料夾
        private int view; //保存在myAdapter之中的畫面

        //透過建構子儲存資料來源與畫面到myAdapater之中
        public MyAdapter (selectActivity.Data[] data, int view) {
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