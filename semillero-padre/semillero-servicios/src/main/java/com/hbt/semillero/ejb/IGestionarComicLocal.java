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
public interface IGestionarComicLocal {
	/**
	 * 
	 * Metodo encargado de 
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 * @param comicDTO
	 * @return
	 */
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic);
	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombre(Short lengthComic);
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception;
	public ComicDTO actualizarNombreComic(Long idComic, String nombre);
	public ComicDTO actualizarEstadoComic(Long idComic, EstadoEnum estado);
	public ComicDTO eliminarComic(Long idComic);
	public List<ComicDTO> consultarComics();

}
