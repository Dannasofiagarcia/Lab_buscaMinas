package prueba;

import modelo.*;
import interfaz.*;

import org.junit.Assert;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BuscaminasTest {
	
	Buscaminas buscaminasJuego;
	Casilla[][] casillaJuego;
	
	private void setupEscenario1() {
		buscaminasJuego = new Buscaminas (Buscaminas.PRINCIPIANTE);
//		casillaJuego = new Casilla [Buscaminas.COLUMNAS_PRINCIPIANTE][Buscaminas.FILAS_PRINCIPIANTE];
	}
	
	private void setupEscenario2() {
		buscaminasJuego = new Buscaminas (Buscaminas.INTERMEDIO);
//		casillaJuego = new Casilla [Buscaminas.COLUMNAS_INTERMEDIO][Buscaminas.FILAS_INTERMEDIO];
	}
	
	private void setupEscenario3() {
		buscaminasJuego = new Buscaminas (Buscaminas.EXPERTO);
//		casillaJuego = new Casilla [Buscaminas.COLUMNAS_EXPERTO][Buscaminas.FILAS_EXPERTO];
	}
	
	//Pruebas nivel principiante

	@Test
	void testInicializarPartidaPrincipiante() {
		setupEscenario1();
		int nivelEsperado = Buscaminas.PRINCIPIANTE;
		int cantidadMinasEsperadas = Buscaminas.CANTIDAD_MINAS_PRINCIPANTE;
		int cantidadColumnasEsperadas = Buscaminas.COLUMNAS_PRINCIPIANTE;
		int cantidadFilasEsperadas = Buscaminas.FILAS_PRINCIPIANTE;
		
		int nivelResultado = (buscaminasJuego.darNivel());
		int cantidadMinasResultado = (buscaminasJuego.darCantidadMinas());
		int cantidadColumnasResultado = buscaminasJuego.darCasillas()[0].length;
		int cantidadFilasResultado = buscaminasJuego.darCasillas().length;
		
		
		assertEquals (nivelEsperado, nivelResultado);
		assertEquals (cantidadMinasEsperadas, cantidadMinasResultado);
		assertEquals (cantidadColumnasEsperadas, cantidadColumnasResultado);
		assertEquals (cantidadFilasEsperadas, cantidadFilasResultado);
		
	}
	
	@Test
	void testInicializarCasillasLibresPrincipiante() {
		setupEscenario1();
		int casillasLibresEsperadas = 54;
		int casillasLibresResultado = 0;
		int contador = 0;
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].darTipo() == Casilla.LIBRE) {
					contador++;
				}
			}
		}
		
		casillasLibresResultado = contador;
		
		assertEquals (casillasLibresEsperadas, casillasLibresResultado);	
	}
	
	@Test
	
	void testCantidadMinasAlrededorPrincipiante() {
		setupEscenario1();
		boolean correctoEsperado = true;
		boolean correctoResultado = true;
		
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].esMina() != true) {
					if(buscaminasJuego.darCasillas()[f][c].darValor() > 8 && buscaminasJuego.darCasillas()[f][c].darValor() < 0) {
						correctoResultado = false;
					}
				}
			}
		}
		
		assertEquals (correctoEsperado, correctoResultado);
	}
	
	@Test
	
	void testGenerarMinasPrincipiante() {
		setupEscenario1();
		int cantidadMinasEsperadas = Buscaminas.CANTIDAD_MINAS_PRINCIPANTE;
		int cantidadMinasResultado = 0;
		int contador = 0;
		
		
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].esMina() == true) {
					contador++;
				}
			}
		}
		cantidadMinasResultado = contador;
		assertEquals (cantidadMinasEsperadas, cantidadMinasResultado);
	}
	
	@Test
	
	void testResolverPrincipiante() {
		setupEscenario1();
		boolean perdioEsperado = true;
		buscaminasJuego.resolver();
		boolean perdioResultado = buscaminasJuego.darPerdio();
		
		assertEquals (perdioEsperado, perdioResultado);
	}
	
	@Test
	
	void testDarCasillaPrincipiante() {
		
		setupEscenario1();
		buscaminasJuego.darCasillas()[0][0].modificarValor(5);
		int valorEsperado = 5;
		int valorResultado = buscaminasJuego.darCasillas()[0][0].darValor();
		
		assertEquals (valorEsperado, valorResultado);
		
	}
	
	@Test
	
	void testGanoPrincipiante() {
		setupEscenario1();
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		boolean esperado = false;
		buscaminasJuego.abrirCasilla(0, 0);
		boolean resultado = buscaminasJuego.gano();
		
		assertEquals (esperado, resultado);
	}
	
	@Test
	
	void testDarPerdioPrincipiante() {
		setupEscenario1();
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		buscaminasJuego.abrirCasilla(0,0);
		
		boolean esperado = true;
		boolean resultado = buscaminasJuego.darPerdio();
		
		assertEquals (esperado, resultado);
	}
	
	@Test
	
	void testDarNivelPrincipiante() {
		setupEscenario1();
		int nivelEsperado = Buscaminas.PRINCIPIANTE;
		int nivelResultado = buscaminasJuego.darNivel();
		
		assertEquals(nivelEsperado, nivelResultado);
	}
	
	@Test
	
	void testDarCantidadMinasPrincipiante() {
		setupEscenario1();
		int cantidadMinasEsperado = Buscaminas.CANTIDAD_MINAS_PRINCIPANTE;
		int cantidadMinasResultado = buscaminasJuego.darCantidadMinas();
		
		assertEquals(cantidadMinasEsperado, cantidadMinasResultado);
	}
	
	
	
	
	//Pruebas nivel intermedio
	
	@Test
	void testInicializarPartidaIntermedio() {
		setupEscenario2();
		int nivelEsperado = Buscaminas.INTERMEDIO;
		int cantidadMinasEsperadas = Buscaminas.CANTIDAD_MINAS_INTERMEDIO;
		int cantidadColumnasEsperadas = Buscaminas.COLUMNAS_INTERMEDIO;
		int cantidadFilasEsperadas = Buscaminas.FILAS_INTERMEDIO;
		
		int nivelResultado = (buscaminasJuego.darNivel());
		int cantidadMinasResultado = (buscaminasJuego.darCantidadMinas());
		int cantidadColumnasResultado = buscaminasJuego.darCasillas()[0].length;
		int cantidadFilasResultado = buscaminasJuego.darCasillas().length;
		
		
		assertEquals (nivelEsperado, nivelResultado);
		assertEquals (cantidadMinasEsperadas, cantidadMinasResultado);
		assertEquals (cantidadColumnasEsperadas, cantidadColumnasResultado);
		assertEquals (cantidadFilasEsperadas, cantidadFilasResultado);
		
	}
	
	@Test
	void testInicializarCasillasLibresIntermedio() {
		setupEscenario2();
		int casillasLibresEsperadas = 216;
		int casillasLibresResultado = 0;
		int contador = 0;
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].darTipo() == Casilla.LIBRE) {
					contador++;
				}
			}
		}
		
		casillasLibresResultado = contador;
		
		assertEquals (casillasLibresEsperadas, casillasLibresResultado);	
	}
	
	@Test
	
	void testCantidadMinasAlrededorIntermedio() {
		setupEscenario2();
		boolean correctoEsperado = true;
		boolean correctoResultado = true;
		
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].esMina() != true) {
					if(buscaminasJuego.darCasillas()[f][c].darValor() > 8 && buscaminasJuego.darCasillas()[f][c].darValor() < 0) {
						correctoResultado = false;
					}
				}
			}
		}
		
		assertEquals (correctoEsperado, correctoResultado);
	}
	
	@Test
	
	void testGenerarMinasIntermedio() {
		setupEscenario2();
		int cantidadMinasEsperadas = Buscaminas.CANTIDAD_MINAS_INTERMEDIO;
		int cantidadMinasResultado = 0;
		int contador = 0;
		
		
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].esMina() == true) {
					contador++;
				}
			}
		}
		cantidadMinasResultado = contador;
		assertEquals (cantidadMinasEsperadas, cantidadMinasResultado);
	}
	
	@Test
	
	void testResolverIntermedio() {
		setupEscenario2();
		boolean perdioEsperado = true;
		buscaminasJuego.resolver();
		boolean perdioResultado = buscaminasJuego.darPerdio();
		
		assertEquals (perdioEsperado, perdioResultado);
	}
	
	@Test
	
	void testDarCasillaIntermedio() {
		
		setupEscenario2();
		buscaminasJuego.darCasillas()[0][0].modificarValor(5);
		int valorEsperado = 5;
		int valorResultado = buscaminasJuego.darCasillas()[0][0].darValor();
		
		assertEquals (valorEsperado, valorResultado);
		
	}
	
	@Test
	
	void testGanoIntermedio() {
		setupEscenario2();
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		boolean esperado = false;
		buscaminasJuego.abrirCasilla(0, 0);
		boolean resultado = buscaminasJuego.gano();
		
		assertEquals (esperado, resultado);
	}
	
	@Test
	
	void testDarPerdioIntermedio() {
		setupEscenario2();
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		buscaminasJuego.abrirCasilla(0,0);
		
		boolean esperado = true;
		boolean resultado = buscaminasJuego.darPerdio();
		
		assertEquals (esperado, resultado);
	}
	
	@Test
	
	void testDarNivelIntermedio() {
		setupEscenario2();
		int nivelEsperado = Buscaminas.INTERMEDIO;
		int nivelResultado = buscaminasJuego.darNivel();
		
		assertEquals(nivelEsperado, nivelResultado);
	}
	
	@Test
	
	void testDarCantidadMinasIntermedio() {
		setupEscenario2();
		int cantidadMinasEsperado = Buscaminas.CANTIDAD_MINAS_INTERMEDIO;
		int cantidadMinasResultado = buscaminasJuego.darCantidadMinas();
		
		assertEquals(cantidadMinasEsperado, cantidadMinasResultado);
	}
	
	
	
	
	//Pruebas nivel experto
	
	@Test
	void testInicializarPartidaExperto() {
		setupEscenario3();
		int nivelEsperado = Buscaminas.EXPERTO;
		int cantidadMinasEsperadas = Buscaminas.CANTIDAD_MINAS_EXPERTO;
		int cantidadColumnasEsperadas = Buscaminas.COLUMNAS_EXPERTO;
		int cantidadFilasEsperadas = Buscaminas.FILAS_EXPERTO;
		
		int nivelResultado = (buscaminasJuego.darNivel());
		int cantidadMinasResultado = (buscaminasJuego.darCantidadMinas());
		int cantidadColumnasResultado = buscaminasJuego.darCasillas()[0].length;
		int cantidadFilasResultado = buscaminasJuego.darCasillas().length;
		
		
		assertEquals (nivelEsperado, nivelResultado);
		assertEquals (cantidadMinasEsperadas, cantidadMinasResultado);
		assertEquals (cantidadColumnasEsperadas, cantidadColumnasResultado);
		assertEquals (cantidadFilasEsperadas, cantidadFilasResultado);
		
	}
	
	@Test
	void testInicializarCasillasLibresExperto() {
		setupEscenario3();
		int casillasLibresEsperadas = 381;
		int casillasLibresResultado = 0;
		int contador = 0;
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].darTipo() == Casilla.LIBRE) {
					contador++;
				}
			}
		}
		
		casillasLibresResultado = contador;
		
		assertEquals (casillasLibresEsperadas, casillasLibresResultado);	
	}
	
	@Test
	
	void testCantidadMinasAlrededorExperto() {
		setupEscenario3();
		boolean correctoEsperado = true;
		boolean correctoResultado = true;
		
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].esMina() != true) {
					if(buscaminasJuego.darCasillas()[f][c].darValor() > 8 && buscaminasJuego.darCasillas()[f][c].darValor() < 0) {
						correctoResultado = false;
					}
				}
			}
		}
		
		assertEquals (correctoEsperado, correctoResultado);
	}
	
	@Test
	
	void testGenerarMinasExperto() {
		setupEscenario3();
		int cantidadMinasEsperadas = Buscaminas.CANTIDAD_MINAS_EXPERTO;
		int cantidadMinasResultado = 0;
		int contador = 0;
		
		
		for(int f = 0; f < buscaminasJuego.darCasillas().length; f++) {
			for(int c = 0; c < buscaminasJuego.darCasillas()[0].length; c++) {
				if(buscaminasJuego.darCasillas()[f][c].esMina() == true) {
					contador++;
				}
			}
		}
		cantidadMinasResultado = contador;
		assertEquals (cantidadMinasEsperadas, cantidadMinasResultado);
	}
	
	@Test
	
	void testResolverExperto() {
		setupEscenario3();
		boolean perdioEsperado = true;
		buscaminasJuego.resolver();
		boolean perdioResultado = buscaminasJuego.darPerdio();
		
		assertEquals (perdioEsperado, perdioResultado);
	}
	
	@Test
	
	void testDarCasillaExperto() {
		
		setupEscenario3();
		buscaminasJuego.darCasillas()[0][0].modificarValor(5);
		int valorEsperado = 5;
		int valorResultado = buscaminasJuego.darCasillas()[0][0].darValor();
		
		assertEquals (valorEsperado, valorResultado);
		
	}
	
	@Test
	
	void testGanoExperto() {
		setupEscenario3();
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		boolean esperado = false;
		buscaminasJuego.abrirCasilla(0, 0);
		boolean resultado = buscaminasJuego.gano();
		
		assertEquals (esperado, resultado);
	}
	
	@Test
	
	void testDarPerdioExperto() {
		setupEscenario3();
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		buscaminasJuego.abrirCasilla(0,0);
		
		boolean esperado = true;
		boolean resultado = buscaminasJuego.darPerdio();
		
		assertEquals (esperado, resultado);
	}
	
	@Test
	
	void testDarNivelExperto() {
		setupEscenario3();
		int nivelEsperado = Buscaminas.EXPERTO;
		int nivelResultado = buscaminasJuego.darNivel();
		
		assertEquals(nivelEsperado, nivelResultado);
	}
	
	@Test
	
	void testDarCantidadMinasExperto() {
		setupEscenario3();
		int cantidadMinasEsperado = Buscaminas.CANTIDAD_MINAS_EXPERTO;
		int cantidadMinasResultado = buscaminasJuego.darCantidadMinas();
		
		assertEquals(cantidadMinasEsperado, cantidadMinasResultado);
	}
	

}//Cierra la clase
	
