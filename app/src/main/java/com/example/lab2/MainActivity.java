package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.loadBar);
        bar.setProgress(0);
        LoadTask loadTask = new LoadTask();
        loadTask.execute();
    }

    class LoadTask extends AsyncTask{
        StringBuilder json_str;
        Scanner sc;
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        ArrayList<Tech> array = new ArrayList<>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setProgress(0);
            json_str = new StringBuilder();
            Resources res = getResources();
            InputStreamReader reader = new InputStreamReader(res.openRawResource(R.raw.tech));
            Log.d("progress", "reader: " + reader.toString());
            sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                json_str.append(sc.nextLine()).append("\n");
            }
            sc.close();
            Log.d("progress", "str size = " + json_str.length());
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            int progress;
            try {
                jsonArray = (JSONArray) parser.parse(String.valueOf(json_str));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d("progress", "size = " + jsonArray.size());
            for (int i = 1; i < jsonArray.size(); i++) {
                JSONObject temp = (JSONObject) jsonArray.get(i);
                String techName = (String)temp.get("name");
                String graphic = (String)temp.get("graphic");
                String helpText = (String)temp.get("helptext");
                array.add(new Tech(techName, graphic, helpText));
                //TimeUnit.MILLISECONDS.sleep(1);
                progress = i + 1;
                publishProgress(progress);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            bar.setProgress((int)values[0]);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent i = new Intent(MainActivity.this, ListActivity.class);
            i.putExtra("data", array);
            startActivity(i);
            finish();
        }
    }

}
