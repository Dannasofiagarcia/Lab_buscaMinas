package interfaz;

public class ExceptionNumeroIncorrecto extends Exception{
	
	ExceptionNumeroIncorrecto (int numero){
		super("La opcion " + numero + " no es valida. Intente de nuevo");
	}

}
