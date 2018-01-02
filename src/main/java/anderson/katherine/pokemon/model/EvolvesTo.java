package anderson.katherine.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EvolvesTo {

    @JsonProperty("evolves_to")
    private EvolvesTo[] evolvesTo;
    private Species species;

    public EvolvesTo() {

    }

    public EvolvesTo[] getEvolvesTo() {
        return evolvesTo;
    }

    public void setEvolvesTo(EvolvesTo[] evolvesTo) {
        this.evolvesTo = evolvesTo;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
