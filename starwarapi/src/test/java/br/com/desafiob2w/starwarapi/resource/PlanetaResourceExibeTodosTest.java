package br.com.desafiob2w.starwarapi.resource;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
public class PlanetaResourceExibeTodosTest {

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
	public void exibeTodosPlanetasTest() throws Exception {
		when(planetaService.exibirTodosPlanetas()).thenReturn(this.planetas());
		mockMvc.perform(get("/planeta/todos"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		verify(planetaService, times(1)).exibirTodosPlanetas();
	    verifyNoMoreInteractions(planetaService);
	}
	@Test
	public void exibeTodosPlanetasTestNotFound() throws Exception {
		when(planetaService.exibirTodosPlanetas()).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/planeta/todos").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
	}
	
	@Test
	public void exibeTodosPlanetasTestException() throws Exception {
		when(planetaService.exibirTodosPlanetas()).thenReturn(this.planetas());
		mockMvc.perform(get("/planeta/").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
	}
	
	@Test
	public void exibeTodosPlanetasTestMethodNotAllowed() throws Exception {
		when(planetaService.exibirTodosPlanetas()).thenReturn(this.planetas());
		mockMvc.perform(get("/planeta/t").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isMethodNotAllowed());
	}
	
	public List<Planeta> planetas(){
		List<Planeta> listaPlanetas = new ArrayList<>();
		listaPlanetas.add(new Planeta(4L, "planeta2","clima2","terreno2",2));
		listaPlanetas.add(new Planeta(4L, "planeta1","clima1","terreno1",5));
		return listaPlanetas;
	}
	
	
	
}
