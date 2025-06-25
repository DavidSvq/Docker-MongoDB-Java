# Docker-MongoDB-Java
Trabajo de ampliación de nota en BBDD
# Proyecto MongoDB + Java (sin frameworks)

Este proyecto levanta una base de datos MongoDB con un contenedor Docker, carga datos iniciales usando varios scripts JS en la carperta (`init/`), y se conecta desde una aplicación Java pura (sin frameworks como Spring).

## 🧩 Tecnologías usadas
- Docker
- MongoDB
- Java SE (con MongoDB Java Driver)
  
## Esta entrega contiene 5 archivos adjuntos, 3 carpetas, 1 documento PDF sobre el proyecto y este readme.txt.
## Las carpetas contienen:

	1. INIT, esta carpeta contiene los script para levantar el contenedor junto a la base de datos, además incluye 5 inserciones por Colección. Para seguir los pasos necesarios, se recomienda acudir al punto 3.1 Consideraciones Clave. También se recomienda, visualizar antes el punto 2.1 Requisitos.
	
	2. CONSULTAS MONGO JS, esta carpeta contiene las agregaciones generadas en MongoDB Compass, que facilita la exportación del código generado (solo las agregaciones son código generado por Compass de todos los scripts enviados en el proyecto).

	3. JAVA, esta carpeta contiene los archivos.java (entrega estándar con Java en programación este curso) necesarios para conectar con la BBDD, modelar las clases de los documentos, crear las clases DAO (Data Access Object) respectivas para operaciones CRUD(insertar, eliminar, modificar y consultar). Así como una pequeña clase App (main) donde se muestra la conexión, y algunas operaciones CRUD y usos de clases que aporta el Mongo Java Driver.

## El Archivo PDF:

	4. AMPLIACION BBDD.PDF, contiene la documentación técnica para el montaje del contenedor en Docker, conexión con MongoDB Compass y el contenedor. Además, explica las estructura y motivo por el que se decidió dicha estructura. También ofrece breves explicaciones, sobre las NoSQL, pero en concreto sobre MongoDB y su entorno (comandos, peculiaridades, etc...). Finalmente durante todo el documento se complementa con capturas de pantalla para aportar una mejor compresión del documento. Para acabar dando una conclusión tras la realización de este proyecto.

	5. LEER EN PRIMER LUGAR, contiene este archivo.txt. 


