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
public class excepcionComicsInactivos extends Exception {
	
	/**
	 * Atributo que determina si es una excepción valida
	 */
	private boolean error;
	
	/**
	 * Método constructor que permite personlizar el mensaje de la excepción, recibe como parametros: la lista completa de comics, la lista de comics activos, la lista de comics inactivos, y false
	 */
	public excepcionComicsInactivos (int total, int activos, int inactivos, boolean error ) {
		super("Se ha detectado que de "+total+" comics se encontraron que "+activos+" se encuentran activos y "+inactivos+" inactivos.");
		this.setError(error);		
	}
	

	/**
	 * Metodo encargado de retornar el valor del atributo error
	 * @return El error asociado a la clase
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo error
	 * @param error El nuevo error a modificar.
	 */
	public void setError(boolean error) {
		this.error = error;
	}

}
