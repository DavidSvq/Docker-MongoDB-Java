package com.david.bbddCine.bbddCine.modelo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Pelicula {
	private final ObjectId _id;
	private String titulo;
	private final ObjectId idDirector;
	private ArrayList<ObjectId> elencoActores;
	private int anio;
	private String sinopsis;
	private int duracion;
	private final ObjectId idGenero;
	private final ObjectId idPais;
	
	//Constructor para recibir objetos desde Mongo
	public Pelicula(ObjectId _id, String titulo, ObjectId idDirector, ArrayList<ObjectId> elencoActores, int anio,
			String sinopsis, int duracion, ObjectId idGenero, ObjectId idPais) {
		super();
		this._id = _id;
		this.titulo = titulo;
		this.idDirector = idDirector;
		this.elencoActores = elencoActores;
		this.anio = anio;
		this.sinopsis = sinopsis;
		this.duracion = duracion;
		this.idGenero = idGenero;
		this.idPais = idPais;
	}

	//Constructor para crear el _id desde Java
	public Pelicula(String titulo, ObjectId idDirector, ArrayList<ObjectId> elencoActores, int anio, String sinopsis,
			int duracion, ObjectId idGenero, ObjectId idPais) {
		this._id = new ObjectId();
		this.titulo = titulo;
		this.idDirector = idDirector;
		this.elencoActores = elencoActores;
		this.anio = anio;
		this.sinopsis = sinopsis;
		this.duracion = duracion;
		this.idGenero = idGenero;
		this.idPais = idPais;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<ObjectId> getElencoActores() {
		return elencoActores;
	}

	public void setElencoActores(ArrayList<ObjectId> elencoActores) {
		this.elencoActores = elencoActores;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public ObjectId get_id() {
		return _id;
	}

	public ObjectId getIdDirector() {
		return idDirector;
	}

	public ObjectId getIdGenero() {
		return idGenero;
	}

	public ObjectId getIdPais() {
		return idPais;
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", anio=" + anio + ", sinopsis=" + sinopsis + ", duracion=" + duracion
				+ "]";
	}
	
	//Metodo para convertir un objeto en formato bson
	public static Pelicula convertirDesdeDocumento(Document doc) {
		ObjectId idAux = doc.getObjectId("_id");
		String tituloAux = doc.getString("titulo");
		ObjectId idAuxDirector = doc.getObjectId("director");
		List<ObjectId> listaAux = doc.getList("elenco_actores", ObjectId.class);
		ArrayList<ObjectId> elencoAux = new ArrayList<>(listaAux);
		int anioAux = doc.getInteger("anio");
		String sinopsisAux = doc.getString("sinopsis");
		int duracionAux = doc.getInteger("duracion");
		ObjectId idAuxGenero = doc.getObjectId("genero");
		ObjectId idAuxPais = doc.getObjectId("pais");
		
		Pelicula peliculaAux = new Pelicula (idAux, tituloAux, idAuxDirector, elencoAux, anioAux, sinopsisAux, duracionAux, idAuxGenero, idAuxPais);
		
		return peliculaAux;
	}
	
	//Método para convertir un documento bson en objeto Java
	public Document convertirEnDocumento() {
		
		Document doc = new Document();
		doc.append("_id", _id);
		doc.append("titulo", titulo);
		doc.append("director", idDirector);
		doc.append("elenco_actores", elencoActores);
		doc.append("anio", anio);
		doc.append("sinopsis", sinopsis);
		doc.append("duracion", duracion);
		doc.append("genero", idGenero);
		doc.append("pais", idPais);
		
		return doc;
	}
	
	
}
