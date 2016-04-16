package es.usc.citius.triapp.adapters;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.Dictionary;
import es.usc.citius.triapp.data.manchester.Discriminator;

public class DiscriminatorsAdapter extends RecyclerView.Adapter<DiscriminatorsAdapter.DiscriminatorViewHolder> {

    public static class DiscriminatorViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        View rectangle;
        CheckBox checkbox;

        DiscriminatorViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            personName = (TextView)itemView.findViewById(R.id.name);
            personAge = (TextView)itemView.findViewById(R.id.description);
            rectangle = (View)itemView.findViewById(R.id.image);
            //checkbox = (CheckBox)itemView.findViewById(R.id.checkbox);
        }
    }

    private List<Discriminator> discriminators;
    private int level;
    private Activity activity;

    public DiscriminatorsAdapter(List<Discriminator> discriminators, int level, Activity activity){
        this.discriminators = discriminators;
        this.level = level;
        this.activity = activity;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DiscriminatorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_discriminators, viewGroup, false);
        return new DiscriminatorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DiscriminatorViewHolder discriminatorViewHolder, final int i) {

        switch (Manchester.getCurrentLevel()) {

            case 0:
                //discriminatorViewHolder.rectangle.setBackgroundColor(900);
                break;
            case 1:
                break;
        }

        discriminatorViewHolder.personName.setText(discriminators.get(i).getDiscriminator());
        discriminatorViewHolder.cv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                discriminators.get(i).setAnswer(true);
                List<Discriminator> questions = (Manchester.getCurrentWorkFlow()).getLevel(level).getDiscriminators();
                for (Discriminator question : questions) {
                    if (question.getAnswer()) {
                        ViewPager vp = (ViewPager) activity.findViewById(R.id.pager);
                        discriminators.get(i).setDescription(Dictionary.getInstance().getDefinition(discriminators.get(i).getDiscriminator()));
                        Manchester.setCurrentLevel(level);
                        vp.setCurrentItem(5);
                        break;
                    } else {
                        Manchester.setCurrentLevel(4);
                    }
                }

            }
        });

        discriminatorViewHolder.cv.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(activity.getBaseContext(), (Dictionary.getInstance()).getDefinition(discriminators.get(i).getDiscriminator()), duration);
                toast.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return discriminators.size();
    }
}