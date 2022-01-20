package br.com.henrique.tarefas.dto;

import java.math.BigDecimal;

public class MetricaTarefaDTO extends StatusDTO {
	
	private static final long serialVersionUID = -3128486143346530062L;
	
	private Integer quantidade;
	private BigDecimal tempoMedioConclusao;
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getTempoMedioConclusao() {
		return tempoMedioConclusao;
	}
	public void setTempoMedioConclusao(BigDecimal tempoMedioConclusao) {
		this.tempoMedioConclusao = tempoMedioConclusao;
	}
}
