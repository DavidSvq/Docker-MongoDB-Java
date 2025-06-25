package com.david.bbddCine.bbddCine.conexionBbdd;

import com.mongodb.MongoException;
import com.mongodb.MongoSecurityException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
	//Atributos para la URI y el nombre de la bbdd
	private static final String URI = "mongodb://david:12345@localhost:27018/?authSource=admin&authMechanism=SCRAM-SHA-256";
	private static final String DATABASE_NAME = "cine_bd";
	
	//Atributos para establecer la conexión y recibir el documento
	private MongoClient conexionMongo;
	private MongoDatabase baseDatosMongo;
	
	//Método para conectar
	public boolean conectar() {
		try{
			conexionMongo = MongoClients.create(URI);
			baseDatosMongo = conexionMongo.getDatabase(DATABASE_NAME);
			System.out.println("Conectado a la base de datos: " + baseDatosMongo.getName());
			return true;
		}
		catch (MongoTimeoutException e) {
            System.err.println("Error: No se pudo conectar al servidor MongoDB.");
        } catch (MongoSecurityException e1) {
            System.err.println("Error: Fallo de autenticación. Revisa usuario/contraseña.");
        } catch (MongoException e2) {
            System.err.println("Error general de MongoDB: " + e2.getMessage());
        }
		return false;
	}
	
	//Método para desconectar
	public boolean desconectar() {
		 if (conexionMongo != null) {
	            conexionMongo.close();
	            System.out.println("Conexión cerrada.");
	            return true;
		 }
		 else {
			 System.err.println();
			 return false;
		 }
	}
	
	//Get bbdd
	public MongoDatabase getDatabase() {
		return baseDatosMongo;
	}
}
