package com.codingblocks.dreambooks.classes;

import java.util.ArrayList;

/**
 * Created by shakshi on 26-03-2016.
 */
public class BookWithReview {
    String title;
    String author;
    int rating;
    String genre;
    int pages;
    ArrayList<CriticReview> critic_reviews;

    public String getTitle(){
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getNoOfCriticalReviews(){
        return critic_reviews.size();
    }
}
