package es.usc.citius.triapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.TriApp;
import es.usc.citius.triapp.adapters.DiscriminatorAdapter;
import es.usc.citius.triapp.adapters.SlideLevelAdapter;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.Discriminator;
import es.usc.citius.triapp.data.manchester.FlowChart;
import es.usc.citius.triapp.fragments.ReportFragment;

public class StartTriageActivity extends AppCompatActivity implements DiscriminatorAdapter.AdapterCallback{

    private ViewPager viewPager;
    private SlideLevelAdapter mAdapter;
    private Toolbar mToolbar;
    private static final String TAG = "StartTriageActivity";


    //Callback que no uso
    @Override
    public void onMethodCallback(int color) {
        Log.v(TAG, "Color: " + color);
        /*viewPager = (ViewPager) findViewById(R.id.pager);
        ReportFragment = mAdapter.getItem(4)(ReportFragment).setcolor(color);*/
        // do something
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triar);


        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);

        int id= (int)getIntent().getExtras().getInt("id");

        mAdapter = new SlideLevelAdapter(getSupportFragmentManager(), id);

        viewPager.setAdapter(mAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbarlayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void endTriage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, TriApp.class);
        List<Discriminator> questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();
        for (Discriminator question : questions) {
            if (question.getAnswer()) {
                question.setAnswer(Boolean.FALSE);
            }
        }

        this.finish();
        startActivity(intent);

    }

    public void onDiscriminatorSelected(int color) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
    }

}
