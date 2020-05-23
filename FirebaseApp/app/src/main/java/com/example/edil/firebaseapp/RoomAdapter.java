package com.example.edil.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class RoomAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] names;
    String[] prices;
    String[] descriptions;

    public RoomAdapter(Context context,String[] c,String[] a,String[] d){
        names = c;
        prices = a;
        descriptions = d;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.my_listview_detalis,null);
        TextView characterTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView ageTextView = (TextView) v.findViewById(R.id.priceTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);

        String name = names[position];
        String price = prices[position];
        String description = descriptions[position];

        characterTextView.setText(name);
        ageTextView.setText(price);
        descriptionTextView.setText(description);

        return v;
    }
}
