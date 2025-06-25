db = db.getSiblingDB("cine_bd");

db.createCollection(
    "pais", {
        validator: {
            $jsonSchema:{
                bsonType: "object",
                required: ["nombre"],
                properties:{
                   nombre:{
                    bsonType: "string",
                    pattern: "^.{3,}$",
                    description: "Debe ser una cadena con 3 o más caracteres"
                   },
                   idioma:{
                    bsonType: "string",
                    pattern: "^.{3,}$",
                    description: "Debe ser una cadena con 3 o más caracteres"
                   } 
                }
            }
        },
        validationLevel: "strict",
        validationAction: "error"
    }
)