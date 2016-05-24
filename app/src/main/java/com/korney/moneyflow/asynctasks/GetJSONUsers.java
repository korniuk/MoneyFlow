package com.korney.moneyflow.asynctasks;


import android.app.Activity;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.util.Log;

import com.korney.moneyflow.util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created on 13.04.2016.
 */
public class GetJSONUsers extends AsyncTask<String,Void,Cursor> {

    private Activity mActivity;

    private String email, password;

    public GetJSONUsers(Activity mActivity, String password, String email) {
        this.mActivity = mActivity;
        this.password = password;
        this.email = email;
    }

    @Override
    protected Cursor doInBackground(String... params) {
        JSONObject jsonObject =null;
        MatrixCursor matrixCursor = new MatrixCursor(
                new String[]{
                        Prefs.ID_FIELD_NAME,
                        Prefs.FN_FIELD_NAME,
                        Prefs.LN_FIELD_NAME,
                        Prefs.BIRTHDAY_FIELD_NAME,
                        Prefs.EMAIL_FIELD_NAME
                });
        Document document = null;
        try {
            try {
                document = Jsoup.connect(params[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements body = document.select("body");
            Element firstBody = body.first();
            String resElementText = firstBody.text();
            Log.d(Prefs.LOG_TAG,resElementText);

            JSONArray arrayUsersJSON = null;
            try {
                arrayUsersJSON = new JSONArray(resElementText);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(Prefs.LOG_TAG,"Length - "+arrayUsersJSON.length());

            JSONObject localJson = null;
            for (int i = 0; i<arrayUsersJSON.length(); i++){
                localJson = arrayUsersJSON.getJSONObject(i);
                localJson = localJson.getJSONObject("user_class");

                Object[] array = new Object[]{
                        localJson.getInt(Prefs.ID_FIELD_NAME),
                        localJson.getString(Prefs.FN_FIELD_NAME),
                        localJson.getString(Prefs.LN_FIELD_NAME),
                        localJson.getString(Prefs.BIRTHDAY_FIELD_NAME),
                        localJson.getString(Prefs.EMAIL_FIELD_NAME)};
                Log.d(Prefs.LOG_TAG, Arrays.toString(array));

                matrixCursor.addRow(array);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matrixCursor;
    }

}

