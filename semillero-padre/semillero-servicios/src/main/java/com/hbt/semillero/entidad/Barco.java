package com.hbt.semillero.entidad;

import com.hbt.semillero.enums.TipoVehiculoEnum;
import com.hbt.semillero.interfaces.AccionesVehiculoInterface;

/**
 * 
 * <b>Descripción:<b> Clase que determina la representacion de las caracteriscas en comun de un vehiculo
 * <b>Caso de Uso:<b> SEMILLERO 2021
 * @author DIEGO FERNANDO
 * @version 1.0 
 */
public class Barco extends Vehiculo implements AccionesVehiculoInterface {

	private String nombreCapitan;
	private String puertoLLegada;
	
	/**
	 * Metodo encargado de retornar el valor del atributo nombreCapitan
	 * @return El nombreCapitan asociado a la clase
	 */
	public String getNombreCapitan() {
		return nombreCapitan;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo nombreCapitan
	 * @param nombreCapitan El nuevo nombreCapitan a modificar.
	 */
	public void setNombreCapitan(String nombreCapitan) {
		this.nombreCapitan = nombreCapitan;
	}
	/**
	 * Metodo encargado de retornar el valor del atributo puertoLLegada
	 * @return El puertoLLegada asociado a la clase
	 */
	public String getPuertoLLegada() {
		return puertoLLegada;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo puertoLLegada
	 * @param puertoLLegada El nuevo puertoLLegada a modificar.
	 */
	public void setPuertoLLegada(String puertoLLegada) {
		this.puertoLLegada = puertoLLegada;
	}
	@Override
	public int obtenerVelocidadMaxima() {
		// TODO Auto-generated method stub
		return 200;
	}
	@Override
	public Long obternerPesoMaximoCarga() {
		// TODO Auto-generated method stub
		return 30L;
	}
	@Override
	public boolean determinarTipoVehiculo(TipoVehiculoEnum tipoVehiculoEmun) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
