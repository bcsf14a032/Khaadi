package com.example.lenovo.khaadi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lenovo.khaadi.Adapters.DataAdapter;
import com.example.lenovo.khaadi.Database.DBHelper;
import com.example.lenovo.khaadi.Models.DressInfo;

import java.util.ArrayList;

public class showKidsDresses extends AppCompatActivity {

    DataAdapter dataAdapter;
    RecyclerView recyclerView;
    ArrayList<DressInfo> ui;
    DBHelper DB_Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kids_dresses);
        DB_Helper = new DBHelper(this);
        ui =new ArrayList<DressInfo>();

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#461F00")));
        ab.setTitle(Html.fromHtml("<font color='Brown'><b>Kids Dresses</b></font>"));
        ab.setSubtitle("Add,Edit Or Delete Dresses");
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewK);

    }


    @Override
    protected void onResume() {
        super.onResume();

        ui= DB_Helper.getKids();

        if (ui.size() <= 0) {
            final Snackbar sb = Snackbar.make(findViewById(R.id.clayout), "No Records Found.", Snackbar.LENGTH_INDEFINITE);
            sb.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sb.dismiss();
                }
            });
            sb.show();
        } else {
            dataAdapter = new DataAdapter(this, ui,"kid");


            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

            recyclerView.setAdapter(dataAdapter);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
            ui.clear();
            if(dataAdapter!=null)
            dataAdapter.notifyDataSetChanged();

            else
            {
                ui= DB_Helper.getKids();


                if (ui.size() <= 0) {
                    final Snackbar sb = Snackbar.make(findViewById(R.id.clayout), "No Records Found.", Snackbar.LENGTH_INDEFINITE);
                    sb.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sb.dismiss();
                        }
                    });
                    sb.show();
                } else {
                    dataAdapter = new DataAdapter(this, ui,"kid");


                    LinearLayoutManager llm = new LinearLayoutManager(this);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(llm);

                    recyclerView.setAdapter(dataAdapter);
                }
            }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addAny:
                startActivity(new Intent(this, addKidsDresses.class));
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
