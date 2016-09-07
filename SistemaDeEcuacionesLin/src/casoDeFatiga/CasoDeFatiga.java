package casoDeFatiga;
import java.io.*;
import java.util.*;



public class CasoDeFatiga {

	public static void main(String[] args) throws IOException {

		int n = 1000;
		
		
		PrintWriter salida = new PrintWriter(new FileWriter("casoFatiga1000.out")); 
		
		salida.println(n);
		
		Random numRand = new Random();
		
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				salida.println(i+" "+j+" "+(numRand.nextDouble() * 100));
				
			}
		}
		
		for (int i = 0; i < n; i++) {
			salida.println(numRand.nextDouble() * 100);
		}
		
		salida.close();

		System.out.println("Archivo Creado");
	}

}
