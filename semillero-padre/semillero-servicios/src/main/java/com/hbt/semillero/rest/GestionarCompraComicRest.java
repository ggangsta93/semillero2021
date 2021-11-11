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
import com.hbt.semillero.ejb.IGestionarCompraComic;
import com.hbt.semillero.enums.EstadoEnum;


@Path("/gestionarComprarComic")
public class GestionarCompraComicRest {
	
	@EJB
	private IGestionarCompraComic gestionarComprarComic;
	
		/**
	 * Método encargado de 
	 * <b>Caso de Uso: </b> semillero HBT
	 * @author Javier Arias apedro@unicauca.edu.co
	 * @param idComic 
	 * @return comicDTO retorna el comic junto con el mensaje 
	 */
	@GET
	@Path("/comprarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO comprarComic(@QueryParam("idComic") Long idComic, @QueryParam("cantidad") Long cantidad) {
		return this.gestionarComprarComic.comprarComic(idComic, cantidad);	
	}//http://localhost:8085/semillero-servicios/rest/gestionarComprarComic/comprarComic
		
	
		

}
