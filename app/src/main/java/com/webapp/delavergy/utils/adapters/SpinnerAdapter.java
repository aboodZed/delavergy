package com.webapp.delavergy.utils.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.webapp.delavergy.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter implements android.widget.SpinnerAdapter {

    ArrayList<String> items;
    Context context;

    public SpinnerAdapter(Context context, ArrayList<String> strings) {
        this.context = context;
        items = strings;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_spinner, null);
        TextView textView = view.findViewById(R.id.tv_text);
        textView.setText(items.get(position));
        return view;
    }
}
