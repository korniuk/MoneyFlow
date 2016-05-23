package com.korney.moneyflow.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;

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

    private static final String ACTION_INSERT_EXPENSE = "com.korney.moneyflow.services.action.INSERT_EXPENCY";

    private static final String EXTRA_INSERT_EXPENSE_NAME = "com.korney.moneyflow.services.action.INSERT_EXPENCY_NAME";
    private static final String EXTRA_INSERT_EXPENSE_VOLUME = "com.korney.moneyflow.services.action.INSERT_EXPENCY_VOLUME";


    public MyIntentService() {
        super("MyIntentService");
    }


    public static void startActionInsertExpency(Context context,String name, int volume){
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
                    handleActionInsertExpense(name, volume);
                    break;

            }

        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    private void handleActionInsertExpense(String name, int volume) {

        ContentValues cv = new ContentValues();
        //cv.put(Prefs.EXPENSE_NAMES_FIELD_NAME, name);
        cv.put(Prefs.EXPENSES_FIELD_ID_PASSIVE,1);
        String date =String.valueOf(Calendar.getInstance().getTimeInMillis());

        cv.put(Prefs.EXPENSES_FIELD_DATE,date);
        cv.put(Prefs.EXPENSES_FIELD_VOLUME, volume);
        getContentResolver().insert(Prefs.URI_EXPENSE,cv);
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
