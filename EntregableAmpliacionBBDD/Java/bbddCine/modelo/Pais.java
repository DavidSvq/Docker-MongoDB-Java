package com.david.bbddCine.bbddCine.modelo;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Pais {
	private final ObjectId _id;
	private String nombre;
	private String idioma;
	
	//Constructor para recibir objetos desde Mongo
	public Pais(ObjectId id, String nombre, String idioma) {
		this._id = id; 
		this.nombre = nombre;
		this.idioma = idioma;
	}
	
	//Constructor para crear el _id desde Java
	public Pais(String nombre, String idioma) {
		this._id = new ObjectId(); 
		this.nombre = nombre;
		this.idioma = idioma;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public ObjectId get_id() {
		return _id;
	}
	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", idioma=" + idioma + "]";
	}
	
	//Metodo para convertir un objeto en formato bson
	public static Pais convertirDesdeDocumento(Document doc) {
		
		ObjectId idAux = doc.getObjectId("_id");
		String nombreAux = doc.getString("nombre");
		String idiomaAux = doc.getString("idioma");
		
		Pais paisAux = new Pais (idAux, nombreAux, idiomaAux);
		
		return paisAux;
	}
	
	//Método para convertir un documento bson en objeto Java
	public Document convertirEnDocumento() {
		
		Document doc = new Document();
		doc.append("_id", _id);
		doc.append("nombre",nombre);
		doc.append("idioma", idioma);
		
		return doc;
	}
}
