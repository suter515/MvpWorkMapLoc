package com.automic.roomdemo.buworkroom.view;

import android.widget.ImageView;

import com.automic.roomdemo.baseparts.BaseView;

/**
 * 类注释：
 * Created by sujingtai on 2017/7/11 0011 上午 10:18
 */

public interface WorkRoomView  {
    void showDialog(String message);
    void showMapInfo(int id,String content);
    void showMapInfo(int id);
}
