package Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CambioAPI {

public class Cambio {

	    private double usd;
	    private double eur;
	    private double gbp;
	    private double chf;
	    private double jpy;
	    private double cny;
	    private double aud;
	    private double cad;
	    private double mxn;
	    private double sek;
	    private double ars;
	 
		public double getUsd() {
			return usd;
		}
		public void setUsd(double usd) {
			this.usd = usd;
		}
		public double getEur() {
			return eur;
		}
		public void setEur(double eur) {
			this.eur = eur;
		}
		public double getGbp() {
			return gbp;
		}
		public void setGbp(double gbp) {
			this.gbp = gbp;
		}
		public double getChf() {
			return chf;
		}
		public void setChf(double chf) {
			this.chf = chf;
		}
		public double getJpy() {
			return jpy;
		}
		public void setJpy(double jpy) {
			this.jpy = jpy;
		}
		public double getCny() {
			return cny;
		}
		public void setCny(double cny) {
			this.cny = cny;
		}
		public double getAud() {
			return aud;
		}
		public void setAud(double aud) {
			this.aud = aud;
		}
		public double getCad() {
			return cad;
		}
		public void setCad(double cad) {
			this.cad = cad;
		}
		public double getMxn() {
			return mxn;
		}
		public void setMxn(double mxn) {
			this.mxn = mxn;
		}
		public double getSek() {
			return sek;
		}
		public void setSek(double sek) {
			this.sek = sek;
		}
		public double getArs() {
			return ars;
		}
		public void setArs(double ars) {
			this.ars = ars;
		}


	    // Faça o mesmo para as outras moedas
	}
	
public Cambio cambioAtual() {
		  try {
			  HttpClient client = HttpClient.newHttpClient();

			  HttpRequest request = HttpRequest.newBuilder()
					  .uri(URI.create("https://open.er-api.com/v6/latest/BRL"))
					  .build();

			  HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			  JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
			  JsonObject rates = json.getAsJsonObject("rates");
			  
			  Cambio cambio = new Cambio();

			  cambio.setUsd(1 / rates.get("USD").getAsDouble());
			  cambio.setEur(1 / rates.get("EUR").getAsDouble());
			  cambio.setGbp(1 / rates.get("GBP").getAsDouble());
			  cambio.setChf(1 / rates.get("CHF").getAsDouble());
			  cambio.setJpy(1 / rates.get("JPY").getAsDouble());
			  cambio.setCny(1 / rates.get("CNY").getAsDouble());
			  cambio.setAud(1 / rates.get("AUD").getAsDouble());
			  cambio.setCad(1 / rates.get("CAD").getAsDouble());
			  cambio.setMxn(1 / rates.get("MXN").getAsDouble());
			  cambio.setSek(1 / rates.get("SEK").getAsDouble());
			  cambio.setArs(1 / rates.get("ARS").getAsDouble());

			  return cambio;

		      } catch (IOException | InterruptedException e) {
			    e.printStackTrace();
			    return null;
		        }
	}
}
