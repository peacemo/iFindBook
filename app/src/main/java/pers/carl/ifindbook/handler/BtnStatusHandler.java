package pers.carl.ifindbook.handler;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.FutureTask;

import pers.carl.ifindbook.Constants;
import pers.carl.ifindbook.pojo.Book;
import pers.carl.ifindbook.utils.DBUtils;

public class BtnStatusHandler {

    private static BtnStatusHandler instance;
    private static ObjectMapper mapper = new ObjectMapper();

    public static BtnStatusHandler getInstance() {
        if(instance == null) {
            return new BtnStatusHandler();
        }
        return instance;
    }

    public static void handleFavBooks(Book incomingBook, Button addToFav) {
        if(DBUtils.getInstance().getBooksFav().contains(incomingBook)) {
            addToFav.setEnabled(false);
        } else {
            addToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DBUtils.getUser().getId() == 0) {
                        Snackbar.make(addToFav, "请先登录哟~", Snackbar.LENGTH_SHORT)
                                .show();
                    }else if (DBUtils.getInstance().addToFav(incomingBook)) {
                        BookStatusHandler bookStatusHandler = new BookStatusHandler(String.valueOf(DBUtils.getUser().getId()), String.valueOf(incomingBook.getId()), Constants.FAV_BOOKS);
                        FutureTask<String> addFavTask = new FutureTask<>(bookStatusHandler);
                        Thread thread = new Thread(addFavTask);
                        thread.start();
                        try{
                            //get获取线程返回值，通过ObjectMapper反序列化为ResponseBody
                            ResponseBody body = mapper.readValue(addFavTask.get(),ResponseBody.class);
                            Log.e("addFav", body.toString());

                            if(body.getResponseCode() == 200) {
                                Snackbar.make(addToFav, "收藏成功", Snackbar.LENGTH_SHORT)
                                        .show();
                                addToFav.setEnabled(false);
                            } else {
                                Snackbar.make(addToFav, "收藏失败，稍后重试", Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }

    public static void handleReadingBooks(Book incomingBook, Button addToReading) {
        if(DBUtils.getInstance().getBooksReading().contains(incomingBook)) {
            addToReading.setEnabled(false);
        } else {
            addToReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DBUtils.getUser().getId() == 0) {
                        Snackbar.make(addToReading, "请先登录哟~", Snackbar.LENGTH_SHORT)
                                .show();
                    }else if (DBUtils.getInstance().addToReading(incomingBook)) {
                        BookStatusHandler bookStatusHandler = new BookStatusHandler(String.valueOf(DBUtils.getUser().getId()), String.valueOf(incomingBook.getId()), Constants.READING_BOOKS);
                        FutureTask<String> addReadingTask = new FutureTask<>(bookStatusHandler);
                        Thread thread = new Thread(addReadingTask);
                        thread.start();
                        try{
                            //get获取线程返回值，通过ObjectMapper反序列化为ResponseBody
                            ResponseBody body = mapper.readValue(addReadingTask.get(),ResponseBody.class);

                            if(body.getResponseCode() == 200) {
                                Snackbar.make(addToReading, "添加成功", Snackbar.LENGTH_SHORT)
                                        .show();
                                addToReading.setEnabled(false);
                            } else {
                                Snackbar.make(addToReading, "添加失败，稍后重试", Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public static void handleReadBooks(Book incomingBook, Button addToRead) {
        if(DBUtils.getInstance().getBooksRead().contains(incomingBook)) {
            addToRead.setEnabled(false);
        } else {
            addToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DBUtils.getUser().getId() == 0) {
                        Snackbar.make(addToRead, "请先登录哟~", Snackbar.LENGTH_SHORT)
                                .show();
                    }else if (DBUtils.getInstance().addToRead(incomingBook)) {
                        BookStatusHandler bookStatusHandler = new BookStatusHandler(String.valueOf(DBUtils.getUser().getId()), String.valueOf(incomingBook.getId()), Constants.READ_BOOKS);
                        FutureTask<String> addReadTask = new FutureTask<>(bookStatusHandler);
                        Thread thread = new Thread(addReadTask);
                        thread.start();
                        try{
                            //get获取线程返回值，通过ObjectMapper反序列化为ResponseBody
                            ResponseBody body = mapper.readValue(addReadTask.get(),ResponseBody.class);

                            if(body.getResponseCode() == 200) {
                                Snackbar.make(addToRead, "添加成功", Snackbar.LENGTH_SHORT)
                                        .show();
                                addToRead.setEnabled(false);
                            } else {
                                Snackbar.make(addToRead, "添加失败，稍后重试", Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

}
