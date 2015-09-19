package com.strangecreation.sim.ciphergame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements TalkyFragment.OnTalkyFragmentListener{

    @Override
    public void switchFragment(Fragment fragment) {

        Log.d("1", "fragment switch reached");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Log.d("1", "fragment transaction assigned");

        transaction.replace(R.id.fragment_container, fragment);
        Log.d("1", "fragment replaced");
        transaction.addToBackStack(null);
        Log.d("1", "current fragment added to back stack");

        transaction.commit();
        Log.d("1", "transaction committed");
    }

    @Override
    public void toast(String msg) {
        Log.d("1","toast reached");

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }
        if(savedInstanceState != null){
            return;
        }

        MainActivityFragment mainFragment = new MainActivityFragment();
        mainFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,mainFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
