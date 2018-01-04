package anderson.katherine.pokemon;

import anderson.katherine.pokemon.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PokemonClient.class)
public class PokemonClientTest {

    @Autowired
    private PokemonClient pokemonClient;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private HttpEntity<String> httpEntity;

    ResponseEntity<Pokemon> pokemonResponseEntity;
    Pokemon pokemon;

    ResponseEntity<PokemonSpecies> pokemonSpeciesResponseEntity;
    PokemonSpecies pokemonSpecies;

    ResponseEntity<EvolutionChain> evolutionChainResponseEntity;
    EvolutionChain evolutionChain;

    @Before
    public void initialize() {
        pokemon = new Pokemon();
        pokemon.setId(100);
        pokemon.setName("Test");
        pokemonResponseEntity = new ResponseEntity<Pokemon>(pokemon, HttpStatus.OK);

        pokemonSpecies = new PokemonSpecies();
        EvolutionChainUrl evolutionChainUrl = new EvolutionChainUrl();
        evolutionChainUrl.setUrl("www.testapi.com");
        pokemonSpecies.setEvolutionChainUrl(evolutionChainUrl);
        pokemonSpeciesResponseEntity = new ResponseEntity<PokemonSpecies>(pokemonSpecies, HttpStatus.OK);

        Species species = new Species();
        species.setName("First in evolution");
        Chain chain = new Chain();
        chain.setSpecies(species);
        chain.setEvolvesTo(new EvolvesTo[0]);
        evolutionChain = new EvolutionChain();
        evolutionChain.setChain(chain);
        evolutionChainResponseEntity = new ResponseEntity<EvolutionChain>(evolutionChain, HttpStatus.OK);
    }

    @Test
    public void getPokemonByIdTest() throws Exception {
        given(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Pokemon.class))).willReturn(pokemonResponseEntity);

        Assert.assertEquals(pokemon, pokemonClient.getPokemonById(100));
    }

    @Test
    public void getPokemonByNameTest() throws Exception {
        given(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Pokemon.class))).willReturn(pokemonResponseEntity);

        Assert.assertEquals(pokemon, pokemonClient.getPokemonByName("Test"));
    }

    @Test
    public void getEvolutionChainByIdTest() throws Exception {
        given(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(PokemonSpecies.class))).willReturn(pokemonSpeciesResponseEntity);
        given(restTemplate.exchange(eq("www.testapi.com"), eq(HttpMethod.GET), any(), eq(EvolutionChain.class))).willReturn(evolutionChainResponseEntity);

        Assert.assertEquals(evolutionChain, pokemonClient.getEvolutionChain("pokemon species url"));
    }
}
