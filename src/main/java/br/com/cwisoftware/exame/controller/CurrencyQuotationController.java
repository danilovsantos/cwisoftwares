package br.com.cwisoftware.exame.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.cwisoftware.exame.bean.CurrencyBean;
import br.com.cwisoftware.exame.exception.CurrencyNotFoundException;
import br.com.cwisoftware.exame.exception.InvalidCurrencyValueException;
import br.com.cwisoftware.exame.service.BCBGovCurrencyQuotationService;

/**
 * @author Danilo Valente
 * Controller que realiza cotação. 
 */

public class CurrencyQuotationController {
	
	private List<CurrencyBean> currencies;
	private CurrencyBean fromBean;
	private CurrencyBean toBean;
	
	
	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) {
		
		try {
			
			BCBGovCurrencyQuotationService service = new BCBGovCurrencyQuotationService();
			
			this.currencies = service.getQuotationFile(new SimpleDateFormat("dd/MM/yyyy").parse(quotation));
			this.validate(from, to, value);
			
			BigDecimal valor = new BigDecimal(value.doubleValue());
			BigDecimal paridade = this.toBean.getParidadeCompra();
			BigDecimal resultado = valor.divide(paridade,2, RoundingMode.CEILING);
			
			return resultado;
			
		} catch (ParseException  | CurrencyNotFoundException  | InvalidCurrencyValueException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private void validate(String from, String to, Number value) throws CurrencyNotFoundException, InvalidCurrencyValueException{
		
		for(CurrencyBean bean : this.currencies){
			
			if(bean.getMoeda().equals(from)){
				this.fromBean = bean;
			}
			
			if(bean.getMoeda().equals(to)){
				this.toBean = bean;
			}
			
		}
		
		if(this.fromBean == null){
			throw new CurrencyNotFoundException("Moeda ("+from+") não encontrada!");
		}
		
		if(this.toBean == null){
			throw new CurrencyNotFoundException("Moeda ("+to+") não encontrada!");
		}
		
		if(value.intValue() < 0){
			throw new InvalidCurrencyValueException("Valor de cotação menor que zero!");
		}
		
	}
	
	
}
