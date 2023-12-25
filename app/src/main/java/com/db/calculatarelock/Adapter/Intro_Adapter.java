package com.db.calculatarelock.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.db.calculatarelock.IntroActivity;
import com.db.calculatarelock.R;

public class Intro_Adapter extends PagerAdapter
{
    IntroActivity introActivity;

    public Intro_Adapter(IntroActivity introActivity) {
        this.introActivity = introActivity;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(introActivity).inflate(R.layout.item_intro,container,false);
        container.addView(view);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView title = view.findViewById(R.id.textView3);
        TextView desc = view.findViewById(R.id.textView4);

        if(position==0)
        {
            imageView.setImageResource(R.drawable.intro1);
            title.setText("Lock Apps");
            desc.setText("Stop Privacy Leakage");
        }
        else if(position==1)
        {
            imageView.setImageResource(R.drawable.intro2);
            title.setText("Lock Files");
            desc.setText("Stop Spying eyes");
        }
        else if (position==2)
        {
            imageView.setImageResource(R.drawable.intro3);
            title.setText("Calculatare Lock");
            desc.setText("Always keep you safe");
        }

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
