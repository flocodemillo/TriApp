package es.usc.citius.triapp.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import es.usc.citius.triapp.R;
import es.usc.citius.triapp.data.Patient;
import es.usc.citius.triapp.data.Patients;


public class UserBarcodeScanActivity extends Activity {

    private TextView formatTxt, contentTxt, patientName, patientLastName, patientBirthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_barcode_scan);

        //Button scanBtn = (Button) findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        patientName = (TextView)findViewById(R.id.patient_name);
        patientLastName = (TextView)findViewById(R.id.patient_lastName);
        patientBirthDate = (TextView)findViewById(R.id.patient_birthDate);


        //scanBtn.setOnClickListener(this);
    }

    public void startScan(View v){
        //if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        //}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText(String.format("Format: %s", scanFormat));
            //contentTxt.setText(String.format("Content: %s", scanContent));

            String[] parse = scanContent.split(";");

            //fake data
            String[] parseName = parse[0].split(":");
            patientName.setText(String.format("%s: %s", parseName[1], parseName[2]));
            String[] parseTelephone = parse[1].split(":");
            patientLastName.setText(parse[1]);
            String[] parseMail = parse[2].split(":");
            patientBirthDate.setText(parse[2]);


            Patient patient = new Patient(parseName[2],parseTelephone[1],parseMail[1],"1");
            //Patient patient = new Patient("Fake", "Patient", "01/11/1911", "000000001");
            //Patient patient = new Patient((String)patientName.getText(), (String)patientLastName.getText(), (String)patientBirthDate.getText(), (String)contentTxt.getText());
            Patients.addPatient(patient);
            Patients.setCurrentPatient(patient);


        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}