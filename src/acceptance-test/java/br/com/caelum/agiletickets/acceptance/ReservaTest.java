package br.com.caelum.agiletickets.acceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.agiletickets.acceptance.page.EstabelecimentosPage;

public class ReservaTest {
	
	public static String BASE_URL = "http://localhost:8080";
	private WebDriver browser;
	private EstabelecimentosPage estabelecimentos;
	
	@Before
	public void setUp() throws Exception{
		browser = new FirefoxDriver();
		estabelecimentos = new EstabelecimentosPage(browser);
	}
	
	@After
	public void tearDown(){
		//browser.close();
	}
	
	@Test
	public void umaSessaoCom100IngressosTem95ReservadosEReservaMaisUmDeveriaTerPreco10PorCentroAumentado(){
		
	}
	
}
