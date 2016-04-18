package es.usc.citius.triapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.TabStepper;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.TriApp;
import es.usc.citius.triapp.adapters.StepSampleAdapter;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.Patients;
import es.usc.citius.triapp.data.manchester.Dictionary;
import es.usc.citius.triapp.data.patients.Patient;
import es.usc.citius.triapp.data.patients.TriageResult;

public class StepSample extends TabStepper {

    private int i = 1;
    private static final String TAG = "StepSample" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setErrorTimeout(1500);
        setLinear(false);
        setTitle("Tab Stepper <small>(Classic Tab) </small>");
        setAlternativeTab(false);
        disabledTouch();

        showPreviousButton();

            //Creo los datos para el nuevo triaje y los asocio con el paciente
            TriageResult data = new TriageResult(Manchester.getCurrentWorkFlow().getNombre(), Patients.getCurrentPatient());
            Patients.getCurrentPatient().addResult(data);
            Patients.getCurrentPatient().setCurrentTriage(data);


            switch (Manchester.getCurrentWorkFlow().getLevels().size()) {
                case 1:
                    addStep(createFragment(new StepSampleAdapter(0)));
                case 2:
                    addStep(createFragment(new StepSampleAdapter(0)));
                    addStep(createFragment(new StepSampleAdapter(1)));

                case 3:
                    addStep(createFragment(new StepSampleAdapter(0)));
                    addStep(createFragment(new StepSampleAdapter(1)));
                    addStep(createFragment(new StepSampleAdapter(2)));
                case 4:
                    addStep(createFragment(new StepSampleAdapter(0)));
                    addStep(createFragment(new StepSampleAdapter(1)));
                    addStep(createFragment(new StepSampleAdapter(2)));
                    addStep(createFragment(new StepSampleAdapter(3)));
            }
        super.onCreate(savedInstanceState);
    }

    private AbstractStep createFragment(AbstractStep fragment) {
        Bundle b = new Bundle();
        b.putInt("position", i++);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onComplete() {

        Patients.getCurrentPatient().getCurrentTriage().setDiscriminator("All OK");
        Patients.getCurrentPatient().getCurrentTriage().setDescription("All OK");
        Patients.getCurrentPatient().getCurrentTriage().setLevel(4);
        Patients.getCurrentPatient().getCurrentTriage().setFinished(true);

        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Resultado")
                .customView(R.layout.dialog_report, true)
                .positiveText("Guardar")
                .negativeText(android.R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Activity host = (Activity) getParent();
                        Intent intent = new Intent(getParent(), TriApp.class);
                        host.startActivity(intent);
                    }
                }).build();


        TextView fcName = (TextView) dialog.getCustomView().findViewById(R.id.fcName);
        fcName.setText(Patients.getCurrentPatient().getCurrentTriage().getFlowChart());
        TextView fcLevel = (TextView) dialog.getCustomView().findViewById(R.id.fcLevel);
        fcLevel.setText(String.valueOf(5));
        TextView discriminator = (TextView) dialog.getCustomView().findViewById(R.id.fcDiscriminator);
        discriminator.setText(Patients.getCurrentPatient().getCurrentTriage().getDiscriminator());
        TextView dDescription = (TextView) dialog.getCustomView().findViewById(R.id.dDescription);
        dDescription.setText(Patients.getCurrentPatient().getCurrentTriage().getDescription());

        TextView date = (TextView) dialog.getCustomView().findViewById(R.id.date);
        GregorianCalendar gc = (GregorianCalendar) Patients.getCurrentPatient().getCurrentTriage().getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        date.setText(sdf.format(gc.getTime()));

        TextView tElapsed = (TextView) dialog.getCustomView().findViewById(R.id.elapsedTime);
        tElapsed.setText(String.valueOf(Patients.getCurrentPatient().getCurrentTriage().getElapsed()));
        TextView pName = (TextView) dialog.getCustomView().findViewById(R.id.pName);
        pName.setText(Patients.getCurrentPatient().getName());
        TextView pDescription = (TextView) dialog.getCustomView().findViewById(R.id.pDescription);
        pDescription.setText(Patients.getCurrentPatient().getDescription());


        dialog.show();
    }
}

