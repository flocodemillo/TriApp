package es.usc.citius.triapp.data.patients;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class TriageResult {

    private Patient patient;
    private String flowChart;
    private int level;
    private String discriminator;
    private String description;
    private GregorianCalendar date;
    private static long startTime;
    private long elapsed;
    private boolean finished;



    public TriageResult(String flowChart, Patient patient) {
        this.flowChart = flowChart;
        this.patient = patient;
        this.date = (GregorianCalendar) GregorianCalendar.getInstance();
        startTime = 0;
        this.finished = false;


    }

    public void setDiscriminator(String discriminator) {this.discriminator=discriminator;}
    public void setDescription(String description) {this.description=description;}
    public void setLevel(int level) {this.level=level;}
    public void setFinished(boolean finished) {
        this.elapsed = calculeElapsed(System.currentTimeMillis());
        this.finished=finished;
    }
    public static void setStartTime(long time) {
        startTime = time;
    }


    public Boolean isFinished() {return finished;}

    public String getFlowChart() {return flowChart;}
    public String getDiscriminator() {return discriminator;}
    public String getDescription() {return description;}
    public int getLevel() {return level;}
    public GregorianCalendar getDate() {return date;}
    private static long calculeElapsed(long currentTime) { return (currentTime - startTime)/1000;}
    public long getElapsed() {return elapsed;}




}
