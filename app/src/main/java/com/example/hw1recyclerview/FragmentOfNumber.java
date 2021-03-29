package com.example.hw1recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentOfNumber extends Fragment {

    private String num;
    private int color;

    public FragmentOfNumber() {
    }

    public void SetArguments(String num, int color) {
        this.num = num;
        this.color = color;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.number_fr, container, false);
        TextView numRV = view.findViewById(R.id.number);
        numRV.setText(num);
        numRV.setTextColor(color);
        return view;

    }
}

