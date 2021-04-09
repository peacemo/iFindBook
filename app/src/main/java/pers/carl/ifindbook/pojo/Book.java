package pers.carl.ifindbook.pojo;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Book {

    private int id;
//    private int status; //0.default  1.reading  2.read  3.wishList
    private String name;
    private String author;
    private String imgUrl;
    private String shortDesc;
    private String longDesc;
//    private boolean isExpanded; //it is used to handle the expand-or-not status of the CardView in RecyclerView
//    private boolean isFav;

    public Book() {
    }
    public Book(
            int id,
            String name, String author, String imgUrl, String shortDesc, String longDesc
//            , boolean isFav, int status
    ) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.imgUrl = imgUrl;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
//        this.isFav = isFav;
//        this.status = status;
//
//        this.isExpanded = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

//    public boolean isExpanded() {
//        return isExpanded;
//    }
//
//    public void setExpanded(boolean expanded) {
//        isExpanded = expanded;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public boolean isFav() {
//        return isFav;
//    }
//
//    public void setFav(boolean fav) {
//        isFav = fav;
//    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", imgURL='" + imgUrl + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null | getClass() != obj.getClass()) {
            return false;
        }
        Book book = (Book) obj;

        //because the id is unique, so the only para we need to confirm is the id.
        return Objects.equals(id, book.id);
    }
}
