package es.usc.citius.triapp.data;


import java.util.ArrayList;
import java.util.List;

public class Patients {
    private static Patients instance;
    private static List<Patient> patients = new ArrayList<>();
    //private static List<TriageResult> result = new ArrayList<>();
    private static Patient currentPatient = null;

    protected Patients() {
    }

    public static Patients getInstance(Patient patient) {
        return instance;
    }

    public static Patients getInstance() {
        return instance;
    }

    public static void addPatient (Patient patient) {
        patients.add(patient);
    }
    public static void setCurrentPatient (Patient patient) { currentPatient = patient;}
    public static Patient getCurrentPatient(){return currentPatient;}


    public static Patient getPatientbyName(String name) {

        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }

        return null;
    }

    public static Patient getPatientbyID(String id) {

        for (Patient patient : patients) {
            if (patient.getID().equals(id)) {
                return patient;
            }
        }

        return null;
    }
}
