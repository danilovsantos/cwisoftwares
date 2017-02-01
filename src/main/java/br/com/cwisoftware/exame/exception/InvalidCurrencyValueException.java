package br.com.cwisoftware.exame.exception;

/**
 * @author Danilo Valente
 * Exceção para valor de moeda inválido (menor que 0).
 */

public class InvalidCurrencyValueException extends Exception{

	private static final long serialVersionUID = -4406320275167294867L;
	
	public InvalidCurrencyValueException(){}
	
	public InvalidCurrencyValueException(String message){
		super(message);
	}

}
