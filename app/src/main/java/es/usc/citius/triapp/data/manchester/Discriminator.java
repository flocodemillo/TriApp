package es.usc.citius.triapp.data.manchester;

/**
 * Created by vasily on 27/5/15.
 */
public class Discriminator {
    private String Discriminator;
    private String Description;
    private Boolean Answer = false;

    public Discriminator(){
    }

    public String getDiscriminator() {
        return Discriminator;
    }

    public String getDescription() {
        return Description;
    }

    public Boolean getAnswer() {
        return Answer;
    }

    public void setDescription(String descripcion) {
        System.out.println(descripcion);
        this.Description = descripcion;
    }

    public void setAnswer(Boolean Answer) {
        this.Answer = Answer;
    }

}