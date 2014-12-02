package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {
	
	private Indicador outro;
	
	public MediaMovelSimples(Indicador indicador) {
		this.outro = indicador;
	}
	
	@Override
	public double calcula (int posicao, SerieTemporal serie){
		double soma = 0.0;
		for(int i = posicao; i > posicao - 3; i--){
			soma += this.outro.calcula(posicao, serie);
		}
		return soma / 3;
	}
	
	public String toString() {
		return "MMS "+outro.toString();
	}
}
