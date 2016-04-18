package es.usc.citius.triapp.data.patients;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String name;
    private String mail;
    private String telephone;
    private String id;
    private String description;
    private long startTime;
    private boolean show;
    private boolean triaged;
    private static List<TriageResult> triageResultList = new ArrayList<>();
    private TriageResult current;


    //private String birthDate; 
    //private List<TriageResult> result = new ArrayList<>();

    public Patient(String name, String telephone, String mail, String id, Boolean show, Boolean triaged) {
        this.name = name;
        this.telephone = telephone;
        this.mail = mail;
        this.id = id;
        this.show = show;
        this.triaged = triaged;
    }

    public static void addResult (TriageResult result) {
        triageResultList.add(result);
        System.out.println("Añadido nuevo resultado\n");
    }

    public String getName() {return this.name;}
    public String getMail() {return this.mail;}
    public String getID() {return this.id;}
    public String getTelephone() {return this.telephone;}
    public String getDescription() {return this.description;}
    public Boolean getTriaged() {return this.triaged;}
    public List<TriageResult> getResults() {return triageResultList;}
    public TriageResult getCurrentTriage() {return current;}


    public Boolean getShow() {return this.show;}
    public void setStartTime(long startTime) {this.startTime=startTime;}

    public long getElapsedTime(long currentTime) {
        return (currentTime - startTime)/1000;
    }

    public void setResult(TriageResult result) {
        triageResultList.add(result);
    }
    public void setShow(Boolean show) {
        this.show = show;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTriaged(Boolean triaged) {this.triaged = triaged; }
    public void setCurrentTriage(TriageResult data) {this.current = data;}

}
