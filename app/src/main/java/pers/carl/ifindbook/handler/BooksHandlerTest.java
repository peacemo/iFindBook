package pers.carl.ifindbook.handler;

import android.provider.ContactsContract;
import android.util.Log;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import pers.carl.ifindbook.Constants;

public class BooksHandlerTest implements Callable<String> {

    private String table;
    private String url;

    public BooksHandlerTest(String table, String url) {
        this.table = table;
        this.url = url;
    }

    @Override
    public String call() {
        try{
            //开启连接
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            Log.e("BooksHandlerTest", "connected");

            //设置请求方法 POST
            connection.setRequestMethod("POST");
            //允许输入输出
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //写数据（也就是发送数据）
            String data = "table="+ URLEncoder.encode(table, StandardCharsets.UTF_8.toString());
            connection.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            //TODO: 获取返回数据
            byte [] bytes = new byte[10240];
            //获取返回的数据
            int len = connection.getInputStream().read(bytes);
            return new String(bytes,0,len,StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String toString() {
        return "BooksHandlerTest{" +
                "table='" + table + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
