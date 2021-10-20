package semillero.pruebasUnitarias;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

/**
 * 
 * <b>Descripción:<b> Clase que determina las pruebas unitarias para el CU1050 gestionar operaciones
 * aritmeticas
 * <b>Caso de Uso:<b> CU1050 gestionar operaciones
 * @author DIEGO FERNANDO
 * @version 1.0
 */
public class OperacionesAritmeticasTest {
	
	/**
	 * Constante que contendra el log de la clase AritmeticaTest
	 */
	private final static Logger log = Logger.getLogger(OperacionesAritmeticasTest.class);
	
	@BeforeTest
	public void inicializar() {
		BasicConfigurator.configure(); // Inicializa el logger con una configuracion basica
		log.info(":::::::::::::::::::::::::::: INICIAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
	
	/**
	 * 
	 * Metodo encargado de de probar que el resultado de la suma de dos numeros
	 * sea 450 
	 * <b>Caso de Uso</b>CU1050 gestionar operaciones
	 * @author DIEGO FERNANDO
	 *
	 */
//@Test(enabled = true) prueba inactiva
	@Test 
	public void validarResultadoSumaExitoso() {
		log.info("Inicia ejecucion del metodo validarResultadoSumaExitoso()");
		
		int numero1 = 300;
		int numero2 = 150;
		int resultado = 450;
		
//		Assert.assertFalse(condition);
//		Assert.assertTrue(condition);
//		Assert.assertEquals(expected, actual);
//		Assert.assertNotNull(object);
//		Assert.assertNull(object);
		Assert.assertEquals(resultado, numero1 + numero2);
		
		log.info("Finaliza la ejecucion del metodo validarResultadoSumaExitoso()");
	}
	
	/**
	 * 
	 * Metodo encargado de 
	 * <b>Caso de Uso</b>
	 * @author DIEGO FERNANDO
	 *
	 */
	@Test
	public void validarResultadoSumaFallido() {
		log.info("Inicia ejecucion del metodo validarResultadoSumaFallido()");
		
		int numero1 = 300;
		int numero2 = 150;
		int resultado = 455;
		
		try {
			//Se espera un resultado true
			Assert.assertTrue(resultado != (numero1 + numero2));
			
			if(resultado != (numero1 + numero2)) {
				log.info("Se ha generado un error funcional probando el test validarResultadoSumaFallido()");
				throw new Exception("La suma ha fallado en el calculo");
			}
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "La suma ha fallado en el calculo");
		}
		
		log.info("Finaliza la ejecucion del metodo validarResultadoSumaFallido()");
	}
	
	@AfterTest
	public void finalizaPruebasUnitarias() {
		log.info(":::::::::::::::::::::::::::: FINALIZAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
}
