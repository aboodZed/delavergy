package com.webapp.delavergy.utils.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.utils.UIUtils;

import java.util.ArrayList;

public class DateSpinnerAdapter extends BaseAdapter implements android.widget.SpinnerAdapter {

   private ArrayList<Long> items;
   private Context context;

    public DateSpinnerAdapter(Context context, ArrayList<Long> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = View.inflate(context, R.layout.item_spinner, null);
        TextView textView = view.findViewById(R.id.tv_text);
        textView.setText(UIUtils.getDate(items.get(i)));
        return view;
    }
}
