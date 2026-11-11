<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="es_ES" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BasicFood · Tienda de comida virtual</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.svg" type="image/svg+xml">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<header class="cabecera">
    <div class="cabecera__interior">
        <a class="marca" href="${pageContext.request.contextPath}/catalogo">
            <span class="marca__logo" aria-hidden="true">B</span>
            <span class="marca__texto">
                <strong>BasicFood</strong>
                <small>Tienda de comida virtual</small>
            </span>
        </a>

        <div class="cabecera__carrito" aria-label="Productos en el carrito">
            <span aria-hidden="true">🛒</span>
            <span><strong>${cantidad}</strong> / ${tope}</span>
        </div>
    </div>
</header>

<main class="contenido">

    <section class="hero">
        <div>
            <p class="hero__etiqueta">Proyecto de clase · Java + JSP</p>
            <h1>¿Qué te apetece hoy?</h1>
            <p class="hero__texto">
                Añade productos al carrito y revisa el total al instante.
                Tu carrito se guarda en la sesión del navegador.
            </p>
        </div>
        <div class="hero__nota">
            <span aria-hidden="true">📦</span>
            <p>Máximo <strong>${tope}</strong> productos por pedido.</p>
        </div>
    </section>

    <c:if test="${not empty mensaje}">
        <div class="aviso aviso--${mensajeTipo}" role="status" aria-live="polite">
            <span aria-hidden="true">
                <c:choose>
                    <c:when test="${mensajeTipo == 'error'}">⚠️</c:when>
                    <c:otherwise>✅</c:otherwise>
                </c:choose>
            </span>
            <span><c:out value="${mensaje}" /></span>
        </div>
    </c:if>

    <div class="tienda">

        <section class="catalogo" aria-labelledby="titulo-catalogo">
            <div class="seccion__cabecera">
                <h2 id="titulo-catalogo">Catálogo</h2>
                <span class="seccion__cuenta">${productos.size()} productos</span>
            </div>

            <div class="rejilla">
                <c:forEach var="producto" items="${productos}">
                    <article class="producto">
                        <div class="producto__emoji" aria-hidden="true">${producto.emoji}</div>

                        <div class="producto__info">
                            <h3><c:out value="${producto.nombre}" /></h3>
                            <p><c:out value="${producto.descripcion}" /></p>
                        </div>

                        <div class="producto__pie">
                            <span class="precio">
                                <fmt:formatNumber value="${producto.precio}" minFractionDigits="2" maxFractionDigits="2" /> €
                            </span>

                            <form method="post" action="${pageContext.request.contextPath}/carrito/add">
                                <input type="hidden" name="nombre" value="<c:out value='${producto.nombre}' />">
                                <button class="boton boton--anadir" type="submit">
                                    Añadir
                                </button>
                            </form>
                        </div>
                    </article>
                </c:forEach>
            </div>
        </section>

        <aside class="carrito" aria-labelledby="titulo-carrito">
            <div class="carrito__cabecera">
                <h2 id="titulo-carrito">Tu carrito</h2>
                <span class="contador">${cantidad} / ${tope}</span>
            </div>

            <c:choose>
                <c:when test="${empty lineas}">
                    <div class="carrito__vacio">
                        <span class="carrito__vacio-icono" aria-hidden="true">🛒</span>
                        <p>Todavía no has añadido nada.</p>
                        <small>Elige algo rico del catálogo.</small>
                    </div>
                </c:when>

                <c:otherwise>
                    <ul class="lineas">
                        <c:forEach var="linea" items="${lineas}">
                            <li class="linea">
                                <span class="linea__emoji" aria-hidden="true">${linea.emoji}</span>

                                <div class="linea__datos">
                                    <strong><c:out value="${linea.nombre}" /></strong>
                                    <small>
                                        ${linea.cantidad} ×
                                        <fmt:formatNumber value="${linea.precio}" minFractionDigits="2" maxFractionDigits="2" /> €
                                    </small>
                                </div>

                                <span class="linea__subtotal">
                                    <fmt:formatNumber value="${linea.subtotal}" minFractionDigits="2" maxFractionDigits="2" /> €
                                </span>

                                <form method="post" action="${pageContext.request.contextPath}/carrito/remove">
                                    <input type="hidden" name="nombre" value="<c:out value='${linea.nombre}' />">
                                    <button class="boton-quitar" type="submit"
                                            title="Quitar una unidad"
                                            aria-label="Quitar una unidad de ${linea.nombre}">
                                        ×
                                    </button>
                                </form>
                            </li>
                        </c:forEach>
                    </ul>

                    <div class="carrito__total">
                        <span>Total</span>
                        <strong>
                            <fmt:formatNumber value="${total}" minFractionDigits="2" maxFractionDigits="2" /> €
                        </strong>
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/carrito/finalizar">
                        <button class="boton boton--comprar" type="submit">
                            Finalizar compra
                        </button>
                    </form>

                    <form method="post" action="${pageContext.request.contextPath}/carrito/vaciar">
                        <button class="boton-texto" type="submit">Vaciar carrito</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </aside>

    </div>
</main>

<footer class="pie">
    <p>Proyecto de clase · <strong>BasicFood</strong> · Java + JSP, sin Spring Boot todavía.</p>
</footer>

</body>
</html>
