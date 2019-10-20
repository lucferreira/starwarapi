package br.com.desafiob2w.starwarapi.resource;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		String Id = "1";
		mockMvc.perform(delete("/planeta/{Id}", Id))
				.andExpect(status().isOk());
		
		verify(planetaService, times(1)).excluirPlaneta("1");
	    verifyNoMoreInteractions(planetaService);
	}
	
	@Test
	public void excluirPlanetaPorIdNotFound() throws Exception{
		String Id = null;
		mockMvc.perform(get("/planeta/{Id}", Id))
        .andExpect(status().isNotFound());
	}
	
	@Test
	public void excluirPlanetaPorIdMettodNotAllowed() throws Exception{
		mockMvc.perform(get("/planeta/"))
        .andExpect(status().isNotFound());
	}

}
