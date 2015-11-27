package es.usc.citius.triapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import es.usc.citius.triapp.R;

public class ReportFragment extends Fragment {

    private static final String TAG = "ReportFragment";
    private int color;
    //OnHeadlineSelectedListener mCallback;

    public ReportFragment() {
        // Required empty public constructor
    }

    //No llego hasta aquí
    public void setColor(int color) {
        this.color = color;
        Log.v(TAG, "Color: " + color);
    }

    public static ReportFragment newInstance() {
        ReportFragment fragment = new ReportFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_report, container, false);
    }



}
