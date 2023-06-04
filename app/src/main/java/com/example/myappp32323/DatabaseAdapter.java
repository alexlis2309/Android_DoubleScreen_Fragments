package com.example.myappp32323;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(ListFragment context) {
        dbHelper = new DatabaseHelper(context.getActivity());
    }

    public DatabaseAdapter open() {
        database = dbHelper.open();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private Cursor getAllEntries() {
        String[] columns = new String[]
                { DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_TITLE, DatabaseHelper.COLUMN_DESCRIPTION, DatabaseHelper.COLUMN_MOREINFO };
        return database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<Article> getArticles() {
        ArrayList<Article> articles = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION));
            @SuppressLint("Range") String moreInfo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MOREINFO));
            articles.add(new Article(id, title, description, moreInfo));
        }
        cursor.close();
        return articles;
    }

    public long getCount() {
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public Article getArticle(long id) {
        Article article = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id) });
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION));
            @SuppressLint("Range") String moreInfo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MOREINFO));

            article = new Article(id, title, description, moreInfo);
        }
        cursor.close();
        return article;
    }

    public long insert(Article article) {

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_TITLE, article.getTitle());
        cv.put(DatabaseHelper.COLUMN_DESCRIPTION, article.getDescription());
        cv.put(DatabaseHelper.COLUMN_MOREINFO, article.getMoreInfo());

        return database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long articleId) {

        String whereClause = " _id = ?";
        String[] whereArgs = new String[]{ String.valueOf(articleId) };
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(Article article) {

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + article.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_TITLE, article.getTitle());
        cv.put(DatabaseHelper.COLUMN_DESCRIPTION, article.getDescription());
        cv.put(DatabaseHelper.COLUMN_MOREINFO, article.getMoreInfo());

        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}

