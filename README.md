# coopeuch
Test prueba tecnica

# Arquitectura
Base Datos MYSQL:
CREATE DATABASE `coopeuch` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

# Registros para pruebas, cada vez que inicia la aplicación crea y borra la tabla tareas
insert into coopeuch.tareas values ('1','tarea1', now(),1);
insert into coopeuch.tareas values ('2','tarea2', now(),1);
insert into coopeuch.tareas values ('3','tarea3', now(),0);
insert into coopeuch.tareas values ('4','tarea2', now(),1);
insert into coopeuch.tareas values ('5','tarea3', now(),0);

# Abrir archivo CoopeuchApplication y dar run como springboot.app 
