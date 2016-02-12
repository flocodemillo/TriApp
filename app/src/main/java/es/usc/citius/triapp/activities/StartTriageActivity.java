package es.usc.citius.triapp.activities;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.adapters.DiscriminatorAdapter;
import es.usc.citius.triapp.adapters.SlideLevelAdapter;
import es.usc.citius.triapp.data.Manchester;

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

        SlideLevelAdapter mAdapter = new SlideLevelAdapter(getSupportFragmentManager(), id, getApplicationContext());

        viewPager.setAdapter(mAdapter);

        //Añade el nombre del workflow al toolbar
        TextView toolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolBarTitle.setText(Manchester.getCurrentWorkFlow().getNombre());


        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbarlayout);
        tabLayout.setupWithViewPager(viewPager);
    }


    public void endTriage(View view) {

    }

}
