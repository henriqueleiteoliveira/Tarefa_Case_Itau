package br.com.henrique.tarefas.facade;

import java.util.List;

import br.com.henrique.tarefas.dto.MetricaTarefaDTO;
import br.com.henrique.tarefas.dto.RetornoDTO;
import br.com.henrique.tarefas.dto.StatusDTO;
import br.com.henrique.tarefas.dto.TarefaDTO;

public interface TarefasFacade {

	public RetornoDTO listarStatus(Integer idStatus);

	public RetornoDTO criarStatus(StatusDTO statusDTO);

	public RetornoDTO criarTarefa(TarefaDTO tarefaDTO);

	public RetornoDTO listarTarefa(Integer idTarefa);

	public RetornoDTO concluirTarefa(TarefaDTO tarefaDTO);

	public RetornoDTO deletarTarefa(TarefaDTO tarefaDTO);

	public RetornoDTO listarVolumeTarefas();

}
