package anderson.katherine.pokemon;

import anderson.katherine.pokemon.model.EvolutionChain;
import anderson.katherine.pokemon.model.EvolutionChainUrl;
import anderson.katherine.pokemon.model.Pokemon;
import anderson.katherine.pokemon.model.PokemonSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class PokemonClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    private final String API_URL = "https://pokeapi.co/api/v2/";

    public Pokemon getPokemonByName(String name) {
        ResponseEntity<Pokemon> responseEntity = restTemplate.exchange(API_URL + "pokemon/" + name, HttpMethod.GET, httpEntity, Pokemon.class);
        return responseEntity.getBody();
    }

    public Pokemon getPokemonById(int id) {
        ResponseEntity<Pokemon> responseEntity = restTemplate.exchange(API_URL + "pokemon/" + id, HttpMethod.GET, httpEntity, Pokemon.class);
        return responseEntity.getBody();
    }

    public Pokemon getRandomPokemon() {
        int randomIndex = randomNumber(802);
        return getPokemonById(randomIndex);
    }

    private int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max) + 1;
    }

    public EvolutionChain getEvolutionChain(String fullUrl) {
        ResponseEntity<PokemonSpecies> responseEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, httpEntity, PokemonSpecies.class);
        PokemonSpecies pokemonSpecies = responseEntity.getBody();
        EvolutionChainUrl evolutionChainUrl = pokemonSpecies.getEvolutionChainUrl();
        ResponseEntity<EvolutionChain> nextResponseEntity = restTemplate.exchange(evolutionChainUrl.getUrl(), HttpMethod.GET, httpEntity, EvolutionChain.class);
        return nextResponseEntity.getBody();
    }
}