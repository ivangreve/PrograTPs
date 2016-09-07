package Tests;
import java.io.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import sel.*;

public class SelTests 
{
	public static void selTester(String inRoot,String outRoot, String nombreCaso) throws IOException
	{
		
		Vector x= new Vector (outRoot);
		Sel sel=new Sel (inRoot);
		Matriz A= sel.getMatriz();
		Vector B=sel.getVector();
		Vector Baux=A.multiplicar(x);
		
		if(Math.abs(Baux.norma2()-B.norma2())<=0.000001d)
			System.out.println("El programa "+nombreCaso+ " funciona correctamente");
		else
			System.out.println("El programa "+nombreCaso+" fall�");
	}
	
	public static void main(String[] args) throws IOException {

		selTester("01_caso2x2simple.in","salidaResultado.out","Caso Basico");
	}
}
