package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<Tech> techs;
    TechAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        techs = intent.getParcelableArrayListExtra("data");
        adapter = new TechAdapter(this, techs);
        listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,  int position, long id) {
                Intent intent_page = new Intent(ListActivity.this, PageActivity.class);
                intent_page.putExtra("position", position);
                intent_page.putParcelableArrayListExtra("data", techs);
                startActivity(intent_page);
            }
        });
    }

}
