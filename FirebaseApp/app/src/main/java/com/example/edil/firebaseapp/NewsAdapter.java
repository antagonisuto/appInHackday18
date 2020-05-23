package com.example.edil.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] title;
    String[] content;

    public NewsAdapter(Context context, String[] c, String[] a){
        title = c;
        content = a;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return title[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.news_list,null);
        TextView characterTextView = (TextView) v.findViewById(R.id.title);
        TextView ageTextView = (TextView) v.findViewById(R.id.content);

        String name = title[position];
        String price = content[position];

        characterTextView.setText(name);
        ageTextView.setText(price);


        return v;
    }
}
