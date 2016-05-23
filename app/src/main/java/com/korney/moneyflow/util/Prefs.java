package com.korney.moneyflow.util;

import android.net.Uri;

/**
 * Created by Korney on 13.04.2016.
 */
public class Prefs {

    public  final static String LOG_TAG = "MyLog";
    public static final String ID_FIELD_NAME = "_id";

    public static final String FN_FIELD_NAME = "first_name";
    public static final String LN_FIELD_NAME = "last_name";
    public static final String BIRTHDAY_FIELD_NAME = "birthday";
    public  static final String EMAIL_FIELD_NAME = "email";

    //Provider constants
    public static final String URI_AUTHORITIES = "com.korney.moneyflow.provider";
    public static final String URI_TYPE_EXPENSE = "expense";
    public static final Uri URI_EXPENSE = Uri.parse("content://" + URI_AUTHORITIES + "/" + URI_TYPE_EXPENSE);



    //DB constants
    public static String DB_NAME = "MoneyFlowDB";
    public static final String FIELD_ID = "_id";
    public static final int DB_CURRENT_VERSION = 1;

    public static final String TABLE_NAME_EXPENSE_NAMES = "expense_name";
    public static final String EXPENSE_NAMES_FIELD_NAME = "name";

    public static final String TABLE_NAME_EXPENSES = "Expenses";
    public static final String EXPENSES_FIELD_ID_PASSIVE = "id_passive";
    public static final String EXPENSES_FIELD_VOLUME = "volume";
    public static final String EXPENSES_FIELD_DATE = "date";


}
