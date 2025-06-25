package com.david.bbddCine.bbddCine;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.david.bbddCine.bbddCine.conexionBbdd.ConexionMongo;
import com.david.bbddCine.bbddCine.dao.ActorDao;
import com.david.bbddCine.bbddCine.dao.DirectorDao;
import com.david.bbddCine.bbddCine.dao.GeneroDao;
import com.david.bbddCine.bbddCine.dao.PaisDao;
import com.david.bbddCine.bbddCine.dao.PeliculaDao;
import com.david.bbddCine.bbddCine.modelo.Actor;
import com.david.bbddCine.bbddCine.modelo.Director;
import com.david.bbddCine.bbddCine.modelo.Genero;
import com.david.bbddCine.bbddCine.modelo.Pais;
import com.david.bbddCine.bbddCine.modelo.Pelicula;
import com.mongodb.client.MongoDatabase;

public class App {
    public static void main(String[] args) {
    	//Crear Conexión
        ConexionMongo conexion = new ConexionMongo();
        
        //Listas para recibir los documentos de Mongo
        List<Pais> paises = new ArrayList<>();
        List<Genero> generos = new ArrayList<>();
        List<Actor> actores = new ArrayList<>();
        List<Director> directores = new ArrayList<>();
        List<Pelicula> peliculas = new ArrayList<>();
        
        //Si la conexión es correcta continua (sino salta el try de la Clase ConexionMongo)
        if(conexion.conectar()) {
        	//Recibir la bbdd de mongo a través de la conexión creada
        	MongoDatabase bd = conexion.getDatabase();
        	
        	//Imprimir nombre de bbdd
        	System.out.println(bd.getName());
        	
        	//Obtener la colección pais y mostrar cuantos resultados se obtuvo
        	paises = new PaisDao(bd).obtenerTodosLosPaises();
        	System.out.println("Número de países encontrados: " + paises.size());
        	
        	//Obtener la coleccion genero y mostrar cuantos resultados se obtuvo
        	generos = new GeneroDao(bd).obtenerTodosLosGeneros();
        	System.out.println("Número de países encontrados: " + generos.size());
        	
        	//Obtener la coleccion genero y mostrar cuantos resultados se obtuvo
        	actores = new ActorDao(bd).obtenerTodosLosActores();
        	System.out.println("Número de países encontrados: " + actores.size());
        	
        	//Obtener la coleccion genero y mostrar cuantos resultados se obtuvo
        	directores = new DirectorDao(bd).obtenerTodosLosDirectores();
        	System.out.println("Número de países encontrados: " + directores.size());
        	
        	//Obtener la colección pelicula y mostrar cuantos resultados se obtuvo
        	peliculas = new PeliculaDao(bd).obtenerTodosLasPeliculas();
        	System.out.println("Número de películas encontradas: " + peliculas.size());
        	
	        //Instancia con valor null para recibir los datos de la Clase Pais
	        Pais paisAux = null;
        
	        //Bucle para recorrer el arrayList paises
	        for (Pais pais : paises) {
	        	//Muestra el nombre de los paises
				System.out.println(pais.getNombre());
				
				//Se busca el objeto pais por el nombre de italia y se guarda
				if(pais.getNombre().equalsIgnoreCase("Italia")) {
					paisAux = pais;
				}
			}
        
	        //Buscar el pais de Italia por su _id
	    	Pais pais = new PaisDao(bd).buscarPorId(paisAux.get_id());
	    	System.out.println(pais);
	    	
	    	
	    	/*Agregar un actor (DESCOMENTAR, AGREGAR Y VOLVER A COMENTAR, esta insersión al mostrarse los datos
	    	cargados previamente, no se ve reflejada en Java hasta que se actualice los datos nuevos insertados en la bbdd) */
	    	
	    	/*Actor actAux = new Actor(new ObjectId(), "Robert DeNiro", obtenerIDPais(paises, "España") );
	    	new ActorDao(bd).insertarActor(actAux);*/
	    	
	    	//Después a al elenco de la pelicula
	    	/*for (Pelicula peli : peliculas) {
	    		if (peli.getTitulo().equalsIgnoreCase("Jackie Browm")) {
	    			new PeliculaDao(bd).agregarActorAElenco(peli.get_id(), actAux.get_id());
				}
	    		
			}*/
	    	
    	
	    	//Bucle para recorrer el arrayList peliculas y mostrar por pantalla
	    	for (Pelicula peli : peliculas) {
				System.out.println("Peliculas: \n" + 
									"Título: " + peli.getTitulo() + "\n" + 
									"Director: " + obtenerNombreDirectorPorId(directores, peli.getIdDirector()) + "\n" + 
									"Actores: " + obtenerNombresActoresPorId(actores,peli.getElencoActores()).toString() + " \n" +
									"Año: " + peli.getAnio() + "\n" +
									"Sinopsis: " + peli.getSinopsis() + "\n" +
									"Duracion: " + peli.getDuracion() + " minutos\n" +
									"Género: " + obtenerNombreGeneroPorId(generos, peli.getIdGenero()) + "\n" +
									"País: "+ obtenerNombrePaisPorID(paises, peli.getIdPais()) + "\n");
			}
        }
    	
        conexion.desconectar();
        
       
    }
    
    //Método para obtener el nombre de pais por el Id, para mostrar información "Amigable"
    public static String obtenerNombrePaisPorID(List<Pais> p, ObjectId id) {
    	String nombrePais = " ";
    	for (Pais pais : p) {
			if(pais.get_id().equals(id)) {
				nombrePais = pais.getNombre();
			}
		}
    	return nombrePais;
    }
    
    //Método para obtener el id del pais buscado por su nombre 
    public static ObjectId obtenerIDPais(List<Pais> paisesAux, String nombre) {
    	
    	for (Pais p : paisesAux) {
			if (p.getNombre().equalsIgnoreCase(nombre)) {
				return p.get_id();
			}
		}
    	return null;
    }
    
    //Método para obtener el nombre de genero por el Id, para mostrar información "Amigable"
    public static String obtenerNombreGeneroPorId(List<Genero> g, ObjectId id) {
    	for (Genero gen : g) {
    		if(gen.get_id().equals(id)) {
				return gen.getNombre().name();
			}
		}	
    	return "Género Desconocido";
    }
    
  //Método para obtener el nombre de actores por el Id, para mostrar información "Amigable"
    public static List<String> obtenerNombresActoresPorId(List<Actor> act, List<ObjectId> ids){
    	List<String> nombresActores = new ArrayList<>();
    	for (ObjectId id : ids) {
			for (Actor actor : act) {
				if (actor.get_id().equals(id)) {
					nombresActores.add(actor.getNombre());
				}
			}
		}
    	return nombresActores;
    }
    
    //Método para obtener el nombre del director por el Id, para mostrar información "Amigable"
    public static String obtenerNombreDirectorPorId(List<Director> dire, ObjectId id) {
    	String nombreDirector = " ";
    	for (Director di : dire) {
			if(di.get_id().equals(id)) {
				nombreDirector = di.getNombre();
			}
		}
    	return nombreDirector;
    }

}
