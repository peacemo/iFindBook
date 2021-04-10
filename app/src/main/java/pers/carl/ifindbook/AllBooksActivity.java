package pers.carl.ifindbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import pers.carl.ifindbook.adapter.BooksRecViewAdapter;
import pers.carl.ifindbook.pojo.Book;
import pers.carl.ifindbook.utils.DBUtils;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private Button btnBack;
//    private ArrayList<Book> books = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                      | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        initView();

        Intent intent = getIntent();
        int pageType = intent.getIntExtra("target", -1);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);

        //judge the page's type which the users wish to open.
        //then put the relevant data into the adepter.
        Log.v("from allBooks", "pageType: " + String.valueOf(pageType));
        if (pageType != -1) {
            switch (pageType) {
                case Constants.DEFAULT: {
                    ArrayList<Book> b = DBUtils.getInstance().getBooksAll();
                    Log.e("ALL", DBUtils.getUser().toString());
                    adapter.setBooks(b);
                    break;
                }
                case Constants.READING: {
                    ArrayList<Book> b = DBUtils.getInstance().getBooksReading();
                    adapter.setBooks(b);
                    break;
                }
                case Constants.READ: {
                    adapter.setBooks(DBUtils.getInstance().getBooksRead());
                    break;
                }
                case Constants.FAV: {
                    adapter.setBooks(DBUtils.getInstance().getBooksFav());
                    break;
                }case Constants.SEARCH: {
                    String query = intent.getStringExtra("query");
                    adapter.setBooks(DBUtils.getInstance().searchBooks(query));
                    break;
                }
                default: {
                    adapter.setBooks(DBUtils.getInstance().getBooksAll());
                    Toast.makeText(this, "oops/.\\ some thing went wrong. ", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }else {
            Toast.makeText(this, "oops/.\\ some thing went wrong. ", Toast.LENGTH_SHORT).show();
        }

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initView() {

        booksRecView = findViewById(R.id.allBooksRecView);
        btnBack = findViewById(R.id.btnBack);

    }

}