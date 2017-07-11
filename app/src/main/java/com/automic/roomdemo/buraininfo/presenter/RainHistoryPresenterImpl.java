package com.automic.roomdemo.buraininfo.presenter;


import android.os.Handler;
import android.os.Looper;

import com.automic.roomdemo.baseparts.NewBasePresenter;
import com.automic.roomdemo.buraininfo.bean.HStationRainHistory;
import com.automic.roomdemo.buraininfo.model.RainHistoryInteractor;
import com.automic.roomdemo.buraininfo.model.RainHistoryInteractorImpl;
import com.automic.roomdemo.buraininfo.view.RainHistoryMvpView;
import com.automic.roomdemo.utils.LogUtils;

import java.util.List;


public class RainHistoryPresenterImpl extends NewBasePresenter<RainHistoryMvpView> implements RainHistoryPresenter,RainHistoryInteractor.OnRainHistoryFinishedListener {

    private RainHistoryInteractor rainHistoryInteractor;
    private Handler mHandler;
    private RainHistoryMvpView mvpView;

    public RainHistoryPresenterImpl(RainHistoryMvpView mvpView) {
        this.rainHistoryInteractor = new RainHistoryInteractorImpl();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mvpView = mvpView;
    }
    @Override
    public void onSuccess(final List<HStationRainHistory> data) {
        LogUtils.e("sjt","当前线程"+Thread.currentThread().getName());
        LogUtils.e("sjt","数据个数"+data.size());
                LogUtils.e("sjt","当前线程"+Thread.currentThread().getName());
                mvpView.hideLoading();
        mvpView.setListItem(data);
    }

    @Override
    public void onFailed() {
        mvpView.showMessage("请求失败");
    }

    @Override
    public void onItemClick(int position) {

        mvpView.showMessage("点击了item" + position);
    }

    @Override
    public void getRainHistoryDataByDate(String stcd,String date) {
        mvpView.showLoading();
        rainHistoryInteractor.requestRainHistoryData(stcd,date,RainHistoryPresenterImpl.this);
    }

}
