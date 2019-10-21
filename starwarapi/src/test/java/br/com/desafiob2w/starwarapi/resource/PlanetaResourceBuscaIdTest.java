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
public class PlanetaResourceBuscaIdTest {
	
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
	public void buscaPlanetaPorIdTest() throws Exception {
		//String id = "1";
		Long id = 1L;
		when(planetaService.buscarPlanetaPorId(id)).thenReturn(this.planeta());
		mockMvc.perform(get("/planeta/id/{Id}", id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		verify(planetaService, times(1)).buscarPlanetaPorId(1L);
	    verifyNoMoreInteractions(planetaService);
	}
	
	@Test
	public void buscaPlanetaPorIdTestNotFound() throws Exception{
		Long id = 1L;
		when(planetaService.buscarPlanetaPorId(id)).thenReturn(null);
		mockMvc.perform(get("/planeta/id/{Id}", "1")
		.accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
	}
	
	@Test
	public void buscaPlanetaPorIdTestMethodNotAllowed() throws Exception{
		Long id = 1L;
		when(planetaService.buscarPlanetaPorId(id)).thenReturn(planeta());
		mockMvc.perform(get("/planeta/id/")
		.accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isMethodNotAllowed());
	}
	
	public Planeta planeta() {
		Planeta planeta = new Planeta(
				1L, "planeta1", "clima", "terreno", 5);
		return planeta;
	}

}
