package com.automic.roomdemo.buworkroom.presenter;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.automic.roomdemo.R;
import com.automic.roomdemo.application.AppContext;
import com.automic.roomdemo.baseparts.NewBasePresenter;
import com.automic.roomdemo.baseparts.dao.EmployeesWorkStationDao;
import com.automic.roomdemo.buworkroom.bean.EmployeesWorkStation;
import com.automic.roomdemo.buworkroom.model.ExtraDbManager;
import com.automic.roomdemo.buworkroom.view.ImageMap;
import com.automic.roomdemo.buworkroom.view.ImageMapTestActivity;
import com.automic.roomdemo.buworkroom.view.WorkRoomView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 类注释：
 * Created by sujingtai on 2017/7/11 0011 上午 10:15
 */

public class WorkRoomPresenterImpl extends NewBasePresenter<WorkRoomView> implements WorkRoomPresenter {
    private final Handler mHandler;
    private WorkRoomView workRoomView;
    private Context mContext;
    private List<EmployeesWorkStation> mList;

    public WorkRoomPresenterImpl(Context context, WorkRoomView workRoomView) {
        this.mContext = context;
        this.workRoomView = workRoomView;
        this.mHandler = new Handler(Looper.getMainLooper());
        //manager自动读取数据到数据库
        ExtraDbManager manager = ExtraDbManager.getmDbManager(context);
    }

    @Override
    public void searchPeople(String text) {
        boolean loading = false;
        mList = AppContext.getDaoInstant().getEmployeesWorkStationDao().queryBuilder().where(EmployeesWorkStationDao.Properties.Name.like(text)).list();
        if (mList.size() != 0) {
            String workStationId = mList.get(0).getWorkStaionId();
            //String content = " " + mList.get(0).getName() + " \n " + mList.get(0).getSection()+" \n"+mList.get(0).getWorkStaionId();
            String content = mList.get(0).getName() + " " + mList.get(0).getSection();
            Log.e("sjt", "shuju===" + content);
            int _id = 0;
            try {
                Class<R.id> res = R.id.class;
                Field field = res.getField(workStationId);
                _id = field.getInt(null);
            } catch (Exception e) {
                _id = 0;
            }
            workRoomView.showMapInfo(_id,content);


//            // mImageMap.showBubble(id, content);
//            //拿到name，从xml解析，然后，查到coods，再绘制
//            try {
//                XmlResourceParser xpp = mContext.getResources().getXml(R.xml.maps);
//                int eventType = xpp.getEventType();
//                while (eventType != XmlPullParser.END_DOCUMENT) {
//                    if (eventType == XmlPullParser.START_DOCUMENT) {
//                        // Start document
//                        //  This is a useful branch for a debug log if
//                        //  parsing is not working
//                    } else if (eventType == XmlPullParser.START_TAG) {
//                        String tag = xpp.getName();
//
//                        if (tag.equalsIgnoreCase("map")) {
//                            String mapname = xpp.getAttributeValue(null, "name");
//                            if (mapname != null) {
//                                if (mapname.equalsIgnoreCase("automic")) {
//                               loading=true;
//                                }
//                            }
//                        }
//                        if (loading){
//                            if (tag.equalsIgnoreCase("area")) {
//                                //String shape = xpp.getAttributeValue(null, "shape");
//                                String coords = xpp.getAttributeValue(null, "coords");
//                                String name = xpp.getAttributeValue(null, "name");
//                                if (name!=null&&coords!=null){
//                                    if (name.equals(workStationId)) {
//                                        Log.e("sjt", "获得coords" + coords);
//                                    }
//                                }
//
//                            }
//                        }
//                    }else if (eventType == XmlPullParser.END_TAG) {
//                        String tag = xpp.getName();
//                        if (tag.equalsIgnoreCase("map")) {
//                            loading = false;
//                        }
//                    }
//                    eventType = xpp.next();
//                }
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Override
    public void initmap(final ImageMap mImageMap) {
        // add a click handler to react when areas are tapped
        mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {
            @Override
            public void onImageMapClicked(int id, ImageMap imageMap) {
                String areaCode = mImageMap.getAreaCodeById(id);
                mList = AppContext.getDaoInstant().getEmployeesWorkStationDao().queryBuilder().where(EmployeesWorkStationDao.Properties.WorkStaionId.eq(areaCode)).list();
                if (mList.size() != 0) {
                    String content = " " + mList.get(0).getName() + " \n " + mList.get(0).getSection();
                    workRoomView.showMapInfo(id, content);
                } else
                    workRoomView.showMapInfo(id);
            }

            @Override
            public void onBubbleClicked(int id) {
                //Toast.makeText(ImageMapTestActivity.this, "hahah" + id, Toast.LENGTH_LONG);

            }
        });
    }
}
