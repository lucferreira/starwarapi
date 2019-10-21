package br.com.desafiob2w.starwarapi.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiob2w.starwarapi.document.Planeta;
import br.com.desafiob2w.starwarapi.repository.PlanetaRepository;
import br.com.desafiob2w.starwarapi.service.PlanetaService;
import br.com.desafiob2w.starwarapi.utils.FilmesGetHttpClient;

@Service
public class PlanetaServiceImpl implements PlanetaService{

	@Autowired
	private PlanetaRepository planetaRepository;

	@Override
	public Planeta buscarPlanetaPorId(Long id) throws ClientProtocolException, IOException {
		Planeta planeta = planetaRepository.findByIdplaneta(id);
		if(planeta == null) {
			return planeta;
		}else {
		Integer quantFilms = FilmesGetHttpClient.jsonQuantPlanetas(planeta.getIdplaneta());
		planeta.setQtdplanetas(quantFilms);
		}
		return planeta;
	}
	
	@Override
	public Planeta buscarPlanetaPorNome(String nome) throws ClientProtocolException, IOException {
		Planeta planeta = planetaRepository.findByNome(nome);
		if(planeta == null) {
			return planeta;
		}else {
		Integer quantFilms = FilmesGetHttpClient.jsonQuantPlanetas(planeta.getIdplaneta());
		planeta.setQtdplanetas(quantFilms);
		}
		return planeta;
	}

	@Override
	public List<Planeta> exibirTodosPlanetas() throws ClientProtocolException, IOException {
		List<Planeta> planetas = planetaRepository.findAll();
		for (Planeta planeta : planetas) {
			planeta.setQtdplanetas(FilmesGetHttpClient.jsonQuantPlanetas(planeta.getIdplaneta()));
		}
		return planetas;
	}

	@Override
	public Planeta incluirNovoPlaneta(Planeta planeta) {
		return planetaRepository.insert(planeta);
	}

	@Override
	public void excluirPlaneta(Long id) {
		planetaRepository.deleteByIdplaneta(id);
	}

	@Override
	public List<Planeta> planetas() {
		return planetaRepository.findAll();
	}
	
	
	
}
