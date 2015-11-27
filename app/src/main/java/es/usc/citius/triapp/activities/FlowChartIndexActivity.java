package es.usc.citius.triapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;


import es.usc.citius.triapp.R;
import es.usc.citius.triapp.fragments.FlowChartDescriptionFragment;
import es.usc.citius.triapp.fragments.FlowChartIndexFragment;
import es.usc.citius.triapp.data.Manchester;

/**
 * On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link FlowChartIndexFragment} and the item details
 * (if present) is a {@link FlowChartDescriptionFragment}.
 * <p/>
 * This activity also implements the required
 * {@link FlowChartIndexFragment.Callbacks} interface
 * to listen for item selections.
 */
public class FlowChartIndexActivity extends Activity//FragmentActivity
        implements FlowChartIndexFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowchart_index);

        if (findViewById(R.id.flowchart_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((FlowChartIndexFragment) this.getFragmentManager() //getSupportFragmentManager()
                    .findFragmentById(R.id.flowchart_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link FlowChartIndexFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        Manchester.setCurrentWorkFlow(Integer.parseInt(id));
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(FlowChartDescriptionFragment.ARG_ITEM_ID, id);
            FlowChartDescriptionFragment fragment = new FlowChartDescriptionFragment();
            fragment.setArguments(arguments);
            //getSupportFragmentManager().beginTransaction()
            this.getFragmentManager().beginTransaction()
                    .replace(R.id.flowchart_detail_container, fragment)
                    .commit();

        }
        ID = Integer.parseInt(id);
    }

    public void startTriage(View view) {


        //Log.v(TAG, "ID: " + numero);
        Intent intent = new Intent(this, StartTriageActivity.class);
        intent.putExtra("id", ID);
        startActivity(intent);

    }
}
