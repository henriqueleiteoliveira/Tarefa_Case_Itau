package br.com.henrique.tarefas.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.tarefas.dao.TarefasDAO;
import br.com.henrique.tarefas.dto.MetricaTarefaDTO;
import br.com.henrique.tarefas.dto.StatusDTO;
import br.com.henrique.tarefas.dto.TarefaDTO;
import br.com.henrique.tarefas.facade.TarefasFacade;

@Service
public class TarefasFacadeImpl implements TarefasFacade {
	
	@Autowired
	TarefasDAO tarefasDAO;

	@Override
	public List<StatusDTO> listarStatus(Integer idStatus) throws Exception{
		return tarefasDAO.listarStatus(idStatus);
	}

	@Override
	public StatusDTO criarStatus(StatusDTO statusDTO) throws Exception {
		return tarefasDAO.criarStatus(statusDTO);
	}

	@Override
	public TarefaDTO criarTarefa(TarefaDTO tarefaDTO) throws Exception {
		return tarefasDAO.criarTarefa(tarefaDTO);
	}

	@Override
	public List<TarefaDTO> listarTarefa(Integer idTarefa) throws Exception {
		return tarefasDAO.listarTarefa(idTarefa);
	}

	@Override
	public List<TarefaDTO> concluirTarefa(TarefaDTO tarefaDTO) throws Exception {
		if(tarefaDTO.getIdTarefa() == null) {
			throw new Exception("É necessário passar o id da tarefa para concluir a mesma.");
		}
		Boolean result = tarefasDAO.concluirTarefa(tarefaDTO);
		if(result) {
			return listarTarefa(tarefaDTO.getIdTarefa());
		} else {
			throw new Exception("Não foi concluido corretamente.");
		}
	}

	@Override
	public void deletarTarefa(TarefaDTO tarefaDTO) throws Exception {
		if(tarefaDTO.getIdTarefa() == null) {
			throw new Exception("É necessário passar o id da tarefa para poder excluir.");
		}
		
		Boolean result = tarefasDAO.deletarTarefa(tarefaDTO);
		if(!result) {
			throw new Exception("Não foi excluído corretamente.");
		}
	}

	@Override
	public List<MetricaTarefaDTO> listarVolumeTarefas() throws Exception {
		return tarefasDAO.listarVolumeTarefas();
	}
}
