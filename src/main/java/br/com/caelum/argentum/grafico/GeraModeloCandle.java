package br.com.caelum.argentum.grafico;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;

import br.com.caelum.argentum.modelo.Candle;

public class GeraModeloCandle implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OhlcChartModel modeloCandle;
	private List<Candle> candles;
	public GeraModeloCandle(List<Candle> candles){
		this.candles = candles;
		this.modeloCandle = new OhlcChartModel();
		plotaGrafico(this.candles);
	}
	
	
	public void plotaGrafico(List<Candle> candles){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		for(Candle c : candles){
			//System.out.println(c.getData().get(Calendar.DAY_OF_MONTH)+"/"+c.getData().get(Calendar.MONTH)+","+ c.getAbertura()+","+ c.getMaximo()+","+ c.getMinimo() +","+ c.getFechamento());
			
			if(c.getData().get(Calendar.MONTH) == 7){
				OhlcChartSeries line = new OhlcChartSeries(sdf.format(c.getData().getTime()), c.getAbertura(), c.getMaximo(), c.getMinimo(), c.getFechamento());
				this.modeloCandle.add(line);	
			}
		}
		
	}
	
	public OhlcChartModel getModeloCandle(){
		return this.modeloCandle;
	}
	
	
}
