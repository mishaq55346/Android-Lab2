package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PageActivity extends AppCompatActivity {

    Bitmap[] pics;
    List<PageFragment> cards = new ArrayList<>();
    ArrayList<Tech> techs;
    private ViewPager mPager;
    int position;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        techs = intent.getParcelableArrayListExtra("data");
        pics = new Bitmap[techs.size()];
        for (int i = 0; i < techs.size(); i++) {
            cards.add(new PageFragment(techs.get(i).graphic, techs.get(i).helpText));
        }
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.view_pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(position);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return cards.get(position);
        }

        @Override
        public int getCount() {
            return cards.size();
        }
    }

}
