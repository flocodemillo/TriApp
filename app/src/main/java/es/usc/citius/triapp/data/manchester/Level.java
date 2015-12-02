package es.usc.citius.triapp.data.manchester;

import java.util.List;


public class Level {

    //private int Time;
    private String Color;
    private List<Discriminator> Discriminators;

    public Level(){
    }

    public String getColor() {
        return Color;
    }

    public List<Discriminator> getDiscriminators() {
        return Discriminators;
    }

    public int getQuestionsSize(){
        return Discriminators.size();
    }


}