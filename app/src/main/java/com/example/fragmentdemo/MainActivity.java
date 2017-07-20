package com.example.fragmentdemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

// When using Fragments, Activities that contain Fragments extends FragmentActivity
public class MainActivity extends FragmentActivity implements InputFragment.OnButtonClickedListener {

    public final static String BUNDLE_POWER = "com.example.fragmentdemo.POWER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            InputFragment inputFragment = new InputFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            inputFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, inputFragment).commit();
        }
    }

    public void onButtonClicked(double current, double voltage) {
        // calculate power
        double power = current * voltage;

        // Create fragment
        OutputFragment outputFragment = new OutputFragment();
        Bundle args = new Bundle();
        args.putDouble(BUNDLE_POWER, power);
        outputFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, outputFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
