package br.com.desafiob2w.starwarapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.desafiob2w.starwarapi.document.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String>{
	
	public Planeta findByIdplaneta(String id);
	
	public Planeta findByNome(String nome);
	
	

}
