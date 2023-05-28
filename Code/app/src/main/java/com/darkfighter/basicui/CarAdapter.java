package com.darkfighter.basicui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp-pc on 14-09-2016.
 */
public class CarAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public CarAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Cars object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        ContactHolder contactHolder;
        if(row==null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.tx_car_no = (TextView)row.findViewById(R.id.tx_car_no);
            contactHolder.tx_car_name = (TextView)row.findViewById(R.id.tx_car_name);
            row.setTag(contactHolder);

        }
        else {
            contactHolder = (ContactHolder)row.getTag();
        }
        Cars contacts = (Cars) this.getItem(position);
        contactHolder.tx_car_no.setText(contacts.getCar_no());
        contactHolder.tx_car_name.setText(contacts.getCar_name());

        return row;
    }

    static class ContactHolder {
        TextView tx_car_no,tx_car_name;
    }
}
