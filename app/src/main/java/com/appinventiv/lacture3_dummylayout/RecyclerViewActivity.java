package com.appinventiv.lacture3_dummylayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView =  findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showDataInRecyclerView();
    }

    void showDataInRecyclerView(){
        ArrayList<String> name = new ArrayList<>(getIntent().getStringArrayListExtra("NAME"));
        recyclerView.setAdapter(new ProgrammingAdapter(name));
    }
}
