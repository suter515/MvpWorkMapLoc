package com.automic.roomdemo.baseparts.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBOpenHelper extends SQLiteOpenHelper{

	private static  String DATABASE_NAME="agriculIrrigate.db" ;
	public static  String TABLENAME="AI_JpushMessage" ;
	public static  String TABLE_NAME_WELL="AI_WellInfoList";
	private static final String TABLE_ID = "_id";
	private static  int DATABASE_VERSION = 1;
	public DBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//LogUtils.e("DBOpenHelper", "agriculIrrigate.db onCreate");
		//存储消息到数据库
				String sql1 = "CREATE TABLE " + TABLENAME + //
						"(" + TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
						"wellNo"+" VARCHAR(50), "+
						"wellName"+" VARCHAR(100), "+
						"type"+" VARCHAR(20), "+
//						"typeOne"+" VARCHAR(20), "+
//						"typeTwo"+" VARCHAR(20), "+
//						"typeThree"+" VARCHAR(20), "+
//						"typeFour"+" VARCHAR(20), "+
//						"typeFive"+" VARCHAR(20), "+
						"msgId" + " VARCHAR(100), " + //
						"msgTime" + " VARCHAR(100), " + //
						"isReaded" + " VARCHAR(2) "  +")";
				db.execSQL(sql1);
		//存储井信息列表数据库表
		String sql2="CREATE TABLE "+TABLE_NAME_WELL+
				"(" + TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"wellNo"+" VARCHAR(100), "+
				"wellName"+" VARCHAR(200), "+
				"cName"+" VARCHAR(200), "+
				"xName"+" VARCHAR(200), "+
				"cId"+" VARCHAR(200), "+
				"xId"+" VARCHAR(202) "  +")";
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//LogUtils.e("DBOpenHelper", "agriculIrrigate.db onUpgrade");
		db.execSQL("DROP TABLE IF EXISTS AI_JpushMessage");
		db.execSQL("DROP TABLE IF EXISTS AI_WellInfoList");
		onCreate(db);
	}

}
