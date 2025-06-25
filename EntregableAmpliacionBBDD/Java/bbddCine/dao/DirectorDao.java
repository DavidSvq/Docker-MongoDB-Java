package com.david.bbddCine.bbddCine.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.david.bbddCine.bbddCine.modelo.Director;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class DirectorDao {
	//Atributo para recibir y manipular el documento
	private final MongoCollection<Document> coleccion;
	
	//Constructor para la conexión de la colección anterior
	public DirectorDao(MongoDatabase db) {
		this.coleccion = db.getCollection("director");
	}
	
	//Métodos CRUD
	//Insertar nuevo documento
	public boolean insertar (Director director) {
		InsertOneResult resultado = coleccion.insertOne(director.convertirEnDocumento());
		return resultado.wasAcknowledged();
	}
	
	//Buscar actor por Id
	public Director buscarPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return Director.convertirDesdeDocumento(doc);
    }
	
	//Obtener todos los documentos sobre los directores
	public List<Director> obtenerTodosLosDirectores (){
		List<Director> directores = new ArrayList<>();
		for (Document doc : coleccion.find()) {
			directores.add(Director.convertirDesdeDocumento(doc));
		}
		
		return directores;
	}
	
	//Modificar el campo nombre del director
	public boolean modificarNombreDirector(ObjectId id,String nombre) {
		UpdateResult resultado = coleccion.updateOne(
		        eq("_id", id),
		        set("nombre", nombre)
		    );
		return resultado.getModifiedCount() > 0;
	}
	
	//Eliminar un documento de la colección
	public boolean eliminarDirector(ObjectId id) {
		DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
		
		return resultado.getDeletedCount() > 0;
	}
}
