package com.asccode.siteswatch.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 25/03/13
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class UserDao extends SQLiteOpenHelper {

    private Context context;
    private final static String loginTable = "login";

    public UserDao(Context context){

        super(context, "", null, 1 );

        this.context = context;
    }

    @Override
    public void onCreate( SQLiteDatabase sqLiteDatabase ){



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
