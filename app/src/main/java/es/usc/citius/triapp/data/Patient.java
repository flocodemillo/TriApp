package es.usc.citius.triapp.data;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String name;
    private String mail;
    private String telephone;
    private String id;

    //private String birthDate; 
    //private List<TriageResult> result = new ArrayList<>();

    public Patient(String name, String telephone, String mail, String id) {
        this.name = name;
        this.telephone = telephone;
        this.mail = mail;
        this.id = id;
    }

    public String getName() {return this.name;}
    public String getMail() {return this.mail;}
    public String getID() {return this.id;}
    public String getTelephone() {return this.telephone;}
}
