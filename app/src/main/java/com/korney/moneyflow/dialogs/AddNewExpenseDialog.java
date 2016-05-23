package com.korney.moneyflow.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.korney.moneyflow.R;
import com.korney.moneyflow.services.MyIntentService;

/**
 * Created on 16.05.2016.
 */
public class AddNewExpenseDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_expency, null,true);
        //TODO set adapter for AutocompleteTextView
        builder.setView(view)
                .setMessage(R.string.message_add_new_expency_dialog)
                .setTitle(R.string.title_add_new_expency_dialog)
                .setPositiveButton(R.string.positive_button_add_new_expency_dialog,
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addNewExpense();
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

    private void addNewExpense() {
        Toast.makeText(getActivity(), "Add from dialog", Toast.LENGTH_SHORT).show();
        MyIntentService.startActionInsertExpency(getActivity(), "Expense", 120);
    }
}
