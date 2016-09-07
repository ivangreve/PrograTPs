package sel;

import java.io.*;
import java.util.*;

public class Matriz {

	private double matriz[][];
	private int fila;
	private int columna;

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public double obtenerElemento(int fi, int co) throws DistDimException {
		if (fi < 0 || fi > fila || co < 0 || co > columna)
			throw new DistDimException("La fila o columna recibida es invalida");
		return matriz[fi][co];
	}

	public Matriz(String entrada) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(entrada));
		sc.useLocale(Locale.US);
		fila = sc.nextInt();
		columna = sc.nextInt();
		matriz = new double[fila][columna];
		int i = 0;

		while (i < (fila * columna)) {
			matriz[sc.nextInt()][sc.nextInt()] = sc.nextDouble();
			i++;
		}
		sc.close();
	}

	public Matriz(int fi, int co) {
		this.fila = fi;
		this.columna = co;
		matriz = new double[fila][columna];
	}

	public Matriz(double[][] m) { 
		// Contructor de obj Matriz a partir de un vector de vectores
		this.matriz = new double[m.length][m.length];

		for (int i = 0; i < m.length; i++) {
			this.matriz[i] = m[i].clone();
		}

		this.fila = matriz.length;
		this.columna = matriz[0].length;
	}

	public Matriz multiplicar(double d) {

		Matriz auxmat = new Matriz(this.fila, this.columna);
		for (int i = 0; i < this.fila; i++) {
			for (int j = 0; j < this.columna; j++) {
				auxmat.matriz[i][j] = this.matriz[i][j] * d;

			}
		}
		return auxmat;
	}

	public Vector multiplicar(Vector objVector) throws DistDimException {
		if (this.columna != objVector.getDimension())
			throw new DistDimException("No Compatibles");

		double vec[] = new double[this.fila];
		for (int fi = 0; fi < this.fila; fi++) {
			for (int co = 0; co < this.columna; co++) {
				vec[fi] += (objVector.obtenerElemento(co) * matriz[fi][co]);
			}
		}
		return new Vector(vec);
	}

	public Matriz multiplicar(Matriz m) throws DistDimException {
		if (this.columna != m.fila)
			throw new DistDimException("No Compatibles");
		double[][] mat = new double[this.fila][m.columna];
		for (int i = 0; i < this.fila; i++) {
			for (int j = 0; j < this.columna; j++) {
				for (int j2 = 0; j2 < this.columna; j2++) {
					mat[i][j] += this.matriz[i][j2] * m.matriz[j2][j];
				}
			}
		}
		return new Matriz(mat);
	}

	public Matriz sumar(Matriz m) throws DistDimException {
		if (this.fila != m.fila || this.columna != m.columna)
			throw new DistDimException("Dimesiones incompatibles");
		Matriz auxmat = new Matriz(m.matriz);
		for (int i = 0; i < this.fila; i++) {
			for (int j = 0; j < this.columna; j++) {
				auxmat.matriz[i][j] += this.matriz[i][j];
			}
		}
		return auxmat;
	}

	public Matriz restar(Matriz m) throws DistDimException {
		if (this.fila != m.fila || this.columna != m.columna)
			throw new DistDimException("Dimesiones incompatibles");
		Matriz auxmat = new Matriz(m.matriz);
		for (int i = 0; i < this.fila; i++) {
			for (int j = 0; j < this.columna; j++) {
				auxmat.matriz[i][j] = this.matriz[i][j] - auxmat.matriz[i][j];
			}
		}
		return auxmat;
	}

	public Matriz clone() {
		return new Matriz(this.matriz);
	}

	public String toString() {
		String salida = "";
		for (int i = 0; i < this.fila; i++) {

			for (int j = 0; j < this.columna; j++) {
				salida += matriz[i][j] + "\t";

			}
			salida += "\n";
		}
		return salida;
	}

	public double determinante() throws DistDimException {
		if (this.fila != this.columna) {
			throw new DistDimException("No es una matriz cuadrada");
		}
		Matriz aux = this.clone();
		int i, j, k, n;
		double resultado = 1;
		/*
		 * Este For me define la cantidad de iteraciones que tengo que hacer
		 * hasta triangular la matriz que es igual al orden de la matriz menos
		 * 1,i representa la fila del pivote
		 */
		for (i = 0; i < this.fila - 1; i++) {
			/* Si el pivote es cero se saltea este paso y voy al else */
			if (aux.matriz[i][i] != 0) {
				/*
				 * Bucles para poner en ceros los pivotes debajo del pivote
				 * anterior el [i][i] con operaciones elementales de filas
				 */
				for (j = i
						+ 1; j < this.fila; j++) {/*
													 * Me posiciono en la fila
													 * siguiente para poner en
													 * ceros los pivotes
													 */
					double compensadordepivote = (aux.matriz[j][i] / aux.matriz[i][i]);
					for (k = 0; k < this.columna; k++) {/*
														 * Bucle para
														 * desplazarme entre
														 * columnas
														 */
						// /NOTA PUSE K=0 Y ESTABA K=I
						aux.matriz[j][k] -= compensadordepivote * aux.matriz[i][k];
						/*
						 * aux.matriz[j][k]-=(aux.matriz[j][k]/aux.matriz[i][k])
						 * * aux.matriz[i][k];
						 */
					} // /Fin del for interno
				} // /Fin del FOR intermedio
			} // /Fin del IF
			else {/*
					 * Caso que el pivote sea cero,invierto el orden de filas,
					 * la de pivote cero la paso a la siguiente fila, y la
					 * siguiente a la posicion anterior,eso implica multiplicar
					 * el determinante por menos uno, por propiedades de los
					 * determinantes
					 */

				for (n = 0; n < this.columna; n++) {
					double auxiliar = aux.matriz[i][n];
					aux.matriz[i][n] = aux.matriz[i + 1][n];
					aux.matriz[i + 1][n] = auxiliar;
				}
				resultado *= -1;
			} // /Fin del else
		} // /Fin del For externo

		j = 0;
		for (i = 0; i < this.fila; i++) {
			resultado *= aux.matriz[i][j];
			j++;
			//Obtengo el determinante multiplicando la diagonal principal


		}
		return resultado;
	}

	public Matriz inversa() throws DistDimException {
		if (this.fila != this.columna || this.determinante() == 0) {
			throw new DistDimException("No se puede invertir la matriz, revise determianate u orden de la misma");
		}
		Matriz identidad = new Matriz(fila, columna);
		Matriz aux = this.clone();
		int filaPrincipal, fi, co, filaTriangulacion, i, j, n, m;
		int pivoteCero = 0;
		for (i = 0; i < this.fila; i++) {
			for (j = 0; j < this.columna; j++) {
				if (i != j) {
					identidad.matriz[i][j] = 0;
				} else {
					identidad.matriz[i][j] = 1;
				}
			}
		}

		// Fin del For externo, y cargada la matriz identidad
		/*
		 * Este For me define la cantidad de iteraciones que tengo que hacer
		 * hasta triangular la matriz que es igual al orden de la matriz menos
		 * 1,i representa la fila del pivote
		 */
		double pivote; // no triangula
		filaPrincipal = 0;
		/* for( i=0;i<this.fil-1;i++) */
		while (filaPrincipal < this.fila - 1) {

			if (pivoteCero == 1) {
				pivoteCero = 0;
				filaPrincipal--;
			}
			if (aux.matriz[filaPrincipal][filaPrincipal] != 0) {
				/*
				 * Bucles para poner en ceros los pivotes debajo del pivote
				 * anterior el [i][i] con operaciones elementales de filas
				 */
				for (filaTriangulacion = filaPrincipal + 1; filaTriangulacion < this.fila; filaTriangulacion++) {
					pivote = (aux.matriz[filaTriangulacion][filaPrincipal] / aux.matriz[filaPrincipal][filaPrincipal]);
					/*
					 * Me posiciono en la fila siguiente para poner en ceros los
					 * pivotes
					 */
					for (co = 0; co < this.columna; co++) {/*
															 * Bucle para
															 * desplazarme entre
															 * columnas
															 */
						aux.matriz[filaTriangulacion][co] -= pivote * aux.matriz[filaPrincipal][co];
						identidad.matriz[filaTriangulacion][co] -= pivote * identidad.matriz[filaPrincipal][co];
					}
				}
			} else {/*
					 * Caso que el pivote sea cero,busco en alguna fila un
					 * elemento que ocupe la misma posicion en columna que el
					 * pivote cero y se la sumo por operaciones elementales de
					 * filas
					 */
				m = 0;
				n = filaPrincipal;
				while (pivoteCero == 0) {
					if (aux.matriz[n][filaPrincipal] != 0) {
						while (m < this.columna) {
							aux.matriz[filaPrincipal][m] += aux.matriz[n][m];
							identidad.matriz[filaPrincipal][m] += identidad.matriz[n][m];
							m++;
						} /// fin del bucle while
						pivoteCero = 1;
					} /// fin del condicional if
					n++;
				} /// fin del while

			} /// Fin del else
			filaPrincipal++;
		} /// Fin del For externo
		/* Ahora hago lo mismo, pero desde la ultima fila hasta la primera */
		for (filaPrincipal = this.fila - 1; filaPrincipal > 0; filaPrincipal--) {
			for (filaTriangulacion = filaPrincipal - 1; filaTriangulacion >= 0; filaTriangulacion--) {
				pivote = (aux.matriz[filaTriangulacion][filaPrincipal] / aux.matriz[filaPrincipal][filaPrincipal]);
				for (co = 0; co < this.columna; co++) {
					aux.matriz[filaTriangulacion][co] -= pivote * aux.matriz[filaPrincipal][co];
					identidad.matriz[filaTriangulacion][co] -= pivote * identidad.matriz[filaPrincipal][co];
				}

			}
		}
		for (fi = 0; fi < this.fila; fi++) {/*
											 * Hago lo mismo pero para dejar los
											 * pivotes en uno
											 */
			pivote = aux.matriz[fi][fi];
			for (co = 0; co < this.columna; co++) {
				aux.matriz[fi][co] /= pivote;
				identidad.matriz[fi][co] /= pivote;
			}

		}
		return identidad;

	}

	public double normaInf() throws DistDimException {
		if (this.fila != this.columna)
			throw new DistDimException("Matriz no cuadrada");

		double filaAcum;
		double[] max = new double[matriz.length];

		for (int i = 0; i < matriz.length; i++) {
			filaAcum = 0;

			for (int j = 0; j < matriz.length; j++) {
				filaAcum += Math.abs(matriz[i][j]);
			}

			max[i] = filaAcum;
		}

		return new Vector(max).mayorValor();
	}

	public double normaUno() throws DistDimException { 
		if (this.fila != this.columna)
			throw new DistDimException("Matriz no cuadrada");
		
		double colAcum;
		double[] max = new double[matriz.length];
				
		for (int j = 0; j < matriz.length; j++) {
			colAcum = 0;
			
			for (int i = 0; i < matriz.length; i++) {
				colAcum += Math.abs(matriz[i][j]);
			}
			
			max[j] = colAcum;
		}
		
		return new Vector(max).mayorValor();
	}
	
	
	public double norma2() throws DistDimException {
		if (this.fila != this.columna)
			throw new DistDimException("Matriz no cuadrada");

		double acum = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				acum += Math.pow(matriz[i][j], 2);
			}
		}

		return Math.sqrt(acum);
	}

}
