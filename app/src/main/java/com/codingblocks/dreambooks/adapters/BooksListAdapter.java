package com.codingblocks.dreambooks.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codingblocks.dreambooks.R;
import com.codingblocks.dreambooks.classes.Book;
import com.codingblocks.dreambooks.classes.BookWithReview;

import java.util.ArrayList;

/**
 * Created by shakshi on 27-03-2016.
 */

/**
 * Created by shakshi on 27-03-2016.
 */
public class BooksListAdapter extends ArrayAdapter<Book> {
    Context context;
    ArrayList<Book> books;

    public BooksListAdapter(Context context, ArrayList<Book> objects) {
        super(context, 0, objects);
        this.context = context;
        this.books = objects;
    }

    static class ViewHolder {
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookReviewSnippet;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.book_list_item, null);
            ViewHolder vh = new ViewHolder();
            vh.bookTitle = (TextView) convertView.findViewById(R.id.bookListItemTitle);
            vh.bookAuthor = (TextView) convertView.findViewById(R.id.bookListItemAuthor);
            vh.bookReviewSnippet = (TextView) convertView.findViewById(R.id.bookListItemSnippet);

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.bookTitle.setText("Title : "+ books.get(position).getTitle());
        vh.bookAuthor.setText("Author : "+ books.get(position).getAuthor());
        vh.bookReviewSnippet.setText("Review Snippet :" + books.get(position).getReviewSnippet());

        return convertView;
    }
}

