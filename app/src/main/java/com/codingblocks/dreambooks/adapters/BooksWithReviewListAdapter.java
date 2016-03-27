package com.codingblocks.dreambooks.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingblocks.dreambooks.R;
import com.codingblocks.dreambooks.classes.Book;
import com.codingblocks.dreambooks.classes.BookWithReview;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shakshi on 27-03-2016.
 */
public class BooksWithReviewListAdapter extends ArrayAdapter<BookWithReview> {
    Context context;
    ArrayList<BookWithReview> books;

    public BooksWithReviewListAdapter(Context context, ArrayList<BookWithReview> objects) {
        super(context, 0, objects);
        this.context = context;
        this.books = objects;
    }

    static class ViewHolder {
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookCriticReviews;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.bookwithreview_list_item, null);
            ViewHolder vh = new ViewHolder();
            vh.bookTitle = (TextView) convertView.findViewById(R.id.bookWithReviewListItemTitle);
            vh.bookAuthor = (TextView) convertView.findViewById(R.id.bookWithReviewListItemAuthor);
            vh.bookCriticReviews = (TextView) convertView.findViewById(R.id.bookWithReviewListItemCritics);

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.bookTitle.setText(books.get(position).getTitle());
        vh.bookAuthor.setText(books.get(position).getAuthor());
        vh.bookCriticReviews.setText(books.get(position).getNoOfCriticalReviews() + " Critic Reviews");


        return convertView;

    }
}