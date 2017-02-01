package br.com.cwisoftware.exame.service;

import java.util.Date;
import java.util.List;

import br.com.cwisoftware.exame.bean.CurrencyBean;

/**
 * @author Danilo Valente
 * Classe que define m�todos padr�es.
 */
public interface CurrencyQuotationService {

	public List<CurrencyBean> getQuotationFile(Date dataCotacao);
	
}
