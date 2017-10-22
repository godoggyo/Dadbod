package com.hackathon.dadbod.dadbod;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iowaf on 10/21/2017.
 */

public class FragmentSignUp extends Fragment {

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private final String TAG = "";

    private Button cAccount;

    private EditText registrationEmail, registrationPassword, registrationName, registrationOccupation, reistrationCollege,
                        registrationAge;

    public FragmentSignUp(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        cAccount = (Button) v.findViewById(R.id.bCreateAccount);
        registrationEmail = (EditText) v.findViewById(R.id.etEmail);
        registrationPassword = (EditText) v.findViewById(R.id.etPassword);

        registrationName = (EditText) v.findViewById(R.id.etName);
        registrationOccupation = (EditText) v.findViewById(R.id.etOccupation);
        reistrationCollege = (EditText) v.findViewById(R.id.etCollege);
        registrationAge = (EditText) v.findViewById(R.id.etAge);


        cAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(registrationEmail.getText().toString(), registrationPassword.getText().toString());
                Toast.makeText(getActivity(), "Account Successfully Created", Toast.LENGTH_LONG).show();
                ActivityFragmentListener fcl = (ActivityFragmentListener) getActivity();
                fcl.replaceFragment(Activity_Tags.FRAGMENT_SIGN_IN);
            }
        });

        return v;
    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(getActivity(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                            String name = registrationName.getText().toString();
                            String occupation = registrationOccupation.getText().toString();
                            String college = reistrationCollege.getText().toString();
                            String age = registrationAge.getText().toString();

                            //Add users info to the Firebase
                            Map userData = new HashMap();
                            userData.put("Name", name);
                            userData.put("Occupation", occupation);
                            userData.put("College", college);
                            userData.put("Age", age);

                            current_user_db.setValue(userData);
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
