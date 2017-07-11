package com.automic.roomdemo.buraininfo.model;

import com.automic.roomdemo.buraininfo.bean.HStationRainHistory;

import java.security.PrivateKey;
import java.util.List;

/**
 * 类注释：雨量数据拦截器
 * Created by sujingtai on 2017/6/21 0021 下午 4:01
 */

public interface RainHistoryInteractor {
     interface OnRainHistoryFinishedListener {

        void onSuccess(List<HStationRainHistory> data);
        void onFailed();
    }
   void requestRainHistoryData(String stcd, String date, OnRainHistoryFinishedListener listener);
}
