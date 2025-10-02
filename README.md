Backend Ecommerce Perfumes
Proyecto desarrollado en la asignatura de Fullstack que consiste en una base para un ecommerce del rubro de perfumes.

Descripción
En esta primera instancia se trabajó en el backend, logrando estructurar una solución para un cliente del sector de venta de perfumes. El cliente contaba con un sistema monolítico que no daba abasto, por lo que se propuso implementar microservicios.

La solución fue desarrollada con Java y Spring Boot, usando MySQL como base de datos y CSS con Bootstrap para diseño básico. Este proyecto base permite comprender y trabajar con un backend full REST, siendo una demostración funcional como punto de partida para futuros desarrollos.

Características
Backend construido con microservicios en Spring Boot.

Comunicación mediante servicios RESTful.

Gestión de base de datos en MySQL.

Base para la futura integración con frontend.

Arquitectura modular para escalabilidad y mantenimiento.

Tecnologías
Java 11+

Spring Boot

MySQL

Bootstrap (para estilos CSS básicos)

Maven (gestor de dependencias)

Instalación
Para correr el proyecto localmente:

Clonar este repositorio

text
git clone https://github.com/usuario/proyecto-backend-ecommerce.git
Configurar base de datos MySQL y actualizar las propiedades en application.properties

Ejecutar con Maven

text
mvn spring-boot:run
El backend estará disponible en http://localhost:8080

Uso
Se puede consumir el backend mediante peticiones REST para gestionar perfumes, usuarios, pedidos y más.

Ejemplo de petición GET para obtener lista de perfumes:

text
GET http://localhost:8080/api/perfumes
Estado del Proyecto
🚧 Proyecto en desarrollo y enfocado en primeros pasos de backend con microservicios.

Contribuciones
Las contribuciones son bienvenidas mediante pull requests y reporte de issues.

Screenshots
<img width="1072" height="713" alt="image" src="https://github.com/user-attachments/assets/187cd0f2-408c-4ff0-bce2-f7edf2c38a58" />
<img width="1365" height="641" alt="image" src="https://github.com/user-attachments/assets/cb26dcba-e353-4588-a13c-2eebf801bb95" />
<img width="1349" height="631" alt="image" src="https://github.com/user-attachments/assets/8f891147-e331-443c-9e45-dffa260d3220" />
<img width="1362" height="634" alt="image" src="https://github.com/user-attachments/assets/75078f76-a93c-4868-8710-a105994cb700" />
<img width="1365" height="629" alt="image" src="https://github.com/user-attachments/assets/de854a3e-0dd7-43a3-8ba0-53a3fdbac8b7" />

