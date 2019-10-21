package br.com.desafiob2w.starwarapi.utils;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FilmesGetHttpClientTest {
	
	@Test
	public void buscaFilmesPorIdTest() throws ClientProtocolException, IOException {
		Long codigo = 1L;
		assertFalse(FilmesGetHttpClient.retornarDadosGetRequest(codigo).isEmpty());
	} 
	
	@Test(expected = RuntimeException.class)
	public void buscaFilmesPorIdException() throws ClientProtocolException, IOException {
		Long codigo = null;
		Assert.assertNotNull(FilmesGetHttpClient.retornarDadosGetRequest(codigo));
	}

}
