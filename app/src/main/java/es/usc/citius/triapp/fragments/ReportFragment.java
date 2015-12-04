package es.usc.citius.triapp.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.TriApp;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.Discriminator;
import es.usc.citius.triapp.data.manchester.FlowChart;

public class ReportFragment extends Fragment {

    private static final String TAG = "ReportFragment";

    //OnHeadlineSelectedListener mCallback;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View V = inflater.inflate(R.layout.fragment_report, container, false);

        return inflater.inflate(R.layout.fragment_report, container, false);
    }


    @Override
    public void onResume() {

        super.onResume();
        Log.v(TAG, "Color: " + Manchester.getCurrentLevel());

        FlowChart flowchart = Manchester.getCurrentWorkFlow();
        int nivel = Manchester.getCurrentLevel();

        if (nivel != 4) {
            List<Discriminator> questions = (Manchester.getCurrentWorkFlow()).getLevel(Manchester.getCurrentLevel()).getDiscriminators();

            for (Discriminator question : questions) {
                Log.v(TAG, "Nivel / Pregunta / Respuesta: " + Manchester.getCurrentLevel() + " / " + question.getDiscriminator() + " / " + question.getAnswer());
                if (question.getAnswer()) {
                    TextView discriminator = (TextView) getView().findViewById(R.id.discriminator);
                    discriminator.setText(String.format("Discriminator: %s", question.getDiscriminator()));
                    String info = question.getDescription();
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
            level.setText(R.string.level_red);
        if (nivel == 1)
            level.setText(R.string.level_orange);
        if (nivel == 2)
            level.setText(R.string.level_yellow);
        if (nivel == 3)
            level.setText(R.string.level_green);
        if (nivel == 4)
            level.setText(R.string.level_blue);

        TextView elapsedTime = (TextView)getView().findViewById(R.id.elapsedTime);
        elapsedTime.setText(String.format("Elapsed time: %ds", Manchester.getElapsedTime(System.currentTimeMillis())));

        Button button = (Button) getView().findViewById(R.id.endTriage);
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
        //builder.setTitle(getString(R.string.dialog_confirm));
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent intent = new Intent(getActivity(), TriApp.class);

                if (Manchester.getCurrentLevel() != 4) {
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
