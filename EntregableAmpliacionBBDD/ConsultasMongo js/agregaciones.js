db.getCollection('pelicula').aggregate(
  [
    {
      $lookup: {
        from: 'director',
        localField: 'director',
        foreignField: '_id',
        as: 'director'
      }
    }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);


db.getCollection('pelicula').aggregate(
  [
    { $match: { anio: { $gte: 2000 } } },
    {
      $lookup: {
        from: 'director',
        localField: 'director',
        foreignField: '_id',
        as: 'director'
      }
    },
    {
      $unwind: {
        path: '$director',
        includeArrayIndex: 'string',
        preserveNullAndEmptyArrays: true
      }
    },
    {
      $project: {
        _id: 0,
        titulo: 1,
        director_pelicula: '$director.nombre',
        minutos_de_duracion: '$duracion'
      }
    }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);




db.getCollection('pelicula').aggregate(
  [
    { $match: { anio: { $gte: 2000 } } },
    {
      $lookup: {
        from: 'director',
        localField: 'director',
        foreignField: '_id',
        as: 'director'
      }
    },
    {
      $unwind: {
        path: '$director',
        includeArrayIndex: 'string',
        preserveNullAndEmptyArrays: true
      }
    },
    {
      $lookup: {
        from: 'actor',
        localField: 'elenco_actores',
        foreignField: '_id',
        as: 'elenco_actores'
      }
    },
    {
      $unwind: {
        path: '$elenco_actores',
        includeArrayIndex: 'string',
        preserveNullAndEmptyArrays: true
      }
    },
    {
      $lookup: {
        from: 'genero',
        localField: 'genero',
        foreignField: '_id',
        as: 'genero'
      }
    },
    {
      $unwind: {
        path: '$genero',
        includeArrayIndex: 'string',
        preserveNullAndEmptyArrays: true
      }
    },
    {
      $lookup: {
        from: 'pais',
        localField: 'pais',
        foreignField: '_id',
        as: 'pais'
      }
    },
    {
      $unwind: {
        path: '$pais',
        includeArrayIndex: 'string',
        preserveNullAndEmptyArrays: true
      }
    },
    {
      $project: {
        _id: 0,
        titulo: 1,
        director_pelicula: '$director.nombre',
        elenco_actores: '$elenco_actores.nombre',
        anio: 1,
        sinopsis: 1,
        duracion: 1,
        genero: '$genero.nombre',
        pais: '$pais.nombre'
      }
    }
  ],
  { maxTimeMS: 60000, allowDiskUse: true }
);


