package br.com.henrique.tarefas.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.tarefas.dao.TarefasDAO;
import br.com.henrique.tarefas.dto.MetricaTarefaDTO;
import br.com.henrique.tarefas.dto.RetornoDTO;
import br.com.henrique.tarefas.dto.StatusDTO;
import br.com.henrique.tarefas.dto.TarefaDTO;
import br.com.henrique.tarefas.facade.TarefasFacade;

@Service
public class TarefasFacadeImpl implements TarefasFacade {
	
	@Autowired
	TarefasDAO tarefasDAO;

	@Override
	public RetornoDTO listarStatus(Integer idStatus) {
		List<StatusDTO> listaStatus;
		RetornoDTO retornoDTO = new RetornoDTO();
		try {
			listaStatus = tarefasDAO.listarStatus(idStatus);
			retornoDTO.setSucesso(true);
			retornoDTO.setData(listaStatus);
		} catch (Exception e) {
			retornoDTO.setMensagem(e.getMessage());
		}
		return retornoDTO;
	}

	@Override
	public RetornoDTO criarStatus(StatusDTO statusDTO) {
		RetornoDTO retornoDTO = new RetornoDTO();
		List<StatusDTO> listaStatus;
		try {
			listaStatus = tarefasDAO.criarStatus(statusDTO);
			retornoDTO.setSucesso(true);
			retornoDTO.setData(listaStatus);
		} catch (Exception e) {
			retornoDTO.setMensagem(e.getMessage());
		}
		return retornoDTO;
	}

	@Override
	public RetornoDTO criarTarefa(TarefaDTO tarefaDTO) {
		RetornoDTO retornoDTO = new RetornoDTO();
		List<TarefaDTO> listaTarefa;
		try {
			listaTarefa = tarefasDAO.criarTarefa(tarefaDTO);
			retornoDTO.setSucesso(true);
			retornoDTO.setData(listaTarefa);
		} catch (Exception e) {
			retornoDTO.setMensagem(e.getMessage());
		}
		return retornoDTO;
	}

	@Override
	public RetornoDTO listarTarefa(Integer idTarefa) {
		RetornoDTO retornoDTO = new RetornoDTO();
		List<TarefaDTO> listaTarefa;
		try {
			listaTarefa = tarefasDAO.listarTarefa(idTarefa);
			retornoDTO.setSucesso(true);
			retornoDTO.setData(listaTarefa);
		} catch (Exception e) {
			retornoDTO.setMensagem(e.getMessage());
		}
		return retornoDTO;
	}

	@Override
	public RetornoDTO concluirTarefa(TarefaDTO tarefaDTO) {
		RetornoDTO retornoDTO = new RetornoDTO();
		List<TarefaDTO> listaTarefa;
		try {
			if(tarefaDTO.getIdTarefa() == null) {
				throw new Exception("É necessário passar o id da tarefa para concluir a mesma.");
			}
			
			Boolean result = tarefasDAO.concluirTarefa(tarefaDTO);
			if(result) {
				listaTarefa = tarefasDAO.listarTarefa(tarefaDTO.getIdTarefa());
				retornoDTO.setSucesso(true);
				retornoDTO.setData(listaTarefa);
			} else {
				throw new Exception("Não foi concluido corretamente.");
			}
		} catch (Exception e) {
			retornoDTO.setMensagem(e.getMessage());
		}
		return retornoDTO;
	}

	@Override
	public RetornoDTO deletarTarefa(TarefaDTO tarefaDTO) {
		RetornoDTO retornoDTO = new RetornoDTO();
		try {
			if(tarefaDTO.getIdTarefa() == null) {
				throw new Exception("É necessário passar o id da tarefa para poder excluir.");
			}
			
			Boolean result = tarefasDAO.deletarTarefa(tarefaDTO);
			if(!result) {
				throw new Exception("Não foi excluído corretamente.");
			}
			retornoDTO.setSucesso(true);
		} catch (Exception e) {
			retornoDTO.setMensagem(e.getMessage());
		}
		return retornoDTO;		
	}

	@Override
	public RetornoDTO listarVolumeTarefas() {
		RetornoDTO retornoDTO = new RetornoDTO();
		List<MetricaTarefaDTO> listaMetrica;
		try {
			listaMetrica = tarefasDAO.listarVolumeTarefas();
			retornoDTO.setSucesso(true);
			retornoDTO.setData(listaMetrica);
		} catch (Exception e) {
			retornoDTO.setMensagem(e.getMessage());
		}
		return retornoDTO;
	}
}
