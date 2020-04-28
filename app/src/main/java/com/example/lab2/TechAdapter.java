package com.example.lab2;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TechAdapter extends BaseAdapter {
    TechItem holder;
    private ArrayList<Tech> techs;
    private LayoutInflater layoutInflater;
    private Context context;
    Bitmap[] pics;

    public TechAdapter(Context context, ArrayList techs) {
        this.techs = techs;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        pics = new Bitmap[techs.size()];
        LoadPics loader = new LoadPics();
        loader.execute();
    }

    @Override
    public int getCount() {
        return techs.size();
    }

    @Override
    public Object getItem(int position) {
        return techs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_numlist, null);
            holder = new TechItem(convertView);
            holder.pic = convertView.findViewById(R.id.frag_tech_img);
            holder.name = convertView.findViewById(R.id.frag_tech_name);
            convertView.setTag(holder);
        } else {
            holder = (TechItem) convertView.getTag();
        }
        holder.name.setText(techs.get(position).techName);
        if (pics[position] != null)
            holder.pic.setImageBitmap(pics[position]);
        else
            holder.pic.setImageResource(R.drawable.chicken);
        return convertView;
    }


    class LoadPics extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            for (int i = 0; i < techs.size(); i++) {
                pics[i] = (ImageManager.downloadImage(techs.get(i).graphic));
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            notifyDataSetChanged();
        }
    }
}
