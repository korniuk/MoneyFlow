package com.korney.moneyflow.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;

import com.korney.moneyflow.R;
import com.korney.moneyflow.services.MyIntentService;

/**
 * Created on 16.05.2016.
 */
public class AddNewExpenseDialog extends DialogFragment {

    private EditText tvValue;
    private AutoCompleteTextView acName;
    CheckBox chbCritical;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_expense, null,true);
        //TODO set adapter for AutocompleteTextView

        tvValue = (EditText) view.findViewById(R.id.etVolumeOfExpency);
        acName = (AutoCompleteTextView) view.findViewById(R.id.acNameOfExpency);
        chbCritical = (CheckBox)view.findViewById(R.id.chbCriticalExpense);
        builder.setView(view)
                .setMessage(R.string.message_add_new_expense_dialog)
                .setTitle(R.string.title_add_new_expense_dialog)
                .setPositiveButton(R.string.positive_button_add_new_expense_dialog,
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            addNewExpense();

                    }
                })
                .setNegativeButton(R.string.negative_button_add_new_expense_dialog,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dismiss();
                            }
                        });

        return builder.create();
    }

    private void addNewExpense() {
        int critical = 0;
        String name = acName.getText().toString();
       Double volume = Double.valueOf(tvValue.getText().toString());
        if (chbCritical.isChecked())
            critical = 1;

        MyIntentService.startActionInsertExpense(getActivity(),name, volume, critical);
    }
}
