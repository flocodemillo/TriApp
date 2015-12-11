package es.usc.citius.triapp.data;


import java.util.Date;

public class TriageResult {
    private String level;
    private String discriminator;
    private Date date;
    private int triageTimeElapsed;

    public TriageResult(String level, String discriminator, Date date, int triageTimeElapsed) {
        this.level = level;
        this.discriminator = discriminator;
        this.date = date;
        this.triageTimeElapsed = triageTimeElapsed;
    }

}
