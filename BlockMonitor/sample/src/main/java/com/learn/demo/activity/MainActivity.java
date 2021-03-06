package com.learn.demo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.learn.demo.R;
import com.learn.demo.adapter.HomeRecyclerAdapter;
import com.learn.demo.base.BaseActivity;
import com.learn.demo.model.HomeModel;

import java.util.Arrays;


public class MainActivity extends BaseActivity {
    private HomeRecyclerAdapter mAdapter;

    // private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.algorithm_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeRecyclerAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        HomeModel model = new HomeModel();
        model.titleArray = Arrays.asList(getResources().getStringArray(R.array.algorithm_list));
        mAdapter.setData(model);
     /*   handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this,1000);
            }
        });*/

    }

    @Override
    protected void onStop() {
        super.onStop();
        // BlockMonitor.getInstance().stop();
    }
}
