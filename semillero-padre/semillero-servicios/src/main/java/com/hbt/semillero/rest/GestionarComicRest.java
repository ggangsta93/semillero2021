/**
 * GestionarComicRest.java
 */
package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.enums.EstadoEnum;


@Path("/gestionarComic")
public class GestionarComicRest {
	
	@EJB
	private IGestionarComicLocal gestionarComicLocal;
	
	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);	
	}//http://localhost:8085/semillero-servicios/rest/gestionarComic/consultarNombrePrecioComic?idComic=3

	@GET
	@Path("/consultarComicTamanioNombre")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombreDTO(@QueryParam("lengthComic") Short lengthComic) {
		return this.gestionarComicLocal.consultarComicTamanioNombre(lengthComic);	
	}//http://localhost:8085/semillero-servicios/rest/gestionarComic/consultarComicTamanioNombre	
	
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO crearComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResultado = new ComicDTO();
		try {
			comicDTOResultado = this.gestionarComicLocal.crearComic(comicDTO);
		} catch (Exception e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("Se ha presentado un error técnico, causa: "+e.getMessage());
		}
		
		return comicDTOResultado;
	}//http://localhost:8085/semillero-servicios/rest/gestionarComic/crearComic
	
	@GET
	@Path("/eliminarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO eliminarComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.eliminarComic(idComic);	
	}//http://localhost:8085/semillero-servicios/rest/gestionarComic/eliminarComic?idComic=4
	
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComics() {
		return this.gestionarComicLocal.consultarComics();	
	}//http://localhost:8085/semillero-servicios/rest/gestionarComic/consultarComics
	
	/**
	 * Método encargado de actualizar el nombre de un comic existente
	 * <b>Caso de Uso: </b> semillero HBT
	 * @author Javier Arias apedro@unicauca.edu.co
	 * @param idComic y nombre del comic que se actulizará
	 * @return comicDTO retorna el comic junto con el mensaje 
	 */
	@GET
	@Path("/actualizarNombreComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO actualizarNombreComic(@QueryParam("idComic") Long idComic, @QueryParam("nombre") String nombre) {
		return this.gestionarComicLocal.actualizarNombreComic(idComic, nombre);	
	}//http://localhost:8085/semillero-servicios/rest/gestionarComic/actualizarNombreComic?idComic=25&nombre=GOKU
		
	/**
	 * Método encargado de actualizar el estado de un comic existente
	 * <b>Caso de Uso: </b> semillero HBT
	 * @author Javier Arias apedro@unicauca.edu.co
	 * @param idComic y estado del comic que se actulizará
	 * @return comicDTO retorna el comic junto con el mensaje 
	 */
	@GET
	@Path("/actualizarEstadoComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO actualizarEstadoComic(@QueryParam("idComic") Long idComic, @QueryParam("estado") EstadoEnum estado) {
		return this.gestionarComicLocal.actualizarEstadoComic(idComic, estado);	
	}//http://localhost:8085/semillero-servicios/rest/gestionarComic/actualizarEstadoComic?idComic=27&estado=INACTIVO
	
	
	/**
	 * Método encargado de actualizar un comic existente
	 * <b>Caso de Uso: </b> semillero HBT
	 * @author Javier Arias apedro@unicauca.edu.co
	 * @param comicDTO DTO con la infomación del comic que se actualizará
	 * @return comicDTO retorna el comic junto con el mensaje 
	 */
	@POST
	@Path("/actualizarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO actualizarComic(ComicDTO comicDTO) {
		return this.gestionarComicLocal.actualizarComic(comicDTO);	
	}//http://localhost:8085/semillero-servicios/rest/gestionarComic/actualizarComic
		

}
