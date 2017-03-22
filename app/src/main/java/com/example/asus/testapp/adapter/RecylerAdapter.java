package com.example.asus.testapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.testapp.R;
import com.example.asus.testapp.dataModels.SingleIteamData;
import com.example.asus.testapp.dialogFragment.spendingEditBox;

import java.util.List;

/**
 * Created by ASUS on 3/22/2017.
 */

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.SingleViewIteam> {

    List<SingleIteamData> allDataForRecylerView;

    public RecylerAdapter(List<SingleIteamData> value) {
        this.allDataForRecylerView = value;

    }

    @Override
    public SingleViewIteam onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SingleViewIteam(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recylerview_holder_design, parent, false));
    }

    @Override
    public void onBindViewHolder(final SingleViewIteam holder, final int position) {
        holder.inputDate.setText(allDataForRecylerView.get(position).getInputDate().toString());
        holder.suppliers.setText(allDataForRecylerView.get(position).getSuppliers());
        holder.spending.setText(allDataForRecylerView.get(position).getSpending());
        holder.subjects.setText(allDataForRecylerView.get(position).getSubjects());
        holder.course.setText(allDataForRecylerView.get(position).getCourse());
        holder.spending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spendingEditBox dialogEditBox = spendingEditBox.newInstance(allDataForRecylerView.get(position).getSuppliers(),
                        allDataForRecylerView.get(position).getSubjects(),
                        allDataForRecylerView.get(position).getCourse(),
                        allDataForRecylerView.get(position).getSuppliersId()
                );
                dialogEditBox.show(((Activity) holder.itemView.getContext()).getFragmentManager(), "dialog");

            }
        });
    }

    @Override
    public int getItemCount() {
        return allDataForRecylerView.size();
    }

    class SingleViewIteam extends RecyclerView.ViewHolder {
        private TextView inputDate;
        private TextView suppliers;

        private TextView spending;
        private TextView subjects;
        private TextView course;

        public SingleViewIteam(View itemView) {
            super(itemView);

            inputDate = (TextView) itemView.findViewById(R.id.dateOfCreate);
            suppliers = (TextView) itemView.findViewById(R.id.tv_suppliers);
            spending = (TextView) itemView.findViewById(R.id.tv_spending);
            subjects = (TextView) itemView.findViewById(R.id.tv_subjects);
            course = (TextView) itemView.findViewById(R.id.tv_course);

        }
    }
}
