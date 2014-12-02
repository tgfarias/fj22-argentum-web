package br.com.caelum.argentum.indicadores;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimplesTest {

	@Test
	public void sequenciaSimplesDeCandles() throws Exception {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1,2,3,4,3,4,5,4,3);
		
		MediaMovelSimples mms = new MediaMovelSimples(new IndicadorFechamento());
		
		Assert.assertEquals(2.0, mms.calcula(1,serie),	0.0001);
		Assert.assertEquals(3.0, mms.calcula(2,serie),	0.0001);
		Assert.assertEquals(4, mms.calcula(3, serie), 0.00001);

		
	}

}
