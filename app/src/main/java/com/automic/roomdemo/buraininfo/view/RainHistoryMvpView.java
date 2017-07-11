package com.automic.roomdemo.buraininfo.view;

import com.automic.roomdemo.baseparts.BaseView;
import com.automic.roomdemo.buraininfo.bean.HStationRainHistory;

import java.util.List;




public interface RainHistoryMvpView extends BaseView {
    void setListItem(List<HStationRainHistory> data);
    void showMessage(String message);

}
