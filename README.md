# ReWatchAppProject
##  ¿De qué se trata?
        La idea de esta apliacion es presentar al usuario una forma de acceder a información sobre peliculas y series, 
        y crear una cuenta en la cual podrá gestionar listas (en el futuro tambien podrá gestionar sus gustos para 
        obtener recomendaciones).
        
        En la página inicial se explica el propósito, funcionamiento y las limitaciones de la aplicación. 
        
        Cada usuario deberá registrarse con nombre, apellido, e-mail, fecha de nacimiento y contraseña.
        
        Una vez logueado (con email y contraseña) se accede al Home donde se encuentran listas actualizadas 
        de las mejores películas, las mas populares y los estrenos.
        
        Al acceder a la información y detalles propios de cada película se le da al usuario la opción de 
        agregarlas a tres listas: "Favoritas", "Ya Vistas" y "de Espera".
        
        En la sección de "Mi Perfil" el usuario puede consultar sus listas previamente mencionadas y gestionarlas.
        
## Funcionamiento
        Gracias al uso de APIs como IMDb, esta aplicación obtiene información sobre películas y series 
        para que el usuario pueda interactuar con ellas gestionando sus listas de favoritas, ya vistas y 
        su lista de espera.
        
        El usuario tendrá la opción de buscar listas de películas y series y también podrá utilizar la barra
        de búsqueda para encontrar los títulos que desea (No disponible de momento).
        
        Las listas que se muestran en el Home tienen permitido actualizarse cada 7 días para evitar el exceso
        de peticiones, estas quedan guardadas en la base de datos durante estos 7 días 
        
## Limitaciones
        El uso de la API de IMDb de forma gratuita es limitado por lo que para evitar exceder ese límite 
        de peticiones, cada vez que un usuario interactúa con una película o serie, se guarda la información
        de la misma en una base de datos propia de manera temporal así, el próximo usuario que interactúe con
        la misma media, accederá a su contenido mediante el uso de la base de datos propia evitando de esta 
        manera el uso de la API.
        
        Sigue siendo posible que las peticiones disponibles se agoten impidiendo el funcionamiento apropiado
        de la aplicación.
        
## Tecnologías utilizadas
        .JAVA
        .Spring
        .JPA
        .Hibernate
        .MySQL
        .Thymeleaf
