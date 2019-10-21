package br.com.desafiob2w.starwarapi.resource;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.desafiob2w.starwarapi.document.Planeta;
import br.com.desafiob2w.starwarapi.service.PlanetaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlanetaResourceExcluiTest {
	
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
	public void excluirPlanetaPorId() throws Exception {
		Long Id = 3L;
		when(planetaService.buscarPlanetaPorId(Id)).thenReturn(this.planeta());
		doNothing().when(planetaService).excluirPlaneta(Id);
		mockMvc.perform(delete("/planeta/{Id}", 3L))
				.andExpect(status().isOk());
		verify(planetaService, times(1)).excluirPlaneta(3L);
	}
	
	@Test
	public void excluirPlanetaPorIdNotFound() throws Exception{
		Long Id = null;
		when(planetaService.buscarPlanetaPorId(null)).thenReturn(this.planeta());
		doNothing().when(planetaService).excluirPlaneta(null);
		mockMvc.perform(delete("/planeta/{Id}", Id))
        .andExpect(status().isNotFound());
	}
	
	@Test
	public void excluirPlanetaPorIdMettodNotAllowed() throws Exception{
		Long Id = 3L;
		when(planetaService.buscarPlanetaPorId(Id)).thenReturn(this.planeta());
		doNothing().when(planetaService).excluirPlaneta(Id);
		mockMvc.perform(delete("/planeta/"))
        .andExpect(status().isNotFound());
	}
	
	public Planeta planeta() {
		Planeta planeta = new Planeta(
				3L, "planeta3", "clima", "terreno", 1);
		return planeta;
	}

}
