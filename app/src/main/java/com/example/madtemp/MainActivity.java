package com.example.madtemp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
    Boolean updated;
    Boolean follow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent rec = getIntent();
        String uname = rec.getStringExtra("uname");
        String des = rec.getStringExtra("des");
        follow = rec.getBooleanExtra("fol",false);
        int id = rec.getIntExtra("id",0);
        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setText(uname);
        TextView description = (TextView) findViewById(R.id.textView2);
        description.setText("Description: " + des);
        Button btn = (Button) findViewById(R.id.button);

        if(follow == false){
            btn.setText("FOLLOW");
        }
        else{
            btn.setText("UNFOLLOW");
        }

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (follow == true) {
                    btn.setText("FOLLOW");
                    updated = false;
                    follow = false;
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                    dbHandler.updateUser(id,updated);
                } else if (follow == false) {
                    btn.setText("UNFOLLOW");
                    updated = true;
                    follow = true;
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    dbHandler.updateUser(id,updated);
                }
            }
        });
    }
}