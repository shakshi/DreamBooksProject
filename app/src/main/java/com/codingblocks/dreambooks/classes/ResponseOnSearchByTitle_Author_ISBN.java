package com.codingblocks.dreambooks.classes;

/**
 * Created by shakshi on 26-03-2016.
 */
public class ResponseOnSearchByTitle_Author_ISBN {
    int total_results;
    BookWithReview book;

    public BookWithReview getBookWithReview(){
        return book;
    }
    public Integer getTotal_results(){
        return total_results;
    }
}
