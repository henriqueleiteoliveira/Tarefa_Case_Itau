package br.com.henrique.tarefas.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henrique.tarefas.dto.MetricaTarefaDTO;
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
	public StatusDTO criarStatus(@RequestBody StatusDTO statusDTO) {
		try {
			return tarefasFacade.criarStatus(statusDTO);
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping
	@RequestMapping("/listar-status")
	public List<StatusDTO> listarStatus(Integer idStatus) {
		try {
			return tarefasFacade.listarStatus(idStatus);
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping
	@RequestMapping("/criar-tarefa")
	public TarefaDTO criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
		try {
			return tarefasFacade.criarTarefa(tarefaDTO);
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping
	@RequestMapping("/listar-tarefa")
	public List<TarefaDTO> listarTarefa(Integer idTarefa) {
		try {
			return tarefasFacade.listarTarefa(idTarefa);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@PutMapping
	@RequestMapping("/concluir-tarefa")
	public List<TarefaDTO> concluirTarefa(@RequestBody TarefaDTO tarefaDTO) {
		try {
			return tarefasFacade.concluirTarefa(tarefaDTO);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@DeleteMapping
	@RequestMapping("/deletar-tarefa")
	public void deletarTarefa(@RequestBody TarefaDTO tarefaDTO){
		try {
			tarefasFacade.deletarTarefa(tarefaDTO);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@GetMapping
	@RequestMapping("/listar-volume-tarefa")
	public List<MetricaTarefaDTO> listarVolumeTarefas(){
		try {
			return tarefasFacade.listarVolumeTarefas();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
}
