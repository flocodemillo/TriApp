package es.usc.citius.triapp.data.manchester;


public class Entry {

    private String Discriminator;
    private String Description;

    public Entry(){
    }

    public String getDiscriminator() {

        return Discriminator;
    }

    public String getExplanation() {

        return Description;
    }

    public void setDiscriminator(String discriminator) {
        this.Discriminator=discriminator;
    }

    public void setDescription(String description) {
        this.Description=description;
    }
}
