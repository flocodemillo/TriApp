package es.usc.citius.triapp.data.patients;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String name;
    private String mail;
    private String telephone;
    private String id;
    private long startTime;
    private boolean show;

    //private String birthDate; 
    private List<TriageResult> result = new ArrayList<>();

    public Patient(String name, String telephone, String mail, String id, Boolean show) {
        this.name = name;
        this.telephone = telephone;
        this.mail = mail;
        this.id = id;
        this.show = show;
    }

    public String getName() {return this.name;}
    public String getMail() {return this.mail;}
    public String getID() {return this.id;}
    public String getTelephone() {return this.telephone;}
    public Boolean getShow() {return this.show;}
    public void setStartTime(long startTime) {this.startTime=startTime;}

    public long getElapsedTime(long currentTime) {
        return (currentTime - startTime)/1000;
    }

    public void setResult(TriageResult result) {
        this.result.add(result);
    }
    public void setShow(Boolean show) {
        this.show = show;
    }
}
