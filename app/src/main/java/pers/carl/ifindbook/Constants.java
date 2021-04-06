package pers.carl.ifindbook;

public class Constants {
    public static final int DEFAULT = 0;
    public static final int READING = 1;
    public static final int READ = 2;
    public static final int WISH_LIST = 3;
    public static final int FAV = 4;

    //server strings
    private static final String HOST = "192.168.1.110";
    private static final String PORT = "8080";

    public static final String SIGN_IN = "http://" + HOST + ":" + PORT + "/sign/in";
//    public static final String SIGN_IN = "http://ifindbook.cn.utools.club/sign/in";
    public static final String SIGN_UP = "http://" + HOST + ":" + PORT + "/sign/up";
//    public static final String SIGN_UP = "http://ifindbook.cn.utools.club/sign/up";
    public static final String ALL_BOOKS = "http://" + HOST + ":" + PORT + "/books/all";
//    public static final String ALL_BOOKS = "http://ifindbook.cn.utools.club/books/all";
}
