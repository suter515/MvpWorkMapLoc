package com.automic.roomdemo.buraininfo.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.automic.roomdemo.R;
import com.automic.roomdemo.baseparts.BaseMvpActivity;
import com.automic.roomdemo.baseparts.BaseMvpNetActivity;
import com.automic.roomdemo.buraininfo.bean.HStationRainHistory;
import com.automic.roomdemo.buraininfo.presenter.RainHistoryPresenterImpl;
import com.automic.roomdemo.utils.ToastUtils;
import com.bigkoo.pickerview.TimePickerView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 业务一的主页面
 */
public class BusinessOneActivity extends BaseMvpNetActivity<RainHistoryMvpView, RainHistoryPresenterImpl> implements RainHistoryMvpView, AdapterView.OnItemClickListener {

    private ListView mvpListView;
    private ProgressBar pb;
    private TimePickerView pvTime;

    private Context mContext;
    private String dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mContext = BusinessOneActivity.this;
        mvpListView = (ListView) findViewById(R.id.mvp_listview);
        mvpListView.setOnItemClickListener(this);
        pb = (ProgressBar) findViewById(R.id.mvp_loading);
        TextView tvwDate = (TextView) findViewById(R.id.tvw_datepicker_time);
        tvwDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(v);
            }
        });

    }

    /**
     * 此界面的重新加载监听
     */
    private View.OnClickListener onceAgainlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogServerError.dismiss();
            ToastUtils.show(mContext, "重新加载");
            presenter.getRainHistoryDataByDate("10000444", dateNow.substring(0, 7));
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        initDatePicker();

    }

    private void initDatePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        // .setRangDate(startDate, endDate)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件(确认)回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                dateNow = getTime(date);
                ((TextView) v).setText(dateNow);
                dateNow = dateNow.replace('年', '-');
                //showDialog(dialogLoading,true);
                presenter.getRainHistoryDataByDate("10000444", dateNow.substring(0, 7));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("年", "月", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                // .setRangDate(startDate, endDate)
                .setBackgroundId(R.color.pickerview_wheelview_textcolor_out) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(date);
    }

    @Override
    public RainHistoryPresenterImpl initPresenter() {
        presenter = new RainHistoryPresenterImpl(this);
        return presenter;
    }

    @Override
    public View.OnClickListener initListener() {
        return onceAgainlistener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClick(position);
    }

    @Override
    public void setListItem(List<HStationRainHistory> data) {
        HydrologyRainHistoryAdapter adapter = new HydrologyRainHistoryAdapter(BusinessOneActivity.this, data);
        mvpListView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.show(this, message);
        showDialog(dialogLoading,false);
showDialog(dialogServerError,true);
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.GONE);
        showDialog(dialogLoading,true);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
        showDialog(dialogLoading,false);
    }
}
