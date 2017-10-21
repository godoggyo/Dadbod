package com.hackathon.dadbod.dadbod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    TextView textView;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.chat_fragment_layout,container,false);
        textView = (TextView)view.findViewById(R.id.Chat);
        //ImageView myImageView = (ImageView) view.findViewById(R.id.imageView);
        //myImageView.setImageResource(R.drawable.beerclink);

        return view;
    }

}
