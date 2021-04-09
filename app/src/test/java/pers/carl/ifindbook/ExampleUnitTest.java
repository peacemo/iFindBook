package pers.carl.ifindbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.util.ArrayList;

import pers.carl.ifindbook.pojo.Book;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

//    @Test
//    public void Test() {
//        try {
//            Connection connection = null;
//            Statement statement = null;
//            Class.forName(Constants.DRIVER);
//            connection = DriverManager.getConnection("jdbc:mysql://" + Constants.IP_ADDRESS + "/ifindbook"
//                    , Constants.USERNAME
//                    , Constants.PASSWORD);
//            statement = connection.createStatement();
//            System.out.println("Database connected. ");
//
//            ResultSet resultSet = statement.executeQuery(Constants.SELECT_ALL_BOOKS);
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String author = resultSet.getString("author");
//                String imgURL = resultSet.getString("img_url");
//                String shortDesc = resultSet.getString("short_desc");
//                String longDesc = resultSet.getString("long_desc");
//
//                System.out.print("ID: " + id + "\t");
//                System.out.print("Name: " + name + "\t");
//                System.out.print("Author: " + author + "\t");
//                System.out.println("URL: " + imgURL + "\t");
//                System.out.println("Overview: " + shortDesc + "\t");
//                System.out.println("Detail: " + longDesc + "\t");
//                System.out.println("---------------------------------------");
//
//            }
//            connection.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        };
//    }

    @Test
    public void testObjectmapper() {
        ArrayList<Book> books = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String string = objectMapper.writeValueAsString(books);
            System.out.println(string);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayList() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(11, "Book_I", "Isaka Kotaro", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3539122593,1620052230&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc"));
        books.add(new Book(101, "Book_X", "Isaka Kotaro", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3780195714,2877537196&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc"));
        System.out.println(String.valueOf(books.contains(new Book(101, "Book_X", "Isaka Kotaro", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3780195714,2877537196&fm=26&gp=0.jpg", "short Desc", "long Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desclong Desc"))));

        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        System.out.println(String.valueOf(arrayList.contains(6)));
    }

}