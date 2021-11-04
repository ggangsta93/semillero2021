package semillero.pruebasUnitarias;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.enums.TematicaEnum;
import com.hbt.semillero.exception.excepcionComicsInactivos;

import junit.framework.Assert;

import java.util.ArrayList; // import the ArrayList class

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * <b>Descripción:<b> Clase que determina las pruebas unitarias para la validación de comits
 * <b>Caso de Uso:<b> validar comits
 * @author PEDRO JAVIER ARIAS
 * @version 1.0
 */

public class CreacionComicTest {
	
	/**
	 * Constante que contendra el log de la clase CreacionComicTest
	 */
	private final static Logger log = Logger.getLogger(CreacionComicTest.class);
	
	@BeforeTest
	public void inicializar() {
		BasicConfigurator.configure(); // Inicializa el logger con una configuracion basica
		log.info(":::::::::::::::::::::::::::: INICIAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
	
	/**
	 * Lista que almacenará el total de los comics
	 */	
	private ArrayList<ComicDTO> comics =new ArrayList<ComicDTO>();
	/**
	 * Lista que almacenará los comics activos
	 */	
	private ArrayList<ComicDTO> activos=new ArrayList<ComicDTO>();
	/**
	 * Lista que almacenará los comics inactivos
	 */	
	private ArrayList<ComicDTO> inactivos=new ArrayList<ComicDTO>();
	
	
	/**
	 * Metodo encargado de crear los comics y almacenarlos en las listas
	 */
	@BeforeTest
	public void cargarComics() {
		
		ComicDTO comic1=new ComicDTO("Dragon Ball Yamcha","Planeta Cómic",TematicaEnum.AVENTURAS,"Manga Shonen",144,new BigDecimal(2100),"Dragon Garow Lee", false,LocalDate.of(2017, 1, 13),EstadoEnum.ACTIVO, 200L);
		ComicDTO comic2=new ComicDTO("El planera de los simios","Planeta Bonito",TematicaEnum.BELICO,"Manga Shonen",124,new BigDecimal(4100),"Dragon Garow Lee", false,LocalDate.of(2018, 1, 13),EstadoEnum.INACTIVO, 100L);
		ComicDTO comic3=new ComicDTO("Titanic","Planeta Cómic",TematicaEnum.CIENCIA_FICCION,"Manga Shonen",154,new BigDecimal(6200),"Dragon Garow Lee", false,LocalDate.of(2019, 1, 13),EstadoEnum.ACTIVO, 800L);
		ComicDTO comic4=new ComicDTO("Naruto","Planeta Bonito",TematicaEnum.DEPORTIVO,"Manga Shonen",100,new BigDecimal(3700),"Dragon Garow Lee", false,LocalDate.of(2020, 1, 13),EstadoEnum.INACTIVO, 150L);
		ComicDTO comic5=new ComicDTO("Xmen","Planeta Cómic",TematicaEnum.FANTASTICO,"Manga Shonen",99,new BigDecimal(2800),"Dragon Garow Lee", false,LocalDate.of(2021, 1, 13),EstadoEnum.ACTIVO, 300L);
		ComicDTO comic6=new ComicDTO("Digimon","Planeta Cómic",TematicaEnum.HISTORICO,"Manga Shonen",10,new BigDecimal(4300),"Dragon Garow Lee", false,LocalDate.of(2017, 1, 13),EstadoEnum.INACTIVO, 600L);
		ComicDTO comic7=new ComicDTO("Mario Bross","Uranio comic",TematicaEnum.HISTORICO,"Manga Shonen",112,new BigDecimal(5600),"Dragon Garow Lee", false,LocalDate.of(2012, 1, 13),EstadoEnum.ACTIVO, 200L);
		ComicDTO comic8=new ComicDTO("Liga de la jsuticia","Planeta Cómic",TematicaEnum.AVENTURAS,"Manga Shonen",124,new BigDecimal(2600),"Dragon Garow Lee", false,LocalDate.of(2014, 1, 13),EstadoEnum.INACTIVO, 500L);
		ComicDTO comic9=new ComicDTO("Dragon ball GT","Planeta Cómic",TematicaEnum.HORROR,"Manga Shonen",118,new BigDecimal(1800),"Dragon Garow Lee", false,LocalDate.of(2011, 1, 13),EstadoEnum.INACTIVO, 100L);
		ComicDTO comic10=new ComicDTO("Goku vs Naruto","Cómic3000",TematicaEnum.BELICO,"Manga Shonen",122,new BigDecimal(3700),"Dragon Garow Lee", false,LocalDate.of(2017, 1, 13),EstadoEnum.ACTIVO, 200L);
		
		comics.add(comic1);//ACTIVO
		comics.add(comic2);//INACTIVO
		comics.add(comic3);//ACTIVO
		comics.add(comic4);//INACTIVO
		comics.add(comic5);//ACTIVO
		comics.add(comic6);//INACTIVO
		comics.add(comic7);//ACTIVO
		comics.add(comic8);//INACTIVO
		comics.add(comic9);//INACTIVO
		comics.add(comic10);//ACTIVO
		
		
		activos.add(comic1);
		activos.add(comic3);
		activos.add(comic5);
		activos.add(comic7);
		activos.add(comic10);
		
		inactivos.add(comic2);
		inactivos.add(comic4);
		inactivos.add(comic6);
		inactivos.add(comic8);
		inactivos.add(comic9);
	}
	
	/**
	 * Metodo encargado de devolver una lista de los comics activos
	 */
	private ArrayList<ComicDTO> comicsActivos(){
		ArrayList<ComicDTO> comicsActivos=new ArrayList<ComicDTO>();
		for (ComicDTO comicDTO : comics) {
			if(comicDTO.getEstadoEnum().equals(EstadoEnum.ACTIVO)) {
				comicsActivos.add(comicDTO);
				System.out.println(comicDTO.toString());
			}
		}
	
		return comicsActivos;
	}
	
	
	/**
	 * Metodo encargado de devolver una lista de los comics inactivos
	 */
	private ArrayList<ComicDTO> comicsInactivos(){
		ArrayList<ComicDTO> comicsInactivos=new ArrayList<ComicDTO>();
		for (ComicDTO comicDTO : comics) {
			if(comicDTO.getEstadoEnum().equals(EstadoEnum.INACTIVO)) {
				comicsInactivos.add(comicDTO);
			}
		}
	
		return comicsInactivos;
	}
	
	/**
	 * Metodo encargado de devolver una cedena de nombres separas por comas, recibe como parametro una lista de ComicDTO
	 */
	private String devuleveNombres(ArrayList<ComicDTO> entrada) {
		String cadena="";
		for (ComicDTO comicDTO : entrada) {
			cadena += comicDTO.getNombre()+", ";
		}
		return cadena;
		
	}
	
	@Test
	public void probarComicsActivos() {
		log.info("Inicia ejecucion del metodo probarComicsActivos()");
		
		Assert.assertEquals(activos, comicsActivos());
		
		log.info("Finaliza la ejecucion del metodo probarComicsActivos()");

	}
	
	@Test
	public void probarComicsInactivos() throws excepcionComicsInactivos {
		log.info("Inicia ejecucion del metodo probarComicsInactivos()");				
		
		try {
												
			if(!inactivos.equals(comicsInactivos())) {
				throw new excepcionComicsInactivos(comics.size(),activos.size(),inactivos.size(),true);
			}
		} catch (excepcionComicsInactivos e) {
			log.info(e.getMessage()+" Los comics inactivos son: "+devuleveNombres(inactivos));
			Assert.assertFalse(e.isError());
		}
		
		log.info("Finaliza la ejecucion del metodo probarComicsInactivos()");

	}
	
	
	
	@AfterTest
	public void finalizaPruebasUnitarias() {
		log.info(":::::::::::::::::::::::::::: FINALIZAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
}








