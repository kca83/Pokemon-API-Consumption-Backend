package anderson.katherine.pokemon;

import anderson.katherine.pokemon.model.EvolutionChain;
import anderson.katherine.pokemon.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PokemonController {

    @Autowired
    PokemonClient pokemonClient;

    @RequestMapping(value = "/pokemon", method = RequestMethod.GET)
    public Pokemon getPokemonByName(@RequestParam String name) {
        Pokemon pokemon = pokemonClient.getPokemonByName(name);
        setEvolutionChain(pokemon);
        return pokemon;
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Pokemon getRandomPokemon() {
        Pokemon pokemon = pokemonClient.getRandomPokemon();
        setEvolutionChain(pokemon);
        return pokemon;
    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
    public Pokemon getPokemonById(@PathVariable Integer id) {
        Pokemon pokemon = pokemonClient.getPokemonById(id);
        pokemon = setEvolutionChain(pokemon);
        return pokemon;
    }

    private Pokemon setEvolutionChain(Pokemon pokemon) {
        EvolutionChain evolutionChain = pokemonClient.getEvolutionChain(pokemon.getSpecies().getUrl());
        pokemon.setEvolutionChain(evolutionChain);
        return pokemon;
    }
}
