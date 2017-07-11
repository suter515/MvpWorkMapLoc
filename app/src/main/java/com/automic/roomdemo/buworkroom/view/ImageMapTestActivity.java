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


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.automic.roomdemo.R;
import com.automic.roomdemo.baseparts.BaseMvpActivity;
import com.automic.roomdemo.buworkroom.presenter.WorkRoomPresenterImpl;


public class ImageMapTestActivity extends BaseMvpActivity<WorkRoomView, WorkRoomPresenterImpl> implements WorkRoomView, View.OnClickListener {
    ImageMap mImageMap;

    private EditText edtName;
    private Button btnSearch;
    private EditText edtSex;
    private Button btnCancle;
    private RelativeLayout rlSearchMap;
    private ImageButton imgbtnSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_map);
        //搜索视图
        rlSearchMap = (RelativeLayout)findViewById(R.id.rl_search_map);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtSex = (EditText) findViewById(R.id.edt_sex);
        imgbtnSearch = (ImageButton)findViewById(R.id.imgbtn_search);
        imgbtnSearch.setOnClickListener(this);
        btnCancle=(Button)findViewById(R.id.btn_cancle);
        btnCancle.setOnClickListener(this);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
        mImageMap = (ImageMap) findViewById(R.id.map);
        mImageMap.setImageResource(R.drawable.automic2);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initmap(mImageMap);
    }

    @Override
    public WorkRoomPresenterImpl initPresenter() {
        presenter = new WorkRoomPresenterImpl(ImageMapTestActivity.this, this);
        return presenter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                if (edtName.getText().toString() != null) {
                    presenter.searchPeople(edtName.getText().toString());
                }
                rlSearchMap.setVisibility(View.GONE);
                imgbtnSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.imgbtn_search:
                rlSearchMap.setVisibility(View.VISIBLE);
                imgbtnSearch.setVisibility(View.GONE);
                break;
            case R.id.btn_cancle:
                rlSearchMap.setVisibility(View.GONE);
                imgbtnSearch.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void showDialog(String message) {

    }

    @Override
    public void showMapInfo(int id, String content) {
        mImageMap.showBubble(id, content);
    }

    @Override
    public void showMapInfo(int id) {
        mImageMap.showBubble(id);
    }


}