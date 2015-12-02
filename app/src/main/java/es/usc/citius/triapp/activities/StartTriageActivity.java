package es.usc.citius.triapp.activities;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.adapters.DiscriminatorAdapter;
import es.usc.citius.triapp.adapters.SlideLevelAdapter;

public class StartTriageActivity extends AppCompatActivity implements DiscriminatorAdapter.AdapterCallback{

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

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        int id= getIntent().getExtras().getInt("id");

        SlideLevelAdapter mAdapter = new SlideLevelAdapter(getSupportFragmentManager(), id);

        viewPager.setAdapter(mAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbarlayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    /*public void endTriage(View view) {
        Intent intent = new Intent(this, TriApp.class);
        List<Discriminator> questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();
        for (Discriminator question : questions) {
            if (question.getAnswer()) {
                question.setAnswer(Boolean.FALSE);
            }
        }

        Manchester.setStartTime(0);

        this.finish();
        startActivity(intent);

    }*/

}
