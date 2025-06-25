package com.david.bbddCine.bbddCine.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.david.bbddCine.bbddCine.modelo.Genero;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class GeneroDao {
	//Atributo para recibir y manipular el documento
	private final MongoCollection<Document> coleccion;

	//Constructor para la conexión de la colección anterior
	public GeneroDao(MongoDatabase db) {
		this.coleccion = db.getCollection("genero");
	}
	
	//Métodos CRUD
	//Insertar nuevo documento
	public boolean insertar (Genero genero) {
		InsertOneResult resultado = coleccion.insertOne(genero.convertirEnDocumento());
		return resultado.wasAcknowledged();
	}
	
	//Buscar actor por Id
	public Genero buscarPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return Genero.convertirDesdeDocumento(doc);
    }
	
	//Obtener todos los documentos sobre los generos
	public List<Genero> obtenerTodosLosGeneros (){
		List<Genero> generos = new ArrayList<>();
		for (Document doc : coleccion.find()) {
			generos.add(Genero.convertirDesdeDocumento(doc));
		}
		
		return generos;
	}
	
	//Añadir un subgenero el campo subgeneros
	public boolean añadirSubgenero(ObjectId id, List<Genero> generos, String nuevoSubgenero) {
		
		List<String> nombresSubgeneros = new ArrayList<>();
		for (Genero gen : generos) {
			if(gen.get_id().equals(id)) {
				nombresSubgeneros = gen.getSubgeneros();
			}
		}
		nombresSubgeneros.add(nuevoSubgenero);
		
		UpdateResult resultado = coleccion.updateOne(
		        eq("_id", id),   
		        set("subgeneros", nombresSubgeneros)
		         
		    );
		return resultado.getModifiedCount() > 0;
	}
	
	//Eliminar un documento de la colección
	public boolean eliminarGenero(ObjectId id) {
		DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
		
		return resultado.getDeletedCount() > 0;
	}
	
}
