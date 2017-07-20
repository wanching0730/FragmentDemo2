package com.example.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by James Ooi on 12/7/2017.
 */

public class OutputFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_output, container, false);


    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        double power = bundle.getDouble(MainActivity.BUNDLE_POWER);

        final EditText etPower = (EditText)getActivity().findViewById(R.id.power);
        etPower.setText(Double.toString(power));
    }
}
