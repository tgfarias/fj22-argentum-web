package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.List;




import org.junit.Assert;
import org.junit.Test;

public class SerieTemporalTest {

	@Test
	public void naoPodeReceberListaNula() {
		List<Candle> candles = new ArrayList<Candle>();
		
		SerieTemporal serie = new SerieTemporal(null);
		Assert.assertEquals(1, serie.getUltimaPosicao());
	}

}
