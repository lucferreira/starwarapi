package br.com.desafiob2w.starwarapi.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import br.com.desafiob2w.starwarapi.document.Planeta;

public interface PlanetaService {
	
	public Planeta buscarPlanetaPorId(Long id) throws ClientProtocolException, IOException;
	
	public Planeta buscarPlanetaPorNome(String nome) throws ClientProtocolException, IOException;
	
	public List<Planeta> exibirTodosPlanetas() throws ClientProtocolException, IOException;
	
	public Planeta incluirNovoPlaneta(Planeta planeta);
	
	public void excluirPlaneta(Long id);
	
	public List<Planeta> planetas();
}
