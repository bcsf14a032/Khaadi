package com.example.lenovo.khaadi;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.khaadi.Database.DBHelper;

import java.util.Arrays;

public class addKidsDresses extends AppCompatActivity {
    EditText dress_code, quantity;
    Spinner dress_type;
    Button addKids_Dress;
    DBHelper DB_Helper;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kids_dresses);
        dress_code = (EditText) findViewById(R.id.kidId);
        dress_type = (Spinner) findViewById(R.id.kidType);
        quantity = (EditText) findViewById(R.id.kquantity);
        addKids_Dress = (Button) findViewById(R.id.addDressK);
        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#461F00")));
        ab.setTitle(Html.fromHtml("<font color='Brown'><b>Add Kids Dresses</b></font>"));
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        DB_Helper = new DBHelper(this);
        addKids_Dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long rid = DB_Helper.insert(dress_code.getText().toString(), dress_type.getSelectedItem().toString(), "Kids", Integer.parseInt(quantity.getText().toString()));
                Cursor b = DB_Helper.read(rid);
                String[] rows = new String[b.getCount()];
                while (b.moveToNext()) {
                    rows[b.getPosition()] = "ID: " + b.getInt(b.getColumnIndex(DBHelper.ID))
                            + "\n" + "DCODE: " + b.getString(b.getColumnIndex(DBHelper.DCODE));
                }
                Toast.makeText(addKidsDresses.this, dress_type.getSelectedItem() + " added", Toast.LENGTH_LONG).show();

                b.close();
                dress_code.setText("");
                quantity.setText("");
            }
        });
        in = getIntent();
        if (in != null) {
            dress_code.setText(in.getStringExtra("dcode"));
            quantity.setText(String.valueOf(in.getIntExtra("quantity",0)));
            String[]arr=getResources().getStringArray(R.array.women_dressType);
            dress_type.setSelection(Arrays.asList(arr).indexOf(in.getStringExtra("dtype")));



        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}