package es.usc.citius.triapp.data;


import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String name;
    private String lastName;
    private String id;
    private String birthDate;
    private List<TriageResult> result = new ArrayList<>();

    public Patient(String name, String lastName, String date, String id) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = date;
        this.id = id;
    }

    public String getName() { return this.name; }
    public String getLastName() { return this.lastName; }
    public String getID() { return this.id; }
    public String getBirthDate() { return this.birthDate; }
}
