package com.strangecreation.sim.ciphergame;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends TalkyFragment
    implements View.OnClickListener{

    private OnTalkyFragmentListener tListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        //Button implementation from: http://stackoverflow.com/a/14571018
        //todo: add button class
        Button startButton = (Button) v.findViewById(R.id.main_menu_button_start);
        startButton.setOnClickListener(this);
        Button toolButton = (Button) v.findViewById(R.id.main_menu_button_tools);
        toolButton.setOnClickListener(this);
        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            tListener = (OnTalkyFragmentListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement OnTalkyFragmentListener");
        }
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.main_menu_button_start:
                fragment_level_select levelSelectFragment =  new fragment_level_select();
                tListener.switchFragment(levelSelectFragment);
                break;
            case R.id.main_menu_button_tools:
                ToolSelectFragment toolSelectFragment =  new ToolSelectFragment();
                tListener.switchFragment(toolSelectFragment);
                break;
        }
    }

}
