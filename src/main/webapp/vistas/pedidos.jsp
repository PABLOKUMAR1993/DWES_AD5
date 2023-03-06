<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <title>Raúl & Pavlo | Venta de camisetas de Star Wars</title>
</head>
<body>


<!-- Barra de navegación -->
<jsp:include page="nav.jsp"></jsp:include>


<!-- Portada -->
<div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://wallpaperaccess.com/full/11792.jpg"
                 class="d-block w-100" alt="imágen de portada para la página de inicio">
            <div class="carousel-caption d-none d-md-block">
                <h1 class="display-1">Pedidos</h1>
                <h1 class="display-5">Aquí tienes tu historial de pedidos.</h1>
            </div>
        </div>
    </div>
</div>


<!-- Título -->
<figure class="text-center" style="margin-top: 50px;">
    <h1 class="display-1">Tus Pedidos</h1>
</figure>


<!-- Si no tienes pedidos -->
<c:if test="${infoList == '[]'}">
    <div class="d-flex justify-content-center" style="margin: 50px 0 50px 0;">
        <div class="container">
            <div class="card text-center">
                <div class="card-header">
                    Pedidos Completados
                </div>
                <div class="card-body" style="margin: 25px 0 25px 0;">
                    <h5 class="card-title">¡Oh vaya! parece que no has realizado ninguna compra todavía.</h5>
                    <p class="card-text">Puedes ir a explorar nuestro catálogo y hacer tu primer pedido.</p>
                </div>
                <div class="card-footer text-muted">
                    <a href="/catalogo" class="btn btn-primary" style="margin: 10px 0 10px 0;">
                        Ir al catálogo
                    </a>
                </div>
            </div>
        </div>
    </div>
</c:if>


<!-- Si tienes pedidos -->
<c:if test="${infoList != '[]'}">
    <div class="d-flex justify-content-center" style="margin: 100px 0 100px 0;">
        <div class="container">
            <div class="card text-center">
                <div class="card-header">
                    Pedidos Completados
                </div>
                <div class="card-body" style="margin: 25px 0 25px 0;">
                    <div class="container text-center">
                        <div class="row">
                            <div class="col">
                                <c:forEach items="${pedidos}" var="pedido">
                                    <c:if test="${pedido.estado == 'completado'}">
                                        <div class="card w-100" style="margin: 0 0 25px 0;">
                                            <div class="card-body">
                                                <table class="table table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Fecha</th>
                                                        <th>Estado</th>
                                                        <th>Total</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td>${pedido.idPedido}</td>
                                                        <td>${pedido.fecha}</td>
                                                        <td>${pedido.estado}</td>
                                                        <c:forEach var="total" items="${totales}">
                                                            <c:if test="${total.idPedido == pedido.idPedido}">
                                                                <td>${total.totalPedido} $</td>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4">
                                                            <table class="table mb-0">
                                                                <tbody>
                                                                <tr>
                                                                    <th>Imagen</th>
                                                                    <th>Nombre</th>
                                                                    <th>Precio</th>
                                                                    <th>Cantidad</th>
                                                                </tr>
                                                                <c:forEach items="${infoList}" var="infoListo">
                                                                    <c:if test="${infoListo.idPedido == pedido.idPedido}">
                                                                        <tr>
                                                                            <td>
                                                                                <img src="${infoListo.imagen}"
                                                                                     alt="imagen de la camiseta"
                                                                                     style="width: 100px; height: 100px;">
                                                                            </td>
                                                                            <td class="align-middle">${infoListo.nombreProducto}</td>
                                                                            <td class="align-middle">${infoListo.precioUnitario}</td>
                                                                            <td class="align-middle">${infoListo.cantidad}</td>
                                                                        </tr>
                                                                    </c:if>
                                                                </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>


<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>