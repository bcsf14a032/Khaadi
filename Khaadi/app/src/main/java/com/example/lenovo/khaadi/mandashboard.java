package com.example.lenovo.khaadi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mandashboard extends AppCompatActivity {
    Button addm,showm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandashboard);
        addm=(Button)findViewById(R.id.addm);
        showm=(Button)findViewById(R.id.showm);
        addm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mandashboard.this,addManDresses.class);
                startActivity(intent);
            }
        });
        showm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mandashboard.this,showManDresses.class);
                startActivity(intent);
            }
        });
    }
}
