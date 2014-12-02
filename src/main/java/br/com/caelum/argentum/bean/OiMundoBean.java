package br.com.caelum.argentum.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OiMundoBean {
	private String nome;
	private String atual;
	
	public String getHoraAtual(){
		
		Calendar data = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YYYY hh:mm");		
		return sdf.format(data.getTime());
	}
	
	
	public String getNome() {
		
		return nome;
	}


	public void setNome(String nome){
		
		this.nome = nome;
	}
	
	public void mostraNome(){
		
		System.out.println("\n "+this.nome);
	}
}
