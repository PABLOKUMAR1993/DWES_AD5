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
            <img src="https://i.gyazo.com/a31fc6b222841e2d292e4d0d8436db40.jpg"
                 class="d-block w-100" alt="imágen de portada para la página de inicio">
        </div>
    </div>
</div>


<!-- Título -->
<figure class="text-center" style="padding: 50px 0 50px 0;">
    <h1 class="display-1">Carrito de la Compra</h1>
</figure>


<!-- Alertas -->
<c:if test="${ mensajeCarritoEliminarOk != null }">
    <div class="d-flex justify-content-center" style="margin: 25px 0 25px 0;">
        <div class="alert alert-success" role="alert">
                ${ mensajeCarritoEliminarOk }
        </div>
    </div>
</c:if>
<c:if test="${ mensajeCarritoActualizarOk != null }">
    <div class="d-flex justify-content-center" style="margin: 25px 0 25px 0;">
        <div class="alert alert-success" role="alert">
                ${ mensajeCarritoActualizarOk }
        </div>
    </div>
</c:if>


<!-- Si no tienes productos en la cesta -->
<c:if test="${productos == '[]'}">
    <div class="d-flex justify-content-center" style="margin: 50px 0 50px 0;">
        <div class="container">
            <div class="card text-center">
                <div class="card-header">
                    Subtotal: 0 $
                </div>
                <div class="card-body" style="margin: 25px 0 25px 0;">
                    <h5 class="card-title">¡Oh vaya! parece que no has añadido ningún producto a la cesta todavía.</h5>
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


<!-- Productos -->
<c:if test="${productos != '[]'}">
    <div class="d-flex justify-content-center" style="margin: 50px 0 100px 0;">
        <div class="container">
            <div class="card text-center">
                <div class="card-header">
                    Subtotal: ${subtotal}$
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Imágen</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">Eliminar</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${productos}" var="producto">
                        <tr>
                            <th scope="row" class="align-middle">${producto.producto.idProducto}</th>
                            <td>
                                <img src="${producto.producto.imagen}" class="img-thumbnail"
                                     alt="imagen del producto" style="width: 150px;"/>
                            </td>
                            <td class="align-middle">${producto.producto.nombre}</td>
                            <td class="align-middle">${producto.producto.precio} $</td>
                            <td class="align-middle">
                                <form method="get" action="/actualizarCantidad/${producto.producto.idProducto}">
                                    <div class="d-flex justify-content-center">
                                        <select class="form-select" name="cantidad"
                                                style="width: 25%; margin-right: 10px;">
                                            <option value="${producto.cantidad}" selected>${producto.cantidad}</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                            <option value="7">7</option>
                                            <option value="8">8</option>
                                            <option value="9">9</option>
                                            <option value="10">10</option>
                                        </select>
                                        <button class="btn btn-success">
                                            Actualizar
                                        </button>
                                    </div>
                                </form>
                            </td>
                            <td class="align-middle">
                                <form method="get" action="/eliminarProducto/${producto.producto.idProducto}">
                                    <button class="btn btn-danger">eliminar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="card-footer text-muted">
                    <a href="/vaciarCesta" class="btn btn-danger" style="margin: 10px 0 10px 0;">
                        Vaciar la Cesta
                    </a>
                    <a href="/direccionEnvioPedido" class="btn btn-primary" style="margin: 10px 0 10px 0;">
                        Continuar con la Dirección
                    </a>
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