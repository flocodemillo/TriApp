package es.usc.citius.triapp.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;


import java.util.Iterator;
import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.adapters.PatientAdapter;
import es.usc.citius.triapp.data.Patients;
import es.usc.citius.triapp.data.patients.Patient;


public class PatientsFragment extends Fragment {


    private RecyclerView rv;
    private ViewStub emptyRV;

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
        emptyRV = (ViewStub) view.findViewById(R.id.empty_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);


        List<Patient> patients = Patients.getPatients();

        for (Iterator<Patient> patient = patients.iterator(); patient.hasNext();) {
            Patient paciente = patient.next();
            if (paciente.getShow().equals(false)) {
                patient.remove();
            }
        }

        //Si no tengo pacientes
        if (patients.isEmpty()) {
            rv.setVisibility(View.GONE);
            emptyRV.setVisibility(View.VISIBLE);
        }
        else{
            rv.setVisibility(View.VISIBLE);
            emptyRV.setVisibility(View.GONE);
        }

        PatientAdapter adapter = new PatientAdapter(patients);
        rv.setAdapter(adapter);

    }

    public void onResume() {
        super.onResume();
        rv = (RecyclerView) getActivity().findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        if (Patients.getPatients().isEmpty()) {
            rv.setVisibility(View.GONE);
            emptyRV.setVisibility(View.VISIBLE);
        }
        else{
            rv.setVisibility(View.VISIBLE);
            emptyRV.setVisibility(View.GONE);
        }

        PatientAdapter adapter = new PatientAdapter(Patients.getPatients());

        rv.setAdapter(adapter);
    }
}
