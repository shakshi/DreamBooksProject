package com.codingblocks.dreambooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.codingblocks.dreambooks.R;
import com.codingblocks.dreambooks.adapters.BooksListAdapter;
import com.codingblocks.dreambooks.classes.Book;
import com.codingblocks.dreambooks.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRecommendedActivity extends AppCompatActivity {

    ArrayList<Book> recommendedBooks;
    final String key="368649633e5ac1633032dd112edded4a0415ef41";

    ListView lv;
    BooksListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_recommended);

        recommendedBooks=new ArrayList<>();
        lv= (ListView) findViewById(R.id.recommendedBooksListView);

        Call<ArrayList<Book>> getAllRecommendedBooksCall= ApiClient.getInterface().getAllRecommendedBooks(key);

        Log.i("call object","made");
        getAllRecommendedBooksCall.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Book> books = response.body();

                    Log.i("response", response.message() + response.code());
                    Log.i("no of recommended books", String.valueOf(books.size()));
                    for (Integer i = 0; i < books.size(); i++) {
                        recommendedBooks.add(books.get(i));
                        Log.i(i.toString(), recommendedBooks.get(i).getTitle());

                    }
                    adapter=new BooksListAdapter(TopRecommendedActivity.this,recommendedBooks);
                    lv.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(TopRecommendedActivity.this, response.message() + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Log.i("in failure", "kmk");

            }
        });

        // Spinner Drop down elements
        ArrayList<String> genres = new ArrayList<>();
        genres.add("all-books");
        genres.add("fiction");
        genres.add("non-fiction");
        genres.add("action-adventure");
        genres.add("arts-photography");
        genres.add("biographies-memoirs");
        genres.add("business-economics");
        genres.add("children-s-books");
        genres.add("comics-graphic-novels");
        genres.add("computers-technology");
        genres.add("cooking");
        genres.add("crafts-hobbies-home");
        genres.add("crime");
        genres.add("current-affairs");
        genres.add("education-reference");
        genres.add("erotica");
        genres.add("gay-lesbian");
        genres.add("health-fitness-dieting");
        genres.add("history");
        genres.add("horror");
        genres.add("humor-entertainment");
        genres.add("law-philosophy");
        genres.add("literature-fiction");
        genres.add("mystery-thriller-suspense");
        genres.add("nature-wildlife");
        genres.add("other");
        genres.add("parenting-relationships");
        genres.add("mystery-thriller-suspense");
        genres.add("political-social-sciences");
        genres.add("professional-technical");
        genres.add("religion-spirituality");
        genres.add("romance");
        genres.add("science-math");
        genres.add("science-fiction-fantasy");
        genres.add("self-help");
        genres.add("sports-outdoors");
        genres.add("travel");
        genres.add("war");
        genres.add("westerns");
        genres.add("young-adult");


        Spinner spinner = (Spinner) findViewById(R.id.genreSpinner);         // Create an ArrayAdapter using the string array and a default spinner layout
        spinner.setPrompt("Genres");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genres);    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);             // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String selectedGenre = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + selectedGenre, Toast.LENGTH_LONG).show();

                Call<ArrayList<Book>> getRecommendedBooksByGenreCall = ApiClient.getInterface().getRecommendedBooksByGenre(key, selectedGenre);
                getRecommendedBooksByGenreCall.enqueue(new Callback<ArrayList<Book>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Book> books = response.body();

                            Log.i("response", response.message() + response.code());
                            Log.i("no of recommended books", String.valueOf(books.size()));
                            recommendedBooks.clear();
                            for (Integer i = 0; i < books.size(); i++) {
                                recommendedBooks.add(books.get(i));
                                Log.i(i.toString(), books.get(i).getTitle());

                            }
                            adapter.notifyDataSetChanged();


                        } else {
                            Toast.makeText(TopRecommendedActivity.this, response.message() + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                        Log.i("in failure", "kmk");
                        Log.i("error", t.getCause().toString());

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
