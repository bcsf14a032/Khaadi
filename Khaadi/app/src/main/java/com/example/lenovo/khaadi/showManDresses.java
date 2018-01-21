package com.example.lenovo.khaadi;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.khaadi.Adapters.DataAdapter;
import com.example.lenovo.khaadi.Database.DBHelper;
import com.example.lenovo.khaadi.Models.DressInfo;

import java.util.ArrayList;

public class showManDresses extends AppCompatActivity {

    DataAdapter dataAdapter;
    RecyclerView recyclerView;
    DBHelper DB_Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_man_dresses);
        DB_Helper = new DBHelper(this);


        //Code to change the title bar of any activity that extends AppCompatActivity
//        ActionBar ab = getSupportActionBar();
//        ab.setTitle(Html.fromHtml("<font color='green'>All</font> <font color='yellow'><b>Records</b>"));
//        ab.setSubtitle("All records are listed below");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewM);
        ArrayList<DressInfo> ui = DB_Helper.getMan();
        if (ui.size() <= 0) {
            final Snackbar sb = Snackbar.make(findViewById(R.id.clayoutM), "No Records Found.", Snackbar.LENGTH_INDEFINITE);
            sb.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sb.dismiss();
                }
            });
            sb.show();
        } else {
            dataAdapter = new DataAdapter(this, ui);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

            recyclerView.setAdapter(dataAdapter);
        }
    }
}
