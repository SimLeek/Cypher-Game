package com.strangecreation.sim.ciphergame;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the interface
 * to handle interaction events.
 * Use the {@link fragment_level_select#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TalkyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    public TalkyFragment() {
        // Required empty public constructor
    }

    public interface OnTalkyFragmentListener {
        // TODO: add fading transition via opacity=(-x^n + t^n)/(t^n) w/ 1=full opacity
        //n=2 and t=0.25s seems good to test
        //also try ((cos(t*pi)+1)^n)/(2^n)
        //or   try (((cos(t*pi)-1)^n)+2^n)/(2^n) where n is odd

        void switchFragment(Fragment fragment);
        void toast(String msg);
    }

}
