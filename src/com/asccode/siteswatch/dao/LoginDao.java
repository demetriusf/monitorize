package com.asccode.siteswatch.dao;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.asccode.siteswatch.R;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 25/03/13
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class LoginDao extends SQLiteOpenHelper {

    private Context context;
    private final static String LOGIN_TABLE = "login";
    private final static int VERSION = 2;

    public LoginDao(Context context){

        super(context, LoginDao.LOGIN_TABLE, null, LoginDao.VERSION );

        this.context = context;
    }

    /**
     * Return the token of the user's login.
     * @return String
     * */
    public String getTokenUserLogged(){

        Cursor cursor = getWritableDatabase().query(LoginDao.LOGIN_TABLE, new String[]{"token"}, null, null, null, null, null );

        String token = null;

        if(cursor.moveToNext()){

            token = cursor.getString(0);

        }

        cursor.close();

        getWritableDatabase().close();

        return token;

    }

    public boolean login( String loginToken ){

        boolean userLogged = false;

        try{

            //Limpa tabela
            getWritableDatabase().delete(LoginDao.LOGIN_TABLE, null, null);

            //Loga user
            ContentValues contentValues = new ContentValues();
            contentValues.put("token", loginToken);

            getWritableDatabase().insertOrThrow(LoginDao.LOGIN_TABLE, null, contentValues);
            userLogged = true;

        }catch (SQLException sqlException){

            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle(this.context.getString(R.string.fbDialogErrorTitleUserLogin));
            builder.setMessage(this.context.getString(R.string.fbDialogErrorBodyUserLogin));
            builder.setPositiveButton(this.context.getString(R.string.fbDialogErrorPositiveButtonUserLogin), null);
            builder.show();

        }

        getWritableDatabase().close();

        return userLogged ;

    }

    public void logout(){

        getWritableDatabase().delete( LoginDao.LOGIN_TABLE, null, null);
    }

    @Override
    public void onCreate( SQLiteDatabase sqLiteDatabase ){

        String sqlCreate = "CREATE TABLE %s( token TEXT UNIQUE NOT NULL);";

        sqLiteDatabase.execSQL(String.format(sqlCreate, LoginDao.LOGIN_TABLE));

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String sqlUpgrade = "DROP TABLE %s ;";

        sqLiteDatabase.execSQL(String.format(sqlUpgrade, LoginDao.LOGIN_TABLE));

        this.onCreate(sqLiteDatabase);

    }
}
