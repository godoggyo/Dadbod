package com.hackathon.dadbod.dadbod;


import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class PageFragment extends Fragment {
    TextView textView;
    TextView textView1;


    public PageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.page_fragment_layout,container,false);
        textView1 = (TextView)view.findViewById(R.id.Name);
        textView = (TextView)view.findViewById(R.id.Job);
        textView = (TextView)view.findViewById(R.id.Sports);
        textView = (TextView)view.findViewById(R.id.School);
        textView = (TextView)view.findViewById(R.id.Age);
        textView = (TextView)view.findViewById(R.id.Hobbies);
        final Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        textView1.setText("This is Dad # " +message+ "");
        ImageView myImageView = (ImageView) view.findViewById(R.id.Title);
        myImageView.setImageResource(R.drawable.dadbod);

        myImageView = (ImageView) view.findViewById(R.id.ProfilePicture);
        myImageView.setImageResource(R.drawable.dad);

        ImageButton androidImageButton = (ImageButton) view.findViewById(R.id.beer);
        androidImageButton.setImageResource(R.drawable.beerclink);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).setCurrentItem(bundle.getInt("count"), true);
            }
        });

        ImageButton androidImageButton2 = (ImageButton) view.findViewById(R.id.dislike);
        androidImageButton2.setImageResource(R.drawable.x);
        androidImageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).setCurrentItem(bundle.getInt("count"), true);
            }
        });

        ImageButton androidImageButton3 = (ImageButton) view.findViewById(R.id.like);
        androidImageButton3.setImageResource(R.drawable.fistbump);
        androidImageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).setCurrentItem(bundle.getInt("count"), true);
            }
        });

        ImageButton androidImageButton4 = (ImageButton) view.findViewById(R.id.back);
        androidImageButton4.setImageResource(R.drawable.backarrow);
        androidImageButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).setCurrentItem(bundle.getInt("count")-2, true);
            }
        });

        ImageButton androidImageButton5 = (ImageButton) view.findViewById(R.id.forward);
        androidImageButton5.setImageResource(R.drawable.forwardarrow);
        androidImageButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).setCurrentItem(bundle.getInt("count"), true);
            }
        });

        ImageButton androidImageButton6 = (ImageButton) view.findViewById(R.id.profile);
        androidImageButton6.setImageResource(R.drawable.myprofile);
        androidImageButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).setCurrentItem(bundle.getInt("count"), true);
            }
        });

        ImageButton androidImageButton7 = (ImageButton) view.findViewById(R.id.chat);
        androidImageButton7.setImageResource(R.drawable.mychats);
        androidImageButton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //((MainActivity)getActivity()).setCurrentItem(bundle.getInt("count"), true);
            }
        });

        return view;
    }

}