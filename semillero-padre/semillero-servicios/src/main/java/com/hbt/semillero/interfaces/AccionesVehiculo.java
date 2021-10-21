package com.hbt.semillero.interfaces;

import com.hbt.semillero.enums.TipoVehiculoEnum;

public abstract class AccionesVehiculo {

	public abstract int obtenerVelocidadMaxima();
	
	public abstract Long obternerPesoMaximoCarga();
	
	public abstract boolean determinarTipoVehiculo(TipoVehiculoEnum tipoVehiculoEmun) throws Exception;
	
	public void acelerar() {
		System.out.println("El vehiculo ha iniciado acelerar");
	}
}
