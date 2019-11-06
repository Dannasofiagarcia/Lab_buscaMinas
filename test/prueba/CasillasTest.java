package prueba;

import modelo.*;
import interfaz.*;

import org.junit.Assert;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CasillasTest {
	
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

	void testModificarValorPrincipiante() {
		setupEscenario1();
		
		int valorEsperado = 4;
		buscaminasJuego.darCasillas()[0][0].modificarValor(4);
		int valorResultado = buscaminasJuego.darCasillas()[0][0].darValor();
		
		assertEquals (valorEsperado, valorResultado);
	}
	
	@Test
	
	void testEsMinaPrincipiante() {
		setupEscenario1();
		
		boolean esperado = true;
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		boolean resultado = buscaminasJuego.darCasillas()[0][0].esMina();
		
		assertEquals(esperado, resultado);
		
	}
	
	@Test
	
	void testMostrarValorCasillaPrincipiante() {
		setupEscenario1();
		
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		buscaminasJuego.darCasillas()[2][2].modificarTipo(Casilla.MINA);
		buscaminasJuego.abrirCasilla(2,2);
		buscaminasJuego.darCasillas()[3][3].modificarTipo(Casilla.LIBRE);
		buscaminasJuego.darCasillas()[1][1].modificarTipo(Casilla.LIBRE);
		buscaminasJuego.darCasillas()[1][1].modificarValor(1);
		buscaminasJuego.abrirCasilla(1,1);
		
		String valorEsperadoCasillaSinSeleccionar = "-";
		String valorResultadoCasillaSinSeleccionarM = buscaminasJuego.darCasillas()[0][0].mostrarValorCasilla();
		String valorResultadoCasillaSinSeleccionarL = buscaminasJuego.darCasillas()[3][3].mostrarValorCasilla();
		String valorEsperadoCasillaSeleccionadaMina = "*";
		String valorEsperadoCasillaSeleccionadaValor = "1";
		String valorResultadoCasillaSeleccionadaMina = buscaminasJuego.darCasillas()[2][2].mostrarValorCasilla();
		String valorResultadoCasillaSeleccionadaValor = buscaminasJuego.darCasillas()[1][1].mostrarValorCasilla();
		
		
		assertEquals (valorEsperadoCasillaSinSeleccionar, valorResultadoCasillaSinSeleccionarM);
		assertEquals (valorEsperadoCasillaSinSeleccionar, valorResultadoCasillaSinSeleccionarL);
		assertEquals(valorEsperadoCasillaSeleccionadaMina, valorResultadoCasillaSeleccionadaMina);
		assertEquals (valorEsperadoCasillaSeleccionadaValor, valorResultadoCasillaSeleccionadaValor);		
	}
	
	@Test
	
	void testDestaparPrincipiante() {
		setupEscenario1();
		boolean seleccionadaEsperado = true;
		buscaminasJuego.abrirCasilla(1,1);
		boolean seleccionadaResultado = buscaminasJuego.darCasillas()[1][1].darSeleccionada();
		
		assertEquals(seleccionadaEsperado, seleccionadaResultado);	
	}
	
	@Test
	
	void testDarSeleccionadaPrincipiante() {
		setupEscenario1();
		boolean seleccionadaEsperado = true;
		buscaminasJuego.abrirCasilla(1,1);
		boolean seleccionadaResultado = buscaminasJuego.darCasillas()[1][1].darSeleccionada();
		
		assertEquals(seleccionadaEsperado, seleccionadaResultado);	
	}
	
	@Test
	
	void testDarValor() {
		setupEscenario1();
		buscaminasJuego.darCasillas()[1][1].modificarValor(1);
		int valorEsperado = 1;
		int valorResultado = buscaminasJuego.darCasillas()[1][1].darValor();
		
		assertEquals (valorEsperado, valorResultado);
	}
	
	//Pruebas nivel intermedio
	
	@Test

	void testModificarValorIntermedio() {
		setupEscenario2();
		
		int valorEsperado = 4;
		buscaminasJuego.darCasillas()[0][0].modificarValor(4);
		int valorResultado = buscaminasJuego.darCasillas()[0][0].darValor();
		
		assertEquals (valorEsperado, valorResultado);
	}
	
	@Test
	
	void testEsMinaIntermedio() {
		setupEscenario2();
		
		boolean esperado = true;
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		boolean resultado = buscaminasJuego.darCasillas()[0][0].esMina();
		
		assertEquals(esperado, resultado);
		
	}
	
	@Test
	
	void testMostrarValorCasillaIntermedio() {
		setupEscenario2();
		
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		buscaminasJuego.darCasillas()[2][2].modificarTipo(Casilla.MINA);
		buscaminasJuego.abrirCasilla(2,2);
		buscaminasJuego.darCasillas()[3][3].modificarTipo(Casilla.LIBRE);
		buscaminasJuego.darCasillas()[1][1].modificarTipo(Casilla.LIBRE);
		buscaminasJuego.darCasillas()[1][1].modificarValor(1);
		buscaminasJuego.abrirCasilla(1,1);
		
		String valorEsperadoCasillaSinSeleccionar = "-";
		String valorResultadoCasillaSinSeleccionarM = buscaminasJuego.darCasillas()[0][0].mostrarValorCasilla();
		String valorResultadoCasillaSinSeleccionarL = buscaminasJuego.darCasillas()[3][3].mostrarValorCasilla();
		String valorEsperadoCasillaSeleccionadaMina = "*";
		String valorEsperadoCasillaSeleccionadaValor = "1";
		String valorResultadoCasillaSeleccionadaMina = buscaminasJuego.darCasillas()[2][2].mostrarValorCasilla();
		String valorResultadoCasillaSeleccionadaValor = buscaminasJuego.darCasillas()[1][1].mostrarValorCasilla();
		
		
		assertEquals (valorEsperadoCasillaSinSeleccionar, valorResultadoCasillaSinSeleccionarM);
		assertEquals (valorEsperadoCasillaSinSeleccionar, valorResultadoCasillaSinSeleccionarL);
		assertEquals(valorEsperadoCasillaSeleccionadaMina, valorResultadoCasillaSeleccionadaMina);
		assertEquals (valorEsperadoCasillaSeleccionadaValor, valorResultadoCasillaSeleccionadaValor);		
	}
	
	@Test
	
	void testDestaparIntermedio() {
		setupEscenario2();
		boolean seleccionadaEsperado = true;
		buscaminasJuego.abrirCasilla(1,1);
		boolean seleccionadaResultado = buscaminasJuego.darCasillas()[1][1].darSeleccionada();
		
		assertEquals(seleccionadaEsperado, seleccionadaResultado);	
	}
	
	@Test
	
	void testDarSeleccionadaIntermedio() {
		setupEscenario2();
		boolean seleccionadaEsperado = true;
		buscaminasJuego.abrirCasilla(1,1);
		boolean seleccionadaResultado = buscaminasJuego.darCasillas()[1][1].darSeleccionada();
		
		assertEquals(seleccionadaEsperado, seleccionadaResultado);	
	}
	
	@Test
	
	void testDarValorIntermedio() {
		setupEscenario2();
		buscaminasJuego.darCasillas()[1][1].modificarValor(1);
		int valorEsperado = 1;
		int valorResultado = buscaminasJuego.darCasillas()[1][1].darValor();
		
		assertEquals (valorEsperado, valorResultado);
	}
	
	
	
	
	//Pruebas nivel experto
	
	@Test

	void testModificarValorExperto() {
		setupEscenario3();
		
		int valorEsperado = 4;
		buscaminasJuego.darCasillas()[0][0].modificarValor(4);
		int valorResultado = buscaminasJuego.darCasillas()[0][0].darValor();
		
		assertEquals (valorEsperado, valorResultado);
	}
	
	@Test
	
	void testEsMinaExperto() {
		setupEscenario3();
		
		boolean esperado = true;
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		boolean resultado = buscaminasJuego.darCasillas()[0][0].esMina();
		
		assertEquals(esperado, resultado);
		
	}
	
	@Test
	
	void testMostrarValorCasillaExperto() {
		setupEscenario3();
		
		buscaminasJuego.darCasillas()[0][0].modificarTipo(Casilla.MINA);
		buscaminasJuego.darCasillas()[2][2].modificarTipo(Casilla.MINA);
		buscaminasJuego.abrirCasilla(2,2);
		buscaminasJuego.darCasillas()[3][3].modificarTipo(Casilla.LIBRE);
		buscaminasJuego.darCasillas()[1][1].modificarTipo(Casilla.LIBRE);
		buscaminasJuego.darCasillas()[1][1].modificarValor(1);
		buscaminasJuego.abrirCasilla(1,1);
		
		String valorEsperadoCasillaSinSeleccionar = "-";
		String valorResultadoCasillaSinSeleccionarM = buscaminasJuego.darCasillas()[0][0].mostrarValorCasilla();
		String valorResultadoCasillaSinSeleccionarL = buscaminasJuego.darCasillas()[3][3].mostrarValorCasilla();
		String valorEsperadoCasillaSeleccionadaMina = "*";
		String valorEsperadoCasillaSeleccionadaValor = "1";
		String valorResultadoCasillaSeleccionadaMina = buscaminasJuego.darCasillas()[2][2].mostrarValorCasilla();
		String valorResultadoCasillaSeleccionadaValor = buscaminasJuego.darCasillas()[1][1].mostrarValorCasilla();
		
		
		assertEquals (valorEsperadoCasillaSinSeleccionar, valorResultadoCasillaSinSeleccionarM);
		assertEquals (valorEsperadoCasillaSinSeleccionar, valorResultadoCasillaSinSeleccionarL);
		assertEquals(valorEsperadoCasillaSeleccionadaMina, valorResultadoCasillaSeleccionadaMina);
		assertEquals (valorEsperadoCasillaSeleccionadaValor, valorResultadoCasillaSeleccionadaValor);		
	}
	
	@Test
	
	void testDestaparExperto() {
		setupEscenario3();
		boolean seleccionadaEsperado = true;
		buscaminasJuego.abrirCasilla(1,1);
		boolean seleccionadaResultado = buscaminasJuego.darCasillas()[1][1].darSeleccionada();
		
		assertEquals(seleccionadaEsperado, seleccionadaResultado);	
	}
	
	@Test
	
	void testDarSeleccionadaExperto() {
		setupEscenario3();
		boolean seleccionadaEsperado = true;
		buscaminasJuego.abrirCasilla(1,1);
		boolean seleccionadaResultado = buscaminasJuego.darCasillas()[1][1].darSeleccionada();
		
		assertEquals(seleccionadaEsperado, seleccionadaResultado);	
	}
	
	@Test
	
	void testDarValorExperto() {
		setupEscenario3();
		buscaminasJuego.darCasillas()[1][1].modificarValor(1);
		int valorEsperado = 1;
		int valorResultado = buscaminasJuego.darCasillas()[1][1].darValor();
		
		assertEquals (valorEsperado, valorResultado);
	}
	
}//Cierra la clase
	
