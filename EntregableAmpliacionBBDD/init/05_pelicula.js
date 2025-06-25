db = db.getSiblingDB("cine_bd");

db.createCollection(
    "pelicula", {
        validator: {
            $jsonSchema:{
                bsonType: "object",
                required: ["titulo", "director", "elenco_actores","anio", "sinopsis", "duracion", "genero", "pais"],
                properties:{
                    titulo:{
                        bsonType: "string",
                        pattern: "^.{1,}$",
                        description:"Debe ser una cadena con al menos un caracter"
                    },
                    director: {
                        bsonType: "objectId",
                        description: "Id de la Clase Director"
                    },
                    elenco_actores:{
                        bsonType: "array",
                        items:{
                            bsonType: "objectId"
                        },
                        description: "Elenco de actores que participan en la pelicula"
                    },
                    anio:{
                        bsonType: "int",
                        minimum: 1900,
                        description: "Debe ser un año posterior a la invención del cine"
                    },
                    sinopsis: {
                        bsonType: "string",
                        pattern: "^.{10,}$",
                        description:"Debe ser una cadena de 10 o más un caracteres"
                    },
                    duracion:{
                        bsonType: "int",
                        minimum: 80,
                        description: "Debe ser de 80 minutos o más para considerarse película"
                    },
                    genero: {
                        bsonType: "objectId",
                        description: "Id de la Clase Genero"
                    },
                    pais:{
                        bsonType: "objectId",
                        description: "Id de la Clase Pais"
                    }
                }
            }
        },
        validationLevel: "strict",
        validationAction: "error"
    }
);