/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * Propuesta de solución laboratorio Unidad 5
 * @author Camilo Barrios - camilo.barrios@correo.icesi.edu.co
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package modelo;

public class Buscaminas {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel
	 * principiante
	 */
	public static final int FILAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel
	 * intermedio
	 */
	public static final int FILAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel
	 * experto
	 */
	public static final int FILAS_EXPERTO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel
	 * principiante
	 */
	public static final int COLUMNAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel
	 * intermedio
	 */
	public static final int COLUMNAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel
	 * experto
	 */
	public static final int COLUMNAS_EXPERTO = 30;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el
	 * nivel principiante
	 */
	public static final int PRINCIPIANTE = 1;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el
	 * nivel intermedio
	 */
	public static final int INTERMEDIO = 2;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el
	 * nivel experto
	 */
	public static final int EXPERTO = 3;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel
	 * principiante
	 */
	public static final int CANTIDAD_MINAS_PRINCIPANTE = 10;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel
	 * intermedio
	 */
	public static final int CANTIDAD_MINAS_INTERMEDIO = 40;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel experto
	 */
	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Casilla[][] casillas;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores
	 * PRINCIPIANTE, INTERMEDIO, EXPERTO>
	 */
	private int nivel;

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructo de la clase Buscaminas
	 * 
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Buscaminas(int nivel) {
		this.nivel = nivel;
		perdio = false;
		//inicializarPartida();

	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a
	 * partir del nivel elegido por el usuario
	 */
	public int[][] inicializarPartida(int numeroMinas, int [][] casilla) {

//		if (nivel == PRINCIPIANTE) {
//			cantidadMinas = CANTIDAD_MINAS_PRINCIPANTE;
//			casillas = new Casilla[FILAS_PRINCIPIANTE][COLUMNAS_PRINCIPIANTE];
//
//		}
//
//		else if (nivel == INTERMEDIO) {
//			cantidadMinas = CANTIDAD_MINAS_INTERMEDIO;
//			casillas = new Casilla[FILAS_INTERMEDIO][COLUMNAS_INTERMEDIO];
//		}
//
//		else if (nivel == EXPERTO) {
//			cantidadMinas = CANTIDAD_MINAS_EXPERTO;
//			casillas = new Casilla[FILAS_EXPERTO][COLUMNAS_EXPERTO];
//		}
		
		//50 es libre

		for (int i = 0; i < casilla.length; i++) {
			for (int j = 0; j < casilla[0].length; j++) {
				casilla[i][j] = 50;
			}
		}

		casilla = generarMinas(numeroMinas, casilla);
		casilla = inicializarCasillasLibres(casilla);
		
		return casilla;
	}

	// Este metodo cambia el tipo

	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public int[][] inicializarCasillasLibres(int [][] casilla) {

		for (int f = 0; f < casilla.length; f++) {
			for (int c = 0; c < casilla[0].length; c++) {
				if (casilla[f][c] == 50)
				casilla[f][c] = cantidadMinasAlrededor(f, c, casilla);
			}
		}
		
		return casilla;

	}

	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una
	 * casillas
	 * @param fila    - La fila de la matriz
	 * @param columna - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int fila, int columna, int [][] casilla) {

		int contador = 0;
		int vertical = 0;
		int horizontal = 0;

		for (int i = fila - 1; vertical < 3 && i < casilla.length; i++) {
			for (int j = columna - 1; horizontal < 3; j++) {
				if ((i >= 0 && j >= 0) && (j < casilla[i].length)) {
					if (casilla[i][j] == 100) {
						contador++;
					}
				}
				horizontal++;
			}
			vertical++;
			horizontal = 0;
		}

		return contador;
	}

	/**
	 * Método que se encarga de generar aleatoriomente las minas
	 */
	public int [][] generarMinas(int cantidadMinas, int [][] casilla) {

		int f = 0;
		int c = 0;
		int minas = 0;

		while (minas < cantidadMinas) {

			f = (int) (Math.random() * casilla.length);
			c = (int) (Math.random() * casilla[0].length);

			//Si es 100 es porque tiene una mina
			if (casilla[f][c] != 100) {
				
				casilla[f][c] = 100;
				minas++;
			}
		}
		return casilla;
	} // Cierra el metodo

