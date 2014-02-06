package com.example.datax;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

import data.Comment;
import data.CommentsSQLiteHelperDeluxe;

public class MainActivity extends ListActivity {
    public CommentsSQLiteHelperDeluxe mCommentsSQLiteHelperDeluxe;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCommentsSQLiteHelperDeluxe = new CommentsSQLiteHelperDeluxe(this);
        mCommentsSQLiteHelperDeluxe.open();

        List<Comment> values = mCommentsSQLiteHelperDeluxe.getAllComments();

        // use the SimpleCursorAdapter to show the elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }


    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()) {
            case R.id.add:
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                comment = mCommentsSQLiteHelperDeluxe.createComment(comments[nextInt]);
                adapter.add(comment);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    mCommentsSQLiteHelperDeluxe.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        mCommentsSQLiteHelperDeluxe.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mCommentsSQLiteHelperDeluxe.close();
        super.onPause();
    }

}
