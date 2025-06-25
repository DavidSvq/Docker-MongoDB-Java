package com.david.bbddCine.bbddCine.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.push;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.david.bbddCine.bbddCine.modelo.Pelicula;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class PeliculaDao {
	//Atributo para recibir y manipular el documento
	private final MongoCollection<Document> coleccion;

	//Constructor para la conexión de la colección anterior
	public PeliculaDao(MongoDatabase db) {
		this.coleccion = db.getCollection("pelicula");
	}
	
	//Métodos CRUD
	//Insertar nuevo documento
	public boolean insertar (Pelicula peli) {
		InsertOneResult resultado = coleccion.insertOne(peli.convertirEnDocumento());
		return resultado.wasAcknowledged();
	}
	
	//Buscar actor por Id
	public Pelicula buscarPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return Pelicula.convertirDesdeDocumento(doc);
    }
	
	//Obtener todos los documentos sobre las peliculas
	public List<Pelicula> obtenerTodosLasPeliculas (){
		List<Pelicula> peliculas = new ArrayList<>();
		for (Document doc : coleccion.find()) {
			peliculas.add(Pelicula.convertirDesdeDocumento(doc));
		}
		
		return peliculas;
	}
	
	//Modificar el campo idioma de una película
	public boolean modificarIdiomaPelicula(ObjectId id, String idioma) {
		UpdateResult resultado = coleccion.updateOne(
		        eq("_id", id),   
		        set("idioma", idioma)	
		         
		    );
		return resultado.getModifiedCount() > 0;
	}
	
	//Eliminar un documento de la colección
	public boolean eliminarPelicula(ObjectId id) {
		DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
		
		return resultado.getDeletedCount() > 0;
	}
	
	//Agregar actor al elenco de la película
	public boolean agregarActorAElenco(ObjectId peliculaId, ObjectId actorId) {
	    UpdateResult result = coleccion.updateOne(
	        eq("_id", peliculaId),
	        push("elenco_actores", actorId)
	    );
	    return result.getModifiedCount() > 0;
	}
}
