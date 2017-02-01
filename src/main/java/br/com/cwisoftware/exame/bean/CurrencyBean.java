package br.com.cwisoftware.exame.bean;

import java.math.BigDecimal;

/**
 * @author Danilo Valente
 * Bean que representa atributos do arquivo de cotação.
 */

public class CurrencyBean {
	
	private String dataCotacao;
	private Long codMoeda;
	private String tipo;
	private String moeda;
	private BigDecimal taxaCompra;
	private BigDecimal taxaVenda;
	private BigDecimal paridadeCompra;
	private BigDecimal paridadeVenda;
	
	public String getDataCotacao() {
		return dataCotacao;
	}
	public void setDataCotacao(String dataCotacao) {
		this.dataCotacao = dataCotacao;
	}
	public Long getCodMoeda() {
		return codMoeda;
	}
	public void setCodMoeda(Long codMoeda) {
		this.codMoeda = codMoeda;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	public BigDecimal getTaxaCompra() {
		return taxaCompra;
	}
	public void setTaxaCompra(BigDecimal taxaCompra) {
		this.taxaCompra = taxaCompra;
	}
	public BigDecimal getTaxaVenda() {
		return taxaVenda;
	}
	public void setTaxaVenda(BigDecimal taxaVenda) {
		this.taxaVenda = taxaVenda;
	}
	public BigDecimal getParidadeCompra() {
		return paridadeCompra;
	}
	public void setParidadeCompra(BigDecimal paridadeCompra) {
		this.paridadeCompra = paridadeCompra;
	}
	public BigDecimal getParidadeVenda() {
		return paridadeVenda;
	}
	public void setParidadeVenda(BigDecimal paridadeVenda) {
		this.paridadeVenda = paridadeVenda;
	}

	

}
