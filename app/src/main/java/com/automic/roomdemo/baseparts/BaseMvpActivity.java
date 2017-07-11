package com.automic.roomdemo.baseparts;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import com.automic.roomdemo.R;
import com.automic.roomdemo.views.CustomProgressDialog;
import com.automic.roomdemo.views.NoNetDialog;

import java.lang.reflect.Method;

public abstract class BaseMvpActivity<V ,T extends NewBasePresenter<V>> extends AppCompatActivity {

    public T presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this,(V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }
    public abstract T initPresenter();
}
