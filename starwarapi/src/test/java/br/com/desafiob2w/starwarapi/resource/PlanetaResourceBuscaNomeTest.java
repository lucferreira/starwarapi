package br.com.desafiob2w.starwarapi.resource;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.desafiob2w.starwarapi.document.Planeta;
import br.com.desafiob2w.starwarapi.service.PlanetaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlanetaResourceBuscaNomeTest {
	
	@Mock
	private PlanetaService planetaService;
	
	@InjectMocks
	private PlanetaResource planetaResource;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(planetaResource).build();
	}
	
	@Test
	public void buscaPlanetaPorNomeTest() throws Exception {
		String nome = "planeta1";
		when(planetaService.buscarPlanetaPorNome(nome)).thenReturn(this.planeta());
		mockMvc.perform(get("/planeta/nome/{nome}", "planeta1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		verify(planetaService, times(1)).buscarPlanetaPorNome("planeta1");
	    verifyNoMoreInteractions(planetaService);
	}
	
	@Test
	public void buscaPlanetaPorNomeTestNotFound() throws Exception{
		String nome = "planeta1";
		when(planetaService.buscarPlanetaPorId(nome)).thenReturn(null);
		mockMvc.perform(get("/planeta/nome/{nome}", "planeta1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
	}
	
	@Test
	public void buscaPlanetaPorNomeTestMethodNotAllowed() throws Exception{
		String nome = "planeta1";
		when(planetaService.buscarPlanetaPorId(nome)).thenReturn(this.planeta());
		mockMvc.perform(get("/planeta/nome/").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isMethodNotAllowed());
	}
	
	public Planeta planeta() {
		Planeta planeta = new Planeta(
				"1", "planeta1", "clima", "terreno", 5);
		return planeta;
	}

}
