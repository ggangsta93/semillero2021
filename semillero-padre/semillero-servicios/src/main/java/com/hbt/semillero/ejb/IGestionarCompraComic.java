/**
 * IGestionarComicLocal.java
 */
package com.hbt.semillero.ejb;

import javax.ejb.Local;
import java.util.List;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.enums.EstadoEnum;



@Local
public interface IGestionarCompraComic {
	/**
	 * 
	 * Método encargado de comprar un comic,
	 * <b>Caso de Uso</b>
	 * @author Javier Arias apedro@unicauca.edu.co
	 * 
	 * @param idComic, cantidad
	 * @return ComicDTO
	 */
	public ComicDTO comprarComic(Long idComic, Long cantidad);

}



