package com.example.nicolas.lollipopapp.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.nicolas.lollipopapp.R;
import com.example.nicolas.lollipopapp.adapters.MyAdapter;
import com.example.nicolas.lollipopapp.interfaces.MyHomeInterface;
import com.example.nicolas.lollipopapp.objecttype.HomeItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyHomeInterface {
    private ListView                    mList;

    private ArrayList<HomeItem>         mItems;
    private MyAdapter                   mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter();
        mAdapter.setInterface(this);
        setItems();

        mList = (ListView) findViewById(R.id.listView);
        if (mList != null) {
            mList.setAdapter(mAdapter);
        }
    }

    private void setItems() {
        mItems = new ArrayList<>();

        mItems.add(new HomeItem("Image1", R.drawable.img01));
        mItems.add(new HomeItem("Image2", R.drawable.img02));
        mItems.add(new HomeItem("Image3", R.drawable.img03));
        mItems.add(new HomeItem("Image4", R.drawable.img04));
        mItems.add(new HomeItem("Image5", R.drawable.img05));
        mItems.add(new HomeItem("Image6", R.drawable.img06));
        mItems.add(new HomeItem("Image7", R.drawable.img07));

        mAdapter.setItems(mItems);
    }

    @Override
    public void onItemClicked(final int imageId, final View view) {
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchSecondActivity(imageId, view);
            }
        }, 150);
    }

    private void launchSecondActivity(int imageId, View view) {
        Intent i = new Intent(this, SecondActivity.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this, view, getString(R.string.transitionImageName)).toBundle();
        i.putExtra(SecondActivity.TAG_IMAGE_ID, imageId);
        startActivity(i, b);
    }
}
