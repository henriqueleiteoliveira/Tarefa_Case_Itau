package br.com.henrique.tarefas.dao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.henrique.tarefas.dao.TarefasDAO;
import br.com.henrique.tarefas.dto.MetricaTarefaDTO;
import br.com.henrique.tarefas.dto.StatusDTO;
import br.com.henrique.tarefas.dto.TarefaDTO;

@Repository
public class TarefasDAOImpl implements TarefasDAO {
	
	@Autowired
    private NamedParameterJdbcTemplate jdbc;
	
	@Override
	public List<StatusDTO> listarStatus(Integer idStatus) throws Exception {
		List<StatusDTO> listaStatus = new ArrayList<StatusDTO>();
		try {
			StringBuilder query = new StringBuilder(" SELECT * FROM status ");
			MapSqlParameterSource params = new MapSqlParameterSource();

			if(idStatus != null) {
				query.append(" where idStatus = :idStatus ");
				params.addValue("idStatus", idStatus);
			}
			
			List<Map<String, Object>> listaRetorno = null;
			listaRetorno = jdbc.queryForList(query.toString(), params);
			
			for(Map<String, Object> mapa : listaRetorno) {
				StatusDTO statusItem = new StatusDTO();
				statusItem.setIdStatus((Integer) mapa.get("idStatus"));
				statusItem.setNomeStatus((String) mapa.get("nomeStatus"));
				listaStatus.add(statusItem);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return listaStatus;
	}

	@Override
	public List<StatusDTO> criarStatus(StatusDTO statusDTO) throws Exception {
		List<StatusDTO> listaStatus = new ArrayList<StatusDTO>();
		try {
			StringBuilder query = new StringBuilder(" INSERT INTO status(nomeStatus) values(:nomeStatus) ");
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("nomeStatus", statusDTO.getNomeStatus());
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbc.update(query.toString(), params, keyHolder);

			StatusDTO statusItem = new StatusDTO();
			statusItem.setIdStatus(keyHolder.getKey().intValue());
			statusItem.setNomeStatus(statusDTO.getNomeStatus());

			listaStatus.add(statusItem);
			
			return listaStatus;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<TarefaDTO> criarTarefa(TarefaDTO tarefaDTO) throws Exception {
		List<TarefaDTO> listaTarefa = new ArrayList<TarefaDTO>();
		try {
			StringBuilder query = new StringBuilder(" INSERT INTO tarefa (nome, dataCriacao, descricao, idStatus, observacao, nomeResponsavel) ")
					.append(" VALUES(:nome, CURRENT_TIMESTAMP, :descricao, :idStatus, :observacao, :nomeResponsavel) ");
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("nome", tarefaDTO.getNome());
			params.addValue("descricao", tarefaDTO.getDescricao());
			params.addValue("idStatus", tarefaDTO.getIdStatus());
			params.addValue("observacao", tarefaDTO.getObservacao());
			params.addValue("nomeResponsavel", tarefaDTO.getNomeResponsavel());
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbc.update(query.toString(), params, keyHolder);

			TarefaDTO tarefaItem = new TarefaDTO();
			tarefaItem.setDataCriacao(LocalDateTime.now());			
			tarefaItem.setIdTarefa(keyHolder.getKey().intValue());
			tarefaItem.setNome(tarefaDTO.getNome());

			listaTarefa.add(tarefaItem);

			return listaTarefa;			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<TarefaDTO> listarTarefa(Integer idTarefa) throws Exception {
		List<TarefaDTO> listaTarefa = new ArrayList<TarefaDTO>();
		try {
			StringBuilder query = new StringBuilder(" SELECT tar.*, stts.nomeStatus FROM tarefa tar ")
					.append(" INNER JOIN status stts ON(tar.idStatus = stts.idStatus)");
			
			MapSqlParameterSource params = new MapSqlParameterSource();

			if(idTarefa != null) {
				query.append(" where tar.idTarefa = :idTarefa ");
				params.addValue("idTarefa", idTarefa);
			}
			
			List<Map<String, Object>> listaRetorno = null;
			listaRetorno = jdbc.queryForList(query.toString(), params);
			
			for(Map<String, Object> mapa : listaRetorno) {
				TarefaDTO tarefaItem = new TarefaDTO();
				tarefaItem.setIdTarefa((Integer) mapa.get("idTarefa"));
				tarefaItem.setNome((String) mapa.get("nome"));
				try {
					tarefaItem.setDataCriacao(((Timestamp) mapa.get("dataCriacao")).toLocalDateTime());
				} catch (Exception e) {
					tarefaItem.setDataCriacao(null);
				}
				try {
					tarefaItem.setDataConclusao(((Timestamp) mapa.get("dataConclusao")).toLocalDateTime());					
				} catch (Exception e) {
					tarefaItem.setDataConclusao(null);
				}
				tarefaItem.setDescricao((String) mapa.get("descricao"));
				tarefaItem.setIdStatus((Integer) mapa.get("idStatus"));
				tarefaItem.setObservacao((String) mapa.get("observacao"));
				tarefaItem.setNomeResponsavel((String) mapa.get("nomeResponsavel"));
				tarefaItem.setNomeStatus((String) mapa.get("nomeStatus"));
				
				listaTarefa.add(tarefaItem);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return listaTarefa;
	}

	@Override
	public Boolean concluirTarefa(TarefaDTO tarefaDTO) throws Exception {
		try {
			StringBuilder query = new StringBuilder(" UPDATE tarefa SET idStatus = (select idStatus from status where nomeStatus = :nomeStatus ORDER BY idStatus ASC limit 1), ")
					.append(" dataConclusao = CURRENT_TIMESTAMP where idTarefa = :idTarefa ");
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("idTarefa", tarefaDTO.getIdTarefa());
			//o valor está fixo, mas poderia ser substituido por um enum
			params.addValue("nomeStatus", "completed");

			return (jdbc.update(query.toString(), params) > 0);
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public Boolean deletarTarefa(TarefaDTO tarefaDTO) throws Exception {
		try {
			StringBuilder query = new StringBuilder(" DELETE FROM tarefa ")
					.append(" where idTarefa = :idTarefa ");
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("idTarefa", tarefaDTO.getIdTarefa());

			return (jdbc.update(query.toString(), params) > 0);
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<MetricaTarefaDTO> listarVolumeTarefas() throws Exception {
		List<MetricaTarefaDTO> listaMetrica = new ArrayList<MetricaTarefaDTO>();
		try {
			StringBuilder query = new StringBuilder(" SELECT stts.idStatus, stts.nomeStatus, count(tar.idTarefa) as quantidade, ")
					.append(" (SELECT AVG(TIMESTAMPDIFF(SECOND, dataCriacao, dataConclusao)) FROM tarefa auxTar ")
					.append(" 	where auxTar.dataConclusao is not null AND auxTar.idStatus = stts.idStatus) as tempoMedio")
					.append(" FROM tarefa tar ")
					.append(" INNER JOIN status stts ON(tar.idStatus = stts.idStatus) ")
					.append(" group by stts.idStatus ");
			
			List<Map<String, Object>> listaRetorno = null;
			listaRetorno = jdbc.queryForList(query.toString(), new MapSqlParameterSource());
			
			for(Map<String, Object> mapa : listaRetorno) {
				MetricaTarefaDTO metricaItem = new MetricaTarefaDTO();
				metricaItem.setIdStatus((Integer) mapa.get("idStatus"));
				metricaItem.setNomeStatus((String) mapa.get("nomeStatus"));
				metricaItem.setQuantidade(((Long) mapa.get("quantidade")).intValue());
				metricaItem.setTempoMedioConclusao((BigDecimal) mapa.get("tempoMedio"));
				
				if(metricaItem.getTempoMedioConclusao() != null) {
					metricaItem.setTempoMedioConclusao(metricaItem.getTempoMedioConclusao().setScale(2, RoundingMode.HALF_EVEN));
				}
				
				listaMetrica.add(metricaItem);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return listaMetrica;
	}

}
