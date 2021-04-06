package pers.carl.ifindbook;

import org.junit.Test;

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

}