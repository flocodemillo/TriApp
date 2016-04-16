package es.usc.citius.triapp.data.patients;


public class TriageResult {
    private String flowChart;
    private int level;
    private String discriminator;
    private String description;
    private long elapsed;
    private String name;
    private String telephone;
    private String mail;


    public TriageResult(String flowChart,int level, String discriminator, String description, long elapsed) {
        this.flowChart = flowChart;
        this.level = level;
        this.discriminator = discriminator;
        this.description = description;
        this.elapsed = elapsed;
    }

    public String getFlowChart() {return this.flowChart;}
    public String getDiscriminator() {return this.discriminator;}




}
