# API Restful Usuario Proyecto Prueba Yadickson Vasquez

Aplicación API Restful de Usuarios para prueba Java.

## Descripción

Aplicación API Restful de Usuarios para prueba Java desarrollada con Spring Boot 3.2.4 y Java 17, se utilizó la base de datos de memoria H2.

Al iniciar la aplicación se ejecuta el script /resources/data.sql que contiene la estructura de la base de datos. Para acceder a la consola de la base de datos H2 debe ir a http://localhost:8080/h2-console/ el usuario es sa y la clave es vacía.

Endpoints desarrollados:

* Crear Usuario

URL: http://localhost:8080/api/users/users. <br>
Tipo: POST. <br>
Request Object: UserDto. <br>

Responses: <br>
Status: 201 Usuario creado, objeto respuesta UserResponseDto


## Iniciar Aplicación
Run MsOauth2AuthorizationServerUtilApplication

### Dependencias

* spring-boot-starter-security
* spring-boot-starter-data-jpa
* spring-boot-starter-web
* lombok
* jakarta.validation-api


## Author

yadicksonvasquez@gmail.com

## Version History

* 1.0