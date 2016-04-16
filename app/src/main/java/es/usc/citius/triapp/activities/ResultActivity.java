package es.usc.citius.triapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.adapters.ResultAdapter;
import es.usc.citius.triapp.data.Patients;
import es.usc.citius.triapp.data.patients.Patient;
import es.usc.citius.triapp.data.patients.TriageResult;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity" ;
    private List<Patient> persons = new ArrayList<>();
    private List<TriageResult> results = new ArrayList<>();
    private RecyclerView rv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            rv=(RecyclerView)findViewById(R.id.rv);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setHasFixedSize(true);

            initializeData();
            initializeAdapter();
        }

        private void initializeData(){

            persons = new ArrayList<>();
            persons = Patients.getPatients();
            Log.v(TAG, "Size: " + persons.size());

            results = Patients.getCurrentPatient().getResults();
            Log.v(TAG, "Size results: " + results.size());


            /*persons.add(persons2.get(0));
            persons.add(persons2.get(1));
            persons.add(persons2.get(2));*/

        }

        private void initializeAdapter(){
            ResultAdapter adapter = new ResultAdapter(results);
            rv.setAdapter(adapter);
        }

    }
