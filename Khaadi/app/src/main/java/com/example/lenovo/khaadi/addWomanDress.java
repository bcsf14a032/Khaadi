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

public class addWomanDress extends AppCompatActivity {
    EditText dress_code, quantity;
    Spinner dress_type;
    Button addWoMan_Dress;
    DBHelper DB_Helper;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_woman_dress);
        dress_code = (EditText) findViewById(R.id.womanDId);
        dress_type = (Spinner) findViewById(R.id.womenType);
        quantity = (EditText) findViewById(R.id.wquantity);
        addWoMan_Dress = (Button) findViewById(R.id.addDressW);
        DB_Helper = new DBHelper(this);
        sessionManager=new SessionManager(this);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#461F00")));
        ab.setTitle(Html.fromHtml("<font color='Brown'><b>Add Woman Dresses</b></font>"));


        addWoMan_Dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dress_code.getText().toString().equals("") || quantity.getText().toString().equals(""))
                {
                    Toast.makeText(addWomanDress.this,"Please fill all fields",Toast.LENGTH_LONG).show();

                }
                else {
                   boolean rid = DB_Helper.insert(dress_code.getText().toString(), dress_type.getSelectedItem().toString(), "Woman", Integer.parseInt(quantity.getText().toString()));
//                    Cursor b = DB_Helper.read(rid);
//                    String[] rows = new String[b.getCount()];
//                    while (b.moveToNext()) {
//                        rows[b.getPosition()] = "ID: " + b.getInt(b.getColumnIndex(DBHelper.ID))
//                                + "\n" + "DCODE: " + b.getString(b.getColumnIndex(DBHelper.DCODE));
//                    }
                    if(rid==false){
                        Toast.makeText(addWomanDress.this, "dress code already exist", Toast.LENGTH_LONG).show();
                        return;}
                    else
                    {
                        Toast.makeText(addWomanDress.this, dress_type.getSelectedItem() + " added", Toast.LENGTH_LONG).show();
                    }
                    //b.close()
                    dress_code.setText("");
                    quantity.setText("");
                }
            }
        });
        sessionManager.checkLogin();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                Intent intent=new Intent(addWomanDress.this,showWomanDresses.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}