package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {
	
	private Indicador outro;
	
	public MediaMovelPonderada(Indicador outro) {
		this.outro = outro;
	}
	@Override
	public double calcula (int posicao, SerieTemporal serie){
		double soma = 0.0;
		int peso = 3;
		for(int i = posicao; i > posicao - 3; i--){			
			soma += this.outro.calcula(posicao, serie) * peso;
			peso--;
		}
		return soma / 6;	
	}
	
	public String toString() {
		return "MMP "+outro.toString();
	}
}
