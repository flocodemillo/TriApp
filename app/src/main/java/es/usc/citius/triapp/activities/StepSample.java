package es.usc.citius.triapp.activities;

import android.os.Bundle;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.TabStepper;

import es.usc.citius.triapp.adapters.StepSampleAdapter;
import es.usc.citius.triapp.data.Manchester;

public class StepSample extends TabStepper {

    private int i = 1;

        @Override
    protected void onCreate(Bundle savedInstanceState) {

        setErrorTimeout(1500);
        setLinear(false);
        setTitle("Tab Stepper <small>(Classic Tab) </small>");
        setAlternativeTab(false);
        disabledTouch();
        showPreviousButton();


            switch (Manchester.getCurrentWorkFlow().getLevels().size()) {
                case 1:
                    addStep(createFragment(new StepSampleAdapter(0)));
                case 2:
                    addStep(createFragment(new StepSampleAdapter(0)));
                    addStep(createFragment(new StepSampleAdapter(1)));
                case 3:
                    addStep(createFragment(new StepSampleAdapter(0)));
                    addStep(createFragment(new StepSampleAdapter(1)));
                    addStep(createFragment(new StepSampleAdapter(2)));
                case 4:
                    addStep(createFragment(new StepSampleAdapter(0)));
                    addStep(createFragment(new StepSampleAdapter(1)));
                    addStep(createFragment(new StepSampleAdapter(2)));
                    addStep(createFragment(new StepSampleAdapter(3)));
            }

        super.onCreate(savedInstanceState);
    }

    private AbstractStep createFragment(AbstractStep fragment) {
        Bundle b = new Bundle();
        b.putInt("position", i++);
        fragment.setArguments(b);
        return fragment;
    }

}

