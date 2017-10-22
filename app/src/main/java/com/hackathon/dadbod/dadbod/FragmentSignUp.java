package com.hackathon.dadbod.dadbod;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by iowaf on 10/21/2017.
 */

public class FragmentSignUp extends Fragment {

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private StorageReference mSrorageRef;

    private final String TAG = "";

    private final int GALLERY_INTENT = 2;

    private Button cAccount, mSelectImage;

    private EditText registrationEmail, registrationPassword, registrationName, registrationOccupation, reistrationCollege,
                        registrationAge;

    private Uri profilePicUri;

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

        mSelectImage = (Button) v.findViewById(R.id.bUploadProfileImage);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, GALLERY_INTENT);
            }

        });

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
                            Toast.makeText(getActivity(), "Account Creation Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String user_id = mAuth.getCurrentUser().getUid();
                            mSrorageRef = FirebaseStorage.getInstance().getReference().child("ProfilePics");
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                            String name = registrationName.getText().toString();
                            String occupation = registrationOccupation.getText().toString();
                            String college = reistrationCollege.getText().toString();
                            String age = registrationAge.getText().toString();

                            StorageReference filepath = mSrorageRef.child("profilePic" + user_id);

                            filepath.putFile(profilePicUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println("profile picture upload failed");
                                }
                            });

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

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            profilePicUri = data.getData();

            Toast.makeText(getActivity(), "File Recieved", Toast.LENGTH_LONG).show();

        }
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
