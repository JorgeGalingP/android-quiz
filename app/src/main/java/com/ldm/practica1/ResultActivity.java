package com.ldm.practica1;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResultActivity extends AppCompatActivity {
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        String result = extras.getString("result");

        resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Final points: " + result);

        // set Fab
        FloatingActionButton fab = findViewById(R.id.reloadFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MainActivity.class);

                context.startActivity(intent);
            }
        });
    }
}