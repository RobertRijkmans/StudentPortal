package com.example.clibby.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.clibby.studentportal.MainActivity.TITLE_STRING;
import static com.example.clibby.studentportal.MainActivity.URL_STRING;

public class NewPortal extends AppCompatActivity {

    private String mURL;
    private String mTitle;
    EditText urlEdit;
    EditText titleEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_portal);
        urlEdit = findViewById(R.id.URL_INPUT);
        titleEdit = findViewById(R.id.TITLE_INPUT);
        Button button = findViewById(R.id.ADD);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mURL = urlEdit.getText().toString();
                mTitle = titleEdit.getText().toString();
                FinishAdding();
            }
        });


    }

    public void FinishAdding(){

        Intent resultIntent = new Intent();
        Bundle extras = new Bundle();
        extras.putString(URL_STRING, mURL);
        extras.putString(TITLE_STRING, mTitle);
        resultIntent.putExtras(extras);
        setResult(Activity.RESULT_OK,resultIntent);
        finish();
    }
}
