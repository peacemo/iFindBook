package pers.carl.ifindbook.handler;

import android.view.View;
import android.widget.Button;

import pers.carl.ifindbook.Constants;
import pers.carl.ifindbook.pojo.Book;
import pers.carl.ifindbook.utils.DBUtils;

public class BooksHandler {

    private static BooksHandler instance;

    public static BooksHandler getInstance() {
        if(instance == null) {
            return new BooksHandler();
        }
        return instance;
    }

    public static void handleReadingBooks(Book book, Button btn) {
        if(book.getStatus() == Constants.READING) {
            btn.setEnabled(false);
        }else {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBUtils.getInstance().addToReading(book);
                    btn.setEnabled(false);
                }
            });
        }
    }

    public static void handleReadBooks(Book book, Button btn) {
        if(book.getStatus() == Constants.READ) {
            btn.setEnabled(false);
        }else {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBUtils.getInstance().addToRead(book);
                    btn.setEnabled(false);
                }
            });
        }
    }

    //TODO: wishlist 相关
//    public static void handleWishBooks(Book book, Button btn) {
//        if(book.getStatus() == Constants.WISH_LIST) {
//            btn.setEnabled(false);
//        }else {
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DBUtils.getInstance().addToWishList(book);
//                    btn.setEnabled(false);
//                }
//            });
//        }
//    }

    public static void handleFavBooks(Book book, Button btn) {
        if(book.isFav()) {
            btn.setText("取消最爱");
        }else {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBUtils.getInstance().changeFavStatus(book);
                    btn.setText("取消最爱");
                }
            });
        }
    }
}
