package es.usc.citius.triapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.Dictionary;
import es.usc.citius.triapp.data.manchester.Discriminator;
import es.usc.citius.triapp.data.manchester.FlowChart;
import es.usc.citius.triapp.data.manchester.Level;

public class ReportFragment extends Fragment {

    private static final String TAG = "ReportFragment";
    private static FlowChart flowchart = null;
    private static Level level = null;
    private List<Discriminator> questions;
    private String info;


    //OnHeadlineSelectedListener mCallback;

    public ReportFragment() {
        // Required empty public constructor
    }


    public static ReportFragment newInstance() {
        ReportFragment fragment = new ReportFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();
        Log.v(TAG, "Color: " + Manchester.getCurrentLevel());
        for (Discriminator question : questions) {


           /* if (question.getAnswer()) {
                Log.v(TAG, "Nivel / Pregunta / Respuesta: " + Manchester.getCurrentLevel() + " / " + question.getDiscriminator() + " / " + question.getAnswer());
            }*/
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View V = inflater.inflate(R.layout.fragment_report, container, false);

        flowchart = Manchester.getCurrentWorkFlow();

        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    public void onResume() {

        super.onResume();

        TextView name = (TextView)getView().findViewById(R.id.name);
        name.setText(flowchart.getNombre());

        TextView level = (TextView)getView().findViewById(R.id.level);
        int nivel = Manchester.getCurrentLevel();
        //level.setText(nivel);
       /* switch (nivel) {
            case 0:
                level.setText("Red");
            case 1:
                level.setText("Orange");
            case 2:
                level.setText("Yellow");
            case 3:
                level.setText("Green");
        }*/

        if (nivel == 0)
            level.setText("Red");
        if (nivel == 1)
            level.setText("Orange");
        if (nivel == 2)
            level.setText("Yellow");
        if (nivel == 3)
            level.setText("Green");

        questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();
        for (Discriminator question : questions) {
            Log.v(TAG, "Nivel / Pregunta / Respuesta: " + Manchester.getCurrentLevel() + " / " + question.getDiscriminator() + " / " + question.getAnswer());
            if (question.getAnswer()) {
                TextView discriminator = (TextView)getView().findViewById(R.id.discriminator);
                discriminator.setText(question.getDiscriminator());
                //level.setText(""+Manchester.getCurrentLevel());
                info = question.getDescription();
                Log.v(TAG, "Final: " + Manchester.getCurrentLevel() + " / " + question.getDiscriminator() + " / " + question.getAnswer() + "/" + question.getDescription());
            }
        }

        TextView description = (TextView)getView().findViewById(R.id.description);
        description.setText(info);


    }



}
