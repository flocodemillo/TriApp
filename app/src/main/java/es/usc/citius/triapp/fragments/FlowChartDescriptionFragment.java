package es.usc.citius.triapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.usc.citius.triapp.R;

import es.usc.citius.triapp.activities.FlowChartIndexActivity;
import es.usc.citius.triapp.data.manchester.FlowChart;
import es.usc.citius.triapp.data.Manchester;
/**
 * A fragment representing a single FlowChart detail screen.
 * This fragment is either contained in a {@link FlowChartIndexActivity}
 * in two-pane mode (on tablets) or a
 * on handsets.
 */
public class FlowChartDescriptionFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private FlowChart flowchart;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FlowChartDescriptionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            //Obtener el número de orden en la lista del elemento seleccionado por el usuario
            int index = Integer.valueOf(getArguments().getString(ARG_ITEM_ID));
            //Obtener la persona que se se encuentra en esa posición de la lista
            //person = (Person)PersonContent.getPersonList().get(index);
            flowchart = (FlowChart)Manchester.getFlowchartList().get(index);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flowchart_description, container, false);

        // Show the dummy content as text in a TextView.
        if (flowchart != null) {
            //((TextView) rootView.findViewById(R.id.textViewName)).setText(person.getName());
            ((TextView) rootView.findViewById(R.id.flowchart_detail)).setText(flowchart.getDescripcion());
        }

        return rootView;
    }
}
