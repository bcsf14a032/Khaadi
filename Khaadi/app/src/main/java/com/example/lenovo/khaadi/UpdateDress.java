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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.khaadi.Database.DBHelper;
import com.example.lenovo.khaadi.Models.DressInfo;

import java.util.Arrays;

public class UpdateDress extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText dress_code, quantity;
    Spinner dress_type,category;
    Button update_Dress;
    DBHelper DB_Helper;
    Intent in,categoryBased_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dress);
        dress_code = (EditText) findViewById(R.id.editId);
        dress_type = (Spinner) findViewById(R.id.editDressType);
        quantity = (EditText) findViewById(R.id.editQuantity);
        update_Dress = (Button) findViewById(R.id.editDress);
        category = (Spinner) findViewById(R.id.editCategory);
        DB_Helper = new DBHelper(this);

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#461F00")));
        ab.setTitle(Html.fromHtml("<font color='Brown'><b>Edit Dresses</b></font>"));
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);


        update_Dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DressInfo info=new DressInfo(in.getIntExtra("id",0),dress_code.getText().toString(),dress_type.getSelectedItem().toString(),category.getSelectedItem().toString(),Integer.parseInt(quantity.getText().toString()));

                DB_Helper.update(info);
                if(in.getStringExtra("category").equals("Man")) {
                    categoryBased_intent = new Intent(UpdateDress.this, showManDresses.class);
                }
                else if(in.getStringExtra("category").equals("Woman")) {
                    categoryBased_intent = new Intent(UpdateDress.this, showWomanDresses.class);
                }
                else
                {
                    categoryBased_intent = new Intent(UpdateDress.this, showKidsDresses.class);
                }
                startActivity(categoryBased_intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        in = getIntent();
        if (in != null) {
            dress_code.setText(in.getStringExtra("dcode"));
            quantity.setText(String.valueOf(in.getIntExtra("quantity", 0)));
            String[] arr = getResources().getStringArray(R.array.women_dressType);
            dress_type.setSelection(getIndexOf(arr,in.getStringExtra("dtype")));
            String[] arr1 = getResources().getStringArray(R.array.Category);
          //  category.setSelection(Arrays.asList(arr).indexOf(in.getStringExtra("category")));
            category.setSelection(getIndexOf(arr1,in.getStringExtra("category")));


        }
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Category, android.R.layout.simple_spinner_item);
        category.setAdapter(adapter1);
        category.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        if (category.getSelectedItem().equals("Man")) {
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.men_dressType, android.R.layout.simple_spinner_item);
            dress_type.setAdapter(adapter2);
        } else if(category.getSelectedItem().equals("Woman")) {
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.women_dressType, android.R.layout.simple_spinner_item);
            dress_type.setAdapter(adapter2);
        }
        else {
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.kid_dressType, android.R.layout.simple_spinner_item);
            dress_type.setAdapter(adapter2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public static int getIndexOf(String[] strings, String item) {
        for (int i = 0; i < strings.length; i++) {
            if (item.equals(strings[i])) return i;
        }
        return -1;
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
