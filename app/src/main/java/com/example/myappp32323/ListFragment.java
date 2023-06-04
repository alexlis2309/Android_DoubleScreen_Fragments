package com.example.myappp32323;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class ListFragment extends Fragment {

    ListView articlesList;
    ArrayAdapter<Article> arrayAdapter;
    DatabaseHelper databaseHelper;

    interface OnFragmentSendDataListener {
        void onSendData(long articleId);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context
            + "должен реализовывать интерфейс OnFragmentSendDataListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.create_db();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        articlesList = view.findViewById(R.id.articlesList);

        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<Article> articles = adapter.getArticles();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, articles);
        articlesList.setAdapter(arrayAdapter);

        articlesList.setOnItemClickListener((parent, view1, position, id) -> {
            Article selectedItem = (Article) parent.getItemAtPosition(position);
            fragmentSendDataListener.onSendData(selectedItem.getId());
        });

        adapter.close();
        return view;
    }
}
