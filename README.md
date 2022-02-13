# ReWatchAppProject
##  ¿De qué se trata?
        Esta aplicación es un proyecto sin fines de lucro que está siendo desarrollado con el único propósito
        personal de ganar experiencia en el desarrollo de aplicaciones web, poner a prueba mis habilidades, aprender
        y disfrutar en el proceso. Espero que tengas una buena experiencia en esta página y que sea de tu agrado.
        Cualquier indicación, sugerencia o crítica es más que binvenida.
        
## Funcionamiento
        Gracias al uso de APIs como IMDb, esta aplicacion obtiene información sobre peliculas y series 
        para que el usuario pueda interactuar con ellas gestionando sus listas de favoritas, ya vistas y 
        su lista de espera, tambien podrá puntuar y dejar su opinión para que otros usuarios puedan conocerla.
        El usuario tendrá la opción de buscar listas de películas y series y también podrá utilizar la barra
        de búsqueda para encontrar los títulos que busca.
        
## Limitaciones
        El uso de la API de IMDb de forma gratuita es limitado por lo que para evitar exceder ese límite 
        de peticiones, cada vez que un usuario interactua con una pelicula o serie, se guarda la información
        de la misma en una base de datos propia de manera temporal así, el proximo usuario que interactue con
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
