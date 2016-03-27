package com.codingblocks.dreambooks.classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shakshi on 26-03-2016.
 */
public class Book {
    String title;
    String author;
    String isbns;

    @SerializedName("review_snippet")
    String reviewSnippet;

    @SerializedName("review_publication_name")
    String reviewPublicationName;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReviewSnippet() {
        return reviewSnippet;
    }
}
