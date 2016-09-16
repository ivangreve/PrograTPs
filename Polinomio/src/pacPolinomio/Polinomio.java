package pacPolinomio;
import java.util.*;
import java.io.*;
import java.util.GregorianCalendar;
import java.util.Calendar;


public class Polinomio {

	private int grado;
	private double coef[];
	
	
	public Polinomio(String pathEntrada) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new File(pathEntrada));
		sc.useLocale(Locale.ENGLISH);
		grado = sc.nextInt();
		
		coef = new double[grado+1];
		
		for (int i = 0; i < grado+1; i++) {
			coef[i] = sc.nextDouble();
			
		}
		
		sc.close();
	}
	
	
	public double evaluarMSucesivas(double x){ //Complejidad N2
		
		double resultado = 0;
		double xElev;
		
		if(x == 0)
		{
			return coef[this.grado];
		}
		
		for (int i = this.grado; i >= 0; i--) {
			xElev = 1;
			
			
			for (int j = 0; j < i; j++) {
				xElev *= x;
				
			}
			resultado += coef[this.grado-i]*xElev;
			
		}
		
		
		return resultado;
	}
	
	
	public static double potenciaOptima(double x,int n)
	{
		
		if(n == 0)
			return 1;
		
		if(n == 1)
			return x;
		
		if(n%2 == 0)
			return potenciaOptima(x*x, n/2);
		
			
		return x*potenciaOptima(x,n-1);
		
	}
	
	public static double potenciaNoOptima(double x,int n)
	{
		
		if(n == 0)
			return 1;
		
		if(n == 1)
			return x;
			
		return x*potenciaNoOptima(x,n-1);
		
	}
	
	public double evaluarRecursivaNoOptima(double x){ //N2
		double resultado = 0;
		if(x == 0)
		{
			return coef[this.grado];
		}
		
		for (int i = this.grado; i >= 0; i--) {
			
			resultado += coef[this.grado-i]*potenciaNoOptima(x,i);
		}
		
		
		return resultado;

	}
	
	public double evaluarProgDinamica(double x){ //Complejidad N
		double resultado = 0;
		double multi = 1;
		
		if(x == 0)
		{
			return coef[this.grado];
		}
		
		for (int i = grado - 1; i >= 0 ; i--) {
			multi *= x;
			resultado += coef[i]*multi;
		}
		
		
		return resultado+coef[grado];

	}
	
	public double evaluarRecursivaOptima(double x){ //Verificar en el Grafico
		double resultado = 0;
		if(x == 0)
		{
			return coef[this.grado];
		}
		
		for (int i = this.grado; i >= 0; i--) {
			
			resultado += coef[this.grado-i]*potenciaOptima(x,i);
		}
		
		
		return resultado;

	}
	
	public double evaluarPow(double x){ //N2
		double resultado = 0;
		if(x == 0)
		{
			return coef[this.grado];
		}
		
		for (int i = this.grado; i >= 0; i--) {
			
			resultado += coef[this.grado-i]*Math.pow(x,i);
		}
		
		
		return resultado;

	}
	
	public String toString(){
		
		String aux = "";
		
		for (int i = 0; i < coef.length; i++) {
		
			aux += coef[i] + " ";
		}
		return aux;
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		
		
		Polinomio p1;
		p1 = new Polinomio("Fatiga.in");
		//System.out.println(p1);
		System.out.println(p1.evaluarRecursivaNoOptima(0.5));
		System.out.println(p1.evaluarProgDinamica(0.5));
		//System.out.println(p1.evaluarRecursivaOptima(0.5));
		Calendar tIni = new GregorianCalendar();
		p1.evaluarProgDinamica(0.5);
		Calendar tFin = new GregorianCalendar();

		long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
		System.out.println(diff);

	}
}

