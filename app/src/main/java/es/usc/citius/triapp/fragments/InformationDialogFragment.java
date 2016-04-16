package es.usc.citius.triapp.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Patients;


public class InformationDialogFragment extends DialogFragment {

    private EditText description;

    public InformationDialogFragment() {

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.fragment_information_dialog, null);


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //builder.setView(inflater.inflate(R.layout.fragment_information_dialog, null))
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                       // final View layout = View.inflate(getActivity(), R.layout.fragment_information_dialog, null);
                       // final EditText description = (EditText) layout.findViewById(R.id.description);

                        //View layout = View.inflate(getActivity(), R.layout.fragment_information_dialog, null);
                        //description = (EditText) layout.findViewById(R.id.description);
                        description = (EditText) view.findViewById(R.id.description);
                        Patients.getCurrentPatient().setDescription(description.getText().toString());
                        getActivity().finish();
                        getActivity().startActivity(getActivity().getIntent());
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        InformationDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
