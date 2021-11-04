package com.hbt.semillero.dto;

import java.util.ArrayList;

public class ConsultaComicTamanioNombreDTO extends ResultadoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> si=new ArrayList<String>();
	private ArrayList<String> no=new ArrayList<String>();
	
	/**
	 * Constructor de la clase.
	 * @param si
	 * @param no
	 */
	public ConsultaComicTamanioNombreDTO() {
		super();
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo si
	 * @return El si asociado a la clase
	 */
	public ArrayList<String> getSi() {
		return si;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo si
	 * @param si El nuevo si a modificar.
	 */
	public void setSi(ArrayList<String> si) {
		this.si = si;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo no
	 * @return El no asociado a la clase
	 */
	public ArrayList<String> getNo() {
		return no;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo no
	 * @param no El nuevo no a modificar.
	 */
	public void setNo(ArrayList<String> no) {
		this.no = no;
	}	
	
	

}
