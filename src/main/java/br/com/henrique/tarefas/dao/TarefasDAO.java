package br.com.henrique.tarefas.dao;

import java.util.List;

import br.com.henrique.tarefas.dto.MetricaTarefaDTO;
import br.com.henrique.tarefas.dto.StatusDTO;
import br.com.henrique.tarefas.dto.TarefaDTO;

public interface TarefasDAO {

	public List<StatusDTO> listarStatus(Integer idStatus) throws Exception;

	public List<StatusDTO> criarStatus(StatusDTO statusDTO) throws Exception;

	public List<TarefaDTO> criarTarefa(TarefaDTO tarefaDTO) throws Exception;

	public List<TarefaDTO> listarTarefa(Integer idTarefa) throws Exception;

	public Boolean concluirTarefa(TarefaDTO tarefaDTO) throws Exception;

	public Boolean deletarTarefa(TarefaDTO tarefaDTO) throws Exception;

	public List<MetricaTarefaDTO> listarVolumeTarefas() throws Exception;

}
