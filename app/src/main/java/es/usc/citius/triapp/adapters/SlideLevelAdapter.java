package es.usc.citius.triapp.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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


    private String[] titulo = {"rojo","amarillo", "verde", "azul", "informe"};
    private static final String TAG = "SlideLevelAdapter";
    private static int levels;
    private static FlowChart flowchart = null;

    public SlideLevelAdapter(FragmentManager fm, int currentWorkFlow) {
        super(fm);
        Manchester.setCurrentWorkFlow(currentWorkFlow);
        flowchart = Manchester.getCurrentWorkFlow();
        levels = flowchart.getLevelsSize();

        //Añado un ultimo level para el report case 4
        levels++;
    }

    @Override
    public Fragment getItem(int index) {

        Bundle args = new Bundle();
        LevelFragment level = new LevelFragment();


        //Añadir un nuevo tab con layout distinto para el report final
        // Crear un nuevo fragment para esto, no reciclar LeverFragment porque
        // el layout no sirve
        // Tener en cuenta de que no siempre hay el mismo numero de tabs, en principio hace falta más de 1 switch

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
                args.putInt("color", 2);
                level.setArguments(args);
                return level;
            case 3:
                args.putInt("color", 3);
                level.setArguments(args);
                return level;
            case 4:
                ReportFragment report = new ReportFragment();
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