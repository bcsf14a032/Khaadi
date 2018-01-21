package com.example.lenovo.khaadi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class womandashboard extends AppCompatActivity {
    Button addW,showWo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womandashboard);
        addW=(Button)findViewById(R.id.addw);
        showWo=(Button)findViewById(R.id.showWo);
        addW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(womandashboard.this,addWomanDress.class);
                startActivity(intent);
            }
        });
        showWo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(womandashboard.this,showWomanDresses.class);
                startActivity(intent);
            }
        });
    }
}
