package com.korney.moneyflow.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.korney.moneyflow.R;
import com.korney.moneyflow.dialogs.AddNewExpenseDialog;
import com.korney.moneyflow.util.Prefs;

public class DashboardActivity extends AppCompatActivity {

    private Button btnDashboardShowExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnDashboardShowExpenses = (Button) findViewById(R.id.btnDashBoardShowExpenses);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_expency:
                AddNewExpenseDialog addNewExpenseDialog = new AddNewExpenseDialog();
                addNewExpenseDialog.show(getSupportFragmentManager(), "addExpense");
                //Toast.makeText(this, "Click on expense", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void onClick(View v) {

        Cursor c = getContentResolver().query(Prefs.URI_EXPENSE, null, null, null, null);

        switch (v.getId()) {
            case R.id.btnDashBoardShowExpenses:
                Intent intent = new Intent(this, ExpensesActivity.class);
                startActivity(intent);

                /* if (c != null) {
                    if (c.moveToFirst()) {
                        do {
                            Log.d(Prefs.LOG_TAG, c.getString(c.getColumnIndex(Prefs.FIELD_ID)) + ", " +
                                    c.getColumnIndex(Prefs.EXPENSE_FIELD_ID_PASSIVE) + ", " +
                                    c.getColumnIndex(Prefs.EXPENSE_FIELD_DATE) + "," +
                                    c.getColumnIndex(Prefs.EXPENSE_FIELD_VOLUME));
                        } while (c.moveToNext());
                    } else
                        Log.d(Prefs.LOG_TAG, "Table:" + Prefs.TABLE_NAME_EXPENSES + "it contains o rows");
                    c.close();
                }*/
                break;
        }
    }

/*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((resultCode == Activity.RESULT_OK && requestCode == 1)) {
            Cursor c = getContentResolver().query(data.getData(), new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
            if (c.moveToFirst()) {
                int columnIndex = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String[] columnName = c.getColumnNames();
                for (int i = 0; i < columnName.length; i++) {
                    i = c.getColumnIndex(columnName[i]);
                    String s = c.getString(i);
                }
            }
        }
    }*/

}