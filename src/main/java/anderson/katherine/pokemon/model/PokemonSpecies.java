package anderson.katherine.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonSpecies {

    @JsonProperty("evolution_chain")
    private EvolutionChainUrl evolutionChainUrl;

    public PokemonSpecies() {

    }

    public EvolutionChainUrl getEvolutionChainUrl() {
        return evolutionChainUrl;
    }

    public void setEvolutionChainUrl(EvolutionChainUrl evolutionChainUrl) {
        this.evolutionChainUrl = evolutionChainUrl;
    }
}
