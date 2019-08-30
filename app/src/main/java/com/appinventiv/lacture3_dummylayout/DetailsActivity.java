package com.appinventiv.lacture3_dummylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailsActivity extends AppCompatActivity {

    TextView details;
    Button btn_goToMainActivity;
    String name_detail, email_detail, gender_detail, hobbies_detail;

    SharedPreferences sharedPreferences;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initialiseId();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Press for Home");
        getSupportActionBar().setSubtitle("Welcome to the Dark World!");

        //showDataViaSharedPreference();
        //showDataViaIntent();
        dbHelper = new DBHelper(this);

        showDataBySQLiteDatabase();

        btn_goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void showDataBySQLiteDatabase() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList = dbHelper.getAllValidationData();
        name_detail = arrayList.get(1);
        email_detail = arrayList.get(2);
        gender_detail = arrayList.get(3);
        hobbies_detail = arrayList.get(4);

        details.setText("Name :-  "+ name_detail+ "\n"+
                "Email :-  " +email_detail+ "\n"+
                "Gender :-  " +gender_detail+ "\n"+
                "Hobbies :-  " +hobbies_detail+ "\n"
        );
    }

    private void initialiseId() {
        btn_goToMainActivity = findViewById(R.id.btn_detail);
        details = (TextView) findViewById(R.id.textView_Deatails);
        sharedPreferences = getSharedPreferences("Hello", Context.MODE_PRIVATE);
    }


    private void showDataViaSharedPreference() {

      name_detail = sharedPreferences.getString("name","");
      email_detail = sharedPreferences.getString("email","");
      gender_detail = sharedPreferences.getString("gender","");
      hobbies_detail = sharedPreferences.getString("hobbies","");

      details.setText("Name :-  "+ name_detail+ "\n"+
               "Email :-  " +email_detail+ "\n"+
               "Gender :-  " +gender_detail+ "\n"+
                "Hobbies :-  " +hobbies_detail+ "\n"
      );

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showDataViaIntent() {

        String dancing="", singing="", gamingg="";

        Bundle data = getIntent().getExtras();

        name_detail = data.getString("name");
        email_detail = data.getString("email");
        gender_detail = data.getString("gender");
        dancing = data.getString("dancing");
        singing = data.getString("singing");
        gamingg = data.getString("gaming");
        hobbies_detail = dancing+ " "+singing+ " "+gamingg;

        details.setText("Name :-  "+ name_detail+ "\n"+
               "Email :-  " +email_detail+ "\n"+
               "Gender :-  " +gender_detail+ "\n"+
                "Hobbies :-  " +hobbies_detail+ "\n"
        );
    }
}
