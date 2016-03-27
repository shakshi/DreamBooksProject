package com.codingblocks.dreambooks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codingblocks.dreambooks.R;
import com.codingblocks.dreambooks.classes.BookWithReview;
import com.codingblocks.dreambooks.classes.ResponseOnSearchByTitle_Author_ISBN;
import com.codingblocks.dreambooks.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editTextView;
    ImageView searchButton;
    String key="368649633e5ac1633032dd112edded4a0415ef41";

    TextView bookTitleTextView,bookAuthorTextView,noOfReviewsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextView= (EditText) findViewById(R.id.editTextView);
        searchButton= (ImageView)findViewById(R.id.searchButton);
        searchButton.setImageResource(android.R.drawable.ic_menu_search);

        bookTitleTextView=(TextView) findViewById(R.id.bookTitleTextView);
        bookAuthorTextView=(TextView) findViewById(R.id.bookAuthorTextView);
        noOfReviewsTextView=(TextView) findViewById(R.id.noOfReviewsTextView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q=editTextView.getEditableText().toString();
                Call bookCall= ApiClient.getInterface().getBook(q,key);
                bookCall.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.isSuccessful()){
                            ResponseOnSearchByTitle_Author_ISBN r= (ResponseOnSearchByTitle_Author_ISBN) response.body();
                            Log.i("Respose code",response.message()+response.code());
                            Log.i("total results",r.getTotal_results().toString());

                            if(r.getTotal_results()>0){
                                BookWithReview b=r.getBookWithReview();
                                Log.i("book ", b.getTitle());
                                bookTitleTextView.setText(b.getTitle());

                                bookAuthorTextView.setText(b.getAuthor());

                                noOfReviewsTextView.setText(b.getNoOfCriticalReviews().toString());

                            }
                            else{
                                Toast.makeText(MainActivity.this,"Sorry..No books found corresponding to your search",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,response.message()+response.code(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.topRecommended){
            Intent i=getIntent();
            i.setClass(MainActivity.this,TopRecommendedActivity.class);
            startActivity(i);
        }
        return true;
    }
}
