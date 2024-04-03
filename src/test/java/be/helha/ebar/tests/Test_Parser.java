package be.helha.ebar.tests;

import be.helha.ebar.dao.daoimpl.ParserConfig;
import be.helha.ebar.dao.daoimpl.Persistance;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Tests unitaires de ParserConfig
 */
public class Test_Parser {
	private Persistance persistance;
	private Persistance persistance99;
	/*@Test
	@Order(1)
	public void testMock1() {
		try {
			persistance = ParserConfig.lireConfiguration("src/test/resources/configMock1.json");
			assertEquals("MOCK", persistance.getType());
			String toStringDao = persistance.getDaoImpl().toString();
			assertTrue(toStringDao.contains("be.iesca.daoimpl.BiereDaoMockImpl"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	public void testMock2() {
		try {
			persistance = ParserConfig.lireConfiguration("src/test/resources/configMock2.json");
			fail("Il aurait du générer une exception car il manque le champ <dao>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	@Order(3)
	public void testMock3() {
		try {
			persistance = ParserConfig.lireConfiguration("src/test/resources/configMock3.json");
			fail("Il aurait du générer une exception car il manque le champ type");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	@Order(4)
	public void testMock4() {
		try {
			persistance = ParserConfig.lireConfiguration("src/test/resources/configMock4.json");
			fail("Il aurait du générer une exception car le type de persistance est incorrect");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}*/

	@Test
	@Order(1)
	public void testPostgres1() {
		try {
			persistance = ParserConfig.lireConfiguration("src/test/resources/configPostgres1.json");
			assertEquals("DB", persistance.getType());
			assertEquals("jdbc:postgresql://localhost:5432/ebar", persistance.getUrl());
			assertEquals("postgres", persistance.getUser());
			assertEquals("1234", persistance.getPassword());
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	public void testPostgres2() {
		try {
			persistance = ParserConfig.lireConfiguration("src/test/resources/configPostgres2.json");
			fail("Il aurait du générer une exception car il manque le champ <password>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	@Order(3)
	public void testPostgres3() {
		try {
			persistance = ParserConfig.lireConfiguration("src/test/resources/configPostgres3.json");
			fail("Il aurait du générer une exception car il manque le champ <url>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	@Order(4)
	public void testPostgres4() {
		try {
			persistance = ParserConfig.lireConfiguration("src/test/resources/configPostgres4.json");
			fail("Il aurait du générer une exception car il manque le champ <user>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

}