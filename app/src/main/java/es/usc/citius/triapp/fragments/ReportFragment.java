package es.usc.citius.triapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.Discriminator;
import es.usc.citius.triapp.data.manchester.FlowChart;
import es.usc.citius.triapp.data.manchester.Level;

public class ReportFragment extends Fragment {

    private static final String TAG = "ReportFragment";
    private static FlowChart flowchart = null;
    private static Level level = null;
    private List<Discriminator> questions;
    private static int color;
    //OnHeadlineSelectedListener mCallback;

    public ReportFragment() {
        // Required empty public constructor
    }

    //No llego hasta aquí
   /* public void setColor(int color) {
        this.color = color;
        Log.v(TAG, "Color: " + color);
    }*/

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


            if (question.getAnswer()) {
                Log.v(TAG, "Nivel / Pregunta / Respuesta: " + Manchester.getCurrentLevel() + " / " + question.getDiscriminator() + " / " + question.getAnswer());
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        flowchart = Manchester.getCurrentWorkFlow();
        Log.v(TAG, "Nombre: " + flowchart.getNombre());


        questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();
        Log.v(TAG, "Color: " + Manchester.getCurrentLevel());
        for (Discriminator question : questions) {


            if (question.getAnswer()) {
                Log.v(TAG, "Nivel / Pregunta / Respuesta: " + Manchester.getCurrentLevel() + " / " + question.getDiscriminator() + " / " + question.getAnswer());
            }
        }

        return inflater.inflate(R.layout.fragment_report, container, false);
    }



}
