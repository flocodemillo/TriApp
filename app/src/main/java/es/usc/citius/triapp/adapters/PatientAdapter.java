package es.usc.citius.triapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.activities.FlowChartIndexActivity;
import es.usc.citius.triapp.activities.ResultActivity;
import es.usc.citius.triapp.data.Patients;
import es.usc.citius.triapp.data.patients.Patient;
import es.usc.citius.triapp.fragments.InformationDialogFragment;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        private TextView personName;
        private TextView personAge;
        private TextView personDescription;
        private TextView personTriaged;
        private ImageButton buttonSave;
        private ImageButton buttonEdit;
        public Activity myActivity;

        PersonViewHolder(View itemView, Activity myActivity) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            personName = (TextView)itemView.findViewById(R.id.name);
            personAge = (TextView)itemView.findViewById(R.id.age);
            personDescription = (TextView)itemView.findViewById(R.id.description);
            personTriaged = (TextView)itemView.findViewById(R.id.triaged);
            buttonSave = (ImageButton)itemView.findViewById(R.id.save);
            buttonEdit = (ImageButton)itemView.findViewById(R.id.edit);
            this.myActivity = myActivity;
        }
    }

    private List<Patient> patients;

    public PatientAdapter(List<Patient> patients){
        this.patients = patients;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_patient, viewGroup, false);
        return new PersonViewHolder(v, (Activity) v.getContext());
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, final int i) {


        personViewHolder.buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event

                Patients.setCurrentPatient(Patients.getPatientbyName(patients.get(i).getName()));
                Patients.getCurrentPatient().setShow(false);
                personViewHolder.myActivity.finish();
                personViewHolder.myActivity.startActivity(personViewHolder.myActivity.getIntent());
                // Falta aladir un metodo para que se refresque el fragment
            }
        });

        personViewHolder.buttonEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event

                Patients.setCurrentPatient(Patients.getPatientbyName(patients.get(i).getName()));
                //Patients.getCurrentPatient().setShow(false);
                InformationDialogFragment info = new InformationDialogFragment();
                info.show(personViewHolder.myActivity.getFragmentManager(),"dsadas");
                //personViewHolder.myActivity.finish();
                //personViewHolder.myActivity.startActivity(personViewHolder.myActivity.getIntent());
                // Falta aladir un metodo para que se refresque el fragment
            }
        });

        personViewHolder.cv.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {


                Intent intent = new Intent(personViewHolder.myActivity,ResultActivity.class);
                personViewHolder.myActivity.startActivity(intent);


                return true;
            }
        });

        personViewHolder.cv.postInvalidate();
        personViewHolder.personName.setText(patients.get(i).getName());
        personViewHolder.personAge.setText(patients.get(i).getMail());
        personViewHolder.personDescription.setText(patients.get(i).getDescription());
        personViewHolder.personTriaged.setText(patients.get(i).getTriaged().toString());
        //personViewHolder.personAge.setText((int) patients.get(i).getElapsedTime(System.currentTimeMillis()));
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }


}


