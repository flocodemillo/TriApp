package es.usc.citius.triapp.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.patients.Patient;
import es.usc.citius.triapp.data.patients.TriageResult;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.PersonViewHolder> {

    private static final String TAG = "ResultAdapter";

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
           // personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    //List<Patient> persons;
    List<TriageResult> persons;

    //public ResultAdapter(List<Patient> persons){
    public ResultAdapter(List<TriageResult> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_result_content, viewGroup, false);
        Log.v(TAG, "Tamaño personas onCreateViewHolder: " + persons.size());
        return new PersonViewHolder(v);

    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).getFlowChart());
        Log.v(TAG, "Size 2: " + persons.size());
        personViewHolder.personAge.setText(persons.get(i).getDiscriminator());

    }

    @Override
    public int getItemCount() {
        Log.v(TAG, "Size getItemCount: " + persons.size());
        return persons.size();
    }
}