package br.com.cwisoftware.exame.test;

import java.math.BigDecimal;

import br.com.cwisoftware.exame.controller.CurrencyQuotationController;

public class TesteQuotation {
	
	public static void main(String[]args){
		
		CurrencyQuotationController controller = new CurrencyQuotationController();
		BigDecimal resultado = controller.currencyQuotation("USD", "EUR", 100.00, "31/01/2017");
		System.out.println(resultado);
	
	}

}
