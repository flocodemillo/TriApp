package es.usc.citius.triapp.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.adapters.PatientAdapter;
import es.usc.citius.triapp.data.Patients;
import es.usc.citius.triapp.model.Person;

public class PatientsFragment extends Fragment {

    CardView mCardView;
    private RecyclerView rv;
    private List<Person> persons;

    public static PatientsFragment newInstance() {
        PatientsFragment fragment = new PatientsFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    public PatientsFragment() {
        // singleton
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patients, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);


        PatientAdapter adapter = new PatientAdapter(Patients.getPatients());

        rv.setAdapter(adapter);
    }

    public void onResume() {
        super.onResume();
        rv = (RecyclerView) getActivity().findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);


        PatientAdapter adapter = new PatientAdapter(Patients.getPatients());

        rv.setAdapter(adapter);
    }
}