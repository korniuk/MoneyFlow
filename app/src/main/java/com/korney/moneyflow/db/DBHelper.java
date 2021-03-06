package com.korney.moneyflow.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.korney.moneyflow.util.Prefs;

/**
 * Created on 16.05.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * Table expencies
     * - _id
     * - id_passive - id from table passive
     * - volume - volume of money
     * - date - date when expenses made
     */

    private static final String CREATE_TABLE_EXPENSES = String.format("create table %s"+
            "(%s integer primary key autoincrement, %s integer, %s text);",
            Prefs.TABLE_NAME_EXPENSES, Prefs.FIELD_ID, Prefs.EXPENSES_FIELD_ID_PASSIVE, Prefs.EXPENSES_FIELD_VOLUME, Prefs.EXPENSES_FIELD_DATE);


    /**
     * Table expense_names
     * - _id
     * - name   -  name of expense
     */
    private static final String CREATE_TABLE_EXPENSE_NAME = String.format("create table %s" + "(%s integer primary key autoincrement, %s text);",
            Prefs.TABLE_NAME_EXPENSE_NAMES, Prefs.FIELD_ID, Prefs.EXPENSE_NAMES_FIELD_NAME);


    public DBHelper(Context context, int version) {
        super(context, Prefs.DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXPENSES);
        db.execSQL(CREATE_TABLE_EXPENSE_NAME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF IT EXISTS" + Prefs.TABLE_NAME_EXPENSES);
        onCreate(db);

    }
}
