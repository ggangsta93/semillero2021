/**
 * excepcionComics.java
 */
package com.hbt.semillero.exception;

import java.util.ArrayList;

import com.hbt.semillero.dto.ComicDTO;

/**
 * <b>Descripción:<b> Clase que determina una excepción personalizada para el método ComprarComicMayor
 * <b>Caso de Uso:<b> Validación de Comics Inactivos
 * @author Pedro Javier Arias
 * @version 
 */
public class excepcionComprarComicMayor extends Exception {
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Método constructor que tiene el mensaje de la excepcion cuando el comic tiene un stock menor al que se quiere comprar
	 */
	public excepcionComprarComicMayor (String nombreComic, long cantidad) {
		super("La cantidad existente del comic "+nombreComic+" es: "+cantidad+", y es nenor a la ingresada");	
	}
	



}
