package pers.carl.ifindbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.carl.ifindbook.handler.BtnStatusHandler;
import pers.carl.ifindbook.pojo.Book;
import pers.carl.ifindbook.utils.DBUtils;

public class BookDetailActivity extends AppCompatActivity {

    private ImageView bookCover;
    private Button btnBack, addToReading, addToRead, addToFav;
    private TextView name, author, page, desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                      | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        initViews();

        BtnStatusHandler btnStatusHandler = new BtnStatusHandler();

        Intent intent = getIntent();
        if(intent != null) {
            if (DBUtils.getUser().getId() != 0) {
                int bookId = intent.getIntExtra("bookId", -1);
                if (bookId != -1) {
                    Book incomingBook = DBUtils.getBookById(bookId);
                    if (incomingBook != null) {
                        setData(incomingBook);
                        btnStatusHandler.getInstance().handleReadingBooks(incomingBook, addToReading);
//                    btnStatusHandler.getInstance().handleReadBooks(incomingBook, addToRead);

                        btnStatusHandler.getInstance().handleFavBooks(incomingBook, addToFav);
                    }
                }
            } else {
                try {
                    Book incomingBook = new ObjectMapper().readValue(intent.getStringExtra("objBook"), Book.class);
                    setData(incomingBook);
                    addToFav.setVisibility(View.GONE);
                    addToRead.setVisibility(View.GONE);
                    addToReading.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setData(Book book) {

        name.setText(book.getName());
        author.setText(book.getAuthor());
//        page.setText(String.valueOf(book.getPages()) + " Pages. ");
        desc.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap().load(book.getImgUrl())
                .into(bookCover);

    }

    private void initViews() {

        bookCover = findViewById(R.id.imgCover);
        addToReading = findViewById(R.id.btnAddToReading);
        addToRead = findViewById(R.id.btnAddToRead);
        addToFav = findViewById(R.id.btnAddToFav);
        addToFav = findViewById(R.id.btnAddToFav);
        name = findViewById(R.id.txtConName);
        author = findViewById(R.id.txtConAuthor);
        desc = findViewById(R.id.txtConDesc);
        btnBack = findViewById(R.id.btnBack);

    }
}