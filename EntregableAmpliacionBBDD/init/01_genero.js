db = db.getSiblingDB("cine_bd");

db.createCollection(
    "genero" ,{
        validator:{
            $jsonSchema:{
                bsonType: "object",
                required: ["nombre"],
                properties: {
                    nombre: {
                        bsonType: "string",
                        enum: ["ACCION", "COMEDIA", "DRAMA", "TERROR", "CIENCIA_FICCION", "FANTASIA"],
                        description: "Debe ser un valor del enum GeneroEnum en Java"
                    },
                    subgeneros:{
                        bsonType: "array",
                        items:{
                            bsonType: "string",
                            pattern: "^.{3,}$",
                            description:"Debe ser una cadena con 3 o más caracteres"
                        },
                        description: "Lista de subgeneros como strings"
                    }
                }
            }
        },
        validationLevel: "strict",
        validationAction: "error"
    }
)



