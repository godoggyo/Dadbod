package com.hackathon.dadbod.dadbod;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by iowaf on 10/21/2017.
 */

public class FragmentSignIn extends Fragment {

    private Button login;

    private Button register;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private final String TAG = "";

    public FragmentSignIn(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signin, container, false);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        register = (Button) v.findViewById(R.id.bsignup);
        login = (Button) v.findViewById(R.id.blogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Test", Toast.LENGTH_LONG).show();
                //might be bugs with this statement here.
                signIn(v.findViewById(R.id.etEmail).toString(), v.findViewById(R.id.etPassword).toString());
            }
        });
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Test", Toast.LENGTH_LONG).show();
                FragmentChangeListener fcl = (FragmentChangeListener) getActivity();
                fcl.replaceFragment(Activity_Tags.FRAGMENT_SIGN_UP);
            }
        });
        return v;
    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(getActivity(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        if(task.isSuccessful()){
                            Intent myIntent = new Intent(getActivity(), MainActivity.class);
                            startActivity(myIntent);
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
