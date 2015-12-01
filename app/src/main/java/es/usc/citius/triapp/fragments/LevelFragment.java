package es.usc.citius.triapp.fragments;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.adapters.DiscriminatorsAdapter;
import es.usc.citius.triapp.adapters.SlideLevelAdapter;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.adapters.DiscriminatorAdapter;
import es.usc.citius.triapp.model.Person;


public class LevelFragment extends ListFragment { // implements DiscriminatorAdapter.AdapterCallback{

    private static final String TAG = "LevelFragment";
    //private View view=null;
    //private int color;
    //private DiscriminatorAdapter.AdapterCallback mAdapterCallback;
    private RecyclerView rv;
    private List<Person> persons;
    private DiscriminatorsAdapter adapter;
    private LinearLayoutManager llm;


    public LevelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //initializeData();



    }

   /* @Override
    public void onMethodCallback(int color) {
        this.color = color;
        Log.v(TAG, "Color: " + color);
        // do something
    }*/

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {


         // Inflate the layout for this fragment

         Log.d(TAG, "Nombre workflow: " + Manchester.getCurrentWorkFlow());
         final View V = inflater.inflate(R.layout.fragment_level, container, false);

         //Cards
        // rv=(RecyclerView)V.findViewById(R.id.rv);
         //adapter = new DiscriminatorsAdapter(persons);
         //llm = new LinearLayoutManager(getActivity());



         //LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        // LinearLayoutManager llm = new LinearLayoutManager(getActivity());
         /*rv.setLayoutManager(llm);
         rv.setBackgroundColor(Color.BLACK);
         rv.setHasFixedSize(true);

         adapter = new DiscriminatorsAdapter(persons);
         rv.setAdapter(adapter);*/


         //initializeData();
 /*rv.postDelayed(new Runnable() {
     @Override
     public void run() {

         rv.setAdapter(adapter);
         Log.d("Level", (rv.getAdapter() != null) + "");
     }
 }, 2000);*/

         //initializeAdapter();


         Bundle args = getArguments();

         //Primer campo callback

         setListAdapter(new DiscriminatorAdapter((DiscriminatorAdapter.AdapterCallback)this.getActivity(), this.getActivity().getBaseContext(), this.getActivity(), (Manchester.getCurrentWorkFlow()).getLevel(args.getInt("color", 0)).getDiscriminators(), args.getInt("color", 0)));



         return inflater.inflate(R.layout.fragment_level, container, false);

     }

   /* @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //mAdapterCallback = (DiscriminatorAdapter.AdapterCallback )activity;
    }*/

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old"));
        persons.add(new Person("Lavery Maiss", "25 years old"));
        persons.add(new Person("Lillie Watts", "35 years old"));
    }

    private void initializeAdapter(){
        DiscriminatorsAdapter adapter = new DiscriminatorsAdapter(persons);
        rv.setAdapter(adapter);
    }


}
