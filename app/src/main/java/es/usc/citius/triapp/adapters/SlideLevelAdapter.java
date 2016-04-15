package es.usc.citius.triapp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.fragments.BlueLevelFragment;
import es.usc.citius.triapp.fragments.LevelFragment;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.FlowChart;
import es.usc.citius.triapp.fragments.ReportFragment;


public class SlideLevelAdapter extends FragmentPagerAdapter {


    private String[] titulo = {"RED","ORANGE", "YELLOW", "GREEN", "BLUE", "REPORT"};
    private static final String TAG = "SlideLevelAdapter";
    private static int levels;

    Drawable myDrawable;
    String title;
    Context c;

    public SlideLevelAdapter(FragmentManager fm, int currentWorkFlow, Context c) {
        super(fm);
        //Empieza a contar el tiempo de triaje
        this.c = c;
        Manchester.setStartTime(System.currentTimeMillis());
        Manchester.setCurrentWorkFlow(currentWorkFlow);
        Manchester.setCurrentLevel(4); //El nivel de gravedad por defecto es 4 = azul
        FlowChart flowchart = Manchester.getCurrentWorkFlow();
        levels = flowchart.getLevelsSize();

        //Añado un ultimo level para el report case 4

        //levels++;
        levels = levels + 2;

        switch (levels) {

            case 4:
                titulo[0] = "RED1........................................................................";
                titulo[1] = "ORANGE";
                titulo[2] = "BLUE";
                titulo[3] = "REPORT";
                break;
            case 5:
                titulo[0] = "RED2........................................................................";
                titulo[1] = "ORANGE";
                titulo[2] = "YELLOW";
                titulo[3] = "BLUE";
                titulo[4] = "REPORT";
                break;
        }
    }

    @Override
    public Fragment getItem(int index) {

        Bundle args = new Bundle();
        LevelFragment level = new LevelFragment();
        BlueLevelFragment blueLevel = new BlueLevelFragment();
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
                if (levels == 4)
                    return blueLevel;

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
                return blueLevel;

            case 5:
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

        // Añadido en styles.xml <item name="textAllCaps">false</item> si no no funcionan las imagenes


       switch (position) {
            case 0:

                myDrawable = c.getDrawable(R.drawable.ic_trending_flat_black_24dp);
                title = c.getString(R.string.red);
                break;
            case 1:
                myDrawable = c.getDrawable(R.drawable.ic_trending_flat_black_24dp);
                title = c.getString(R.string.red);
                break;
            case 2:
                myDrawable = c.getDrawable(R.drawable.ic_trending_flat_black_24dp);
                title = c.getString(R.string.red);
                break;
            case 3:
                myDrawable = c.getDrawable(R.drawable.ic_trending_flat_black_24dp);
                title = c.getString(R.string.red);
                break;
            case 4:
                myDrawable = c.getDrawable(R.drawable.ic_trending_flat_black_24dp);
                title = c.getString(R.string.red);
                break;
           case 5:
               myDrawable = c.getDrawable(R.drawable.ic_action_add);
               title = c.getString(R.string.red);
               break;
            default:
                break;
        }

        SpannableStringBuilder sb = new SpannableStringBuilder(" " + titulo[position]); // space added before text for convenience

        myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
        sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;

        //return titulo[position];
    }

}