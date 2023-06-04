package com.example.myappp32323;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    private LayoutInflater inflater;
    private int layout;
    private List<Article> articles;

    public ArticleAdapter(Context context, int resource, List<Article> articles) {
        super(context, resource, articles);
        this.articles = articles;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Article article = articles.get(position);

        viewHolder.titleView.setText(article.getTitle());
        viewHolder.moreInfoView.setText(article.getMoreInfo());

        return convertView;
    }

    private class ViewHolder {
        final TextView titleView, moreInfoView;
        ViewHolder(View view) {
            titleView = view.findViewById(R.id.title);
            moreInfoView = view.findViewById(R.id.moreInfo);
        }
    }
}

