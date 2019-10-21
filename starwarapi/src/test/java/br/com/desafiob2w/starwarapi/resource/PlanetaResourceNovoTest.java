package br.com.desafiob2w.starwarapi.resource;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.desafiob2w.starwarapi.document.Planeta;
import br.com.desafiob2w.starwarapi.service.PlanetaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlanetaResourceNovoTest {
	
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
	public void incluiNovoPlanetaTest() throws Exception {
		Planeta plan = new Planeta("planeta1", "clima", "terreno");
		when(planetaService.incluirNovoPlaneta(plan)).thenReturn(planeta());
		mockMvc.perform(post("/planeta/novo")
		.contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(plan)))
		.andExpect(status().isOk());
		verify(planetaService, times(1)).incluirNovoPlaneta(anyObject());
		
	}
	
	@Test
	public void incluiNovoPlanetaTestNotFound() throws Exception {
		Planeta plan = new Planeta("planeta1","clima", "terreno");
		when(planetaService.incluirNovoPlaneta(plan)).thenReturn(this.planeta());
		mockMvc.perform(post("/planeta/")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void incluiNovoPlanetaTestBadRequest() throws Exception {
		Planeta plan = new Planeta("planeta1","clima", "terreno");
		when(planetaService.incluirNovoPlaneta(plan)).thenReturn(null);
		mockMvc.perform(post("/planeta/novo"))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void incluiNovoPlanetaTestMethodNotAllowed() throws Exception {
		Planeta plan = new Planeta("planeta1","clima", "terreno");
		when(planetaService.incluirNovoPlaneta(plan)).thenReturn(null);
		mockMvc.perform(post("/planeta/n"))
		.andExpect(status().isMethodNotAllowed());
		
	}
	
	public Planeta planeta() {
		Planeta planeta = new Planeta("planeta1", "clima", "terreno");
		return planeta;
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
