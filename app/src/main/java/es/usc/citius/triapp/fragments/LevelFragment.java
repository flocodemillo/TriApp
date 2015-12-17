package es.usc.citius.triapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.adapters.DiscriminatorsAdapter;
import es.usc.citius.triapp.adapters.PatientAdapter;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.adapters.DiscriminatorAdapter;
import es.usc.citius.triapp.data.Patients;


//public class LevelFragment extends ListFragment { // implements DiscriminatorAdapter.AdapterCallback{
public class LevelFragment extends Fragment {

    private static final String TAG = "LevelFragment";

    //private DiscriminatorAdapter.AdapterCallback mAdapterCallback;

    public LevelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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
         Bundle args = getArguments();
         //Primer campo callback
         //setListAdapter(new DiscriminatorAdapter((DiscriminatorAdapter.AdapterCallback)this.getActivity(), this.getActivity().getBaseContext(), this.getActivity(), (Manchester.getCurrentWorkFlow()).getLevel(args.getInt("color", 0)).getDiscriminators(), args.getInt("color", 0)));



         return inflater.inflate(R.layout.fragment_level, container, false);
     }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv2);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        Bundle args = getArguments();
        DiscriminatorsAdapter adapter = new DiscriminatorsAdapter((Manchester.getCurrentWorkFlow()).getLevel(args.getInt("color", 0)).getDiscriminators(),args.getInt("color", 0), this.getActivity());

        rv.setAdapter(adapter);

    }

}
