package com.automic.roomdemo.buworkroom.model;

import android.content.Context;


import com.automic.roomdemo.application.AppContext;
import com.automic.roomdemo.buworkroom.bean.EmployeesWorkStation;
import com.automic.roomdemo.utils.SharepreferenceUtils;

import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 类注释：
 * Created by sujingtai on 2017/6/30 0030 下午 1:37
 */

public class ExtraDbManager {
    private static ExtraDbManager mDbManager = null;

    public static ExtraDbManager getmDbManager(Context context) {
        if (mDbManager == null) {
            mDbManager = new ExtraDbManager(context.getApplicationContext());
        }
        return mDbManager;
    }

    private ExtraDbManager(Context context) {
        if (!SharepreferenceUtils.getHasSaved(context)) {
            try {
                readExcelToDb(context);
            } catch (BiffException e) {
                e.printStackTrace();
            }
        }
    }

    private void readExcelToDb(Context context) throws BiffException {
        try {
            InputStream inputStream = context.getAssets().open("work.xls");
            Workbook book = Workbook.getWorkbook(inputStream);
            book.getNumberOfSheets();
            //
            Sheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            EmployeesWorkStation employeews = null;
            for (int i = 1; i < rows; i++) {
                String id = sheet.getCell(0, i).getContents();
                String name = sheet.getCell(1, i).getContents();
                String tell = sheet.getCell(2, i).getContents();
                String section = sheet.getCell(3, i).getContents();
                String group = sheet.getCell(4, i).getContents();
                String workStationId = sheet.getCell(5, i).getContents();
                String sex = sheet.getCell(6, i).getContents();
                String imageurl = sheet.getCell(7, i).getContents();

                int sexNum=0;
                if (!"".equals(sex)&&sex!=null){
                    sexNum=Integer.parseInt(sex);
                }
                employeews = new EmployeesWorkStation(Long.parseLong(id), name, tell, section, group, workStationId,sexNum , imageurl);
                AppContext.getDaoInstant().getEmployeesWorkStationDao().insert(employeews);
                SharepreferenceUtils.saveFlagHasSaved(context,true);
            }
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
