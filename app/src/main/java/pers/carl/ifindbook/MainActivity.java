package pers.carl.ifindbook;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private static String activityName = "首页";
    private ImageView imgLogo;
    private TextView txtLicense;
    private WindowInsets windowHelper;
    private EditText edtSearch;
    private Button btnGoSign;
    private CardView btnAll, btnReading, btnRead, btnWishList, btnFav, btnAbout;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(
                  View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        initView();

        //TODO: 取消注释
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("pageName", MainActivity.activityName);
                intent.putExtra("target", Constants.DEFAULT);
                startActivity(intent);
            }
        });

        btnReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("pageName", MainActivity.activityName);
                intent.putExtra("target", Constants.READING);
                startActivity(intent);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("pageName", MainActivity.activityName);
                intent.putExtra("target", Constants.READ);
                startActivity(intent);
            }
        });

        //TODO: wishlist 相关
//        btnWishList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
//                intent.putExtra("pageName", MainActivity.activityName);
//                intent.putExtra("target", Constants.WISH_LIST);
//                startActivity(intent);
//            }
//        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("pageName", MainActivity.activityName);
                intent.putExtra("target", Constants.FAV);
                startActivity(intent);
            }
        });

        btnGoSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

    }


    /**
     *  Initialize the Views in the MainActivity
     */
    private void initView() {
//        imgLogo = findViewById(R.id.imgLogo);
//        imgLogo.setFitsSystemWindows(true);

        txtLicense = findViewById(R.id.txtLicense);
        txtLicense.setFitsSystemWindows(true);
        btnGoSign = findViewById(R.id.btnGoSign);
        btnAll = findViewById(R.id.btnAll);
        btnReading = findViewById(R.id.btnReading);
        btnRead = findViewById(R.id.btnAlreadyRead);
        //TODO: wishlist 相关
//        btnWishList = findViewById(R.id.btnWishList);
        btnFav = findViewById(R.id.btnFav);
//        btnAbout = findViewById(R.id.btnAbout);

        edtSearch = findViewById(R.id.edtSearch);
    }
}