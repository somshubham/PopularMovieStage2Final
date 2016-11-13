package com.movie.som.popularmoviestage2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.facebook.stetho.Stetho;

public class MainActivity extends ActionBarActivity {
    public static  boolean mTwoPane;
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivityFragment())
                    .commit();
        }


        if (findViewById(R.id.container2) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container2, new MovieDetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.container, new MainActivityFragment())
//                        .commit();


            }
        } else {
            mTwoPane = false;
        }





    }




    public boolean isTablet() {
        return mTwoPane;
    }





    public void replaceFragment(String[] data) {
        Log.i("tab", "replace");
        Bundle args = new Bundle();
        args.putString("ARGUMENTS", "Created from MainActivity");
        args.putString("title", data[0]);
        args.putString("overview", data[1]);
        args.putString("vote_average", data[4]);
        args.putString("release_date", data[5]);
        args.putString("url", data[2]);
        args.putString("id", data[6]);
        args.putString("url2", data[3]);

        MovieDetailFragment detailFragment = new MovieDetailFragment();
        detailFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container2, detailFragment).commit();
    }











}
