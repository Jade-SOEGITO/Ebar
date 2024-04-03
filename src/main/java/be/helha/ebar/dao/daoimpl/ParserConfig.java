package be.helha.ebar.dao.daoimpl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;


public class ParserConfig {

	public static Persistance lireConfiguration(String fichierConfiguration) throws Exception {
		FileReader fr = new FileReader(fichierConfiguration, StandardCharsets.UTF_8);
		JsonReader reader = new JsonReader(fr);
		Gson gson = new Gson();
		Persistance persistance=gson.fromJson(reader, Persistance.class);
		reader.close();
		fr.close();
		validation(persistance);
		return persistance;
	}

	private static void validation(Persistance persistance) throws Exception {
		if (persistance.getType().equals(Persistance.DB)) {
			if (persistance.getUser().isEmpty()) {
				throw new Exception("Le champ <user> est vide");
			}
			else if (persistance.getPassword().isEmpty()) {
				throw new Exception("Le champ <password> est vide");
			} else if (persistance.getDbName().isEmpty()) {
				throw new Exception("Le champ <url> est vide");
			}
			else {
				Field url = persistance.getClass().getDeclaredField("url");
				url.setAccessible(true);
				url.set(persistance, "jdbc:" + persistance.getUser() + "ql://localhost:" + persistance.getHostName() + "/" + persistance.getDbName());
			}
		} else if (!persistance.getType().equals(Persistance.MOCK)) {
			throw new Exception("Le type de connexion est vide ou invalide");
		}
	}
}