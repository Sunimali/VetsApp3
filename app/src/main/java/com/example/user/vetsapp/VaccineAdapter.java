package com.example.user.vetsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class VaccineAdapter extends ArrayAdapter {
    private List list = new ArrayList();
    public VaccineAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);

    }

    @Override
    public void add(Object object) {
       list.add(object);
        super.add(object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // return super.getView(position, convertView, parent);
        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.text_vaccine);
        TextView textViewdate = (TextView) rowView.findViewById(R.id.text_Date);
        vaccineEntity e = (vaccineEntity) getItem(position);
        textViewName.setText(e.getVaccine());
        textViewdate.setText(e.getDate());
        return rowView;
    }
}
