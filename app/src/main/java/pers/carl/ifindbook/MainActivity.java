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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pers.carl.ifindbook.adapter.BooksRecViewAdapter;
import pers.carl.ifindbook.utils.DBUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLogo;
    private WindowInsets windowHelper;
    private SearchView edtSearch;
    private Button btnGoSign;
    private CardView btnAll, btnReading, btnRead, btnFav;
    private RecyclerView recmdRecView;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            try {
                btnGoSign.setText("Hi, " + data.getExtras().getString("userName"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(
                  View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        initView();
        setOnclickListener();
        handleRecmd();

    }

    private void handleRecmd() {
        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        adapter.setBooks(DBUtils.getInstance().getRecmdBook());
        recmdRecView.setAdapter(adapter);
        recmdRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
    }

    private void setOnclickListener() {
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("target", Constants.DEFAULT);
                startActivity(intent);
            }
        });

        btnReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("target", Constants.READING);
                startActivity(intent);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("target", Constants.READ);
                startActivity(intent);
            }
        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("target", Constants.FAV);
                startActivity(intent);
            }
        });

        btnGoSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        edtSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                intent.putExtra("target", Constants.SEARCH);
                intent.putExtra("query", "%" + query + "%");
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    /**
     *  Initialize the Views in the MainActivity
     */
    private void initView() {

        btnGoSign = findViewById(R.id.btnGoSign);
        if (DBUtils.getInstance().getUser().getId() != 0) {
            btnGoSign.setText("Hi, " + DBUtils.getUser().getNickname());
        }

        btnAll = findViewById(R.id.btnAll);
        btnReading = findViewById(R.id.btnReading);
        btnRead = findViewById(R.id.btnAlreadyRead);
        btnFav = findViewById(R.id.btnFav);
//        btnAbout = findViewById(R.id.btnAbout);

        edtSearch = findViewById(R.id.edtSearch);

        recmdRecView = findViewById(R.id.recmdRecView);
    }
}