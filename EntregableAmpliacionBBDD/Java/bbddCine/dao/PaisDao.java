package com.david.bbddCine.bbddCine.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.david.bbddCine.bbddCine.modelo.Pais;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.combine;


public class PaisDao {
	//Atributo para recibir y manipular el documento
	private final MongoCollection<Document> coleccion;

	//Constructor para la conexión de la colección anterior
	public PaisDao(MongoDatabase db) {
		this.coleccion = db.getCollection("pais");
	}
	
	//Métodos CRUD
	//Insertar nuevo documento
	public boolean insertar (Pais pais) {
		InsertOneResult resultado = coleccion.insertOne(pais.convertirEnDocumento());
		return resultado.wasAcknowledged();
	}
	
	//Buscar actor por Id
	public Pais buscarPorId(ObjectId id) {
        Document doc = coleccion.find(eq("_id", id)).first();
        if (doc == null) return null;
        return Pais.convertirDesdeDocumento(doc);
    }
	
	//Obtener todos los documentos sobre los paises
	public List<Pais> obtenerTodosLosPaises (){
		List<Pais> paises = new ArrayList<>();
		for (Document doc : coleccion.find()) {
			paises.add(Pais.convertirDesdeDocumento(doc));
		}
		
		return paises;
	}
	
	//Modificar los campo nombre e idioma del pais
	public boolean modificarDatosPais(ObjectId id,String nombre,  String idioma) {
		UpdateResult resultado = coleccion.updateOne(
		        eq("_id", id),   
		        combine(
		        		set("nombre", nombre),
		        		set("idioma", idioma)	
		        )
		         
		    );
		return resultado.getModifiedCount() > 0;
	}
	
	//Eliminar un documento de la colección
	public boolean eliminarPais(ObjectId id) {
		DeleteResult resultado = coleccion.deleteOne(eq("_id", id));
		
		return resultado.getDeletedCount() > 0;
	}
	
}
