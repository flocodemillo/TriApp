package es.usc.citius.triapp.fragments;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.adapters.DiscriminatorAdapter;



public class LevelFragment extends ListFragment { // implements DiscriminatorAdapter.AdapterCallback{

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
         setListAdapter(new DiscriminatorAdapter((DiscriminatorAdapter.AdapterCallback)this.getActivity(), this.getActivity().getBaseContext(), this.getActivity(), (Manchester.getCurrentWorkFlow()).getLevel(args.getInt("color", 0)).getDiscriminators(), args.getInt("color", 0)));

         return inflater.inflate(R.layout.fragment_level, container, false);
     }

}
