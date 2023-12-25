package com.db.calculatarelock.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.db.calculatarelock.R;
import com.db.calculatarelock.nav_Activitys.FullImageActivity;

import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter
{
    FullImageActivity fullImageActivity;
    ArrayList<Uri> imageUris;
    public ImagePagerAdapter(FullImageActivity fullImageActivity, ArrayList<Uri> imageUris) {
        this.fullImageActivity = fullImageActivity;
        this.imageUris=imageUris;
    }

    @Override
    public int getCount() {
        return imageUris.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(fullImageActivity).inflate(R.layout.item_img_pager,container,false);
        container.addView(view);

        ImageView imageView = view.findViewById(R.id.itemimgpager);

        imageView.setImageURI(imageUris.get(position));

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
