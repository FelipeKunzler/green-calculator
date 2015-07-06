package com.felipekunzler.greencalculator;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen, resizing the image view if necessary.
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ImageView image_view = (ImageView) getActivity().findViewById(R.id.image_view_eccos);
            int pxImg = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics()));
            image_view.getLayoutParams().height = pxImg;
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            ImageView image_view = (ImageView) getActivity().findViewById(R.id.image_view_eccos);
            image_view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
    }
}
