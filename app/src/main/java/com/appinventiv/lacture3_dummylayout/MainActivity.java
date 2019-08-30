package com.appinventiv.lacture3_dummylayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    EditText editText_Name, editText_Email;
    TextView et_title, tv_count;
    CheckBox c1_singing, c2_dance, c3_gaming;
    RadioGroup gender_Group;
    RadioButton radioButton_Male, radioButton_Female;
    Button btn_Submit;
    String gender, name = "", email = "";
    String singing="", dancing="", gaming="";
    String hobbies;
    boolean check = false;
    LinearLayout linearLayout;

    static int count = 0;
    public  static final int REQUEST_CODE = 2;

    static ArrayList<String> arrayList = new ArrayList<String>(10);

    SharedPreferences sharedPreferences;
    public static final String PREFS_Name ="Pravesh Singh";
    public static final String SCORED = "Score";

    DBHelper dbHelperSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelperSQLite = new DBHelper(this);

        linearLayout = findViewById(R.id.actionbar_linear_layout);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        initialiseId();
        //storeDataValidationViaSQLite();

    }


    private void initialiseId() {
        et_title = findViewById(R.id.tv_title);
        tv_count = findViewById(R.id.tv_main);

        sharedPreferences = getSharedPreferences("Hello", Context.MODE_PRIVATE);

        editText_Email = findViewById(R.id.edt_Email);
        editText_Name = findViewById(R.id.edt_Name);

        c2_dance = findViewById(R.id.checkbox_dancing);
        c1_singing = findViewById(R.id.checkbox_singing);
        c3_gaming = findViewById(R.id.checkbox_Gaming);
        c1_singing.setOnCheckedChangeListener(this);
        c2_dance.setOnCheckedChangeListener(this);
        c3_gaming.setOnCheckedChangeListener(this);

        radioButton_Male = findViewById(R.id.radio_Male);
        radioButton_Female = findViewById(R.id.radio_Female);
        gender_Group = findViewById(R.id.gender_Group);
        gender_Group.setOnCheckedChangeListener(this);

        btn_Submit = findViewById(R.id.btn_Submit);
        btn_Submit.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        count = arrayList.size();
        tv_count.setText( "Count = " + Integer.toString(count));
        if (arrayList.size() >= 10){
            btn_Submit.setText("Proceed for RecyclerView");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        name = editText_Name.getText().toString();
        arrayList.add(name);
    }

    public class DataTransactionThroughInterface implements Interface_Transaction {

        MainActivity data=new MainActivity();

        String name_data = "Default";
        String email_data = "Default";
        String gender_data = "Default";
        String hobbies_data = "Default";

        @Override
        public void setName(String name) {
            name = name_data;
        }

        @Override
        public String getName() {
            return name_data;
        }

        @Override
        public void setEmail(String email) {

            email = email_data;

        }

        @Override
        public String getEmail() {
            return email_data;
        }

        @Override
        public void setGender(String gender) {
            gender = gender_data;

        }

        @Override
        public String getGender() {
            return gender_data;
        }

        @Override
        public void setHobbies(String hobbies) {

            hobbies = hobbies_data;

        }

        @Override
        public String getHobbies() {
            return hobbies_data;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radio_Female :
                gender = "FEMALE";
                name = editText_Name.getText().toString();
                email = editText_Email.getText().toString();
                if (name.equals("")){
                    editText_Name.setError("Please Enter your Name");
                }
                if (email.equals("")){
                    editText_Email.setError("Please Enter Your Email");
                }
                check = true;
                break;

            case R.id.radio_Male :
                gender = "Male";
                name = editText_Name.getText().toString();
                email = editText_Email.getText().toString();
                if (name.equals("")){
                    editText_Name.setError("Please Enter your Name");
                }
                if (email.equals("")){
                    editText_Email.setError("Please Enter Your Email");
                }
                check = true;
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        name = editText_Name.getText().toString();
        email = editText_Email.getText().toString();

        if (isChecked){
            if (compoundButton == c1_singing){
               singing = "Singing";
            }
            if (compoundButton == c2_dance){
                dancing = "Dancing";
            }
            if (compoundButton == c3_gaming){
                gaming = "Gaming";
            }

            if (check && !name.equals("") && !email.equals("")) {
                btn_Submit.setEnabled(true);
            }

            hobbies = singing+ " "+ dancing + " " + gaming;

            //sharedPreferencesSetValue();
            //startActivityForResultImplementation();
            storeDataValidationViaSQLite();

            btn_Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (arrayList.size() >= 10){
                        Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                        intent.putStringArrayListExtra("NAME",arrayList);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        startActivityForResult(intent,REQUEST_CODE);
                    }
                }
            });

        }



        if( !isChecked ) {
            if (compoundButton == c1_singing){
                singing = "";
            }
            if (compoundButton == c2_dance){
                dancing = "";
            }
            if (compoundButton == c3_gaming){
                gaming = "";
            }

            if (singing.equals("") && dancing.equals("") && gaming.equals("")){
                btn_Submit.setEnabled(false);
            }
        }

    }


    private void storeDataValidationViaSQLite() {
        boolean insertCheck = dbHelperSQLite.insertValidationData(name, email, gender, hobbies);
        if (insertCheck == false){
            Toast.makeText(this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        }



    }

    public void startActivityForResultImplementation(){

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataTransactionThroughInterface data = new DataTransactionThroughInterface();
                data.email_data = email;
                data.name_data = name;
                data.hobbies_data = hobbies;
                data.gender_data = gender;

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivityForResult(intent, 2);

            }
        });
//        DataTransactionThroughInterface data = new DataTransactionThroughInterface();
//        data.email_data = email;
//        data.name_data = name;
//        data.hobbies_data = hobbies;
//        data.gender_data = gender;
    }

    public void sharedPreferencesSetValue(){

        sharedPreferences.edit().putString("name",name).apply();
        sharedPreferences.edit().putString("email",email).apply();
        sharedPreferences.edit().putString("hobbies",hobbies).apply();
        sharedPreferences.edit().putString("gender",gender).apply();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "OnActivityResult is working fine", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
