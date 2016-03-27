package com.codingblocks.dreambooks.network;

import com.codingblocks.dreambooks.classes.Book;
import com.codingblocks.dreambooks.classes.ResponseOnSearchByTitle_Author_ISBN;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shakshi on 26-03-2016.
 */
public interface ApiInterface {
    /*@GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);

    @GET("users")
    Call<ArrayList<User>> getAllUsers();

    @GET("posts/")
    Call<ArrayList<Post>> getPostsForUser(@Query("UserId")int userId);

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int postId);

    @GET("comments/")
    Call<ArrayList<Comment>> getCommentsForPost(@Query("postId") int postId);

    @POST("posts")
    Call<Post> createPost(@Body Post post);*/

    @GET("books/reviews.json")
    Call<ResponseOnSearchByTitle_Author_ISBN> getBook(@Query("q") String q,@Query("key") String key);

    @GET("publications/recent_recos.json")
    Call<ArrayList<Book> > getAllRecommendedBooks(@Query("key") String key);

    @GET("publications/recent_recos.json")
    Call<ArrayList<Book> > getRecommendedBooksByGenre(@Query("key") String key,@Query("slug") String slug);


}
