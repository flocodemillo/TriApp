package es.usc.citius.triapp.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
//import android.app.ListFragment;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.adapters.DiscriminatorAdapter;


public class LevelFragment extends ListFragment implements DiscriminatorAdapter.AdapterCallback{

    private static final String TAG = "LevelFragment";
    private View view=null;
    //private DiscriminatorAdapter mMyAdapter;
    private int color;
    private DiscriminatorAdapter.AdapterCallback mAdapterCallback;


    public LevelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onMethodCallback(int color) {
        this.color = color;
        Log.v(TAG, "Color: " + color);
        // Esto no se sincroniza
        // do something
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.v(TAG, "Nombre workflow: " + Manchester.getCurrentWorkFlow());
        final View V = inflater.inflate(R.layout.fragment_level, container, false);

        Bundle args = getArguments();

        //Primer campo callback
        setListAdapter(new DiscriminatorAdapter(this, this.getActivity().getBaseContext(), this.getActivity(), (Manchester.getCurrentWorkFlow()).getLevel(args.getInt("color", 0)).getDiscriminators(), args.getInt("color", 0)));



        return inflater.inflate(R.layout.fragment_level, container, false);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mAdapterCallback = (DiscriminatorAdapter.AdapterCallback )activity;
    }


}
