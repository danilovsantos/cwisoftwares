package br.com.cwisoftware.exame.exception;

/**
 * @author Danilo Valente
 * Exce��o para arquivo de cota��o remota n�o localizado.
 */

public class RemoteQuotationResourceNotFoundException extends Exception{

	private static final long serialVersionUID = -235290724405083743L;
	
	public RemoteQuotationResourceNotFoundException(){}
	
	public RemoteQuotationResourceNotFoundException(String message){
		super(message);
	}

}
