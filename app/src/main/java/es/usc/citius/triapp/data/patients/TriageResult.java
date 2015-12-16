package es.usc.citius.triapp.data.patients;


public class TriageResult {
    private String level;
    private String discriminator;
    private String date;
    private String triageTimeElapsed;

    public TriageResult(String level,String triageTimeElapsed) {
        this.level = level;
        //this.discriminator = discriminator;
        //this.date = date;
        this.triageTimeElapsed = triageTimeElapsed;
    }

}
