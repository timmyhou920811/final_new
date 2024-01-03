package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity7 extends AppCompatActivity {


    Button btPlay, btStop;

    class Data {
        int photo; //圖片id
        String name; //名稱
    }


    private MusicBinder myBinder;
    private Handler mHandler = new Handler();
    Intent MediaServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);


        ImageView imageView_top = findViewById(R.id.imageView_top);
        ImageView imageView_pants = findViewById(R.id.imageView_pants);
        ImageView imageView_shoes = findViewById(R.id.imageView_shoes);
        ImageView imageView_model = findViewById(R.id.imageView_model);
        ImageView bgimageView = findViewById(R.id.bgimageView);
        Button btn_restart = findViewById(R.id.btn_restart);
        Button btn_play = findViewById(R.id.btn_play);

        //----------------------------------------------------------

        /**開始使用播放音樂的Service*/
//        MediaServiceIntent = new Intent(this, MediaService.class);
//        startService(MediaServiceIntent);
//        /**將Service的播放狀態進行監聽，並綁定給介面*/
//        bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
//        /**取得介面*/
//        btPlay = findViewById(R.id.btn_play);


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
                R.drawable.shoes1, R.drawable.shoes2,
                R.drawable.shoes3_2, R.drawable.shoes4,
                R.drawable.shoes5_2, R.drawable.shoes6_2};
        //-------------------------------------------------------------------

        Bundle bundle = getIntent().getExtras();
        int top = bundle.getInt("top");
        int pants = bundle.getInt("pants");
        int shoes = bundle.getInt("shoes");
        int sex = bundle.getInt("sex");
        imageView_top.setImageResource(topPhotoIdArray[top]);
        imageView_pants.setImageResource(pantsPhotoIdArray[pants]);
        imageView_shoes.setImageResource(shoesPhotoIdArray[shoes]);
        if (sex == 1) {
            imageView_model.setImageResource(R.drawable.model_male);
        } else if (sex == 2) {
            imageView_model.setImageResource(R.drawable.model_female);
        }

        //-------------------------------------------------------------------
        String[] bgNameArray = new String[]{"top1", "top2", "top3", "top4", "top5", "top6", "top7"};
        int[] bgPhotoIdArray = new int[]{
                R.drawable.bg1, R.drawable.bg2,
                R.drawable.bg3, R.drawable.bg4,
                R.drawable.bg5, R.drawable.bg6,
                R.drawable.bg7};
        //Step1：建立資料來源，用陣列方式宣告自行設計的類別，將陣列的每個內容填入要顯示的資料
        MainActivity7.Data[] bgData = new MainActivity7.Data[bgNameArray.length];
        for (int i = 0; i < bgData.length; i++) {
            bgData[i] = new Data();
            bgData[i].name = bgNameArray[i];
            bgData[i].photo = bgPhotoIdArray[i];
        }

        MainActivity7.MyAdapter bgAdapter = new MainActivity7.MyAdapter(bgData, R.layout.cubee_list2);
        Gallery bggallery = findViewById(R.id.bggallery);
        bggallery.setAdapter(bgAdapter);
        //ggridView.setNumColumns(3); //Step3：連接 GridView 元件，並連結 myAdapter
        //-------------------------------------------------------------------
        bggallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int bgselectedItem = position; // 所选值即为 selectedItem
                String topselectedname = bgNameArray[position];
                //Toast.makeText(selectActivity.this, "你選取了" + topselectedItem, Toast.LENGTH_SHORT).show();
                bgimageView.setImageResource(bgData[bgselectedItem].photo);


            }
        });
        //-------------------------------------------------------------------
        btn_restart.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        });


    }

    public class MyAdapter extends BaseAdapter { //繼承BaseAdapter
        private MainActivity7.Data[] data; //保存在myAdapter之中的資料夾
        private int view; //保存在myAdapter之中的畫面

        //透過建構子儲存資料來源與畫面到myAdapater之中
        public MyAdapter(MainActivity7.Data[] data, int view) {
            this.data = data;
            this.view = view;
        }

        //回傳資料來源筆數
        @Override
        public int getCount() {
            return data.length;
        }

        //回傳資料項目內容
        @Override
        public Object getItem(int position) {
            return data[position];
        }

        //回傳某筆項目id
        @Override
        public long getItemId(int position) {
            return 0;
        }

        //取得畫面元件-------------------------------------
        @Override               //需要顯示的項目編號
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(view, parent, false); //取得XML畫面
            TextView name = convertView.findViewById(R.id.name);            //連接 TextView 元件
            name.setText(data[position].name);                              //根據 position 把字串顯示到 TextView
            ImageView imageView = convertView.findViewById(R.id.imageView); //連接 ImageView 元件
            imageView.setImageResource(data[position].photo);               //根據 position 把圖片顯示到 ImageView

            return convertView;
        }
    }


//    private ServiceConnection mServiceConnection = new ServiceConnection() {
//        /**如果介面有和MediaService成功綁定時，便會跳至onServiceConnected，反之onServiceDisconnected*/
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            /**取用Binder中的各種音樂操作的方法*/
//            myBinder = (MusicBinder) service;
//            if (myBinder.isPlaying()) {
//                btPlay.setBackgroundResource(R.drawable.play);
//            } else {
//                btPlay.setBackgroundResource(R.drawable.play);
//            }
//
//            btPlay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    setIsPlayButton(btPlay);
//                }
//            });
//            btStop.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //mHandler.removeCallbacks(runnable);
//                    myBinder.closeMedia();
//                    stopService(MediaServiceIntent);
//                    finish();
//                }
//            });
////            /**開始使用執行緒，使之每秒更新一次進度條*/
////            mHandler.post(runnable);
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };
//    /**使用Handler配合runnable，始進度條每秒進行更新*/
//
//    /**
//     * 設置播放音樂與暫停音樂
//     */
//    private void setIsPlayButton(Button bt) {
//        if (myBinder.isPlaying()) {
//            myBinder.pauseMusic();
//            bt.setBackgroundResource(R.drawable.play);
//        } else {
//            myBinder.playMusic();
//            bt.setBackgroundResource(R.drawable.play);
//        }
//    }
//
//    /**
//     * 畫面消失時，則不監聽目前播放狀態
//     */
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unbindService(mServiceConnection);
//    }
}