package com.example.myappp32323;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    TextView title, moreInfo;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void setSelectedItem(long selectedItem) {
        title = getView().findViewById(R.id.title);
        moreInfo = getView().findViewById(R.id.moreInfo);

        sqlHelper = new DatabaseHelper(getActivity());
        db = sqlHelper.open();

        if (selectedItem > 0) {
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(selectedItem)});
            userCursor.moveToFirst();
            title.setText(userCursor.getString(1));
            moreInfo.setText(userCursor.getString(3));
            moreInfo.setMovementMethod(new ScrollingMovementMethod());
            userCursor.close();
        }
    }

}
