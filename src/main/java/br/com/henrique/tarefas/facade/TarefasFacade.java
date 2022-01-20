package br.com.henrique.tarefas.facade;

import java.util.List;

import br.com.henrique.tarefas.dto.MetricaTarefaDTO;
import br.com.henrique.tarefas.dto.StatusDTO;
import br.com.henrique.tarefas.dto.TarefaDTO;

public interface TarefasFacade {

	public List<StatusDTO> listarStatus(Integer idStatus) throws Exception;

	public StatusDTO criarStatus(StatusDTO statusDTO) throws Exception;

	public TarefaDTO criarTarefa(TarefaDTO tarefaDTO) throws Exception;

	public List<TarefaDTO> listarTarefa(Integer idTarefa) throws Exception;

	public List<TarefaDTO> concluirTarefa(TarefaDTO tarefaDTO) throws Exception;

	public void deletarTarefa(TarefaDTO tarefaDTO) throws Exception;

	public List<MetricaTarefaDTO> listarVolumeTarefas() throws Exception;

}
