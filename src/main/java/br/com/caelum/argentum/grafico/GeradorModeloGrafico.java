package br.com.caelum.argentum.grafico;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.MediaMovelPonderada;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorModeloGrafico {
	private int inicio;
	private int fim;
	private SerieTemporal serie;
	private CartesianChartModel modeloGrafico;
	
	public GeradorModeloGrafico(SerieTemporal serie, int inicio, int fim){
		this.inicio = inicio;
		this.fim = fim;
		this.serie = serie;
		this.modeloGrafico = new CartesianChartModel();
	}
	
	public void plotaIndicador(Indicador indicador){		
		LineChartSeries line = new LineChartSeries(indicador.toString());
		for(int i = inicio; i < fim; i++){
			double value = indicador.calcula(i, serie);
			line.set(i, value);
		}
		this.modeloGrafico.addSeries(line);
	}
	
	
	public CartesianChartModel getModelo(){
		return this.modeloGrafico;
	}
}
