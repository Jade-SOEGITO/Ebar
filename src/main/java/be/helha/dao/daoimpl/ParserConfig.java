package be.helha.ebar.dao.daoimpl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
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

		// vérifier la persistance et lancer une exception en cas de problème
		// TOUS LES PARAMETRES SONT A VERIFIER
		//

	}