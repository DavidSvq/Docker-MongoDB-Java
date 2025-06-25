package com.david.bbddCine.bbddCine.modelo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Genero {
	
	//Enum de los géneros disponibles
	public enum GeneroCine{
		ACCION, COMEDIA, DRAMA, TERROR, CIENCIA_FICCION, FANTASIA
	}
	
	private final ObjectId _id;
	private GeneroCine nombre;
	private ArrayList <String> subgeneros;
	
	//Constructor para recibir objetos desde Mongo
	public Genero(ObjectId _id, GeneroCine nombre, ArrayList<String> subgeneros) {
		this._id = _id;
		this.nombre = nombre;
		this.subgeneros = subgeneros;
	}

	//Constructor para crear el _id desde Java
	public Genero(GeneroCine nombre, ArrayList<String> subgeneros) {
		this._id = new ObjectId ();
		this.nombre = nombre;
		this.subgeneros = subgeneros;
	}

	public GeneroCine getNombre() {
		return nombre;
	}

	public void setNombre(GeneroCine nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getSubgeneros() {
		return subgeneros;
	}

	public void setSubgeneros(ArrayList<String> subgeneros) {
		this.subgeneros = subgeneros;
	}

	public ObjectId get_id() {
		return _id;
	}

	@Override
	public String toString() {
		return "Genero [nombre=" + nombre + ", subgeneros=" + subgeneros + "]";
	}
	
	//Metodo para convertir un objeto en formato bson
	public static Genero convertirDesdeDocumento(Document doc) {
		
		ObjectId idAux = doc.getObjectId("_id");
		String tipoTexto = doc.getString("nombre");
		GeneroCine nombreAux = GeneroCine.valueOf(tipoTexto);
		List<String> listaAux = doc.getList("subgeneros", String.class);
		if(listaAux == null) {
			listaAux = List.of();
		}
		ArrayList<String> listaSubgeneros = new ArrayList<>(listaAux);
		
		Genero generoAux = new Genero(idAux, nombreAux, listaSubgeneros);
		
		return generoAux;
	}
	
	//Método para convertir un documento bson en objeto Java
	public Document convertirEnDocumento() {
		
		Document doc = new Document();
		doc.append("_id", _id);
		doc.append("nombre",nombre);
		doc.append("subgeneros", subgeneros);
		
		return doc;
	}
}
