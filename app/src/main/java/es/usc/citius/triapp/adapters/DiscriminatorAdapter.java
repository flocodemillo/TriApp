package es.usc.citius.triapp.adapters;



import android.app.Activity;

import android.content.Context;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.data.manchester.Dictionary;
import es.usc.citius.triapp.data.manchester.Discriminator;


import java.util.List;

/**
 * Created by vasily on 28/5/15.
 */
public class DiscriminatorAdapter extends ArrayAdapter<Discriminator>{

        private static final String TAG = "DiscriminatorAdapter";
        private LayoutInflater layoutInflater;
        private Context context;
        private Activity activity;
        private int color;
        private List<Discriminator> questions;
        private Discriminator question;
        private AdapterCallback mAdapterCallback;
        //private AdapterCallback mAdapterCallbackActivity;




        public DiscriminatorAdapter(AdapterCallback callback, Context context, Activity activity,List<Discriminator> objects, int color)
        {
            super(context, 0, objects);
            layoutInflater = LayoutInflater.from(context);
            this.context = context;
            this.activity = activity;
            this.color = color;
            this.mAdapterCallback = callback;
            //this.mAdapterCallbackActivity = callback_activity;
        }

        public interface AdapterCallback {
            void onMethodCallback(int color);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // holder pattern
            Holder holder = null;
            if (convertView == null)
            {
                holder = new Holder();

                convertView = layoutInflater.inflate(R.layout.listview_discriminator, null);
                holder.setTextViewTitle((TextView) convertView.findViewById(R.id.discriminator));
                //holder.setTextViewSubtitle((TextView) convertView.findViewById(R.id.description));
                holder.setCheckBox((CheckBox) convertView.findViewById(R.id.answer));
                holder.setButton((Button) convertView.findViewById(R.id.help));
                convertView.setTag(holder);
            }
            else
            {
                holder = (Holder) convertView.getTag();
            }

            final Discriminator discriminator = getItem(position);

            holder.getTextViewTitle().setText(discriminator.getDiscriminator());
            //holder.getTextViewSubtitle().setText(discriminator.getDescription());
            //holder.getCheckBox().setChecked(true);
            holder.getCheckBox().setTag(discriminator.getDiscriminator());
            holder.getCheckBox().setChecked(discriminator.getAnswer());
            holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                    //asegura que se modifica la Row originalmente asociado a este CheckBox
                    //para evitar que al reciclar la vista se reinicie el row que antes se mostraba en esta
                    //fila. Es imprescindible tagear el Row antes de establecer el valor del CheckBox
                    if (discriminator.getDiscriminator().equals(view.getTag().toString())) {
                        discriminator.setAnswer(isChecked);
                        questions = (Manchester.getCurrentWorkFlow()).getLevel(color).getDiscriminators();
                        for (Discriminator question : questions) {
                            //question.setAnswer(false);
                            Log.v(TAG, "Nivel / Pregunta / Respuesta: " + color + " / " + question.getDiscriminator() + " / " + question.getAnswer());
                            //System.out.println("Respuesta: " + question.getAnswer());

                            if (question.getAnswer()) {
                                ViewPager v = (ViewPager) activity.findViewById(R.id.pager);
                                mAdapterCallback.onMethodCallback(color);
                                Manchester.setCurrentLevel(color);
                                v.setCurrentItem(4);
                            }

                        }

                    }
                }
            });

            //Manejador del botón de ayuda
            holder.getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, (Dictionary.getInstance()).getDefinition(discriminator.getDiscriminator()), duration);
                    toast.show();
                }
            });


            return convertView;
        }

    }

    class Holder
    {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox CheckBox;
        Button button;

        public TextView getTextViewTitle()
        {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle)
        {
            this.textViewTitle = textViewTitle;
        }

        public TextView getTextViewSubtitle()
        {
            return textViewSubtitle;
        }

        public void setTextViewSubtitle(TextView textViewSubtitle)
        {
            this.textViewSubtitle = textViewSubtitle;
        }

        public CheckBox getCheckBox()
        {
            return CheckBox;
        }

        public void setCheckBox(CheckBox CheckBox)
        {
            this.CheckBox = CheckBox;
        }

        public void setButton(Button button)
        {
            this.button = button;
        }

        public Button getButton()
        {
            return button;
        }

    }

