package com.automic.roomdemo.utils;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.automic.roomdemo.application.AppContext;


public class AppUtils {
	/**
	 * 获取当前程序版本
	 * 
	 * @return
	 */
	public static String getPackageVersion() {
		String version = "";
		try {
			PackageManager pm = AppContext.getInstance().getPackageManager();
			PackageInfo pi = null;
			pi = pm.getPackageInfo(AppContext.getInstance().getPackageName(), 0);
			version = pi.versionName;
		} catch (Exception e) {
			version = ""; // failed, ignored
		}
		return version;
	}

	/**
	 * 获取当前程序包名
	 * 
	 * @return
	 */
	public static String getPackageName() {
		String version = "";
		try {
			PackageManager pm = AppContext.getInstance().getPackageManager();
			PackageInfo pi = null;
			pi = pm.getPackageInfo(AppContext.getInstance().getPackageName(), 0);
			version = pi.packageName;
		} catch (Exception e) {
			version = ""; // failed, ignored
		}
		return version;
	}

	/**
	 * 获取当前程序版本code
	 * 
	 * @return
	 */
	public static String getPackageCode() {
		String code = "";
		try {
			PackageManager pm = AppContext.getInstance().getPackageManager();
			PackageInfo pi = null;
			pi = pm.getPackageInfo(AppContext.getInstance().getPackageName(), 0);
			code = pi.versionName;
		} catch (Exception e) {
			code = "1"; // failed, ignored
		}
		return code;
	}

}
