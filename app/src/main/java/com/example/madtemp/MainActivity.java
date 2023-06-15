package com.example.madtemp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent rec = getIntent();
        String name = rec.getStringExtra("name");
        String des = rec.getStringExtra("des");
        Boolean follow = rec.getBooleanExtra("fol",false);
        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setText(name);
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
                if (btn.getText() == "UNFOLLOW") {
                    btn.setText("FOLLOW");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();

                } else if (btn.getText() == "FOLLOW") {
                    btn.setText("UNFOLLOW");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}