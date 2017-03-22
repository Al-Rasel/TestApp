package com.example.asus.testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.asus.testapp.adapter.RecylerAdapter;
import com.example.asus.testapp.dataModels.SingleIteamData;
import com.example.asus.testapp.dialogFragment.SuppliersSelection;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;


public class MainActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private static Realm mRealmDataBase;

    //refreshViewWithCurrentQurry is a method for refreshview and make quarry
    public static void refreshViewWithCurrentQurry(int suppliersId, View v) {
        RealmResults<SingleIteamData> result = mRealmDataBase.where(SingleIteamData.class).equalTo("suppliersId", suppliersId).findAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        RecylerAdapter recylerAdapter = new RecylerAdapter(result);
        recyclerView.setAdapter(recylerAdapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(recylerAdapter));
    }

    //saveTODatabase is a method for save data to databse
    private static void saveToDatabase(int suppliersId, String date, String suppliers, String income, String spending, String subjects, String course) {

        mRealmDataBase.beginTransaction();
        SingleIteamData saveSingeModel = mRealmDataBase.createObject(SingleIteamData.class);
        saveSingeModel.setSuppliersId(suppliersId);
        saveSingeModel.setInputDate(date);
        saveSingeModel.setSuppliers(suppliers);
        saveSingeModel.setIncome(income);
        saveSingeModel.setSpending(spending);
        saveSingeModel.setSubjects(subjects);
        saveSingeModel.setCourse(course);

        mRealmDataBase.commitTransaction();
    }

    //copyAnotherIteamWithAmount is a mehtod for copying data
    public static void copyAnotherIteamWithAmount(String suppliers, String subjects, String course, int suppliersId, View v, String spending) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String formattedDate = formatter.format(date);

        saveToDatabase(suppliersId, formattedDate, suppliers, "income ", spending, subjects, course);
        RealmResults<SingleIteamData> result = mRealmDataBase.where(SingleIteamData.class).findAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        RecylerAdapter recylerAdapter = new RecylerAdapter(result);
        recyclerView.setAdapter(recylerAdapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(recylerAdapter));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button  for suppliers options selection
        FloatingActionButton buttonSuppliers = (FloatingActionButton) findViewById(R.id.button_suppliers);
        recyclerView = (RecyclerView) findViewById(R.id.recylerView_fullListOfData);

        //suppliers button click event
        buttonSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SuppliersSelection dialog_color_select = SuppliersSelection.newInstance();
                dialog_color_select.show(getFragmentManager(), "dialog");
            }
        });
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Database starts from here<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealmDataBase = Realm.getInstance(config);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        RealmResults<SingleIteamData> retriveDatabaseForDataAddCheck = mRealmDataBase.where(SingleIteamData.class).findAll();
        if (retriveDatabaseForDataAddCheck.size() < 1) {

            Date date = new Date();
            String formattedDate = formatter.format(date);
            saveToDatabase(0, formattedDate, "東京電力株式会社", "income ", "3000円", "新聞の帳簿費", "東京電力株式会社");
            saveToDatabase(1, formattedDate, "東京ガス", "income ", "1000円", "通信料", "東京ガス");


        }
        RealmResults<SingleIteamData> retiveDataForShowingInMainView = mRealmDataBase.where(SingleIteamData.class).findAll();
        Log.e("myDatabse", "onCreate: " + String.valueOf(retiveDataForShowingInMainView));


        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Database ends here<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        /*>>>>>>>>>>>>>>>>>>>Recylerview starts from here<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecylerAdapter recylerAdapter = new RecylerAdapter(retiveDataForShowingInMainView);
        recyclerView.setAdapter(recylerAdapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(recylerAdapter));

    }

}
