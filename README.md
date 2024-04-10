# API Restful Usuario Proyecto Prueba Yadickson Vasquez

Aplicación API Restful de Usuarios para prueba Java.

## Descripción

Aplicación API Restful de Usuarios para prueba Java desarrollada con Spring Boot 3.2.4 y Java 17, se utilizó la base de datos de memoria H2.

Al iniciar la aplicación se ejecuta automáticamente el script src/main/resources/data.sql que contiene la estructura de las tablas de la base de datos. 

Para acceder a la consola de la base de datos H2 debe ir a http://localhost:8080/h2-console/ el usuario es sa y la clave es vacía.

Endpoints desarrollados:

* <b>Crear Usuario</b>

URL: http://localhost:8080/api/users/<br>
Tipo: POST. <br>
Request Object: UserDto. <br>

Responses: <br>
Status: 201 Usuario creado, objeto respuesta UserResponseDto.<br>
Status 400 Bad Request, error retornado si no se cumplen las validaciones del objeto JSON recibido.<br>

Validaciones:
1. El correo debe seguir una expresión regular para validar que formato sea el correcto. El formato a validar es: aaaaaaa@dominio.cl
2. La clave debe seguir una expresión regular para validar que formato sea el correcto. La expresion regular configurada valida lo siguiente: debe tener al menos una minuscula [a-z], al menos una mayuscula [A-Z], al menos 1 digito [0-9], al menos un simbolo especial  ! @ # & ( ), debe contener minimo longitud 8 y maximo 20 caracteres

Ejemplo:<br>
curl --location 'localhost:8080/api/users/' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=C21FCD01391AE13AD99FF95F0E00DCC1' \
--data-raw '{
 "name": "Juan Rodriguez",
 "email": "juan@dominio.cl",
 "password": "a2rp3d!Kih",
 "phones": [
   {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
    }
  ]
}'

* <b>Obtener Usuarios</b>
Para invocar este enpoint debe utilizar el Token JWT de algún usuario registrado en el sistema por el endpoint de crear usuario.

URL: http://localhost:8080/api/users/users. <br>
Tipo: GET. <br>

Responses: <br>
Status: 200 Lista de UserDto.<br>
Status: 401 En caso de que el token JWT no sea autorizado.<br>

Ejemplo:<br>
curl --location 'localhost:8080/api/users/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5YWRpY2tzb24iLCJzdWIiOiJqdWFuMkBkb21pbmlvLmNsIiwiZXhwIjoxNzEyNzk0NTI4LCJpYXQiOjE3MTI3MDgxMjh9.damlSioZzxd5e-68GhVJsefEEiNRkSi8jFKPd12kgoc' \
--header 'Cookie: JSESSIONID=B516378697F8EECD2D39679B34D94DF0'

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