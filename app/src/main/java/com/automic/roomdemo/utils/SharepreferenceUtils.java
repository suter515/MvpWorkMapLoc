package com.automic.roomdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 类注释：
 * Created by sujingtai on 2017/6/30 0030 下午 2:55
 */

public class SharepreferenceUtils {
    public static  final String HAS_SAVE_EXCEL_TO_DB="has_save_excel_todb";

    /**
     * 保存是否 "读取数据文件到数据库"标志
     * @param context
     * @param hasSaveed
     */
    public static  void saveFlagHasSaved(Context context,boolean hasSaveed){
        SharedPreferences preferences=context.getSharedPreferences("excel_to_db",0);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(HAS_SAVE_EXCEL_TO_DB,hasSaveed);
        editor.commit();
    }

    /**
     * 获得 是否读入数据库的标志
     * @param context
     * @return
     */
    public static boolean getHasSaved(Context context){
        SharedPreferences preferences=context.getSharedPreferences("excel_to_db",0);
        boolean hasSaved=preferences.getBoolean(HAS_SAVE_EXCEL_TO_DB,false);
        return  hasSaved;
    }
}
