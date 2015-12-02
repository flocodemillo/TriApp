package es.usc.citius.triapp.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.util.Log;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.fragments.LevelFragment;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.FlowChart;
import es.usc.citius.triapp.fragments.ReportFragment;

/**
 * Created by vasily on 27/5/15.
 */

public class SlideLevelAdapter extends FragmentPagerAdapter {


    private String[] titulo = {"RED","ORANGE", "YELLOW", "GREEN", "REPORT"};
    private static final String TAG = "SlideLevelAdapter";
    private static int levels;
    private static FlowChart flowchart = null;

    public SlideLevelAdapter(FragmentManager fm, int currentWorkFlow) {
        super(fm);
        Manchester.setCurrentWorkFlow(currentWorkFlow);
        Manchester.setCurrentLevel(4); //El nivel de gravedad por defecto es 4 = azul
        flowchart = Manchester.getCurrentWorkFlow();
        levels = flowchart.getLevelsSize();

        //Añado un ultimo level para el report case 4

        levels++;

        switch (levels) {

            case 3:
                titulo[0] = "RED";
                titulo[1] = "ORANGE";
                titulo[2] = "REPORT";
                break;
            case 4:
                titulo[0] = "RED";
                titulo[1] = "ORANGE";
                titulo[2] = "YELLOW";
                titulo[3] = "REPORT";
                break;
        }
    }

    @Override
    public Fragment getItem(int index) {

        Bundle args = new Bundle();
        LevelFragment level = new LevelFragment();
        ReportFragment report = new ReportFragment();


        Log.v(TAG, "Index del switch: " + index);


        switch (index) {
            case 0:
                args.putInt("color", 0);
                level.setArguments(args);
                return level;

            case 1:
                args.putInt("color", 1);
                level.setArguments(args);
                return level;

            case 2:
                if (levels == 3)
                    return report;

                args.putInt("color", 2);
                level.setArguments(args);
                return level;

            case 3:
                if (levels == 4)
                    return report;

                args.putInt("color", 3);
                level.setArguments(args);
                return level;

            case 4:
                return report;
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return levels;
    }

    public CharSequence getPageTitle(int position) {
        return titulo[position];
    }

}