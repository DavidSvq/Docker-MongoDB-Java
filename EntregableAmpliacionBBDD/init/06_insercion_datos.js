db = db.getSiblingDB("cine_bd");

db.genero.insertOne(
    {
        nombre: "COMEDIA",
        subgeneros: ["Romantica", "Aventuras"]
    }
)

db.genero.insertMany(
    [
        {
            nombre: "DRAMA",
            subgeneros: ["Crimen"]
        },
        {
            nombre: "ACCION",
            subgeneros:["Suspense", "Aventuras", "Bélica"]
        },
        {
            nombre: "TERROR",
            subgeneros:["Psicologico", "Intriga"]
        },
        {
            nombre: "CIENCIA_FICCION",
            subgeneros:["Aventura", "Suspense", "Animada"]
        }
    ]
)

db.pais.insertOne(
    {
        nombre: "España",
        idioma: "Español"
    }
)

db.pais.insertMany(
    [
        {
            nombre: "U.S.A.",
            idioma: "Inlgés"
        },
        {
            nombre: "Francia",
            idioma: "Francés"
        },
        {
            nombre: "U.K.",
            idioma: "Inglés"
        },
        {
            nombre: "Italia",
            idioma: "Italiano"
        }
    ]
)

const usa = db.pais.findOne({nombre: "U.S.A."})._id;
const uk = db.pais.findOne({nombre: "U.K."})._id;
const espania = db.pais.findOne({nombre: "España"})._id;
const francia = db.pais.findOne({nombre: "Francia"})._id;
const italia = db.pais.findOne({nombre: "Italia"})._id;

db.director.insertOne(
    {
        nombre: "Quenttin Tarantino",
        pais: usa
    }
)

db.director.insertMany(
    [
        {
            nombre: "Guy Ricchi",
            pais: uk
        },
        {
            nombre: "Santiago Segura",
            pais: espania
        },
        {
            nombre: "Jean-Pierre Jeunet",
            pais: francia
        },
        {
            nombre: "Roberto Benigni",
            pais : italia
        }
    ]
)

db.actor.insertOne(
    {
        nombre: "Samuel L. Jackson",
        pais: usa
    }

)

db.actor.insertMany(
    [
        {
            nombre: "Aubrey Plaza",
            pais: usa
        },
        {
            nombre: "Audrey Tautou", 
            pais: francia
        },
        {
            nombre: "Nicoleta Braschi",
            pais: italia
        },
        {
            nombre: "Alex Angulo",
            pais: espania
        }
    ]
)

const directorLaVidaEsBella = db.director.findOne({nombre: "Roberto Benigni"})._id;
const actrizLaVidaEsBella = db.actor.findOne({nombre: "Nicoleta Braschi"})._id;
const generoLavidaEsBella = db.genero.findOne({nombre: "DRAMA"})._id;

db.pelicula.insertOne(
    {
        titulo: "La vida es bella",
        director: directorLaVidaEsBella,
        elenco_actores: [actrizLaVidaEsBella],
        anio: 1997,
        sinopsis: "Relata la historia de Guido Orefice, un hombre judío italiano, que usa su ingenio y humor para proteger a su hijo de los horrores del Holocausto en un campo de concentración nazi",
        duracion: 116,
        genero: generoLavidaEsBella,
        pais: italia
    }
)

const directorOP = db.director.findOne({nombre: "Guy Ricchi"})._id;
const actrizOP = db.actor.findOne({nombre: "Aubrey Plaza"})._id;
const generoOP = db.genero.findOne({nombre: "ACCION"})._id;

const directorEDB = db.director.findOne({nombre: "Santiago Segura"})._id;
const actorEDB = db.actor.findOne({nombre: "Alex Angulo"})._id;
const generoEDB = db.genero.findOne({nombre: "COMEDIA"})._id;

const directorAmelie = db.director.findOne({nombre: "Jean-Pierre Jeunet"})._id;
const actrizAmelie = db.actor.findOne({nombre: "Audrey Tautou"})._id;
const generoAmelie = db.genero.findOne({nombre: "COMEDIA"})._id;

const directorJB = db.director.findOne({nombre: "Quenttin Tarantino"})._id;
const actorJB = db.actor.findOne({nombre: "Samuel L. Jackson"})._id;
const generoJB = db.genero.findOne({nombre: "ACCION"})._id;

db.pelicula.insertMany(
    [
        {
           titulo: "Operación Fortune",
            director: directorOP,
            elenco_actores: [actrizOP],
            anio: 2023,
            sinopsis: "El espía de élite Orson Fortune debe localizar y detener la venta de una nueva y mortífera tecnología armamentística que maneja un multimillonario traficante de armas. Fortune y su equipo reclutan a la mayor estrella de Hollywood para que les ayude.",
            duracion: 114,
            genero: generoOP,
            pais: uk 
        },
        {
           titulo: "El dia de la bestia",
            director: directorEDB,
            elenco_actores: [actorEDB],
            anio: 1995,
            sinopsis: "Un sacerdote cree haber descifrado el mensaje secreto del Apocalipsis: el Anticristo nacerá el 25 de diciembre de 1995 en Madrid. Para impedir el nacimiento del hijo de Satanás, el cura se alía con José María, un joven aficionado al death metal.",
            duracion: 103,
            genero: generoEDB,
            pais: espania 
        },
        {
           titulo: "Amelie",
            director: directorAmelie,
            elenco_actores: [actrizAmelie],
            anio: 1997,
            sinopsis: "Amélie, una inocente e ingenua camarera parisina con una imaginación vívida, no es una chica como las demás. A sus 22 años, la joven chica descubre que su propósito en la vida es ayudar a otros. Por ello, el hallazgo de un tesoro olvidado pone a Amélie a cuestionar y alterar la vida de quienes la rodean.",
            duracion: 122,
            genero: generoAmelie,
            pais: francia 
        },
        {
           titulo: "Jackie Browm",
            director: directorJB,
            elenco_actores: [actorJB],
            anio: 1997,
            sinopsis: "Jackie incrementa su sueldo de azafata haciendo de correo para el mafioso Ordell Robbie, hasta que un agente de aduanas y un policía la atrapan.",
            duracion: 154,
            genero: generoJB,
            pais: usa 
        }

    ]
)
