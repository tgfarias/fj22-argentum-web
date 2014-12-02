package br.com.caelum.argentum.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.OhlcChartModel;

import br.com.caelum.argentum.grafico.GeraModeloCandle;
import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.IndicadorAbertura;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelPonderada;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;


@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {
	

	private static final long serialVersionUID = 5924268131065442997L;
	private String mediaMovel = "MediaMovelSimples";
	private String indicadorBase = "IndicadorAbertura";
	
	private List<Negociacao> negociacoes;
	private CartesianChartModel modelo = new CartesianChartModel();
	private OhlcChartModel modeloCandle = new OhlcChartModel();
	
	public ArgentumBean(){
		//Evita chamadas ao WS toda vez que abrir a pagina
		this.negociacoes = new ClienteWebService().getNegociacoes();
		List<Candle> candles = new CandlestickFactory().constroiCandles(this.negociacoes);
		//geraGrafico();		
		GeraModeloCandle geradorCandle = new GeraModeloCandle(candles);
		this.modeloCandle = geradorCandle.getModeloCandle();
		
		this.geraGrafico();
	}

	public void geraGrafico() {
		System.out.println("PLOTANDO: " + mediaMovel + " de " + indicadorBase);
		List<Candle> candles = new CandlestickFactory().constroiCandles(this.negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		GeradorModeloGrafico gerador = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());	
		gerador.plotaIndicador(constroiIndicador());		
		this.modelo = gerador.getModelo();		
	}
	
	public Indicador constroiIndicador(){
		String pacote = "br.com.caelum.argentum.indicadores.";
		
		try {
			Class<?> classIndBase = Class.forName(pacote+this.indicadorBase);
			Indicador indBase = (Indicador) classIndBase.newInstance();
			
			Class<?> classMediaMovel = Class.forName(pacote+this.mediaMovel);
			Constructor<?> constr = classMediaMovel.getConstructor(Indicador.class);
			Indicador mediaMovel = (Indicador) constr.newInstance(indBase);
			
			return mediaMovel;
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Negociacao> getNegociacoes(){
		return this.negociacoes;		
	}
	
	public CartesianChartModel getModelo(){
		return this.modelo;
	}
	
	public OhlcChartModel getModeloCandle(){
		return this.modeloCandle;
	}
	public String getMediaMovel() {
		return mediaMovel;
	}
	public void setMediaMovel(String mediaMovel) {
		this.mediaMovel = mediaMovel;
	}
	public String getIndicadorBase() {
		return indicadorBase;
	}
	public void setIndicadorBase(String indicadorBase) {
		this.indicadorBase = indicadorBase;
	}
	
	
	
	
}
