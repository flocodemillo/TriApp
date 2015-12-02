package es.usc.citius.triapp.data.manchester;

import java.util.List;


public class FlowChart {

    private String ID;
    private String Name;
    private int RiskLimit;
    private String Description;

    private List<Level> Level;


    public FlowChart(){
    }

    public String getNombre() {
        return Name;
    }

    public String getID() {
        return ID;
    }

    public String getDescripcion() {
        return Description;
    }

    public int getRiskLimit() {
        return RiskLimit;
    }

    public List<Level> getLevels() {
        return Level;
    }

    public Level getLevel(int current) {
        return Level.get(current);
    }

    public int getLevelsSize() {
        return Level.size();
    }


    public String toString() {
        return Name;
    }
}