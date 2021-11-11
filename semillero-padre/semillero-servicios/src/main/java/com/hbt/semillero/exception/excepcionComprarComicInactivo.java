/**
 * excepcionComics.java
 */
package com.hbt.semillero.exception;

import java.util.ArrayList;

import com.hbt.semillero.dto.ComicDTO;

/**
 * <b>Descripción:<b> Clase que determina una excepción personalizada para el método probarComitsInactivos
 * <b>Caso de Uso:<b> Validación de Comics Inactivos
 * @author Pedro Javier Arias
 * @version 
 */
public class excepcionComprarComicInactivo extends Exception {
	
	/**
	 * Atributo que determina  
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método constructor que tiene el mensaje de la excepcion cuando el comic es INACTIVO
	 */
	public excepcionComprarComicInactivo () {
		super("El comic seleccionado no cuenta con stock en bodega.");	
	}
	



}
