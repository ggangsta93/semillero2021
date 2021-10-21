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
public class Bicicleta extends Vehiculo implements AccionesVehiculoInterface {

	private String tipoBicicleta;
	private boolean utilizaChalecoReflector;
	
	/**
	 * Metodo encargado de retornar el valor del atributo tipoBicicleta
	 * @return El tipoBicicleta asociado a la clase
	 */
	public String getTipoBicicleta() {
		return tipoBicicleta;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo tipoBicicleta
	 * @param tipoBicicleta El nuevo tipoBicicleta a modificar.
	 */
	public void setTipoBicicleta(String tipoBicicleta) {
		this.tipoBicicleta = tipoBicicleta;
	}
	/**
	 * Metodo encargado de retornar el valor del atributo utilizaChalecoReflector
	 * @return El utilizaChalecoReflector asociado a la clase
	 */
	public boolean isUtilizaChalecoReflector() {
		return utilizaChalecoReflector;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo utilizaChaleco
	 * Reflector
	 * @param utilizaChalecoReflector El nuevo utilizaChalecoReflector a modificar.
	 */
	public void setUtilizaChalecoReflector(boolean utilizaChalecoReflector) {
		this.utilizaChalecoReflector = utilizaChalecoReflector;
	}
	
	@Override
	public int obtenerVelocidadMaxima() {
		// TODO Auto-generated method stub
		return 20;
	}
	@Override
	public Long obternerPesoMaximoCarga() {
		// TODO Auto-generated method stub
		return 100L;
	}
	@Override
	public boolean determinarTipoVehiculo(TipoVehiculoEnum tipoVehiculoEmun) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
