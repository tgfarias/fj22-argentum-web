package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {

	public Candle constroiCandleParaData(Calendar data, List<Negociacao> negocios) {

		double maximo = Double.MIN_VALUE;
		double minimo = Double.MAX_VALUE;
		double volume = 0;
		//Alt+Shift+L --> Criar uma variavel local
		for (Negociacao negocio : negocios) {
			volume += negocio.getVolume();
			double preco = negocio.getPreco();
			maximo = (preco > maximo) ? preco : maximo; // IF Ternário
			minimo = (preco < minimo) ? preco : minimo;		
		}

		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(negocios.size() - 1).getPreco();

		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}
	//Criar num novo metodo - refatoracao Ctrl+Shift+M (Cria-se um novo método)
	public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {
		List<Candle> candles = new ArrayList<Candle>();

		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();

		for (Negociacao negociacao : todasNegociacoes) {
			// se não for mesmo dia, fecha candle e reinicia variáveis
			if (!negociacao.isMesmoDia(dataAtual)) {
				controiEGuardaCandle(candles, negociacoesDoDia, dataAtual);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);
		}
		controiEGuardaCandle(candles, negociacoesDoDia, dataAtual);

		return candles;
	}

	public void controiEGuardaCandle(List<Candle> candles,
			List<Negociacao> negociacoesDoDia, Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual, 
												  negociacoesDoDia);
		candles.add(candleDoDia);
	}
}
