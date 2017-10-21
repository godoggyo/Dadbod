package com.hackathon.dadbod.dadbod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView textView;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Bundle bundle = getArguments();
        View view = inflater.inflate(R.layout.profile_fragment_layout,container,false);
        textView = (TextView)view.findViewById(R.id.Dad_Name);
        ImageView myimageView = (ImageView) view.findViewById(R.id.Dadbod);
        myimageView.setImageResource(R.drawable.dadbod);
        myimageView = (ImageView) view.findViewById(R.id.ProfilePicture);
        myimageView.setImageResource(R.drawable.dad);
        textView = (TextView)view.findViewById(R.id.Job);
        textView = (TextView)view.findViewById(R.id.Sports);
        textView = (TextView)view.findViewById(R.id.University);
        textView = (TextView)view.findViewById(R.id.Age);
        textView = (TextView)view.findViewById(R.id.Hobbies);
        ImageButton androidImageButton = (ImageButton) view.findViewById(R.id.Back);
        androidImageButton.setImageResource(R.drawable.backarrow);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentChangeListener fcl = (FragmentChangeListener) getActivity();
                fcl.replaceFragment(Activity_Tags.FRAGMENT_PAGE);
            }
        });

        androidImageButton = (ImageButton) view.findViewById(R.id.Chat);
        androidImageButton.setImageResource(R.drawable.mychats);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentChangeListener fcl = (FragmentChangeListener) getActivity();
                fcl.replaceFragment(Activity_Tags.FRAGMENT_PAGE);
            }
        });

        return view;
    }

}
