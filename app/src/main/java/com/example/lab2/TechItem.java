package com.example.lab2;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
public class TechItem{
    TextView name;
    ImageView pic;
    public TechItem(@NonNull View itemView) {
        name = itemView.findViewById(R.id.frag_tech_name);
        pic = itemView.findViewById(R.id.frag_tech_img);
    }
}
