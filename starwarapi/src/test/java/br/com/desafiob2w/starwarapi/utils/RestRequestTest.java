package br.com.desafiob2w.starwarapi.utils;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestRequestTest {
	
	@Mock
	StatusLine statusLine;
	
	@Test
	public void testarLinkRequest() throws ClientProtocolException, IOException {
		String codigo = "1";
		HttpGet request = new HttpGet("http://swapi.co/api/planets/"+codigo);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(request);
		when(statusLine.getStatusCode()).thenReturn(response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testarLinkRequestException() throws ClientProtocolException, IOException {
		String codigo = "0";
		HttpGet request = new HttpGet("http://swapi.co/api/planets/"+codigo);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(request);
		when(statusLine.getStatusCode()).thenReturn(response.getStatusLine().getStatusCode());
	}

}
