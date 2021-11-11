/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.exception.excepcionComprarComicInactivo;
import com.hbt.semillero.exception.excepcionComprarComicMayor;
import com.hbt.semillero.exception.excepcionMaximoCaracteres;

@Stateless // Está más orientado al manejo de servicios
@TransactionManagement(TransactionManagementType.CONTAINER) // El container lo hace de forma automatica, el bean se
															// utiliza para aplicaciones de tipo bancario "manual" para
															// getionar el commit y rollback manualmente
public class GestionarCompraComicBean implements IGestionarCompraComic {

	private final static Logger log = Logger.getLogger(GestionarCompraComicBean.class);

	@PersistenceContext
	private EntityManager em;

	
	/**
	 * Metodo encargado de comprar un comic, recibe el id y la cantidad a comprar
	 * 
	 * @author Pedro Javier Arias
	 * @param idComic y cantidad
	 * @return ComicDTO
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public ComicDTO comprarComic(Long idComic, Long cantidad){
		ComicDTO comicDTOResultado = new ComicDTO();
		String findComic = " SELECT cm FROM Comic cm WHERE cm.id = :idComic";
		boolean compraExitosa = false;
		
		try {	
			Query queryFindComic = em.createQuery(findComic);
			queryFindComic.setParameter("idComic", idComic);
			Comic comic = (Comic) queryFindComic.getSingleResult();
			comicDTOResultado = convertirComicToComicDTO(comic);	
			
			
			/*
			 *Condicional que validad si el comic que se quiere comprar se encuentra en estado INACTIVO lanza una excepción
			 */
			if(comicDTOResultado.getEstadoEnum().equals(EstadoEnum.INACTIVO) ){
				throw new excepcionComprarComicInactivo();
			}
			
			/*
			 *Condicional que verifica si hay cantidad disponible para venta
			 */
			
			GestionarComicBean gestionarComicBean=new GestionarComicBean();
			
			/*
			 *Condicional que verifica si la cantidad que se quiere comprar es mayor al stock
			 */
			if(comicDTOResultado.getCantidad()< cantidad) {
				throw new excepcionComprarComicMayor(comicDTOResultado.getNombre(), comicDTOResultado.getCantidad());
			}	
			
			/*
			 *Condicional que verifica si la cantidad que se quiere comprar es igual al stock
			 */
			if(comicDTOResultado.getCantidad()==cantidad) {
				compraExitosa = actualizarComic(idComic, cantidad, EstadoEnum.INACTIVO);
			}			

			/*
			 *Condicional que verifica si la cantidad que se quiere comprar es menor al stock
			 */
			if(comicDTOResultado.getCantidad()>cantidad) {
				compraExitosa = actualizarComic(idComic, cantidad, EstadoEnum.ACTIVO);
			}			
			
			/*
			 *Se realiza de nuevo la consulta para que retorne el comic actualizado y así ser enviado.
			 * */
			queryFindComic = em.createQuery(findComic);
			queryFindComic.setParameter("idComic", idComic);
			comic = (Comic) queryFindComic.getSingleResult();
			comicDTOResultado = convertirComicToComicDTO(comic);				
			
			comicDTOResultado.setExitoso(true);
			comicDTOResultado.setMensajeEjecucion("La compra del comic "+comicDTOResultado.getNombre()+" fue exitosa.");
		} catch (excepcionComprarComicMayor e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion(e.getLocalizedMessage());			
		} catch (excepcionComprarComicInactivo e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion(e.getLocalizedMessage());			
		} catch (NoResultException e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("No existe comic con ese id.");
		} catch (Exception e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic" + e.getMessage());
		}
		return comicDTOResultado;
	}
	
	
	/**
	 * Metodo encargado de actualizar la fecha, la cantidad de un comic y su estado
	 * 
	 * @author Pedro Javier Arias
	 * @param Long idComic, Long cantidad, EstadoEnum estado
	 * @return
	 */
	public Boolean actualizarComic(Long idComic, Long cantidad, EstadoEnum estado) {
		
		/* Tuve problemas para cuadrar lo de la fecha, ya casi estaba!*/
		String fechaVenta = LocalDate.now().toString();		
		log.info("LA FECHA ES: "+fechaVenta);
		
		String actualizarNombreComic = "UPDATE Comic c SET c.estadoEnum = :estado, c.cantidad = :cantidad WHERE c.id = :idComic";
		Query queryActualizarComic = em.createQuery(actualizarNombreComic);
		queryActualizarComic.setParameter("estado", estado);
		queryActualizarComic.setParameter("cantidad", cantidad);
		queryActualizarComic.setParameter("idComic", idComic);
		queryActualizarComic.executeUpdate();		
		return true;
	}

	
	
	
	
	
	
	
	
	
	

	
	/**
	 * Metodo encargado de convertir un comicDTO a comic
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		comic.setId(comicDTO.getId());
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}

	/**
	 * Metodo encargado de convertir un comic a comicDTO
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		comicDTO.setId(comic.getId());
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}


	


}
