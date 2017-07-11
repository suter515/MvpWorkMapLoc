package com.automic.roomdemo.buraininfo.model;

import com.automic.roomdemo.buraininfo.bean.HStationRainHistory;
import com.automic.roomdemo.buraininfo.bean.RainHistoryRespond;
import com.automic.roomdemo.http.ObjectLoader;
import com.automic.roomdemo.http.RetrofitServiceManager;

import java.util.List;


import rx.Observable;
import rx.functions.Func1;

/**
 * 类注释：雨量历史loader
 * Created by sujingtai on 2017/6/20 0020 下午 3:25
 */

public class RainStHistoryLoader extends ObjectLoader {
    private RainStationInfoService mRainHistoryService;
    public RainStHistoryLoader() {
        mRainHistoryService= RetrofitServiceManager.getInstance().create(RainStationInfoService.class);
    }
    public Observable<List<HStationRainHistory>> getRainHistory(String stcd, String queryDate){
        return  observe(mRainHistoryService.getAllAreaInfo(stcd,queryDate))
                .map(new Func1<RainHistoryRespond,List<HStationRainHistory>>() {

                    @Override
                    public List<HStationRainHistory> call(RainHistoryRespond rainHistoryRespond) {
                        return rainHistoryRespond.result;
                    }
                });
    }
}
