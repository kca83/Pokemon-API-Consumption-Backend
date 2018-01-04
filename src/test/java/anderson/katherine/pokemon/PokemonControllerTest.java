package anderson.katherine.pokemon;

import anderson.katherine.pokemon.model.Chain;
import anderson.katherine.pokemon.model.EvolutionChain;
import anderson.katherine.pokemon.model.Pokemon;
import anderson.katherine.pokemon.model.Species;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PokemonController.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonClient pokemonClient;

    Pokemon pokemon;
    EvolutionChain evolutionChain;

    @Before
    public void initialize() {
        pokemon = new Pokemon();
        pokemon.setId(100);
        pokemon.setName("Test");

        evolutionChain = new EvolutionChain();
        evolutionChain.setChain(new Chain());

        Species species = new Species();
        species.setUrl("www.testurl.com");

        pokemon.setEvolutionChain(evolutionChain);
        pokemon.setSpecies(species);
    }

    @Test
    public void getPokemonByIdTest() throws Exception {
        given(pokemonClient.getPokemonById(100)).willReturn(pokemon);
        given(pokemonClient.getEvolutionChain("www.testurl.com")).willReturn(evolutionChain);

        mockMvc.perform(get("/pokemon/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(pokemon.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.evolutionChain.chain.species").value(pokemon.getEvolutionChain().getChain().getSpecies()));
    }

    @Test
    public void getRandomPokemonTest() throws Exception {
        given(pokemonClient.getRandomPokemon()).willReturn(pokemon);
        given(pokemonClient.getEvolutionChain("www.testurl.com")).willReturn(evolutionChain);

        mockMvc.perform(get("/random")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(pokemon.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.evolutionChain.chain.species").value(pokemon.getEvolutionChain().getChain().getSpecies()));
    }

    @Test
    public void getPokemonByNameTest() throws Exception {
        given(pokemonClient.getPokemonByName("Test")).willReturn(pokemon);
        given(pokemonClient.getEvolutionChain("www.testurl.com")).willReturn(evolutionChain);

        mockMvc.perform(get("/pokemon?name=Test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(pokemon.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.evolutionChain.chain.species").value(pokemon.getEvolutionChain().getChain().getSpecies()));
    }
}
