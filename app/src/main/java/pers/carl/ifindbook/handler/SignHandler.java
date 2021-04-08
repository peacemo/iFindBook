package pers.carl.ifindbook.handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public class SignHandler implements Callable<String> {
    //sign in needed
    private final String account;
    private final String pwd;

    //sign up needed
    private final String nickname;
    private final String email;

    private final String url;

    public SignHandler(String account, String pwd, String url) {
        this.account = account;
        this.pwd = pwd;
        this.url = url;
        this.nickname = "";
        this.email = "";
    }

    public SignHandler(String account, String pwd, String nickname, String email, String url) {
        this.account = account;
        this.pwd = pwd;
        this.url = url;
        this.nickname = nickname;
        this.email = email;
    }

    @Override
    public String call(){
        try {
            //开启连接
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            //拼接数据
            String data = "account="+ URLEncoder.encode(account, StandardCharsets.UTF_8.toString())+
                    "&pwd="+URLEncoder.encode(pwd,StandardCharsets.UTF_8.toString()) +
                    "&nickname="+URLEncoder.encode(nickname,StandardCharsets.UTF_8.toString()) +
                    "&email="+URLEncoder.encode(email, StandardCharsets.UTF_8.toString());
            //设置请求方法
            connection.setRequestMethod("POST");
            //允许输入输出
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //写数据（也就是发送数据）
            connection.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            byte [] bytes = new byte[1024];
            //获取返回的数据
            int len = connection.getInputStream().read(bytes);
            return new String(bytes,0,len,StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    @Override
    public String toString() {
        return "SignHandler{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
