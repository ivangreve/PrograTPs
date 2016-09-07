package Tests;
import java.io.*;
import java.util.*;



public class CasoDeFatiga {

	public static void generarMatrizFatiga() throws IOException
	{
		int n = 1000;
		
		
		PrintWriter salida = new PrintWriter(new FileWriter("casoFatiga1000.out")); 
		
		salida.println(n);
		
		Random numRand = new Random();
		
		
		for (int i = 0; i < n; i++) {
			salida.println(i+" "+0+" "+1);//AL SER 1 EL PRIMER VALOR, ME ASEGURO QUE LA FUNCION NO GENERE MATRICES LINEALMENTE DEPENDIENTES A NO SER QUE LOS OTROS 999 VALORES DE UNA FILA SEAN EXACTAMENTE IGUALES
			for (int j = 1; j < n; j++) {
				
				salida.println(i+" "+j+" "+(numRand.nextDouble() * 100));
				
			}
		}
		
		for (int i = 0; i < n; i++) {
			salida.println(numRand.nextDouble() * 100);
		}
		
		salida.close();

		System.out.println("Archivo Creado");
	}
	
	/*public static void main(String[] args) throws IOException {

		generarMatrizFatiga();
	}*/

}
