package pers.carl.ifindbook.handler;

import android.util.Log;
import android.widget.Button;

import pers.carl.ifindbook.pojo.Book;
import pers.carl.ifindbook.utils.DBUtils;

public class BooksStatusHandler {

    private static BooksStatusHandler instance;

    public static BooksStatusHandler getInstance() {
        if(instance == null) {
            return new BooksStatusHandler();
        }
        return instance;
    }

    public static void handleFavBooks(Book incomingBook, Button addToFav) {
        if(DBUtils.getInstance().getBooksFav().contains(incomingBook)) {
            addToFav.setEnabled(false);
        }
        Log.e("status Handler", String.valueOf(DBUtils.getInstance().getBooksFav().contains(incomingBook)));

    }

//    public static void handleReadingBooks(Book book, Button btn) {
//        if(book.getStatus() == Constants.READING) {
//            btn.setEnabled(false);
//        }else {
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DBUtils.getInstance().addToReading(book);
//                    btn.setEnabled(false);
//                }
//            });
//        }
//    }

//    public static void handleReadBooks(Book book, Button btn) {
//        if(book.getStatus() == Constants.READ) {
//            btn.setEnabled(false);
//        }else {
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DBUtils.getInstance().addToRead(book);
//                    btn.setEnabled(false);
//                }
//            });
//        }
//    }

}
