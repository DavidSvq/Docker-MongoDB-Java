package com.david.bbddCine.bbddCine.modelo;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Actor {
	private final ObjectId _id;
	private String nombre;
	private final ObjectId idPais;
	
	//Constructor para recibir objetos desde Mongo
	public Actor(ObjectId _id, String nombre, ObjectId idPais) {
		this._id = _id;
		this.nombre = nombre;
		this.idPais = idPais;
	}
	
	//Constructor para crear el _id desde Java
	public Actor(String nombre, ObjectId idPais) {
		this._id = new ObjectId();
		this.nombre = nombre;
		this.idPais = idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ObjectId get_id() {
		return _id;
	}

	public ObjectId getIdPais() {
		return idPais;
	}

	@Override
	public String toString() {
		return "Actor [nombre=" + nombre + "]";
	}
	
	//Metodo para convertir un objeto en formato bson
	public static Actor convertirDesdeDocumento(Document doc) {
		
		ObjectId idAuxActor =doc.getObjectId("_id");
		String nombreAux = doc.getString("nombre");
		ObjectId idAuxPais = doc.getObjectId("pais");
		
		Actor actorAux = new Actor(idAuxActor, nombreAux, idAuxPais);
		
		return actorAux;
	}
	
	
	//Método para convertir un documento bson en objeto Java
	public Document convertirEnDocumento() {
		
		Document doc = new Document();
		doc.append("_id", _id);
		doc.append("nombre",nombre);
		doc.append("pais", idPais);
		
		return doc;
	}
	
}
