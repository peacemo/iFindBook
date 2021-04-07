package pers.carl.ifindbook.handler;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public class BooksHandlerTest implements Callable<String> {

    private String table;
    private String url;

    public BooksHandlerTest(String data, String url) {
        this.table = data;
        this.url = url;
    }

    @Override
    public String call() {
        try{
            //开启连接
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            //设置请求方法 POST
            connection.setRequestMethod("POST");
            //允许输入输出
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //写数据（也就是发送数据）
            String data = "data="+ URLEncoder.encode(table, StandardCharsets.UTF_8.toString());
            connection.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            //TODO: 获取返回数据
            byte [] bytes = new byte[10240];
            //获取返回的数据
            int len = connection.getInputStream().read(bytes);
            return new String(bytes,0,len,StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Handler", "connection timeout. ");
            return "[]";
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
