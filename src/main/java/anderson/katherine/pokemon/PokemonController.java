package anderson.katherine.pokemon;

import anderson.katherine.pokemon.model.EvolutionChain;
import anderson.katherine.pokemon.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PokemonController {

    @Autowired
    PokemonClient pokemonClient;

    @RequestMapping(value = "/pokemon-species/{name}", method = RequestMethod.GET)
    public Pokemon getPokemonByName(@PathVariable String name) {
        return pokemonClient.getPokemonByName(name);
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Pokemon getRandomPokemon() {
        Pokemon pokemon = pokemonClient.getRandomPokemon();
        EvolutionChain evolutionChain = pokemonClient.getEvolutionChain(pokemon.getSpecies().getUrl());
        pokemon.setEvolutionChain(evolutionChain);
        return pokemon;
    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
    public Pokemon getPokemonById(@PathVariable Integer id) {
        Pokemon pokemon = pokemonClient.getPokemonById(id);
        EvolutionChain evolutionChain = pokemonClient.getEvolutionChain(pokemon.getSpecies().getUrl());
        pokemon.setEvolutionChain(evolutionChain);
        return pokemon;
    }
}
