package pers.carl.ifindbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.concurrent.FutureTask;

import pers.carl.ifindbook.adapter.BooksRecViewAdapter;
import pers.carl.ifindbook.handler.BooksHandlerTest;
import pers.carl.ifindbook.handler.ResponseBody;
import pers.carl.ifindbook.handler.SignHandler;
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
        btnBack.setText(intent.getStringExtra("pageName"));
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

                    //TEST
                    FutureTask<String> allBooksTask = new FutureTask<>(new BooksHandlerTest("books", Constants.ALL_BOOKS));
                    Thread thread = new Thread(allBooksTask);
                    thread.start();
                    try{
                        //get获取线程返回值，通过ObjectMapper反序列化为ResponseBody
//                        ResponseBody body = mapper.readValue(allBooksTask.get(),ResponseBody.class);
                        ArrayList<Book> booksTemp = (ArrayList<Book>) mapper.readValue(allBooksTask.get(), ArrayList.class);

                        ArrayList<Book> books = mapper.convertValue(booksTemp, new TypeReference<ArrayList<Book>>() {});
//                        adapter.setBooks(DBUtils.getInstance().getBooks());
                        adapter.setBooks(books);
                        break;
                        //根据返回码确定提示信息
//                        Toast.makeText(getApplicationContext(),body.getResponseCode() == 200 ? "登录成功" : "登录失败",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        break;
                    }


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
//                case Constants.WISH_LIST: {
//                    adapter.setBooks(DBUtils.getInstance().getBooksWish());
//                    break;
//                }
                case Constants.FAV: {
                    adapter.setBooks(DBUtils.getInstance().getBooksFav());
                    break;
                }
                default: {
                    adapter.setBooks(DBUtils.getInstance().getBooks());
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