package com.example.asus.testapp.dialogFragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.testapp.MainActivity;
import com.example.asus.testapp.R;

/**
 * Created by ASUS on 3/22/2017.
 */

public class spendingEditBox extends DialogFragment {


    private String suppliers;
    private String subjects;
    private String course;
    private int suppliersId;

    public spendingEditBox() {
    }

    public static spendingEditBox newInstance(String suppliers, String subjects, String course, int suppliersId) {
        spendingEditBox fragment = new spendingEditBox();
        fragment.suppliers = suppliers;
        fragment.subjects = subjects;
        fragment.course = course;
        fragment.suppliersId = suppliersId;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_spending_edit, null);
        Button done = (Button) view.findViewById(R.id.buttonApply);
        final EditText editTextSpending = (EditText) view.findViewById(R.id.editTextSpending);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextSpending.getText().toString().equals("")){
                    Toast.makeText(view.getContext(),"You don't put any value",Toast.LENGTH_LONG).show();
                }else {
                    MainActivity.copyAnotherIteamWithAmount(suppliers, subjects, course, suppliersId, view, editTextSpending.getText().toString());
                }

                dismiss();
            }
        });

        alert.setView(view, 0, 0, 0, 0);


        return alert.create();
    }
}
