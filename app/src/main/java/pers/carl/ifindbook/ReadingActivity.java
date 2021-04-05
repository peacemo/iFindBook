package pers.carl.ifindbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pers.carl.ifindbook.adapter.BooksRecViewAdapter;
import pers.carl.ifindbook.utils.DBUtils;

public class ReadingActivity extends AppCompatActivity {

    private Button btnBack;
    private RecyclerView readingRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        initView();

        btnBack.setOnClickListener(new View.OnClickListener() {  //set a on click listener for the back button
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        btnBack.setText(intent.getStringExtra("pageName"));

        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        adapter.setBooks(DBUtils.getInstance().getBooksReading());

        readingRecView.setAdapter(adapter);
        readingRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        btnBack = findViewById(R.id.btnBackReading);
        readingRecView = findViewById(R.id.readingRecView);
    }
}