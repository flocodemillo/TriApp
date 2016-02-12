package es.usc.citius.triapp.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.patients.Patient;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            personName = (TextView)itemView.findViewById(R.id.name);
            personAge = (TextView)itemView.findViewById(R.id.description);
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
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(patients.get(i).getName());
        personViewHolder.personAge.setText(patients.get(i).getMail());
        //personViewHolder.personAge.setText((int) patients.get(i).getElapsedTime(System.currentTimeMillis()));
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }
}


