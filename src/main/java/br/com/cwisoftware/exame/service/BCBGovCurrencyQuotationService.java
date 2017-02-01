package br.com.cwisoftware.exame.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import br.com.cwisoftware.exame.bean.CurrencyBean;
import br.com.cwisoftware.exame.exception.RemoteQuotationResourceNotFoundException;

/**
 * @author Danilo Valente
 * Classe que implementa servicos para consumo de informações do BCB-GOV
 */
public class BCBGovCurrencyQuotationService implements CurrencyQuotationService{


	private Calendar quotationDate;

	
	
	/**
	 *Obtem contação local ou remota de todas moedas. 
	 *@return List<CurrencyBean>
	 */
	@Override
	public List<CurrencyBean> getQuotationFile(Date dataCotacao){

		this.quotationDate = Calendar.getInstance();
		this.quotationDate.setTime(dataCotacao);

		try{

			return this.getRemoteQuotationFile();

		}catch(RemoteQuotationResourceNotFoundException ex){
			ex.printStackTrace();
			try {
				
				if(this.isNotWorkingDay()){
					this.getPrecedingDay();
					return this.getRemoteQuotationFile();
				}
				
			} catch (RemoteQuotationResourceNotFoundException e) {
				e.printStackTrace();
			}
		}

		return new ArrayList<CurrencyBean>();

	}




	/**
	 *Obtem arquivo contendo informações sobre todas moedas no BCB-GOV. 
	 *@return List<CurrencyBean>
	 */
	private List<CurrencyBean> getRemoteQuotationFile() throws RemoteQuotationResourceNotFoundException{

		try{
			
			Client client = ClientBuilder.newClient();		
			String result = client.target("http://www4.bcb.gov.br")
					.path("/Download/fechamento/"+this.getFileName())
					.request(MediaType.TEXT_PLAIN)
					.get(String.class);
			
			System.out.println("Utilizando "+"http://www4.bcb.gov.br/Download/fechamento/"+this.getFileName());
			
			return this.parseToCurrencyBeanList(result);

		}catch(Exception e){
			throw new RemoteQuotationResourceNotFoundException("Erro ao obter cotação remota em http://www4.bcb.gov.br/Download/fechamento/"+this.getFileName());
		}

	}



	/**
	 * Monta o nome do arquivo com a data atual conforme padrão BCP-GOV.
	 * @return String
	 */
	private String getFileName(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String date = sf.format(this.quotationDate.getTime());
		return date+".csv";
	}



	/**
	 * Realiza parse do arquivo para lista de CurrencyBean.
	 * @param String
	 * @return List<CurrencyBean>
	 */
	private List<CurrencyBean> parseToCurrencyBeanList(String info){

		List<CurrencyBean> list = new ArrayList<CurrencyBean>();

		try {

			String line;
			InputStream is = new ByteArrayInputStream(info.getBytes());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				CurrencyBean currency = this.parseToCurrencyBean(line);
				list.add(currency);
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;

	}



	
	/**
	 * Realiza parse da linha do arquivo para objeto CurrencyBean.
	 * @param String
	 * @return CurrencyBean
	 */
	private CurrencyBean parseToCurrencyBean(String line){

		String[] columns = line.split(";");

		CurrencyBean currency = new CurrencyBean();

		currency.setDataCotacao(columns[0]);
		currency.setCodMoeda(Long.valueOf(columns[1]));
		currency.setTipo(columns[2]);
		currency.setMoeda(columns[3]);
		currency.setTaxaCompra(new BigDecimal(columns[4].replace(",", ".")));
		currency.setTaxaVenda(new BigDecimal(columns[5].replace(",", ".")));
		currency.setParidadeCompra(new BigDecimal(columns[6].replace(",", ".")));
		currency.setParidadeVenda(new BigDecimal(columns[7].replace(",", ".")));

		return currency;

	}


	
	
	/**
	 * Obtem dia útil anterior.
	 */
	private void getPrecedingDay(){
		this.quotationDate.add(Calendar.DATE, -1);
		while(this.isNotWorkingDay()){
			this.quotationDate.add(Calendar.DATE, -1);
		}
	}

	
	
	
	/**
	 * Verifica se a data de cotação informada é um dia útil da semana.
	 * @return boolean
	 */
	private boolean isNotWorkingDay(){
		int dayOfWeek = this.quotationDate.get(Calendar.DAY_OF_WEEK);
		return(dayOfWeek == 1 || dayOfWeek == 7);
	}



}
