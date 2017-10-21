package com.hackathon.dadbod.dadbod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class PageFragment extends Fragment {
    TextView textView;

    public PageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.page_fragment_layout,container,false);
        textView = (TextView)view.findViewById(R.id.editText);
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        textView.setText("This is Dad # " +message+ "");
        ImageView myImageView = (ImageView) view.findViewById(R.id.imageView9);
        myImageView.setImageResource(R.drawable.dad);
        return view;
    }

}
