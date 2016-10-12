package es.usc.citius.triapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.ThemeSingleton;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.TriApp;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.Patients;
import es.usc.citius.triapp.data.manchester.Dictionary;
import es.usc.citius.triapp.data.manchester.Discriminator;

public class DiscriminatorsAdapter extends RecyclerView.Adapter<DiscriminatorsAdapter.DiscriminatorViewHolder> {

    private static final String TAG = "DiscriminatorAdapter" ;

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

        discriminatorViewHolder.personName.setText(discriminators.get(i).getDiscriminator());
        discriminatorViewHolder.cv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(final View v) {
                Patients.getCurrentPatient().getCurrentTriage().setDiscriminator(discriminators.get(i).getDiscriminator());
                Patients.getCurrentPatient().getCurrentTriage().setDescription(Dictionary.getInstance().getDefinition(discriminators.get(i).getDiscriminator()));
                Log.v(TAG, "Size: " + discriminators.get(i).getDiscriminator());

                Patients.getCurrentPatient().getCurrentTriage().setLevel(level);
                Patients.getCurrentPatient().getCurrentTriage().setFinished(true);


                View positiveAction;
                    MaterialDialog dialog = new MaterialDialog.Builder(activity)
                            .title("Resultado")
                            .customView(R.layout.dialog_report, true)
                            .positiveText("Guardar")
                            .negativeText("Seguir triando")
                           .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    //showToast("Password: " + passwordInput.getText().toString());
                                    Log.v(TAG, "Estoy aquí" + level);
                                    Activity host = (Activity) v.getContext();
                                    host.finish();
                                    Intent intent = new Intent(v.getContext(), TriApp.class);
                                    host.startActivity(intent);
                                }
                            }).build();
                TextView fcName = (TextView) dialog.getCustomView().findViewById(R.id.fcName);
                fcName.setText(Patients.getCurrentPatient().getCurrentTriage().getFlowChart());
                TextView fcLevel = (TextView) dialog.getCustomView().findViewById(R.id.fcLevel);
                fcLevel.setText(String.valueOf(level));
                TextView discriminator = (TextView) dialog.getCustomView().findViewById(R.id.fcDiscriminator);
                discriminator.setText(Patients.getCurrentPatient().getCurrentTriage().getDiscriminator());
                TextView dDescription = (TextView) dialog.getCustomView().findViewById(R.id.dDescription);
                dDescription.setText(Patients.getCurrentPatient().getCurrentTriage().getDescription());

                TextView date = (TextView) dialog.getCustomView().findViewById(R.id.date);
                GregorianCalendar gc = (GregorianCalendar) Patients.getCurrentPatient().getCurrentTriage().getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
                date.setText(sdf.format(gc.getTime()));

                TextView tElapsed = (TextView) dialog.getCustomView().findViewById(R.id.elapsedTime);
                tElapsed.setText(String.valueOf(Patients.getCurrentPatient().getCurrentTriage().getElapsed()));
                TextView pName = (TextView) dialog.getCustomView().findViewById(R.id.pName);
                pName.setText(Patients.getCurrentPatient().getName());
                TextView pDescription = (TextView) dialog.getCustomView().findViewById(R.id.pDescription);
                pDescription.setText(Patients.getCurrentPatient().getDescription());

                positiveAction = dialog.getActionButton(DialogAction.POSITIVE);


                dialog.show();


                /*discriminators.get(i).setAnswer(true);
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
                }*/

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