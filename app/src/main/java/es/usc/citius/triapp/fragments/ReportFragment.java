package es.usc.citius.triapp.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.TriApp;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.Dictionary;
import es.usc.citius.triapp.data.manchester.Discriminator;
import es.usc.citius.triapp.data.manchester.FlowChart;
import es.usc.citius.triapp.data.manchester.Level;

public class ReportFragment extends Fragment {

    private static final String TAG = "ReportFragment";
    private static FlowChart flowchart = null;
    private List<Discriminator> questions;
    private String info;
    //int nivel = 4; //Por defecto el nivel de gravedad es 4 = azul


    //OnHeadlineSelectedListener mCallback;

    public ReportFragment() {
        // Required empty public constructor
    }


   /* public static ReportFragment newInstance() {
        ReportFragment fragment = new ReportFragment();

        return fragment;
    }*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Manchester.getCurrentLevel();
        //Log.v(TAG, "Color: " + Manchester.getCurrentLevel());
        //questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();
        //Log.v(TAG, "Color: " + Manchester.getCurrentLevel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View V = inflater.inflate(R.layout.fragment_report, container, false);



        return inflater.inflate(R.layout.fragment_report, container, false);
    }


    @Override
    public void onResume() {

        super.onResume();
        Log.v(TAG, "Color: " + Manchester.getCurrentLevel());

        flowchart = Manchester.getCurrentWorkFlow();
        int nivel = Manchester.getCurrentLevel();

        if (nivel != 4) {
            questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();

            for (Discriminator question : questions) {
                Log.v(TAG, "Nivel / Pregunta / Respuesta: " + Manchester.getCurrentLevel() + " / " + question.getDiscriminator() + " / " + question.getAnswer());
                if (question.getAnswer()) {
                    TextView discriminator = (TextView) getView().findViewById(R.id.discriminator);
                    discriminator.setText(String.format("Discriminator: %s", question.getDiscriminator()));
                    //level.setText(""+Manchester.getCurrentLevel());
                    info = question.getDescription();
                    Log.v(TAG, "Final: " + Manchester.getCurrentLevel() + " / " + question.getDiscriminator() + " / " + question.getAnswer() + "/" + question.getDescription());
                    TextView description = (TextView)getView().findViewById(R.id.description);
                    description.setText(String.format("Description: %s", info));
                }
            }
        }

        TextView name = (TextView)getView().findViewById(R.id.name);
        name.setText(String.format("Flowchar: %s", flowchart.getNombre()));

        TextView level = (TextView)getView().findViewById(R.id.level);
        if (nivel == 0)
            level.setText("Level: Red");
        if (nivel == 1)
            level.setText("Level: Orange");
        if (nivel == 2)
            level.setText("Level: Yellow");
        if (nivel == 3)
            level.setText("Level: Green");
        if (nivel == 4)
            level.setText("Level: Blue");


        TextView elapsedTime = (TextView)getView().findViewById(R.id.elapsedTime);
        elapsedTime.setText(String.format("Elapsed time: %ds", Manchester.getElapsedTime(System.currentTimeMillis())));
        //description.setText("" + SystemClock.elapsedRealtime());

        Button button = (Button) (Button)getView().findViewById(R.id.endTriage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowAlertDialog();
            }
        });

    }

    private void createAndShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent intent = new Intent(getActivity(), TriApp.class);

                if (Manchester.getCurrentLevel()!=4) {
                    List<Discriminator> questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();
                    for (Discriminator question : questions) {
                        if (question.getAnswer()) {
                            question.setAnswer(Boolean.FALSE);
                        }
                    }
                }

                Manchester.setStartTime(0);

                getActivity().finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
