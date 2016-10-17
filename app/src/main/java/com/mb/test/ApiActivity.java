package com.mb.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mb.test.activity.MainActivity;
import com.mb.test.utils.SharePreHelper;

import java.util.ArrayList;

public class ApiActivity extends AppCompatActivity {

    private ListView mListview;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        mListview = (ListView) findViewById(R.id.listview);
        mList = new ArrayList<>();
        mList.add("http://apicloud.mob.com/v1/");
        mList.add("http://apicloud.mob.com/");
        mListview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, mList));
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharePreHelper.getIns().savePath(mList.get(i));
                startActivity(new Intent(ApiActivity.this, MainActivity.class));
            }
        });
    }
}
