package test;

import java.io.*;


public class generadorFatiga {

	public static void main(String args[]) throws IOException{
	
		PrintWriter arch = new PrintWriter("Fatiga.in");
		int grado = 100;
		
		arch.println(grado);
	
		for (int i = 0; i <= grado; i++) {
			arch.println(-1+Math.random()*2);
			
		}
		
		arch.close();
		System.out.println("Archivo Creado");
	}
}
