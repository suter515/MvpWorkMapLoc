package com.automic.roomdemo.buworkroom.presenter;

import android.widget.ImageView;

import com.automic.roomdemo.buworkroom.view.ImageMap;

/**
 * 类注释：
 * Created by sujingtai on 2017/7/11 0011 上午 10:12
 */

public interface WorkRoomPresenter {
    void searchPeople(String text);
    void initmap(ImageMap imgv);
}
