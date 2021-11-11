/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.io.Console;
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
import com.hbt.semillero.exception.excepcionMaximoCaracteres;

@Stateless // Está más orientado al manejo de servicios
@TransactionManagement(TransactionManagementType.CONTAINER) // El container lo hace de forma automatica, el bean se
															// utiliza para aplicaciones de tipo bancario "manual" para
															// getionar el commit y rollback manualmente
public class GestionarComicBean implements IGestionarComicLocal {

	private final static Logger log = Logger.getLogger(GestionarComicBean.class);

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo encargado de consultar nombre y precio de un comic con el idComic
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) {
		String consulta = "SELECT new com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO(c.nombre, c.precio)  "
				+ " FROM Comic c WHERE c.id = :idComic";
		ConsultaNombrePrecioComicDTO consultaNombrePrecioDTO = new ConsultaNombrePrecioComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("idComic", idComic);
			consultaNombrePrecioDTO = (ConsultaNombrePrecioComicDTO) consultaNativa.getSingleResult();
			consultaNombrePrecioDTO.setExitoso(true);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");
		} catch (NoResultException e) {
			consultaNombrePrecioDTO.setExitoso(false);
			consultaNombrePrecioDTO.setMensajeEjecucion("No existe comic con ese id");
		} catch (Exception e) {
			consultaNombrePrecioDTO.setExitoso(false);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}

		return consultaNombrePrecioDTO;
	}

	/**
	 * Metodo encargado de devolver los nombres de los comics que cumplen y no
	 * cumplen con la restricción de longitud solicitada
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombre(Short lengthComic) {
		String consulta = "SELECT c.nombre FROM Comic c";

		ConsultaComicTamanioNombreDTO consultaComicTamanioNombreDTO = new ConsultaComicTamanioNombreDTO();
		ArrayList<String> superan = new ArrayList<String>();
		ArrayList<String> noSuperan = new ArrayList<String>();

		try {
			Query consultaNativa = em.createQuery(consulta);
			List<String> comics = consultaNativa.getResultList();

			if (lengthComic > 100)
				throw new excepcionMaximoCaracteres("La longitud permitida es de 100 caracteres.");

			for (String comic : comics) {
				if (comic.length() >= lengthComic)
					superan.add(comic);
				else
					noSuperan.add(comic);
			}
			consultaComicTamanioNombreDTO.setComicsSuperanTamanio(superan);
			consultaComicTamanioNombreDTO.setComicsNoSuperanTamanio(noSuperan);
			consultaComicTamanioNombreDTO.setExitoso(true);
			consultaComicTamanioNombreDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");
		} catch (excepcionMaximoCaracteres e) {
			consultaComicTamanioNombreDTO.setExitoso(false);
			consultaComicTamanioNombreDTO.setMensajeEjecucion(e.getMessage());
		} catch (Exception e) {
			consultaComicTamanioNombreDTO.setExitoso(false);
			consultaComicTamanioNombreDTO
					.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}

		return consultaComicTamanioNombreDTO;
	}

	/**
	 * Metodo encargado de crear un comic
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception {

		if (comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		ComicDTO comicDTOResultado = null;
		Comic comic = convertirComicDTOToComic(comicDTO);
		em.persist(comic);
		comicDTOResultado = convertirComicToComicDTO(comic);
		comicDTOResultado.setExitoso(true);
		comicDTOResultado.setMensajeEjecucion("El comic se ha creado exitosamente.");
		return comicDTOResultado;
	}

	/**
	 * Metodo encargado de actualizar el nombre de un comic con el idComic
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public ComicDTO actualizarNombreComic(Long idComic, String nombre) {
		String actualizarNombreComic = "UPDATE Comic c SET c.nombre = :nombre WHERE c.id =:idComic";
		Query queryActualizarComic = em.createQuery(actualizarNombreComic);
		queryActualizarComic.setParameter("nombre", nombre);
		queryActualizarComic.setParameter("idComic", idComic);
		queryActualizarComic.executeUpdate();

		ComicDTO comicDTOResultado = new ComicDTO();
		try {
			comicDTOResultado = convertirComicToComicDTO(consultarComic(idComic));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comicDTOResultado;
	}

	/**
	 * Metodo encargado de actualizar el estado de un comic con el idComic
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public ComicDTO actualizarEstadoComic(Long idComic, EstadoEnum estado) {
		String actualizarNombreComic = "UPDATE Comic c SET c.estadoEnum = :estado WHERE c.id = :idComic";
		Query queryActualizarComic = em.createQuery(actualizarNombreComic);
		queryActualizarComic.setParameter("estado", estado);
		queryActualizarComic.setParameter("idComic", idComic);
		queryActualizarComic.executeUpdate();

		ComicDTO comicDTOResultado = new ComicDTO();
		try {
			comicDTOResultado = convertirComicToComicDTO(consultarComic(idComic));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comicDTOResultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public ComicDTO actualizarComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResultado = new ComicDTO();
		try {	
			//condicional  que valida que
			if(consultarComic(comicDTO.getId()) != null){
				em.merge(convertirComicDTOToComic(comicDTO));
				comicDTOResultado = comicDTO;
			}
			comicDTOResultado.setExitoso(true);
			comicDTOResultado.setMensajeEjecucion("Se actualizo con exito el comic.");
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
	 * Metodo encargado de eliminar un comic con el idComic y retorna el comic
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public ComicDTO eliminarComic(Long idComic) {
		ComicDTO comicDTOResultado = new ComicDTO();

		String eliminarComic = " DELETE FROM Comic WHERE id = :idComic";
		try {
			Comic comic = consultarComic(idComic);
			comicDTOResultado = convertirComicToComicDTO(comic);
			Query queryEliminar = em.createQuery(eliminarComic);
			queryEliminar.setParameter("idComic", idComic);
			queryEliminar.executeUpdate();
			comicDTOResultado.setExitoso(true);
			comicDTOResultado.setMensajeEjecucion("Se eliminó exitosamente el comic");
		} catch (NoResultException e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("No existe comic con ese id");
		} catch (Exception e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}
		return comicDTOResultado;
	}

	/**
	 * Metodo encargado de devolver todos los comics
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public List<ComicDTO> consultarComics() {
		String findAllComic = " SELECT cm FROM Comic cm ";
		Query queryFindAllComic = em.createQuery(findAllComic);
		List<ComicDTO> listaComics = queryFindAllComic.getResultList();
	
		return listaComics;
	}

	/**
	 * Metodo encargado de devolver un comic con el id El método eliminarComic lo
	 * utiliza para devolverlo antes eliminarlo
	 * 
	 * @author Pedro Javier Arias
	 * @param comic
	 * @return
	 * @throws Exception
	 */
	private Comic consultarComic(Long idComic) throws Exception {
		Comic comic = null;
		try {
			String findComic = " SELECT cm FROM Comic cm WHERE cm.id = :idComic";
			Query queryFindComic = em.createQuery(findComic);
			queryFindComic.setParameter("idComic", idComic);
			comic = (Comic) queryFindComic.getSingleResult();
		} catch (NoResultException e) {
			throw new NoResultException();// Excepción para id inexistente.
		} catch (Exception e) {
			throw new Exception();
		}
		return comic;
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
