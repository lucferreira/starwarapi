package br.com.desafiob2w.starwarapi.resource;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafiob2w.starwarapi.document.Planeta;
import br.com.desafiob2w.starwarapi.service.PlanetaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/planeta")
@Api(value = "Endpoints de consulta dos planetas do filme StarWars")
public class PlanetaResource {
	
	@Autowired
	private PlanetaService planetaService;
	
	@GetMapping("/id/{Id}")
	@ApiOperation(value = "Consulta de planeta por Id")
	public ResponseEntity<Planeta> consultarPlanetasId(@PathVariable Long Id) throws ClientProtocolException, IOException{
		Planeta planeta = planetaService.buscarPlanetaPorId(Id);
		if (planeta == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Planeta não encontrado com esse Id");
		}
		return ResponseEntity.ok(planeta);
	}
	
	@GetMapping("/todos")
	@ApiOperation(value = "Exibe todos os planetas")
	public ResponseEntity<List<Planeta>> mostrarTodosOsPlanetas() throws ClientProtocolException, IOException{
		List<Planeta> planetas = planetaService.exibirTodosPlanetas();
		if (planetas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe planetas");
		}
		return ResponseEntity.ok(planetas);
	}
	
	@GetMapping("/nome/{nome}")
	@ApiOperation(value = "Consulta de planeta por Nome")
	public ResponseEntity<Planeta> consultaPlanetaPorNome(@PathVariable String nome) throws ClientProtocolException, IOException{
		Planeta planeta = planetaService.buscarPlanetaPorNome(nome);
		if (planeta == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Planeta não encontrado com esse nome");
		}
		
		return ResponseEntity.ok(planeta);
	}
	
	@PostMapping("/novo")
	@ApiOperation(value = "Incluir um novo planeta")
	public ResponseEntity<Planeta> incluirNovoPlaneta(@RequestBody Planeta planeta) throws ClientProtocolException, IOException{
		Planeta isPlaneta = planetaService.buscarPlanetaPorId(planeta.getIdplaneta());
		if (isPlaneta != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Planeta já incluído");
		}
		//IncrementaId ic = new IncrementaId();
		Long num = getNext();
		planeta.setIdplaneta(num);
		planetaService.incluirNovoPlaneta(planeta);
		return ResponseEntity.ok(planeta);
	}
	
	@DeleteMapping("/{Id}")
	@ApiOperation(value = "Exclui um planeta por Id")
	public ResponseEntity<Planeta> excluirPlaneta(@PathVariable Long Id) throws ClientProtocolException, IOException{
		Planeta isPlaneta = planetaService.buscarPlanetaPorId(Id);
		if (isPlaneta == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Planeta não encontrado");
		}
		planetaService.excluirPlaneta(Id);
		return ResponseEntity.ok().build();
	}
	
	public long getNext() {
		List<Planeta> planetas = planetaService.planetas();
		if (planetas.size() == 0) {
			return planetas.size() + 1L;
		}
		Long idUlt = planetas.get(planetas.size() - 1).getIdplaneta();
		Long idSeq = idUlt + 1;
        return idSeq;
    }
	
}
