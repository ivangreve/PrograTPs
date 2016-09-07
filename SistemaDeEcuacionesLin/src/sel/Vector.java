package sel;

import java.util.*;
import java.io.*;

public class Vector {

	private int dimension;
	private double vector[];

	public int getDimension() {
		return dimension;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (dimension != other.dimension)
			return false;
		if (!Arrays.equals(vector, other.vector))
			return false;
		return true;
	}

	public Vector clone() {
		Vector aux = new Vector(this.dimension);
		for (int x = 0; x < this.dimension; x++)
			aux.vector[x] = this.vector[x];

		return aux;
	}

	public double obtenerElemento(int pos) throws DistDimException {
		if (pos > this.dimension)
			throw new DistDimException(" Distinta Dimension ");
		return vector[pos];
	}

	public Vector(double args[]) {
		this.dimension = args.length;
		this.vector = args;
	}

	public Vector(String entrada) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(entrada));
		sc.useLocale(Locale.US);
		dimension = sc.nextInt();
		vector = new double[dimension];
		for (int x = 0; x < dimension; x++) {
			vector[x] = sc.nextDouble();

		}
		sc.close();
	}

	public Vector(int dim) {

		vector = new double[dim];
		this.dimension = dim;
		

	}

	public Vector resta(Vector vec) throws DistDimException {
		if (dimension != vec.dimension)
			throw new DistDimException(" Distinta Dimension ");

		Vector aux = new Vector(this.dimension);
		for (int x = 0; x < dimension; x++) {
			aux.vector[x] = this.vector[x] - vec.vector[x];
			
		}

		return aux;
	}

	public Vector suma(Vector vec) throws DistDimException {
		if (dimension != vec.dimension)
			throw new DistDimException(" Distinta Dimension ");

		Vector aux = new Vector(this.dimension);
		for (int x = 0; x < dimension; x++) {
			aux.vector[x] = this.vector[x] + vec.vector[x];
			
		}

		return aux;
	}

	public String toString() {
		String aux = "";
		for (int i = 0; i < vector.length; i++) {
			aux = aux.concat(vector[i] + "\n");
		}
		return aux;
	}

	public double producto(Vector vec) {

		double aux = 0;
		if (dimension != vec.dimension)
			throw new DistDimException(" Distinta Dimension ");
		for (int x = 0; x < dimension; x++) {
			aux += this.vector[x] * vec.vector[x];
		}

		return aux;
	}

	public Vector producto(double escalar) {
		Vector aux = new Vector(this.dimension);
		for (int x = 0; x < this.dimension; x++) {
			aux.vector[x] = this.vector[x] * escalar;
		}
		return aux;
	}

	public Vector producto(Matriz matParametro) throws DistDimException {
		if (matParametro.getFila() != this.dimension)
			throw new DistDimException(" Distinta tamaño de fila ");
		double mul[] = new double[matParametro.getFila()];

		for (int co = 0; co < matParametro.getColumna(); co++) {

			for (int fi = 0; fi < this.dimension; fi++) {
				mul[fi] += this.vector[fi] * matParametro.obtenerElemento(fi, co);
			}

		}
		return new Vector(mul);
	}

	public double norma1() {

		double aux = 0;

		for (int x = 0; x < this.dimension; x++) {
			aux += Math.abs(this.vector[x]);
		}
		return aux;
	}

	public double norma2() {

		double aux = 0;

		for (int x = 0; x < this.dimension; x++) {
			aux += Math.pow(this.vector[x], 2);
		}
		return Math.sqrt(aux);
	}

	public double normaInf() {

		double max = Math.abs(this.vector[0]);

		for (int x = 1; x < this.dimension; x++) {

			if (Math.abs(this.vector[x]) > max)
				max = Math.abs(this.vector[x]);

		}
		return max;
	}

	public double mayorValor() {
		double max = this.vector[0];

		for (int i = 1; i < this.vector.length; i++) {
			if (max < this.vector[i])
				max = this.vector[i];
		}

		return max;
	}

}
