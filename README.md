# BasicFood 🍕

Tienda de comida virtual de clase. El objetivo es tener un **back en Java** y un
**front sencillo**, todo en el mismo proyecto y sin Spring Boot todavía.

El front es HTML generado en el servidor con **JSP**. No hay API REST, ni JSON,
ni `fetch`, ni JavaScript: los formularios hacen `POST` y el servidor vuelve a
pintar la página. Un monolito clásico.

## Cómo está montado

```
src/main/java/com/basicfood/
├── Main.java                 ← la app de consola de siempre (intacta)
├── api/                      ← Producto y Carrito
├── controller/               ← ProductoController y CarritoController
├── service/                  ← lógica y datos en memoria
└── web/                      ← capa web nueva
    ├── WebMain.java          ← arranca Tomcat en local
    ├── CatalogoServlet.java  ← pinta catálogo + carrito
    ├── CarritoAddServlet.java
    ├── CarritoRemoveServlet.java
    ├── CarritoVaciarServlet.java
    ├── CarritoFinalizarServlet.java
    ├── CarritoDeSesion.java  ← un carrito por navegador
    └── model/                ← clases solo para pintar la JSP

src/main/webapp/
├── WEB-INF/views/catalogo.jsp
├── css/estilos.css
└── favicon.svg
```

## Arrancar en local

Requisitos: **Java 17+** y **Maven**.

```bash
mvn compile exec:java
```

Abre <http://localhost:8080>.

La app de consola sigue funcionando:

```bash
mvn compile
java -cp target/classes com.basicfood.Main
```

## Rutas

| Método | Ruta                | Qué hace                          |
|--------|---------------------|-----------------------------------|
| GET    | `/` o `/catalogo`   | catálogo + carrito                |
| POST   | `/carrito/add`      | añade un producto                 |
| POST   | `/carrito/remove`   | quita una unidad                  |
| POST   | `/carrito/vaciar`   | vacía el carrito                  |
| POST   | `/carrito/finalizar`| confirma el pedido y lo vacía     |

Después de cada `POST` se redirige a `/catalogo` (patrón POST/Redirect/GET), así
que refrescar el navegador no duplica la compra.

## Dónde vive el carrito

En la `HttpSession` de cada navegador. `CarritoDeSesion` crea un
`CarritoController` la primera vez y lo guarda en la sesión. El tope de 20
productos lo sigue controlando `CarritoService`, como en la consola.

## Compilar el WAR

```bash
mvn clean package
```

Genera `target/basicfood.war`. Ese WAR se puede subir a cualquier Tomcat 10.1.

## Desplegar en Render

El repo ya trae `Dockerfile` y `render.yaml`.

1. Sube los cambios a GitHub.
2. Entra en <https://render.com> y crea una cuenta (el plan free no pide tarjeta).
3. **New → Blueprint**, conecta el repositorio y aplica el `render.yaml`.
4. Render construye la imagen y te da una URL pública.

Notas del plan free:

- El servicio **se duerme** a los 15 minutos sin visitas. La primera carga
  después puede tardar alrededor de un minuto.
- No hay base de datos: los productos y los carritos viven en memoria, así que
  se reinician cuando el servicio se reinicia.

También puedes probar la imagen en local si tienes Docker:

```bash
docker build -t basicfood .
docker run --rm -p 8080:8080 basicfood
```

## Cuando llegue Spring Boot

La idea es que este front no se tire a la basura. El equivalente sería:

| Ahora (JSP)                  | Con Spring Boot                     |
|------------------------------|-------------------------------------|
| `CatalogoServlet`            | `@Controller` + `@GetMapping`       |
| `CarritoAddServlet`          | `@PostMapping("/carrito/add")`      |
| `CarritoDeSesion`            | bean con `@SessionScope`            |
| `catalogo.jsp`               | Thymeleaf (misma estructura HTML)   |
| Tomcat embebido en `WebMain` | Tomcat embebido de Spring Boot      |

## Ejercicios para el alumno

1. Añadir un producto nuevo en `ProductoService` y comprobar que aparece solo.
2. Poner el tope del carrito en 5 y probar el aviso de carrito lleno.
3. Añadir un botón para quitar **todas** las unidades de un producto.
4. Cambiar los colores en `css/estilos.css` usando las variables de `:root`.
5. Calcular el total en `CarritoService` en vez de en el servlet.
