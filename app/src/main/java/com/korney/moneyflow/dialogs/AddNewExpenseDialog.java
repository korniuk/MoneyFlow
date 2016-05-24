package com.korney.moneyflow.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.korney.moneyflow.R;
import com.korney.moneyflow.services.MyIntentService;

/**
 * Created on 16.05.2016.
 */
public class AddNewExpenseDialog extends DialogFragment {

    private EditText tvValue;
    private AutoCompleteTextView acName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_expense, null,true);
        //TODO set adapter for AutocompleteTextView

        tvValue = (EditText) view.findViewById(R.id.etVolumeOfExpency);
        acName = (AutoCompleteTextView) view.findViewById(R.id.acNameOfExpency);
        builder.setView(view)
                .setMessage(R.string.message_add_new_expense_dialog)
                .setTitle(R.string.title_add_new_expense_dialog)
                .setPositiveButton(R.string.positive_button_add_new_expency_dialog,
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String expenseValue = tvValue.getText().toString();
                        if (expenseValue.matches("[0-9]*")) {
                            addNewExpense(acName.getText().toString(), Double.parseDouble(expenseValue));
                        }
                    }
                })
                .setNegativeButton(R.string.negative_button_add_new_expency_dialog,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dismiss();
                            }
                        });

        return builder.create();
    }

    private void addNewExpense(String name, Double value) {
        Toast.makeText(getActivity(), "Add from dialog", Toast.LENGTH_SHORT).show();
        MyIntentService.startActionInsertExpency(getActivity(), name, value);
    }
}
