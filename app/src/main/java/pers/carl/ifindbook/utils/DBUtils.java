package pers.carl.ifindbook.utils;

import android.util.Log;
import java.util.ArrayList;
import pers.carl.ifindbook.Constants;
import pers.carl.ifindbook.pojo.Book;
public class DBUtils {

    private static DBUtils instance;

    private static ArrayList<Book> books;
    private static ArrayList<Book> booksReading;
    private static ArrayList<Book> booksRead;
//    private static ArrayList<Book> booksWish;
    private static ArrayList<Book> booksFav;
    private DBUtils() {
        if (books == null) {
            books = new ArrayList<>();
        }
        if (booksReading == null) {
            booksReading = new ArrayList<>();
        }
        if (booksRead == null) {
            booksRead = new ArrayList<>();
        }
        //TODO: wishlist 相关
//        if (booksWish == null) {
//            booksWish = new ArrayList<>();
//        }
        if (booksFav == null) {
            booksFav = new ArrayList<>();
        }
        initData();
    }

    private void initData() {
        //TODO: Add data from database.
//      these are the sample data.
        books.add(new Book(1, "Book_I", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(2, "Book_II", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(3, "Book_III", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(4, "Book_IV", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(5, "Book_V", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(6, "Book_VI", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(7, "Book_VII", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(8, "Book_VIII", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(9, "Book_IX", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));
        books.add(new Book(10, "Book_X", "Isaka Kotaro", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3780195714,2877537196&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc", false, Constants.DEFAULT));

        tagBooks();
    }

    private static boolean tagBooks() {
        //clear the items from the arrayLists
        booksReading.clear();
        booksRead.clear();
        //TODO: wishlist 相关
//        booksWish.clear();
        booksFav.clear();

        for(Book b : books) {
            int status = b.getStatus();

            //add books to the target status list
            switch (status) {
                case Constants.READ: {
                    booksRead.add(b);
                    break;
                }
                case Constants.READING: {
                    booksReading.add(b);
                    break;
                }
                //TODO: wishlist 相关
//                case Constants.WISH_LIST: {
//                    booksWish.add(b);
//                    break;
//                }
                default: break;
            }

            //add books to the fav list
            if(b.isFav()) {
                booksFav.add(b);
            }
        }

        return true;
    }
    public static DBUtils getInstance() {
        if (null == instance) {
            instance = new DBUtils();
        }
        return instance;
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }

    public static ArrayList<Book> getBooksReading() {
        return booksReading;
    }

    public static ArrayList<Book> getBooksRead() {
        return booksRead;
    }

    //TODO: wishlist 相关
//    public static ArrayList<Book> getBooksWish() {
//        return booksWish;
//    }

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
        for (Book b : books) {
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
        b.setStatus(Constants.READING);
        if(tagBooks()) {
            Log.v("log: ", "added to Reading." +
                    "\n------" +
                    booksReading.get(booksReading.size() -1).toString());
            //TODO: update record to database
            return true;
        }
        return false;
    }

    public static boolean addToRead(Book b) {
        b.setStatus(Constants.READ);
        if(tagBooks()) {
            Log.v("log: ", "added to Read.");
            //TODO: update record to database
            return true;
        }
        return false;
    }

    //TODO: wishlist 相关
//    public static boolean addToWishList(Book b) {
//        b.setStatus(Constants.WISH_LIST);
//        if(tagBooks()) {
//            Log.v("log: ", "added to Wish List.");
//            //TODO: update record to database
//            return true;
//        }
//        return false;
//    }

    public static boolean changeFavStatus(Book b) {
        if(b.isFav()) {
            b.setFav(false);
        }else {
            b.setFav(true);
        }

        if(tagBooks()) {
            Log.v("log: ", "added to Fav.");
            //TODO: update record to database
            return true;
        }
        return false;
    }
}
