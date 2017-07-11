package com.automic.roomdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.automic.roomdemo.R;
import com.automic.roomdemo.buraininfo.view.BusinessOneActivity;
import com.automic.roomdemo.buworkroom.view.ImageMapTestActivity;
import com.automic.roomdemo.utils.NetWorkUtils;
import com.automic.roomdemo.utils.ToastUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private int netState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        setupView();
    }

    private void setupView() {
        Button btnBusinessOne = (Button) findViewById(R.id.btn_business_one);
        btnBusinessOne.setOnClickListener(this);
        Button btnBusinessTwo = (Button) findViewById(R.id.btn_business_two);
        btnBusinessTwo.setOnClickListener(this);
        Button btnBusinessThree = (Button) findViewById(R.id.btn_business_three);
        btnBusinessThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_business_one:
                netState = NetWorkUtils.checkNetworkType(mContext);
                if (netState != NetWorkUtils.TYPE_NET_WORK_DISABLED) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, BusinessOneActivity.class);
                    startActivity(intent);
                } else {
                    ToastUtils.show(MainActivity.this, "当前无网络连接，请设置网络");
                }
                break;
            case R.id.btn_business_two:
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, ImageMapTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
