package com.example.lenovo.khaadi;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_woman_dress);
        dress_code = (EditText) findViewById(R.id.womanDId);
        dress_type = (Spinner) findViewById(R.id.womenType);
        quantity = (EditText) findViewById(R.id.wquantity);
        addWoMan_Dress = (Button) findViewById(R.id.addDressW);
        DB_Helper = new DBHelper(this);
        addWoMan_Dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long rid = DB_Helper.insert(dress_code.getText().toString(), dress_type.getSelectedItem().toString(), "Woman", Integer.parseInt(quantity.getText().toString()));
                Cursor b = DB_Helper.read(rid);
                String[] rows = new String[b.getCount()];
                while (b.moveToNext()) {
                    rows[b.getPosition()] = "ID: " + b.getInt(b.getColumnIndex(DBHelper.ID))
                            + "\n" + "DCODE: " + b.getString(b.getColumnIndex(DBHelper.DCODE));
                }
                Toast.makeText(addWomanDress.this, dress_type.getSelectedItem() + " added", Toast.LENGTH_LONG).show();

                b.close();
                dress_code.setText("");
                quantity.setText("");
            }
        });
    }
}