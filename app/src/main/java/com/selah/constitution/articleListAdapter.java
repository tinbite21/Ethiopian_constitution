package com.selah.constitution;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class articleListAdapter extends ArrayAdapter<String> {
Activity context;
String[] articles;
String[] description;
String[] detail;
Integer[] article_images;


    public articleListAdapter(@NonNull Activity context1, String[] articles, String[] description, Integer[] article_images, String[] detail) {
        super(context1, R.layout.article_list_layout,articles);
        this.context = context1;
        this.articles = articles;
        this.description = description;
        this.detail = detail;
        this.article_images = article_images;
    }
// create getView method

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.article_list_layout,null,true);

        ImageView articles_image = row.findViewById(R.id.articles_image);
        TextView articles_name = row.findViewById(R.id.article_name);
        TextView articles_description = row.findViewById(R.id.article_description);

        articles_name.setText(articles[position]);
        articles_description.setText(description[position]);
        articles_image.setImageResource(article_images[position]);
        return row;
 }
}
