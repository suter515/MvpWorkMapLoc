/*
 * Copyright (C) 2011 Scott Lund
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.automic.roomdemo.buworkroom.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.automic.roomdemo.R;
import com.automic.roomdemo.application.AppContext;
import com.automic.roomdemo.baseparts.dao.EmployeesWorkStationDao;
import com.automic.roomdemo.buworkroom.bean.EmployeesWorkStation;
import com.automic.roomdemo.buworkroom.model.ExtraDbManager;

import java.util.List;

public class ImageMapTestActivity extends Activity implements View.OnClickListener{
    ImageMap mImageMap;
    private List<EmployeesWorkStation> mList;
    private EditText edtSearch;
    private Button btnSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        edtSearch = (EditText) findViewById(R.id.edt_search);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
        mImageMap = (ImageMap) findViewById(R.id.map);
        //initMap();
        //manager自动读取数据到数据库
        ExtraDbManager manager = ExtraDbManager.getmDbManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMap();
    }

    private void initMap() {
        //mImageMap.setImageResource(R.drawable.usamap);
        mImageMap.setImageResource(R.drawable.automic2);

        // add a click handler to react when areas are tapped
        mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {
            @Override
            public void onImageMapClicked(int id, ImageMap imageMap) {
                String areaCode = mImageMap.getAreaCodeById(id);
                mList = AppContext.getDaoInstant().getEmployeesWorkStationDao().queryBuilder().where(EmployeesWorkStationDao.Properties.WorkStaionId.eq(areaCode)).list();
                if (mList.size() != 0) {
                    String content = " " + mList.get(0).getName() + " \n " + mList.get(0).getSection();
                    mImageMap.showBubble(id, content);
                } else


                    // when the area is tapped, show the name in a
                    // text bubble
                    mImageMap.showBubble(id);
            }

            @Override
            public void onBubbleClicked(int id) {
                Toast.makeText(ImageMapTestActivity.this, "hahah" + id, Toast.LENGTH_LONG);
                // react to info bubble for area being tapped
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                if (edtSearch.getText().toString()!=null){
                    queryMan(edtSearch.getText().toString());
                }

                break;

        }
    }

    private void queryMan(String text) {
        mList = AppContext.getDaoInstant().getEmployeesWorkStationDao().queryBuilder().where(EmployeesWorkStationDao.Properties.Name.like(text)).list();
        if (mList.size() != 0) {
            String content = " " + mList.get(0).getName() + " \n " + mList.get(0).getSection()+" \n"+mList.get(0).getWorkStaionId();
            Log.e("sjt","shuju==="+content);
           // mImageMap.showBubble(id, content);
            //拿到name，从xml解析，然后，查到coods，再绘制
        }
    }
}