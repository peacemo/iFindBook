package pers.carl.ifindbook;

public class Constants {
    public static final int DEFAULT = 0;
    public static final int READING = 1;
    public static final int READ = 2;
    public static final int FAV = 4;
    public static final int SEARCH = 5;

    //server strings
    private static final String HOST = "192.168.1.110";
    private static final String PORT = "8080";

    public static final String SIGN_IN = "http://" + HOST + ":" + PORT + "/sign/in";
//    public static final String SIGN_IN = "http://ifindbook.cn.utools.club/sign/in";
    public static final String SIGN_UP = "http://" + HOST + ":" + PORT + "/sign/up";
//    public static final String SIGN_UP = "http://ifindbook.cn.utools.club/sign/up";
    public static final String ALL_BOOKS = "http://" + HOST + ":" + PORT + "/books/all";
//    public static final String ALL_BOOKS = "http://ifindbook.cn.utools.club/books/all";
    public static final String READING_BOOKS = "http://" + HOST + ":" + PORT + "/books/reading";
//    public static final String READING_BOOKS = "http://ifindbook.cn.utools.club/books/reading";
    public static final String FAV_BOOKS = "http://" + HOST + ":" + PORT + "/books/fav";
//    public static final String FAV_BOOKS = "http://ifindbook.cn.utools.club/books/fav";
    public static final String READ_BOOKS = "http://" + HOST + ":" + PORT + "/books/read";
//    public static final String READ_BOOKS = "http://ifindbook.cn.utools.club/books/read";
    public static final String SEARCH_BOOKS = "http://" + HOST + ":" + PORT + "/books/search";
//    public static final String SEARCH_BOOKS = "http://ifindbook.cn.utools.club/books/search";
    public static final String RECMD_BOOKS = "http://" + HOST + ":" + PORT + "/books/recmd";
//    public static final String RECMD_BOOKS = "http://ifindbook.cn.utools.club/books/recmd";
}
