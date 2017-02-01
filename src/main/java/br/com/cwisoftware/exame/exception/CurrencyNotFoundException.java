package br.com.cwisoftware.exame.exception;

/**
 * @author Danilo Valente
 * Exce��o para tipo de moeda n�o encontrada.
 */
public class CurrencyNotFoundException extends Exception{

	private static final long serialVersionUID = -2060656368541790235L;
	
	public CurrencyNotFoundException(){}
	
	public CurrencyNotFoundException(String message){
		super(message);
	}

}
