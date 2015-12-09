package es.usc.citius.triapp.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.FlowChart;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlueLevelFragment extends Fragment {


    public BlueLevelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_blue_level, container, false);
    }

    @Override
    public void onResume() {

        super.onResume();
        FlowChart flowchart = Manchester.getCurrentWorkFlow();

        TextView name = (TextView)getView().findViewById(R.id.name);
        name.setText(String.format("Flowchar: %s", flowchart.getNombre()));

        TextView level = (TextView)getView().findViewById(R.id.level);
        level.setText("Level: Blue");

        /*TextView elapsedTime = (TextView)getView().findViewById(R.id.elapsedTime);
        elapsedTime.setText(String.format("Elapsed time: %ds", Manchester.getElapsedTime(System.currentTimeMillis())));*/

        Button button = (Button) getView().findViewById(R.id.endTriage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReport();
            }
        });

    }

    private void goToReport() {
        ViewPager v = (ViewPager) getActivity().findViewById(R.id.pager);
        //Manchester.setCurrentLevel(4);
        v.setCurrentItem(5);
    }

}
