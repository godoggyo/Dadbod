package com.hackathon.dadbod.dadbod;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends FragmentActivity implements FragmentChangeListener {
    ViewPager viewPager;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);

        fm = getSupportFragmentManager();

    }

    public void setCurrentItem(int item, boolean smoothScroll){
        viewPager.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void replaceFragment(Activity_Tags activityTag) {
        FragmentTransaction ftransaction = fm.beginTransaction();
        if(activityTag == Activity_Tags.FRAGMENT_PROFILE){
            viewPager.setVisibility(View.GONE);
            ftransaction.replace(R.id.contentFragment, new ProfileFragment());
            ftransaction.commit();
        }
        else if(activityTag == Activity_Tags.FRAGMENT_CHAT){
            viewPager.setVisibility(View.GONE);
            ftransaction.replace(R.id.contentFragment, new ProfileFragment());
            ftransaction.commit();
        }
        else if(activityTag == Activity_Tags.FRAGMENT_PAGE){
            viewPager.setCurrentItem(1);
        }

    }
}