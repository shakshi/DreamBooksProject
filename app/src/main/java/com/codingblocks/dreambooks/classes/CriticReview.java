package com.codingblocks.dreambooks.classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shakshi on 26-03-2016.
 */
public class CriticReview {
    String  snippet;
    String source;

    @SerializedName("star_rating")
    double starRating;

    @SerializedName("source_logo")
    String sourceLogo;


}
