package com.example.nicolas.lollipopapp.adapters;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nicolas.lollipopapp.ApplicationController;
import com.example.nicolas.lollipopapp.R;
import com.example.nicolas.lollipopapp.interfaces.MyHomeInterface;
import com.example.nicolas.lollipopapp.objecttype.HomeItem;
import com.example.nicolas.lollipopapp.views.MyElevatingButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nicolas on 17/03/2016.
 * (c) Touchnote Ltd., 2015
 */
public class MyAdapter implements ListAdapter {
    private ArrayList<HomeItem>             mItems;
    private MyHomeInterface                 mInterface;

    public void setItems(ArrayList<HomeItem> items) {
        mItems = items;
    }

    public void setInterface(MyHomeInterface i) {
        mInterface = i;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {}

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {}

    @Override
    public int getCount() {
        return (mItems == null ? 0 : mItems.size());
    }

    @Override
    public Object getItem(int position) {
        return (mItems == null ? null : mItems.get(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(ApplicationController.getContext()).inflate(R.layout.list_item, parent, false);
            ItemHolder h = new ItemHolder();
            h.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.mainLayout);
            h.textView = (TextView) convertView.findViewById(R.id.text);
            h.imageView = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(h);
        }
        final ItemHolder holder = (ItemHolder) convertView.getTag();
        final HomeItem item = (HomeItem) getItem(position);

        final View view = convertView;

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterface != null) {
                    mInterface.onItemClicked(item.getResId(), view);
                }
            }
        });

        holder.textView.setText(item.getName());
        Picasso.with(ApplicationController.getContext()).load(item.getResId()).into(holder.imageView);

        return (convertView);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return (mItems == null || mItems.size() == 0);
    }

    public static class ItemHolder {
        public RelativeLayout           relativeLayout;
        public TextView                 textView;
        public ImageView                imageView;
    }
}
