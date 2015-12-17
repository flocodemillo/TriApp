package es.usc.citius.triapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import es.usc.citius.triapp.activities.UserBarcodeScanActivity;
import es.usc.citius.triapp.data.Manchester;
import es.usc.citius.triapp.activities.FlowChartIndexActivity;
import es.usc.citius.triapp.data.Patients;
import es.usc.citius.triapp.data.patients.Patient;
import es.usc.citius.triapp.fragments.PatientsFragment;
import es.usc.citius.triapp.fragments.FragmentDrawer;


public class TriApp extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triapp);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentDrawer drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);

        // Inicializa datos algoritmo triaje
        Manchester.getInstance(getApplicationContext());

        //Inicializa datos pacientes
        Patients.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_triapp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                //fragment = new HomeFragment();
                fragment = new PatientsFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                title = getString(R.string.title_friends);
                break;
            case 2:
                //fragment = new MessagesFragment();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public void startTriage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, FlowChartIndexActivity.class);
        startActivity(intent);

    }

    public void starUserScan(View view) {
        Intent intent = new Intent(this, UserBarcodeScanActivity.class);
        startActivity(intent);
    }

    public void addPatient(View v){
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
            //formatTxt.setText(String.format("Format: %s", scanFormat));
            //contentTxt.setText(String.format("Content: %s", scanContent));

            if ( scanContent != null ) {
                String[] parse = scanContent.split(";");

                //fake data
                String[] parseName = parse[0].split(":");
                //patientName.setText(String.format("%s: %s", parseName[1], parseName[2]));
                String[] parseTelephone = parse[1].split(":");
                //patientLastName.setText(parse[1]);
                String[] parseMail = parse[2].split(":");
                //patientBirthDate.setText(parse[2]);


                Patient patient = new Patient(parseName[2], parseTelephone[1], parseMail[1], "1");
                //Patient patient = new Patient("Fake", "Patient", "01/11/1911", "000000001");
                //Patient patient = new Patient((String)patientName.getText(), (String)patientLastName.getText(), (String)patientBirthDate.getText(), (String)contentTxt.getText());
                Patients.addPatient(patient);
                Patients.setCurrentPatient(patient);
            }

        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
