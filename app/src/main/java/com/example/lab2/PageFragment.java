package com.example.lab2;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
public class PageFragment extends Fragment {
    private final static String IMAGE_URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
    TextView desc;
    ImageView img;
    String desc_str;
    String pic_name;
    PageFragment(String pic_name, String desc_str){
        this.desc_str = desc_str;
        this.pic_name = pic_name;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_page, container, false);
        desc = rootView.findViewById(R.id.page_desc);
        img = rootView.findViewById(R.id.page_pic);
        if (desc_str == null)
            desc.setText("No description for tech");
        else
            desc.setText(desc_str);
        Glide.with(this).load(IMAGE_URL + pic_name).into(img);
        Log.d("download_pics", pic_name);
        return rootView;
    }
}