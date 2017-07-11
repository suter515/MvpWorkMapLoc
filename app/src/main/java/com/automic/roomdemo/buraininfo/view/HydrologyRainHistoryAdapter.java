package com.automic.roomdemo.buraininfo.view;

import android.content.Context;
import android.widget.TextView;

import com.automic.roomdemo.R;
import com.automic.roomdemo.baseparts.BaseTtAdapter;
import com.automic.roomdemo.buraininfo.bean.HStationRainHistory;

import java.util.List;


/**
 * 类注释：雨量测站历史记录adapter
 * Created by sujingtai on 2017/6/2 0002 下午 4:58
 */

public class HydrologyRainHistoryAdapter extends BaseTtAdapter<HStationRainHistory> {
    private Context mContext;

    public HydrologyRainHistoryAdapter(Context context, List<HStationRainHistory> mList) {
        super(context, mList);
        this.mContext = context;
        this.setLayoutId(R.layout.item_two_textview);
        int[] ids = {R.id.tvw_item_date, R.id.tvw_item_value};
        String fields[] = {"tvwDate", "tvwValue"};
        this.setClass(ViewHolderRainHistory.class);
        this.setViewIds(ids);
        this.setFields(fields);
    }

    @Override
    public void addDataToView(HStationRainHistory hStationRainHistory, Object o) {
        if (o instanceof ViewHolderRainHistory) {
            ((ViewHolderRainHistory) o).tvwDate.setText(hStationRainHistory.getDateDay());
            ((ViewHolderRainHistory) o).tvwValue.setText(String.valueOf(hStationRainHistory.getCountDay()));
        }
    }

    static class ViewHolderRainHistory {
        TextView tvwDate;
        TextView tvwValue;
    }
}