	/**
	 * Metodo que se encarga de convertir el tablero a un String para poder verlo en
	 * pantalla
	 * 
	 * @return String - El tablero en formato String
	 */
	public String mostrarTablero() {

		String msg = "";

		// Columnas

		msg += ("     ");

		for (int c = 0; c < casillas[0].length; c++) {
			msg += ((c + 1) + "  ");
		}
		msg += "\n" + "\n";

		for (int f = 0; f < casillas.length; f++) {

			if (f <= 8) {
				msg += ((f + 1) + "    ");
			}
			if (f >= 9) {
				msg += (f + 1 + "   ");
			}

			for (int c = 0; c < casillas[0].length; c++) {

				if (c <= 9) {
					msg += (casillas[f][c].mostrarValorCasilla() + "  ");
				}

				if (c > 9) {
					msg += (" " + casillas[f][c].mostrarValorCasilla() + "  ");
				}
			}
			msg += "\n";
		}

		return msg;
	}

	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {

		for (int f = 0; f < casillas.length; f++) {
			for (int c = 0; c < casillas[0].length; c++) {
				casillas[f][c].destapar();
				perdio = true;
			}
		}

	}

	/**
	 * Metodo dar del atributo casillas
	 * 
	 * @return la relacion casillas
	 */
	public Casilla[][] darCasillas() {
		return casillas;
	}

	/**
	 * Este metodo se encargaa de abrir una casilla Si se abre una casilla de tipo
	 * Mina, se marca que el jugador perdio el juego.
	 * 
	 * @param f - la fila donde esta la casilla
	 * @param c - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int f, int c) throws ArrayIndexOutOfBoundsException {

		boolean casillaDestapada = false;

		if (casillas[f][c].darSeleccionada() != true) {
			casillas[f][c].destapar();
			casillaDestapada = true;

			if (casillas[f][c].esMina() == true) {
				perdio = true;

			}
		}
		return casillaDestapada;
	}

	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * 
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {

		boolean ganar = false;
		boolean perder = false;
		boolean sinEquivocarse = false;

		for (int f = 0; f < casillas.length && !perder; f++) {
			for (int c = 0; c < casillas[0].length && !perder; c++) {
				if (casillas[f][c].darSeleccionada() == true && casillas[f][c].esMina() != true) {
					sinEquivocarse = true;
				}
				if (f == casillas.length && c == casillas[0].length) {
					if (sinEquivocarse == true) {
						ganar = true;

					}
				} else {
					darPerdio();
					perder = true;
				}
			}
		}

		return ganar;
	}

	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo
	 * valor sea Mayor que 0
	 * 
	 * @return String, Mensaje de la Casilla que marco abierta, En caso de no haber
	 *         casillas posibles para dar una pista, retorna el mensaje no hay
	 *         pistas para dar
	 */
	public String darPista() {

		String msg = "";
		boolean pistaDada = false;

		for (int f = 0; f < casillas.length && !pistaDada; f++) {
			for (int c = 0; c < casillas[0].length && !pistaDada; c++) {
				if (casillas[f][c].esMina() != true) {
					if (casillas[f][c].darValor() > 0 && casillas[f][c].darSeleccionada() == false) {
						casillas[f][c].destapar();
						msg = "Se destapo la casilla de la fila " + f + " en la columna " + c;
						pistaDada = true;
					}
				}
			}
		}
		return msg;
	}

	/***
	 * Metodo dar del atributo perdio
	 * 
	 * @return boolean el atributo
	 */
	public boolean darPerdio() {
		return perdio;
	}

	/***
	 * Metodo dar del atributo nivel
	 * 
	 * @return int nivel
	 */
	public int darNivel() {
		return nivel;
	}

	/***
	 * Metodo dar del atributo cantidad minas
	 * 
	 * @return int cantidadMinas
	 */
	public int darCantidadMinas() {
		return cantidadMinas;
	}

}