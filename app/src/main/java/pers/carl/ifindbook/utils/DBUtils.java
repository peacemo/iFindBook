package pers.carl.ifindbook.utils;

import android.util.Log;

import androidx.annotation.Nullable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.concurrent.FutureTask;

import pers.carl.ifindbook.Constants;
import pers.carl.ifindbook.handler.BooksDataHandler;
import pers.carl.ifindbook.pojo.Book;
import pers.carl.ifindbook.pojo.User;

public class DBUtils {

    private static DBUtils instance;

    private static User user;
    private static ArrayList<Book> booksAll;
    private static ArrayList<Book> booksReading;
    private static ArrayList<Book> booksRead;
    private static ArrayList<Book> booksFav;
    private static ArrayList<Book> booksSearch;

    private final ObjectMapper mapper = new ObjectMapper();

    private DBUtils() {
        if (booksAll == null) {
            booksAll = new ArrayList<>();
        }
        if (booksReading == null) {
            booksReading = new ArrayList<>();
        }
        if (booksRead == null) {
            booksRead = new ArrayList<>();
        }
        if (booksFav == null) {
            booksFav = new ArrayList<>();
        }if (booksSearch == null) {
            booksSearch = new ArrayList<>();
        }
        if (user == null) {
            user = new User();
            user.setId(0);
        }

//        initData();
    }

    public void initData() {
        booksRead.clear();
        booksReading.clear();
        booksFav.clear();

        //Add data from database.
        //make sure every set of books comes in a row
        if (requestBooks(String.valueOf(user.getId()), "reading", booksReading)) {
            if (requestBooks(String.valueOf(user.getId()), "fav", booksFav)) {
                requestBooks(String.valueOf(user.getId()), "read", booksRead);
            }
        }
        getBooksAll();
    }

    /**
     *
     * @param data the part of the sql sent to server,
     *             it's:
     *              "books: String" while requesting all books,
     *              "uid: int" while requesting for a certain kind of books like "reading/read/fav",
     *              "query: String" while fuzzy search for some books.
     *
     * @param reqType the type of the request destination url,
     *                it's:
     *                  Constants.READING_BOOKS while equals "reading"
     *                  Constants.FAV_BOOKS while equals "fav"
     *                  Constants.READ_BOOKS while equals "read"
     *                  Constants.SEARCH_BOOKS while equals "search"
     *                  Constants.ALL_BOOKS while default.
     *
     * @param targetList the target ArrayList that receives the result.
     * @return the ArrayList that the function requests from server.
     */
    private boolean requestBooks(String data, String reqType, ArrayList<Book> targetList) {

        ArrayList<Book> booksTemp = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        String url = "";
        switch (reqType) {
            case "reading": {
                url = Constants.READING_BOOKS;
                break;
            }
            case "fav": {
                url = Constants.FAV_BOOKS;
                break;
            }
            case "read": {
                url = Constants.READ_BOOKS;
                break;
            }
            case "search": {
                url = Constants.SEARCH_BOOKS;
                break;
            }
            default: {
                url = Constants.ALL_BOOKS;
                break;
            }
        }
        //TEST
        FutureTask<String> allBooksTask = new FutureTask<>(new BooksDataHandler(data, reqType, url));
        Thread thread = new Thread(allBooksTask);
        thread.start();
        try{
            //get获取线程返回值，通过ObjectMapper反序列化为ResponseBody
            booksTemp = (ArrayList<Book>) mapper.readValue(allBooksTask.get(), ArrayList.class);
            //the booksTemp are automatically set as LinkedHashMap, so the
            //following code converts is into ArrayList<Book>
            books = mapper.convertValue(booksTemp, new TypeReference<ArrayList<Book>>() {});

            for(Book b:books) {
                targetList.add(b);
            }

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static DBUtils getInstance() {
        if (null == instance) {
            instance = new DBUtils();
        }
        return instance;
    }

    public  ArrayList<Book> getBooksAll() {
        booksAll.clear();
        requestBooks("books", "all", booksAll);
        return booksAll;
    }

    public ArrayList<Book> getBooksReading() {
//        booksReading.clear();

        return booksReading;
    }

    public static ArrayList<Book> getBooksRead() {
        return booksRead;
    }

    public ArrayList<Book> getBooksFav() {
//        booksFav.clear();

        return booksFav;
    }

    public static User getUser() {
        return user;
    }

    public  boolean setUser(User user) {
        this.user = user;
        return true;
    }

    /**
     * find the Book object from target book id
     * and return it.
     * @param _id
     * @return
     */
    public static Book getBookById(int _id) {
        for (Book b : booksAll) {
            if (b.getId() == _id) {
                return b;
            }
        }
        return null;
    }

    /**
     * add books into the reading list
     * @param b
     * @return
     */

    public static boolean addToReading(Book b) {
        try {
            booksReading.add(b);
            booksRead.remove(b);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addToRead(Book b) {
        try {
            booksRead.add(b);
            booksReading.remove(b);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addToFav(Book b) {
        try {
            booksFav.add(b);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Book> searchBooks(String query) {
        booksSearch.clear();
        requestBooks(query, "search", booksSearch);
        return booksSearch;
    }
}
