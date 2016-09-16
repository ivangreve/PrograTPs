package test;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import pacPolinomio.Polinomio;

public class PolinomioTests {

	
	@Test
	public void ProbarMSucesivas() throws FileNotFoundException{
		Polinomio p1 = new Polinomio("entrada.in");
		
		Assert.assertEquals(769, p1.evaluarMSucesivas(2), 0.01);

	}
	
	@Test
	public void ProbarRecursivaNoOptima() throws FileNotFoundException{
		Polinomio p1 = new Polinomio("entrada.in");
		
		Assert.assertEquals(769, p1.evaluarRecursivaNoOptima(2), 0.01);
		
	}
	
	@Test
	public void ProbarRecursivaOptima() throws FileNotFoundException{
		Polinomio p1 = new Polinomio("entrada.in");
		
		Assert.assertEquals(769, p1.evaluarRecursivaOptima(2), 0.01);
	
	}
	
	@Test
	public void ProbarPow() throws FileNotFoundException{
		Polinomio p1 = new Polinomio("entrada.in");
		
		Assert.assertEquals(769, p1.evaluarPow(2), 0.01);
	}
	
	@Test
	public void ProbarProgDinamica() throws FileNotFoundException{
		Polinomio p1 = new Polinomio("entrada.in");
		
		Assert.assertEquals(769, p1.evaluarProgDinamica(2), 0.01);
		
	}
	
}
