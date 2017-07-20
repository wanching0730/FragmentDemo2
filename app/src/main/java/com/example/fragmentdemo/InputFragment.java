package com.example.fragmentdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by James Ooi on 12/7/2017.
 */

public class InputFragment extends Fragment {
    OnButtonClickedListener mCallback;
    Button calcPower;

    // Container Activity must implement this interface
    // This is for the Activity to handle the event when the Button in this Fragment is clicked
    public interface OnButtonClickedListener {
        public void onButtonClicked(double current, double voltage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input, container, false);


    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText etCurrent = (EditText)getActivity().findViewById(R.id.current);
        final EditText etVoltage = (EditText)getActivity().findViewById(R.id.voltage);

        calcPower = (Button)getActivity().findViewById(R.id.calc_power);
        calcPower.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                double current = Double.parseDouble(etCurrent.getText().toString());
                double voltage = Double.parseDouble(etVoltage.getText().toString());
                mCallback.onButtonClicked(current, voltage);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnButtonClickedListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnButtonClickedListener");
        }


    }
}
