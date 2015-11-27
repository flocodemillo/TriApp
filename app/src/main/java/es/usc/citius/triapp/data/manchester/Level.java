package es.usc.citius.triapp.data.manchester;

import java.util.List;

/**
 * Created by vasily on 27/5/15.
 */
public class Level {

    //private int Time;
    private String Color;
    private List<Discriminator> Discriminators;

    public Level(){
    }


  /*  public int getTime() {
        return Time;
    }  */

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