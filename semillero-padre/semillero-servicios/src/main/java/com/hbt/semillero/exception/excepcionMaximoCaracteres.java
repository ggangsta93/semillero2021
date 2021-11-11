/**
 * excepcionComics.java
 */
package com.hbt.semillero.exception;

/**
 * <b>Descripción:<b> Clase que determina una excepción personalizada para el método consultarComicTamanioNombre
 * <b>Caso de Uso:<b> Lectura de comic con restricción de longitud
 * @author Pedro Javier Arias
 * @version 
 */
public class excepcionMaximoCaracteres extends Exception {
	
	/**
	 * Atributo que determina  
	 */
	private static final long serialVersionUID = 1L;

	public excepcionMaximoCaracteres (String mensaje) {
		super(mensaje);		
	}
	

}
