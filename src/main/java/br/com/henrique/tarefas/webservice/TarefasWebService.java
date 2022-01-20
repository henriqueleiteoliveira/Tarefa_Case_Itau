package br.com.henrique.tarefas.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henrique.tarefas.dto.RetornoDTO;
import br.com.henrique.tarefas.dto.StatusDTO;
import br.com.henrique.tarefas.dto.TarefaDTO;
import br.com.henrique.tarefas.facade.TarefasFacade;

@RestController
@RequestMapping("/tarefas")
public class TarefasWebService {
	@Autowired
	TarefasFacade tarefasFacade;
	
	@PostMapping
	@RequestMapping("/criar-status")
	public RetornoDTO criarStatus(@RequestBody StatusDTO statusDTO) {
		return tarefasFacade.criarStatus(statusDTO);
	}
	
	@GetMapping
	@RequestMapping("/listar-status")
	public RetornoDTO listarStatus(Integer idStatus) {
		return tarefasFacade.listarStatus(idStatus);
	}
	
	@PostMapping
	@RequestMapping("/criar-tarefa")
	public RetornoDTO criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
		return tarefasFacade.criarTarefa(tarefaDTO);
	}
	
	@GetMapping
	@RequestMapping("/listar-tarefa")
	public RetornoDTO listarTarefa(Integer idTarefa) {
		return tarefasFacade.listarTarefa(idTarefa);
	}
	
	@PutMapping
	@RequestMapping("/concluir-tarefa")
	public RetornoDTO concluirTarefa(@RequestBody TarefaDTO tarefaDTO) {
		return tarefasFacade.concluirTarefa(tarefaDTO);
	}
	
	@DeleteMapping
	@RequestMapping("/deletar-tarefa")
	public RetornoDTO deletarTarefa(@RequestBody TarefaDTO tarefaDTO){
		return tarefasFacade.deletarTarefa(tarefaDTO);
	}
	
	@GetMapping
	@RequestMapping("/listar-volume-tarefa")
	public RetornoDTO listarVolumeTarefas(){
		return tarefasFacade.listarVolumeTarefas();		
	}
}
