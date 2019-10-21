package br.com.desafiob2w.starwarapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.desafiob2w.starwarapi.document.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, Long>{
	
	public Planeta findByIdplaneta(Long id);
	
	public Planeta findByNome(String nome);
	
	public void deleteByIdplaneta(Long id);
	
	

}
