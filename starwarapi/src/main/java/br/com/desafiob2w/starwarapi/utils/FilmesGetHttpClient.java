package br.com.desafiob2w.starwarapi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class FilmesGetHttpClient {
	
	public static String retornarDadosGetRequest(String IdCategoria) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://swapi.co/api/planets/"+IdCategoria.toString());
		CloseableHttpResponse response = httpclient.execute(request);
		int status = response.getStatusLine().getStatusCode();
		if (status != 200) {
			throw new RuntimeException("Falha na requisição - " + status);
		}
		 BufferedReader br = new BufferedReader(new InputStreamReader(response
                    .getEntity().getContent()));
         
        String line = "";
        String dados = "";
        while ((line = br.readLine()) != null) {
            dados = line;
        }
		response.close();
		return dados;
	}
	
	public static Integer jsonQuantPlanetas(String IdCategoria) throws ClientProtocolException, IOException {
		String dadosRequest = FilmesGetHttpClient.retornarDadosGetRequest(IdCategoria);
		
		JSONObject jsonObj = new JSONObject(dadosRequest);
		JSONArray dados1 = jsonObj.getJSONArray("films");
		List<JSONArray> listaFilms = Arrays.asList(dados1);
		
		Integer quantidadeFilmesPlaneta = listaFilms.get(0).length();
		
		return quantidadeFilmesPlaneta;
	}

	
}
