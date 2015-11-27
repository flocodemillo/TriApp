package es.usc.citius.triapp.data.manchester;

import java.util.HashMap;

/**
 * Created by vasily on 27/5/15.
 */
public class Dictionary {

    private static final HashMap<String, String> definitions = new HashMap<>();
    private static Dictionary instance = null;



    public static Dictionary getInstance(Entry[] Entry){

        if(instance == null) {
            for (Entry dc : Entry) {
                definitions.put(dc.getDiscriminator(), dc.getExplanation());
            }
        }
        return instance;
    }

    public static Dictionary getInstance() {
        instance = new Dictionary();
        return instance;
    }

    public String getDefinition(String discriminator) {
        String definition = definitions.get(discriminator);

        return definition;
    }
}