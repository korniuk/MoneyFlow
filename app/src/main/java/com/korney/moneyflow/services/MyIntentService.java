package com.korney.moneyflow.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;

import com.korney.moneyflow.util.Prefs;

import java.util.Calendar;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    private static final String ACTION_INSERT_EXPENSE = "com.korney.moneyflow.services.action.INSERT_EXPENSE";
    private static final String EXTRA_INSERT_EXPENSE_NAME = "com.korney.moneyflow.services.extra.INSERT_EXPENSE_NAME";
    private static final String EXTRA_INSERT_EXPENSE_VOLUME = "com.korney.moneyflow.services.extra.INSERT_EXPENSE_VOLUME";
    private static final String EXTRA_EXPENSE_CRITICAL = "com.korney.moneyflow.services.extra.EXPENSE_CRITICAL";


    public MyIntentService() {
        super("MyIntentService");
    }


    public static void startActionInsertExpense(Context context, String name, Double volume, int critical){
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_INSERT_EXPENSE);
        intent.putExtra(EXTRA_INSERT_EXPENSE_NAME,name);
        intent.putExtra(EXTRA_INSERT_EXPENSE_VOLUME,volume);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case ACTION_INSERT_EXPENSE:
                    String name = intent.getStringExtra(EXTRA_INSERT_EXPENSE_NAME);
                    int volume = intent.getIntExtra(EXTRA_INSERT_EXPENSE_VOLUME, 0);
                    int critical = intent.getIntExtra(EXTRA_EXPENSE_CRITICAL, 0);
                    handleActionInsertExpense(name, volume,critical);
                    break;

            }

        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    private void handleActionInsertExpense(String name, int volume, int critical) {

        ContentValues cv = new ContentValues();
        ContentValues cv1 = new ContentValues();

        String date = String.valueOf(Calendar.getInstance().getTimeInMillis());

        cv1.put(Prefs.EXPENSE_NAMES_FIELD_NAME, name);
        cv1.put(Prefs.EXPENSE_NAMES_FIELD_CRITICAL, critical);

        Cursor c = getContentResolver().query(Prefs.URI_EXPENSES_NAMES, null, null, null, null);
        int acc = 0;
        int position = 0;

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    if ((c.getString(c.getColumnIndex(Prefs.EXPENSE_NAMES_FIELD_NAME))).equals(name)) {
                        acc++;
                        position = c.getPosition();
                    }
                } while (c.moveToNext());
                if (acc == 0) {
                    getContentResolver().insert(Prefs.URI_EXPENSES_NAMES, cv1);

                }
            } else
                getContentResolver().insert(Prefs.URI_EXPENSES_NAMES, cv1);
        }

        try {
            if (acc == 0) {
                c.moveToLast();
                cv.put(Prefs.EXPENSES_FIELD_ID_PASSIVE, Integer.valueOf(c.getString(c.getColumnIndex(Prefs.FIELD_ID))) + 1);
            } else {
                c.moveToPosition(position);
                cv.put(Prefs.EXPENSES_FIELD_ID_PASSIVE, Integer.valueOf(c.getString(c.getColumnIndex(Prefs.FIELD_ID))));
            }
        } catch (android.database.CursorIndexOutOfBoundsException e) {
            cv.put(Prefs.EXPENSES_FIELD_ID_PASSIVE, 1);
        }

        cv.put(Prefs.EXPENSES_FIELD_VOLUME, volume);
        cv.put(Prefs.EXPENSES_FIELD_DATE, date);

        c.close();

        getContentResolver().insert(Prefs.URI_EXPENSE, cv);
    }



    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
