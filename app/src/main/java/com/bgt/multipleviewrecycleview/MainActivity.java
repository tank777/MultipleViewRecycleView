package com.bgt.multipleviewrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bgt.multipleviewrecycleview.adapter.FeedsAdapter;
import com.bgt.multipleviewrecycleview.model.FeedData;
import com.bgt.multipleviewrecycleview.model.FeedsImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    FeedsAdapter feedsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recycleView.setLayoutManager(linearLayoutManager);
        List<FeedsImage> feedsImages = new ArrayList<>();

        FeedsImage feedsImage = new FeedsImage();
        feedsImage.setId(1);
        feedsImage.setUrl("https://cdn.pixabay.com/photo/2017/01/06/19/15/soap-bubble-1958650_960_720.jpg");

        feedsImages.add(feedsImage);

        FeedsImage feedsImage1 = new FeedsImage();
        feedsImage1.setId(2);
        feedsImage1.setUrl("https://cdn.pixabay.com/photo/2017/01/07/22/45/soap-bubble-1961790__340.jpg");

        feedsImages.add(feedsImage1);

        List<FeedsImage> feedsImages1 = new ArrayList<>();
        feedsImages1.add(feedsImage);

        List<FeedData> feedDatas = new ArrayList<>();

        FeedData feedData = new FeedData();
        feedData.setId(1);
        feedData.setImages(feedsImages);

        FeedData feedData1 = new FeedData();
        feedData1.setId(2);
        feedData1.setImages(feedsImages1);

        feedDatas.add(feedData);
        feedDatas.add(feedData1);

        feedsAdapter = new FeedsAdapter(MainActivity.this);
        recycleView.setAdapter(feedsAdapter);

        feedsAdapter.setNews(feedDatas);
    }
}
