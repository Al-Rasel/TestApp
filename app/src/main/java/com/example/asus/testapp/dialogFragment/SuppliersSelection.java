package com.example.asus.testapp.dialogFragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.asus.testapp.MainActivity;
import com.example.asus.testapp.R;

/**
 * Created by ASUS on 3/22/2017.
 */

public class SuppliersSelection extends DialogFragment {

    public SuppliersSelection() {
    }

    public static SuppliersSelection newInstance() {
        SuppliersSelection fragment = new SuppliersSelection();

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chose_suppliers, null);
        Button buttonFirstSuppliers = (Button) view.findViewById(R.id.bttonFirstSuppliers);
        Button buttonSecondSuppliers = (Button) view.findViewById(R.id.buttonsSecondSuppliers);
        buttonFirstSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.refreshViewWithCurrentQurry(0,view);
                dismiss();
            }
        });
        buttonSecondSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.refreshViewWithCurrentQurry(1,view);
                dismiss();
            }
        });
        alert.setView(view, 0, 0, 0, 0);


        return alert.create();
    }
}
