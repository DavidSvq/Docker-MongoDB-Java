package com.david.bbddCine.bbddCine.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.david.bbddCine.bbddCine.modelo.Actor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class ActorDao {
	//Atributo para recibir y manipular el documento
	private final MongoCollection<Document> coleccion;
	
	//Constructor para la conexión de la colección anterior
	public ActorDao(MongoDatabase db) {
		this.coleccion = db.getCollection("actor");
	}
	
	//Métodos CRUD
	//Insertar nuevo documento
	public boolean insertarActor (Actor actor) {
		InsertOneResult resultado = coleccion.insertOne(actor.convertirEnDocumento());
		return resultado.wasAcknowledged();
	}
	
	//Buscar actor por Id
	public Actor buscarPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return Actor.convertirDesdeDocumento(doc);
    }
	
	//Obtener todos los documentos sobre los actores
	public List<Actor> obtenerTodosLosActores (){
		List<Actor> actores = new ArrayList<>();
		for (Document doc : coleccion.find()) {
			actores.add(Actor.convertirDesdeDocumento(doc));
		}
		
		return actores;
	}
	
	//Modificar el campo nombre del actor
	public boolean modificarNombreActor(ObjectId id,String nombre) {
		UpdateResult resultado = coleccion.updateOne(
		        eq("_id", id),
		        set("nombre", nombre)
		    );
		return resultado.getModifiedCount() > 0;
	}
	
	//Eliminar un documento de la colección
	public boolean eliminarActor(ObjectId id) {
		DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
		
		return resultado.getDeletedCount() > 0;
	}
}
