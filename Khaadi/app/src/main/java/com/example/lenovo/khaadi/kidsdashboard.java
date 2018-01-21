package com.example.lenovo.khaadi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class kidsdashboard extends AppCompatActivity {
    Button addk,showk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kidsdashboard);
        addk=(Button)findViewById(R.id.addK);
        showk=(Button)findViewById(R.id.showk);
        addk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(kidsdashboard.this,addKidsDresses.class);
                startActivity(intent);
            }
        });
        showk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(kidsdashboard.this,showKidsDresses.class);
                startActivity(intent);
            }
        });
    }
}
