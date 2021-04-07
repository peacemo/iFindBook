package pers.carl.ifindbook.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.concurrent.FutureTask;

import pers.carl.ifindbook.Constants;
import pers.carl.ifindbook.handler.BooksHandlerTest;
import pers.carl.ifindbook.pojo.Book;
public class DBUtils {

    private static DBUtils instance;

    private static ArrayList<Book> booksAll;
    private static ArrayList<Book> booksReading;
    private static ArrayList<Book> booksRead;
//    private static ArrayList<Book> booksWish;
    private static ArrayList<Book> booksFav;

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
        }
        initData();
    }

    private void initData() {
        //Add data from database.
        requestBooks("books", booksAll);

        //these are the sample data.
        booksAll.add(new Book(11, "Book_I", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        booksAll.add(new Book(101, "Book_X", "Isaka Kotaro", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3780195714,2877537196&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));

//        tagBooks();
    }

    /**
     * request target books from server
     * @return the status of the processing result
     */
    private boolean requestBooks(String data, ArrayList<Book> targetList) {

        ArrayList<Book> booksTemp = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        //TEST
        FutureTask<String> allBooksTask = new FutureTask<>(new BooksHandlerTest(data, Constants.ALL_BOOKS));
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

//    private static boolean tagBooks() {
//        //clear the items from the arrayLists
//        booksReading.clear();
//        booksRead.clear();
//        booksFav.clear();
//
//        for(Book b : books) {
//            int status = b.getStatus();
//
//            //add books to the target status list
//            switch (status) {
//                case Constants.READ: {
//                    booksRead.add(b);
//                    break;
//                }
//                case Constants.READING: {
//                    booksReading.add(b);
//                    break;
//                }
//                default: break;
//            }
//
//            //add books to the fav list
//            if(b.isFav()) {
//                booksFav.add(b);
//            }
//        }
//
//        return true;
//    }

    public static DBUtils getInstance() {
        if (null == instance) {
            instance = new DBUtils();
        }
        return instance;
    }

    public static ArrayList<Book> getBooksAll() {
        return booksAll;
    }

    public static ArrayList<Book> getBooksReading() {
        return booksReading;
    }

    public static ArrayList<Book> getBooksRead() {
        return booksRead;
    }

    public static ArrayList<Book> getBooksFav() {
        return booksFav;
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

//    public static boolean addToReading(Book b) {
//        b.setStatus(Constants.READING);
//        if(tagBooks()) {
//            Log.v("log: ", "added to Reading." +
//                    "\n------" +
//                    booksReading.get(booksReading.size() -1).toString());
//            //TODO: update record to database
//            return true;
//        }
//        return false;
//    }

//    public static boolean addToRead(Book b) {
//        b.setStatus(Constants.READ);
//        if(tagBooks()) {
//            Log.v("log: ", "added to Read.");
//            //TODO: update record to database
//            return true;
//        }
//        return false;
//    }

//    public static boolean changeFavStatus(Book b) {
//        if(b.isFav()) {
//            b.setFav(false);
//        }else {
//            b.setFav(true);
//        }
//
//        if(tagBooks()) {
//            Log.v("log: ", "added to Fav.");
//            //TODO: update record to database
//            return true;
//        }
//        return false;
//    }
}
