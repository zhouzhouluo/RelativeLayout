package com.example.refreshlistview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.refreshlistview.RefreshLayout.OnLoadListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author mrsimple
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.refresh);

        // ģ��һЩ����
        final List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("item - " + i);
        }

        // ����������
        final BaseAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                datas);
        // ��ȡlistviewʵ��
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        // ��ȡRefreshLayoutʵ��
        final RefreshLayout myRefreshListView = (RefreshLayout)
                findViewById(R.id.swipe_layout);

        // ��������ˢ��ʱ����ɫֵ,��ɫֵ��Ҫ������xml��
        myRefreshListView.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
                android.R.color.holo_orange_light, android.R.color.holo_red_light); 
        // ��������ˢ�¼�����
        myRefreshListView.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {

                Toast.makeText(MainActivity.this, "refresh", Toast.LENGTH_SHORT).show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // ��������
                        datas.add(new Date().toGMTString());
                        adapter.notifyDataSetChanged();
                        // ���������ø÷�������ˢ��
                        myRefreshListView.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        // ���ؼ�����
        myRefreshListView.setOnLoadListener(new OnLoadListener() {

            @Override
            public void onLoad() {

                Toast.makeText(MainActivity.this, "load", Toast.LENGTH_SHORT).show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        datas.add(new Date().toGMTString());
                        adapter.notifyDataSetChanged();
                        // ���������ø÷���
                        myRefreshListView.setLoading(false);
                    }
                }, 1500);

            }
        });
    }

    }