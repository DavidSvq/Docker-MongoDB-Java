db = db.getSiblingDB("cine_bd");

db.createCollection(
    "actor",{
        validator:{
            $jsonSchema:{
                bsonType: "object",
                required: ["nombre", "pais"],
                properties: {
                    nombre:{
                        bsonType: "string",
                        pattern: "^.{1,}$",
                        description: "Debe ser una cadena caracteres con al menos 1 caracter"
                    },
                    pais:{
                        bsonType: "objectId",
                        description: "Id de la Clase Pais"
                    },
                    peliculas:{
                        bsonType: "array",
                        items: {
                            bsonType: "objectId"
                        },
                        description: "Filmografia del actor"
                    }
                }
            }
        },
        validationLevel: "strict",
        validationAction: "error"
    }
);