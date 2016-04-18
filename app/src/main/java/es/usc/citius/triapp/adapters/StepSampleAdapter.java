package es.usc.citius.triapp.adapters;

        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.text.Html;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;

        import com.github.fcannizzaro.materialstepper.AbstractStep;

        import es.usc.citius.triapp.R;
        import es.usc.citius.triapp.data.Manchester;


public class StepSampleAdapter extends AbstractStep {

    private int i = 1;
    private Button button;


    private int level = 0;
    private String[] title = {"","Red","Orange", "Yellow", "Green"};


    public StepSampleAdapter(int level) {
        this.level = level;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.step, container, false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv2);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);


        DiscriminatorsAdapter adapter = new DiscriminatorsAdapter((Manchester.getCurrentWorkFlow()).getLevel(level).getDiscriminators(),level, this.getActivity());
        rv.setAdapter(adapter);

       /* button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText(Html.fromHtml("Tap <b>" + (i++) + "</b>"));
                if (mStepper != null)
                    mStepper.getExtras().putInt("Click", i);
            }
        });*/

        return v;
    }

    @Override
    public void onStepVisible() {
        super.onStepVisible();
    }

    @Override
    public String name() {
        //return "Tab " + getArguments().getInt("position", 0);
        return title[getArguments().getInt("position", 0)];
    }

    @Override
    public boolean isOptional() {
        return true;
    }

    @Override
    public String optional() {
        return "You can skip";
    }

    @Override
    public boolean nextIf() {
        return i > 1;
    }

    @Override
    public String error() {
        return "<b>You must click!</b> <small>this is the condition!</small>";
    }
}
