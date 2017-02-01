package br.com.cwisoftware.exame.exception;

/**
 * @author Danilo Valente
 * Exceção para arquivo de cotação remota não localizado.
 */

public class RemoteQuotationResourceNotFoundException extends Exception{

	private static final long serialVersionUID = -235290724405083743L;
	
	public RemoteQuotationResourceNotFoundException(){}
	
	public RemoteQuotationResourceNotFoundException(String message){
		super(message);
	}

}
