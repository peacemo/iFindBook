package pers.carl.ifindbook.handler;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public class BooksDataHandler implements Callable<String> {

    private String data;
    private String reqType;
    private String url;

    public BooksDataHandler(String data, String reqType, String url) {
        this.data = data;
        this.reqType = reqType;
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
            String data = "data=" + URLEncoder.encode(this.data, StandardCharsets.UTF_8.toString())
                            + "&reqType=" + URLEncoder.encode(this.reqType, StandardCharsets.UTF_8.toString());
            connection.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            //TODO: 获取返回数据
            //获取返回的数据
            InputStream in = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int read;
            int len = 0;
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            while ((read = in.read(buffer)) > 0) {
                len += read;
                byteArray.write(buffer, 0, read);
                byteArray.flush();
            }
            byte [] bytes = byteArray.toByteArray();

            connection.disconnect();
            return new String(bytes,0,len,StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Handler", "connection timeout. ");
            return "[]";
        }
    }

    @Override
    public String toString() {
        return "BooksDataHandler{" +
                "table='" + data + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
