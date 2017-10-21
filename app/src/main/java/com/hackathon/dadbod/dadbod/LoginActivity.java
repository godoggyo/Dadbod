package com.hackathon.dadbod.dadbod;




import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;


/**
 * Created by iowaf on 10/20/2017.
 */

public class LoginActivity extends FragmentActivity implements FragmentChangeListener {

    private FragmentManager fm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fm = getFragmentManager();
        FragmentTransaction ftransaction = fm.beginTransaction();

        ftransaction.add(R.id.contentFragment, new FragmentSignIn());
        ftransaction.commit();
    }

    @Override
    public void replaceFragment(Activity_Tags activityTag) {
        FragmentTransaction ftransaction = fm.beginTransaction();
        if(activityTag == Activity_Tags.FRAGMENT_SIGN_UP){
            ftransaction.replace(R.id.contentFragment, new FragmentSignUp());
            ftransaction.commit();
        }
    }
}
